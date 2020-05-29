package com.mine.springboot.di.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "productor")
public class Productor implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Size(min = 4, max = 20)
	private String nombre;

	@NotEmpty
	@Size(min = 4, max = 20)
	private String apellido;

	private String descripcion;
	private String especificacion;

	@NotEmpty
	@Size(min = 4, max = 20)
	private String nickname;

	@NotEmpty
	private String ciudad;

	@NotEmpty
	@Email
	private String email;

	private boolean cantante;
	private boolean productorm;
	private boolean sonido;

	@OneToMany(mappedBy = "productor", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Audio> audios;

	@OneToMany(mappedBy = "productor", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Album> albums;

	private String foto;

	public Productor() {
		albums = new ArrayList<Album>();
		audios = new ArrayList<Audio>();
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEspecificacion() {
		return especificacion;
	}

	public void setEspecificacion(String especificacion) {
		this.especificacion = especificacion;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isCantante() {
		return cantante;
	}

	public void setCantante(boolean cantante) {
		this.cantante = cantante;
	}

	public boolean isProductorm() {
		return productorm;
	}

	public void setProductorm(boolean productorm) {
		this.productorm = productorm;
	}

	public boolean isSonido() {
		return sonido;
	}

	public void setSonido(boolean sonido) {
		this.sonido = sonido;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public void addAlbum(Album album) {
		albums.add(album);
	}

	public List<Audio> getAudios() {
		return audios;
	}

	public void setAudios(List<Audio> audios) {
		this.audios = audios;
	}

	public void addAudio(Audio audio) {
		audios.add(audio);
	}

	private static final long serialVersionUID = 1L;

}
