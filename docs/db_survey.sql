-- --------------------------------------------------------
-- Hôte :                        127.0.0.1
-- Version du serveur:           5.7.16 - MySQL Community Server (GPL)
-- SE du serveur:                Win64
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Export de la structure de la base pour db_survey
DROP DATABASE IF EXISTS `db_survey`;
CREATE DATABASE IF NOT EXISTS `db_survey` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `db_survey`;


-- Export de la structure de table db_survey. t_question
DROP TABLE IF EXISTS `t_question`;
CREATE TABLE IF NOT EXISTS `t_question` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `text` varchar(160) NOT NULL,
  `type` int(11) unsigned NOT NULL,
  `CREATED_AT` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIED_AT` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_question_type` (`type`),
  CONSTRAINT `FK_question_type` FOREIGN KEY (`type`) REFERENCES `t_question_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Export de données de la table db_survey.t_question : ~3 rows (environ)
/*!40000 ALTER TABLE `t_question` DISABLE KEYS */;
INSERT INTO `t_question` (`id`, `text`, `type`, `CREATED_AT`, `MODIFIED_AT`) VALUES
	(1, 'Simple question', 1, '2016-12-20 10:34:48', NULL),
	(2, 'Choix simple', 2, '2016-12-20 10:35:04', NULL),
	(3, 'Choix multiple', 3, '2016-12-20 10:35:17', NULL);
/*!40000 ALTER TABLE `t_question` ENABLE KEYS */;


-- Export de la structure de table db_survey. t_question_choice
DROP TABLE IF EXISTS `t_question_choice`;
CREATE TABLE IF NOT EXISTS `t_question_choice` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_question` int(10) unsigned NOT NULL,
  `value` varchar(50) NOT NULL,
  `CREATED_AT` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIED_AT` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_qc_question` (`id_question`),
  CONSTRAINT `FK_qc_question` FOREIGN KEY (`id_question`) REFERENCES `t_question` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Export de données de la table db_survey.t_question_choice : ~6 rows (environ)
/*!40000 ALTER TABLE `t_question_choice` DISABLE KEYS */;
INSERT INTO `t_question_choice` (`id`, `id_question`, `value`, `CREATED_AT`, `MODIFIED_AT`) VALUES
	(1, 2, 'Q2 - Choix 1', '2016-12-20 10:35:53', '2016-12-20 10:36:12'),
	(2, 2, 'Q2 - Choix 2', '2016-12-20 10:36:03', NULL),
	(3, 2, 'Q2 - Choix 3', '2016-12-20 10:36:24', NULL),
	(4, 3, 'Q3 - Choix 1', '2016-12-20 10:36:36', NULL),
	(5, 3, 'Q3 - Choix 2', '2016-12-20 10:36:41', '2016-12-20 10:36:49'),
	(6, 3, 'Q3 - Choix 3', '2016-12-20 10:36:57', NULL);
/*!40000 ALTER TABLE `t_question_choice` ENABLE KEYS */;


-- Export de la structure de table db_survey. t_question_type
DROP TABLE IF EXISTS `t_question_type`;
CREATE TABLE IF NOT EXISTS `t_question_type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `label` varchar(50) NOT NULL,
  `have_choices` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `allow_multiple` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `CREATED_AT` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIED_AT` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `label` (`label`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Export de données de la table db_survey.t_question_type : ~3 rows (environ)
/*!40000 ALTER TABLE `t_question_type` DISABLE KEYS */;
INSERT INTO `t_question_type` (`id`, `label`, `have_choices`, `allow_multiple`, `CREATED_AT`, `MODIFIED_AT`) VALUES
	(1, 'SIMPLE', 0, 0, '2016-12-20 10:33:09', NULL),
	(2, 'RADIO', 1, 0, '2016-12-20 10:33:54', '2016-12-20 10:33:55'),
	(3, 'CHECKBOX', 1, 1, '2016-12-20 10:34:06', NULL);
/*!40000 ALTER TABLE `t_question_type` ENABLE KEYS */;


-- Export de la structure de table db_survey. t_survey
DROP TABLE IF EXISTS `t_survey`;
CREATE TABLE IF NOT EXISTS `t_survey` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `label` varchar(50) NOT NULL,
  `short_desc` varchar(500) DEFAULT NULL,
  `long_desc` text,
  `date` date NOT NULL,
  `creator` int(10) unsigned DEFAULT '0',
  `CREATED_AT` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIED_AT` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_survey_creator` (`creator`),
  CONSTRAINT `FK_survey_creator` FOREIGN KEY (`creator`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Export de données de la table db_survey.t_survey : ~1 rows (environ)
/*!40000 ALTER TABLE `t_survey` DISABLE KEYS */;
INSERT INTO `t_survey` (`id`, `label`, `short_desc`, `long_desc`, `date`, `creator`, `CREATED_AT`, `MODIFIED_AT`) VALUES
	(1, 'Test Survey', NULL, NULL, '2016-12-20', 1, '2016-12-20 10:37:29', '2016-12-20 10:39:57');
/*!40000 ALTER TABLE `t_survey` ENABLE KEYS */;


-- Export de la structure de table db_survey. t_survey_question
DROP TABLE IF EXISTS `t_survey_question`;
CREATE TABLE IF NOT EXISTS `t_survey_question` (
  `id_survey` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_question` int(10) unsigned NOT NULL,
  `enabled` tinyint(3) unsigned NOT NULL DEFAULT '1',
  `CREATED_AT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIED_AT` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_survey`,`id_question`),
  KEY `FK_sq_question` (`id_question`),
  CONSTRAINT `FK_sq_question` FOREIGN KEY (`id_question`) REFERENCES `t_question` (`id`),
  CONSTRAINT `FK_sq_survey` FOREIGN KEY (`id_survey`) REFERENCES `t_survey` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COMMENT='Store association between surveys and questions.\r\nBL: \r\neach surveys has 0-n questions; \r\neach questions may be in multiple surveys; \r\nthere should be no redundant question in each survey';

-- Export de données de la table db_survey.t_survey_question : ~3 rows (environ)
/*!40000 ALTER TABLE `t_survey_question` DISABLE KEYS */;
INSERT INTO `t_survey_question` (`id_survey`, `id_question`, `enabled`, `CREATED_AT`, `MODIFIED_AT`) VALUES
	(1, 1, 1, '2016-12-20 10:37:49', NULL),
	(1, 2, 1, '2016-12-20 10:37:59', NULL),
	(1, 3, 1, '2016-12-20 10:38:03', NULL);
/*!40000 ALTER TABLE `t_survey_question` ENABLE KEYS */;


-- Export de la structure de table db_survey. t_survey_response
DROP TABLE IF EXISTS `t_survey_response`;
CREATE TABLE IF NOT EXISTS `t_survey_response` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `survey` int(10) unsigned NOT NULL,
  `user_answering` int(10) unsigned NOT NULL,
  `comment` text,
  `CREATED_AT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIED_AT` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_response_survey` (`survey`),
  KEY `FK_response_user` (`user_answering`),
  CONSTRAINT `FK_response_survey` FOREIGN KEY (`survey`) REFERENCES `t_survey` (`id`),
  CONSTRAINT `FK_response_user` FOREIGN KEY (`user_answering`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Export de données de la table db_survey.t_survey_response : ~0 rows (environ)
/*!40000 ALTER TABLE `t_survey_response` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_survey_response` ENABLE KEYS */;


-- Export de la structure de table db_survey. t_survey_response_data
DROP TABLE IF EXISTS `t_survey_response_data`;
CREATE TABLE IF NOT EXISTS `t_survey_response_data` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `survey_response` int(10) unsigned NOT NULL,
  `survey_question` int(10) unsigned NOT NULL,
  `answer` varchar(100) DEFAULT NULL,
  `choice` int(10) unsigned DEFAULT NULL,
  `CREATED_AT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIED_AT` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_response_choice` (`choice`),
  KEY `FK_srd_response` (`survey_response`),
  KEY `FK_srd_question` (`survey_question`),
  CONSTRAINT `FK_srd_choice` FOREIGN KEY (`choice`) REFERENCES `t_question_choice` (`id`),
  CONSTRAINT `FK_srd_question` FOREIGN KEY (`survey_question`) REFERENCES `t_question` (`id`),
  CONSTRAINT `FK_srd_response` FOREIGN KEY (`survey_response`) REFERENCES `t_survey_response` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- Export de données de la table db_survey.t_survey_response_data : ~0 rows (environ)
/*!40000 ALTER TABLE `t_survey_response_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_survey_response_data` ENABLE KEYS */;


-- Export de la structure de table db_survey. t_user
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE IF NOT EXISTS `t_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `label` varchar(50) NOT NULL,
  `login` varchar(50) NOT NULL,
  `password` varchar(64) NOT NULL,
  `plain_pass` varchar(50) NOT NULL,
  `role` int(10) unsigned NOT NULL DEFAULT '1',
  `enabled` tinyint(3) unsigned NOT NULL DEFAULT '1',
  `CREATED_AT` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIED_AT` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`),
  KEY `FK_user_role` (`role`),
  CONSTRAINT `FK_user_role` FOREIGN KEY (`role`) REFERENCES `t_user_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Export de données de la table db_survey.t_user : ~2 rows (environ)
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` (`id`, `label`, `login`, `password`, `plain_pass`, `role`, `enabled`, `CREATED_AT`, `MODIFIED_AT`) VALUES
	(1, 'SuperAdmin', 'root', 'ce5ca673d13b36118d54a7cf13aeb0ca012383bf771e713421b4d1fd841f539a', 'toor', 1, 1, '2016-12-20 10:31:41', '2016-12-20 11:27:27'),
	(2, 'Simple User', 'user', '04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb', 'user', 4, 1, '2016-12-20 10:32:17', '2016-12-20 11:27:39');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;


-- Export de la structure de table db_survey. t_user_role
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE IF NOT EXISTS `t_user_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `label` varchar(50) NOT NULL,
  `allow_survey_crud` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `allow_question_crud` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `allow_user_crud` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `CREATED_AT` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIED_AT` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `label` (`label`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Export de données de la table db_survey.t_user_role : ~4 rows (environ)
/*!40000 ALTER TABLE `t_user_role` DISABLE KEYS */;
INSERT INTO `t_user_role` (`id`, `label`, `allow_survey_crud`, `allow_question_crud`, `allow_user_crud`, `CREATED_AT`, `MODIFIED_AT`) VALUES
	(1, 'SUPERADMIN', 1, 1, 1, '2016-12-20 10:29:41', NULL),
	(2, 'SURVEY MANAGER', 1, 1, 0, '2016-12-20 10:30:03', NULL),
	(3, 'USER MANAGER', 0, 0, 1, '2016-12-20 10:30:12', NULL),
	(4, 'END USER', 0, 0, 0, '2016-12-20 10:30:54', NULL);
/*!40000 ALTER TABLE `t_user_role` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
