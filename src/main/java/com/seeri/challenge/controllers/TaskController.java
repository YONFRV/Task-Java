package com.seeri.challenge.controllers;

import com.seeri.challenge.models.RequestDataTaskModel;
import com.seeri.challenge.models.ResponseControllerModel;
import com.seeri.challenge.models.ResponseModel;
import com.seeri.challenge.services.taskService.deleteTaskService.IDeleteTaskService;
import com.seeri.challenge.services.taskService.getAllTasksService.IGetAllTasksService;
import com.seeri.challenge.services.taskService.getByTaskService.IGetByTaskService;
import com.seeri.challenge.services.taskService.postTaskService.IPostTaskService;
import com.seeri.challenge.services.taskService.putTaskService.IPutTaskService;
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


    @PostMapping("/by-task")
    public ResponseEntity<ResponseModel<String>> searchTask(@PathVariable long taskId) {
        ResponseControllerModel response = iGetByTaskService.getByTaskService(taskId);
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