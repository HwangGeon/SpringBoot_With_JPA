package com.geon.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geon.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

}
