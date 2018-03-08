USE ShopManage;
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `CollectStockClassification`;
CREATE TABLE `CollectStockClassification` (
  `ClassId` int primary key AUTO_INCREMENT,
  `ClassName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;