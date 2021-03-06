package br.com.vsmo.forumapi.controller.dtos;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.vsmo.forumapi.model.Topic;

public class TopicDTO {

  private Long id;
  private String title;
  private String message;
  private LocalDateTime creationDate;

  public TopicDTO(Topic topic) {
    this.id = topic.getId();
    this.title = topic.getTitle();
    this.message = topic.getMessage();
    this.creationDate = topic.getCreationDate();
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getMessage() {
    return message;
  }

  public LocalDateTime getCreationDate() {
    return creationDate;
  }

  public static Page<TopicDTO> mapper(Page<Topic> topics) {
    return topics.map(TopicDTO::new);
  }

}
