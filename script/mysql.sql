INSERT INTO User (username,email,password,firstName, lastName,enabled, createdDate, avatar)
VALUES ('ocurspotter','ocur@gmail.com','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y','Mario','Ferreira',true, '2018-01-19 03:14:07', 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/96/Creative-Tail-People-boy.svg/1024px-Creative-Tail-People-boy.svg.png'),
('alex','alex@gmail.com','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y','Alex','Teixeira',true, '2018-01-19 03:14:07', 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/96/Creative-Tail-People-boy.svg/1024px-Creative-Tail-People-boy.svg.png');

INSERT INTO UserRole (userId, role)
VALUES (1, 'ROLE_USER'),
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

INSERT INTO Type (name, description)
VALUES ('Type1', 'Type1'),
('Type2', 'Type2'),
('Type3', 'Type3');

INSERT INTO Occurrence(title, description, status, openDate, closeDate, type, userId, latitude, longitude, typeId, suggestion, image)
VALUES ('Ocurrence1', 'Occurrence1', 0, '2018-01-18 03:14:07', '2018-01-19 03:14:07', 1, 1, '41.5454486', '-8.426507000000015', 1, 0, 'http://i.dailymail.co.uk/i/pix/2016/11/08/11/48ADDoMZCF3ae3117d0fea6ff028-3915562-A_massive_shinkhole_is_created_in_the_middle_of_the_business_dis-a-47_1478603034735.jpg'),
('Ocurrence2', 'Occurrence2', 0, '2018-01-18 03:14:07', '2018-01-19 03:14:07', 2, 2, '41.5454486', '-8.426507000000015', 2, 0, 'http://i.dailymail.co.uk/i/pix/2016/11/08/11/48ADDoMZCF3ae3117d0fea6ff028-3915562-A_massive_shinkhole_is_created_in_the_middle_of_the_business_dis-a-47_1478603034735.jpg'),
('Ocurrence3', 'Occurrence3', 0, '2018-01-18 03:14:07', '2018-01-19 03:14:07', 3, 2, '41.5454486', '-8.426507000000015', 1, 0, 'http://i.dailymail.co.uk/i/pix/2016/11/08/11/48ADDoMZCF3ae3117d0fea6ff028-3915562-A_massive_shinkhole_is_created_in_the_middle_of_the_business_dis-a-47_1478603034735.jpg');

INSERT INTO Solution(userId, occurrenceId, openDate, deadline, value, description, choosed, status)
VALUES (1, 7, '2018-01-18 03:14:07', '2018-01-19 03:14:07', 25, 'Solution1', 0, 0),
(1, 8, '2018-01-18 03:14:07', '2018-01-19 03:14:07', 50, 'Solution2', 0, 0),
(2, 9, '2018-01-18 03:14:07', '2018-01-19 03:14:07', 100, 'Solution3', 0, 0);

INSERT INTO OccurrenceVote(userId, occurrenceId, vote)
VALUES (1,7,0),
(1,8,1),
(2,9,1);

INSERT INTO SolutionVote(userId, solutionId, vote)
VALUES (1,4,0),
(1,5,1),
(2,6,1);

INSERT INTO UserType(userId, typeId)
VALUES (1,1),
(1,2),
(2,3);