CREATE TABLE IF NOT EXISTS `t_user`
(
    `id`       BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(20)  NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    `email`    VARCHAR(50)  NOT NULL
);