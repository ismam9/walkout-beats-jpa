package com.mine.springboot.di.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.mine.springboot.di.app.models.entity.Categoria;

public interface ICategoriaDao extends CrudRepository<Categoria, Long> {

}
