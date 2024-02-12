INSERT INTO telegram(id, user_id, telegram_chat_id, telegram_user_id, telegram_user_first_name, telegram_user_last_name,
                     telegram_username, is_activated, created)
VALUES (255, 2, 100, 100, 'user_100', NULL, NULL, TRUE, '2021-04-26 11:34:17');
INSERT INTO telegram(id, user_id, telegram_chat_id, telegram_user_id, telegram_user_first_name, telegram_user_last_name,
                     telegram_username, is_activated, created)
VALUES (256, 2, 101, 101, 'user_101', NULL, NULL, TRUE, '2021-04-26 11:34:58');
INSERT INTO telegram(id, user_id, telegram_chat_id, telegram_user_id, telegram_user_first_name, telegram_user_last_name,
                     telegram_username, is_activated, created)
VALUES (257, 3, 103, 103, 'user_103', NULL, NULL, TRUE, '2021-04-26 11:35:40');
INSERT INTO telegram(id, user_id, telegram_chat_id, telegram_user_id, telegram_user_first_name, telegram_user_last_name,
                     telegram_username, is_activated, created)
VALUES (258, 3, 104, 104, 'user_104', NULL, NULL, TRUE, '2021-04-26 11:36:06');
INSERT INTO telegram(id, user_id, telegram_chat_id, telegram_user_id, telegram_user_first_name, telegram_user_last_name,
                     telegram_username, is_activated, created)
VALUES (259, 2, 104, 104, 'user_105', NULL, NULL, FALSE, '2021-04-26 11:36:06');

INSERT INTO notification_source(id, name, description, enabling_state, type, methods, user_id, params, created, updated,
                                message, pending_seconds)
VALUES (260, 'Включение Зажигания', 'зажигание включено', 'ENABLE', 'SENSOR_RANGE_CONTROL', '{PUSH}', 2,
        '{"min": 1, "action": "within", "sensorName": "Зажигание"}', '2021-02-09 14:13:19', '2021-02-09 14:13:19', 'notification',
        120);

INSERT INTO notification_source(id, name, description, enabling_state, type, methods, user_id, params, created, updated,
                                message, pending_seconds)
VALUES (261, 'Выключение Зажигания', 'зажигание выключено', 'ENABLE', 'SENSOR_RANGE_CONTROL', '{PUSH}', 2,
        '{"min": 1, "action": "out-of", "sensorName": "Зажигание"}', '2021-02-09 14:13:19', '2021-02-09 14:13:19', 'notification',
        120);

INSERT INTO notification_source_telegram (notification_source_id, telegram_id)
VALUES (260, 255);
INSERT INTO notification_source_telegram (notification_source_id, telegram_id)
VALUES (260, 256);
