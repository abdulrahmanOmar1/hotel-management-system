package org.example.finalprojectweb.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class HouseKeepingTaskDTO {
    private Long id;
    private Long roomId;
    private Long employeeId;
    private String taskDescription;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date taskDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date completedDate;
    private String status;
}