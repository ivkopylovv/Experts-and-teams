-- Запросы для Эксперта
-- Регистрация
INSERT INTO users (name, username, password)
VALUES (?, ?, ?);
INSERT INTO users_roles (user_id, role_id)
VALUES (?, ?);
INSERT INTO expert_details (user_id, max_teams_count)
VALUES (?, ?);
INSERT INTO expert_skills (user_id, skill)
VALUES (?, ?);