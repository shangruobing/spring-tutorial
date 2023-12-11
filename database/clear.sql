USE SpringTutorial;

SET foreign_key_checks = 0;
DELETE FROM company;
ALTER TABLE company AUTO_INCREMENT = 1;
SET foreign_key_checks = 1;

SET foreign_key_checks = 0;
DELETE FROM user;
ALTER TABLE user AUTO_INCREMENT = 1;
SET foreign_key_checks = 1;