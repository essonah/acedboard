package com.acedboard.acedboard_server.api;

import java.util.List;

import com.acedboard.acedboard_server.repo.TaskRepo;

import jakarta.validation.Valid;

import com.acedboard.acedboard_server.dto.ProjectInput;
import com.acedboard.acedboard_server.dto.TaskInput;
import com.acedboard.acedboard_server.model.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acedboard.acedboard_server.model.Task;
import com.acedboard.acedboard_server.repo.ProjectRepo;

import lombok.RequiredArgsConstructor;

@RestController @RequestMapping("/api") @RequiredArgsConstructor
public class ProjectTaskController {

    private final ProjectRepo projects;
    private final TaskRepo tasks;

    

    @GetMapping("/projects")
    public List<Project> listProjects() {
        return projects.findAll();
    }

    @PostMapping("/projects")
    public Project createProject(@Valid @RequestBody ProjectInput in) {
        var p = new Project();
        p.setUserId(in.userId());
        p.setProjectName(in.name());
        return projects.save(p);
    }

    @GetMapping("/projects/{projectId}/tasks")
    public List<Task> listTasks(@PathVariable Long projectId) {
        return tasks.findByProjectId(projectId);
    }

    @PostMapping("/projects/{projectId}/tasks")
    public Task createTask(@PathVariable Long projectId, @RequestBody TaskInput in) {
        var t = new Task();
        t.setProjectId(projectId);
        t.setTitle(in.title());
        t.setDescription(in.description());
        if (in.status() != null)
            t.setStatus(in.status());
        if (in.priority() != null)
            t.setPriority(in.priority());
        t.setEstimatedMinutes(in.estimatedMinutes());
        return tasks.save(t);

    }

    @PatchMapping("/tasks/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody TaskInput in) {
        var t = tasks.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        if (in.title() != null)
            t.setTitle(in.title());
        if (in.description() != null)
            t.setDescription(in.description());
        if (in.status() != null)
            t.setStatus(in.status());
        if (in.priority() != null)
            t.setPriority(in.priority());
        if (in.estimatedMinutes() != null)
            t.setEstimatedMinutes(in.estimatedMinutes());
        return tasks.save(t);
    }

}
