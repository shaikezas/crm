package com.knowledgehut.crm.repositories;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.knowledgehut.crm.entities.Course;

@Repository
@Scope("singleton")
public interface CourseRepository extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course> {

 
  public List<Course> findByActive(boolean isActive);
}
