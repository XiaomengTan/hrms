package com.hrms.filter;

import com.alibaba.fastjson.JSONObject;
import com.hrms.domain.common.entity.User;
import com.hrms.domain.common.pojo.Result;
import com.hrms.user.mapper.UserMapper;
import com.hrms.utils.JwtUtils;
import com.hrms.utils.ThreadLocalUtil;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            try {
                String redisToken = stringRedisTemplate.opsForValue().get(token);
                if (!StringUtils.hasLength(redisToken)) {
                    //token失效了
                    throw new RuntimeException("token验证失败，因token已经失效，请重新登录");
                }
                Jws jws = JwtUtils.parseJwt(token);
                Map<String, Object> claims;
                claims = (Map<String, Object>) jws.getPayload();

                ThreadLocalUtil.set(claims);

                Integer userId = (Integer) claims.get("id");
                String role = claims.get("role").toString();

                User user = userMapper.selectById(userId);


                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(role));

                UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                        String.valueOf(userId), user.getPassword(), authorities);
//                log.info("执行到0");
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
//                log.info("执行到1");
            } catch (Exception e) {
                // token无效，忽略
//                log.error(e.getMessage());
//                log.info("解析令牌失败，返回未登录错误信息");
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                Result error = Result.error("登录已过期，请重新登录");
//                String notLogin = JSONObject.toJSONString(error);//手动将对象转换为json格式，借助阿里巴巴fastJSON工具依赖
//                response.getWriter().write(notLogin);
                log.error("Token validation failed: {}", e.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                Result error = Result.error("登录已过期，请重新登录");
                response.getWriter().write(JSONObject.toJSONString(error));
                return;  // 阻止继续filterChain.doFilter
            }
        }
//        log.info("执行到2");
        filterChain.doFilter(request, response);
//        log.info("执行到3");
    }
}