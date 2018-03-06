USE ShopManage;
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `WeChatVoiceSourceMaterial`;
CREATE TABLE `WeChatVoiceSourceMaterial` (
  `media_id` varchar(255),
  `name` varchar(255) NOT NULL,
  `url` varchar(5000) NOT NULL,
  `update_time` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;