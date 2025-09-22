package com.acedboard.acedboard_server.dto;

public record TaskInput(Long projectId,
                String title, String description, String status,
                String priority, Long dueAt, Integer estimatedMinutes) {

}
