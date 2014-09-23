DROP TABLE IF EXISTS Counselor;
DROP TABLE IF EXISTS Subject;


CREATE TABLE Counselor (
       counselor_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
       first_name VARCHAR (50),
       nick_name VARCHAR (50),
       last_name VARCHAR (50),
       telephone VARCHAR (25),
       email VARCHAR (50),
       member_since DATE DEFAULT '0000-00-00',
       PRIMARY KEY (counselor_id)
);


CREATE TABLE Subject (
	subject_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
	name VARCHAR (50),
	description TEXT,
	counselor_idfk SMALLINT UNSIGNED,
	PRIMARY KEY (subject_id)
);


INSERT INTO Counselor (first_name, nick_name, last_name, telephone, email, member_since)
       VALUES  	('Jake', '"The Snake"', 'Roberts', '412 565-5656', 'snake@ifpwafcad.com', '2003-09-10'),
               	('Andre', '"The Giant"', '', '606 443-4567', 'bobo@ifpwafcad.com', '2001-01-12'),
               	('Brutus', '"The Barber"', 'Beefcake', '555 555-4432', 'beefcake@ifpwafcad.com', '2005-03-08'),
               	('Randy', '"Macho Man"', 'Savage', '555 317-4444', 'machoman@hotmail.com', '2000-11-10'),
               	('Ricky', '"The Dragon"', 'Steamboat','334 612-5678', 'r_steamboat@ifpwafcad.com', '1996-01-01'),
               	('George', '"The Animal"', 'Steele', '412 565-5656', 'george@ifpwafcad.com', Now()),
               	('Koko', '"The Birdman"', 'B. Ware', '553 499-8162', 'birdman@ifpwafcad.com', '1999-12-03'),
               	('Greg', '"The Hammer"', 'Valentine', '617 889-5545', 'valentino@ifpwafcad.com', '1998-05-07'),
               	('Bobby', '"The Brain"', 'Heenan', '777 513-3333', 'b_heenan@ifpwafcad.com', '2002-07-09');


INSERT INTO Subject (name, description, counselor_idfk)
	VALUES  ('Financial Consultancy', 'Investment advice and financial planning guidance, helping you to maximize your net worth through proper asset allocation. This includes the stocks, bonds, mutual funds, insurance products, and gambling strategies proven to work.', '9'),
		('Existential Psychotherapy', 'Often wonder what the purpose of life is? After learning the basics of Existential Psychotherapy, you''ll understand why you''re happy when you''re feeling happy, and why you''re not feeling happy when you''re not happy, allowing you to transcend to a state of pure bliss.', '7'),
	        ('Temper Management', 'Are your angry outbursts affecting your relationships with loved-ones? Do tantrums at work hinder your ability to perform? Temper management helps you to channel your anger into positive, life-changing productivity.', '4'),
		('Past-Life Regression', 'Past-Life Regression is a journey of the soul, backward and forward through time, like a yo-yo.', '2'),	      
	       	('Marriage Guidance', 'Even if you share a solid, caring and mutually beneficial relationship with your spouse, you may both still need urgent counseling. There''s only one way to find out. Contact us now!', '1'),
	       	('Crisis Management', 'Whether you''re a fireman, executive CEO, or housewife, applying crisis management techniques at the right moment can be life-saving for you as well as all those around you.', '3'),
	       	('Dream Analysis', 'Dream Analysis will allow you to delve into the depths of your subconcious. Your counselor will put you through a rigorous, disciplined training program, allowing you to remain in a waking state while dreaming. By the end, you''ll be able to analyse your dreams while you are having them!', '8'),	       
	       	('Hypnosis', 'Contrary to popular belief, hypnosis can be a powerful and effective form of counseling.', '6'),
		('Reiki', 'Need a massage but are afraid to let a stranger touch your body? Reiki could be the perfect solution for you.', '5');
