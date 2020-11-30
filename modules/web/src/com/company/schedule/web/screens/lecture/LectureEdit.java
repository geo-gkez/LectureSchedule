package com.company.schedule.web.screens.lecture;

import com.haulmont.cuba.gui.screen.*;
import com.company.schedule.entity.Lecture;

@UiController("schedule_Lecture.edit")
@UiDescriptor("lecture-edit.xml")
@EditedEntityContainer("lectureDc")
@LoadDataBeforeShow
public class LectureEdit extends StandardEditor<Lecture> {
    @Subscribe
    public void onInitEntity(InitEntityEvent<Lecture> event) {
        event.getEntity().setDuration(1);
    }
}