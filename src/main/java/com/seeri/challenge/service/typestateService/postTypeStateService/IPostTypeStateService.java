package com.seeri.challenge.service.typestateService.postTypeStateService;

import com.seeri.challenge.model.RequestBodyStateTypeModel;
import com.seeri.challenge.model.ResponseControllerModel;

public interface IPostTypeStateService {
    ResponseControllerModel postTypeState(RequestBodyStateTypeModel bodyRequest);
}
