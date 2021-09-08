package minhhn.blog.controller;

import minhhn.blog.dto.ImageUploadResponse;
import minhhn.blog.dto.PostImageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/images/upload")
public class ImageController {

  @PostMapping
  public ResponseEntity<ImageUploadResponse> uploadPostImage(@ModelAttribute PostImageDto postImageDto) {
    ImageUploadResponse response = new ImageUploadResponse();
    response.setUploaded(true);
    response.setUrl("https://is1-ssl.mzstatic.com/image/thumb/Purple123/v4/7d/fb/7c/7dfb7ce6-9963-ca7d-74da-4c78bf69301c/source/256x256bb.jpg");
    return ResponseEntity.ok().body(response);
  }

}
