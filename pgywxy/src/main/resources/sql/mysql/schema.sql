DROP TABLE IF EXISTS `pgywxy_admin_role`;
DROP TABLE IF EXISTS `pgywxy_message`;
DROP TABLE IF EXISTS `pgywxy_admin`;
DROP TABLE IF EXISTS `pgywxy_role`;
DROP TABLE IF EXISTS `pgywxy_log`;
DROP TABLE IF EXISTS `pgywxy_member_rank`;
/*Table structure for table `pgywxy_admin` */

CREATE TABLE `pgywxy_admin` (
  `id` varchar(32) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `is_account_enabled` bit(1) NOT NULL,
  `is_account_expired` bit(1) NOT NULL,
  `is_account_locked` bit(1) NOT NULL,
  `is_credentials_expired` bit(1) NOT NULL,
  `locked_date` datetime DEFAULT NULL,
  `login_date` datetime DEFAULT NULL,
  `login_failure_count` int(11) NOT NULL,
  `login_ip` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `pgywxy_role` */

CREATE TABLE `pgywxy_role` (
  `id` varchar(32) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `authority_list_store` text,
  `description` text,
  `is_system` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `pgywxy_admin_role` */

CREATE TABLE `pgywxy_admin_role` (
  `admin_set_id` varchar(32) NOT NULL,
  `role_set_id` varchar(32) NOT NULL,
  PRIMARY KEY (`admin_set_id`,`role_set_id`),
  KEY `pgywxy_role_admin_set` (`role_set_id`),
  KEY `pgywxy_admin_role_set` (`admin_set_id`),
  CONSTRAINT `pgywxy_admin_role_set` FOREIGN KEY (`admin_set_id`) REFERENCES `pgywxy_admin` (`id`),
  CONSTRAINT `pgywxy_role_admin_set` FOREIGN KEY (`role_set_id`) REFERENCES `pgywxy_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `pgywxy_message` */

CREATE TABLE `pgywxy_message` (
  `id` varchar(32) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `content` text NOT NULL,
  `delete_status` int(11) NOT NULL,
  `is_read` bit(1) NOT NULL,
  `is_save_draftbox` bit(1) NOT NULL,
  `title` varchar(255) NOT NULL,
  `from_member_id` varchar(32) DEFAULT NULL,
  `to_member_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pgywxy_message_from_member` (`from_member_id`),
  KEY `pgywxy_message_to_member` (`to_member_id`),
  KEY `pgywxy_message_create_date` (`create_date`),
  CONSTRAINT `pgywxy_message_from_member` FOREIGN KEY (`from_member_id`) REFERENCES `pgywxy_admin` (`id`),
  CONSTRAINT `pgywxy_message_to_member` FOREIGN KEY (`to_member_id`) REFERENCES `pgywxy_admin` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `pgywxy_log` */

CREATE TABLE `pgywxy_log` (
  `id` varchar(32) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `action_class` varchar(255) NOT NULL,
  `action_method` varchar(255) NOT NULL,
  `info` text,
  `ip` varchar(255) NOT NULL,
  `operation` varchar(255) NOT NULL,
  `operator` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `pgywxy_log_create_date` (`create_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `pgywxy_member_rank` */

CREATE TABLE `pgywxy_member_rank` (
  `id` varchar(32) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `is_default` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `preferential_scale` double NOT NULL,
  `score` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `score` (`score`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
