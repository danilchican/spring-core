-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema order_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `order_db` ;

-- -----------------------------------------------------
-- Schema order_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `order_db` DEFAULT CHARACTER SET utf8 ;
USE `order_db` ;

-- -----------------------------------------------------
-- Table `order_db`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `order_db`.`users` ;

CREATE TABLE IF NOT EXISTS `order_db`.`users` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `firstname` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NULL,
  `birthday` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `order_db`.`auditoriums`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `order_db`.`auditoriums` ;

CREATE TABLE IF NOT EXISTS `order_db`.`auditoriums` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `seats_available` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `order_db`.`seats`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `order_db`.`seats` ;

CREATE TABLE IF NOT EXISTS `order_db`.`seats` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `auditorium_id` INT UNSIGNED NOT NULL,
  `is_vip` TINYINT(1) NOT NULL DEFAULT 0,
  `number` INT NOT NULL,
  `row` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `auditorium_id_fk_idx` (`auditorium_id` ASC),
  CONSTRAINT `seats_auditorium_id_fk`
    FOREIGN KEY (`auditorium_id`)
    REFERENCES `order_db`.`auditoriums` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `order_db`.`events`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `order_db`.`events` ;

CREATE TABLE IF NOT EXISTS `order_db`.`events` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(80) NOT NULL,
  `base_price` DOUBLE UNSIGNED NOT NULL,
  `rating` ENUM('LOW', 'MID', 'HIGH') NOT NULL DEFAULT 'LOW',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `order_db`.`dates`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `order_db`.`dates` ;

CREATE TABLE IF NOT EXISTS `order_db`.`dates` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `event_id` INT UNSIGNED NOT NULL,
  `datetime` DATETIME NOT NULL,
  `auditorium_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `event_id_fk_idx` (`event_id` ASC),
  INDEX `dates_auditorium_id_fk_idx` (`auditorium_id` ASC),
  CONSTRAINT `dates_event_id_fk`
    FOREIGN KEY (`event_id`)
    REFERENCES `order_db`.`events` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `dates_auditorium_id_fk`
    FOREIGN KEY (`auditorium_id`)
    REFERENCES `order_db`.`auditoriums` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `order_db`.`tickets`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `order_db`.`tickets` ;

CREATE TABLE IF NOT EXISTS `order_db`.`tickets` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT UNSIGNED NOT NULL,
  `seat_id` INT UNSIGNED NOT NULL,
  `date_id` INT UNSIGNED NOT NULL,
  `ticket_price` DOUBLE UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `event_seat_UNIQUE` (`seat_id` ASC),
  INDEX `seat_id_idx` (`seat_id` ASC),
  INDEX `user_id_fk_idx` (`user_id` ASC),
  INDEX `tickets_dates_id_fk_idx` (`date_id` ASC),
  CONSTRAINT `tickets_seat_id_fk`
    FOREIGN KEY (`seat_id`)
    REFERENCES `order_db`.`seats` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `tickets_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `order_db`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `tickets_dates_id_fk`
    FOREIGN KEY (`date_id`)
    REFERENCES `order_db`.`dates` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
