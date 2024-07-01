INSERT INTO notification_source
(id, name, description, enabling_state, type, methods, user_id, params, created, updated, message, pending_seconds)
VALUES (1, 'Включение Зажигания', 'зажигание включено', 'ENABLE', 'SENSOR_RANGE_CONTROL', '{PUSH}', 2,
        '{"min": 1, "action": "within", "sensorName": "Зажигание"}', '2021-02-09 14:13:19', '2021-02-09 14:13:19',
        'notification', 120);

INSERT INTO notification_source_unit (source_id, unit_id)
VALUES (1, 7);

INSERT INTO notification
(id, source_id, unit_id, start_time, finish_time, is_read, created_time, updated_time, status)
VALUES (1, 1, 7, '2021-02-18 14:00:00', '2021-02-18 14:10:00', FALSE, '2021-02-18 14:00:00', '2021-02-18 14:00:00',
        'COMPLETED'),
       (2, 1, 7, '2021-02-18 14:20:00', '2021-02-18 14:30:00', FALSE, '2021-02-18 14:20:00', '2021-02-18 14:20:00',
        'COMPLETED'),
       (3, 1, 7, '2021-02-18 14:40:00', '2021-02-18 14:50:00', TRUE, '2021-02-18 14:40:00', '2021-02-18 14:40:00',
        'COMPLETED'),
       (4, 1, 7, '2021-02-18 14:51:00', '2021-02-18 14:52:00', FALSE, '2021-02-18 14:40:00', '2021-02-18 14:40:00',
        'CANCELLED'),
       (5, 1, 7, '2021-02-18 15:00:00', NULL, FALSE, '2021-02-18 15:00:00', '2021-02-18 15:00:00', 'ACTIVE');
