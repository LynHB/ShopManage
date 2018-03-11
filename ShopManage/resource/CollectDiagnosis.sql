USE ShopManage;
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `CollectDiagnosis`;
CREATE TABLE `CollectDiagnosis` (
  `ChilderId` varchar(255) NOT NULL,
  `SymptomDescript` text NOT NULL,
  
  `HappendDate` varchar(255) NOT NULL,
  `DiagnosisResult` varchar(255) NOT NULL,
  `recoveryDate` varchar(255) NOT NULL,
  `FollowDiagnosis` int,
  `Summary` text,
  `CreateTime` varchar(255) NOT NULL,
  `UpdateTime` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;