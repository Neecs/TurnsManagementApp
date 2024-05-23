CREATE DATABASE IF NOT EXISTS turns_management_app_users_data;

use turns_management_app_users_data;

CREATE USER 'users_data_manager'@'%' IDENTIFIED BY 'admin';

GRANT ALL PRIVILEGES ON turns_management_app_users_data.* TO 'users_data_manager'@'%';

FLUSH PRIVILEGES;

CREATE TABLE user (
    id VARCHAR(100) PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    organization VARCHAR(50)
);

drop table user;