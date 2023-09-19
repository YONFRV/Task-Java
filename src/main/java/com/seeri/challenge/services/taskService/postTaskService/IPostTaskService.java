package com.seeri.challenge.services.taskService.postTaskService;

import com.seeri.challenge.models.RequestDataTaskModel;
import com.seeri.challenge.models.ResponseControllerModel;

public interface IPostTaskService {
    ResponseControllerModel postTask(RequestDataTaskModel dataBody);
}
