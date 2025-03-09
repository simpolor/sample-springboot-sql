-- Mysql 서버 접속 ( 비밀번호: password )
> mysql -u root -p;

-- Mysql 유저 생성 ( user: test, password: test12#$)
CREATE USER 'test'@'%' IDENTIFIED BY 'test12#$';

-- Mysql 데이터베이스 생성
CREATE DATABASE master_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE DATABASE slave_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Mysql 데이터베이스 조회
SHOW DATABASES;

-- Mysql 유저 권한 부여 ( 모든 권한 부여, demo_db에 대한 모든 권한 부여 )
GRANT ALL PRIVILEGES ON *.* TO 'test'@'%';
GRANT ALL PRIVILEGES ON master_db.* TO 'test'@'%';
GRANT ALL PRIVILEGES ON slave_db.* TO 'test'@'%';

-- Mysql 갱신
FLUSH PRIVILEGES;

-- Mysql 서보 접속 종료
mysql> quit