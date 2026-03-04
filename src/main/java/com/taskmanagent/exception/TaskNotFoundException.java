package com.taskmanagent.exception;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(String id) {
        super("Tarefa não encontrada com o ID: " + id);
    }
}