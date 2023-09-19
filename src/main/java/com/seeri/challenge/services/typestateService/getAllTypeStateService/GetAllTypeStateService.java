package com.seeri.challenge.services.typestateService.getAllTypeStateService;

import com.google.gson.Gson;
import com.seeri.challenge.entitys.TypeStateEntity;
import com.seeri.challenge.models.ResponseControllerModel;
import com.seeri.challenge.models.ResponseModel;
import com.seeri.challenge.repositorys.TypeStateRepository;
import com.seeri.challenge.util.IUtil;
import com.seeri.challenge.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllTypeStateService implements IGetAllTypeStateService {

    private ResponseControllerModel responController = new ResponseControllerModel();
    @Autowired
    private TypeStateRepository typeStateRepositoryImpl;
    private IUtil util = new Util();
    private Gson gson = new Gson();

    public ResponseControllerModel getAllTypeState(){
        try{
            List<TypeStateEntity> typeStateList = typeStateRepositoryImpl.findAll();
            responController=processAllTypeState(typeStateList);
        }catch (Exception e) {
            responController.setStatus(404);
            responController.setResponse(util.responseTryCatch(e.getMessage(),"'type_state'"));
        }
        return responController;
    }

    private ResponseControllerModel processAllTypeState(List<TypeStateEntity> typeStateList ){
        int state = typeStateList.isEmpty() ? 404 : 200;
        ResponseModel<String> responseModel = util.response(typeStateList.isEmpty() ? "Sin datos" : "Cantidad de items " + typeStateList.size(),gson.toJson(typeStateList),true,typeStateList.isEmpty());
        responController.setStatus(state);
        responController.setResponse(responseModel);
        return responController;
    }
}
