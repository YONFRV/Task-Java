package com.seeri.challenge.services.taskService.putTaskService;

import com.google.gson.Gson;
import com.seeri.challenge.entitys.TaskEntity;
import com.seeri.challenge.entitys.TypeStateEntity;
import com.seeri.challenge.models.RequestDataTaskModel;
import com.seeri.challenge.models.ResponseControllerModel;
import com.seeri.challenge.repositorys.TaskRepository;
import com.seeri.challenge.repositorys.TypeStateRepository;
import com.seeri.challenge.util.IUtil;
import com.seeri.challenge.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PutTaskService implements IPutTaskService{
    private String mennsageTypState ="TypeState asociado no se encuentra en base de datos";
    private ResponseControllerModel responController = new ResponseControllerModel();
    @Autowired
    private TypeStateRepository typeStateRepository;
    @Autowired
    private TaskRepository taskRepository;
    private  String  tabledb ="'task'";
    private IUtil util = new Util();
    private Gson gson = new Gson();
    public ResponseControllerModel updateTask(RequestDataTaskModel dataBody){
        try{
            TypeStateEntity typeState = typeStateRepository.findById(Long.valueOf(dataBody.getState())).orElse(null);
            responController= processValidationTypeStateUpdate(typeState,dataBody);
        }catch (Exception e) {
            responController.setStatus(404);
            responController.setResponse(util.responseTryCatch(e.getMessage(),tabledb));
        }
        return responController;
    }
    private ResponseControllerModel processValidationTypeStateUpdate(TypeStateEntity typeState,RequestDataTaskModel dataBody){
        if(typeState!=null){
            responController = dataTask(typeState,dataBody);
        }
        else{
            responController.setStatus(404);
            responController.setResponse(util.response(mennsageTypState,"",true,true));
        }
        return responController;
    }
    private ResponseControllerModel dataTask(TypeStateEntity typeState,RequestDataTaskModel dataBody){
        try{
            Optional<TaskEntity> taskData = taskRepository.findById(Long.valueOf(dataBody.getId()));
            responController = validationDataTask(typeState,taskData,dataBody);
        }catch (Exception e) {
            responController.setStatus(404);
            responController.setResponse(util.responseTryCatch(e.getMessage(),tabledb));
        }
        return responController;
    }
    private ResponseControllerModel validationDataTask(TypeStateEntity typeState,Optional<TaskEntity> taskData,RequestDataTaskModel dataBody){
        if(taskData.isPresent()){
            TaskEntity task= new TaskEntity();
            task.setId(taskData.get().getId());
            task.setState(taskData.get().getState());
            task.setDescripcion(taskData.get().getDescripcion());
            task.setState(taskData.get().getState());
            responController= updateDataTask(typeState,task,dataBody);
        }
        else{
            responController.setStatus(404);
            responController.setResponse(util.response(mennsageTypState,"",true,true));
        }
        return responController;
    }
    private ResponseControllerModel updateDataTask(TypeStateEntity typeState,TaskEntity task,RequestDataTaskModel dataBody){
        int state = 0;
        try{
            state= 200;
            Date currentDate = new Date();
            task.setTitulo(dataBody.getTitulo());
            task.setDescripcion(dataBody.getDescripcion());
            task.setState(typeState);
            task.setUpdateDate(currentDate);
            TaskEntity responseTaskUpdate = taskRepository.save(task);
            responController.setResponse(util.response("Task Actualizado: ",gson.toJson(responseTaskUpdate),true,false));
        }catch (Exception e) {
            state= 404;
            responController.setResponse(util.responseTryCatch(e.getMessage(),tabledb));
        }
        responController.setStatus(state);
        return responController;
    }
}
