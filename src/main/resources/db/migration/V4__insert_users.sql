-- insert default owner and client with bcrypt-encoded passwords
INSERT INTO users(username, password, role) VALUES
('owner', '$2a$12$V5nXmqw4iVSvTHvCYG.Wke6N.ugT.r2EF8aaGmRfylHu2bGSRg3Yy', 'ROLE_OWNER'),
('client', '$2a$12$1bbPNFClnge.To2DsO4y6OOfgDVPu68831rs6DXE8Xt74LN.4dBU6', 'ROLE_CLIENT');
