CREATE USER 'demo_java' IDENTIFIED BY 'java';
grant all on *.* to 'demo_java'@'%' identified by 'java';
FLUSH PRIVILEGES;
CREATE DATABASE jt_database CHARACTER SET utf8 COLLATE utf8_general_ci;




