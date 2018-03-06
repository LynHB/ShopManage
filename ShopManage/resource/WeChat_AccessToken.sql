USE ShopManage;
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `WeChat_AccessToken`;
CREATE TABLE `WeChat_AccessToken` (
  `AccessToken` varchar(255),
  `TokenUpdateTime` varchar(255) NOT NULL,
  `ExpiresIn` varchar(255),
  `ErrCode` varchar(255),
  `ErrMsg` varchar(255),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;