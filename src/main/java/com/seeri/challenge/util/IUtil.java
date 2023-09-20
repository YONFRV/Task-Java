package com.seeri.challenge.util;

import com.seeri.challenge.model.ResponseModel;

public interface IUtil {
    ResponseModel<String> responseTryCatch(String error, String table);
    ResponseModel<String> response(String Menssage,String Data,boolean sucess,boolean warning);
}
