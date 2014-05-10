insert into `user` (`ID`, `USERNAME`, `PASSWORD`, `FIRSTNAME`, `LASTNAME`, `EMAIL`, `MOBILE`, `IS_ACTIVE`, `DELETED`, `CREATEDBY`, `MODIFIEDBY`, `CREATED_DATE`, `MODIFIED_DATE`) values('1','ezas','ezas','Ezas','Shaik','shaikezas@gmail.com','9741800988','1','0','0','0','2014-05-06 23:44:20','2014-05-06 23:44:17');


insert into `ROLES` (`ID`, `ROLE_NAME`, `ROLE_DESC`, `IS_ACTIVE`, `DELETED`, `CREATEDBY`, `MODIFIEDBY`, `CREATED_DATE`, `MODIFIED_DATE`) values('1','crm_admin','CRM Admin','1','0','0','0','2014-05-10 12:08:23','2014-05-10 12:08:26');
insert into `ROLES` (`ID`, `ROLE_NAME`, `ROLE_DESC`, `IS_ACTIVE`, `DELETED`, `CREATEDBY`, `MODIFIEDBY`, `CREATED_DATE`, `MODIFIED_DATE`) values('2','crm_super_user','CRM Super User','1','0','0','0',NOW(),'2014-05-10 12:11:44');
insert into `ROLES` (`ID`, `ROLE_NAME`, `ROLE_DESC`, `IS_ACTIVE`, `DELETED`, `CREATEDBY`, `MODIFIEDBY`, `CREATED_DATE`, `MODIFIED_DATE`) values('3','crm_manager','CRM Category Manager','1','0','0','0',NOW(),'2014-05-10 12:12:01');
insert into `ROLES` (`ID`, `ROLE_NAME`, `ROLE_DESC`, `IS_ACTIVE`, `DELETED`, `CREATEDBY`, `MODIFIEDBY`, `CREATED_DATE`, `MODIFIED_DATE`) values('4','crm_executive','CRM Excecutive','1','0','0','0',NOW(),'2014-05-10 12:12:16');

insert into `user_roles` (`USER_ID`, `ROLE_ID`, `IS_ACTIVE`, `DELETED`, `CREATEDBY`, `MODIFIEDBY`, `CREATED_DATE`, `MODIFIED_DATE`) values('1','1','1','0','0','0','2014-05-10 12:08:41','0000-00-00 00:00:00');


