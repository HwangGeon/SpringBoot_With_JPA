package com.geon.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration //빈등록
@EnableWebSecurity //시큐리티 필터가 등록이 된다. = 스프링 시큐리티가 이미 활성화가 되어 있는데 어떤 설정을 해당 파일에서 하겠다.
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다. //위 3개 어노테이션은 세트!!
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()                    //요청이들어오면
				.antMatchers("/auth/**")			//auth 이하의 경로는 누구나 들어올수있다
				.permitAll()									//
				.anyRequest()								//다른 모든 요청은
				.authenticated()				        	//인증이 되야한다.
			.and()
				.formLogin()
				.loginPage("/auth/loginForm");
	}
}
