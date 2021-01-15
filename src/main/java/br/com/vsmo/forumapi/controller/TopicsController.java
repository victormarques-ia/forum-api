package br.com.vsmo.forumapi.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vsmo.forumapi.controller.dtos.TopicDTO;
import br.com.vsmo.forumapi.model.Course;
import br.com.vsmo.forumapi.model.Topic;

@RestController
public class TopicsController {

  @RequestMapping("/topics")
  public List<TopicDTO> listTopics() {
    Topic topic = new Topic("Dúvida", "Dúvida com Sprint", new Course("Spring", "Programação"));

    return TopicDTO.mapper(Arrays.asList(topic, topic));
  }

}
