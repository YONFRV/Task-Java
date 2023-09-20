package com.seeri.challenge.service.taskService.deleteTaskService;

import com.seeri.challenge.model.ResponseControllerModel;

public interface IDeleteTaskService {
    ResponseControllerModel deleteTask(Long idTask);
}
