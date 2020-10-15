package com.geon.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴!
//ORM -> java(다른언어) Object -> 테이블로 매핑해주는 기술
@Entity //User 클래스가 MySQL에 테이블이 생성이 된다
		//! Enum사용으로수정 // @DynamicInsert //insert 할때 null 인 필드 제외 (여기서 쓰는 이유: role을 디폴트값지정을 해놓았지만 요청으로 null을 받게되면 null값이 들어감)
public class User {
	
	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; // 시퀀스, auto_increment
	
	@Column(nullable = false, length = 30)
	private String username; // 아이디
	
	@Column(nullable =  false, length = 100) // 비밀번호 암호화
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
			//! Enum사용으로 수정// @ColumnDefault("'user'") //문자라는걸 알려주게 하기위해 '" "'사용
			//! Enum사용으로 수정// private String role; // Enum을 쓰는게 좋음(도메인을 정할수있음=>들어갈수있는값 정의) //ex) admin, user, manager 권한을 줄때 3개의 값중 무조건 하나만 들어갈수있게 강제함
	
	//DB는 RoleType이라는게 없다.
	//디폴트값은 회원가입시 user.setRole(RoleType.USER) 로 넣어버림
	@Enumerated(EnumType.STRING)
	private RoleType role;
	
	@CreationTimestamp // 시간이 자동 입력
	private Timestamp createDate;
}
