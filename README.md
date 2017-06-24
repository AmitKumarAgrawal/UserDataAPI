# UserDataAPI
This application makes use of REST full services in conjunction with EJB.

Application configuration and other details can be found at below link.

https://github.com/AmitKumarAgrawal/UserDataAPI/blob/master/important_project_files


---------------------------------
--Application Usecase :-
---------------------------------


You will create four tables in the MySQL database. You must configure the JBoss AS to provide a MySQL datasource.
Software Required: To complete this exercise the following software is required.

 JBoss Developer Studio 10+ or Eclipse IDE
 JBoss Wildfly 10 Application Server or EAP 7 (community version)
 MySQL 5.7 (community version)

Task: The activities below describe the task to be performed.

 Using MySQL Implement four tables. The SQL script is provided below.
create table user (
user_id bigint auto_increment,
email varchar(40),
username varchar(12),
password varchar(20)
) ;

primary key(user_id);
create table userrole (
role_id bigint auto_increment,
user_id bigint not null,
rolename varchar(20)
);

primary key (role_id);
foreign key (user_id) references user (user_id);
create table userhobby (
id bigint auto_increment,
user_id bigint not null,
hobby varchar(20) not null,
createdBy varchar(40) not null,
createdOn DateTime not null
);

primary key (id);
foreign key (user_id) references user (user_id);
create table userphone (
id bigint auto_increment,
user_id bigint not null,
type varchar(10) not null, #enum (OFFICE, HOME, MOBILE)
phoneNumber varchar(10) not null,
createdBy varchar(40) not null,
createdOn DateTime not null
);

primary key (id);
foreign key (user_id) references user (user_id);

 Create sample data for the four tables and insert the data into the database.
 Configure the JBoss application server to create a MySQL datasource.
 Develop EJBs to provide CRUD (create, retrieve, update and delete) functions for the four tables.
 Using the EJBs developed above develop a simple Rest API that includes the following methods signatures:

o public void addUser(User user);
o public void deletePhone(Long userId, String type,);
o public void updatePhone(Long userId, String type, String phone);
o public List&lt;UserHobby&gt; getHobbies(Long userId);
o public List&lt;User&gt; getUsers();

 Deploy the Rest API to the application server.
