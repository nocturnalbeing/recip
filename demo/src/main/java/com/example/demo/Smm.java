package com.example.demo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.websocket.server.PathParam;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Smm {
	@GetMapping(value="{value}" ,produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] m(@PathParam("value")String value) throws IOException {
		
		// FileReader reader = new FileReader(new File("C:\\Users\\sanjay\\Downloads\\san.JPEG"));
		 BufferedImage bImage = ImageIO.read(new File("C:\\Users\\sanjay\\Downloads\\sanj.jpg"));
	      ByteArrayOutputStream bos = new ByteArrayOutputStream();
	      ImageIO.write(bImage, "jpeg", bos );
	      byte [] data = bos.toByteArray();
	return data;
	}

}
