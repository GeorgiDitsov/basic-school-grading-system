INSERT INTO role(role_id, role_name) VALUES (1, 'admin');
INSERT INTO role(role_id, role_name) VALUES (2, 'student');

SELECT setval('public.role_id_seq', 2, true);

INSERT INTO public.user(user_id, username, password, role_id)
	VALUES (1, 'admin', 'admin', 1);
INSERT INTO public.user(user_id, username, password, role_id)
	VALUES (2, 'student1', 'pass', 2);
	
SELECT setval('public.user_id_seq', 2, true);

UPDATE student SET user_id=2 WHERE student_id=2;