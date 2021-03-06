SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
DROP DATABASE IF EXISTS restaurante;

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
  `precoVenda` DECIMAL(10,2) NULL,
  PRIMARY KEY (`idproduto`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restaurante`.`vendaAndamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurante`.`vendaAndamento` (
  `dataA` DATETIME,
  `dataB` DATETIME,
  `idproduto` INT,
  `idmesa` INT,
  `quantidade` DECIMAL(10,2) NULL,
  `preco` DECIMAL(10,2) NULL,
  PRIMARY KEY (`dataA`, `dataB`, `idmesa`, `idproduto`),
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
  `ordemVenda` INT,
  `dataA` DATETIME,
  `dataB` DATETIME,
  `idproduto` INT NOT NULL,
  `idmesa` INT NOT NULL,
  `quantidade` DECIMAL(10,2) NULL,
  `preco` DECIMAL(10,2) NULL,
  PRIMARY KEY (`ordemVenda`, dataA, `dataB`, idmesa, idproduto))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


-- -----------------------------------------------------
-- TRIGGER para colocar os dados  da tabela vendaAndamento na vendaEncerrada
-- -----------------------------------------------------
DROP TRIGGER IF EXISTS `invendaEncerrada`;
DELIMITER //
CREATE TRIGGER `invendaEncerrada` BEFORE DELETE ON `vendaAndamento`
FOR EACH ROW 
   BEGIN    
	 DECLARE idmesaV INT;
	 DECLARE dataV DATETIME;
	 SET idmesaV = OLD.idmesa;
	 SET dataV = OLD.dataB;
	 CALL ProInvendaEncerrada(dataV, idmesaV);
	 update mesa set status = 'l' where idmesa = OLd.idmesa;
   END
// DELIMITER ;

DELIMITER //

CREATE PROCEDURE ProInvendaEncerrada(IN dataP DATETIME, IN idmesaA INT)
BEGIN
  DECLARE fim INT DEFAULT 0;
  DECLARE o INT;
  DECLARE idmesaX INT;
  DECLARE dataX DATETIME;    
  DECLARE idmesaV INT;
  DECLARE dataV DATETIME;   
  DECLARE busVendaEN CURSOR FOR SELECT ordemVenda, idmesa, dataB FROM `restaurante`.`vendaEncerrada` WHERE ordemVenda = (SELECT MAX(ordemVenda) FROM `restaurante`.`vendaEncerrada`);  
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET fim = 0;
  OPEN busVendaEN;
  SET idmesaV = idmesaA;
  SET dataV = dataP;
  FETCH busVendaEN INTO o, idmesaX, dataX;
  SET fim = o;
  IF fim > 0 THEN
		  CALL ProInvendaEncerrada2(dataV, idmesaV, o, idmesaX, dataX);	   
  ELSE
  SET idmesaX = idmesaV;
  SET o = 0;
	CALL ProInvendaEncerrada2(dataV, idmesaV, o, idmesaX, dataX);
  END IF;
  CLOSE busVendaEN;
 END
// 

CREATE PROCEDURE ProInvendaEncerrada2(IN dataD DATETIME, IN idmesaD INT, In ordemMesaE INT, IN idmesVe INT, IN dataVe DATETIME)
BEGIN
  DECLARE fim2 INT DEFAULT 0;
  DECLARE ordem INT DEFAULT 0;
  DECLARE da DATETIME;
  DECLARE db DATETIME;
  DECLARE idm INT;
  DECLARE idp INT;
  DECLARE qua DECIMAL(10,2);
  DECLARE pre DECIMAL(10,2); 
  DECLARE busVenda CURSOR FOR SELECT * FROM `restaurante`.`vendaAndamento` WHERE idmesa = idmesaD;
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET fim2 = 0;
  SET fim2 = ordemMesaE + 1;
  SET ordem = ordemMesaE;
  OPEN busVenda;
--  read_loop: LOOP
	FETCH busVenda INTO da, db, idm, idp, qua, pre;	
       IF idmesVe = idmesaD AND dataVe = dataD THEN
		  INSERT INTO `restaurante`.`vendaEncerrada` VALUES (ordem, da, db, idm, idp, qua, pre);
	   elseif fim2 > 1 THEN
		  INSERT INTO `restaurante`.`vendaEncerrada` VALUES (fim2, da, db, idm, idp, qua, pre);
	   elseif ordemMesaE = 0 then
		  INSERT INTO `restaurante`.`vendaEncerrada` VALUES (1, da, db, idm, idp, qua, pre);
       END IF;	   
--  END LOOP;
  CLOSE busVenda;
 END
// 

DELIMITER ;

-- --
-- INSERT INTO mesa VALUES (1, 'MESA 1', 'o');
-- INSERT INTO mesa VALUES (2, 'MESA 2', 'o');
-- INSERT INTO produto VALUES (1, 'Protsjakasd', 123);
-- INSERT INTO produto VALUES (2, 'Prot', 123);
-- INSERT INTO produto VALUES (3, 'asasdas', 123124);
-- INSERT INTO vendaAndamento VALUES ('2013-08-30 19:05:00', '2013-08-30 19:05:00', 1, 1, 2, 123);
-- INSERT INTO vendaAndamento VALUES ('2013-08-30 19:05:06', '2013-08-30 19:05:00', 3, 1, 2, 123);
-- INSERT INTO vendaAndamento VALUES ('2013-08-30 19:05:04', '2013-08-30 19:05:00', 2, 1, 4, 123);
-- INSERT INTO vendaAndamento VALUES ('2013-08-30 19:05:05', '2013-08-30 19:05:05', 1, 2, 2, 123);