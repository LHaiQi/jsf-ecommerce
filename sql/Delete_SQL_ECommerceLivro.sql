ALTER TABLE Wishlist DROP CONSTRAINT BOOK_WISHLIST_FK;
ALTER TABLE Wishlist DROP CONSTRAINT USUARIO_WISHLIST_FK;
Drop table Wishlist;

ALTER TABLE BOOKS DROP CONSTRAINT BOOKS_PK;
ALTER TABLE BOOKS DROP CONSTRAINT BOOKS_AUTHOR_FK;
ALTER TABLE BOOKS DROP CONSTRAINT BOOKS_GENRE_FK;
ALTER TABLE BOOKS DROP CONSTRAINT BOOKS_PUBLISHER_FK;
Drop Table BOOKS;

ALTER TABLE AUTHOR DROP CONSTRAINT AUTHOR_PK;
Drop Table Author;

ALTER TABLE GENRE DROP CONSTRAINT GENRE_PK;
Drop Table GENRE;

ALTER TABLE "LOGIN" DROP CONSTRAINT LOGIN_PK;
Alter Table "LOGIN" Drop CONSTRAINT LOGIN_USER_FK;
Drop Table "LOGIN";

ALTER TABLE PUBLISHER DROP CONSTRAINT PUBLISHER_PK;
Drop Table PUBLISHER;

ALTER TABLE USUARIO DROP CONSTRAINT USER_PK;
Drop Table USUARIO;
