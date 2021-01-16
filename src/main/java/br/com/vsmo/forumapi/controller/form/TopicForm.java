package br.com.vsmo.forumapi.controller.form;

import br.com.vsmo.forumapi.model.Course;
import br.com.vsmo.forumapi.model.Topic;
import br.com.vsmo.forumapi.repositories.CourseRepository;

public class TopicForm {

  private String title;
  private String message;
  private String courseName;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public Topic mapper(CourseRepository courseRepository) {
    Course course = courseRepository.findByName(courseName);
    return new Topic(title, message, course);
  }

}
