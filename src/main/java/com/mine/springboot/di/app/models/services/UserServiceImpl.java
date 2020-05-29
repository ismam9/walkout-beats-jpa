package com.mine.springboot.di.app.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.springboot.di.app.models.dao.IUsuarioDao;
import com.mine.springboot.di.app.models.entity.Usuario;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private IUsuarioDao usuarioDao;

	@Override
	public void save(Usuario usuario) {
		// TODO Auto-generated method stub
		usuarioDao.save(usuario);
	}

}
