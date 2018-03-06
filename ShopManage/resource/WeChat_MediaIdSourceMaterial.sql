USE ShopManage;
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `WeChat_MediaIdSourceMaterial`;
CREATE TABLE `WeChat_MediaIdSourceMaterial` (
  `media_id` varchar(255),
  `type` varchar(255) NOT NULL,
  `media_number` varchar(255) NOT NULL,
  `create_time` varchar(255) NOT NULL,
  `update_time` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;