package com.taskmanagent.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taskmanagent.enums.TaskPriority;
import com.taskmanagent.enums.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados para atualização de uma tarefa existente")
public class UpdateTaskRequest {

    @Size(min = 3, max = 100, message = "Título deve ter entre 3 e 100 caracteres")
    @Schema(description = "Novo título da tarefa", example = "Estudar Spring Boot - Atualizado")
    private String title;

    @Schema(description = "Nova descrição da tarefa")
    private String description;

    @Schema(description = "Novo status da tarefa", example = "in_progress",
            allowableValues = {"pending", "in_progress", "completed", "cancelled"})
    private TaskStatus status;

    @Schema(description = "Nova prioridade da tarefa", example = "medium",
            allowableValues = {"low", "medium", "high"})
    private TaskPriority priority;

    @JsonProperty("due_date")
    @Schema(description = "Nova data de vencimento no formato yyyy-MM-dd", example = "2026-12-31")
    private String dueDate;
}
