package com.sobieraj.olivia.SongManager.Controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sobieraj.olivia.SongManager.Entity.Photo;
import com.sobieraj.olivia.SongManager.Repo.PhotoRepo;


@Controller
public class PhotoController {
	
	@Autowired
	PhotoRepo pRepo;
	
	@PostMapping("/addPhoto")
	public String addPhoto(@RequestParam("image") MultipartFile image) {

		if(!image.isEmpty()) {
			
			try {
				byte[] content = image.getBytes();
				Photo photo = new Photo();
				photo.setFileName(image.getOriginalFilename());
				photo.setContent(content);
				pRepo.save(photo);
				return "redirect:/photos";
			}
			catch(Exception e) {
				
			}
			
		}
		
		return "error";
	}
	
	
	//ChatGPT code that I need to study
	@GetMapping("/photoss/{id}")
	@ResponseBody
	public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) {
	    Optional<Photo> photoOptional = pRepo.findById(id);
	    if (photoOptional.isPresent()) {
	        Photo photo = photoOptional.get();
	        byte[] content = photo.getContent();

	        // Set the appropriate content type based on the image format
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.IMAGE_JPEG);

	        return new ResponseEntity<>(content, headers, HttpStatus.OK);
	    }

	    return ResponseEntity.notFound().build();
	}


}
