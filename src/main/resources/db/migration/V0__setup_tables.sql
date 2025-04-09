
CREATE TABLE "tb_skin" (
    "id" varchar(255) NULL,
    "name" varchar(255) NULL,
    "description" text NULL,
    "image" text NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE "tb_agent" (
    "id" varchar(255) NULL,
    "name" varchar(255) NULL,
    "description" text NULL,
    "image" text NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE "tb_crate" (
    "id" varchar(255) NULL,
    "name" varchar(255) NULL,
    "description" text NULL,
    "image" text NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE "tb_key" (
    "id" varchar(255) NULL,
    "name" varchar(255) NULL,
    "description" text NULL,
    "image" text NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE "tb_team" (
    "id" varchar(255) NULL,
    "name" varchar(255) NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE "tb_agent_team" (
    "agentId" varchar(255) NULL,
    "teamId" varchar(255) NULL,
    PRIMARY KEY ("agentId", "teamId")
);

CREATE TABLE "tb_skin_team" (
    "skinId" varchar(255) NULL,
    "teamId" varchar(255) NULL,
    PRIMARY KEY ("skinId", "teamId")
);

CREATE TABLE "tb_skin_crate" (
    "skinId" varchar(255) NULL,
    "crateId" varchar(255) NULL,
    PRIMARY KEY ("skinId", "crateId")
);

CREATE TABLE "tb_key_crate" (
    "keyId" varchar(255) NULL,
    "crateId" varchar(255) NULL,
    PRIMARY KEY ("keyId", "crateId")
);

CREATE TABLE "tb_user" (
    "id" UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    "name" varchar(255) NULL,
    "email" varchar(255) NULL,
    "password" varchar(255) NULL,
    "salt" varchar(255) NULL
);

ALTER TABLE "tb_agent_team" ADD FOREIGN KEY ("agentId") REFERENCES "tb_agent" ("id");

ALTER TABLE "tb_agent_team" ADD FOREIGN KEY ("teamId") REFERENCES "tb_team" ("id");

ALTER TABLE "tb_skin_team" ADD FOREIGN KEY ("skinId") REFERENCES "tb_skin" ("id");

ALTER TABLE "tb_skin_team" ADD FOREIGN KEY ("teamId") REFERENCES "tb_team" ("id");

ALTER TABLE "tb_skin_crate" ADD FOREIGN KEY ("skinId") REFERENCES "tb_skin" ("id");

ALTER TABLE "tb_skin_crate" ADD FOREIGN KEY ("crateId") REFERENCES "tb_crate" ("id");

ALTER TABLE "tb_key_crate" ADD FOREIGN KEY ("keyId") REFERENCES "tb_key" ("id");

ALTER TABLE "tb_key_crate" ADD FOREIGN KEY ("crateId") REFERENCES "tb_crate" ("id");