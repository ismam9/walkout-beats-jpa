package com.mine.springboot.di.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mine.springboot.di.app.models.entity.Productor;

public interface IProductorDao extends CrudRepository<Productor, Long>{
	
	@Query("select p from Productor p left join fetch p.albums a where p.id=?1")
	public Productor fetchByIdWithAlbumnes(Long id);
	
	@Query("select p from Productor p left join fetch p.beats b where p.id=?1")
	public Productor fetchByIdWithBeats(Long id);
	
}
