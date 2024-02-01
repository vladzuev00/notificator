INSERT INTO geofence (id, name, description, color, max_speed, user_id, boundaries)
VALUES (1, 'test-geo0.0', 'description0.0', '#111111', 0, 1, ST_GeomFromText('POLYGON((0 1, 10 0, 10 10, 0 10, 0 1))', 4326));
INSERT INTO geofence (id, name, description, color, max_speed, user_id, boundaries)
VALUES (2, 'test-geo5.0', 'description5.0', '#111111', 1, 2, ST_GeomFromText('POLYGON((0 2, 10 0, 10 10, 0 10, 0 2))', 4326));
INSERT INTO geofence (id, name, description, color, max_speed, user_id, boundaries)
VALUES (3, 'test-geo10.0', 'description10.0', '#111111', 2, 2, ST_GeomFromText('POLYGON((0 3, 10 0, 10 10, 0 10, 0 3))', 4326));
INSERT INTO geofence (id, name, description, color, max_speed, user_id, boundaries)
VALUES (4, 'test-geo15.0', 'description15.0', '#111111', 3, 4, ST_GeomFromText('POLYGON((0 4, 10 0, 10 10, 0 10, 0 4))', 4326));
INSERT INTO geofence (id, name, description, color, max_speed, user_id, boundaries)
VALUES (5, 'test-geo20.0', 'description20.0', '#111111', 4, 4, ST_GeomFromText('POLYGON((0 5, 10 0, 10 10, 0 10, 0 5))', 4326));
