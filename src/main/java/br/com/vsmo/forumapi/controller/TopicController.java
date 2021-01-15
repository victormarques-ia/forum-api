package br.com.vsmo.forumapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vsmo.forumapi.controller.dtos.TopicDTO;

import br.com.vsmo.forumapi.model.Topic;
import br.com.vsmo.forumapi.repositories.TopicRepository;

@RestController
public class TopicController {

  @Autowired
  private TopicRepository topicRepository;

  @RequestMapping("/topics")
  public List<TopicDTO> listTopics(String courseName) {
    if (courseName == null) {
      List<Topic> topics = topicRepository.findAll();

      return TopicDTO.mapper(topics);
    } else {
      List<Topic> topics = topicRepository.findByCourseName(courseName);

      return TopicDTO.mapper(topics);
    }
  }

}
