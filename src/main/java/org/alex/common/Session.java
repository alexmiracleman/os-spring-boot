package org.alex.common;

import lombok.Builder;
import lombok.Data;
import org.alex.entity.Item;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class Session {
    private String token;
    private LocalDateTime expireDate;
    private List<Item> cart;
    private String userType;
}
