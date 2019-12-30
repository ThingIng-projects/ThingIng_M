package com.thinging.project.repository;

import com.thinging.project.entity.ThingingEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface EventRepository extends JpaRepository<ThingingEvent,Long> {

    List<ThingingEvent> findByIdIn(Set<Long> idList);

}
