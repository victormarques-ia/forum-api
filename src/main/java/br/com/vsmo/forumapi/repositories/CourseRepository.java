package br.com.vsmo.forumapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vsmo.forumapi.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

  Course findByName(String courseName);

}
