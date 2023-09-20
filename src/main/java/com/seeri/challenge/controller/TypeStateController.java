package com.seeri.challenge.controller;

import com.seeri.challenge.model.RequestBodyStateTypeModel;
import com.seeri.challenge.model.ResponseControllerModel;
import com.seeri.challenge.model.ResponseModel;
import com.seeri.challenge.service.typestateService.deleteTypeStateService.IDeleteTypeStateService;
import com.seeri.challenge.service.typestateService.getAllTypeStateService.GetAllTypeStateService;
import com.seeri.challenge.service.typestateService.getByTypeStateService.GetByTypeStateService;
import com.seeri.challenge.service.typestateService.postTypeStateService.IPostTypeStateService;
import com.seeri.challenge.service.typestateService.putTypeStateService.IPutTypeStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("api/v1")
public class TypeStateController {
    @Autowired
    private IDeleteTypeStateService iDeleteTypeStateService;
    @Autowired
    private GetAllTypeStateService getAllTypeStateService;
    @Autowired
    private GetByTypeStateService getByTypeStateService;
    @Autowired
    private IPostTypeStateService iPostTypeStateService;
    @Autowired
    private IPutTypeStateService iPutTypeStateService;


    @GetMapping("/full-types-states")
    public  ResponseEntity<ResponseModel<String>> fullTypeStates() {
        ResponseControllerModel response = getAllTypeStateService.getAllTypeState();
        return new ResponseEntity<>(response.getResponse(), HttpStatus.valueOf(response.getStatus()));
    }

    @GetMapping("/by-type-state/{idTypeState}")
    public  ResponseEntity<ResponseModel<String>> byTypeState(@PathVariable long idTypeState) {
        ResponseControllerModel response = getByTypeStateService.getByIdTypeState(idTypeState);
        return new ResponseEntity<>(response.getResponse(), HttpStatus.valueOf(response.getStatus()));
    }

    @PostMapping("save-type-state")
    public  ResponseEntity<ResponseModel<String>> saveTypeState(@RequestBody RequestBodyStateTypeModel bodyRequest) {
        ResponseControllerModel response = iPostTypeStateService.postTypeState(bodyRequest);
        return new ResponseEntity<>(response.getResponse(), HttpStatus.valueOf(response.getStatus()));
    }

   @PutMapping("update-type-state/{idTypeState}")
    public ResponseEntity<ResponseModel<String>> updateTypeState(@RequestBody RequestBodyStateTypeModel bodyRequest, @PathVariable long idTypeState) {
        bodyRequest.setTypeStateId(idTypeState);
        ResponseControllerModel response = iPutTypeStateService.putTypeState(bodyRequest);
        return new ResponseEntity<>(response.getResponse(), HttpStatus.valueOf(response.getStatus()));
    }

    @DeleteMapping("delete-type-state/{idTypeState}")
    public ResponseEntity<ResponseModel<String>> deleteTypeState( @PathVariable long idTypeState) {
        ResponseControllerModel response = iDeleteTypeStateService.deleteUpdateTypeState(idTypeState);
        return new ResponseEntity<>(response.getResponse(), HttpStatus.valueOf(response.getStatus()));
    }
}
