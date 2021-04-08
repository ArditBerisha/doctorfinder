package com.temadiplomes.doctorfinder.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.temadiplomes.doctorfinder.entity.PhoneNumber;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Integer> {

}
