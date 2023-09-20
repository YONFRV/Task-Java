package com.seeri.challenge.service.typestateService.getByTypeStateService;

import com.google.gson.Gson;
import com.seeri.challenge.entity.TypeStateEntity;
import com.seeri.challenge.model.ResponseControllerModel;
import com.seeri.challenge.repository.TypeStateRepository;
import com.seeri.challenge.util.IUtil;
import com.seeri.challenge.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetByTypeStateService implements IGetByTypeStateService {

    private ResponseControllerModel responController = new ResponseControllerModel();
    @Autowired
    private TypeStateRepository typeStateRepositoryImpl;
    private IUtil util = new Util();
    private Gson gson = new Gson();
    public ResponseControllerModel getByIdTypeState(long idTypeState){
        try {
            TypeStateEntity typeState = typeStateRepositoryImpl.findById(idTypeState).orElse(null);
            responController = validationByIdTypeState(typeState);
        }catch (Exception e) {
            responController.setStatus(404);
            responController.setResponse(util.responseTryCatch(e.getMessage(),"'type_state'"));
        }
        return responController;
    }

    private ResponseControllerModel validationByIdTypeState(TypeStateEntity typeState ){
        if(typeState != null){
            responController.setStatus(200);
            responController.setResponse(util.response("Tipo de estado: ",gson.toJson(typeState),true,false));
        }else{
            responController.setStatus(404);
            responController.setResponse(util.response("No se encontro typeState en base de datos","",true,true));
        }
        return responController;
    }
}
