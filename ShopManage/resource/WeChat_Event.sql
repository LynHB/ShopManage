USE ShopManage;
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `WeChat_Event`;
CREATE TABLE `WeChat_Event` (
  `EventType` varchar(255) NOT NULL,
  `EvetReply` varchar(255) NOT NULL,
  `EventCode` varchar(255),
  `ReplyType` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;