package com.taskmanagent.exception;

public class TaskNotEditableException extends RuntimeException {

    public TaskNotEditableException(String id) {
        super("Tarefa com ID '" + id + "' não pode ser editada pois está com status 'completed'");
    }
}
