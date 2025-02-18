-- Mysql 서버 접속 ( 비밀번호: password )
> mysql -u root -p;

-- Mysql 유저 생성 ( user: demo, password: demo)
CREATE USER 'demo'@'%' IDENTIFIED BY 'demo';

-- Mysql 데이터베이스 생성
CREATE DATABASE master_db default CHARACTER SET UTF8;
CREATE DATABASE slave_db default CHARACTER SET UTF8;

-- Mysql 데이터베이스 조회
SHOW DATABASES;

-- Mysql 유저 권한 부여 ( 모든 권한 부여, demo_db에 대한 모든 권한 부여 )
GRANT ALL PRIVILEGES ON *.* TO 'demo'@'%';
GRANT ALL PRIVILEGES ON master_db.* TO 'demo'@'%';
GRANT ALL PRIVILEGES ON slave_db.* TO 'demo'@'%';

-- Mysql 갱신
FLUSH PRIVILEGES;

-- Mysql 서보 접속 종료
mysql> quit