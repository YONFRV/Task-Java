package com.seeri.challenge.service.taskService.postTaskService;

import com.seeri.challenge.model.RequestDataTaskModel;
import com.seeri.challenge.model.ResponseControllerModel;

public interface IPostTaskService {
    ResponseControllerModel postTask(RequestDataTaskModel dataBody);
}
