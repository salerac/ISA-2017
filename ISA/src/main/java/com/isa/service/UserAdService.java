package com.isa.service;

import java.util.Collection;
import java.util.List;

import com.isa.domain.UserAd;

public interface UserAdService {
	
	Collection<UserAd> findAll();
	
	UserAd save(UserAd userAd,Boolean aprove);
	UserAd findOne(Long id);
	UserAd delete(Long id);
	
	UserAd aprove(Long id);
	
	List<UserAd> findByCreatorId(Long Id);
	

}
