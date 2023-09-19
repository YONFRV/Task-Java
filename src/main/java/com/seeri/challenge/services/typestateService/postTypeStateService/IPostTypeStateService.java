package com.seeri.challenge.services.typestateService.postTypeStateService;

import com.seeri.challenge.models.RequestBodyStateTypeModel;
import com.seeri.challenge.models.ResponseControllerModel;

public interface IPostTypeStateService {
    ResponseControllerModel postTypeState(RequestBodyStateTypeModel bodyRequest);
}
