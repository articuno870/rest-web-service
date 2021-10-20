insert into course(id, name) 
values(10001,'JPA in 50 Steps');
insert into course(id, name) 
values(10002,'Spring in 50 Steps');
insert into course(id, name) 
values(10003,'Spring Boot in 100 Steps');


insert into passport(id,number)
values(40001,'E123456');
insert into passport(id,number)
values(40002,'N123457');
insert into passport(id,number)
values(40003,'L123890');

insert into student(id,name,passport_id)
values(20001,'Ranga',40001);
insert into student(id,name,passport_id)
values(20002,'Adam',40002);
insert into student(id,name,passport_id)
values(20003,'Jane',40003);

insert into review(id,description,course_id)
values(50001,'Great Course',10001);
insert into review(id,description,course_id)
values(50002,'Wonderful Course',10001);
insert into review(id,description,course_id)
values(50003,'Awesome Course',10003);
