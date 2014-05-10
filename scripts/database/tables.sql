DROP TABLE `USER` ;
CREATE TABLE `USER` (
  `ID` smallint(6) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(32) NOT NULL,
  `PASSWORD` varchar(32) NOT NULL,
  `FIRSTNAME` varchar(32) NOT NULL,
  `LASTNAME` varchar(32) DEFAULT NULL,
  `EMAIL` varchar(512) NOT NULL,
  `MOBILE` varchar(32) DEFAULT NULL,
  `IS_ACTIVE` tinyint(1) NOT NULL DEFAULT '1',
   MANAGER smallint(6) NULL,
  `DELETED` tinyint(1) NOT NULL DEFAULT '0',
   `CREATEDBY`  bigint(20) NOT NULL DEFAULT '0',
   `MODIFIEDBY`  bigint(20) NOT NULL DEFAULT '0',
   `CREATED_DATE` timestamp NOT NULL,
   `MODIFIED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
   `CREATED_DATE` timestamp NOT NULL,
   `MODIFIED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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
   `CREATED_DATE` timestamp NOT NULL,
   `MODIFIED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
  
);

DROP TABLE `ROLES` ;
CREATE TABLE `ROLES` (
  `ID` smallint(6) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(32) NOT NULL,
  `ROLE_DESC` varchar(32) NOT NULL,
  `IS_ACTIVE` tinyint(1) NOT NULL DEFAULT '1',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0',
  `CREATEDBY`  bigint(20) NOT NULL DEFAULT '0',
   `MODIFIEDBY`  bigint(20) NOT NULL DEFAULT '0',
   `CREATED_DATE` timestamp NOT NULL,
   `MODIFIED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ROLE_NAME` (`ROLE_NAME`)
);

DROP TABLE `USER_ROLES` ;
CREATE TABLE `USER_ROLES` (
  `USER_ID` smallint(6) NOT NULL,
  `ROLE_ID` smallint(6) NOT NULL,
  `IS_ACTIVE` tinyint(1) NOT NULL DEFAULT '1',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0',
  `CREATEDBY`  bigint(20) NOT NULL DEFAULT '0',
   `MODIFIEDBY`  bigint(20) NOT NULL DEFAULT '0',
   `CREATED_DATE` timestamp NOT NULL,
   `MODIFIED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

DROP TABLE `PERMISSIONS` ;
CREATE TABLE `PERMISSIONS` (
	`ID` smallint(6) NOT NULL AUTO_INCREMENT,
  `PERMISSION` varchar(32) NOT NULL,
  `IS_ACTIVE` tinyint(1) NOT NULL DEFAULT '1',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0',
  `CREATEDBY`  bigint(20) NOT NULL DEFAULT '0',
   `MODIFIEDBY`  bigint(20) NOT NULL DEFAULT '0',
   `CREATED_DATE` timestamp NOT NULL,
   `MODIFIED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`ID`),
  UNIQUE KEY `PERMISSION` (`PERMISSION`)
);

DROP TABLE `ROLES_PERMISSIONS` ;
CREATE TABLE `ROLES_PERMISSIONS` (
  `ROLE_ID` smallint(6) NOT NULL,
  `PERMISSION_ID` smallint(6) NOT NULL,
  `IS_ACTIVE` tinyint(1) NOT NULL DEFAULT '1',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0',
  `CREATEDBY`  bigint(20) NOT NULL DEFAULT '0',
   `MODIFIEDBY`  bigint(20) NOT NULL DEFAULT '0',
   `CREATED_DATE` timestamp NOT NULL,
   `MODIFIED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

DROP TABLE COURSE;
CREATE TABLE `COURSE` (
   ID bigint(20) NOT NULL AUTO_INCREMENT,
   NAME varchar(256) NOT NULL,
   DESCRIPTION text,
   CATEGORY_ID bigint(20) NOT NULL,
   ACTIVE char(1) NULL,
   KEY_WORDS varchar(256) NULL,
   CREATEDBY  bigint(20) NOT NULL,
   CREATED_DATE timestamp NOT NULL,
   MODIFIED_DATE timestamp NOT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE WORK_SHOP;
CREATE TABLE `WORK_SHOP` (
   ID bigint(20) NOT NULL AUTO_INCREMENT,
   COURSE_ID bigint(20) NOT NULL,
   CITY smallint(20) NOT NULL,
   START_DATE timestamp NOT NULL,
   END_DATE timestamp NOT NULL,
   ADDR1 varchar(100) NULL,
   ADDR2 varchar(100) NULL,
   STATUS smallint(20) NULL,
   REMARKS varchar(256) NULL,
   INVOICE_ID bigint(20) NULL,
   CREATEDBY  bigint(20) NOT NULL,
   CREATED_DATE timestamp NOT NULL,
   MODIFIED_DATE timestamp NOT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE WORK_SHOP_TRANSITION;
CREATE TABLE `WORK_SHOP_TRANSITION` (
   ID bigint(20) NOT NULL AUTO_INCREMENT,
   WORK_SHOP_ID bigint(20) NOT NULL,
   CITY smallint(20) NOT NULL,
   FSTATE smallint(20) NOT NULL,
   TSTATE smallint(20) NOT NULL,
   REMARKS varchar(256) NULL,
   CREATEDBY  bigint(20) NOT NULL,
   CREATED_DATE timestamp NOT NULL,
   MODIFIED_DATE timestamp NOT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE WORK_SHOP_REGISTRATION;
CREATE TABLE `WORK_SHOP_REGISTRATION` (
   ID bigint(20) NOT NULL AUTO_INCREMENT,
   WORK_SHOP_ID bigint(20) NOT NULL,
   FNAME varchar(100) NOT NULL,
   LNAME varchar(100) NULL,
   ADDR1 varchar(128) NULL,
   ADDR2 varchar(128) NULL,
   CITY smallint(20) NULL,
   STATUS smallint(20) NOT NULL,
   EMAIL varchar(256) NOT NULL,
   REMARKS varchar(256) NULL,
   CREATEDBY  bigint(20) NOT NULL,
   CREATED_DATE timestamp NOT NULL,
   MODIFIED_DATE timestamp NOT NULL,
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
   CREATED_DATE timestamp NOT NULL,
   MODIFIED_DATE timestamp NOT NULL,
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
   CREATED_DATE timestamp NOT NULL,
   MODIFIED_DATE timestamp NOT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE INVOICE;
CREATE TABLE `INVOICE` (
   ID bigint(20) NOT NULL AUTO_INCREMENT,
   INVOICE_NUM varchar(256) NOT NULL,
   CREATEDBY  bigint(20) NOT NULL,
   CREATED_DATE timestamp NOT NULL,
   MODIFIED_DATE timestamp NOT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE `CASE`;
CREATE TABLE `CASE` (
   ID bigint(20) NOT NULL AUTO_INCREMENT,
   CASE_TYPE varchar(128) NOT NULL,
   DESCRIPTION text,
   STATUS smallint(20) NOT NULL,
   CATEGORY smallint(20) NOT NULL,
   ACTIVE char(1) NULL,
   FNAME varchar(30) NULL,
   LNAME varchar(30) NULL,
   EMAIL varchar(100) NULL,
   COURSE_ID bigint(20) NULL,
   COUNTRY smallint(20) NULL,
   CONTACT varchar(15) NULL,
   MESSAGE varchar(256) NULL,
   CREATEDBY  bigint(20) NOT NULL,
   MODIFIED_BY  bigint(20) NOT NULL,
   CREATED_DATE timestamp NOT NULL,
   MODIFIED_DATE timestamp NOT NULL,
  PRIMARY KEY (ID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE `CASE_TRANSITION`;
CREATE TABLE `CASE_TRANSITION` (
   ID bigint(20) NOT NULL AUTO_INCREMENT,
   CASE_ID varchar(128) NOT NULL,
   FSTATE smallint(20) NOT NULL,
   TSTATE smallint(20) NOT NULL,
   REMARKS varchar(256) NOT NULL,
   CREATEDBY  bigint(20) NOT NULL,
   MODIFIED_BY  bigint(20) NOT NULL,
   CREATED_DATE timestamp NOT NULL,
   MODIFIED_DATE timestamp NOT NULL,
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
  `IS_ACTIVE` tinyint(1) NOT NULL DEFAULT '1',
  `DELETED` tinyint(1) NOT NULL DEFAULT '0',
   `CREATEDBY`  bigint(20) NOT NULL DEFAULT '0',
   `MODIFIEDBY`  bigint(20) NOT NULL DEFAULT '0',
   `CREATED_DATE` timestamp NOT NULL,
   `MODIFIED_DATE` timestamp NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `EMAIL` (`EMAIL`)
);


