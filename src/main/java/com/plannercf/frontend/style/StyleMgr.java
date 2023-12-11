package com.plannercf.frontend.style;

import lombok.Getter;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Getter
@Service
public class StyleMng {
    private final Map<String, String> daysColor;

    public StyleMng() {
        this.daysColor = new HashMap<>();
        daysColor.put("MONDAY", Color.MONDAY.getColor());
        daysColor.put("TUESDAY", Color.TUESDAY.getColor());
        daysColor.put("WEDNESDAY", Color.WEDNESDAY.getColor());
        daysColor.put("THURSDAY", Color.THURSDAY.getColor());
        daysColor.put("FRIDAY", Color.FRIDAY.getColor());
        daysColor.put("SATURDAY", Color.SATURDAY.getColor());
        daysColor.put("SUNDAY", Color.SUNDAY.getColor());
    }
}
