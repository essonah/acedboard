package com.acedboard.acedboard_server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.acedboard.acedboard_server.model.Project;

public interface ProjectRepo extends JpaRepository<Project, Long>{
    List<Project> findByUserId(Long userId);

    
}
