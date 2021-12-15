# spring-security

CREATE TABLE userdata (
id INTEGER NOT NULL PRIMARY KEY, 
name VARCHAR(200) DEFAULT NULL, 
password VARCHAR(100) DEFAULT NULL, 
role VARCHAR(100) DEFAULT NULL,
geo VARCHAR(100) DEFAULT NULL
);

insert into userdata (id, name,password, role, geo)
values (1, 'a', '1', 'admin', 'US');
insert into UserData (id, name,password, role, geo)
values (2, 'b', '2', 'user', 'UK');

insert into userdata (id, name,password, role, geo)
values (3, 'c', '3', 'admin', 'UK');

insert into userdata (id, name,password, role, geo)
values (4, 'd', '4', 'user', 'US');