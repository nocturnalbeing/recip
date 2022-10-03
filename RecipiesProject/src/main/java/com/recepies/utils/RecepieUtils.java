package com.recepies.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import com.recepies.dao.HomePageDAO;
import com.recepies.model.Recepie;
import com.recepies.model.RecepieRequest;

public class RecepieUtils {

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

	public static String buildAnURI(String nameOftheImageInFileSystem) throws UnknownHostException {
		InetAddress ip = InetAddress.getLocalHost();
		return ip.getHostAddress().toString() + ":8080/api/recepies/" + nameOftheImageInFileSystem;
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
	public static List<Recepie> buildRecepieModel(HomePageDAO homePageDAO) {
		return StreamSupport.stream(homePageDAO.getAllRecepies().spliterator(), false).collect(Collectors.toList())
				.stream().map(recepieEntity -> {
					Recepie recepieModel = new Recepie();
					recepieModel.setDateOfPublishing(recepieEntity.getDateOfPublishing());
					recepieModel.setDescription(recepieEntity.getDescription());
					recepieModel.setIngredients(recepieEntity.getIngredients());
					recepieModel.setName(recepieEntity.getName());
					recepieModel.setProcedureOfTheRecepie(recepieEntity.getProcedureOfTheRecepie());
					recepieModel.setPublishedBy(recepieEntity.getPublishedBy());
					recepieModel.setRecepieId(recepieEntity.getRecepieId());
					recepieModel.setRecepieURL(recepieEntity.getRecepieURL());

					return recepieModel;

				}).collect(Collectors.toList());
	}

	public static com.recepies.entities.Recepie buildRecepieEntity(String uri, RecepieRequest request) {
		com.recepies.entities.Recepie recepie = new com.recepies.entities.Recepie();
		recepie.setDateOfPublishing(new Date());
		recepie.setDescription(request.getDescription());
		recepie.setIngredients(request.getIngredients());
		recepie.setName(request.getName());
		recepie.setPublishedBy(request.getPublishedBy());
		recepie.setProcedureOfTheRecepie(request.getProcedureOfTheRecepie());
		recepie.setRecepieURL(uri);
		return recepie;
		
	}

}
