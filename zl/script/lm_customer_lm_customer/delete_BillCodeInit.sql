DELETE FROM pub_bcr_candiattr WHERE pk_nbcr = '0001ZZ1000000001O9GN';
DELETE FROM pub_bcr_elem WHERE pk_billcodebase in ( select pk_billcodebase from pub_bcr_RuleBase where nbcrcode = 'H340' );
DELETE FROM pub_bcr_RuleBase WHERE nbcrcode = 'H340';
DELETE FROM pub_bcr_nbcr WHERE pk_nbcr = '0001ZZ1000000001O9GN';
DELETE FROM pub_bcr_OrgRela WHERE pk_billcodebase = '0001ZZ1000000001O9GO';
DELETE FROM pub_bcr_RuleBase WHERE pk_billcodebase = '0001ZZ1000000001O9GO';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ1000000001O9GP';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ1000000001O9GQ';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ1000000001O9GR';