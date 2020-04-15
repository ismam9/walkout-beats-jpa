package com.mine.springboot.di.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mine.springboot.di.app.models.entity.Beat;

public interface IBeatDao extends CrudRepository<Beat, Long>{
	
	@Query("select b from Beat b where b.nombre like %?1%")
	public List<Beat> findByNombre(String name);
	
	//public List<Beat> findByNombreLikeIgnoreCase(String term);
}
