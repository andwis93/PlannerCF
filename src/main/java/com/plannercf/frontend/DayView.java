package com.plannercf.frontend;

import com.plannercf.backend.domain.Day;
import com.plannercf.backend.facade.DayFacade;
import com.plannercf.frontend.style.StyleMgr;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@PageTitle("Day | PlannerCF")
@Route(value = "")
public class DayView extends VerticalLayout {

    private final DayFacade facade;
    private final StyleMgr styleMgr;

    @Autowired
    public DayView(DayFacade facade, StyleMgr styleMgn) {
        this.facade = facade;
        this.styleMgr = styleMgn;
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
        List<Day> days = facade.getAllDays();
        for(Day day: days) {
            dayBar(day.getDayName(),day.getDate().toString(), styleMgr.getDaysColor().get(day.getDayName()));
        }
    }
}
