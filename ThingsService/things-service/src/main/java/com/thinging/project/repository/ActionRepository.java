package com.thinging.project.repository;

import com.thinging.project.entity.ThingingAction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository<ThingingAction,Long> {
}
