USE ShopManage;
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `WeChat_ArtileSourceMaterial`;
CREATE TABLE `WeChat_ArtileSourceMaterial` (
  `media_id` varchar(255),
  `title` varchar(255) NOT NULL,
  `thumb_media_id` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `digest` varchar(255) NOT NULL,
  `show_cover_pic` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `content_source_url` varchar(255) NOT NULL,
  `need_open_comment` varchar(255) NOT NULL,
  `only_fans_can_comment` varchar(255) NOT NULL,
  `url` varchar(5000) NOT NULL,
  `thumb_url` varchar(5000) NOT NULL,
  `order` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;