SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `pd_attachment`
-- ----------------------------
DROP TABLE IF EXISTS `father`;
CREATE TABLE `father` (
                          `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
                          `name` VARCHAR(45) NOT NULL DEFAULT '',
                          PRIMARY KEY(`id`)
) ENGINE = InnoDB;

DROP TABLE IF EXISTS `son`;
CREATE TABLE `son` (
                       `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
                       `name` VARCHAR(45) NOT NULL DEFAULT '',
                       PRIMARY KEY(`id`)
) ENGINE = InnoDB;