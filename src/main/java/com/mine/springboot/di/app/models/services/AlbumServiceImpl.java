package com.mine.springboot.di.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mine.springboot.di.app.models.dao.IAlbumDao;
import com.mine.springboot.di.app.models.entity.Album;

@Service
public class AlbumServiceImpl implements IAlbumService{
	
	@Autowired
	private IAlbumDao albumDao;
		
	@Override
	@Transactional(readOnly = true)
	public List<Album> findAll() {
		// TODO Auto-generated method stub
		return (List<Album>) albumDao.findAll();
	}

	@Override
	public void save(Album album) {
		// TODO Auto-generated method stub
		albumDao.save(album);
	}

	@Override
	public Album findOne(Long id) {
		// TODO Auto-generated method stub
		return albumDao.findById(id).orElse(null);
	}
	
}
