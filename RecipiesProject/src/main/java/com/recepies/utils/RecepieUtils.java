package com.recepies.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import com.recepies.dao.HomePageDAO;
import com.recepies.entities.User;
import com.recepies.model.CommentModel;
import com.recepies.model.RecepieModel;
import com.recepies.model.RecepieRequest;

public class RecepieUtils {


	/*
	 * public static List<RecepieModel> buildRecepieModel(HomePageDAO
	 * homePageDAO,boolean like) { return
	 * StreamSupport.stream(homePageDAO.getAllRecepies().spliterator(),
	 * false).collect(Collectors.toList()) .stream().map(recepieEntity
	 * ->RecepieUtils.buildRecepieModel(recepieEntity, like)
	 * 
	 * 
	 * { RecepieModel recepieModel = new RecepieModel();
	 * recepieModel.setDateOfPublishing(recepieEntity.getDateOfPublishing());
	 * recepieModel.setDescription(recepieEntity.getDescription());
	 * recepieModel.setIngredients(recepieEntity.getIngredients());
	 * recepieModel.setName(recepieEntity.getName());
	 * recepieModel.setProcedureOfTheRecepie(recepieEntity.getProcedureOfTheRecepie(
	 * )); recepieModel.setPublishedBy(recepieEntity.getPublishedBy());
	 * recepieModel.setRecepieId(recepieEntity.getRecepieId());
	 * recepieModel.setRecepieURL(recepieEntity.getRecepieURL());
	 * recepieModel.setCommentModels(recepieEntity.getComments().stream()
	 * .map(comment ->
	 * RecepieUtils.buildCommentModel(comment)).collect(Collectors.toList()));
	 * 
	 * return recepieModel;
	 * 
	 * }).collect(Collectors.toList()); }
	 */

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

	public static com.recepies.model.RecepieModel buildRecepieModel(com.recepies.entities.Recepie recepieEntity,
			boolean like) {
		com.recepies.model.RecepieModel recepieModel = new com.recepies.model.RecepieModel();
		recepieModel.setRecepieId(recepieEntity.getRecepieId());
		recepieModel.setDateOfPublishing(new Date());
		recepieModel.setDescription(recepieEntity.getDescription());
		recepieModel.setIngredients(recepieEntity.getIngredients());
		recepieModel.setName(recepieEntity.getName());
		recepieModel.setPublishedBy(recepieEntity.getPublishedBy());
		recepieModel.setProcedureOfTheRecepie(recepieEntity.getProcedureOfTheRecepie());
		recepieModel.setRecepieURL(recepieEntity.getRecepieURL());
		recepieModel.setCommentModels(recepieEntity.getComments() != null ? recepieEntity.getComments().stream()
				.map(x -> RecepieUtils.buildCommentModel(x)).collect(Collectors.toList()) : new ArrayList<>());
		recepieModel.setWheatherLikedByuser(like);
		return recepieModel;

	}

	public static com.recepies.model.CommentModel buildCommentModel(com.recepies.entities.Comment commentEntity) {
		CommentModel commentModel = new CommentModel();
		commentModel.setCommentedContent(commentEntity.getCommentedContent());
		commentModel.setPostedBy(commentEntity.getPostedBy());
		commentModel.setPostedOnRecepie(commentEntity.getRecepie().getRecepieId());
		return commentModel;
	}

	public static com.recepies.entities.Comment buildCommentEntity(CommentModel commentModel,
			com.recepies.entities.Recepie recepie) {
		com.recepies.entities.Comment commentEntity = new com.recepies.entities.Comment();
		commentEntity.setCommentedContent(commentModel.getCommentedContent());
		commentEntity.setPostedBy(commentModel.getPostedBy());
		commentEntity.setRecepie(recepie);
		return commentEntity;
	}

}
