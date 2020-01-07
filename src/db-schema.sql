CREATE TABLE `students` (
   `id` int(20) NOT NULL AUTO_INCREMENT,
   `username` varchar(45) NOT NULL,
   `password` text,
   `name` varchar(45) DEFAULT NULL,
   `dept` enum('Dietics','Informatics','Geography','Economics') NOT NULL,
   `activated` enum('active','inactive') DEFAULT 'inactive',
   `email` varchar(45) DEFAULT NULL,
   `points` int(4) DEFAULT '0',
   PRIMARY KEY (`id`),
   UNIQUE KEY `username` (`username`),
   UNIQUE KEY `email` (`email`)
 ) ENGINE=InnoDB AUTO_INCREMENT=2122623272 DEFAULT CHARSET=utf8;
 
CREATE TABLE `officers` (
   `id` int(20) NOT NULL AUTO_INCREMENT,
   `username` varchar(50) NOT NULL,
   `password` varchar(45) DEFAULT NULL,
   `department` enum('Informatics','Geography','Dietics','Economics') DEFAULT NULL,
   `role` enum('Admin','Supervisor','Officer') DEFAULT 'Officer',
   PRIMARY KEY (`id`),
   UNIQUE KEY `username` (`username`)
 ) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
 
CREATE TABLE `applications` (
   `id` int(20) NOT NULL AUTO_INCREMENT,
   `dept` enum('Dietics','Informatics','Geography','Economics') NOT NULL,
   `percentage` int(20) DEFAULT '0',
   `active` enum('active','inactive') DEFAULT 'inactive',
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
 
CREATE TABLE `applicationdetails` (
   `id` int(20) NOT NULL AUTO_INCREMENT,
   `email` varchar(30) NOT NULL,
   `username` varchar(45) NOT NULL,
   `dept` enum('Dietics','Informatics','Geography','Economics') NOT NULL,
   `fullName` varchar(45) NOT NULL,
   `city` varchar(45) NOT NULL,
   `income` int(11) NOT NULL,
   `familyIncome` int(11) NOT NULL,
   `parent1_employmentStatus` enum('emp','unemp') NOT NULL,
   `parent2_employmentStatus` enum('emp','unemp') NOT NULL,
   `siblingsStudents` int(2) NOT NULL,
   `active` enum('active','inactive') DEFAULT 'inactive',
   PRIMARY KEY (`id`),
   UNIQUE KEY `email` (`email`)
 ) ENGINE=InnoDB AUTO_INCREMENT=212125 DEFAULT CHARSET=utf8;