package minhhn.blog.service;

import minhhn.blog.dto.TagDto;
import minhhn.blog.mapper.TagMapper;
import minhhn.blog.model.Tag;
import minhhn.blog.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

  private final TagRepository tagRepository;
  private final TagMapper tagMapper;

  public TagService(TagRepository tagRepository, TagMapper tagMapper) {
    this.tagRepository = tagRepository;
    this.tagMapper = tagMapper;
  }

  public List<TagDto> getTags() {
    return this.tagMapper.toDtoList(tagRepository.findAll());
  }

  public TagDto findById(Long id) {
    return this.tagMapper.toDto(tagRepository.findById(id).orElseThrow());
  }

  public TagDto createTag(TagDto tagDto) {
    Tag toBeSaved = this.tagMapper.toEntity(tagDto);
    return this.tagMapper.toDto(this.tagRepository.save(toBeSaved));
  }

  public TagDto updateTag(TagDto tagDto) {
    Tag toBeUpdated = this.tagMapper.toEntity(tagDto);
    Tag fromDb = this.tagRepository.findById(toBeUpdated.getId()).orElseThrow();
    fromDb.setName(toBeUpdated.getName());
    return this.tagMapper.toDto(this.tagRepository.save(fromDb));
  }

}
