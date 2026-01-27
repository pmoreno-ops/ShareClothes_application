--
-- PostgreSQL database dump
--

\restrict 57qbh5CGYkdI7DPis7N7JWqi2ee75cY0XC00fVGOmvfcOxXu5hc4xxu4TK0SyqW

-- Dumped from database version 18.0
-- Dumped by pg_dump version 18.0

-- Started on 2026-01-27 12:50:23

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 5 (class 2615 OID 17540)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 5118 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS '';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 227 (class 1259 OID 17615)
-- Name: favoritos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.favoritos (
    user_id bigint NOT NULL,
    publicacion_id bigint NOT NULL
);


ALTER TABLE public.favoritos OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 17745)
-- Name: favoritos_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.favoritos_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.favoritos_seq OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 17576)
-- Name: imagenes_producto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.imagenes_producto (
    imagen_id bigint NOT NULL,
    producto_id bigint,
    url character varying(255) NOT NULL
);


ALTER TABLE public.imagenes_producto OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 17575)
-- Name: imagenes_producto_imagen_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.imagenes_producto_imagen_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.imagenes_producto_imagen_id_seq OWNER TO postgres;

--
-- TOC entry 5120 (class 0 OID 0)
-- Dependencies: 223
-- Name: imagenes_producto_imagen_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.imagenes_producto_imagen_id_seq OWNED BY public.imagenes_producto.imagen_id;


--
-- TOC entry 229 (class 1259 OID 17633)
-- Name: mensajes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mensajes (
    mensaje_id bigint NOT NULL,
    remitente_id bigint,
    destinatario_id bigint,
    publicacion_id bigint,
    contenido character varying(255) NOT NULL,
    fecha_envio timestamp without time zone DEFAULT now(),
    leido boolean DEFAULT false
);


ALTER TABLE public.mensajes OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 17632)
-- Name: mensajes_mensaje_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.mensajes_mensaje_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.mensajes_mensaje_id_seq OWNER TO postgres;

--
-- TOC entry 5121 (class 0 OID 0)
-- Dependencies: 228
-- Name: mensajes_mensaje_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.mensajes_mensaje_id_seq OWNED BY public.mensajes.mensaje_id;


--
-- TOC entry 222 (class 1259 OID 17558)
-- Name: productos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.productos (
    producto_id bigint NOT NULL,
    user_id bigint,
    titulo character varying(255) NOT NULL,
    descripcion character varying(200),
    categoria character varying(255),
    genero character varying(255),
    estado character varying(255),
    marca character varying(255),
    talla character varying(255),
    color character varying(255),
    fecha_creacion timestamp without time zone DEFAULT now(),
    activo boolean DEFAULT true
);


ALTER TABLE public.productos OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 17557)
-- Name: productos_producto_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.productos_producto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.productos_producto_id_seq OWNER TO postgres;

--
-- TOC entry 5122 (class 0 OID 0)
-- Dependencies: 221
-- Name: productos_producto_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.productos_producto_id_seq OWNED BY public.productos.producto_id;


--
-- TOC entry 235 (class 1259 OID 17766)
-- Name: publicacion_productos; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.publicacion_productos (
    publicacion_id bigint NOT NULL,
    producto_id bigint NOT NULL
);


ALTER TABLE public.publicacion_productos OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 17592)
-- Name: publicaciones; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.publicaciones (
    publicacion_id bigint NOT NULL,
    producto_id bigint,
    usuario_id bigint,
    tipo character varying(255) NOT NULL,
    precio numeric(10,2),
    descripcion_extra character varying(255),
    disponible boolean DEFAULT true,
    fecha_publicacion timestamp without time zone DEFAULT now()
);


ALTER TABLE public.publicaciones OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 17591)
-- Name: publicaciones_publicacion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.publicaciones_publicacion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.publicaciones_publicacion_id_seq OWNER TO postgres;

--
-- TOC entry 5123 (class 0 OID 0)
-- Dependencies: 225
-- Name: publicaciones_publicacion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.publicaciones_publicacion_id_seq OWNED BY public.publicaciones.publicacion_id;


--
-- TOC entry 231 (class 1259 OID 17661)
-- Name: transacciones; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transacciones (
    transaccion_id bigint NOT NULL,
    publicacion_id bigint,
    comprador_id bigint,
    vendedor_id bigint,
    tipo character varying(255) NOT NULL,
    precio_final numeric(10,2),
    fecha_transaccion timestamp without time zone DEFAULT now()
);


ALTER TABLE public.transacciones OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 17660)
-- Name: transacciones_transaccion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.transacciones_transaccion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.transacciones_transaccion_id_seq OWNER TO postgres;

--
-- TOC entry 5124 (class 0 OID 0)
-- Dependencies: 230
-- Name: transacciones_transaccion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.transacciones_transaccion_id_seq OWNED BY public.transacciones.transaccion_id;


--
-- TOC entry 220 (class 1259 OID 17542)
-- Name: usuarios; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuarios (
    user_id bigint NOT NULL,
    nombre character varying(255) NOT NULL,
    apellido character varying(255),
    email character varying(255) NOT NULL,
    telefono character varying(255),
    password_hash character varying(255) NOT NULL,
    foto_perfil text,
    fecha_registro timestamp without time zone DEFAULT now()
);


ALTER TABLE public.usuarios OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 17541)
-- Name: usuarios_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuarios_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.usuarios_user_id_seq OWNER TO postgres;

--
-- TOC entry 5125 (class 0 OID 0)
-- Dependencies: 219
-- Name: usuarios_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuarios_user_id_seq OWNED BY public.usuarios.user_id;


--
-- TOC entry 233 (class 1259 OID 17686)
-- Name: valoraciones; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.valoraciones (
    valoracion_id bigint NOT NULL,
    evaluador_id bigint,
    evaluado_id bigint,
    transaccion_id bigint,
    puntuacion integer,
    comentario character varying(255),
    fecha timestamp without time zone DEFAULT now(),
    CONSTRAINT valoraciones_puntuacion_check CHECK (((puntuacion >= 1) AND (puntuacion <= 5)))
);


ALTER TABLE public.valoraciones OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 17685)
-- Name: valoraciones_valoracion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.valoraciones_valoracion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.valoraciones_valoracion_id_seq OWNER TO postgres;

--
-- TOC entry 5126 (class 0 OID 0)
-- Dependencies: 232
-- Name: valoraciones_valoracion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.valoraciones_valoracion_id_seq OWNED BY public.valoraciones.valoracion_id;


--
-- TOC entry 4900 (class 2604 OID 17579)
-- Name: imagenes_producto imagen_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.imagenes_producto ALTER COLUMN imagen_id SET DEFAULT nextval('public.imagenes_producto_imagen_id_seq'::regclass);


--
-- TOC entry 4904 (class 2604 OID 17636)
-- Name: mensajes mensaje_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mensajes ALTER COLUMN mensaje_id SET DEFAULT nextval('public.mensajes_mensaje_id_seq'::regclass);


--
-- TOC entry 4897 (class 2604 OID 17561)
-- Name: productos producto_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.productos ALTER COLUMN producto_id SET DEFAULT nextval('public.productos_producto_id_seq'::regclass);


--
-- TOC entry 4901 (class 2604 OID 17595)
-- Name: publicaciones publicacion_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.publicaciones ALTER COLUMN publicacion_id SET DEFAULT nextval('public.publicaciones_publicacion_id_seq'::regclass);


--
-- TOC entry 4907 (class 2604 OID 17664)
-- Name: transacciones transaccion_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transacciones ALTER COLUMN transaccion_id SET DEFAULT nextval('public.transacciones_transaccion_id_seq'::regclass);


--
-- TOC entry 4895 (class 2604 OID 17545)
-- Name: usuarios user_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios ALTER COLUMN user_id SET DEFAULT nextval('public.usuarios_user_id_seq'::regclass);


--
-- TOC entry 4909 (class 2604 OID 17689)
-- Name: valoraciones valoracion_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.valoraciones ALTER COLUMN valoracion_id SET DEFAULT nextval('public.valoraciones_valoracion_id_seq'::regclass);


--
-- TOC entry 5104 (class 0 OID 17615)
-- Dependencies: 227
-- Data for Name: favoritos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.favoritos VALUES (2, 1);
INSERT INTO public.favoritos VALUES (3, 1);
INSERT INTO public.favoritos VALUES (1, 4);
INSERT INTO public.favoritos VALUES (4, 2);


--
-- TOC entry 5101 (class 0 OID 17576)
-- Dependencies: 224
-- Data for Name: imagenes_producto; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.imagenes_producto VALUES (1, 1, 'img/chaqueta1.jpg');
INSERT INTO public.imagenes_producto VALUES (2, 1, 'img/chaqueta2.jpg');
INSERT INTO public.imagenes_producto VALUES (3, 2, 'img/zapatillas1.jpg');
INSERT INTO public.imagenes_producto VALUES (4, 3, 'img/vaquero1.jpg');
INSERT INTO public.imagenes_producto VALUES (5, 4, 'img/vestido1.jpg');
INSERT INTO public.imagenes_producto VALUES (6, 5, 'img/sudadera1.jpg');


--
-- TOC entry 5106 (class 0 OID 17633)
-- Dependencies: 229
-- Data for Name: mensajes; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.mensajes VALUES (1, 2, 1, 1, 'Hola, ¿sigue disponible la chaqueta?', '2025-12-01 13:56:08.32059', false);
INSERT INTO public.mensajes VALUES (2, 1, 2, 1, 'Sí, sigue disponible.', '2025-12-01 13:56:08.32059', false);
INSERT INTO public.mensajes VALUES (3, 3, 4, 5, 'Hola, me interesa alquilar la sudadera.', '2025-12-01 13:56:08.32059', false);
INSERT INTO public.mensajes VALUES (4, 4, 3, 5, 'Perfecto, cuando la necesitas?', '2025-12-01 13:56:08.32059', false);


--
-- TOC entry 5099 (class 0 OID 17558)
-- Dependencies: 222
-- Data for Name: productos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.productos VALUES (2, 1, 'Zapatillas running', 'Perfectas para correr.', 'Calzado', 'Unisex', 'Buen estado', 'Nike', '42', 'Blanco', '2025-12-01 13:56:00.901275', true);
INSERT INTO public.productos VALUES (3, 2, 'Pantalón vaquero', 'Vaquero clásico azul.', 'Ropa', 'Hombre', 'Usado', 'Levis', '32', 'Azul', '2025-12-01 13:56:00.901275', true);
INSERT INTO public.productos VALUES (4, 3, 'Vestido floral', 'Vestido ligero para verano.', 'Ropa', 'Mujer', 'Nuevo', 'H&M', 'S', 'Rojo', '2025-12-01 13:56:00.901275', true);
INSERT INTO public.productos VALUES (5, 4, 'Sudadera oversize', 'Sudadera cómoda estilo urbano.', 'Ropa', 'Unisex', 'Como nueva', 'Adidas', 'L', 'Gris', '2025-12-01 13:56:00.901275', true);
INSERT INTO public.productos VALUES (7, NULL, 'Camiseta', 'Camiseta de algodón', 'Ropa', 'Unisex', 'Nuevo', 'MarcaX', 'M', 'Azul', '2025-12-15 08:14:29.070181', true);
INSERT INTO public.productos VALUES (1, 1, 'Producto Nuevo', 'Descripción actualizada', NULL, NULL, NULL, NULL, NULL, NULL, '2025-12-01 13:56:00.901275', true);
INSERT INTO public.productos VALUES (8, NULL, 'Camiseta', 'Camiseta de algodón', 'Ropa', 'Unisex', 'Nuevo', 'MarcaX', 'M', 'Azul', '2025-12-15 12:48:12.918227', true);
INSERT INTO public.productos VALUES (9, NULL, 'Camiseta', 'Camiseta de algodón', 'Ropa', 'Unisex', 'Nuevo', 'MarcaX', 'M', 'Azul', '2025-12-15 12:55:24.908005', true);
INSERT INTO public.productos VALUES (10, NULL, 'Camiseta', 'Camiseta de algodón', 'Ropa', 'Unisex', 'Nuevo', 'MarcaX', 'M', 'Azul', '2025-12-16 13:17:03.547489', true);
INSERT INTO public.productos VALUES (11, NULL, 'Camiseta', 'Camiseta de algodón', 'Ropa', 'Unisex', 'Nuevo', 'MarcaX', 'M', 'Azul', '2025-12-16 16:31:54.497397', true);
INSERT INTO public.productos VALUES (12, NULL, 'fdgdfgd', NULL, 'dfdfd', NULL, NULL, 'dfgdfgdg', 'l', 'asfdas', '2025-12-16 16:35:47.443914', true);
INSERT INTO public.productos VALUES (13, NULL, 'dsvvdfvdb', NULL, 'sfgvddf', NULL, NULL, 'dfbdbf', 'l', 'sdsgd', '2025-12-16 16:38:27.398568', true);
INSERT INTO public.productos VALUES (14, NULL, 'gzfvdbf', NULL, 'sgdfg', NULL, NULL, 'sdfgdg', 'l', 'asgdfg', '2025-12-16 16:41:07.228395', true);
INSERT INTO public.productos VALUES (15, NULL, 'dfgdg', '', 'sdfsf', '', '', '', 'd', '', '2025-12-16 16:46:11.382131', true);
INSERT INTO public.productos VALUES (16, NULL, 'sdfsfd', '', 'sds', NULL, NULL, 'sdfsd', 's', NULL, '2025-12-16 16:54:22.917863', true);
INSERT INTO public.productos VALUES (17, NULL, 'tuputamadre', 'tuputadamadre', 'd', NULL, NULL, 'd', 'f', NULL, '2025-12-16 17:01:16.856865', true);


--
-- TOC entry 5112 (class 0 OID 17766)
-- Dependencies: 235
-- Data for Name: publicacion_productos; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 5103 (class 0 OID 17592)
-- Dependencies: 226
-- Data for Name: publicaciones; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.publicaciones VALUES (1, 1, NULL, 'venta', 45.00, 'Precio negociable.', true, '2025-12-01 13:56:04.561429');
INSERT INTO public.publicaciones VALUES (2, 2, NULL, 'alquiler', 5.00, 'Alquiler por día.', true, '2025-12-01 13:56:04.561429');
INSERT INTO public.publicaciones VALUES (3, 3, NULL, 'venta', 20.00, NULL, true, '2025-12-01 13:56:04.561429');
INSERT INTO public.publicaciones VALUES (4, 4, NULL, 'venta', 30.00, 'Incluye envío.', true, '2025-12-01 13:56:04.561429');
INSERT INTO public.publicaciones VALUES (5, 5, NULL, 'alquiler', 4.00, 'Disponible toda la semana.', true, '2025-12-01 13:56:04.561429');


--
-- TOC entry 5108 (class 0 OID 17661)
-- Dependencies: 231
-- Data for Name: transacciones; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 5097 (class 0 OID 17542)
-- Dependencies: 220
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.usuarios VALUES (1, 'Ana', 'Gómez', 'ana@example.com', '600111222', 'hash1', 'foto1.jpg', '2025-12-01 13:55:57.956716');
INSERT INTO public.usuarios VALUES (2, 'Luis', 'Martínez', 'luis@example.com', '600333444', 'hash2', 'foto2.jpg', '2025-12-01 13:55:57.956716');
INSERT INTO public.usuarios VALUES (3, 'Carla', 'Pérez', 'carla@example.com', '600555666', 'hash3', 'foto3.jpg', '2025-12-01 13:55:57.956716');
INSERT INTO public.usuarios VALUES (4, 'Mateo', 'Lara', 'mateo@example.com', '600777888', 'hash4', 'foto4.jpg', '2025-12-01 13:55:57.956716');
INSERT INTO public.usuarios VALUES (7, 'Juan', 'Perez', 'juan@example.com', '1234567890', '$2a$10$jY3I68LhMzhMJj0sGUt4vO6z6V/OeFz768I926Ojf2acrdhZRNHee', NULL, NULL);
INSERT INTO public.usuarios VALUES (9, 'Juanito', 'Perez', 'juan23@example.com', '1234567890', '$2a$10$YsBhHoT0eJNcBPY0uHz7lORf3zeNmiDwxcfeDKASIkXTP9t4Lq.Ia', NULL, NULL);
INSERT INTO public.usuarios VALUES (10, 'Juanillo', 'Perez', 'juan234@example.com', '1234567890', '$2a$10$NZcjpZJ7.2XPMhFuV8pmouUwLi5PlZmuy/pvv0DIBQmy44Mj3qkwq', NULL, NULL);


--
-- TOC entry 5110 (class 0 OID 17686)
-- Dependencies: 233
-- Data for Name: valoraciones; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 5127 (class 0 OID 0)
-- Dependencies: 234
-- Name: favoritos_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.favoritos_seq', 1, false);


--
-- TOC entry 5128 (class 0 OID 0)
-- Dependencies: 223
-- Name: imagenes_producto_imagen_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.imagenes_producto_imagen_id_seq', 31, true);


--
-- TOC entry 5129 (class 0 OID 0)
-- Dependencies: 228
-- Name: mensajes_mensaje_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.mensajes_mensaje_id_seq', 4, true);


--
-- TOC entry 5130 (class 0 OID 0)
-- Dependencies: 221
-- Name: productos_producto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.productos_producto_id_seq', 106, true);


--
-- TOC entry 5131 (class 0 OID 0)
-- Dependencies: 225
-- Name: publicaciones_publicacion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.publicaciones_publicacion_id_seq', 71, true);


--
-- TOC entry 5132 (class 0 OID 0)
-- Dependencies: 230
-- Name: transacciones_transaccion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transacciones_transaccion_id_seq', 127, true);


--
-- TOC entry 5133 (class 0 OID 0)
-- Dependencies: 219
-- Name: usuarios_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuarios_user_id_seq', 247, true);


--
-- TOC entry 5134 (class 0 OID 0)
-- Dependencies: 232
-- Name: valoraciones_valoracion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.valoraciones_valoracion_id_seq', 13, true);


--
-- TOC entry 4923 (class 2606 OID 17621)
-- Name: favoritos favoritos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.favoritos
    ADD CONSTRAINT favoritos_pkey PRIMARY KEY (user_id, publicacion_id);


--
-- TOC entry 4919 (class 2606 OID 17585)
-- Name: imagenes_producto imagenes_producto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.imagenes_producto
    ADD CONSTRAINT imagenes_producto_pkey PRIMARY KEY (imagen_id);


--
-- TOC entry 4925 (class 2606 OID 17644)
-- Name: mensajes mensajes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mensajes
    ADD CONSTRAINT mensajes_pkey PRIMARY KEY (mensaje_id);


--
-- TOC entry 4917 (class 2606 OID 17569)
-- Name: productos productos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.productos
    ADD CONSTRAINT productos_pkey PRIMARY KEY (producto_id);


--
-- TOC entry 4931 (class 2606 OID 17772)
-- Name: publicacion_productos publicacion_productos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.publicacion_productos
    ADD CONSTRAINT publicacion_productos_pkey PRIMARY KEY (publicacion_id, producto_id);


--
-- TOC entry 4921 (class 2606 OID 17603)
-- Name: publicaciones publicaciones_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.publicaciones
    ADD CONSTRAINT publicaciones_pkey PRIMARY KEY (publicacion_id);


--
-- TOC entry 4927 (class 2606 OID 17669)
-- Name: transacciones transacciones_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transacciones
    ADD CONSTRAINT transacciones_pkey PRIMARY KEY (transaccion_id);


--
-- TOC entry 4913 (class 2606 OID 17738)
-- Name: usuarios usuarios_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_email_key UNIQUE (email);


--
-- TOC entry 4915 (class 2606 OID 17554)
-- Name: usuarios usuarios_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios
    ADD CONSTRAINT usuarios_pkey PRIMARY KEY (user_id);


--
-- TOC entry 4929 (class 2606 OID 17696)
-- Name: valoraciones valoraciones_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.valoraciones
    ADD CONSTRAINT valoraciones_pkey PRIMARY KEY (valoracion_id);


--
-- TOC entry 4936 (class 2606 OID 17627)
-- Name: favoritos favoritos_publicacion_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.favoritos
    ADD CONSTRAINT favoritos_publicacion_id_fkey FOREIGN KEY (publicacion_id) REFERENCES public.publicaciones(publicacion_id) ON DELETE CASCADE;


--
-- TOC entry 4937 (class 2606 OID 17622)
-- Name: favoritos favoritos_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.favoritos
    ADD CONSTRAINT favoritos_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.usuarios(user_id) ON DELETE CASCADE;


--
-- TOC entry 4933 (class 2606 OID 17586)
-- Name: imagenes_producto imagenes_producto_producto_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.imagenes_producto
    ADD CONSTRAINT imagenes_producto_producto_id_fkey FOREIGN KEY (producto_id) REFERENCES public.productos(producto_id) ON DELETE CASCADE;


--
-- TOC entry 4938 (class 2606 OID 17650)
-- Name: mensajes mensajes_destinatario_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mensajes
    ADD CONSTRAINT mensajes_destinatario_id_fkey FOREIGN KEY (destinatario_id) REFERENCES public.usuarios(user_id);


--
-- TOC entry 4939 (class 2606 OID 17655)
-- Name: mensajes mensajes_publicacion_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mensajes
    ADD CONSTRAINT mensajes_publicacion_id_fkey FOREIGN KEY (publicacion_id) REFERENCES public.publicaciones(publicacion_id);


--
-- TOC entry 4940 (class 2606 OID 17645)
-- Name: mensajes mensajes_remitente_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mensajes
    ADD CONSTRAINT mensajes_remitente_id_fkey FOREIGN KEY (remitente_id) REFERENCES public.usuarios(user_id);


--
-- TOC entry 4932 (class 2606 OID 17570)
-- Name: productos productos_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.productos
    ADD CONSTRAINT productos_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.usuarios(user_id) ON DELETE CASCADE;


--
-- TOC entry 4947 (class 2606 OID 17778)
-- Name: publicacion_productos publicacion_productos_producto_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.publicacion_productos
    ADD CONSTRAINT publicacion_productos_producto_id_fkey FOREIGN KEY (producto_id) REFERENCES public.productos(producto_id) ON DELETE CASCADE;


--
-- TOC entry 4948 (class 2606 OID 17773)
-- Name: publicacion_productos publicacion_productos_publicacion_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.publicacion_productos
    ADD CONSTRAINT publicacion_productos_publicacion_id_fkey FOREIGN KEY (publicacion_id) REFERENCES public.publicaciones(publicacion_id) ON DELETE CASCADE;


--
-- TOC entry 4934 (class 2606 OID 17604)
-- Name: publicaciones publicaciones_producto_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.publicaciones
    ADD CONSTRAINT publicaciones_producto_id_fkey FOREIGN KEY (producto_id) REFERENCES public.productos(producto_id) ON DELETE CASCADE;


--
-- TOC entry 4935 (class 2606 OID 17609)
-- Name: publicaciones publicaciones_usuario_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.publicaciones
    ADD CONSTRAINT publicaciones_usuario_id_fkey FOREIGN KEY (usuario_id) REFERENCES public.usuarios(user_id) ON DELETE CASCADE;


--
-- TOC entry 4941 (class 2606 OID 17675)
-- Name: transacciones transacciones_comprador_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transacciones
    ADD CONSTRAINT transacciones_comprador_id_fkey FOREIGN KEY (comprador_id) REFERENCES public.usuarios(user_id);


--
-- TOC entry 4942 (class 2606 OID 17670)
-- Name: transacciones transacciones_publicacion_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transacciones
    ADD CONSTRAINT transacciones_publicacion_id_fkey FOREIGN KEY (publicacion_id) REFERENCES public.publicaciones(publicacion_id);


--
-- TOC entry 4943 (class 2606 OID 17680)
-- Name: transacciones transacciones_vendedor_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transacciones
    ADD CONSTRAINT transacciones_vendedor_id_fkey FOREIGN KEY (vendedor_id) REFERENCES public.usuarios(user_id);


--
-- TOC entry 4944 (class 2606 OID 17702)
-- Name: valoraciones valoraciones_evaluado_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.valoraciones
    ADD CONSTRAINT valoraciones_evaluado_id_fkey FOREIGN KEY (evaluado_id) REFERENCES public.usuarios(user_id);


--
-- TOC entry 4945 (class 2606 OID 17697)
-- Name: valoraciones valoraciones_evaluador_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.valoraciones
    ADD CONSTRAINT valoraciones_evaluador_id_fkey FOREIGN KEY (evaluador_id) REFERENCES public.usuarios(user_id);


--
-- TOC entry 4946 (class 2606 OID 17707)
-- Name: valoraciones valoraciones_transaccion_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.valoraciones
    ADD CONSTRAINT valoraciones_transaccion_id_fkey FOREIGN KEY (transaccion_id) REFERENCES public.transacciones(transaccion_id);


--
-- TOC entry 5119 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;


-- Completed on 2026-01-27 12:50:25

--
-- PostgreSQL database dump complete
--

\unrestrict 57qbh5CGYkdI7DPis7N7JWqi2ee75cY0XC00fVGOmvfcOxXu5hc4xxu4TK0SyqW

