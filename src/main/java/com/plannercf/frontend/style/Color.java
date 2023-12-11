package com.plannercf.frontend.style;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Color {
    MONDAY("#B0C4DE"),
    TUESDAY("#FFEFD5"),
    WEDNESDAY("#DDA0DD"),
    THURSDAY("#87CEFA"),
    FRIDAY("#90EE90"),
    SATURDAY("#FFB6C1"),
    SUNDAY("#DCDCDC");

    private final String color;
}
