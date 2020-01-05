package com.thinging.project.repository;

import com.thinging.project.entity.ThingGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface GroupRepository extends JpaRepository<ThingGroup,Long> {

    boolean existsByName(String name);
    Optional<ThingGroup> findByName(String groupName);

    List<ThingGroup> findByNameIn(Set<String> names);

}
