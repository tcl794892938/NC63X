INSERT INTO pub_bcr_nbcr (ts, pk_nbcr, metaid, codelenth, codescope, code, orgtype, name5, name6, name3, name4, name, codestyle, name2 ) VALUES ('2018-01-04 11:48:30', '0001ZZ1000000001O9GN', '5b15ddb1-59a0-4a46-9bd1-bd944c0a8745', 40, 'global', 'H340', 'GLOBLE00000000000000', null, null, null, null, 'H340', 'after', null );
INSERT INTO pub_bcr_RuleBase (ts, isused, islenvar, nbcrcode, isdefault, rulecode, rulename, isautofill, codescope, format, dataoriginflag, rulename6, rulename5, rulename4, rulename3, rulename2, iseditable, isgetpk, codemode, pk_billcodebase, pk_group ) VALUES ('2018-01-04 11:48:31', 'Y', 'Y', 'H340', 'N', 'H340', 'H340', 'Y', 'g', 'yyyyMMdd', 0, null, null, null, null, null, 'N', 'N', 'after', '0001ZZ1000000001O9GO', 'GLOBLE00000000000000' );
INSERT INTO pub_bcr_elem (ts, isrefer, elemvalue, elemlenth, pk_billcodebase, pk_billcodeelem, eorder, pk_billcodeentity, elemtype ) VALUES ('2018-01-04 11:48:31', 0, 'H340', 4, '0001ZZ1000000001O9GO', '0001ZZ1000000001O9GP', 0, '~', 0 );
INSERT INTO pub_bcr_elem (ts, isrefer, elemvalue, elemlenth, pk_billcodebase, pk_billcodeelem, eorder, pk_billcodeentity, elemtype ) VALUES ('2018-01-04 11:48:31', 0, '2018-01-04 11:48:30', 8, '0001ZZ1000000001O9GO', '0001ZZ1000000001O9GQ', 1, '~', 2 );
INSERT INTO pub_bcr_elem (ts, isrefer, elemvalue, elemlenth, pk_billcodebase, pk_billcodeelem, eorder, pk_billcodeentity, elemtype ) VALUES ('2018-01-04 11:48:31', 0, 'H340', 8, '0001ZZ1000000001O9GO', '0001ZZ1000000001O9GR', 2, '~', 3 );