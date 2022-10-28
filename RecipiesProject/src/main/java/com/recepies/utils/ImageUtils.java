package com.recepies.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import com.recepies.model.RecepieRequest;
import com.recepies.model.UserRegisterRequest;

public class ImageUtils {

	public static String saveAnImage(RecepieRequest request, String filePath) {
		MultipartFile file = request.getImageOfTheRecepie();
		String fileName = request.getPublishedBy() + System.currentTimeMillis()
				+ request.getImageOfTheRecepie().getOriginalFilename();
		String completeFilepath = filePath + fileName;
		System.out.println("filePath " + filePath);
		System.out.println(file);
		try {
			file.transferTo(new File(completeFilepath));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return "failure";
		}
		return fileName;

	}
	public static String saveAnImage(UserRegisterRequest request, String filePath) {
		MultipartFile file = request.getProfPicture();
		String fileName = request.getEmail() + System.currentTimeMillis()
				+ request.getProfPicture().getOriginalFilename();
		String completeFilepath = filePath + fileName;
		System.out.println("filePath " + filePath);
		System.out.println(file);
		try {
			file.transferTo(new File(completeFilepath));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return "failure";
		}
		return fileName;

	}

	public static String buildAnURI(String nameOftheImageInFileSystem,String port) throws UnknownHostException {
		InetAddress ip = InetAddress.getLocalHost();
		return ip.getHostAddress().toString() + ":"+port+"/api/recepies/images/" + nameOftheImageInFileSystem;
	}

	public static byte[] ImageFetch(String imageName, String filePath) throws IOException {

		// FileReader reader = new FileReader(new
		// File("C:\\Users\\sanjay\\Downloads\\san.JPEG"));
		BufferedImage bImage = ImageIO.read(new File(filePath + imageName));
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		if (imageName.toLowerCase().contains(".jpeg"))
			ImageIO.write(bImage, "jpeg", bos);
		else if (imageName.toLowerCase().contains(".jpg"))
			ImageIO.write(bImage, "jpg", bos);
		else if (imageName.toLowerCase().contains(".png"))
			ImageIO.write(bImage, "png", bos);
		byte[] data = bos.toByteArray();
		return data;
	}
}
