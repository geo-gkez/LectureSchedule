package com.company.schedule.web.screens.lecture;

import com.company.schedule.service.LectureService;
import com.haulmont.cuba.core.global.Security;
import com.haulmont.cuba.gui.components.Calendar;
import com.haulmont.cuba.gui.components.EditorScreenFacet;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.*;
import com.company.schedule.entity.Lecture;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Date;

@UiController("schedule_Lecture.browse")
@UiDescriptor("lecture-browse.xml")
@LookupComponent("lecturesTable")
@LoadDataBeforeShow
public class LectureBrowse extends StandardLookup<Lecture> {

    @Inject
    private EditorScreenFacet<Lecture, LectureEdit> lectureEditDialog;
    @Inject
    private LectureService lectureService;
    @Inject
    private CollectionContainer<Lecture> lecturesDc;
    @Inject
    private Security security;

    private Boolean permissions () {
       return security.isSpecificPermitted("admin");
     }

    @Subscribe("lecturesCalendar")
    public void onLecturesCalendarCalendarEventClick(Calendar.CalendarEventClickEvent<Date> event) {
         if(!permissions()){
             return;
         }
          permissions();
          lectureEditDialog.setEntityProvider(()->(Lecture) event.getEntity());
          lectureEditDialog.show();

    }

    @Subscribe("lecturesCalendar")
    public void onLecturesCalendarCalendarEventMove(Calendar.CalendarEventMoveEvent<LocalDateTime> event) {

         Lecture lectureEvent = (Lecture) event.getEntity();
         LocalDateTime newStart = event.getNewStart();
        if(!permissions() || newStart.isBefore(LocalDateTime.now())){
            lecturesDc.replaceItem(lectureEvent);
            return ;
        }
          Lecture lecture = lectureService.rescheduleLecture(lectureEvent,newStart);
          lecturesDc.replaceItem(lecture);

    }


}