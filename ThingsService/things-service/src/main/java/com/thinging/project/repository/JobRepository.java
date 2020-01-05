package com.thinging.project.repository;

import com.thinging.project.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface JobRepository extends JpaRepository<Job,Long> {

    boolean existsByName(String name);
    Optional<Job> findByName(String name);

    List<Job> findByNameIn(Set<String> names);

}
