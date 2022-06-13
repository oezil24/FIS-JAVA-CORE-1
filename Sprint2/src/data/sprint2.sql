CREATE TABLE Detective (

detectiveId INT,
version INT NOT NULL,
createdAt DATETIME NOT NULL,
modifiedAt DATETIME NOT NULL,

username VARCHAR(100) UNIQUE NOT NULL,
firstName VARCHAR(100) NOT NULL,
lastName VARCHAR (100) NOT NULL,
pw VARCHAR(100) NOT NULL,
hiringDate DATETIME NOT NULL,
badgeNumber VARCHAR(50) UNIQUE NOT NULL,
rankOfDetective ENUM('TRAINEE', 'JUNIOR', 'SENIOR', 'INSPECTOR', 'CHIEF_INSPECTOR') NOT NULL,
armed TINYINT(1) NOT NULL,
stt ENUM ('ACTIVE', 'SUSPENDED', 'VACATION', 'UNDER_INVESTIGATION', 'RETIRED') NOT NULL,

criminalCaseId INT NOT NULL,
trackEntryId INT NOT NULL,

CONSTRAINT pk_d PRIMARY KEY (detectiveId, criminalCaseId, trackEntryId)
);
CREATE TABLE CriminalCase(

criminalCaseId INT,
version INT NOT NULL,
createdAt DATETIME NOT NULL,
modifiedAt DATETIME NOT NULL,

number VARCHAR(100) UNIQUE NOT NULL,
type ENUM('UNCATEGORIZED', 'INFRACTION', 'MISDEMEANOR', 'FELONY'),
shortDescription VARCHAR(500) NOT NULL,
detailedDescription TEXT NOT NULL,
status ENUM('SUBMITTED', 'UNDER_INVESTIGATION', 'IN_COURT', 'CLOSED', 'DISMISSED', 'COLD'),
notes VARCHAR(500) NOT NULL,

evidenceId INT UNIQUE NOT NULL,
leadInvestigatorId INT NOT NULL,
assignedId INT NOT NULL,

CONSTRAINT pk_cc PRIMARY KEY (criminalCaseId, evidenceId, assignedId, leadInvestigatorId),

CONSTRAINT lead_investigator_fk FOREIGN KEY (leadInvestigatorId) REFERENCES Detective(detectiveId)
);
CREATE TABLE Working_Detective_Case(
workingId INT,
detectiveId INT,
criminalCaseId INT,

CONSTRAINT pk_wdc PRIMARY KEY (workingId),

CONSTRAINT case_id_fk FOREIGN KEY (criminalCaseId) REFERENCES CriminalCase(criminalCaseId),
CONSTRAINT detective_id_fk FOREIGN KEY (detectiveId) REFERENCES Detective(detectiveId)
);
CREATE TABLE Storage(

storageId INT,
version INT NOT NULL,
createdAt DATETIME NOT NULL,
modifiedAt DATETIME NOT NULL,

name VARCHAR(100) NOT NULL,
location VARCHAR (500) NOT NULL,

evidenceId INT UNIQUE NOT NULL,

CONSTRAINT pk_s PRIMARY KEY (storageId, evidenceId)
);
CREATE TABLE Evidence (

evidenceId INT,
version INT NOT NULL,
createdAt DATETIME NOT NULL,
modifiedAt DATETIME NOT NULL,

number VARCHAR(100) UNIQUE NOT NULL,
itemName VARCHAR(100) NOT NULL,
note VARCHAR(500) NOT NULL,
archived SMAlLINT(1) NOT NULL,

criminalCaseId INT NOT NULL,
trackEntryId INT NOT NULL,
storageId INT NOT NULL,

CONSTRAINT pk_e PRIMARY KEY (evidenceId, criminalCaseId, trackEntryId, storageId),

CONSTRAINT storage_fk FOREIGN KEY (storageId) REFERENCES Storage(storageId),
CONSTRAINT case_fk FOREIGN KEY (criminalCaseId) REFERENCES CriminalCase(criminalCaseId)
);
CREATE TABLE TrackEntry(

  trackEntryId INT,
  version INT NOT NULL,
  createdAt DATETIME NOT NULL,
  modifiedAt DATETIME NOT NULL,

  trackDate DATETIME NOT NULL,
  action ENUM('SUBMITTED','RETRIEVED','RETURNED'),
  reason VARCHAR(100) NOT NULL,

  detectiveId INT NOT NULL,
  evidenceId INT NOT NULL,

  CONSTRAINT pk_te PRIMARY KEY (trackEntryId, detectiveId, evidenceId),

  CONSTRAINT detective_fk FOREIGN KEY (detectiveId) REFERENCES Detective(detectiveId),
  CONSTRAINT evidence_fk FOREIGN KEY (evidenceId) REFERENCES Evidence(evidenceId)
  )