USE ShopManage;
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `CollectStockManage`;
CREATE TABLE `CollectStockManage` (
  `Cid` varchar(255) NOT NULL,
  `Sid` bigInt(20) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `StockBalance` int(10) Not NULL,
  `StockSell` int(10) Not NULL,
  `AverageCost` varchar(255) NOT NULL,
  `MarketingPrice` varchar(255) NOT NULL,
  `Classification` varchar(255) NOT NULL,
  `Specifications` varchar(255) NOT NULL,
  `Brand` varchar(255) NOT NULL,
  `Detail` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;