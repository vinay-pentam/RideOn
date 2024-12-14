INSERT INTO user_table (email, password, name)
VALUES
('rider1@example.com', 'password123', 'Rider One'),
('driver1@example.com', 'password456', 'Driver One'),
('driver2@example.com', 'password789', 'Driver Two'),
('driver3@example.com', 'password321', 'Driver Three'),
('driver4@example.com', 'password654', 'Driver Four');

INSERT INTO user_roles (user_id, roles)
VALUES
(1, 'RIDER'),  -- Only this user is a rider
(2, 'DRIVER'),
(3, 'DRIVER'),
(4, 'DRIVER'),
(5, 'DRIVER'),
(2, 'RIDER'),
(3, 'RIDER'),
(4, 'RIDER'),
(5, 'RIDER');

INSERT INTO rider (rating, user_id)
VALUES
(3.0, 1);

INSERT INTO driver (user_id, rating, available, current_location)
VALUES
(2, 4.5, true, ST_GeomFromText('POINT(78.561712 17.401454)', 4326)), -- Location near uppal
(3, 3.9, true, ST_GeomFromText('POINT(78.550780 17.346092)', 4326)), -- Location near l b nagar
(4, 4.8, true, ST_GeomFromText('POINT(78.472121 17.329345)', 4326)), -- Location near falaknuma
(5, 4.2, true, ST_GeomFromText('POINT(78.432395 17.395629)', 4326)); -- Location near medhipatnam

--INSERT INTO driver (user_id, rating, available, current_location)
--VALUES
--(2, 4.5, true, ST_GeomFromText('POINT(77.1025 28.7041)', 4326)), -- Location near Charminar
--(3, 3.9, false, ST_GeomFromText('POINT(77.2167 28.6667)', 4326)), -- Location near Gachibowli
--(4, 4.8, true, ST_GeomFromText('POINT(77.2273 28.6353)', 4326)), -- Location near Miyapur
--(5, 4.2, true, ST_GeomFromText('POINT(77.2500 28.5500)', 4326)); -- Location near Hitech City

INSERT INTO wallet (amount, user_id)
VALUES
(300, 1),
(600, 2);