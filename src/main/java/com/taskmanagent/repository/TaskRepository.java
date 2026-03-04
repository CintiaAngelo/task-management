package com.taskmanagent.repository;


import com.taskmanagent.entity.Task;
import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    Task salvar(Task task);

    Optional<Task> buscarPorId(String id);

    List<Task> listarTodas();

    List<Task> listarPorStatus(String status);

    List<Task> listarPorPrioridade(String priority);

    void deletar(String id);
}

