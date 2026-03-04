package com.taskmanagent.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Representação de uma tarefa")
public class TaskResponse {

    @Schema(description = "Identificador único da tarefa", example = "550e8400-e29b-41d4-a716-446655440000")
    private String id;

    @Schema(description = "Título da tarefa", example = "Estudar Spring Boot")
    private String title;

    @Schema(description = "Descrição da tarefa", example = "Revisar conceitos de DynamoDB")
    private String description;

    @Schema(description = "Status atual da tarefa", example = "pending")
    private String status;

    @Schema(description = "Prioridade da tarefa", example = "high")
    private String priority;

    @JsonProperty("due_date")
    @Schema(description = "Data de vencimento", example = "2026-12-31")
    private String dueDate;

    @JsonProperty("created_at")
    @Schema(description = "Data e hora de criação")
    private String createdAt;

    @JsonProperty("updated_at")
    @Schema(description = "Data e hora da última atualização")
    private String updatedAt;
}
