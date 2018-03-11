USE ShopManage;
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `CollectMemberManage`;
CREATE TABLE `CollectMemberManage` (
  `UserId` varchar(255) PRIMARY KEY,
  `UserName` varchar(255) NOT NULL,
  `UserChildId` varchar(255) NOT NULL,
  `Address` varchar(255),
  `Integral` int NOT NULL,
  `CreateTime` varchar(255) NOT NULL,
  `UpdateTime` varchar(255) NOT NULL,
  `Detail` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;