package com.mine.springboot.di.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mine.springboot.di.app.models.entity.Beat;
import com.mine.springboot.di.app.models.entity.Categoria;
import com.mine.springboot.di.app.models.entity.Productor;
import com.mine.springboot.di.app.models.services.ICategoriaService;
import com.mine.springboot.di.app.models.services.IProductorService;
import com.mine.springboot.di.app.models.services.IUploadFileService;

@Controller
@RequestMapping("/beat")
@SessionAttributes("beat")
public class BeatController {
	
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
		
		Beat beat = productorService.findByBeatById(id);
		
		if (beat != null) {
			productorService.deleteBeat(id);
			flash.addFlashAttribute("success", "Beat eliminada con exito");
			
			
			if (uploadFileService.delete(beat.getFoto())) {
				flash.addFlashAttribute("info", "Foto: " + beat.getFoto() + "eliminada con exito");
			}
			
			return "redirect:/productores/ver-beat/" + beat.getProductor().getId();
			
		}
		
		flash.addFlashAttribute("error", "El beat no existe en la base de datos");
		return "redirect:/productores/ver/";
		
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
		
		Beat beat = new Beat();
		beat.setProductor(productor);
		
		model.put("beat", beat);
		model.put("title", "Crear un Beat");
		
		return "forms/beat-form";

	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/form")
	public String guardar(@Valid Beat beat,
			BindingResult result,
			Model model,
			@RequestParam(name = "categoria", required = false) Categoria categoria,
			@RequestParam("file") MultipartFile[] files,
			RedirectAttributes flash,
			SessionStatus status) {
		
		if (result.hasErrors()) {
			model.addAttribute("title", "Crear Beat");
			return "forms/beat-form";
		}
		
		if (files.length > 0) {

			if (beat.getId() != null && beat.getId() > 0 && beat.getAudio() != null
					&& beat.getAudio().length() > 0 && beat.getFoto() != null
					&& beat.getFoto().length() > 0) {

				uploadFileService.delete(beat.getFoto());

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

			beat.setFoto(uniqueFilename);
			beat.setAudio(uniqueFilenamea);
		}
		
		String mensajeFlash = (beat.getId() != null) ? "Beat editado con exito" : "Beat creado con exito!";
		
		beat.setCategoria(categoria);
		productorService.saveBeat(beat);
		
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		
		return "redirect:/productores/ver-beat/" + beat.getProductor().getId();		
	}
}
