package com.temadiplomes.doctorfinder.service;

import java.util.List;

import com.temadiplomes.doctorfinder.entity.Comments;

public interface CommentsService {

	public List<Comments> findAll();
	
	public Comments findById(int theId);
	
	public void save(Comments theComment);
	
	public void deleteById(int theId);
}
