INSERT INTO author(id, name) VALUES(1001, 'Mohammed');
INSERT INTO author(id, name) VALUES(1002, 'Ahmed');
INSERT INTO author(id, name) VALUES(1003, 'Uncle BOb');

INSERT INTO publisher(id, name) VALUES(2001, 'Blog');
INSERT INTO publisher(id, name) VALUES(2002, 'Stackoverflow');
INSERT INTO publisher(id, name) VALUES(2003, 'Blog Stackoverflow');
INSERT INTO publisher(id, name) VALUES(2004, 'Blog Quera');

INSERT INTO reference(id, name) VALUES(3001, 'Quera');
INSERT INTO reference(id, name) VALUES(3002, 'Stackoverflow');

INSERT INTO article(id, content, deleted, published_by_id) VALUES(4001, 'Spring Boot', 0, 2001);
INSERT INTO article(id, content, deleted, published_by_id) VALUES(4002, 'Spring Data', 0, 2002);