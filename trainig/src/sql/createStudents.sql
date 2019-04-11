create table students(
student_no integer primary key,
student_name varchar(60) not null,
class_code varchar(10) references classes (class_code) not null,
created_at timestamp not null,
update_at timestamp not null
);