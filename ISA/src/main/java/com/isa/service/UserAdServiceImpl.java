package com.isa.service;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.domain.Requisite;
import com.isa.domain.UserAd;
import com.isa.repository.UserAdRepository;

@Service
public class UserAdServiceImpl implements UserAdService {
	
	@Autowired
	public UserAdRepository userAdRepository;

	@Override
	public Collection<UserAd> findAll() {
		// TODO Auto-generated method stub
		return userAdRepository.findAll();
	}

	@Override
	public UserAd save(UserAd userAd, Boolean aprove) {
		// TODO Auto-generated method stub
		return userAdRepository.save(userAd);
	}
	
	@Override
	public UserAd findOne(Long id) {
		// TODO Auto-generated method stub
		return userAdRepository.getOne(id);
	}

	@Override
	public UserAd delete(Long id) {
		// TODO Auto-generated method stub
		UserAd userAd = userAdRepository.getOne(id);
		if(userAd == null) {
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant useerAd");
		}
		userAdRepository.delete(userAd);
		return userAd;
	}

	@Override
	public UserAd aprove(Long id) {
		// TODO Auto-generated method stub
			System.out.println("Usao");
			UserAd userAd = userAdRepository.getOne(id);
			
			if(userAd != null) {
				userAd.setAproved(true);
			    userAdRepository.save(userAd);
			    return userAd;
			
			}
			return null;
		}
		
	

}
