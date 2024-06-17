package org.example.finalprojectweb.controller;


import org.example.finalprojectweb.DTO.HouseKeepingTaskDTO;
import org.example.finalprojectweb.services.interfaces.HouseKeepingTaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/housekeepingtasks")
public class HouseKeepingTaskController {

    private final HouseKeepingTaskService houseKeepingTaskService;

    public HouseKeepingTaskController(HouseKeepingTaskService houseKeepingTaskService) {
        this.houseKeepingTaskService = houseKeepingTaskService;
    }

    @GetMapping
    public ResponseEntity<List<HouseKeepingTaskDTO>> getAllHouseKeepingTasks() {
        List<HouseKeepingTaskDTO> tasks = houseKeepingTaskService.getAllHouseKeepingTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HouseKeepingTaskDTO> getHouseKeepingTaskById(@PathVariable Long id) {
        HouseKeepingTaskDTO task = houseKeepingTaskService.getHouseKeepingTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HouseKeepingTaskDTO> createHouseKeepingTask(@RequestBody HouseKeepingTaskDTO taskDTO) {
        HouseKeepingTaskDTO newTask = houseKeepingTaskService.createHouseKeepingTask(taskDTO);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HouseKeepingTaskDTO> updateHouseKeepingTask(@RequestBody HouseKeepingTaskDTO taskDTO,
                                                                      @PathVariable Long id) {
        HouseKeepingTaskDTO updatedTask = houseKeepingTaskService.updateHouseKeepingTask(taskDTO, id);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHouseKeepingTask(@PathVariable Long id) {
        houseKeepingTaskService.deleteHouseKeepingTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}