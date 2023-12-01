package com.plannercf.frontend;

import com.plannercf.backend.domain.Day;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.util.ArrayList;
import java.util.List;

@PageTitle("Day | PlannerCF")
@Route(value = "")
public class DayView extends VerticalLayout {

    public DayView() {
    setSizeFull();
    
    getDayBarGenerator();
    }

    private void dayBar(String name, String date, String color) {
        NativeLabel dayName = new NativeLabel(name);
        dayName.addClassName("dayName");

        NativeLabel dayDate = new NativeLabel(date);
        dayDate.addClassName("date");

        VerticalLayout vlDay = new VerticalLayout(dayName, dayDate);
        vlDay.setWidthFull();
        vlDay.addClassName("vlDay");
        vlDay.getStyle().set("background", color);
        vlDay.setSpacing(false);
        vlDay.setAlignItems(Alignment.CENTER);

        VerticalLayout vlGroups = new VerticalLayout();

        this.add(vlDay, vlGroups);
    }

    private void getDayBarGenerator() {
        List<Day> days = new ArrayList<>();
        for(Day day: days) {
            dayBar(day.getDayName(), day.getDate().toString(), "color");
        }
    }
}
