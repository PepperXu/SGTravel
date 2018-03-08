create database if not exist SGTravel;

#show database
use SGTravel

drop table if exists Plan;

create table Plan 
  ( 
	planID INT,           # 000——100
	planTitle varchar(50),
	duration INT, 
	remaining_seat INT, 
	country varchar(50),
	plan_detail text,
	img varchar(50),  #?
   );

drop table if exists StartDate;

create table Plan_StartDate
	(
		planID INT,
		start_date DATE, 
	)


drop table if exists Country;

create table Plan_Country
	(
		planID INT,
		country varchar(50) 
		)

create table Country
	(
		country varchar(50),
		continent varchar(50)    #Asia, Africa, North America, South America, Antarctica, Europe, Australia
	);

drop table if exists City;

create table City
	(
		city varchar(50),
		contry varchar(50)
	)

drop table if exists Filght;

create table Flight
	(
		city varchar(50)
	)


drop table if exists User;

create table User
	(
		username varchar(50),
		passwords varchar(50)
	)

drop table if UserSearch;

create table UserSearch
	(
		country varchar(50)
		city varchar(50)
		duration INT,
		start_date DATE,
	    end_date DATE,
		)

drop table if exists Comment;

create table Comment
	(
		planID INT,
		username varchar(50),
		comment_detail varchar(70),
		mark INT
		)

#Plan
#planID INT, planTitle varchar(50), duration INT,  remaining_seat INT, country varchar(50), plan_detail varchar(200)

insert into Plan values(001,'Beijing 2 Days Trip' 2, 100, 'China','xxxxxxxx' )
insert into Plan values(002,'Shanghai 2 Days Trip' 2, 100, 'China','xxxxxxxx' )
insert into Plan values(003, 'Singapore 3 Days Trip',3, 100, 'Singapore','xxxxxxxx' )  
insert into Plan values(004, 'Janpan 5 Days',5, 100, 'Japan','xxxxxxxx' )  
insert into Plan values(005, 'Thailand 4 Days Trip',4, 100, 'Thailand','xxxxxxxx' ) 


#Country
#country varchar(50), continent varchar(50)
insert into Country values ('China','Asia');
insert into Country values ('Singapore','Asia');
insert into Country values ('Japan','Asia');
insert into Country values ('Thailand','Asia');
insert into Country values ('Italy','Europe');
insert into Country values ('Norway','Europe');
insert into Country values ('France','Europe');
insert into Country values ('United Kingdom','Europe');
insert into Country values ('Egypt','Africa');
insert into Country values ('Morocco','Africa');
insert into Country values ('Peru','South America');
insert into Country values ('united states','North America');
insert into Country values ('Australia ','Australia');	


# City 
#city varchar(50), contry varchar(50)
insert into City values ('Beijing','China') ;
insert into City values ('Shanghai','China') ;
insert into City values ('Chongqing','China') ;
insert into City values ('Singapore','Sinagpore') ;
insert into City values ('Singapore','Japan') ;
insert into City values ('Singapore','Sinagpore') ;








