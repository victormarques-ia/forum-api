package br.com.vsmo.forumapi.controller.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.vsmo.forumapi.model.StatusTopic;
import br.com.vsmo.forumapi.model.Topic;

public class TopicDetailsDTO {

  private Long id;
  private String title;
  private String message;
  private LocalDateTime creationDate;
  private String authorName;
  private StatusTopic status;
  private List<AnswerDTO> answers;

  public TopicDetailsDTO(Topic topic) {
    this.id = topic.getId();
    this.title = topic.getTitle();
    this.message = topic.getMessage();
    this.creationDate = topic.getCreationDate();
    this.authorName = topic.getAuthor().getName();
    this.status = topic.getStatus();
    this.answers = new ArrayList<>();
    this.answers.addAll(topic.getAnswers().stream().map(AnswerDTO::new).collect(Collectors.toList()));
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

  public String getAuthorName() {
    return authorName;
  }

  public StatusTopic getStatus() {
    return status;
  }

  public List<AnswerDTO> getAnswers() {
    return answers;
  }

}
