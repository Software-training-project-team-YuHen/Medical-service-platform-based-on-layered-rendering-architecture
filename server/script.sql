create table health_questionnaire
(
    id                  int auto_increment
        primary key,
    time                datetime default CURRENT_TIMESTAMP not null,
    userid              int                                not null,
    height              decimal(5, 2)                      null,
    weight              decimal(5, 2)                      null,
    waist_circumference decimal(5, 2)                      null,
    body_temperature    decimal(5, 2)                      null,
    heart_rate          decimal(5, 2)                      null,
    lung_capacity       int                                null,
    blood_pressure      decimal(5, 2)                      null,
    blood_sugar         decimal(5, 2)                      null,
    creat_by            int                                not null
)
    comment '健康调查表';

create index health_questionnaire_time_index
    on health_questionnaire (time);

create index health_questionnaire_userid_time_index
    on health_questionnaire (userid, time);

create table image
(
    id    int auto_increment,
    image mediumblob null,
    constraint id_UNIQUE
        unique (id)
);

alter table image
    add primary key (id);

create table login_history
(
    user int                                not null,
    time datetime default CURRENT_TIMESTAMP not null,
    constraint login_history_pk
        unique (user, time)
);

create index login_history_time_index
    on login_history (time);

create index login_history_user_index
    on login_history (user);

create table user
(
    id                int auto_increment,
    name              varchar(45) default '用户'                               null,
    sex               enum ('male', 'female')                                null,
    email             varchar(45)                                            null,
    wechat_id         varchar(45)                                            null,
    head_portrait_id  int                                                    null,
    password          varchar(255)                                           null,
    registration_time datetime    default CURRENT_TIMESTAMP                  not null,
    phone             varchar(45)                                            null,
    type              enum ('user', 'medical_institutions', 'administrator') null,
    status            varchar(45)                                            null,
    constraint email_UNIQUE
        unique (email),
    constraint idUser_UNIQUE
        unique (id),
    constraint wechat_id_UNIQUE
        unique (wechat_id)
);

alter table user
    add primary key (id);


