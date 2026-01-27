CREATE DATABASE share_clothes;
USE share_clothes;


CREATE TABLE usuarios (
                          user_id SERIAL PRIMARY KEY,
                          nombre VARCHAR(100) NOT NULL,
                          apellido VARCHAR(100),
                          email VARCHAR(150) UNIQUE NOT NULL,
                          telefono VARCHAR(30),
                          password_hash TEXT NOT NULL,
                          foto_perfil TEXT,
                          fecha_registro TIMESTAMP DEFAULT NOW()
);


CREATE TABLE productos (
                           producto_id SERIAL PRIMARY KEY,
                           user_id INT REFERENCES usuarios(user_id),
                           titulo VARCHAR(150) NOT NULL,
                           descripcion TEXT,
                           categoria VARCHAR(100),
                           genero VARCHAR(20),
                           estado VARCHAR(50),
                           marca VARCHAR(100),
                           talla VARCHAR(20),
                           color VARCHAR(50),
                           fecha_creacion TIMESTAMP DEFAULT NOW(),
                           activo BOOLEAN DEFAULT TRUE
);


CREATE TABLE imagenes_producto (
                                   imagen_id SERIAL PRIMARY KEY,
                                   producto_id INT REFERENCES productos(producto_id) ON DELETE CASCADE,
                                   url TEXT NOT NULL
);


CREATE TABLE publicaciones (
                               publicacion_id SERIAL PRIMARY KEY,
                               producto_id INT REFERENCES productos(producto_id) ON DELETE CASCADE,
                               tipo VARCHAR(20) NOT NULL,
                               precio NUMERIC(10,2),
                               descripcion_extra TEXT,
                               disponible BOOLEAN DEFAULT TRUE,
                               fecha_publicacion TIMESTAMP DEFAULT NOW()
);


CREATE TABLE favoritos (
                           user_id INT REFERENCES usuarios(user_id) ON DELETE CASCADE,
                           publicacion_id INT REFERENCES publicaciones(publicacion_id) ON DELETE CASCADE,
                           PRIMARY KEY (user_id, publicacion_id)
);


CREATE TABLE mensajes (
                          mensaje_id SERIAL PRIMARY KEY,
                          remitente_id INT REFERENCES usuarios(user_id),
                          destinatario_id INT REFERENCES usuarios(user_id),
                          publicacion_id INT REFERENCES publicaciones(publicacion_id),
                          contenido TEXT NOT NULL,
                          fecha_envio TIMESTAMP DEFAULT NOW(),
                          leido BOOLEAN DEFAULT FALSE
);


CREATE TABLE transacciones (
                               transaccion_id SERIAL PRIMARY KEY,
                               publicacion_id INT REFERENCES publicaciones(publicacion_id),
                               comprador_id INT REFERENCES usuarios(user_id),
                               vendedor_id INT REFERENCES usuarios(user_id),
                               tipo VARCHAR(20) NOT NULL,
                               precio_final NUMERIC(10,2),
                               fecha_transaccion TIMESTAMP DEFAULT NOW()
);


CREATE TABLE valoraciones (
                              valoracion_id SERIAL PRIMARY KEY,
                              evaluador_id INT REFERENCES usuarios(user_id),
                              evaluado_id INT REFERENCES usuarios(user_id),
                              transaccion_id INT REFERENCES transacciones(transaccion_id),
                              puntuacion INT CHECK (puntuacion BETWEEN 1 AND 5),
                              comentario TEXT,
                              fecha TIMESTAMP DEFAULT NOW()
);


DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
drop database share_clothes;




INSERT INTO usuarios (nombre, apellido, email, telefono, password_hash, foto_perfil)
VALUES
    ('Ana', 'Gómez', 'ana@example.com', '600111222', 'hash1', 'foto1.jpg'),
    ('Luis', 'Martínez', 'luis@example.com', '600333444', 'hash2', 'foto2.jpg'),
    ('Carla', 'Pérez', 'carla@example.com', '600555666', 'hash3', 'foto3.jpg'),
    ('Mateo', 'Lara', 'mateo@example.com', '600777888', 'hash4', 'foto4.jpg');


INSERT INTO productos (user_id, titulo, descripcion, categoria, genero, estado, marca, talla, color)
VALUES
    (1, 'Chaqueta de cuero', 'Chaqueta poco usada, muy cómoda.', 'Ropa', 'Mujer', 'Como nueva', 'Zara', 'M', 'Negro'),
    (1, 'Zapatillas running', 'Perfectas para correr.', 'Calzado', 'Unisex', 'Buen estado', 'Nike', '42', 'Blanco'),
    (2, 'Pantalón vaquero', 'Vaquero clásico azul.', 'Ropa', 'Hombre', 'Usado', 'Levis', '32', 'Azul'),
    (3, 'Vestido floral', 'Vestido ligero para verano.', 'Ropa', 'Mujer', 'Nuevo', 'H&M', 'S', 'Rojo'),
    (4, 'Sudadera oversize', 'Sudadera cómoda estilo urbano.', 'Ropa', 'Unisex', 'Como nueva', 'Adidas', 'L', 'Gris');


INSERT INTO imagenes_producto (producto_id, url)
VALUES
    (1, 'img/chaqueta1.jpg'),
    (1, 'img/chaqueta2.jpg'),
    (2, 'img/zapatillas1.jpg'),
    (3, 'img/vaquero1.jpg'),
    (4, 'img/vestido1.jpg'),
    (5, 'img/sudadera1.jpg');


INSERT INTO publicaciones (producto_id, tipo, precio, descripcion_extra)
VALUES
    (1, 'venta', 45.00, 'Precio negociable.'),
    (2, 'alquiler', 5.00, 'Alquiler por día.'),
    (3, 'venta', 20.00, NULL),
    (4, 'venta', 30.00, 'Incluye envío.'),
    (5, 'alquiler', 4.00, 'Disponible toda la semana.');


INSERT INTO favoritos (user_id, publicacion_id)
VALUES
    (2, 1),
    (3, 1),
    (1, 4),
    (4, 2);


INSERT INTO mensajes (remitente_id, destinatario_id, publicacion_id, contenido)
VALUES
    (2, 1, 1, 'Hola, ¿sigue disponible la chaqueta?'),
    (1, 2, 1, 'Sí, sigue disponible.'),
    (3, 4, 5, 'Hola, me interesa alquilar la sudadera.'),
    (4, 3, 5, 'Perfecto, cuando la necesitas?');


INSERT INTO transacciones (publicacion_id, comprador_id, vendedor_id, tipo, precio_final)
VALUES
    (1, 2, 1, 'venta', 40.00),
    (3, 1, 2, 'venta', 18.00),
    (4, 2, 3, 'venta', 30.00);


INSERT INTO valoraciones (evaluador_id, evaluado_id, transaccion_id, puntuacion, comentario)
VALUES
    (2, 1, 1, 5, 'Muy buen trato, producto perfecto.'),
    (1, 2, 2, 4, 'Todo bien, algo de retraso en el envío.'),
    (2, 3, 3, 5, 'Vendedor muy amable.');

