create table OSPUTIL_UTIL (
	uuid_ VARCHAR(75) null,
	utilId LONG not null primary key,
	groupId LONG,
	companyId LONG
);