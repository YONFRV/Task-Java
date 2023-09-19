package com.seeri.challenge.services.typestateService.putTypeStateService;

import com.seeri.challenge.models.RequestBodyStateTypeModel;
import com.seeri.challenge.models.ResponseControllerModel;

public interface IPutTypeStateService {
    ResponseControllerModel putTypeState(RequestBodyStateTypeModel bodyRequest);
}
