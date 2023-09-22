package org.alex.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class Item {

    private int id;
    private String name;
    private int price;
    private ItemDepartmentType itemDepartmentType;
    private LocalDateTime creationModificationDate;
}
