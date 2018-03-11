USE ShopManage;
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `CollectMemberChild`;
CREATE TABLE `CollectMemberChild` (
  `ChilderId` varchar(255) PRIMARY KEY,
  `Birthday` varchar(255) NOT NULL,
  `Sex` int NOT NULL,
  `CreateTime` varchar(255) NOT NULL,
  `UpdateTime` varchar(255) NOT NULL,
  `UserId` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;