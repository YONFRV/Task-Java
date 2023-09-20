package com.seeri.challenge.service.typestateService.putTypeStateService;

import com.google.gson.Gson;
import com.seeri.challenge.entity.TypeStateEntity;
import com.seeri.challenge.model.RequestBodyStateTypeModel;
import com.seeri.challenge.model.ResponseControllerModel;
import com.seeri.challenge.model.ResponseModel;
import com.seeri.challenge.repository.TypeStateRepository;
import com.seeri.challenge.util.IUtil;
import com.seeri.challenge.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class PutTypeStateService implements  IPutTypeStateService {
    private ResponseControllerModel responController = new ResponseControllerModel();
    @Autowired
    private TypeStateRepository typeStateRepositoryImpl;
    private final String  tabledb ="'type_state'";
    private final IUtil util = new Util();
    private Gson gson = new Gson();

    public ResponseControllerModel putTypeState( RequestBodyStateTypeModel bodyRequest){
        if(!bodyRequest.getName().isEmpty()){
            Date currentDate = new Date();
            TypeStateEntity typeStateEntity = new TypeStateEntity();
            typeStateEntity.setName(bodyRequest.getName());
            typeStateEntity.setState(bodyRequest.isState());
            typeStateEntity.setCreateDate(currentDate);
            responController = validationDataPostTypeState(typeStateEntity);
        }
        else{
            responController.setStatus(400);
            responController.setResponse(util.response("Falta de información","",true,false));
        }
        return responController;
    }
    private ResponseControllerModel validationDataPostTypeState(TypeStateEntity typeStateEntity){
        try{
            TypeStateEntity typeState = typeStateRepositoryImpl.getTypeState(typeStateEntity.getName());
            responController=  validationDataTypeState(typeState,typeStateEntity);
        }catch (Exception e) {
            responController.setStatus(404);
            responController.setResponse(util.responseTryCatch(e.getMessage(),tabledb));
        }
        return responController;
    }
    private ResponseControllerModel validationDataTypeState(TypeStateEntity typeState,TypeStateEntity typeStateEntity){
        if(typeState==null){
            responController = processPostTypeState(typeStateEntity);
        }
        else{
            responController.setStatus(404);
            responController.setResponse(util.response("Este dato ya se encuentra creado: ",gson.toJson(typeState),true,true));
        }
        return responController;
    }
    private ResponseControllerModel processPostTypeState(TypeStateEntity typeStateEntity){
        int state;
        ResponseModel<String> responseModel;
        try{
            state=200;
            TypeStateEntity resul = typeStateRepositoryImpl.save(typeStateEntity);
            responseModel = util.response("Proceso Realizado",gson.toJson(resul),true,false);
        }
        catch (Exception e) {
            state=404;
            responseModel = util.responseTryCatch(e.getMessage(),tabledb);
        }
        responController.setStatus(state);
        responController.setResponse(responseModel);
        return responController;
    }
}
