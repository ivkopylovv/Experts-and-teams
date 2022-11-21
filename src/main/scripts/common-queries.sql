-- Авторизация
SELECT *
FROM users
WHERE username = ?;

SELECT *
FROM users
WHERE username = ? AND password = ?;

SELECT name
FROM users_roles
         INNER JOIN users ON users.id = users_roles.user_id
         INNER JOIN roles ON roles.id = users_roles.role_id
WHERE user_id = ?;

INSERT INTO sessions (user_id, expired_at)
VALUES (?, ?);

-- Выход из системы
DELETE
FROM sessions
WHERE user_id = ?;


