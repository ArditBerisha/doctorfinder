package com.temadiplomes.doctorfinder.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.temadiplomes.doctorfinder.entity.Comments;

public interface CommentsRepository extends JpaRepository<Comments, Integer> {

}
