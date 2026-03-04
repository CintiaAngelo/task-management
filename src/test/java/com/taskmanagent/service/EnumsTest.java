package com.taskmanagent.service;

import com.taskmanagent.enums.TaskPriority;
import com.taskmanagent.enums.TaskStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Enums - Testes Unitários")
class EnumsTest {

    @Nested
    @DisplayName("TaskStatus")
    class TaskStatusTest {

        @Test
        @DisplayName("deve converter string 'pending' para TaskStatus.PENDING")
        void deveConverterStringPendingParaTaskStatusPending() {
            assertThat(TaskStatus.fromValue("pending")).isEqualTo(TaskStatus.PENDING);
        }

        @Test
        @DisplayName("deve converter string 'in_progress' para TaskStatus.IN_PROGRESS")
        void deveConverterStringInProgressParaTaskStatusInProgress() {
            assertThat(TaskStatus.fromValue("in_progress")).isEqualTo(TaskStatus.IN_PROGRESS);
        }

        @Test
        @DisplayName("deve converter string 'completed' para TaskStatus.COMPLETED")
        void deveConverterStringCompletedParaTaskStatusCompleted() {
            assertThat(TaskStatus.fromValue("completed")).isEqualTo(TaskStatus.COMPLETED);
        }

        @Test
        @DisplayName("deve converter string 'cancelled' para TaskStatus.CANCELLED")
        void deveConverterStringCancelledParaTaskStatusCancelled() {
            assertThat(TaskStatus.fromValue("cancelled")).isEqualTo(TaskStatus.CANCELLED);
        }

        @ParameterizedTest
        @ValueSource(strings = {"PENDING", "Pending", "PENDING"})
        @DisplayName("deve converter string em qualquer case para o enum correto")
        void deveConverterStringEmQualquerCaseParaEnumCorreto(String valor) {
            assertThat(TaskStatus.fromValue(valor)).isEqualTo(TaskStatus.PENDING);
        }

        @Test
        @DisplayName("deve lançar IllegalArgumentException para status inválido")
        void deveLancarIllegalArgumentExceptionParaStatusInvalido() {
            assertThatThrownBy(() -> TaskStatus.fromValue("invalido"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Status inválido");
        }

        @Test
        @DisplayName("deve retornar o value correto para cada status")
        void deveRetornarValueCorretoParaCadaStatus() {
            assertThat(TaskStatus.PENDING.getValue()).isEqualTo("pending");
            assertThat(TaskStatus.IN_PROGRESS.getValue()).isEqualTo("in_progress");
            assertThat(TaskStatus.COMPLETED.getValue()).isEqualTo("completed");
            assertThat(TaskStatus.CANCELLED.getValue()).isEqualTo("cancelled");
        }
    }

    @Nested
    @DisplayName("TaskPriority")
    class TaskPriorityTest {

        @Test
        @DisplayName("deve converter string 'low' para TaskPriority.LOW")
        void deveConverterStringLowParaTaskPriorityLow() {
            assertThat(TaskPriority.fromValue("low")).isEqualTo(TaskPriority.LOW);
        }

        @Test
        @DisplayName("deve converter string 'medium' para TaskPriority.MEDIUM")
        void deveConverterStringMediumParaTaskPriorityMedium() {
            assertThat(TaskPriority.fromValue("medium")).isEqualTo(TaskPriority.MEDIUM);
        }

        @Test
        @DisplayName("deve converter string 'high' para TaskPriority.HIGH")
        void deveConverterStringHighParaTaskPriorityHigh() {
            assertThat(TaskPriority.fromValue("high")).isEqualTo(TaskPriority.HIGH);
        }

        @Test
        @DisplayName("deve lançar IllegalArgumentException para prioridade inválida")
        void deveLancarIllegalArgumentExceptionParaPrioridadeInvalida() {
            assertThatThrownBy(() -> TaskPriority.fromValue("urgente"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Prioridade inválida");
        }

        @Test
        @DisplayName("deve retornar o value correto para cada prioridade")
        void deveRetornarValueCorretoParaCadaPrioridade() {
            assertThat(TaskPriority.LOW.getValue()).isEqualTo("low");
            assertThat(TaskPriority.MEDIUM.getValue()).isEqualTo("medium");
            assertThat(TaskPriority.HIGH.getValue()).isEqualTo("high");
        }
    }
}
