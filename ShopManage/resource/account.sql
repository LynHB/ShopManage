USE ShopManage;
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `SId` bigint(20) NOT NULL,
  `accountId` varchar(255) NOT NULL,
  `userName` varchar(255) NOT NULL,
  `userPassWord` varchar(255) NOT NULL,
  `create_time` varchar(255) NOT NULL,
  `update_time` varchar(255) DEFAULT NULL,
  `user2PassWord` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) NOT NULL,
  `sex` varchar(255) NOT NULL,
  `TypeId` varchar(255) NOT NULL,
  `Content` varchar(255) NOT NULL,
  `PhoneId` varchar(255) NOT NULL,
  `E_mail` varchar(255) NOT NULL, 
  `Authority` varchar(255) NOT NULL,
  PRIMARY KEY (`SId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;