package com.mine.springboot.di.app.models.services;

import java.util.List;

import com.mine.springboot.di.app.models.entity.Album;
import com.mine.springboot.di.app.models.entity.Audio;
import com.mine.springboot.di.app.models.entity.Productor;

public interface IProductorService {
	
	public List<Productor> findAll();
	
	public void save(Productor productor);
	
	public Productor findOne(Long id);
	
	public Productor fetchByIdWithAlbumnes(Long id);
	
	public Productor fetchByIdWithAudios(Long id);
	
	public void delete(Long id);
	
	public List<Audio> findAllAudios();
	
	public List<Audio> findByNombre(String name);
	
	public void saveAlbum(Album album);
	
	public void saveAudio(Audio audio);
	
	public Audio findByAudioById(Long id);
	
	public Album findByAlbumById(Long id);
	
	public void deleteAlbum(Long id);
	
	public void deleteAudio(Long id);
	
	public Album fetchByIdWithProductorWithAudioAlbumWithAlbum(Long id);
	
}
