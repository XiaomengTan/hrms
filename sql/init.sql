CREATE DATABASE IF NOT EXISTS hrms DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE hrms;

DROP TABLE IF EXISTS job;
CREATE TABLE IF NOT EXISTS job
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(192) NOT NULL,
    demand       TEXT         NOT NULL,
    type         TINYINT      NOT NULL CHECK (type IN (1, 2, 3)),
    salary       INT          NOT NULL CHECK (salary >= 2000 AND salary <= 60000),
    overtime_pay INT          NOT NULL CHECK (overtime_pay >= 20 AND overtime_pay <= 100),
    introduction TEXT         NOT NULL,
    total_worker INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS worker
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(192) NOT NULL,
    birthday        DATE         NOT NULL,
    social_security TINYINT      NOT NULL CHECK (social_security IN (0, 1)),
    rp_record       TEXT,
    transfer        TEXT,
    work_status     TINYINT      NOT NULL CHECK (work_status IN (0, 1)),
    certificate     TEXT,
    labor_status    TINYINT      NOT NULL CHECK (labor_status IN (1, 2, 3)),
    job             INT          NOT NULL,
    onboarding_time DATE         NOT NULL,
    departure_time  DATE,
    FOREIGN KEY (job) REFERENCES job (id)
);

CREATE TABLE IF NOT EXISTS department
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(192) NOT NULL,
    sub_dept INT,
    FOREIGN KEY (sub_dept) REFERENCES department (id)
);

CREATE TABLE IF NOT EXISTS lesson
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(192) NOT NULL,
    schedule  DATE         NOT NULL,
    time_plan VARCHAR(384) NOT NULL,
    address   VARCHAR(384) NOT NULL
);
ALTER TABLE lesson
    ADD COLUMN info VARCHAR(3072) NOT NULL
        CHECK (CHAR_LENGTH(info) BETWEEN 1 AND 1024);

CREATE TABLE IF NOT EXISTS trainee
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    les_id    INT NOT NULL,
    worker_id INT NOT NULL,
    FOREIGN KEY (les_id) REFERENCES lesson (id),
    FOREIGN KEY (worker_id) REFERENCES worker (id)
);

CREATE TABLE IF NOT EXISTS payment
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    profit          DECIMAL(12, 9) DEFAULT 0,
    tax             DECIMAL(12, 9) DEFAULT 0,
    social_security DECIMAL(12, 9) DEFAULT 0,
    water           DECIMAL(12, 9) DEFAULT 0,
    electricity     DECIMAL(12, 9) DEFAULT 0,
    salary          DECIMAL(12, 9) DEFAULT 0,
    outlay          DECIMAL(12, 9) DEFAULT 0,
    date            DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS salary
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    job_id          INT     NOT NULL,
    worker_id       INT     NOT NULL,
    settlement_time DATE    NOT NULL,
    cycle           TINYINT NOT NULL CHECK (cycle IN (1, 2, 3)),
    salary_offset   INT     NOT NULL CHECK (salary_offset >= 20 AND salary_offset <= 2000),
    salary          INT     NOT NULL CHECK (salary >= 2000 AND salary <= 60000),
    FOREIGN KEY (job_id) REFERENCES job (id),
    FOREIGN KEY (worker_id) REFERENCES worker (id)
);

DROP TABLE IF EXISTS user;
CREATE TABLE IF NOT EXISTS user
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(192) NOT NULL,
    password VARCHAR(64)  NOT NULL DEFAULT '88888888',
    worker   INT
);

DROP TABLE IF EXISTS log;
CREATE TABLE IF NOT EXISTS log
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    operate_user  INT          NOT NULL,
    operate_time  DATETIME     NOT NULL,
    class_name    VARCHAR(128) NOT NULL,
    method_name   VARCHAR(128) NOT NULL,
    method_params TEXT,
    return_value  TEXT,
    cost_time     INT          NOT NULL,
    FOREIGN KEY (operate_user) REFERENCES user (id)
);