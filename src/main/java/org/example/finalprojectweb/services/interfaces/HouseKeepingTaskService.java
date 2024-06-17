package org.example.finalprojectweb.services.interfaces;


import org.example.finalprojectweb.DTO.HouseKeepingTaskDTO;

import java.util.List;

public interface HouseKeepingTaskService {
    HouseKeepingTaskDTO createHouseKeepingTask(HouseKeepingTaskDTO houseKeepingTaskDTO);
    HouseKeepingTaskDTO getHouseKeepingTaskById(Long id);
    HouseKeepingTaskDTO updateHouseKeepingTask(HouseKeepingTaskDTO houseKeepingTaskDTO, Long id);
    void deleteHouseKeepingTask(Long id);
    List<HouseKeepingTaskDTO> getAllHouseKeepingTasks();
}