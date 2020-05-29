package com.mine.springboot.di.app.controllers;

import java.util.Arrays;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mine.springboot.di.app.models.entity.Rol;
import com.mine.springboot.di.app.models.entity.Usuario;
import com.mine.springboot.di.app.models.services.IUserService;

@SessionAttributes("usuario")
@Controller
public class UserController{
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private IUserService usuarioService;
	
	@GetMapping(value ="/signup")
	private String crear(Map<String, Object> model) {
		
		Usuario usuario = new Usuario();
		
		model.put("usuario", usuario);
		model.put("title", "Registro");

		return "sign-up";
	}
	
	@PostMapping(value = "/signup")
	private String guardar(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
		
		if (result.hasErrors()) {
			model.addAttribute("title", "Registro User");
			return "sign-up";
		}
		
		String mensajeFlash = (usuario.getId() != null) ? "Usuario editado con exito" : "Usuario creado con exito!";
		
		Rol rol = new Rol();
		rol.setAuthority("ROLE_USER");
		
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuario.setRoles(Arrays.asList(rol));
		usuario.setEnabled(true);
		
		usuarioService.save(usuario);
		
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);

		return "redirect:/home/";
	}
}
