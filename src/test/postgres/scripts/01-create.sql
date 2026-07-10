-- Tabla de Órdenes
CREATE TABLE orders (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    code VARCHAR(50) UNIQUE NOT NULL,
    description VARCHAR(255),
    status VARCHAR(20) NOT NULL DEFAULT 'CREATED',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT valid_status CHECK (status IN ('CREATED', 'CONFIRMED', 'CANCELLED', 'SHIPPED', 'DELIVERED'))
);

-- Tabla de Líneas de Orden
CREATE TABLE order_lines (
         id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
         order_id UUID NOT NULL REFERENCES orders(id) ON DELETE CASCADE,
         model VARCHAR(100) NOT NULL,
         quality VARCHAR(50) NOT NULL,
         quantity INT NOT NULL CHECK (quantity > 0),
         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
         CONSTRAINT valid_quality CHECK (quality IN ('PREMIUM', 'STANDARD', 'ECONOMY'))
);

-- Índices para mejorar búsquedas
CREATE INDEX idx_orders_code ON orders(code);
CREATE INDEX idx_orders_status ON orders(status);
CREATE INDEX idx_orders_created_at ON orders(created_at);
CREATE INDEX idx_order_lines_order_id ON order_lines(order_id);

-- Tabla de auditoría (opcional)
CREATE TABLE orders_audit (
      id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
      order_id UUID NOT NULL REFERENCES orders(id) ON DELETE CASCADE,
      action VARCHAR(20) NOT NULL,
      old_status VARCHAR(20),
      new_status VARCHAR(20),
      changed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      changed_by VARCHAR(100)
);

-- Vista para órdenes con contador de líneas
CREATE VIEW orders_summary AS
SELECT
    o.id,
    o.code,
    o.description,
    o.status,
    COUNT(ol.id) as total_items,
    SUM(ol.quantity) as total_quantity,
    o.created_at,
    o.updated_at
FROM orders o
         LEFT JOIN order_lines ol ON o.id = ol.order_id
GROUP BY o.id, o.code, o.description, o.status, o.created_at, o.updated_at;