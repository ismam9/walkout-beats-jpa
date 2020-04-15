package com.mine.springboot.di.app.models.services;

import com.mine.springboot.di.app.models.entity.Categoria;

public interface ICategoriaService {
	
	public Categoria findOne(Long id);
}
