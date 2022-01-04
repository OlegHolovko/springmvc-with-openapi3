package com.holovko.springmvc.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventCustomDTO {
    private Integer num;
    private String name;
    private String startDate;
}
