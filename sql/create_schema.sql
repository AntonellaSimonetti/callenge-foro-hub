CREATE TABLE topico
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo         VARCHAR(255) NOT NULL,
    mensaje        TEXT         NOT NULL,
    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    status         VARCHAR(50),
    autor          VARCHAR(100),
    curso          VARCHAR(100)
);

CREATE TABLE user
(

    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
)