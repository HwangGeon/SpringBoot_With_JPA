package com.geon.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geon.blog.model.Board;


public interface BoardRepository extends JpaRepository<Board, Integer> {

}
