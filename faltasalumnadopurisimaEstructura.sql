CREATE TABLE "Alumno" ("dni" VARCHAR PRIMARY KEY  NOT NULL ,"nombre" TEXT,"primer_apellido" TEXT,"segundo_apellido" TEXT,"email" VARCHAR,"telefono" NUMERIC,"curso" INTEGER REFERENCES Curso(id) DEFAULT (null) );
CREATE TABLE "Curso" ("id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , "curso" INTEGER, "etapa" TEXT, "grupo" TEXT);
CREATE TABLE "Falta" ("id" INTEGER PRIMARY KEY  NOT NULL ,"fecha" VARCHAR,"hora" VARCHAR,"alumno" VARCHAR NOT NULL REFERENCES Alumno(dni) DEFAULT (null) );
