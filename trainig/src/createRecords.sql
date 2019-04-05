create table records(
student_no integer references students(student_no),
subject_code varchar(30) references subjects(subject_code),
record_point integer not null,
created_at timestamp not null,
update_at timestamp not null,
primary key(student_no, subject_code)
);