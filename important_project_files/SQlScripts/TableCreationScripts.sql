

-----------------------------------------------------------------------
-- BELOW commands will delete the tables and all its contents
-----------------------------------------------------------------------
-- DROP TABLE IF EXISTS  `project_db`.`userphone`;
-- DROP TABLE IF EXISTS  `project_db`.`userhobby`;
-- DROP TABLE IF EXISTS  `project_db`.`userrole`;
-- DROP TABLE IF EXISTS  `project_db`.`user`;
-- DROP DATABASE project_db;
-----------------------------------------------------------------------

-----------------------------------------------------------------------
-- BELOW SCRIPT WILL CREATE A DATABASE IN MYSQL
-----------------------------------------------------------------------
CREATE DATABASE project_db;
-----------------------------------------------------------------------


-----------------------------------------------------------------------
-- Below script will create table `user` with `user_id` as primary key
-----------------------------------------------------------------------
CREATE TABLE `project_db`.`user` (
  `user_id` BIGINT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(40) NULL,
  `username` VARCHAR(12) NULL,
  `password` VARCHAR(20) NULL,
  PRIMARY KEY (`user_id`));
-----------------------------------------------------------------------


-----------------------------------------------------------------------
-- INSERT SCRIPT FOR USER TABLE
-----------------------------------------------------------------------
INSERT INTO `project_db`.`user` (`email`, `username`, `password`) VALUES ('user1@test.com', 'testUser1', 'testPass1');
INSERT INTO `project_db`.`user` (`email`, `username`, `password`) VALUES ('user2@test.com', 'testUser2', 'testPass2');
INSERT INTO `project_db`.`user` (`email`, `username`, `password`) VALUES ('user3@test.com', 'testUser3', 'testPass3');
INSERT INTO `project_db`.`user` (`email`, `username`, `password`) VALUES ('user4@test.com', 'testUser4', 'testPass4');
-----------------------------------------------------------------------

-----------------------------------------------------------------------
-- Below script will create table `userrole` with `role_id` as primary key
-- and `user_id` as foreign key
-----------------------------------------------------------------------
CREATE TABLE `project_db`.`userrole` (
  `role_id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `rolename` VARCHAR(20) NULL,
  PRIMARY KEY (`role_id`),
  INDEX `user_id_idx` (`user_id` ASC),
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `project_db`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
-----------------------------------------------------------------------


-----------------------------------------------------------------------
-- INSERT SCRIPT FOR userrole TABLE
-----------------------------------------------------------------------
INSERT INTO `project_db`.`userrole` (`user_id`, `rolename`) 	VALUES (1, 'admin');
INSERT INTO `project_db`.`userrole` (`user_id`, `rolename`) 	VALUES (2, 'developer');
INSERT INTO `project_db`.`userrole` (`user_id`, `rolename`) 	VALUES (3, 'testEngineer');
INSERT INTO `project_db`.`userrole` (`user_id`, `rolename`) 	VALUES (4, 'productOwner');    
-----------------------------------------------------------------------	
	
-----------------------------------------------------------------------
-- Below script will create table `userhobby` with `id` as primary key
-- and `user_id` as foreign key
-----------------------------------------------------------------------
CREATE TABLE `project_db`.`userhobby` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `hobby` VARCHAR(20) NOT NULL,
  `createdBy` VARCHAR(40) NOT NULL,
  `createdOn` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id_idx` (`user_id` ASC),
  CONSTRAINT `user_id_2`
    FOREIGN KEY (`user_id`)
    REFERENCES `project_db`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
	
-----------------------------------------------------------------------


-----------------------------------------------------------------------
-- INSERT SCRIPT FOR userhobby TABLE
-----------------------------------------------------------------------
INSERT INTO `project_db`.`userhobby` (`user_id`, `hobby`, `createdBy`, `createdOn`) VALUES (1, 'golfing', 'creator1', '2017-04-15 18:20:01');
INSERT INTO `project_db`.`userhobby` (`user_id`, `hobby`, `createdBy`, `createdOn`) VALUES (1, 'jogging', 'creator1', '2017-04-15 18:20:01');
INSERT INTO `project_db`.`userhobby` (`user_id`, `hobby`, `createdBy`, `createdOn`) VALUES (1, 'baseball', 'creator1', '2017-04-15 18:20:01');
INSERT INTO `project_db`.`userhobby` (`user_id`, `hobby`, `createdBy`, `createdOn`) VALUES (2, 'walking', 'creator2', '2017-04-15 18:20:01');
INSERT INTO `project_db`.`userhobby` (`user_id`, `hobby`, `createdBy`, `createdOn`) VALUES (2, 'running', 'creator2', '2017-04-15 18:20:01');
INSERT INTO `project_db`.`userhobby` (`user_id`, `hobby`, `createdBy`, `createdOn`) VALUES (2, 'soccer', 'creator2', '2017-04-15 18:20:01');
INSERT INTO `project_db`.`userhobby` (`user_id`, `hobby`, `createdBy`, `createdOn`) VALUES (3, 'bungee jumping', 'creator3', '2017-04-15 18:20:01');
INSERT INTO `project_db`.`userhobby` (`user_id`, `hobby`, `createdBy`, `createdOn`) VALUES (3, 'swimming', 'creator3', '2017-04-15 18:20:01');
INSERT INTO `project_db`.`userhobby` (`user_id`, `hobby`, `createdBy`, `createdOn`) VALUES (3, 'chess', 'creator3', '2017-04-15 18:20:01');
INSERT INTO `project_db`.`userhobby` (`user_id`, `hobby`, `createdBy`, `createdOn`) VALUES (4, 'mountaineerring', 'creator4', '2017-04-15 18:20:01');
INSERT INTO `project_db`.`userhobby` (`user_id`, `hobby`, `createdBy`, `createdOn`) VALUES (4, 'scuba diving', 'creator4', '2017-04-15 18:20:01');
INSERT INTO `project_db`.`userhobby` (`user_id`, `hobby`, `createdBy`, `createdOn`) VALUES (4, 'meditation', 'creator4', '2017-04-15 18:20:01');
-----------------------------------------------------------------------


-----------------------------------------------------------------------
-- Below script will create table `userphone` with `id` as primary key
-- and `user_id` as foreign key
-----------------------------------------------------------------------
CREATE TABLE `project_db`.`userphone` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `type` VARCHAR(10) NOT NULL,
  `phoneNumber` VARCHAR(10) not null,
  `createdBy` VARCHAR(40) not null,
  `createdOn` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id_idx` (`user_id` ASC),
  CONSTRAINT `user_id_3`
    FOREIGN KEY (`user_id`)
    REFERENCES `project_db`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
	
	
INSERT INTO `project_db`.`userphone` (`user_id`, `type`, `phoneNumber`, `createdBy`, `createdOn`)
VALUES  (1, 'office', '1111111111', 'creator1', '2017-04-15 18:20:01');

INSERT INTO `project_db`.`userphone` (`user_id`, `type`, `phoneNumber`, `createdBy`, `createdOn`)
VALUES  (1, 'home', '1111111112', 'creator1', '2017-04-15 18:20:01');

INSERT INTO `project_db`.`userphone` (`user_id`, `type`, `phoneNumber`, `createdBy`, `createdOn`)
VALUES  (1, 'personal', '1111111113', 'creator1', '2017-04-15 18:20:01');

INSERT INTO `project_db`.`userphone` (`user_id`, `type`, `phoneNumber`, `createdBy`, `createdOn`)
VALUES  (2, 'office', '2222222222', 'creator2', '2017-04-15 18:20:01');

INSERT INTO `project_db`.`userphone` (`user_id`, `type`, `phoneNumber`, `createdBy`, `createdOn`)
VALUES  (2, 'home', '2222222223', 'creator2', '2017-04-15 18:20:01');

INSERT INTO `project_db`.`userphone` (`user_id`, `type`, `phoneNumber`, `createdBy`, `createdOn`)
VALUES  (2, 'personal', '2222222224', 'creator2', '2017-04-15 18:20:01');

INSERT INTO `project_db`.`userphone` (`user_id`, `type`, `phoneNumber`, `createdBy`, `createdOn`)
VALUES  (3, 'office', '3333333333', 'creator3', '2017-04-15 18:20:01');

INSERT INTO `project_db`.`userphone` (`user_id`, `type`, `phoneNumber`, `createdBy`, `createdOn`)
VALUES  (3, 'home', '3333333334', 'creator3', '2017-04-15 18:20:01');

INSERT INTO `project_db`.`userphone` (`user_id`, `type`, `phoneNumber`, `createdBy`, `createdOn`)
VALUES  (3, 'personal', '3333333335', 'creator3', '2017-04-15 18:20:01');	

INSERT INTO `project_db`.`userphone` (`user_id`, `type`, `phoneNumber`, `createdBy`, `createdOn`)
VALUES  (4, 'office', '4444444444', 'creator4', '2017-04-15 18:20:01');

INSERT INTO `project_db`.`userphone` (`user_id`, `type`, `phoneNumber`, `createdBy`, `createdOn`)
VALUES  (4, 'home', '4444444445', 'creator4', '2017-04-15 18:20:01');

INSERT INTO `project_db`.`userphone` (`user_id`, `type`, `phoneNumber`, `createdBy`, `createdOn`)
VALUES  (4, 'personal', '4444444446', 'creator4', '2017-04-15 18:20:01');	


    
