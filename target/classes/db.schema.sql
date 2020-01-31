CREATE TABLE `user` (
   `username` varchar(50) NOT NULL,
   `password` varchar(100) NOT NULL,
   `enabled` tinyint(4) NOT NULL,
   PRIMARY KEY (`username`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
 
CREATE TABLE `department` (
   `department_name` enum('Informatics','Geography','Dietics','Economics') NOT NULL,
   `percentage` int(11) DEFAULT '0',
   `active` enum('active','inactive') DEFAULT 'inactive',
   PRIMARY KEY (`department_name`),
   UNIQUE KEY `department_name` (`department_name`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
 
CREATE TABLE `authorities` (
   `username` varchar(50) NOT NULL,
   `authority` varchar(50) NOT NULL,
   UNIQUE KEY `ix_auth_username` (`username`,`authority`),
   CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
 
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
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
 
CREATE TABLE `application` (
   `username` varchar(50) NOT NULL,
   `department_name` enum('Informatics','Geography','Dietics','Economics') DEFAULT NULL,
   `city` varchar(50) NOT NULL,
   `personalIncome` int(11) NOT NULL,
   `familyIncome` int(11) NOT NULL,
   `parent1_employmentStatus` enum('emp','unemp') NOT NULL,
   `parent2_employmentStatus` enum('emp','unemp') NOT NULL,
   `siblingsStudents` int(11) NOT NULL,
   `active` enum('active','inactive'),
   PRIMARY KEY (`username`),
   UNIQUE KEY `department_name` (`department_name`),
   CONSTRAINT `fk_application_department` FOREIGN KEY (`department_name`) REFERENCES `department` (`department_name`),
   CONSTRAINT `fk_application_user` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


insert into user(username, password, enabled) values ('root', '{bcryptedPassword}', 1);
 
insert into department(department_name) values('Informatics');
insert into department(department_name) values('Geography');
insert into department(department_name) values('Dietics');
insert into department(department_name) values('Economy');