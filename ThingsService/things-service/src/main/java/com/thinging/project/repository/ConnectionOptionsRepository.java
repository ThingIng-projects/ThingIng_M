package com.thinging.project.repository;

import com.thinging.project.entity.ConnectionOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectionOptionsRepository extends JpaRepository<ConnectionOption,Long> {
}
