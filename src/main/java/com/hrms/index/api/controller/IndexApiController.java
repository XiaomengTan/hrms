package com.hrms.index.api.controller;

import com.hrms.domain.common.enums.UserRoleEnum;
import com.hrms.index.api.IndexApi;
import com.hrms.domain.common.pojo.Result;
import com.hrms.domain.user.dto.LoginFormDTO;
import com.hrms.domain.user.dto.RegisterFormDTO;
import com.hrms.index.service.IndexService;
import com.hrms.utils.EmailSenderUtils;
import com.hrms.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.hrms.domain.common.pojo.Result.success;

@Slf4j
@RestController
public class IndexApiController implements IndexApi {

    @Autowired
    private IndexService indexService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private EmailSenderUtils emailSenderUtils;

    @Override
    public Result login(@RequestBody LoginFormDTO loginFormDTO) {
        //连接成功
        log.info("connect success with: {}", loginFormDTO);
        User user = indexService.login(loginFormDTO);
        if (user != null) {
            //生成jwt
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getId());
//            log.info(UserRoleEnum.USER.getRole());
            claims.put("role", UserRoleEnum.USER.getRole());
            String jwt = JwtUtils.generateJwt(claims);
            //将token存储到redis中
            stringRedisTemplate.opsForValue().set(jwt, jwt, 1, TimeUnit.HOURS);
            return Result.success(jwt, "登录成功");
        } else {
            return Result.error("用户不存在");
        }
    }

}
