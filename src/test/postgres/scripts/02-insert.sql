-- Insertar órdenes de prueba
INSERT INTO orders (code, description, status) VALUES
       ('ORD-2024-001', 'Orden de electrónica', 'CREATED'),
       ('ORD-2024-002', 'Orden de muebles', 'CONFIRMED'),
       ('ORD-2024-003', 'Orden especial', 'SHIPPED');

-- Insertar líneas de órdenes
INSERT INTO order_lines (order_id, model, quality, quantity)
SELECT id, 'LAPTOP-PRO', 'PREMIUM', 2 FROM orders WHERE code = 'ORD-2024-001'
UNION ALL
SELECT id, 'MOUSE-WIRELESS', 'STANDARD', 3 FROM orders WHERE code = 'ORD-2024-001'
UNION ALL
SELECT id, 'SILLA-ERGONOMICA', 'PREMIUM', 1 FROM orders WHERE code = 'ORD-2024-002'
UNION ALL
SELECT id, 'ESCRITORIO-MADERA', 'STANDARD', 1 FROM orders WHERE code = 'ORD-2024-002';
