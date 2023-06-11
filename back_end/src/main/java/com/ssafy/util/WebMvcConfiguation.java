package com.ssafy.util;

import java.util.Arrays;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.interceptor.ConfirmInterceptor;


@Configuration
//@EnableWebMvc
@MapperScan(basePackages = {"com.ssafy.**.mapper"})
public class WebMvcConfiguation implements WebMvcConfigurer  {
	
	private ConfirmInterceptor confirmInterceptor;
	private final List<String> patterns = Arrays.asList("/article/*","/admin","/user/list");
	public WebMvcConfiguation(ConfirmInterceptor confirmInterceptor) {
		this.confirmInterceptor = confirmInterceptor;
	}

//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(confirmInterceptor).addPathPatterns(patterns);
//	}
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**") // 정적 리소스에 접근할 URL 패턴을 지정
                .addResourceLocations("file:c:/springboot/img/"); // 실제 파일이 저장된 경로를 지정
    }
}
