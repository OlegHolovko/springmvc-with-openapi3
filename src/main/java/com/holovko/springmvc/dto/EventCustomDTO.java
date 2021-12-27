package com.holovko.springmvc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventCustomDTO {
    private Integer num;
    private String name;
    private String startDate;
}
