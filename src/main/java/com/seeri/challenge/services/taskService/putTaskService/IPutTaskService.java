package com.seeri.challenge.services.taskService.putTaskService;

import com.seeri.challenge.models.RequestDataTaskModel;
import com.seeri.challenge.models.ResponseControllerModel;

public interface IPutTaskService {
    ResponseControllerModel updateTask(RequestDataTaskModel dataBody);
}
