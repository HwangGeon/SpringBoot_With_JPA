package com.geon.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

//ORM -> java(다른언어) Object -> 테이블로 매핑해주는 기술
@Entity //User 클래스가 MySQL에 테이블이 생성이 된다
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
	
	@ColumnDefault("'user'") //문자라는걸 알려주게 하기위해 '"
	private String role; // Enum을 쓰는게 좋음(도메인을 정할수있음=>들어갈수있는값 정의) //ex) admin, user, manager 권한을 줄때 3개의 값중 무조건 하나만 들어갈수있게 강제함
	
	@CreationTimestamp // 시간이 자동 입력
	private Timestamp createDate;
}