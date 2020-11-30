package com.company.schedule.service;

import com.company.schedule.entity.Lecture;
import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service(LectureService.NAME)
public class LectureServiceBean implements LectureService {

    @Inject
    private DataManager dataManager;

    @Override
    public Lecture rescheduleLecture(Lecture lecture, LocalDateTime newStartDate) {

        LocalDateTime dayStart = newStartDate.truncatedTo(ChronoUnit.DAYS).withHour(8);
        LocalDateTime dayEnd = newStartDate.truncatedTo(ChronoUnit.DAYS).withHour(19);

        Long lecturesSameTime = dataManager.loadValue("select count(l) " +
                " from schedule_Lecture l " +
                " where (l.startDate between :dayStart and :dayEnd) " +
                " and l.teacher = :teacher " +
                "and l.id <> :lectureId ", Long.class)
                .parameter("dayStart", dayStart)
                .parameter("dayEnd", dayEnd)
                .parameter("teacher", lecture.getTeacher())
                .parameter("lectureId", lecture.getId())
                .one();
        if(lecturesSameTime >=2){
            return lecture;
        }
        lecture.setStartDate(newStartDate);
        return dataManager.commit(lecture);
    }
}