-- CURRENT_TIMESTAMP generates different value than application
-- "createdOn":  1367740978000,
-- "modifiedOn": 1367733946000,
INSERT INTO user VALUES ('admin',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'adminus', true, 'adminator' ,'admin');
INSERT INTO user VALUES ('ala',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'Joanna', false, 'Brodzik' ,'bratdzika');
INSERT INTO user VALUES ('ola',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'Aleksandra', false, 'Wielka', 'mezopoto');
INSERT INTO user VALUES ('ela',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'Elzbieta', false, 'Druga' ,'zmywak');
INSERT INTO item VALUES (1,CURRENT_TIMESTAMP ,CURRENT_TIMESTAMP,'testAmount', 'testDescription',CURRENT_TIMESTAMP,'testItemName',5.5,CURRENT_TIMESTAMP,'ola');
INSERT INTO item VALUES (2,CURRENT_TIMESTAMP ,CURRENT_TIMESTAMP,'testAmount', 'testDescription',CURRENT_TIMESTAMP,'testItemName',5.5,CURRENT_TIMESTAMP,'ala');
INSERT INTO user VALUES ('testUser',CURRENT_TIMESTAMP , CURRENT_TIMESTAMP,'testFirstName',true,'testSurname','testPassword');