DROP SCHEMA `spring`;

CREATE SCHEMA IF NOT EXISTS `spring` DEFAULT CHARACTER SET utf8;

CREATE TABLE IF NOT EXISTS `spring`.`user`
(
    `id`        INTEGER AUTO_INCREMENT,
    `username`  VARCHAR(255),
    `password`  VARCHAR(255),
    `algorithm` ENUM ('BCRYPT', 'SCRYPT') NOT NULL DEFAULT 'BCRYPT',
    PRIMARY KEY (`id`),
    INDEX `INDEX_USER_AUTH_USER_GROUP` (`username` ASC) VISIBLE
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `spring`.`role`
(
    `id`      INTEGER AUTO_INCREMENT,
    `user_id` INTEGER      NOT NULL,
    `role`    VARCHAR(128) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_USER_AUTH_USER_GROUP`
        FOREIGN KEY (`user_id`)
            REFERENCES `spring`.`user` (`id`)
            ON delete NO ACTION
            ON update NO ACTION,
    UNIQUE (`user_id`, `role`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `spring`.`post`
(
    `id`           INTEGER AUTO_INCREMENT,
    `content`      VARCHAR(255),
    `manufacturer` VARCHAR(255),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;