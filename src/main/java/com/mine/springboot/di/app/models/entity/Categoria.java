package com.mine.springboot.di.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat
	private Date createAt;
	
	@OneToMany(mappedBy = "categoria", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)
	private List<Beat> beats;
	
	public Categoria() {
		this.beats = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	public List<Beat> getBeats() {
		return beats;
	}

	public void setBeats(List<Beat> Beats) {
		this.beats = Beats;
	}
	
	public void addBeat(Beat beat) {
		beats.add(beat);
	}

	private static final long serialVersionUID = 1L;

}
