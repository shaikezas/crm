DROP TABLE `USER` ;
CREATE TABLE `USER` (
  `ID` smallint(6) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(32) NOT NULL,
  `PASSWORD` varchar(32) NOT NULL,
  `FIRSTNAME` varchar(32) NOT NULL,
  `LASTNAME` varchar(32) DEFAULT NULL,
  `EMAIL` varchar(512) NOT NULL,
  `MOBILE` varchar(32) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL DEFAULT '1',
   MANAGER smallint(6) NULL,
  `DELETED` tinyint(1) NOT NULL DEFAULT '0',
   `CREATEDBY`  bigint(20) NOT NULL DEFAULT '0',
   `MODIFIEDBY`  bigint(20) NOT NULL DEFAULT '0',
   `MODIFIED_DATE` timestamp NOT NULL DEFAULT NOW() ON UPDATE NOW(),
   `CREATED_DATE` timestamp NOT NULL,
   PRIMARY KEY (`ID`),
  UNIQUE KEY `USERNAME` (`USERNAME`)
);

DROP TABLE `USER_REGION` ;
CREATE TABLE `USER_REGION` (
  `ID` smallint(6) NOT NULL AUTO_INCREMENT,
  `USER_ID` varchar(32) NOT NULL,
   CITY smallint(20) NULL,
   COUNTRY smallint(20) NULL,
   REGION smallint(6) NULL,
  `DELETED` tinyint(1) NOT NULL DEFAULT '0',
   `CREATEDBY`  bigint(20) NOT NULL DEFAULT '0',
   `MODIFIEDBY`  bigint(20) NOT NULL DEFAULT '0',
   `MODIFIED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   `CREATED_DATE` timestamp NOT NULL,
  PRIMARY KEY (`ID`)
  
);

DROP TABLE `USER_COURSE` ;
CREATE TABLE `USER_COURSE` (
  `ID` smallint(6) NOT NULL AUTO_INCREMENT,
  `USER_REGION_ID` varchar(32) NOT NULL,
   COURSE_ID smallint(20) NULL,
  `DELETED` tinyint(1) NOT NULL DEFAULT '0',
   `CREATEDBY`  bigint(20) NOT NULL DEFAULT '0',
   `MODIFIEDBY`  bigint(20) NOT NULL DEFAULT '0',
   `MODIFIED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   `CREATED_DATE` timestamp NOT NULL,
  PRIMARY KEY (`ID`)
  
);

DROP TABLE `ROLES` ;
CREATE TABLE `ROLES` (
  `ID` smallint(6) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(32) NOT NULL,
  `ROLE_DESC` varchar(32) NOT NULL,
  `ACTIVE` tinyint(1) NOT NULL DEFAULT '1',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0',
  `CREATEDBY`  bigint(20) NOT NULL DEFAULT '0',
   `MODIFIEDBY`  bigint(20) NOT NULL DEFAULT '0',
   `MODIFIED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   `CREATED_DATE` timestamp NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ROLE_NAME` (`ROLE_NAME`)
);

DROP TABLE `USER_ROLES` ;
CREATE TABLE `USER_ROLES` (
  `USER_ID` smallint(6) NOT NULL,
  `ROLE_ID` smallint(6) NOT NULL,
  `ACTIVE` tinyint(1) NOT NULL DEFAULT '1',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0',
  `CREATEDBY`  bigint(20) NOT NULL DEFAULT '0',
   `MODIFIEDBY`  bigint(20) NOT NULL DEFAULT '0',
   `MODIFIED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   `CREATED_DATE` timestamp NOT NULL
);

DROP TABLE `PERMISSIONS` ;
CREATE TABLE `PERMISSIONS` (
	`ID` smallint(6) NOT NULL AUTO_INCREMENT,
  `PERMISSION` varchar(32) NOT NULL,
  `ACTIVE` tinyint(1) NOT NULL DEFAULT '1',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0',
  `CREATEDBY`  bigint(20) NOT NULL DEFAULT '0',
   `MODIFIEDBY`  bigint(20) NOT NULL DEFAULT '0',
   `MODIFIED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   `CREATED_DATE` timestamp NOT NULL,
    PRIMARY KEY (`ID`),
  UNIQUE KEY `PERMISSION` (`PERMISSION`)
);

DROP TABLE `ROLES_PERMISSIONS` ;
CREATE TABLE `ROLES_PERMISSIONS` (
  `ROLE_ID` smallint(6) NOT NULL,
  `PERMISSION_ID` smallint(6) NOT NULL,
  `ACTIVE` tinyint(1) NOT NULL DEFAULT '1',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0',
  `CREATEDBY`  bigint(20) NOT NULL DEFAULT '0',
   `MODIFIEDBY`  bigint(20) NOT NULL DEFAULT '0',
   `MODIFIED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   `CREATED_DATE` timestamp NOT NULL

);

DROP TABLE COURSE;
CREATE TABLE `COURSE` (
   ID bigint(20) NOT NULL AUTO_INCREMENT,
   NAME varchar(256) NOT NULL,
   DESCRIPTION text,
   CATEGORY_ID bigint(20) NOT NULL,
   ACTIVE char(1) NULL,
   KEY_WORDS varchar(256) NULL,
   CREATEDBY  bigint(20) NOT NULL  DEFAULT '0',
   MODIFIEDBY  bigint(20) NOT NULL  DEFAULT '0',
   MODIFIED_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   CREATED_DATE timestamp NOT NULL,
   
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE WORK_SHOP;
CREATE TABLE `WORK_SHOP` (
   ID bigint(20) NOT NULL AUTO_INCREMENT,
   COURSE_ID bigint(20) NOT NULL,
   CITY smallint(20) NOT NULL,
   MODIFIED_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   START_DATE timestamp NOT NULL,
   END_DATE timestamp NOT NULL,
   ADDR1 varchar(100) NULL,
   ADDR2 varchar(100) NULL,
   STATUS smallint(20) NULL,
   PRICE smallint(10) NOT NULL,
   CURRENCY varchar(20) NOT NULL,
   REMARKS varchar(256) NULL,
   INVOICE_ID bigint(20) NULL,
   CATEGORY smallint(10) NULL,
   `DELETED` tinyint(1) NOT NULL DEFAULT '0',
   CREATEDBY  bigint(20) NOT NULL,
   CREATED_DATE timestamp NOT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE WORK_SHOP_TRANSITION;
CREATE TABLE `WORK_SHOP_TRANSITION` (
   ID bigint(20) NOT NULL AUTO_INCREMENT,
   WORK_SHOP_ID bigint(20) NOT NULL,
   FSTATE smallint(20) NOT NULL,
   TSTATE smallint(20) NOT NULL,
   REMARKS varchar(256) NULL,
   CREATEDBY  bigint(20) NOT NULL,
   MODIFIED_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   CREATED_DATE timestamp NOT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE WORK_SHOP_REGISTRATION;
CREATE TABLE `WORK_SHOP_REGISTRATION` (
   ID bigint(20) NOT NULL AUTO_INCREMENT,
   WORK_SHOP_ID bigint(20) NOT NULL,
   FNAME varchar(100) NOT NULL,
   LNAME varchar(100) NULL,
   QTY smallint(10) NOT NULL,
   PRICE smallint(10) NOT NULL,
   CURRENCY varchar(20) NOT NULL,
   TOTAL_PRICE smallint(10) NOT NULL,
   ADDR1 varchar(128) NULL,
   ADDR2 varchar(128) NULL,
   CITY smallint(20) NULL,
   STATUS smallint(20) NOT NULL,
   EMAIL varchar(256) NOT NULL,
   `DELETED` tinyint(1) NOT NULL DEFAULT '0',
   CREATEDBY  bigint(20) NOT NULL,
	MODIFIED_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   CREATED_DATE timestamp NOT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE WORK_SHOP_REG_TRANSITION;
CREATE TABLE `WORK_SHOP_REG_TRANSITION` (
   ID bigint(20) NOT NULL AUTO_INCREMENT,
   WORK_SHOP_REG_ID bigint(20) NOT NULL,
   FSTATE smallint(20) NOT NULL,
   TSTATE smallint(20) NOT NULL,
   REMARKS varchar(256) NULL,
   CREATEDBY  bigint(20) NOT NULL,
   MODIFIED_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   CREATED_DATE timestamp NOT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE WORK_SHOP_PARTICIPANTS;
CREATE TABLE `WORK_SHOP_PARTICIPANTS` (
   ID bigint(20) NOT NULL AUTO_INCREMENT,
   WORK_SHOP_ID bigint(20) NOT NULL,
   FNAME varchar(100) NOT NULL,
   LNAME varchar(100) NULL,
   ADDR1 varchar(128) NULL,
   ADDR2 varchar(128) NULL,
   CITY smallint(20) NULL,
   STATUS smallint(20) NOT NULL,
   EMAIL varchar(256) NOT NULL,
   INVOICE_ID bigint(20) NULL,
   REMARKS varchar(256) NULL,
   CREATEDBY  bigint(20) NOT NULL,
	MODIFIED_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   CREATED_DATE timestamp NOT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE INVOICE;
CREATE TABLE `INVOICE` (
   ID bigint(20) NOT NULL AUTO_INCREMENT,
   INVOICE_NUM varchar(256) NOT NULL,
   WORK_SHOP_ID bigint(20) NOT NULL,
   CREATEDBY  bigint(20) NOT NULL,
   MODIFIED_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   CREATED_DATE timestamp NOT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE `LEAD`;
CREATE TABLE `LEAD` (
   ID bigint(20) NOT NULL AUTO_INCREMENT,
   LEAD_TYPE  BIGINT(20) NOT NULL ,
   DESCRIPTION text,
   STATUS smallint(20) NOT NULL,
   ACTIVE char(1) NULL,
   FNAME varchar(30) NULL,
   LNAME varchar(30) NULL,
   EMAIL varchar(100) NULL,
   COURSE_ID bigint(20) NULL,
   COUNTRY smallint(20) NULL,
   CONTACT varchar(20) NULL,
   CREATEDBY  bigint(20) NOT NULL,
   MODIFIEDBY  bigint(20) NOT NULL  DEFAULT '0',
	MODIFIED_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   CREATED_DATE timestamp NOT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE `LEAD_TRANSITION`;
CREATE TABLE `LEAD_TRANSITION` (
   ID bigint(20) NOT NULL AUTO_INCREMENT,
   LEAD_ID varchar(128) NOT NULL,
   FSTATE smallint(20) NOT NULL,
   TSTATE smallint(20) NOT NULL,
   EXTERNAL_COMMUNICATION text,
   INTERNAL_COMMUNICATION text,
   REMARKS varchar(256) NOT NULL,
   CREATEDBY  bigint(20) NOT NULL,
   MODIFIED_BY  bigint(20) NOT NULL,
   MODIFIED_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   CREATED_DATE timestamp NOT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE `CUSTOMER` ;
CREATE TABLE `CUSTOMER` (
  `ID` smallint(6) NOT NULL AUTO_INCREMENT,
  `PASSWORD` varchar(32) NOT NULL,
  `FIRSTNAME` varchar(32) NOT NULL,
  `LASTNAME` varchar(32) DEFAULT NULL,
  `EMAIL` varchar(512) NOT NULL,
  `MOBILE` varchar(32) DEFAULT NULL,
  `ACTIVE` tinyint(1) NOT NULL DEFAULT '1',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0',
   `CREATEDBY`  bigint(20) NOT NULL DEFAULT '0',
   `MODIFIEDBY`  bigint(20) NOT NULL DEFAULT '0',
	MODIFIED_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   `CREATED_DATE` timestamp NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `EMAIL` (`EMAIL`)
);

DROP table `LEAD_TYPE`;
CREATE TABLE `LEAD_TYPE` (
   ID BIGINT(20) NOT NULL AUTO_INCREMENT,
   LEAD_TYPE VARCHAR(128) NOT NULL,
   DESCRIPTION varchar(256) NOT NULL,
   GROUP_ID BIGINT(20)  NULL ,
   ACTIVE CHAR(1) NULL,
   CREATEDBY  BIGINT(20) NOT NULL,
   MODIFIEDBY  BIGINT(20) NOT NULL,
   MODIFIED_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   CREATED_DATE TIMESTAMP NOT NULL,
  PRIMARY KEY (ID)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP table `LEAD_STATE_MASTER`;
CREATE TABLE `LEAD_STATE_MASTER` (
   ID BIGINT(20) NOT NULL AUTO_INCREMENT,
   NAME VARCHAR(128) NOT NULL,
   DESCRIPTION varchar(256) NOT NULL,
   DELETED CHAR(1) NULL,
   CREATEDBY  BIGINT(20) NOT NULL,
   MODIFIEDBY  BIGINT(20) NOT NULL,
   MODIFIED_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   CREATED_DATE TIMESTAMP NOT NULL,
  PRIMARY KEY (ID)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP table `LEAD_STATE_TRANSITION_MASTER`;
CREATE TABLE `LEAD_STATE_TRANSITION_MASTER` (
   ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  FROM_STATE BIGINT(20) NOT NULL,
  TO_STATE BIGINT(20) NOT NULL,
  COMMANDNAME varchar(128)  NULL,
   DELETED CHAR(1) NULL,
   CREATEDBY  BIGINT(20) NOT NULL,
   MODIFIEDBY  BIGINT(20) NOT NULL,
   MODIFIED_DATE timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   CREATED_DATE TIMESTAMP NOT NULL,
  PRIMARY KEY (ID)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS COUNTRY;
CREATE TABLE COUNTRY(
 ID smallint NOT NULL AUTO_INCREMENT,
 `NAME` varchar(50) NOT NULL,
 CODE varchar(5) NOT NULL,
 ISO2 varchar(5) NULL,
 ISO3 varchar(5) NULL,
 ISON varchar(5) NULL,
 INTERNET_CODE varchar(5) NULL,
 CAPITAL varchar(50) NOT NULL,
 MAP_REFERENCE varchar(50) NULL,
 NATIONALITY_SINGULAR varchar(30) NULL,
 NATIONALITY_PLURAL varchar(30) NULL,
 CURRENCY varchar(30) NULL,
 CURRENCY_CODE varchar(5) NULL,
 POPULATION bigint(10) NULL,
 TITLE varchar(50) NULL,
 `COMMENT` varchar(256) NULL,
 ACTIVE char(1) NULL,
 PRIMARY KEY(ID) 
 ) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

