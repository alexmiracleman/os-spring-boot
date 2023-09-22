package org.alex.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component

public class SessionTime {
    @Value("${sessionTimeToLive}")
    private long sessionTimeToLive;
}
