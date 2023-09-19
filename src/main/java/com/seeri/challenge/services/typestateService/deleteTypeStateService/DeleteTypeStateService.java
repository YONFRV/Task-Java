package com.seeri.challenge.services.typestateService.deleteTypeStateService;

import com.google.gson.Gson;
import com.seeri.challenge.entities.TaskEntity;
import com.seeri.challenge.entities.TypeStateEntity;
import com.seeri.challenge.models.ResponseControllerModel;
import com.seeri.challenge.models.ResponseModel;
import com.seeri.challenge.repositorys.TaskRepository;
import com.seeri.challenge.repositorys.TypeStateRepository;
import com.seeri.challenge.util.IUtil;
import com.seeri.challenge.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DeleteTypeStateService implements IDeleteTypeStateService{
    private ResponseControllerModel responController = new ResponseControllerModel();
    @Autowired
    private TypeStateRepository typeStateRepositoryImpl;
    @Autowired
    private TaskRepository taskRepository;
    private final IUtil util = new Util();
    private Gson gson = new Gson();
    public ResponseControllerModel deleteUpdateTypeState(long idTypeState){
        try{
            TypeStateEntity typeState = typeStateRepositoryImpl.findById(idTypeState).orElse(null);
            if(typeState != null){
                responController=validationDataTask(typeState);
            }else{
                responController.setStatus(404);
                responController.setResponse(util.response("Dato no encontrado para eliminar ",null,true,true));
            }
        }catch (Exception e) {
            responController.setStatus(404);
            responController.setResponse(util.responseTryCatch(e.getMessage(),"'type_state'"));
        }
        return responController;
    }
    private ResponseControllerModel validationDataTask(TypeStateEntity typeState){
        List<TaskEntity> task = taskRepository.getTask(typeState.getTypeStateId());
        if(task.isEmpty()){
            responController=processDeleteUpdateTypeState(typeState);
        }else{
            responController.setStatus(404);
            responController.setResponse(util.response("El dato no se puede eliminar porque está siendo usado en Task",gson.toJson(typeState),true,true));
        }
        return responController;
    }
    private ResponseControllerModel processDeleteUpdateTypeState(TypeStateEntity typeState){
        typeStateRepositoryImpl.delete(typeState);
        ResponseModel<String> responseModel = util.response("Proceso Realizado", gson.toJson(typeState),true,false);
        responController.setResponse(responseModel);
        responController.setStatus(200);
        return responController;
    }
}
