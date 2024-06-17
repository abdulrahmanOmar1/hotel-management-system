package org.example.finalprojectweb.services.implementations;


import org.example.finalprojectweb.DTO.HouseKeepingTaskDTO;
import org.example.finalprojectweb.entity.HouseKeepingTask;
import org.example.finalprojectweb.entity.Room;
import org.example.finalprojectweb.entity.User;
import org.example.finalprojectweb.exceptions.ResourceNotFoundException;
import org.example.finalprojectweb.repository.HouseKeepingTaskRepository;
import org.example.finalprojectweb.repository.RoomRepository;
import org.example.finalprojectweb.repository.UserRepository;
import org.example.finalprojectweb.services.interfaces.HouseKeepingTaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HouseKeepingTaskServiceImpl implements HouseKeepingTaskService {

    private final HouseKeepingTaskRepository houseKeepingTaskRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public HouseKeepingTaskServiceImpl(HouseKeepingTaskRepository houseKeepingTaskRepository,
                                       RoomRepository roomRepository,
                                       UserRepository userRepository) {
        this.houseKeepingTaskRepository = houseKeepingTaskRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    @Override
    public HouseKeepingTaskDTO createHouseKeepingTask(HouseKeepingTaskDTO houseKeepingTaskDTO) {
        HouseKeepingTask houseKeepingTask = convertToEntity(houseKeepingTaskDTO);
        HouseKeepingTask newTask = houseKeepingTaskRepository.save(houseKeepingTask);
        return convertToDto(newTask);
    }

    @Override
    public HouseKeepingTaskDTO getHouseKeepingTaskById(Long id) {
        HouseKeepingTask task = houseKeepingTaskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("HouseKeepingTask", "id", id));
        return convertToDto(task);
    }

    @Override
    public HouseKeepingTaskDTO updateHouseKeepingTask(HouseKeepingTaskDTO houseKeepingTaskDTO, Long id) {
        HouseKeepingTask existingTask = houseKeepingTaskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("HouseKeepingTask", "id", id));

        existingTask.setRoom(getRoomById(houseKeepingTaskDTO.getRoomId()));
        existingTask.setEmployee(getUserById(houseKeepingTaskDTO.getEmployeeId()));
        existingTask.setTaskDescription(houseKeepingTaskDTO.getTaskDescription());
        existingTask.setTaskDate(houseKeepingTaskDTO.getTaskDate());
        existingTask.setCompletedDate(houseKeepingTaskDTO.getCompletedDate());
        existingTask.setStatus(HouseKeepingTask.Status.valueOf(houseKeepingTaskDTO.getStatus()));

        HouseKeepingTask updatedTask = houseKeepingTaskRepository.save(existingTask);
        return convertToDto(updatedTask);
    }

    @Override
    public void deleteHouseKeepingTask(Long id) {
        HouseKeepingTask task = houseKeepingTaskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("HouseKeepingTask", "id", id));
        houseKeepingTaskRepository.delete(task);
    }

    @Override
    public List<HouseKeepingTaskDTO> getAllHouseKeepingTasks() {
        List<HouseKeepingTask> tasks = houseKeepingTaskRepository.findAll();
        return tasks.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private HouseKeepingTaskDTO convertToDto(HouseKeepingTask task) {
        HouseKeepingTaskDTO taskDTO = new HouseKeepingTaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setRoomId(task.getRoom().getId());
        taskDTO.setEmployeeId(task.getEmployee().getId());
        taskDTO.setTaskDescription(task.getTaskDescription());
        taskDTO.setTaskDate(task.getTaskDate());
        taskDTO.setCompletedDate(task.getCompletedDate());
        taskDTO.setStatus(task.getStatus().name());
        return taskDTO;
    }

    private HouseKeepingTask convertToEntity(HouseKeepingTaskDTO taskDTO) {
        HouseKeepingTask task = new HouseKeepingTask();
        // Assuming there are methods to get Room and User by id
        task.setRoom(getRoomById(taskDTO.getRoomId()));
        task.setEmployee(getUserById(taskDTO.getEmployeeId()));
        task.setTaskDescription(taskDTO.getTaskDescription());
        task.setTaskDate(taskDTO.getTaskDate());
        task.setCompletedDate(taskDTO.getCompletedDate());
        task.setStatus(HouseKeepingTask.Status.valueOf(taskDTO.getStatus()));
        return task;
    }

    private Room getRoomById(Long roomId) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);
        return roomOptional.orElseThrow(() -> new ResourceNotFoundException("Room", "id", roomId));
    }

    private User getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }
}