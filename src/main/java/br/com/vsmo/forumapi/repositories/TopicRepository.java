package br.com.vsmo.forumapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vsmo.forumapi.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

  List<Topic> findByCourseName(String courseName);

}
