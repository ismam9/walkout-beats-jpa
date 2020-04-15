package com.mine.springboot.di.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mine.springboot.di.app.models.entity.Album;

public interface IAlbumDao extends CrudRepository<Album, Long> {
	
	@Query("select a from Album a join fetch a.productor p join fetch a.beatsa b join fetch b.beat where a.id=?1")
	public Album fetchByIdWithProductorWithBeatAlbumWithAlbum(Long id);
}
