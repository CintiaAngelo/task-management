package com.taskmanagent.exception;

import com.taskmanagent.controller.TaskController;
import com.taskmanagent.dto.CreateTaskRequest;
import com.taskmanagent.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("GlobalExceptionHandler - Testes Unitários")
class GlobalExceptionHandlerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    private MockMvc mockMvc;

    @BeforeEach
    void configurar() {
        mockMvc = MockMvcBuilders.standaloneSetup(taskController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    @DisplayName("deve retornar 500 para exceção genérica inesperada")
    void deveRetornar500ParaExcecaoGenerica() throws Exception {
        when(taskService.criarTarefa(any())).thenThrow(new RuntimeException("erro inesperado"));

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Tarefa\"}"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.message").value("Ocorreu um erro interno. Tente novamente mais tarde."));
    }

    @Test
    @DisplayName("deve retornar 400 para IllegalArgumentException")
    void deveRetornar400ParaIllegalArgumentException() throws Exception {
        when(taskService.criarTarefa(any())).thenThrow(new IllegalArgumentException("argumento inválido"));

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Tarefa\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("argumento inválido"));
    }

    @Test
    @DisplayName("deve retornar 400 com mensagem de status inválido quando JSON contém TaskStatus inválido")
    void deveRetornar400ComMensagemStatusInvalidoNoJson() throws Exception {
        mockMvc.perform(put("/tasks/qualquer-id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"status\": \"invalido\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value(
                        "Status inválido. Valores permitidos: pending, in_progress, completed, cancelled"));
    }

    @Test
    @DisplayName("deve retornar 400 com mensagem de prioridade inválida quando JSON contém TaskPriority inválida")
    void deveRetornar400ComMensagemPrioridadeInvalidaNoJson() throws Exception {
        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Tarefa\", \"priority\": \"urgente\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value(
                        "Prioridade inválida. Valores permitidos: low, medium, high"));
    }

    @Test
    @DisplayName("deve retornar 400 para JSON malformado")
    void deveRetornar400ParaJsonMalformado() throws Exception {
        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value(
                        "Requisição inválida. Verifique os valores enviados."));
    }
}