package com.mine.springboot.di.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.springboot.di.app.models.entity.Beat;

@Service
public class BeatServiceImpl implements IBeatService{
	
	@Autowired
	private IBeatService beatDao;
	
	@Override
	public List<Beat> findAll() {
		// TODO Auto-generated method stub
		return (List<Beat>) beatDao.findAll();
	}

	@Override
	public void save(Beat beat) {
		// TODO Auto-generated method stub
		beatDao.save(beat);
	}

	@Override
	public Beat findOne(Long id) {
		// TODO Auto-generated method stub
		return beatDao.findOne(id);
	}

}
