INSERT INTO user(id,username,password,active) VALUES (1,'admin','$2a$10$n9qoSXJrVvSZNgGWlbP9qOLN5Hdat6Nuj2Qly4eqkBvIHlHeyz7la',1);
INSERT INTO user(id,username,password,active) VALUES (2,'user','$2a$10$n9qoSXJrVvSZNgGWlbP9qOLN5Hdat6Nuj2Qly4eqkBvIHlHeyz7la',1);

INSERT INTO role (id,role) VALUES(1,'ROLE_ADMIN');
INSERT INTO role (id,role) VALUES(2,'ROLE_USER');

INSERT INTO user_role(user_id, role_id) VALUES (1,1);
INSERT INTO user_role(user_id, role_id) VALUES (2,2);

INSERT INTO oauth_client_details
    (client_id, client_secret, scope, authorized_grant_types,
    web_server_redirect_uri, authorities, access_token_validity,
    refresh_token_validity, additional_information, autoapprove)
VALUES
    ('paingan-client', '$2a$10$WG7koQ8zgDlPAQSJu2Tl8OhjKw.DuX2IN0/hz0w.ix6ShTjKktmiW', 'read,write,trust',
    'password,authorization_code,refresh_token,implicit', null, null, 36000, 36000, null, true);