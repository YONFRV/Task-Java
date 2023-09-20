package com.seeri.challenge.service.taskService.deleteTaskService;

import com.google.gson.Gson;
import com.seeri.challenge.entity.TaskEntity;
import com.seeri.challenge.model.ResponseControllerModel;
import com.seeri.challenge.repository.TaskRepository;
import com.seeri.challenge.util.IUtil;
import com.seeri.challenge.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DeleteTaskService implements  IDeleteTaskService{
    private ResponseControllerModel responController = new ResponseControllerModel();
    @Autowired
    private TaskRepository taskRepository;
    private static final String  table ="'task'";
    private IUtil util = new Util();
    private Gson gson = new Gson();
    public ResponseControllerModel deleteTask(Long idTask){
        try{
            TaskEntity taskData = taskRepository.findById(idTask).orElse(null);
            responController = validateTask(taskData);
        }catch (Exception e) {
            responController.setStatus(404);
            responController.setResponse(util.responseTryCatch(e.getMessage(),table));
        }
        return responController;
    }
    private ResponseControllerModel validateTask(TaskEntity taskData ){
        if(taskData != null){
            TaskEntity task = new TaskEntity();
            task.setId(taskData.getId());
            task.setTitulo(taskData.getTitulo());
            task.setDescripcion(taskData.getDescripcion());
            responController = processDeleteTask(task);
        }
        else{
            responController.setStatus(404);
            responController.setResponse(util.response("Task no encontrada en base de datos para eleminar","",true,true));
        }
        return responController;
    }
    private ResponseControllerModel processDeleteTask(TaskEntity taskData){
      int state;

        try{
            state= 200;
            taskRepository.delete(taskData);
            responController.setResponse( util.response("Task eliminado", gson.toJson(taskData),true,false));
        }catch (Exception e) {
            state= 404;
            responController.setResponse(util.responseTryCatch(e.getMessage(),table));
        }
        responController.setStatus(state);
        return responController;
    }

}
