package com.geon.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.geon.blog.dto.ResponseDto;
import com.geon.blog.model.User;
import com.geon.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController : save 호출됨");
		userService.signUp(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user){
		userService.userModify(user);
		//여기서 트랜잭션이 종료되기 때문에 DB의 값은 변경이 됨
		//하지만 세션값은 변경되지 않은 상태이기 때문에 직접 세션값을 변경해줄 것임 
		//세션등록
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	/*전통적인 로그인
	 * @PostMapping("/api/user/login") public ResponseDto<Integer>
	 * login(@RequestBody User user, HttpSession session){
	 * System.out.println("UserApiController : login 호출됨"); User principal =
	 * userService.login(user); // principal : 접근주체
	 * 
	 * if(principal != null) { session.setAttribute("principal", principal); }
	 * return new ResponseDto<Integer>(HttpStatus.OK.value(),1); }
	 */
}
	


