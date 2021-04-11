CREATE TABLE IF NOT EXISTS `t_user`
(
    `id`       BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(20)  NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    `email`    VARCHAR(50)  NOT NULL
);

INSERT INTO `t_user` (`username`, `password`, `email`)
VALUES ('Tom', '123@Ab789', 'test-tom@test.hk');