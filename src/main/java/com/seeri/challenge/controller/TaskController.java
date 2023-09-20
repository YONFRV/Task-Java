package com.seeri.challenge.controller;

import com.seeri.challenge.model.RequestDataTaskModel;
import com.seeri.challenge.model.ResponseControllerModel;
import com.seeri.challenge.model.ResponseModel;
import com.seeri.challenge.service.taskService.deleteTaskService.IDeleteTaskService;
import com.seeri.challenge.service.taskService.getAllTasksService.IGetAllTasksService;
import com.seeri.challenge.service.taskService.getByTaskService.IGetByTaskService;
import com.seeri.challenge.service.taskService.postTaskService.IPostTaskService;
import com.seeri.challenge.service.taskService.putTaskService.IPutTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("api/v1")
public class TaskController {
    @Autowired
    private IGetByTaskService iGetByTaskService;
    @Autowired
    private IGetAllTasksService iGetAllTasksService;
    @Autowired
    private IPostTaskService iPostTaskService;
    @Autowired
    private IPutTaskService iPutTaskService;
    @Autowired
    private IDeleteTaskService iDeleteTaskService;

    @GetMapping("/full-task")
    public ResponseEntity<ResponseModel<String>> getAllTask() {
        ResponseControllerModel response = iGetAllTasksService.getAllTask();
        return new ResponseEntity<>(response.getResponse(), HttpStatus.valueOf(response.getStatus()));
    }


    @GetMapping("/by-task/{idTask}")
    public ResponseEntity<ResponseModel<String>> searchTask(@PathVariable long idTask) {
        ResponseControllerModel response = iGetByTaskService.getByTaskService(idTask);
        return new ResponseEntity<>(response.getResponse(), HttpStatus.valueOf(response.getStatus()));
    }

    @PostMapping("/save-task")
    public ResponseEntity<ResponseModel<String>> saveTask(@RequestBody RequestDataTaskModel dataBody)  {
        ResponseControllerModel response = iPostTaskService.postTask(dataBody);
        return new ResponseEntity<>(response.getResponse(), HttpStatus.valueOf(response.getStatus()));
    }

    @PutMapping("/update-task/{idTask}")
    public ResponseEntity<ResponseModel<String>> updateTask(@RequestBody RequestDataTaskModel dataBody, @PathVariable long idTask)  {
        dataBody.setId(idTask);
        ResponseControllerModel response = iPutTaskService.updateTask(dataBody);
        return new ResponseEntity<>(response.getResponse(), HttpStatus.valueOf(response.getStatus()));
    }

    @DeleteMapping("/delete-task/{idTask}")
    public ResponseEntity<ResponseModel<String>> deleteTypeState( @PathVariable long idTask) {
        ResponseControllerModel response = iDeleteTaskService.deleteTask(idTask);
        return new ResponseEntity<>(response.getResponse(), HttpStatus.valueOf(response.getStatus()));
    }
}
