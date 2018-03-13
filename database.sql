create database if not exists SGTravel;

use SGTravel;

drop table if exists Plan;

create table Plan
  (
	planID int,
	startCity varchar(50),
	planTitle varchar(200),
	duration int,
	country varchar(50),
	img_path char(200)
   );

drop table if exists Plan_StartDate;

drop table if exists Plan_Date;

create table Plan_Date
	(
		itemID int,
		planID int,
		startDate date,
		endDate date,
		remaining_seat int,
		price int
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
		country varchar(50),
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
		filghtNumber int,
		airplane varchar(50),
		departureCity varchar(50),
		departureTime DATETIME,
		arrivalCity varchar(50),
		arrivalTime DATETIME
	);

drop table if exists Plan_Flight;

create table Plan_Flight
	(
		planID int,
		flightNumber int
	);

drop table if exists User;

drop table if exists Users;

create table Users
	(
		username char(16) not null,
		password char(41) not null,
		primary key (username)
	);

drop table if exists Plan_Users;

create table Plan_Users
	(
		purchaseID char(128),
		itemID int,
		planID int,
		username char(16),
		price int,
		amount int
	);

drop table if exists Comment;

create table Comment
	(
		userID int,
		planID int,
		username varchar(50),
		content varchar(70),
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

insert into Plan values(001, 'Singapore','4 Days China Tour (Beijing)', 4, 'China', 'images/travel/beijing.jpg');
insert into Plan values(002, 'Singapore','3 Days China Tour (Shanghai) ', 3, 'China', 'images/travel/shanghai.jpg');
insert into Plan values(003, 'New York','2 Days China Tour (Chongqing)', 2, 'China', 'images/travel/chongqing.jpg');

insert into Plan values(004, 'New York','3 Days Singapore Tour', 3, 'Singapore', 'images/travel/singapore.jpg');

insert into Plan values(005, 'London','5 Days Thailand Tour (Bangkok + Chiang Mai + Pattaya)', 5, 'Thailand', 'images/travel/thai.jpg') ;

insert into Plan values(006, 'London','4 Days Italy Tour (Rome + Milan + Florence)', 4, 'Italy', 'images/travel/italy.jpg') ;
insert into Plan values(007, 'Paris','9 Days Italy Tour (Rome + Milan + Florence + Venice + Palermo)', 9, 'Italy' , 'images/travel/italy_2.jpg') ;

insert into Plan values(008, 'Singapore','9 Days France Tour (Bordeaux + Paris + Lille + Nice + Lyon)', 9, 'France' , 'images/travel/france.jpg') ;
insert into Plan values(009, 'Sydney','2 Days France Tour (Paris)', 2, 'France' , 'images/travel/paris.jpg') ;
insert into Plan values(010, 'Sydney','1 Days France Tour (Bordeaux )', 1, 'France', 'images/travel/bordeaux.jpg') ;

insert into Plan values(011, 'Tokyo','1 Days Egypt Tour (Cairo)', 1, 'Egypt', 'images/travel/cairo.jpg') ;
insert into Plan values(012, 'Tokyo','1 Days Egypt Tour (Alexandria)', 1, 'Egypt', 'images/travel/alexandria.jpg') ;

insert into Plan values(013, 'Beijing','4 Days France Tour (Rio de Janeiro + Sao Paulo)', 4, 'Brazil', 'images/travel/sao_paulo.jpg') ;

insert into Plan values(014, 'Beijing','15 Days United States Tour (New York + Chicago + Philadelphia + Boston + Washington)', 15, 'United States', 'images/travel/new_york.jpg') ;
insert into Plan values(015, 'Shanghai','15 Days United States Tour (Los Angeles + San Francisco + Las Vegas + Yellowstone Park)', 15, 'United States', 'images/travel/los_angeles.jpg') ;
insert into Plan values(016, 'Shanghai','2 Days Australia Tour (Sydney)', 2, 'Australia', 'images/travel/sydney.jpg') ;
insert into Plan values(017, 'Paris','2 Days Australia Tour (Melbourne)', 2, 'Australia', 'images/travel/melbourne.jpg') ;




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


#create table Plan_Date
#(itemID int, planID int, startDate date, endDate date, remaining_seat int, price int);

insert into Plan_Date values (001001, 001, '2018-03-01', '2018-03-04', 0, 800);
insert into Plan_Date values (001002, 001, '2018-04-01', '2018-04-04', 5, 800);
insert into Plan_Date values (001003, 001, '2018-05-01', '2018-05-04', 10, 780);
insert into Plan_Date values (001004, 001, '2018-06-01', '2018-06-04', 45, 700);
insert into Plan_Date values (001005, 001, '2018-07-01', '2018-07-04', 50, 800);
insert into Plan_Date values (001006, 001, '2018-08-01', '2018-08-04', 50, 800);
insert into Plan_Date values (001007, 001, '2018-09-01', '2018-09-04', 50, 700);
insert into Plan_Date values (001008, 001, '2018-10-01', '2018-10-04', 50, 700);
insert into Plan_Date values (001009, 001, '2018-11-01', '2018-11-04', 50, 700);
insert into Plan_Date values (001010, 001, '2018-12-01', '2018-12-04', 50, 700);

insert into Plan_Date values (002001, 002, '2018-04-01', '2018-04-03', 20, 700);
insert into Plan_Date values (002002, 002, '2018-05-15', '2018-05-18', 30, 600);
insert into Plan_Date values (002003, 002, '2018-06-21', '2018-06-23', 20, 600);
insert into Plan_Date values (002004, 002, '2018-07-21', '2018-07-23', 40, 700);
insert into Plan_Date values (002005, 002, '2018-08-21', '2018-08-23', 20, 600);
insert into Plan_Date values (002006, 002, '2018-09-21', '2018-09-23', 10, 600);

insert into Plan_Date values (003001, 003, '2018-08-01', '2018-08-02', 15, 400);
insert into Plan_Date values (003002, 003, '2018-09-01', '2018-09-02', 20, 400);
insert into Plan_Date values (003003, 003, '2018-10-01', '2018-10-02', 30, 400);
insert into Plan_Date values (003004, 003, '2018-11-01', '2018-11-02', 40, 400);
insert into Plan_Date values (003005, 003, '2018-12-01', '2018-12-02', 20, 500);

insert into Plan_Date values (004003, 004, '2018-05-01', '2018-05-03', 10, 780);
insert into Plan_Date values (004004, 004, '2018-06-01', '2018-06-03', 45, 700);
insert into Plan_Date values (004005, 004, '2018-07-01', '2018-07-03', 50, 800);
insert into Plan_Date values (004006, 004, '2018-08-01', '2018-08-03', 5, 800);
insert into Plan_Date values (004007, 004, '2018-09-01', '2018-09-03', 0, 700);
insert into Plan_Date values (004008, 004, '2018-10-01', '2018-10-03', 50, 700);

insert into Plan_Date values (005001, 005, '2018-05-01', '2018-05-05', 10, 1200);
insert into Plan_Date values (005002, 005, '2018-06-01', '2018-06-05', 45, 1200);
insert into Plan_Date values (005003, 005, '2018-07-01', '2018-07-05', 30, 1100);
insert into Plan_Date values (005004, 005, '2018-08-01', '2018-08-05', 5, 1200);
insert into Plan_Date values (005005, 005, '2018-09-01', '2018-09-05', 10, 1200);
insert into Plan_Date values (005006, 005, '2018-10-01', '2018-10-05', 50, 1100);

insert into Plan_Date values (006001, 006, '2018-03-01', '2018-03-04', 6, 800);
insert into Plan_Date values (006002, 006, '2018-06-01', '2018-06-04', 20, 900);
insert into Plan_Date values (006003, 006, '2018-07-01', '2018-07-04', 30, 1000;
insert into Plan_Date values (006004, 006, '2018-08-01', '2018-08-04', 5, 1000);
insert into Plan_Date values (006005, 006, '2018-09-01', '2018-09-04', 10, 800);
insert into Plan_Date values (006006, 006, '2018-10-01', '2018-10-04', 40, 800);

insert into Plan_Date values (007001, 007, '2018-04-01', '2018-04-09', 7, 1200);
insert into Plan_Date values (007002, 007, '2018-06-01', '2018-06-09', 20, 1200);
insert into Plan_Date values (007003, 007, '2018-07-01', '2018-07-09', 10, 1400);
insert into Plan_Date values (007004, 007, '2018-08-01', '2018-08-09', 9, 1100);
insert into Plan_Date values (007005, 007, '2018-09-01', '2018-09-09', 10, 1200);
insert into Plan_Date values (007006, 007, '2018-10-01', '2018-10-09', 40, 1100);

insert into Plan_Date values (008001, 008, '2018-06-01', '2018-06-09', 20, 1200);
insert into Plan_Date values (008002, 008, '2018-07-01', '2018-07-09', 10, 1400);
insert into Plan_Date values (008003, 008, '2018-08-01', '2018-08-09', 9, 1100);
insert into Plan_Date values (008004, 008, '2018-09-01', '2018-09-09', 10, 1200);
insert into Plan_Date values (008005, 008, '2018-10-01', '2018-10-09', 40, 1100);

insert into Plan_Date values (009001, 009, '2018-07-01', '2018-07-02', 60, 300);
insert into Plan_Date values (009002, 009, '2018-08-01', '2018-08-02', 60, 300);

insert into Plan_Date values (010001, 010, '2018-07-01', '2018-07-01', 60, 300);
insert into Plan_Date values (010002, 010, '2018-08-01', '2018-08-01', 60, 300);

insert into Plan_Date values (011001, 011, '2018-06-01', '2018-06-01', 10, 400);
insert into Plan_Date values (011002, 011, '2018-07-01', '2018-07-01', 0, 400);
insert into Plan_Date values (011003, 011, '2018-08-01', '2018-08-01', 10, 400);

insert into Plan_Date values (012001, 012, '2018-11-01', '2018-11-01', 10, 450);
insert into Plan_Date values (012002, 012, '2018-12-01', '2018-12-01', 0, 450);

insert into Plan_Date values (013001, 013, '2018-09-01', '2018-09-04', 10, 900);
insert into Plan_Date values (013002, 013, '2018-10-01', '2018-10-04', 40, 900);
insert into Plan_Date values (013003, 013, '2018-11-01', '2018-11-04', 30, 1000);
insert into Plan_Date values (013004, 013, '2018-12-01', '2018-12-04', 7, 1000);

insert into Plan_Date values (014001, 014, '2018-08-01', '2018-08-15', 15, 2000);
insert into Plan_Date values (014002, 014, '2018-09-01', '2018-09-15', 20, 2000);
insert into Plan_Date values (014003, 014, '2018-10-01', '2018-10-15', 20, 1800);
insert into Plan_Date values (014004, 014, '2018-11-01', '2018-11-15', 20, 1800);
insert into Plan_Date values (014005, 014, '2018-12-01', '2018-12-15', 20, 1800);

insert into Plan_Date values (015001, 015, '2018-08-01', '2018-08-15', 0, 2000);
insert into Plan_Date values (015002, 015, '2018-09-01', '2018-09-15', 5, 2000);
insert into Plan_Date values (015003, 015, '2018-10-01', '2018-10-15', 10, 1800);

insert into Plan_Date values (016001, 016, '2018-08-01', '2018-08-02', 15, 700);
insert into Plan_Date values (016002, 016, '2018-09-01', '2018-09-02', 5, 700);
insert into Plan_Date values (016003, 016, '2018-10-01', '2018-10-02', 10, 700);

insert into Plan_Date values (017001, 017, '2018-06-01', '2018-06-02', 0, 600);
insert into Plan_Date values (017002, 017, '2018-07-01', '2018-07-02', 15, 500);
insert into Plan_Date values (017003, 017, '2018-08-01', '2018-08-02', 10, 500);









