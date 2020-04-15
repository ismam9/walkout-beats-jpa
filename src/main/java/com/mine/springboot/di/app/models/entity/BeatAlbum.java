package com.mine.springboot.di.app.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "beat_album")
public class BeatAlbum implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="beat_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Beat beat;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Beat getBeat() {
		return beat;
	}

	public void setBeat(Beat beat) {
		this.beat = beat;
	}

	private static final long serialVersionUID = 1L;

}
