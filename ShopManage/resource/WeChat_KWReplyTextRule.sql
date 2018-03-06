USE ShopManage;
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `WeChat_KWReplyTextRule`;
CREATE TABLE `WeChat_KWReplyTextRule` (
  `ruleName` varchar(255) NOT NULL,
  `matchWord` varchar(255) NOT NULL,
  `replyContent` varchar(255) NOT NULL,
  `matchRule` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;