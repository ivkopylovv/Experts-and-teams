-- Триггеры
-- Создание триггера для генерации Id при INSERT INTO для таблицы Пользователи
CREATE OR REPLACE TRIGGER users_on_insert
  BEFORE INSERT ON users
  FOR EACH ROW
BEGIN
SELECT users_seq.nextval
INTO :new.id
FROM dual;
END;

-- Создание триггера для генерации Id при INSERT INTO для таблицы Сессии
CREATE OR REPLACE TRIGGER sessions_on_insert
  BEFORE INSERT ON sessions
  FOR EACH ROW
BEGIN
SELECT sessions_seq.nextval
INTO :new.id
FROM dual;
END;

-- Создание триггера для генерации Id при INSERT INTO для таблицы Команды
CREATE OR REPLACE TRIGGER teams_on_insert
  BEFORE INSERT ON teams
  FOR EACH ROW
BEGIN
SELECT teams_seq.nextval
INTO :new.id
FROM dual;
END;

-- Создание триггера для генерации Id при INSERT INTO для таблицы Сообщения команд
CREATE OR REPLACE TRIGGER team_messages_on_insert
  BEFORE INSERT ON team_messages
  FOR EACH ROW
BEGIN
SELECT team_messages_seq.nextval
INTO :new.id
FROM dual;
END;

-- Создание триггера для генерации Id при INSERT INTO для таблицы Запросы на вступление в команду
CREATE OR REPLACE TRIGGER team_join_requests_on_insert
  BEFORE INSERT ON team_join_requests
  FOR EACH ROW
BEGIN
SELECT team_join_requests_seq.nextval
INTO :new.id
FROM dual;
END;

-- Создание триггера для генерации Id при INSERT INTO для таблицы Уведомления
CREATE OR REPLACE TRIGGER notifications_on_insert
  BEFORE INSERT ON notifications
  FOR EACH ROW
BEGIN
SELECT notifications_seq.nextval
INTO :new.id
FROM dual;
END;