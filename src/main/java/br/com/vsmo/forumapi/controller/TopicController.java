package br.com.vsmo.forumapi.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.vsmo.forumapi.controller.dtos.TopicDTO;
import br.com.vsmo.forumapi.controller.form.TopicForm;

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
  public List<TopicDTO> listTopics(String courseName) {
    if (courseName == null) {
      List<Topic> topics = topicRepository.findAll();

      return TopicDTO.mapper(topics);
    } else {
      List<Topic> topics = topicRepository.findByCourseName(courseName);

      return TopicDTO.mapper(topics);
    }
  }

  @PostMapping
  public ResponseEntity<TopicDTO> createTopic(@RequestBody TopicForm topicForm, UriComponentsBuilder uriBuilder) {
    Topic topic = topicForm.mapper(courseRepository);
    topicRepository.save(topic);

    URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();

    return ResponseEntity.created(uri).body(new TopicDTO(topic));
  }
}
