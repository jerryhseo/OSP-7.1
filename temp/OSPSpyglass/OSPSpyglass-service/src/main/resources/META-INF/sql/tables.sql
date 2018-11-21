create table SPYGLASS_InputPorts (
	scienceAppId LONG not null primary key,
	inputPorts VARCHAR(75) null
);

create table SPYGLASS_Layout (
	scienceAppId LONG not null primary key,
	layout VARCHAR(75) null
);

create table SPYGLASS_LogPorts (
	scienceAppId LONG not null primary key,
	logPorts VARCHAR(75) null
);

create table SPYGLASS_OutputPorts (
	scienceAppId LONG not null primary key,
	outputPorts VARCHAR(75) null
);

create table SPYGLASS_ScienceApp (
	uuid_ VARCHAR(75) null,
	scienceAppId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	version VARCHAR(75) null,
	title STRING null,
	descriptionId STRING null,
	previousVersionId LONG,
	iconId STRING null,
	manualId STRING null,
	exeFileName VARCHAR(75) null,
	appType VARCHAR(75) null,
	runType VARCHAR(75) null,
	authorId LONG,
	stage VARCHAR(75) null,
	status INTEGER,
	parallelModule VARCHAR(75) null,
	openLevel VARCHAR(75) null,
	license VARCHAR(75) null,
	srcFileName VARCHAR(75) null,
	languages VARCHAR(75) null
);