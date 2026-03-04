package com.taskmanagent.repository;

import com.taskmanagent.entity.Task;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

    private final DynamoDbTable<Task> taskTable;

    @Override
    public Task salvar(Task task) {
        log.debug("Salvando tarefa com ID: {}", task.getId());
        taskTable.putItem(task);
        return task;
    }

    @Override
    public Optional<Task> buscarPorId(String id) {
        log.debug("Buscando tarefa com ID: {}", id);
        Key key = Key.builder().partitionValue(id).build();
        Task task = taskTable.getItem(key);
        return Optional.ofNullable(task);
    }

    @Override
    public List<Task> listarTodas() {
        log.debug("Listando todas as tarefas");
        return taskTable.scan(ScanEnhancedRequest.builder().build())
                .items()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> listarPorStatus(String status) {
        log.debug("Listando tarefas com status: {}", status);
        return listarTodas().stream()
                .filter(task -> status.equalsIgnoreCase(task.getStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> listarPorPrioridade(String priority) {
        log.debug("Listando tarefas com prioridade: {}", priority);
        return listarTodas().stream()
                .filter(task -> priority.equalsIgnoreCase(task.getPriority()))
                .collect(Collectors.toList());
    }

    @Override
    public void deletar(String id) {
        log.debug("Deletando tarefa com ID: {}", id);
        Key key = Key.builder().partitionValue(id).build();
        taskTable.deleteItem(key);
    }
}
