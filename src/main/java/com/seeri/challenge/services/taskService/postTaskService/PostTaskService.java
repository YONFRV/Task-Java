package com.seeri.challenge.services.taskService.postTaskService;

import com.google.gson.Gson;
import com.seeri.challenge.entities.TaskEntity;
import com.seeri.challenge.entities.TypeStateEntity;
import com.seeri.challenge.models.RequestDataTaskModel;
import com.seeri.challenge.models.ResponseControllerModel;
import com.seeri.challenge.repositorys.TaskRepository;
import com.seeri.challenge.repositorys.TypeStateRepository;
import com.seeri.challenge.util.IUtil;
import com.seeri.challenge.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PostTaskService implements IPostTaskService{

    private ResponseControllerModel responController = new ResponseControllerModel();
    @Autowired
    private TypeStateRepository typeStateRepository;
    @Autowired
    private TaskRepository taskRepository;
    private  String  tabledb ="'task'";
    private IUtil util = new Util();
    private Gson gson = new Gson();

    public ResponseControllerModel postTask(RequestDataTaskModel dataBody){
        try{
            TypeStateEntity typeState = typeStateRepository.findById(Long.valueOf(dataBody.getState())).orElse(null);
            responController= processValidationTypeState(typeState,dataBody);
        }catch (Exception e) {
            responController.setStatus(404);
            responController.setResponse(util.responseTryCatch(e.getMessage(),tabledb));
        }
        return responController;
    }
    private ResponseControllerModel processValidationTypeState(TypeStateEntity typeState,RequestDataTaskModel dataBody){
        if(typeState !=null){
            responController = processSaveTask(typeState,dataBody);
        }
        else{
            responController.setStatus(400);
            responController.setResponse(util.response("TypeState asociado no se encuentra en base de datos","",true,true));
        }
        return responController;
    }
    private ResponseControllerModel processSaveTask(TypeStateEntity typeState,RequestDataTaskModel dataBody){
        TaskEntity taskResult;
        try{
            Date currentDate = new Date();
            TaskEntity task= new TaskEntity();
            task.setState(typeState);
            task.setCreateDate(currentDate);
            task.setTitulo(dataBody.getTitulo());
            task.setDescripcion(dataBody.getDescripcion());
            taskResult = taskRepository.save(task);
            responController.setStatus(200);
            responController.setResponse(util.response("Task creada: ",gson.toJson(taskResult),true,false));
        }catch (Exception e) {
            responController.setStatus(404);
            responController.setResponse(util.responseTryCatch(e.getMessage(),tabledb));
        }
        return responController;
    }
}
