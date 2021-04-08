package com.temadiplomes.doctorfinder.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.temadiplomes.doctorfinder.entity.AttributeValue;

public interface AttributeValueRepository extends JpaRepository<AttributeValue, Integer> {

}
