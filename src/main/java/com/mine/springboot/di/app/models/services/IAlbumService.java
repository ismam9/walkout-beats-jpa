package com.mine.springboot.di.app.models.services;

import java.util.List;

import com.mine.springboot.di.app.models.entity.Album;

public interface IAlbumService {
	
	public List<Album> findAll();
	
	public void save(Album album);
	
	public Album findOne(Long id);
	
}
