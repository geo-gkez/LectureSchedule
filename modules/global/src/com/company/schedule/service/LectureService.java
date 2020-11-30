package com.company.schedule.service;

import com.company.schedule.entity.Lecture;

import java.time.LocalDateTime;

public interface LectureService {
    String NAME = "schedule_LectureService";

    Lecture rescheduleLecture(Lecture lecture, LocalDateTime newStartDate);
}