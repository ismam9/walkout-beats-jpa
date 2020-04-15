package com.mine.springboot.di.app.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mine.springboot.di.app.models.dao.ICategoriaDao;
import com.mine.springboot.di.app.models.entity.Categoria;

@Service
public class CategoriaServiceImpl implements ICategoriaService{
	
	@Autowired
	private ICategoriaDao categoriaDao;
	
	@Override
	@Transactional(readOnly = true)
	public Categoria findOne(Long id) {
		
		return categoriaDao.findById(id).orElse(null);
	}

}
