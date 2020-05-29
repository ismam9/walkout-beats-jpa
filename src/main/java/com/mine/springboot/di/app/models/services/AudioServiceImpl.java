package com.mine.springboot.di.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.springboot.di.app.models.entity.Audio;

@Service
public class AudioServiceImpl implements IAudioService{
	
	@Autowired
	private IAudioService audioDao;
	
	@Override
	public List<Audio> findAll() {
		// TODO Auto-generated method stub
		return (List<Audio>) audioDao.findAll();
	}

	@Override
	public void save(Audio audio) {
		// TODO Auto-generated method stub
		audioDao.save(audio);
	}

	@Override
	public Audio findOne(Long id) {
		// TODO Auto-generated method stub
		return audioDao.findOne(id);
	}

}
