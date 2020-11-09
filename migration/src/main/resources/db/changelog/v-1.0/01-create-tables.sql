create table author (
       id bigint not null auto_increment,
        created datetime,
        status varchar(255),
        updated datetime,
        user_id bigint,
        primary key (id)
    ) engine=InnoDB
GO
    
    create table course (
       id bigint not null,
        created_date datetime,
        last_updated_date datetime,
        name varchar(100) not null,
        author_id bigint,
        primary key (id)
    ) engine=InnoDB
GO
    
    create table hibernate_sequence (
       next_val bigint
    ) engine=InnoDB
GO
    
    insert into hibernate_sequence values ( 1 )
GO
    
    create table lesson (
       id bigint not null auto_increment,
        created datetime,
        status varchar(255),
        updated datetime,
        name varchar(100) not null,
        section_id bigint,
        primary key (id)
    ) engine=InnoDB
GO
    
    create table roles (
       id bigint not null auto_increment,
        created datetime,
        status varchar(255),
        updated datetime,
        name varchar(255),
        primary key (id)
    ) engine=InnoDB
GO
    
    create table section (
       id bigint not null auto_increment,
        created datetime,
        status varchar(255),
        updated datetime,
        name varchar(100) not null,
        course_id bigint,
        primary key (id)
    ) engine=InnoDB
GO
    
    create table student (
       id bigint not null auto_increment,
        created datetime,
        status varchar(255),
        updated datetime,
        user_id bigint,
        primary key (id)
    ) engine=InnoDB
GO
    
    create table student_courses (
       students_id bigint not null,
        courses_id bigint not null
    ) engine=InnoDB
GO
    
    create table user_roles (
       user_id bigint not null,
        role_id bigint not null
    ) engine=InnoDB
GO
    
    create table users (
       id bigint not null auto_increment,
        created datetime,
        status varchar(255),
        updated datetime,
        email varchar(255),
        first_name varchar(255),
        last_name varchar(255),
        password varchar(255),
        username varchar(255),
        primary key (id)
    ) engine=InnoDB
GO
    
    alter table author 
       add constraint FKqhtybqur3lfhup9k7vnyo2g82 
       foreign key (user_id) 
       references users (id)
GO
    
    alter table course 
       add constraint FK5t0ynyp27x7h1rny57tvgjb3o 
       foreign key (author_id) 
       references author (id)
GO 
    
    alter table lesson 
       add constraint FKl7uog042qrnneaje24n3vc10x 
       foreign key (section_id) 
       references section (id)
GO
    
    alter table section 
       add constraint FKoy8uc0ftpivwopwf5ptwdtar0 
       foreign key (course_id) 
       references course (id)
GO
    
    alter table student 
       add constraint FKk0thg920a3xk3v59yjbsatw1l 
       foreign key (user_id) 
       references users (id)
GO
    
    alter table student_courses 
       add constraint FKlwviiijdg10oc2ui4yl7adh1o 
       foreign key (courses_id) 
       references course (id)
GO
    
    alter table student_courses 
       add constraint FKrgo64lg01pxfwq2x9753jgywc 
       foreign key (students_id) 
       references student (id)
GO
    
    alter table user_roles 
       add constraint FKh8ciramu9cc9q3qcqiv4ue8a6 
       foreign key (role_id) 
       references roles (id)
GO
    
    alter table user_roles 
       add constraint FKhfh9dx7w3ubf1co1vdev94g3f 
       foreign key (user_id) 
       references users (id)