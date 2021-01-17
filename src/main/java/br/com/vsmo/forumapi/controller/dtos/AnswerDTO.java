package br.com.vsmo.forumapi.controller.dtos;

import java.time.LocalDateTime;

import br.com.vsmo.forumapi.model.Answer;

public class AnswerDTO {

  private Long id;
  private String message;
  private LocalDateTime creationDate;
  private String authorName;

  public AnswerDTO(Answer answer) {
    this.id = answer.getId();
    this.message = answer.getMessage();
    this.creationDate = answer.getCreationDate();
    this.authorName = answer.getAuthor().getName();
  }

  public Long getId() {
    return id;
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

}
