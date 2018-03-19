CREATE TABLE `seven` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bucketName` varchar(64) DEFAULT NULL,
  `path` varchar(128) DEFAULT NULL,
  `say` varchar(1024) DEFAULT NULL,
  `createTime` datetime  DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;;
