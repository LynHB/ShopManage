USE ShopManage;
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `WeChatVideoSourceMaterial`;
CREATE TABLE `WeChatVideoSourceMaterial` (
  `media_id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `update_time` varchar(255) NOT NULL,
  `url` varchar(1000) NOT NULL,
  `title` varchar(255),
  `description` varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;