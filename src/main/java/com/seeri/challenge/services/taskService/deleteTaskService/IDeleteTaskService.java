package com.seeri.challenge.services.taskService.deleteTaskService;

import com.seeri.challenge.models.ResponseControllerModel;

public interface IDeleteTaskService {
    ResponseControllerModel deleteTask(Long idTask);
}
