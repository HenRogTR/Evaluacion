-- MySQL Workbench Synchronization
-- Generated: 2016-02-06 23:36
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Henrri

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

ALTER TABLE `evaluacion`.`semestre` 
DROP FOREIGN KEY `fk_semestre_encuesta1`;

ALTER TABLE `evaluacion`.`curso` 
DROP FOREIGN KEY `fk_curso_encuesta_detalle1`;

ALTER TABLE `evaluacion`.`facultad` 
ADD COLUMN `abreviatura` VARCHAR(10) NULL DEFAULT NULL AFTER `nombre_facultad`,
ADD COLUMN `descripcion` VARCHAR(100) NULL DEFAULT NULL AFTER `abreviatura`;

ALTER TABLE `evaluacion`.`escuela` 
ADD COLUMN `descripcion` VARCHAR(10) NULL DEFAULT NULL AFTER `nombre_escuela`,
ADD COLUMN `abreviatura` VARCHAR(10) NULL DEFAULT NULL AFTER `descripcion`;

ALTER TABLE `evaluacion`.`semestre` 
DROP COLUMN `encuesta_id_encuesta`,
CHANGE COLUMN `nombre_semestre` `nombre_semestre` VARCHAR(45) NOT NULL ,
ADD COLUMN `anio` VARCHAR(5) NOT NULL AFTER `nombre_semestre`,
ADD COLUMN `periodo` VARCHAR(5) NOT NULL AFTER `anio`,
ADD COLUMN `descripcion` VARCHAR(50) NULL DEFAULT NULL AFTER `fecha_fin`,
DROP INDEX `fk_semestre_encuesta1_idx` ;

ALTER TABLE `evaluacion`.`curso` 
DROP COLUMN `encuesta_detalle_id_encuesta_detalle`,
DROP INDEX `fk_curso_encuesta_detalle1_idx` ;

ALTER TABLE `evaluacion`.`encuesta` 
ADD COLUMN `semestre_id_semestre` INT(11) NOT NULL AFTER `id_encuesta`,
ADD INDEX `fk_encuesta_semestre1_idx` (`semestre_id_semestre` ASC);

ALTER TABLE `evaluacion`.`encuesta_detalle` 
ADD COLUMN `curso_id_curso` INT(11) NOT NULL AFTER `escuela_id_escuela`,
ADD INDEX `fk_encuesta_detalle_curso1_idx` (`curso_id_curso` ASC);

ALTER TABLE `evaluacion`.`encuesta` 
ADD CONSTRAINT `fk_encuesta_semestre1`
  FOREIGN KEY (`semestre_id_semestre`)
  REFERENCES `evaluacion`.`semestre` (`id_semestre`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `evaluacion`.`encuesta_detalle` 
ADD CONSTRAINT `fk_encuesta_detalle_curso1`
  FOREIGN KEY (`curso_id_curso`)
  REFERENCES `evaluacion`.`curso` (`id_curso`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
