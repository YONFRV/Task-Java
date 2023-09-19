package com.seeri.challenge.services.taskService.getAllTasksService;

import com.google.gson.Gson;
import com.seeri.challenge.entities.TaskEntity;
import com.seeri.challenge.models.ResponseControllerModel;
import com.seeri.challenge.models.ResponseModel;
import com.seeri.challenge.repositorys.TaskRepository;
import com.seeri.challenge.util.IUtil;
import com.seeri.challenge.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllTasksService implements IGetAllTasksService{
    @Autowired
    private TaskRepository taskRepository;
    private ResponseControllerModel responController = new ResponseControllerModel();
    private IUtil util = new Util();
    private  String  tabledb ="'task'";
    private Gson gson = new Gson();

    public ResponseControllerModel getAllTask(){
        try{
            List<TaskEntity> listTask = taskRepository.findAll();
            responController=processGetListTask(listTask);
        }catch (Exception e) {
            responController.setStatus(404);
            responController.setResponse(util.responseTryCatch(e.getMessage(),tabledb));
        }
        return responController;
    }

    private ResponseControllerModel processGetListTask(List<TaskEntity> listTask){
        int state = listTask.isEmpty() ? 404 : 200;
        ResponseModel<String> responseModel = util.response(listTask.isEmpty() ? "Sin datos" : "Cantidad de items " + listTask.size(),gson.toJson(listTask),true,listTask.isEmpty());
        responController.setStatus(state);
        responController.setResponse(responseModel);
        return responController;
    }
}
