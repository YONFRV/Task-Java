package com.seeri.challenge.util;


import com.seeri.challenge.models.ResponseModel;
import org.springframework.stereotype.Service;

@Service
public class Util  implements  IUtil{

    public ResponseModel<String> response(String Menssage, String Data, boolean sucess, boolean warning){
        ResponseModel<String> responseModel = new ResponseModel<>();
        responseModel.setMessage(Menssage);
        responseModel.setWarning(warning);
        responseModel.setSuccess(sucess);
        responseModel.setData(Data);
        return responseModel;
    }
    public ResponseModel<String> responseTryCatch(String error, String table){
        ResponseModel<String> responseModel = new ResponseModel<>();
        responseModel.setMessage("Error en la consulta a la base de datos de la tabla "+table+": " +error);
        responseModel.setWarning(true);
        return  responseModel;
    }
}
