package com.seeri.challenge.service.taskService.putTaskService;

import com.seeri.challenge.model.RequestDataTaskModel;
import com.seeri.challenge.model.ResponseControllerModel;

public interface IPutTaskService {
    ResponseControllerModel updateTask(RequestDataTaskModel dataBody);
}
