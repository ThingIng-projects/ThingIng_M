package com.thinging.project.repository;

import com.thinging.project.entity.Thing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ThingRepository extends JpaRepository<Thing,Long> {

     Optional<Thing> findByThingId(String thingId);
     boolean existsByThingId(String thingId);

     List<Thing> findByThingIdIn(Set<String> thingIds);

}
