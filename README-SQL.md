# by-coders-backend

MYSQL-DOCKER

# mysql -u root -p
Enter password:
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 22
Server version: 8.0.27 MySQL Community Server - GPL

Copyright (c) 2000, 2021, Oracle and/or its affiliates.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> create database bycoders;
Query OK, 1 row affected (0.02 sec)

mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| bycoders           |
| information_schema |
| mysql              |
| performance_schema |
| sys                |
| tasklogs           |
+--------------------+
6 rows in set (0.01 sec)

mysql> use bycoders;
Database changed
mysql> create table test(tipo VARCHAR(10) NOT NULL,data VARCHAR(10) NOT NULL,valor INTEGER,cpf VARCHAR(10));
Query OK, 0 rows affected (0.06 sec)

mysql> SHOW TABLES;
+--------------------+
| Tables_in_bycoders |
+--------------------+
| test               |
+--------------------+
1 row in set (0.00 sec)

mysql> DROP TABLE test;
Query OK, 0 rows affected (0.03 sec)

mysql> DESCRIBE cnab;
+-----------+-------------+------+-----+---------+-------+
| Field     | Type        | Null | Key | Default | Extra |
+-----------+-------------+------+-----+---------+-------+
| tipo      | varchar(1)  | YES  |     | NULL    |       |
| data      | date        | YES  |     | NULL    |       |
| valor     | double      | YES  |     | NULL    |       |
| cpf       | int         | YES  |     | NULL    |       |
| cartao    | int         | YES  |     | NULL    |       |
| hora      | time        | YES  |     | NULL    |       |
| dono_loja | varchar(14) | YES  |     | NULL    |       |
| nome_loja | varchar(19) | YES  |     | NULL    |       |
+-----------+-------------+------+-----+---------+-------+
8 rows in set (0.00 sec)

mysql> alter table cnab add column id INT unsigned NOT NULL AUTO_INCREMENT;
ERROR 1075 (42000): Incorrect table definition; there can be only one auto column and it must be defined as a key
mysql> alter table cnab add column id INT unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY;
Query OK, 0 rows affected (0.09 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> DESCRIBE cnab;
+-----------+--------------+------+-----+---------+----------------+
| Field     | Type         | Null | Key | Default | Extra          |
+-----------+--------------+------+-----+---------+----------------+
| tipo      | varchar(1)   | YES  |     | NULL    |                |
| data      | date         | YES  |     | NULL    |                |
| valor     | double       | YES  |     | NULL    |                |
| cpf       | int          | YES  |     | NULL    |                |
| cartao    | int          | YES  |     | NULL    |                |
| hora      | time         | YES  |     | NULL    |                |
| dono_loja | varchar(14)  | YES  |     | NULL    |                |
| nome_loja | varchar(19)  | YES  |     | NULL    |                |
| id        | int unsigned | NO   | PRI | NULL    | auto_increment |
+-----------+--------------+------+-----+---------+----------------+
9 rows in set (0.00 sec)

mysql> alter table cnab drop column id INT unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY;
ERROR 1064 (42000): You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'INT unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY' at line 1
mysql> alter table cnab drop column id;
Query OK, 0 rows affected (0.08 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql> DESCRIBE cnab;
+-----------+-------------+------+-----+---------+-------+
| Field     | Type        | Null | Key | Default | Extra |
+-----------+-------------+------+-----+---------+-------+
| tipo      | varchar(1)  | YES  |     | NULL    |       |
| data      | date        | YES  |     | NULL    |       |
| valor     | double      | YES  |     | NULL    |       |
| cpf       | int         | YES  |     | NULL    |       |
| cartao    | int         | YES  |     | NULL    |       |
| hora      | time        | YES  |     | NULL    |       |
| dono_loja | varchar(14) | YES  |     | NULL    |       |
| nome_loja | varchar(19) | YES  |     | NULL    |       |
+-----------+-------------+------+-----+---------+-------+
8 rows in set (0.01 sec)

mysql> alter table cnab add column id LONG unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY;
ERROR 1064 (42000): You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY' at line 1
mysql> alter table cnab add column id INT unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY;
Query OK, 0 rows affected (0.07 sec)
Records: 0  Duplicates: 0  Warnings: 0

mysql>