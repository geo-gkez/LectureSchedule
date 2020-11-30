create table SCHEDULE_LECTURE (
    ID varchar(32),
    VERSION integer not null,
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    DELETE_TS datetime(3),
    DELETED_BY varchar(50),
    --
    TOPIC varchar(255) not null,
    START_DATE datetime(3) not null,
    DURATION integer not null,
    TEACHER_ID varchar(32) not null,
    DESCRIPTION longtext,
    --
    primary key (ID)
);