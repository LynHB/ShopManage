USE ShopManage;
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `CollectFlowWater`;
CREATE TABLE `CollectFlowWater` (
  `FlowWaterId` varchar(255) NOT NULL,
  `CreateTime` varchar(255) NOT NULL,
  `ShopId` varchar(255) NOT NULL,
  `ShopName` varchar(255) NOT NULL,
  `Specifications` varchar(255) NOT NULL,
  `Brand` varchar(255) NOT NULL,
  `Classification` varchar(255) NOT NULL,
  `UserId` varchar(255) NOT NULL,
  `UserName` varchar(255) NOT NULL,
  `Price` varchar(255) NOT NULL,
  `Cost` varchar(255) NOT NULL,
  `ShopTransaction` varchar(255) NOT NULL,
  `ShopNumber` varchar(255) NOT NULL,
  `Integral` varchar(255) NOT NULL,
  `Turnover` varchar(255) NOT NULL,
  `DiscountType` varchar(255) NOT NULL,
  `Profit` varchar(255) NOT NULL,
  `FlowWaterType` varchar(255) NOT NULL,
  primary key  (FlowWaterId,ShopId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;