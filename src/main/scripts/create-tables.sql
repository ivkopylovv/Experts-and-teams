-- Создание таблицы Пользователи
CREATE TABLE users
(
    id NUMBER,
    name VARCHAR2 (30 CHAR) NOT NULL,
    username VARCHAR2 (30 CHAR) UNIQUE NOT NULL,
    password VARCHAR2 (150 CHAR) NOT NULL,
    is_blocked NUMBER(1, 0) DEFAULT 0
);

-- Первичный ключ таблицы Пользователи
ALTER TABLE users
    ADD (
    CONSTRAINT users_pk PRIMARY KEY (id)
  );

-- Создание последовательности для автогенерации первичного ключа
CREATE SEQUENCE users_seq START WITH 1;

-----------------------------------------------

-- Создание таблицы Роли
CREATE TABLE roles
(
    id NUMBER,
    name VARCHAR2(30 CHAR) NOT NULL
);

-- Первичный ключ таблицы Роли
ALTER TABLE roles
    ADD (
    CONSTRAINT roles_pk PRIMARY KEY (id)
  );

-- Создание последовательности для автогенерации первичного ключа
CREATE SEQUENCE roles_seq START WITH 1;

-----------------------------------------------------------

-- Создание промежуточной таблицы Роли пользователей
CREATE TABLE users_roles
(
    user_id NUMBER,
    role_id NUMBER
);

-- Первичный ключ промежуточной таблицы Роли пользователей
ALTER TABLE users_roles
    ADD (
    CONSTRAINT users_roles_pk PRIMARY KEY (user_id, role_id)
  );

-- Создание внешнего ключа на таблицу Роли
ALTER TABLE users_roles
    ADD CONSTRAINT roles_fk
        FOREIGN KEY (role_id)
            REFERENCES roleTypes (id);

-- Создание внешнего ключа на таблицу Пользователи
ALTER TABLE users_roles
    ADD CONSTRAINT users_fk
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE CASCADE;

-----------------------------------------------------------

-- Создание таблицы Сессии
CREATE TABLE sessions
(
    id NUMBER,
    user_id NUMBER NOT NULL,
    expired_at TIMESTAMP NOT NULL
);

-- Первичный ключ таблицы Сессии
ALTER TABLE sessions
    ADD (
    CONSTRAINT sessions_pk PRIMARY KEY (id)
  );

-- Создание последовательности для автогенерации первичного ключа
CREATE SEQUENCE sessions_seq START WITH 1;

-- Создание внешнего ключа на таблицу Пользователи
ALTER TABLE sessions
    ADD CONSTRAINT sessions_fk
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE CASCADE;

-----------------------------------------------------------

-- Создание таблицы Детали эксперта
CREATE TABLE expert_details
(
    user_id NUMBER,
    current_teams_count NUMBER DEFAULT 0,
    max_teams_count NUMBER NOT NULL
);

-- Первичный ключ таблицы Детали эксперта
ALTER TABLE expert_details
    ADD (
    CONSTRAINT expert_details_pk PRIMARY KEY (user_id)
  );

-- Создание внешнего ключа на таблицу Пользователи
ALTER TABLE expert_details
    ADD CONSTRAINT expert_details_fk
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE CASCADE;

---------------------------------------------------------------

-- Создание таблицы Команды
CREATE TABLE teams
(
    id NUMBER,
    name VARCHAR2(40 CHAR) UNIQUE NOT NULL,
    members_count NUMBER NOT NULL,
    captain_id NUMBER NOT NULL
);

-- Первичный ключ таблицы Команды
ALTER TABLE teams
    ADD (
    CONSTRAINT teams_pk PRIMARY KEY (id)
  );

-- Создание последовательности для автогенерации первичного ключа
CREATE SEQUENCE teams_seq START WITH 1;

-- Создание внешнего ключа на таблицу Пользователи
ALTER TABLE teams
    ADD CONSTRAINT teams_captain_fk
        FOREIGN KEY (captain_id)
            REFERENCES users (id)
            ON DELETE CASCADE;

------------------------------------------------

-- Создание таблицы Чаты команд
CREATE TABLE team_chats
(
    message_id NUMBER,
    team_id NUMBER NOT NULL,
    user_id NUMBER NOT NULL,
    message VARCHAR2(500 CHAR) NOT NULL,
    message_date TIMESTAMP NOT NULL,
    expert_id NUMBER NOT NULL
);

-- Первичный ключ таблицы Чаты команд
ALTER TABLE team_chats
    ADD (
    CONSTRAINT team_chats_pk PRIMARY KEY (message_id)
  );

-- Создание последовательности для автогенерации первичного ключа
CREATE SEQUENCE team_chats_seq START WITH 1;

-- Создание внешнего ключа на таблицу Команды
ALTER TABLE team_chats
    ADD CONSTRAINT team_chats_team_fk
        FOREIGN KEY (team_id)
            REFERENCES teams (id)
            ON DELETE CASCADE;

-- Создание внешнего ключа на таблицу Пользователи
ALTER TABLE team_chats
    ADD CONSTRAINT team_chats_user_fk
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE CASCADE;

-- Создание внешнего ключа на таблицу Пользователи
ALTER TABLE team_chats
    ADD CONSTRAINT team_chats_expert_fk
        FOREIGN KEY (expert_id)
            REFERENCES users (id)
            ON DELETE CASCADE;

------------------------------------------------

-- Создание промежуточной таблицы Участники команды
CREATE TABLE teams_members
(
    team_id NUMBER,
    user_id NUMBER
);

-- Первичный ключ промежуточной таблицы Участники команды
ALTER TABLE teams_members
    ADD (
    CONSTRAINT teams_members_pk PRIMARY KEY (team_id, user_id)
  );

-- Создание внешнего ключа на таблицу Команды
ALTER TABLE teams_members
    ADD CONSTRAINT teams_members_team_fk
        FOREIGN KEY (team_id)
            REFERENCES teams (id)
            ON DELETE CASCADE;

-- Создание внешнего ключа на таблицу Пользователи
ALTER TABLE teams_members
    ADD CONSTRAINT teams_members_user_fk
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE CASCADE;

------------------------------------------------

-- Создание промежуточной таблицы Экперты команды
CREATE TABLE teams_experts
(
    team_id NUMBER,
    expert_id NUMBER,
    is_blocked NUMBER(1, 0) DEFAULT 0
);

-- Первичный ключ промежуточной таблицы Участники команды
ALTER TABLE teams_experts
    ADD (
    CONSTRAINT teams_experts_pk PRIMARY KEY (team_id, expert_id)
  );

-- Создание внешнего ключа на таблицу Команды
ALTER TABLE teams_experts
    ADD CONSTRAINT teams_experts_team_fk
        FOREIGN KEY (team_id)
            REFERENCES teams (id)
            ON DELETE CASCADE;

-- Создание внешнего ключа на таблицу Пользователи
ALTER TABLE teams_members
    ADD CONSTRAINT teams_experts_expert_fk
        FOREIGN KEY (expert_id)
            REFERENCES users (id)
            ON DELETE CASCADE;

------------------------------------------------

-- Создание промежуточной таблицы Навыки экспертов
CREATE TABLE expers_skills
(
    user_id NUMBER,
    skill VARCHAR2(40 CHAR)
);

-- Первичный ключ промежуточной таблицы Навыки экспертов
ALTER TABLE expers_skills
    ADD (
    CONSTRAINT expers_skills_pk PRIMARY KEY (expert_id)
  );

-- Создание внешнего ключа на таблицу Пользователи
ALTER TABLE expers_skills
    ADD CONSTRAINT expers_skills_expert_fk
        FOREIGN KEY (expert_id)
            REFERENCES users (id);

------------------------------------------------

-- Создание таблицы Запросы вступления в команду
CREATE TABLE team_join_requests
(
    id NUMBER,
    user_id NUMBER NOT NULL,
    captain_id NUMBER NOT NULL,
    message VARCHAR2(100 CHAR) NOT NULL
);

-- Первичный ключ таблицы Запросы вступления в команду
ALTER TABLE team_join_requests
    ADD (
    CONSTRAINT team_join_requests_pk PRIMARY KEY (id)
  );

-- Создание последовательности для автогенерации первичного ключа
CREATE SEQUENCE team_join_requests_seq START WITH 1;

-- Создание внешнего ключа на таблицу Пользователи
ALTER TABLE team_join_requests
    ADD CONSTRAINT team_join_requests_user_fk
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE CASCADE;

-- Создание внешнего ключа на таблицу Пользователи
ALTER TABLE team_join_requests
    ADD CONSTRAINT team_join_requests_captain_fk
        FOREIGN KEY (captain_id)
            REFERENCES users (id)
            ON DELETE CASCADE;

