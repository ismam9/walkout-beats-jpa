package com.mine.springboot.di.app.models.dao;


import org.springframework.data.repository.CrudRepository;

import com.mine.springboot.di.app.models.entity.Rol;

public interface IRolDao extends CrudRepository<Rol, Long>{
	
}
