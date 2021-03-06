create index IX_FAC0312F on SPYGLASS_ScienceApp (appType[$COLUMN_LENGTH:75$], status);
create index IX_DD87775C on SPYGLASS_ScienceApp (authorId);
create index IX_D60DDBE3 on SPYGLASS_ScienceApp (groupId, appType[$COLUMN_LENGTH:75$]);
create index IX_4C32A4CD on SPYGLASS_ScienceApp (groupId, runType[$COLUMN_LENGTH:75$]);
create index IX_420A956E on SPYGLASS_ScienceApp (groupId, status);
create index IX_A40C72CF on SPYGLASS_ScienceApp (groupId, userId, appType[$COLUMN_LENGTH:75$], status);
create index IX_B376B1B9 on SPYGLASS_ScienceApp (groupId, userId, runType[$COLUMN_LENGTH:75$], status);
create index IX_B2160BA8 on SPYGLASS_ScienceApp (groupId, userId, status);
create index IX_3668F887 on SPYGLASS_ScienceApp (name[$COLUMN_LENGTH:75$], version[$COLUMN_LENGTH:75$]);
create index IX_A2A7019 on SPYGLASS_ScienceApp (runType[$COLUMN_LENGTH:75$], status);
create index IX_9B60A148 on SPYGLASS_ScienceApp (status);
create index IX_DDD94C66 on SPYGLASS_ScienceApp (title[$COLUMN_LENGTH:75$]);
create index IX_5D41D64F on SPYGLASS_ScienceApp (userId, appType[$COLUMN_LENGTH:75$]);
create index IX_D3669F39 on SPYGLASS_ScienceApp (userId, runType[$COLUMN_LENGTH:75$]);
create index IX_88779D82 on SPYGLASS_ScienceApp (userId, status);
create index IX_9D16D1D6 on SPYGLASS_ScienceApp (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_2CB5D5D8 on SPYGLASS_ScienceApp (uuid_[$COLUMN_LENGTH:75$], groupId);