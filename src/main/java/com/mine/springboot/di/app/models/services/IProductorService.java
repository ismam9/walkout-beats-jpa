package com.mine.springboot.di.app.models.services;

import java.util.List;

import com.mine.springboot.di.app.models.entity.Album;
import com.mine.springboot.di.app.models.entity.Beat;
import com.mine.springboot.di.app.models.entity.Productor;

public interface IProductorService {
	
	public List<Productor> findAll();
	
	public void save(Productor productor);
	
	public Productor findOne(Long id);
	
	public Productor fetchByIdWithAlbumnes(Long id);
	
	public Productor fetchByIdWithBeats(Long id);
	
	public void delete(Long id);
	
	public List<Beat> findAllBeats();
	
	public List<Beat> findByNombre(String name);
	
	public void saveAlbum(Album album);
	
	public void saveBeat(Beat beat);
	
	public Beat findByBeatById(Long id);
	
	public Album findByAlbumById(Long id);
	
	public void deleteAlbum(Long id);
	
	public void deleteBeat(Long id);
	
	public Album fetchByIdWithProductorWithBeatAlbumWithAlbum(Long id);
	
}
