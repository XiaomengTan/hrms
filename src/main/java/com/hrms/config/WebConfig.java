package com.hrms.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration//声明配置类
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }


    //    不做配置，则关闭springboot对静态资源的请求处理操作
//    @Autowired
//    private LoginCheckInterceptor loginCheckInterceptor;

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //在这个方法中配置拦截器的拦截范围，/**表示拦截/下的所有层级的请求，/*表示只拦截/下的第一层级的请求，
//        //excludePathPatterns表示排除某个路径
////        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/**").excludePathPatterns("/index/*"
////                , "/email/*"
////                , "/user/resetPass"
////                , "/doc.html"
////                , "/webjars/**");
//
////        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/**")
////                .excludePathPatterns("/index/*", "/email/*", "/user/resetPass", "/doc.html", "/webjars/**", "/swagger-ui/**", "/v3/api-docs/**");
//
//        registry.addInterceptor(loginCheckInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns(
//                        "/index/*",
//                        "/email/*",
//                        "/user/resetPass",
//                        "/redoc.html",
//                        "/favicon.ico",
//                        "/swagger-ui.html",
//                        "/swagger-ui/**",
//                        "/v3/api-docs",
//                        "/v3/api-docs/**",
//                        "/webjars/**"
//                );
//    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*") // 允许所有域名
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                .allowedHeaders("*")
//                .allowCredentials(true);
//    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOriginPatterns("*") // 使用通配符允许所有域名
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                .allowedHeaders("*")
//                .allowCredentials(true);
//    }


//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")  // 允许跨域的路径
//                .allowedOrigins("http://localhost:5173")  // 允许跨域的域名
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")  // 允许的请求方法
//                .allowCredentials(true)  // 允许发送cookie信息
//                .maxAge(3600);  // 预检请求的缓存时间
//    }
}
