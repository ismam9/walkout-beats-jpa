package com.mine.springboot.di.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mine.springboot.di.app.models.services.IProductorService;

@Controller
@RequestMapping("/walkout")
public class HomeController {
	
	@Autowired
	private IProductorService productorService;
	
	@GetMapping(value = {"","/"})
	public String portada(){
		
		return "portada";
	}
	
	@GetMapping(value = "/home")
	public String beats(Model model) {
		model.addAttribute("title", "Plataforma de venta de beats...");
		model.addAttribute("allbeats", productorService.findAllBeats());		
		
		return "home";
	}
}
