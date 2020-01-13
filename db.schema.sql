CREATE TABLE `user` (
   `username` varchar(50) NOT NULL,
   `password` varchar(100) NOT NULL,
   `enabled` tinyint(4) NOT NULL,
   PRIMARY KEY (`username`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 
 CREATE TABLE `department` (
   `department_name` enum('Informatics','Geography','Dietics','Economics') NOT NULL,
   `percentage` int(11) DEFAULT '0',
   `active` enum('active','inactive') DEFAULT 'inactive',
   PRIMARY KEY (`department_name`),
   UNIQUE KEY `department_name` (`department_name`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 
 CREATE TABLE `authorities` (
   `username` varchar(50) NOT NULL,
   `authority` varchar(50) NOT NULL,
   UNIQUE KEY `ix_auth_username` (`username`,`authority`),
   CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 
 CREATE TABLE `user_information` (
   `username` varchar(50) NOT NULL,
   `name` varchar(45) DEFAULT NULL,
   `email` varchar(45) DEFAULT NULL,
   `department_name` enum('Informatics','Geography','Dietics','Economics') DEFAULT NULL,
   `points` int(11) DEFAULT '0',
   `activated` enum('active','inactive') DEFAULT 'inactive',
   PRIMARY KEY (`username`),
   KEY `fk_user_information_department` (`department_name`),
   CONSTRAINT `fk_user_information_department` FOREIGN KEY (`department_name`) REFERENCES `department` (`department_name`),
   CONSTRAINT `fk_user_information_user` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 
 CREATE TABLE `application` (
   `username` varchar(50) NOT NULL,
   `department_name` enum('Informatics','Geography','Dietics','Economics') DEFAULT NULL,
   `city` varchar(50) NOT NULL,
   `personalIncome` int(11) NOT NULL,
   `familyIncome` int(11) NOT NULL,
   `parent1_employmentStatus` enum('emp','unemp') NOT NULL,
   `parent2_employmentStatus` enum('emp','unemp') NOT NULL,
   `siblingsStudents` int(11) NOT NULL,
   `active` enum('active','inactive') DEFAULT 'inactive',
   PRIMARY KEY (`username`),
   UNIQUE KEY `department_name` (`department_name`),
   CONSTRAINT `fk_application_department` FOREIGN KEY (`department_name`) REFERENCES `department` (`department_name`),
   CONSTRAINT `fk_application_user` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 
 
insert into user(username, password, enabled)
values ('officer1', '1234', '1');
insert into user(username, password, enabled)
values ('officer2', '1234', '1');
insert into user(username, password, enabled)
values ('officer3', '1234', '1');
insert into user(username, password, enabled)
values ('student1', '1234', '1');

update user set password="$2y$12$pPFU7J.7MTmBjScWVV3./.eAtHduxk4kGEFEKAfjBjV42t7uXTqyy";

insert into department(department_name) values('Informatics');
insert into department(department_name) values('Geography');
insert into department(department_name) values('Dietics');
insert into department(department_name) values('Economy');

insert into authorities(username, authority)
	values('officer1', 'ROLE_ADMIN');
insert into authorities(username, authority)
	values('officer2', 'ROLE_SUPERVISOR');
insert into authorities(username, authority)
	values('officer3', 'ROLE_OFFICER');
insert into authorities(username, authority)
	values('student1', 'ROLE_STUDENT');

insert into user_information(username, name, email)
	values ('officer1', 'root', 'r@r.r');
insert into user_information(username, name, email)
	values ('officer2', 'Anna', 'a@a.a');
insert into user_information(username, name, email, department_name)
	values ('officer3', 'Dimitra', 'd@d.d', 'Informatics');
insert into user_information(username, name, email, department_name)
	values ('student1', 'Vasilis', 'v@v.v', 'Informatics');
	
	
insert into application (username, 
department_name, 
city, 
personalIncome, 
familyIncome, parent1_employmentStatus,
parent2_employmentStatus,
siblingsStudents) values
('student1', 'Informatics', 'Athens', 0, 8000, 'emp', 'unemp', 1);