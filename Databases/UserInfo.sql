CREATE TABLE user_info(
	username VARCHAR(255) NOT NULL PRIMARY KEY,
	firstName VARCHAR(255) NOT NULL,
	lastName VARCHAR(255) NOT NULL,
	DOB DATE NOT NULL,
	address TEXT NOT NULL,
	email VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL
);

INSERT INTO user_info VALUES ("test","test","test","1999-03-20","test","test@test.com","test");