package com.seeri.challenge.services.taskService.getByTaskService;

import com.google.gson.Gson;
import com.seeri.challenge.entities.TaskEntity;
import com.seeri.challenge.models.ResponseControllerModel;
import com.seeri.challenge.repositorys.TaskRepository;
import com.seeri.challenge.util.IUtil;
import com.seeri.challenge.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetByTaskService implements IGetByTaskService {
    private ResponseControllerModel responController = new ResponseControllerModel();
    @Autowired
    private TaskRepository taskRepository;
    private  String  tabledb ="'task'";
    private IUtil util = new Util();
    private Gson gson = new Gson();

    public ResponseControllerModel getByTaskService(long taskId) {
        try{
            TaskEntity task = taskRepository.findById(taskId).orElse(null);
            responController = processRepsonse(task);
        }catch (Exception ex){
            responController.setStatus(404);
            responController.setResponse(util.responseTryCatch(ex.getMessage(),tabledb));
        }
        return responController;
    }

    private ResponseControllerModel processRepsonse(TaskEntity task){
        if(task!=null){
            responController.setStatus(202);
            responController.setResponse(util.response("Task: ",gson.toJson(task),true,false));
        }
        else{
            responController.setStatus(404);
            responController.setResponse(util.response("Task asociado no se encuentra en base de datos id buscado: "+task.getId(),tabledb,true,true));
        }
        return responController;
    }
}
