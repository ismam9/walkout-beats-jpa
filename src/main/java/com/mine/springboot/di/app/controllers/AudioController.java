package com.mine.springboot.di.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mine.springboot.di.app.models.entity.Audio;
import com.mine.springboot.di.app.models.entity.Categoria;
import com.mine.springboot.di.app.models.entity.Productor;
import com.mine.springboot.di.app.models.services.ICategoriaService;
import com.mine.springboot.di.app.models.services.IProductorService;
import com.mine.springboot.di.app.models.services.IUploadFileService;

@Controller
@RequestMapping("/audio")
@SessionAttributes("audio")
public class AudioController {
	
	@Autowired
	public IProductorService productorService;
	
	@Autowired
	public ICategoriaService categoriaService;
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	@GetMapping(value = "/uploads-productor/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {
		Resource recurso = null;

		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() +"\"")
				.body(recurso);
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id,
			RedirectAttributes flash) {
		
		Audio audio = productorService.findByAudioById(id);
		
		if (audio != null) {
			productorService.deleteAudio(id);
			flash.addFlashAttribute("success", "Beat eliminada con exito");
			
			
			if (uploadFileService.delete(audio.getFoto())) {
				flash.addFlashAttribute("info", "Foto: " + audio.getFoto() + "eliminada con exito");
			}
			
			return "redirect:/productores/ver-audio/" + audio.getProductor().getId();
			
		}
		
		flash.addFlashAttribute("error", "El beat no existe en la base de datos");
		return "redirect:/productores/ver/";
		
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/editform/{id}")
	public String editar(@PathVariable(value = "id") Long id,
			Map<String, Object> model,
			RedirectAttributes flash) {
		
		Audio audio = null;
		
		if (id > 0) {
			audio = productorService.findByAudioById(id);
			if (audio == null) {
				flash.addFlashAttribute("error", "El ID del beat no existe en la BBDD");
				return "redirect:/productores";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del cliente no puede ser cero");
			return "redirect:/productores";
		}
		
		Categoria categoria = new Categoria();
		
		model.put("categoria", categoria);
		model.put("audio", audio);
		model.put("title", "Editar Audio");
		return "forms/audio-form";
		
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/form/{id}")
	public String crear(@PathVariable(value = "id") Long id,
			Map<String, Object> model,
			RedirectAttributes flash) {
		
		Productor productor = productorService.findOne(id);
		
		if (productor == null) {
			flash.addFlashAttribute("error", "el productor no existe");
		}
		
		Audio audio = new Audio();
		audio.setProductor(productor);
		
		Categoria categoria = new Categoria();
		
		model.put("categoria", categoria);
		model.put("audio", audio);
		model.put("title", "Crear un Audio");
		
		return "forms/audio-form";

	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/form")
	public String guardar(@Valid Audio audio,
			BindingResult result,
			Model model,
			@RequestParam(name = "categoria", required = false) Categoria categoria,
			@RequestParam("file") MultipartFile[] files,
			@RequestParam(name = "hastag") String hastag,
			RedirectAttributes flash,
			SessionStatus status) {
		
		if (result.hasErrors()) {
			model.addAttribute("title", "Crear Beat");
			return "forms/audio-form";
		}
		
		if (files.length > 0) {

			if (audio.getId() != null && audio.getId() > 0 && audio.getAudio() != null
					&& audio.getAudio().length() > 0 && audio.getFoto() != null
					&& audio.getFoto().length() > 0) {

				uploadFileService.delete(audio.getFoto());
				uploadFileService.delete(audio.getAudio());

			}

			String uniqueFilename = null;
			String uniqueFilenamea = null;

			try {
				uniqueFilename = uploadFileService.copy(files[0]);
				uniqueFilenamea = uploadFileService.copy(files[1]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			flash.addFlashAttribute("info", "has subido correctamente '" + files[0].getOriginalFilename() + files[1].getOriginalFilename() + "'");

			audio.setFoto(uniqueFilename);
			audio.setAudio(uniqueFilenamea);
		}
		
		String mensajeFlash = (audio.getId() != null) ? "Audio editado con exito" : "Audio creado con exito!";
		
		audio.setHastag(null);
		audio.setHastag(Arrays.asList(hastag.split(",")));
		
		audio.setCategoria(categoria);
		productorService.saveAudio(audio);
		
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		
		return "redirect:/productores/ver-audio/" + audio.getProductor().getId();		
	}
}
