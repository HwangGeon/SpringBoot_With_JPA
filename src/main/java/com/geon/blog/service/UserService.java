package com.geon.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geon.blog.model.RoleType;
import com.geon.blog.model.User;
import com.geon.blog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Transactional
	public void signUp(User user) {
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}

	@Transactional
	public void userModify(User user) {
		// 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정
		// selet를 해서 User오브젝트를 DB로 부터 가져오는 이유는 영속화를 하기위해서
		// 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려줌
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원 찾기 실패");
		});
		
		//validate 체크 => oauth 필드에 값이 없으면 수정가능
		if(persistance.getOauth() == null || persistance.getOauth().equals("")) {
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			persistance.setPassword(encPassword);
			persistance.setEmail(user.getEmail());
		}
		 
		// 회원수정 함수 종료시 => 서비스 종료 => 트랜잭션 종료 => commit 자동으로 됨
	}

	@Transactional(readOnly = true)
	public User userFind(String username) {
		User user = userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
		return user;
	}
	
	/*시큐리티사용으로 주석처리
	 * @Transactional(readOnly = true) //Select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성)
	 * public User login(User user) { return
	 * userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword(
	 * )); }
	 */
} 
