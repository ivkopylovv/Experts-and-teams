-- Запросы для Пользователя
-- Регистрация
INSERT INTO users (name, username, password)
VALUES (?, ?, ?);
INSERT INTO users_roles (user_id, role_id)
VALUES (?, ?);