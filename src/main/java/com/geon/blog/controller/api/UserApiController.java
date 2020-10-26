package com.geon.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.geon.blog.dto.ResponseDto;
import com.geon.blog.model.RoleType;
import com.geon.blog.model.User;
import com.geon.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;

	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController : save 호출됨");
		user.setRole(RoleType.USER);
		userService.signUp(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
}
