package com.recepies.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.recepies.entities.Likes;
import com.recepies.entities.Recepie;
import com.recepies.entities.User;
import com.recepies.model.CommentModel;
import com.recepies.model.LikeModel;
import com.recepies.model.RecepieModel;
import com.recepies.repositories.CommentRepository;
import com.recepies.repositories.LikesRepository;
import com.recepies.repositories.RecepieRepository;
import com.recepies.repositories.UserRepository;
import com.recepies.utils.RecepieUtils;

@Repository
public class HomePageDAO {
	@Autowired
	RecepieRepository recepieRepository;
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	LikesRepository likesRepository;

	public List<RecepieModel> getAllRecepies(String userEmail) {
		Iterable<Recepie> recepiesIterable = recepieRepository.findAll();
		List<Recepie> recepiesList = StreamSupport.stream(recepiesIterable.spliterator(), false)
				.collect(Collectors.toList());
		return recepiesList.stream().map(recepieEntity -> {
			Likes l = likesRepository.findByLikedOnRecepieAndEmailOfLikedUser(recepieEntity.getRecepieId(), userEmail);
			return RecepieUtils.buildRecepieModel(recepieEntity, l != null);

		}).collect(Collectors.toList());

	}

	public Recepie publishRecepie(Recepie recepieEntity) {
		Recepie SavedRecepie = recepieRepository.save(recepieEntity);
		return SavedRecepie;

	}

	public List<CommentModel> commentOnARecepie(CommentModel commentModel) {
		System.out.println("entered dao");
		System.out.println(commentModel);
		Optional<Recepie> optionalRecepie = recepieRepository.findById(commentModel.getPostedOnRecepie());

		if (optionalRecepie.isPresent()) {
			Recepie recepie = optionalRecepie.get();
			com.recepies.entities.Comment commentEntity = RecepieUtils.buildCommentEntity(commentModel, recepie);
			recepie.getComments().add(commentEntity);
			commentEntity.setRecepie(recepie);
			Recepie updatedRecepie = recepieRepository.save(recepie);
			System.out.println("exited dao");
			// System.out.println("exited dao");

			return updatedRecepie.getComments().stream().map(t -> RecepieUtils.buildCommentModel(t))
					.collect(Collectors.toList());
		}
		System.out.println("exited dao");

		return new ArrayList<>();
	}

	public RecepieModel likeARecepie(LikeModel like) {
		Optional<Recepie> recepie = recepieRepository.findById(like.getLikedOnRecepie());
		if (recepie.isPresent()) {
			User user = userRepository.findByEmail(like.getEmailOfLikedUser());
			if (user != null) {
				Likes likeinRepo = likesRepository.findByLikedOnRecepieAndEmailOfLikedUser(like.getLikedOnRecepie(),
						like.getEmailOfLikedUser());
				if (likeinRepo != null) {
					likesRepository.delete(likeinRepo);
				} else {
					Likes likeEntity = new Likes();
					likeEntity.setEmailOfLikedUser(like.getEmailOfLikedUser());
					likeEntity.setLikedOnRecepie(like.getLikedOnRecepie());
					likesRepository.save(likeEntity);
				}
				Likes l = likesRepository.findByLikedOnRecepieAndEmailOfLikedUser(like.getLikedOnRecepie(),
						like.getEmailOfLikedUser());
				if (l != null) {
					return RecepieUtils.buildRecepieModel(recepie.get(), true);
				} else {
					return RecepieUtils.buildRecepieModel(recepie.get(), false);
				}

			}

		}

		// return new RecepieModel();
		throw new RuntimeException("no such recepie found");
	}

	public List<RecepieModel> searchARecepie(String keyword) {
		System.out.println("key word in dao is " + keyword);
		List<Recepie> allFetchedRecepies = recepieRepository.findByNameContaining( keyword.trim().replace("\\", ""));
		return allFetchedRecepies.stream().map(entity -> {
			return RecepieUtils.buildRecepieModel(entity, false);
		}).collect(Collectors.toList());

	}

}
