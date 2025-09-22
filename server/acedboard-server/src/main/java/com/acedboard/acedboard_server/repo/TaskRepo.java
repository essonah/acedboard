package com.acedboard.acedboard_server.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acedboard.acedboard_server.model.Task;
public interface TaskRepo extends JpaRepository<Task, Long>{
    List<Task> findByProjectId(Long projectId);
    
}
