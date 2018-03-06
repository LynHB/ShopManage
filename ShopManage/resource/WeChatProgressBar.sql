USE ShopManage;
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `WeChatProgressBar`;
CREATE TABLE `WeChatProgressBar` (
  `ProgressBarName` varchar(255) NOT NULL,
  `ProgressDescribe` varchar(1000),
  `ProgressValue` varchar(255),
  `ProgressLock` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO WeChatProgressBar(ProgressBarName,ProgressDescribe,ProgressValue,ProgressLock) VALUES("WeChatSourceMaterialSync","","","0");
INSERT INTO WeChatProgressBar(ProgressBarName,ProgressDescribe,ProgressValue,ProgressLock) VALUES("WeChatNewsSourceMaterialSync","","","0");
INSERT INTO WeChatProgressBar(ProgressBarName,ProgressDescribe,ProgressValue,ProgressLock) VALUES("WeChatImageSourceMaterialSync","","","0");
INSERT INTO WeChatProgressBar(ProgressBarName,ProgressDescribe,ProgressValue,ProgressLock) VALUES("WeChatVoiceSourceMaterialSync","","","0");
INSERT INTO WeChatProgressBar(ProgressBarName,ProgressDescribe,ProgressValue,ProgressLock) VALUES("WeChatVideoSourceMaterialSync","","","0");
