CREATE TABLE CriminalCase(
id INT UNIQUE NOT NULL,
number VARCHAR(30) UNIQUE NOT NULL,
type ENUM('uncategorized', 'infraction', 'misdemeanor', 'felony'),
shortDescription VARCHAR(500) NOT NULL,
detailedDescription TEXT NOT NULL,
status ENUM('submitted', 'under_investigation', 'in_court', 'closed', 'dismissed', 'cold'),
note VARCHAR(500) NOT NULL,

evidenceId INT UNIQUE NOT NULL,
leadInvestigatorId INT UNIQUE NOT NULL,
assignedId INT UNIQUE NOT NULL,


CONSTRAINT pk_cc PRIMARY KEY (id))