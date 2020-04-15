package com.mine.springboot.di.app.models.services;

import java.util.List;

import com.mine.springboot.di.app.models.entity.Beat;

public interface IBeatService {
	
	public List<Beat> findAll();
	
	public void save(Beat beat);
	
	public Beat findOne(Long id);
}
