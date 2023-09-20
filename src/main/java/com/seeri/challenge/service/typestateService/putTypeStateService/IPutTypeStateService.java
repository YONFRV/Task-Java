package com.seeri.challenge.service.typestateService.putTypeStateService;

import com.seeri.challenge.model.RequestBodyStateTypeModel;
import com.seeri.challenge.model.ResponseControllerModel;

public interface IPutTypeStateService {
    ResponseControllerModel putTypeState(RequestBodyStateTypeModel bodyRequest);
}
