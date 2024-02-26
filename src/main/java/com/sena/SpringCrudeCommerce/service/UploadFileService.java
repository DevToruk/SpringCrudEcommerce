package com.sena.SpringCrudeCommerce.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {

	// en la base de datos se almacena el nombre y la extancion de la img
	private String folder = "images//";

	// este método es la carga de la imagen
	public String saveImages(MultipartFile file, String nombre) throws IOException {

		// validacion de imagenes
		if (!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			// variable tipo path redirige al directorio del project
			Path path = Paths.get(folder + nombre + file.getOriginalFilename());
			Files.write(path, bytes);
			return nombre + file.getOriginalFilename();
		}

		return "default.jpg";
	}

	// metdo para borrar la imagen
	public void deleteImages(String nombre) {
		String ruta = "images//";
		// el import java.io
		File file = new File(ruta + nombre);
		file.delete();
	}

}
