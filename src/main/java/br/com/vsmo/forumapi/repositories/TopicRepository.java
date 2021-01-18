package br.com.vsmo.forumapi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vsmo.forumapi.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

  Page<Topic> findByCourseName(String courseName, Pageable pagination);

}
