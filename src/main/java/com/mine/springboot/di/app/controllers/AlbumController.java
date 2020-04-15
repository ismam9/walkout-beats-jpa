package com.mine.springboot.di.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mine.springboot.di.app.models.entity.Album;
import com.mine.springboot.di.app.models.entity.Beat;
import com.mine.springboot.di.app.models.entity.BeatAlbum;
import com.mine.springboot.di.app.models.entity.Productor;
import com.mine.springboot.di.app.models.services.IProductorService;
import com.mine.springboot.di.app.models.services.IUploadFileService;

@Controller
@RequestMapping("/album")
@SessionAttributes("album")
public class AlbumController {
	
	@Autowired
	public IProductorService productorService;
	
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
	public String ver(@PathVariable(value = "id") Long id,
			Model model,
			RedirectAttributes flash) {
		
		Album album = productorService.fetchByIdWithProductorWithBeatAlbumWithAlbum(id);
		
		if (album == null) {
			flash.addFlashAttribute("album", album);
			return "redirect:/productores";
		}
		
		model.addAttribute("album", album);
		model.addAttribute("title", "Album: " + album.getDescripcion());
		
		return "album/album-listar";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id,
			RedirectAttributes flash) {
		
		Album album = productorService.findByAlbumById(id);
		
		if (album != null) {
			productorService.deleteAlbum(id);
			flash.addFlashAttribute("success", "Album eliminada con exito");
			
			if (uploadFileService.delete(album.getFoto())) {
				flash.addFlashAttribute("info", "Foto: " + album.getFoto() + "eliminada con exito");
			}
			
			return "redirect:/productores/ver/" + album.getProductor().getId();
			
		}
		
		flash.addFlashAttribute("error", "El album no existe en la base de datos");
		return "redirect:/productores/";
		
	}
	
	@GetMapping("/form/{productorId}")
	public String crear(@PathVariable(value = "productorId") Long productorId,
			Map<String, Object> model,
			RedirectAttributes flash) {
		
		Productor productor = productorService.findOne(productorId);
		
		if (productor == null) {
			flash.addAttribute("error", "El productor no existe");
		}
		
		Album album = new Album();
		album.setProductor(productor);
		
		model.put("album", album);
		model.put("title", "Crear un Album");
		
		return "forms/album-form";
	}
	
	@GetMapping(value="/cargar-beats/{term}", produces= {"application/json"})
	public @ResponseBody List<Beat> cargarBeats(@PathVariable String term){
		return productorService.findByNombre(term);
	}
		
	@PostMapping("/form")
	public String guardar(@Valid Album album,
			BindingResult result,
			Model model,
			@RequestParam(name = "beatsa_id[]", required = false) Long[] beatsaId,
			@RequestParam("file") MultipartFile foto,
			RedirectAttributes flash,
			SessionStatus status) {
		
		if (result.hasErrors()) {
			model.addAttribute("title", "Crear Album");
			return "album/form";
		}
		
		if (beatsaId == null || beatsaId.length == 0) {
			model.addAttribute("title", "Crear Album");
			model.addAttribute("error", "Error: La factura no puede estar vacia");
		}
		
		for (int i = 0; i < beatsaId.length; i++) {
			Beat beat = productorService.findByBeatById(beatsaId[i]);
			
			BeatAlbum song = new BeatAlbum();
			song.setBeat(beat);
			album.addBeatAlbum(song);
			
		}
		
		if (!foto.isEmpty()) {

			if (album.getId() != null && album.getId() > 0 && album.getFoto() != null
					&& album.getFoto().length() > 0) {

				uploadFileService.delete(album.getFoto());

			}

			String uniqueFilename = null;

			try {
				uniqueFilename = uploadFileService.copy(foto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			flash.addFlashAttribute("info", "has subido correctamente '" + foto.getOriginalFilename() + "'");

			album.setFoto(uniqueFilename);
		}
		
		productorService.saveAlbum(album);
		status.setComplete();
		flash.addFlashAttribute("success", "Album creado con exito");
		
		return "redirect:/productores/ver/" + album.getProductor().getId();
		
	}
	
}
