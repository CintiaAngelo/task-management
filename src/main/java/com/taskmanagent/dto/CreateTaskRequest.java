package com.taskmanagent.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taskmanagent.enums.TaskPriority;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dados para criação de uma nova tarefa")
public class CreateTaskRequest {

    @NotBlank(message = "Título é obrigatório")
    @Size(min = 3, max = 100, message = "Título deve ter entre 3 e 100 caracteres")
    @Schema(description = "Título da tarefa", example = "Estudar Spring Boot", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @Schema(description = "Descrição detalhada da tarefa", example = "Revisar conceitos de DynamoDB")
    private String description;

    @Schema(description = "Prioridade da tarefa", example = "high", allowableValues = {"low", "medium", "high"})
    private TaskPriority priority;

    @JsonProperty("due_date")
    @Schema(description = "Data de vencimento no formato yyyy-MM-dd", example = "2026-12-31")
    private String dueDate;
}
