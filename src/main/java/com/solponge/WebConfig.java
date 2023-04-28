package com.solponge;

import com.solponge.web.view.login.interceptor.AdminLoginCheckInterceptor;
import com.solponge.web.view.login.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 인터셉터를 등록
     */
    public void addInterceptors(InterceptorRegistry registry){

        /*어드민 여부 확인*/
        registry.addInterceptor(new AdminLoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/com.solponge/admin/**");

        /*회원 여부 확인*/
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                /*체크 예외 경로*/
                .excludePathPatterns(
                        "/com.solponge/main",
                        "/com.solponge/join",
                        "/com.solponge/login",
                        "/com.solponge/product/**",
                        "/com.solponge/productList/**",
                        "/com.solponge/jobinfolist/**",
                        "/com.solponge/jobinfo/**",
                        "/css/**",
                        "/img/**",
                        "/js/**"

                );

    }
}
