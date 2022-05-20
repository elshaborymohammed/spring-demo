INSERT INTO spring.user(id, username, algorithm, password)
VALUES (1, 'admin', 'BCRYPT', '12345678');
INSERT INTO spring.user(id, username, algorithm, password)
VALUES (2, 'user', 'BCRYPT', '12345678');

INSERT INTO spring.role (user_id, role)
VALUES (1, 'USER');
INSERT INTO spring.role (user_id, role)
VALUES (1, 'ADMIN');
INSERT INTO spring.role (user_id, role)
VALUES (2, 'USER');

INSERT INTO spring.post (content, manufacturer)
VALUES ('content', 'manufacturer');
INSERT INTO spring.post (content, manufacturer)
VALUES ('content', 'manufacturer');
INSERT INTO spring.post (content, manufacturer)
VALUES ('content', 'manufacturer');
INSERT INTO spring.post (content, manufacturer)
VALUES ('content', 'manufacturer');
INSERT INTO spring.post (content, manufacturer)
VALUES ('content', 'manufacturer');

