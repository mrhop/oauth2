INSERT INTO user (username,email, password, activated) VALUES ('admin', 'admin@mail.me', 'b8f57d6d6ec0a60dfe2e20182d4615b12e321cad9e2979e0b9f81e0d6eda78ad9b6dcfe53e4e22d1', true);
INSERT INTO user (username,email, password, activated) VALUES ('user', 'user@mail.me', 'd6dfa9ff45e03b161e7f680f35d90d5ef51d243c2a8285aa7e11247bc2c92acde0c2bb626b1fac74', true);
INSERT INTO user (username,email, password, activated) VALUES ('rajith', 'rajith@abc.com', 'd6dfa9ff45e03b161e7f680f35d90d5ef51d243c2a8285aa7e11247bc2c92acde0c2bb626b1fac74', true);

INSERT INTO authority (name) VALUES ('ROLE_USER');
INSERT INTO authority (name) VALUES ('ROLE_ADMIN');

INSERT INTO user_authority (username,authority) VALUES ('rajith', 'ROLE_USER');
INSERT INTO user_authority (username,authority) VALUES ('user', 'ROLE_USER');
INSERT INTO user_authority (username,authority) VALUES ('admin', 'ROLE_USER');
INSERT INTO user_authority (username,authority) VALUES ('admin', 'ROLE_ADMIN');

INSERT INTO oauth_approvals (userId,	clientId,	scope ,status) VALUES ('admin', 'rajithapp1','read','APPROVED');


INSERT INTO oauth_client_details (client_id,resource_ids, client_secret, scope,authorities,authorized_grant_types,access_token_validity) VALUES ('rajithapp', 'staff_test,staff_test1', 'secret', 'read,write','ROLE_ADMIN,ROLE_USER','password,refresh_token',1800);
INSERT INTO oauth_client_details (client_id,resource_ids, client_secret, scope,authorities,authorized_grant_types,access_token_validity,autoapprove) VALUES ('rajithapp1', 'staff_test,staff_test1', 'secret', 'read,write','ROLE_ADMIN,ROLE_USER','authorization_code,refresh_token',30,'true');
INSERT INTO oauth_client_details (client_id,resource_ids, client_secret, scope,authorities,authorized_grant_types,access_token_validity) VALUES ('rajithapp2', 'staff_test,staff_test1', 'secret', 'read,write','ROLE_ADMIN,ROLE_USER','authorization_code,client_credentials',1800);
INSERT INTO oauth_client_details (client_id,resource_ids, client_secret, scope,authorities,authorized_grant_types,access_token_validity) VALUES ('rajithapp3', 'staff_test,staff_test1', 'secret', 'read,write','ROLE_ADMIN,ROLE_USER','authorization_code,implicit',1800);
