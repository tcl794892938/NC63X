DELETE FROM pub_bcr_candiattr WHERE pk_nbcr = '0001ZZ1000000001NBUS';
DELETE FROM pub_bcr_elem WHERE pk_billcodebase in ( select pk_billcodebase from pub_bcr_RuleBase where nbcrcode = 'H310' );
DELETE FROM pub_bcr_RuleBase WHERE nbcrcode = 'H310';
DELETE FROM pub_bcr_nbcr WHERE pk_nbcr = '0001ZZ1000000001NBUS';
DELETE FROM pub_bcr_OrgRela WHERE pk_billcodebase = '0001ZZ1000000001NBUT';
DELETE FROM pub_bcr_RuleBase WHERE pk_billcodebase = '0001ZZ1000000001NBUT';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ1000000001NBUU';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ1000000001NBUV';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ1000000001NBUW';
DELETE FROM bd_billtype2 WHERE pk_billtypeid = '0001ZZ1000000001NBUN';
DELETE FROM bd_fwdbilltype WHERE pk_billtypeid = '0001ZZ1000000001NBUN';
DELETE FROM pub_function WHERE pk_billtype = 'H310';
DELETE FROM pub_billaction WHERE pk_billtypeid = '0001ZZ1000000001NBUN';
DELETE FROM pub_billactiongroup WHERE pk_billtype = 'H310';
DELETE FROM bd_billtype WHERE pk_billtypeid = '0001ZZ1000000001NBUN';
delete from temppkts;
DELETE FROM sm_rule_type WHERE pk_rule_type = null;
DELETE FROM sm_permission_res WHERE pk_permission_res = null;
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ1000000001NBUO';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ1000000001NBUP';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ1000000001NBUQ';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ1000000001NBUR';
DELETE FROM pub_systemplate_base where pk_systemplate = '0001ZZ1000000001NBUM';
delete from pub_query_condition where pk_templet = '0001ZZ1000000001NBU3';
delete from pub_query_templet where id = '0001ZZ1000000001NBU3';
DELETE FROM aam_appasset WHERE pk_asset = '0001ZZ1000000001NBUL';
DELETE FROM pub_systemplate_base where pk_systemplate = '0001ZZ1000000001NBU2';
delete from pub_billtemplet_b where pk_billtemplet = '0001ZZ1000000001NBTG';
delete from pub_billtemplet where pk_billtemplet = '0001ZZ1000000001NBTG';
DELETE FROM pub_billtemplet_t WHERE pk_billtemplet = '0001ZZ1000000001NBTG';
DELETE FROM aam_appasset WHERE pk_asset = '0001ZZ1000000001NBU1';
DELETE FROM sm_menuitemreg WHERE pk_menuitem = '0001ZZ1000000001NBTF';
DELETE FROM sm_funcregister WHERE cfunid = '0001ZZ1000000001NBTD';
DELETE FROM sm_paramregister WHERE pk_param = '0001ZZ1000000001NBTE';