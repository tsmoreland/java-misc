CREATE TABLE IF NOT EXISTS "LogEntity" (
  "Id" INTEGER NOT NULL CONSTRAINT "PK_LogEntity" PRIMARY KEY AUTOINCREMENT,
  "Message" TEXT NOT NULL,
  "ObjectEntityId" INTEGER NOT NULL,
  "Severity" INTEGER NOT NULL DEFAULT 0,
  CONSTRAINT "FK_LogEntity_Objects_ObjectEntityId" FOREIGN KEY ("ObjectEntityId") REFERENCES "Objects" ("Id") ON DELETE CASCADE
)