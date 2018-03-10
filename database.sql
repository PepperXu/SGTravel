create database if not exists SGTravel;

use SGTravel;

drop table if exists Plan;

create table Plan
  (
	planID int,
	planTitle varchar(200),
	price int,
	duration int,
	remaining_seat int,
	country varchar(50)
   );

drop table if exists Plan_StartDate;

drop table if exists Plan_Date;

create table Plan_Date
	(
		planID int,
		startDate date,
		endDate date
	);

#drop table if exists Country;
#create table Country
#	(
#		country varchar(50),
#		continent varchar(50)    #Asia, Africa, North America, South America, Antarctica, Europe, Australia
#	);

drop table if exists City;

create table City
	(
		city varchar(50),
		contry varchar(50),
		cityID int
	);

drop table if exists Plan_City;

create table Plan_City
	(
		planID int,
		cityID int
	);


drop table if exists Flight;

create table Flight
	(
		filghtID int,
		airplane varchar(50),
		city varchar(50)
	);


drop table if exists User;

create table User
	(
		userID int,
		username varchar(50),
		passwords varchar(50)
	);

drop table if exists UserSearch;

create table UserSearch
	(
		country varchar(50),
		city varchar(50),
		duration int,
		start_date DATE,
	    end_date DATE
	);

drop table if exists Comment;

create table Comment
	(
		userID int,
		planID int,
		username varchar(50),
		comment_detail varchar(70),
		mark int
	);

drop table if exists Cart;

create table Cart
      (
        planID int,
        qty  int,
        img varchar(200)
      );
#Plan
#planID INT, planTitle varchar(50), duration INT,  remaining_seat INT, country varchar(50), plan_detail varchar(200)

insert into Plan values(001, '4 Days China Tour (Beijing)',600, 3, 100, 'China');
insert into Plan values(002, '3 Days China Tour (Shanghai) ',500, 3, 100, 'China');
insert into Plan values(003, '2 Days China Tour (Chongqing)',400, 2, 100, 'China');

insert into Plan values(004, '3 Days Singapore Tour',600, 3, 100, 'Singapore');

insert into Plan values(005, '5 Days Thailand Tour (Bangkok + Chiang Mai + Pattaya)',600, 5, 30, 'Thailand') ;

insert into Plan values(006, '4 Days Italy Tour (Rome + Milan + Florence)',600, 4, 100, 'Italy' ) ;
insert into Plan values(007, '9 Days Italy Tour (Rome + Milan + Florence + Venice + Palermo)',1200, 9, 30, 'Italy' ) ;

insert into Plan values(008, '9 Days France Tour (Bordeaux + Paris + Lille + Nice + Lyon)',1300,9, 30, 'France' ) ;
insert into Plan values(009, '2 Days France Tour (Paris)',300, 2, 100, 'France' ) ;
insert into Plan values(010, '1 Days France Tour (Bordeaux )',150,1, 100, 'France') ;

insert into Plan values(011, '1 Days Egypt Tour (Cairo)',120,1, 70, 'Egypt') ;
insert into Plan values(012, '1 Days Egypt Tour (Alexandria)',150,1, 70, 'Egypt') ;

insert into Plan values(013, '4 Days France Tour (Rio de Janeiro + Sao Paulo)',500,4, 70, 'Brazil') ;

insert into Plan values(014, '15 Days United States Tour (New York + Chicago + Philadelphia + Boston + Washington) )',2000,15, 50, 'United States') ;
insert into Plan values(015, '15 Days United States Tour (Los Angeles + San Francisco + Las Vegas + Yellowstone Park) )',2000,15, 50, 'United States') ;


insert into Plan values(016, '2 Days Australia Tour (Sydney) )',350,15, 50, 'Australia') ;
insert into Plan values(017, '2 Days Australia Tour (Melbourne) )',350,15, 50, 'Australia') ;




#Country
#country varchar(50), continent varchar(50)
#insert into Country values ('China','Asia');
#insert into Country values ('Singapore','Asia');
#insert into Country values ('Thailand','Asia');
#
#insert into Country values ('Italy','Europe');
#insert into Country values ('France','Europe');
#
#insert into Country values ('Egypt','Africa');
#
#insert into Country values ('Brazil','South America');
#
#insert into Country values ('United states','North America');
#
#insert into Country values ('Australia ','Australia');


#City
#city varchar(50), contry varchar(50),cityID INT
insert into City values ('Beijing','China',1) ;
insert into City values ('Shanghai','China',2) ;
insert into City values ('Chongqing','China',3) ;

insert into City values ('Singapore','Sinagpore',4) ;

insert into City values ('Bangkok','Thailand',5) ;
insert into City values ('Chiang Mai','Thailand',6) ;
insert into City values ('Pattaya','Thailand',7) ;

insert into City values ('Rome','Italy',8) ;
insert into City values ('Milan','Italy',9) ;
insert into City values ('Venice','Italy',10) ;
insert into City values ('Florence','Italy',11) ;
insert into City values ('Palermo','Italy',12) ;

insert into City values ('Bordeaux','France',13) ;
insert into City values ('Paris','France',14) ;
insert into City values ('Lille','France',15) ;
insert into City values ('Lyon','France',16) ;
insert into City values ('Nice','France',17) ;

insert into City values ('Cairo','Egypt',18) ;
insert into City values ('Alexandria','Egypt',19) ;

insert into City values ('Rio de Janeiro','Brazil',20) ;
insert into City values ('Sao Paulo','Brazil',21) ;


insert into City values ('New York','United States',22);
insert into City values ('Chicago','United States',23);
insert into City values ('Philadelphia','United States',24);
insert into City values ('Boston','United States',25);
insert into City values ('Washington','United States',26);
insert into City values ('Los Angeles','United States',27);
insert into City values ('San Francisco','United States',28);
insert into City values ('Las Vegas','United States',29);
insert into City values ('Yellowstone Park','United States',30);


insert into City values ('Sydney','Australia',31);
insert into City values ('Melbourne','Australia',32);




#Plan_City
#planID INT, cityID INT
#001, 4 Days China Tour (Beijing)
#002, 3 Days China Tour (Shanghai)
#003, 2 Days China Tour (Chongqing)
#004, 3 Days Singapore Tour
#005, 5 Days Thailand Tour (Bangkok + Chiang Mai + Pattaya)
#006, 4 Days Italy Tour (Rome + Milan + Florence)
#007, 9 Days Italy Tour (Rome + Milan + Florence + Venice + Palermo)
#008, 9 Days France Tour (Bordeaux + Paris + Lille + Nice + Lyon)
#009, 2 Days France Tour (Paris)
#010, 1 Days France Tour (Bordeaux)
#011, 1 Days Egypt Tour (Cairo)
#012, 1 Days Egypt Tour (Alexandria)
#013, 4 Days France Tour (Rio de Janeiro + Sao Paulo)
#014, 15 Days United States Tour (New York + Chicago + Philadelphia + Boston + Washington)
#015, 15 Days United States Tour (Los Angeles + San Francisco + Las Vegas + Yellowstone Park)
#016, 2 Days Australia Tour (Sydney)
#017, 2 Days Australia Tour (Melbourne)

insert into Plan_City values (001, 1);

insert into Plan_City values (002, 2);

insert into Plan_City values (003, 3);

insert into Plan_City values (004, 4);

insert into Plan_City values (005, 5);
insert into Plan_City values (005, 6);
insert into Plan_City values (005, 7);

insert into Plan_City values (006, 8);
insert into Plan_City values (006, 9);
insert into Plan_City values (006, 11);

insert into Plan_City values (007, 8);
insert into Plan_City values (007, 9);
insert into Plan_City values (007, 10);
insert into Plan_City values (007, 11);
insert into Plan_City values (007, 12);

insert into Plan_City values (008, 13);
insert into Plan_City values (008, 14);
insert into Plan_City values (008, 15);
insert into Plan_City values (008, 16);
insert into Plan_City values (008, 17);

insert into Plan_City values (009, 14);

insert into Plan_City values (010, 13);

insert into Plan_City values (011, 18);

insert into Plan_City values (012, 19);

insert into Plan_City values (013, 20);
insert into Plan_City values (013, 21);

insert into Plan_City values (014, 22);
insert into Plan_City values (014, 23);
insert into Plan_City values (014, 24);
insert into Plan_City values (014, 25);
insert into Plan_City values (014, 26);

insert into Plan_City values (015, 27);
insert into Plan_City values (015, 28);
insert into Plan_City values (015, 29);
insert into Plan_City values (015, 30);

insert into Plan_City values (016, 31);
insert into Plan_City values (017, 32);


#Flight
#airplane varchar(50), city varchar(50) ,filghtID int


#Plan_StartDate
#planID int, start_date date
insert into Plan_Date values (001, '2018-03-01', '2018-03-04');
insert into Plan_Date values (001, '2018-04-01', '2018-04-04');
insert into Plan_Date values (001, '2018-05-01', '2018-05-04');
insert into Plan_Date values (001, '2018-06-01', '2018-06-04');
insert into Plan_Date values (001, '2018-07-01', '2018-07-04');
insert into Plan_Date values (001, '2018-08-01', '2018-08-04');
insert into Plan_Date values (001, '2018-09-01', '2018-09-04');
insert into Plan_Date values (001, '2018-10-01', '2018-10-04');
insert into Plan_Date values (001, '2018-11-01', '2018-11-04');
insert into Plan_Date values (001, '2018-12-01', '2018-12-04');
insert into Plan_Date values (002, '2018-08-01', '2018-08-04');
insert into Plan_Date values (002, '2018-09-01', '2018-09-04');
insert into Plan_Date values (002, '2018-10-01', '2018-10-04');
insert into Plan_Date values (002, '2018-11-01', '2018-11-04');
insert into Plan_Date values (002, '2018-12-01', '2018-12-04');












