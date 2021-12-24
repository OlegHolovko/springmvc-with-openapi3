package com.holovko.springmvc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class EventDTO {
    private Integer num;
    private String name;
    private String startDate;
}
