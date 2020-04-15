package com.mine.springboot.di.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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

import com.mine.springboot.di.app.models.entity.Productor;
import com.mine.springboot.di.app.models.services.IProductorService;
import com.mine.springboot.di.app.models.services.IUploadFileService;

@Controller
@SessionAttributes("productor")
@RequestMapping(value = "/productores")
public class ProductorController {

	@Autowired
	private IProductorService productorService;

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

	@GetMapping("/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Productor productor = productorService.fetchByIdWithAlbumnes(id);

		if (productor == null) {
			flash.addFlashAttribute("error", "El productor no existe en la base de datos");
			return "redirect:/productores";
		}

		model.put("productor", productor);
		model.put("title", "Contenido del productor: " + productor.getNickname());

		return "detalles";
	}

	@GetMapping("/ver-beat/{id}")
	public String verBeat(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Productor productor = productorService.fetchByIdWithBeats(id);

		if (productor == null) {
			flash.addFlashAttribute("error", "El productor no existe en la base de datos");
			return "redirect:/productores";
		}

		model.put("productor", productor);
		model.put("title", "Contenido del productor: " + productor.getNickname());

		return "detalles-beat";
	}

	@GetMapping(value = { "", "/" })
	public String listar(Model model) {
		model.addAttribute("title", "Listado de Productores");
		model.addAttribute("productores", productorService.findAll());
		return "productor-listar";
	}

	@GetMapping(value = "/form")
	public String crear(Map<String, Object> model) {

		Productor productor = new Productor();
		model.put("productor", productor);
		model.put("title", "Creacion de un nuevo productor");

		return "forms/productor-form";
	}

	@GetMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Productor productor = null;

		if (id > 0) {
			productor = productorService.findOne(id);
			if (productor == null) {
				flash.addFlashAttribute("error", "El ID del cliente no existe en la BBDD");
				return "redirect:/productores";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del cliente no puede ser cero");
			return "redirect:/productores";
		}

		model.put("productor", productor);
		model.put("title", "Edicion del productor");
		return "forms/productor-form";
	}

	@PostMapping(value = "/form")
	public String guardar(@Valid Productor productor, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("title", "Creacion de un nuevo productor");
			return "forms/productor-form";
		}

		if (!foto.isEmpty()) {

			if (productor.getId() != null && productor.getId() > 0 && productor.getFoto() != null
					&& productor.getFoto().length() > 0) {

				uploadFileService.delete(productor.getFoto());

			}

			String uniqueFilename = null;

			try {
				uniqueFilename = uploadFileService.copy(foto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			flash.addFlashAttribute("info", "has subido correctamente '" + foto.getOriginalFilename() + "'");

			productor.setFoto(uniqueFilename);
		}

		String mensajeFlash = (productor.getId() != null) ? "Productor editado con exito" : "Cliente creado con exito!";

		productorService.save(productor);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);

		return "redirect:/productores/";
	}

	@GetMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			Productor productor = productorService.findOne(id);

			productorService.delete(id);
			flash.addFlashAttribute("success", "Productor eliminado con exito");

			if (uploadFileService.delete(productor.getFoto())) {
				flash.addFlashAttribute("info", "Foto: " + productor.getFoto() + "eliminada con exito");
			}

		}
		return "redirect:/productores/";
	}

}
