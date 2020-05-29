package com.mine.springboot.di.app.models.services;

import java.util.List;

import com.mine.springboot.di.app.models.entity.Audio;

public interface IAudioService {
	
	public List<Audio> findAll();
	
	public void save(Audio audio);
	
	public Audio findOne(Long id);
}
