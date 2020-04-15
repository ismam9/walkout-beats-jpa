package com.mine.springboot.di.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mine.springboot.di.app.models.dao.IAlbumDao;
import com.mine.springboot.di.app.models.dao.IBeatDao;
import com.mine.springboot.di.app.models.dao.IProductorDao;
import com.mine.springboot.di.app.models.entity.Album;
import com.mine.springboot.di.app.models.entity.Beat;
import com.mine.springboot.di.app.models.entity.Productor;

@Service
public class ProductorServiceImpl implements IProductorService {
	
	@Autowired
	private IProductorDao productorDao;
	
	@Autowired
	private IAlbumDao albumDao;
	
	@Autowired
	private IBeatDao beatDao;
	
	@Override
	@Transactional
	public List<Productor> findAll() {
		// TODO Auto-generated method stub
		return (List<Productor>) productorDao.findAll();
	}

	@Override
	@Transactional
	public void save(Productor productor) {
		// TODO Auto-generated method stub
		productorDao.save(productor);
	}

	@Override
	@Transactional(readOnly = true)
	public Productor findOne(Long id) {
		// TODO Auto-generated method stub
		return productorDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		productorDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Productor fetchByIdWithAlbumnes(Long id) {
		// TODO Auto-generated method stub
		return productorDao.fetchByIdWithAlbumnes(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Productor fetchByIdWithBeats(Long id) {
		// TODO Auto-generated method stub
		return productorDao.fetchByIdWithBeats(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Album fetchByIdWithProductorWithBeatAlbumWithAlbum(Long id) {
		// TODO Auto-generated method stub
		return albumDao.fetchByIdWithProductorWithBeatAlbumWithAlbum(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Beat> findAllBeats() {
		// TODO Auto-generated method stub
		return (List<Beat>) beatDao.findAll();
	}

	@Override
	public List<Beat> findByNombre(String name) {
		// TODO Auto-generated method stub
		return beatDao.findByNombre(name);
	}

	@Override
	@Transactional
	public void saveAlbum(Album album) {
		// TODO Auto-generated method stub
		albumDao.save(album);
	}
	
	@Override
	@Transactional
	public void saveBeat(Beat beat) {
		
		beatDao.save(beat);
	}

	@Override
	@Transactional(readOnly = true)
	public Beat findByBeatById(Long id) {
		// TODO Auto-generated method stub
		return beatDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Album findByAlbumById(Long id) {
		// TODO Auto-generated method stub
		return albumDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteAlbum(Long id) {
		// TODO Auto-generated method stub
		albumDao.deleteById(id);
	}

	@Override
	public void deleteBeat(Long id) {
		// TODO Auto-generated method stub
		beatDao.deleteById(id);
	}


}
