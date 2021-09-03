package minhhn.blog.controller;

import minhhn.blog.dto.TagDto;
import minhhn.blog.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tags")
public class TagController {

  private final TagService tagService;

  public TagController(TagService tagService) {
    this.tagService = tagService;
  }

  @GetMapping
  ResponseEntity<List<TagDto>> getAllTags() {
    return ResponseEntity.ok().body(this.tagService.getTags());
  }

  @GetMapping("{id}")
  ResponseEntity<TagDto> getTagById(@PathVariable Long id) {
    return ResponseEntity.ok().body(this.tagService.findById(id));
  }

  @PostMapping
  ResponseEntity<TagDto> createTag(@RequestBody TagDto tagDto) {
    return ResponseEntity.ok().body(this.tagService.createTag(tagDto));
  }

  @PutMapping
  ResponseEntity<TagDto> updateTag(@RequestBody TagDto tagDto) {
    return ResponseEntity.ok().body(this.tagService.updateTag(tagDto));
  }

}
