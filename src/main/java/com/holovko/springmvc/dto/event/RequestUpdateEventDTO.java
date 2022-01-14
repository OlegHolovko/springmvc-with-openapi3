package com.holovko.springmvc.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestUpdateEventDTO {
    private Long id;
    private String name;
    private Integer amount;
    private Integer price;
    private String startDate;
}
