SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `restaurante` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `restaurante` ;

-- -----------------------------------------------------
-- Table `restaurante`.`mesa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurante`.`mesa` (
  `idmesa` INT NOT NULL COMMENT 'Numero da mesa, pk, nao se repete.',
  `descricao` VARCHAR(45) NULL COMMENT 'Nome da mesa. Ex: \"Mesa 12\"',
  `status` VARCHAR(1) NULL COMMENT 'Define o estado da mesa:\nL : Livre\nO : Ocupada\n\ncoloquei varchar caso queira aumentar as possilibidades (suja) por exemplo.',
  PRIMARY KEY (`idmesa`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restaurante`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurante`.`produto` (
  `idproduto` INT NOT NULL,
  `descricao` VARCHAR(45) NULL,
  `precoVenda` DECIMAL NULL,
  PRIMARY KEY (`idproduto`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restaurante`.`vendaAndamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurante`.`vendaAndamento` (
  `data` DATETIME NOT NULL,
  `idproduto` INT NOT NULL,
  `idmesa` INT NOT NULL,
  `quantidade` DECIMAL NULL,
  `preco` DECIMAL NULL,
  INDEX `fk_itens_produto1_idx` (`idproduto` ASC),
  INDEX `fk_itens_mesa1_idx` (`idmesa` ASC),
  PRIMARY KEY (`data`),
  CONSTRAINT `fk_venda_produto`
    FOREIGN KEY (`idproduto`)
    REFERENCES `restaurante`.`produto` (`idproduto`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_venda_mesa`
    FOREIGN KEY (`idmesa`)
    REFERENCES `restaurante`.`mesa` (`idmesa`)
    ON DELETE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restaurante`.`vendaEncerrada`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurante`.`vendaEncerrada` (
  `ordemVenda` INT NOT NULL,
  `data` DATETIME NOT NULL,
  `idmesa` INT NOT NULL,
  `idproduto` INT NULL,
  `quantidade` DECIMAL NULL,
  `preco` DECIMAL NULL,
  PRIMARY KEY (`ordemVenda`, `data`, `idmesa`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
