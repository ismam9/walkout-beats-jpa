package com.mine.springboot.di.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mine.springboot.di.app.models.entity.Audio;

public interface IAudioDao extends CrudRepository<Audio, Long>{
	
	@Query("select a from Audio a where a.nombre like %?1%")
	public List<Audio> findByNombre(String name);
	
	@Query("select a from Audio a where a.active = true")
	public List<Audio> findAllAudioActive();
	
	//public List<Beat> findByNombreLikeIgnoreCase(String term);
}
