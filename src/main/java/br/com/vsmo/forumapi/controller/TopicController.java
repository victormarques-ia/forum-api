package br.com.vsmo.forumapi.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.vsmo.forumapi.controller.dtos.TopicDTO;
import br.com.vsmo.forumapi.controller.dtos.TopicDetailsDTO;
import br.com.vsmo.forumapi.controller.form.TopicForm;
import br.com.vsmo.forumapi.controller.form.TopicUpdateForm;
import br.com.vsmo.forumapi.model.Topic;
import br.com.vsmo.forumapi.repositories.CourseRepository;
import br.com.vsmo.forumapi.repositories.TopicRepository;

@RestController
@RequestMapping("/topics")
public class TopicController {

  @Autowired
  private TopicRepository topicRepository;

  @Autowired
  private CourseRepository courseRepository;

  @GetMapping
  @Cacheable(value = "findTopics")
  public Page<TopicDTO> findTopics(@RequestParam(required = false) String courseName,
      @PageableDefault(sort = "id", direction = Direction.ASC) Pageable pagination) {

    if (courseName == null) {
      Page<Topic> topics = topicRepository.findAll(pagination);

      return TopicDTO.mapper(topics);
    } else {
      Page<Topic> topics = topicRepository.findByCourseName(courseName, pagination);

      return TopicDTO.mapper(topics);
    }
  }

  @PostMapping
  @Transactional
  @CacheEvict(value = "findTopics", allEntries = true)
  public ResponseEntity<TopicDTO> createTopic(@RequestBody @Valid TopicForm topicForm,
      UriComponentsBuilder uriBuilder) {
    Topic topic = topicForm.mapper(courseRepository);
    topicRepository.save(topic);

    URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();

    return ResponseEntity.created(uri).body(new TopicDTO(topic));
  }

  @GetMapping("/{id}")
  public ResponseEntity<TopicDetailsDTO> findTopic(@PathVariable Long id) {
    Optional<Topic> optionalTopic = topicRepository.findById(id);

    if (optionalTopic.isPresent()) {
      return ResponseEntity.ok(new TopicDetailsDTO(optionalTopic.get()));
    }

    return ResponseEntity.notFound().build();
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<TopicDTO> updateTopic(@PathVariable Long id,
      @RequestBody @Valid TopicUpdateForm topicUpdateForm) {

    Optional<Topic> optionalTopic = topicRepository.findById(id);

    if (optionalTopic.isPresent()) {
      Topic topic = topicUpdateForm.update(id, topicRepository);
      return ResponseEntity.ok(new TopicDTO(topic));
    }

    return ResponseEntity.notFound().build();

  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity removeTopic(@PathVariable Long id) {
    Optional<Topic> optionalTopic = topicRepository.findById(id);

    if (optionalTopic.isPresent()) {
      topicRepository.deleteById(id);
      return ResponseEntity.ok().build();
    }

    return ResponseEntity.notFound().build();

  }
}
