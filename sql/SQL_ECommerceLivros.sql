CREATE TABLE AUTHOR (
  authorID             NUMBER NOT NULL ,
  name                 VARCHAR2 (100) NOT NULL
);
ALTER TABLE AUTHOR ADD CONSTRAINT AUTHOR_PK PRIMARY KEY ( authorID ) ;
ALTER TABLE AUTHOR ADD (gender varchar2(50) not null,
lastName varchar2(150) not null,
nationality varchar2(100) not null);

CREATE TABLE BOOKS
(
  bookID                NUMBER NOT NULL ,
  name                  VARCHAR2 (100) NOT NULL,
  price                 NUMBER (5,2) NOT NULL ,
  authorID              NUMBER NOT NULL ,
  genreID               NUMBER NOT NULL ,
  publisherID           NUMBER NOT NULL
);
ALTER TABLE BOOKS ADD CONSTRAINT BOOKS_PK PRIMARY KEY ( bookID ) ;
ALTER TABLE BOOKS ADD CONSTRAINT name_book_UN UNIQUE ( name );
ALTER TABLE  BOOKS ADD (isbn number not null,
synopsis varchar(400)
);

CREATE TABLE GENRE
(
  genreID               NUMBER NOT NULL ,
  genre                 VARCHAR2 (50) NOT NULL
);
ALTER TABLE GENRE ADD CONSTRAINT GENRE_PK PRIMARY KEY ( genreID ) ;

CREATE TABLE PUBLISHER
(
  publisherID           NUMBER NOT NULL ,
  publisher             VARCHAR2 (100) NOT NULL
);
ALTER TABLE PUBLISHER ADD CONSTRAINT PUBLISHER_PK PRIMARY KEY ( publisherID ) ;
ALTER TABLE PUBLISHER ADD (
cnpj number(14) not null,
email varchar(100) not null,
phonenumber number not null,
country varchar(100) not null,
cState varchar(100) not null,
street varchar(100) not null,
zipcode number not null,
addressnumber number not null
);

CREATE TABLE LOGIN (
  loginID               NUMBER NOT NULL ,
  username                VARCHAR2 (100) NOT NULL ,
  password              VARCHAR2 (100) NOT NULL ,
  userID                NUMBER NOT NULL
);
ALTER TABLE LOGIN ADD CONSTRAINT LOGIN_PK PRIMARY KEY ( loginID ) ;
ALTER TABLE LOGIN ADD CONSTRAINT LOGIN_USER_UN UNIQUE ( userID );
ALTER TABLE LOGIN ADD loginType NUMBER NOT NULL;
ALTER TABLE LOGIN ADD CONSTRAINT LOGIN_USERNAME_UN UNIQUE ( username );

CREATE TABLE USUARIO (
  userID                NUMBER NOT NULL ,
  name                  VARCHAR2 (150) NOT NULL ,
  cpf                   NUMBER (11) NOT NULL
);

ALTER TABLE USUARIO ADD CONSTRAINT USER_PK PRIMARY KEY ( userID ) ;
Alter Table USUARIO add (lastName varchar2(150) not null, 
email varchar2(150) not null,
gender varchar2(50) not null,
birthDate date not null,
phoneNumber number not null,
zipCode number not null,
city varchar2(50) not null,
cstate varchar2(50) not null,
street varchar2(100) not null,
houseNumber number not null);

ALTER TABLE LOGIN ADD CONSTRAINT LOGIN_USER_FK FOREIGN KEY ( userID ) REFERENCES USUARIO ( userID ) ;
ALTER TABLE BOOKS ADD CONSTRAINT BOOKS_AUTHOR_FK FOREIGN KEY ( authorID ) REFERENCES AUTHOR ( authorID ) ;
ALTER TABLE BOOKS ADD CONSTRAINT BOOKS_GENRE_FK FOREIGN KEY ( genreID ) REFERENCES GENRE ( genreID ) ;
ALTER TABLE BOOKS ADD CONSTRAINT BOOKS_PUBLISHER_FK FOREIGN KEY ( publisherID ) REFERENCES PUBLISHER ( publisherID ) ;

Create Table Wishlist (
  bookID Number,
  CONSTRAINT book_wishlist_fk FOREIGN KEY (bookID) REFERENCES Books(bookID),
  userID number,
  CONSTRAINT usuario_wishlist_fk FOREIGN KEY (userID) REFERENCES Usuario(userID)
);