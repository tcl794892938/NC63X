DELETE FROM pub_bcr_candiattr WHERE pk_nbcr = '0001ZZ1000000001SONE';
DELETE FROM pub_bcr_elem WHERE pk_billcodebase in ( select pk_billcodebase from pub_bcr_RuleBase where nbcrcode = 'H430' );
DELETE FROM pub_bcr_RuleBase WHERE nbcrcode = 'H430';
DELETE FROM pub_bcr_nbcr WHERE pk_nbcr = '0001ZZ1000000001SONE';
DELETE FROM pub_bcr_OrgRela WHERE pk_billcodebase = '0001ZZ1000000001SONF';
DELETE FROM pub_bcr_RuleBase WHERE pk_billcodebase = '0001ZZ1000000001SONF';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ1000000001SONG';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ1000000001SONH';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001ZZ1000000001SONI';
DELETE FROM bd_billtype2 WHERE pk_billtypeid = '0001ZZ1000000001SON1';
DELETE FROM bd_fwdbilltype WHERE pk_billtypeid = '0001ZZ1000000001SON1';
DELETE FROM pub_function WHERE pk_billtype = 'H430';
DELETE FROM pub_billaction WHERE pk_billtypeid = '0001ZZ1000000001SON1';
DELETE FROM pub_billactiongroup WHERE pk_billtype = 'H430';
DELETE FROM bd_billtype WHERE pk_billtypeid = '0001ZZ1000000001SON1';
delete from temppkts;
DELETE FROM sm_rule_type WHERE pk_rule_type = null;
DELETE FROM sm_permission_res WHERE pk_permission_res = null;
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ1000000001SON2';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ1000000001SON3';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ1000000001SON4';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ1000000001SON5';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ1000000001SON6';
DELETE FROM pub_billaction WHERE pk_billaction = '0001ZZ1000000001SON7';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ1000000001SON8';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ1000000001SON9';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ1000000001SONA';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ1000000001SONB';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ1000000001SONC';
DELETE FROM pub_busiclass WHERE pk_busiclass = '0001ZZ1000000001SOND';
DELETE FROM pub_systemplate_base where pk_systemplate = '0001ZZ1000000001SON0';
delete from pub_print_datasource where ctemplateid = '0001ZZ1000000001SNM0';
delete from pub_print_cell where ctemplateid = '0001ZZ1000000001SNM0';
delete from pub_print_line where ctemplateid = '0001ZZ1000000001SNM0';
delete from pub_print_variable where ctemplateid = '0001ZZ1000000001SNM0';
delete from pub_print_template where ctemplateid = '0001ZZ1000000001SNM0';
DELETE FROM aam_appasset WHERE pk_asset = '0001ZZ1000000001SOMZ';
DELETE FROM pub_systemplate_base where pk_systemplate = '0001ZZ1000000001SNLZ';
delete from pub_query_condition where pk_templet = '0001ZZ1000000001SNKA';
delete from pub_query_templet where id = '0001ZZ1000000001SNKA';
DELETE FROM aam_appasset WHERE pk_asset = '0001ZZ1000000001SNLY';
DELETE FROM pub_systemplate_base where pk_systemplate = '0001ZZ1000000001SNK9';
delete from pub_billtemplet_b where pk_billtemplet = '0001ZZ1000000001SNE4';
delete from pub_billtemplet where pk_billtemplet = '0001ZZ1000000001SNE4';
DELETE FROM pub_billtemplet_t WHERE pk_billtemplet = '0001ZZ1000000001SNE4';
DELETE FROM aam_appasset WHERE pk_asset = '0001ZZ1000000001SNK8';
DELETE FROM sm_menuitemreg WHERE pk_menuitem = '0001ZZ1000000001SNE3';
DELETE FROM sm_funcregister WHERE cfunid = '0001ZZ1000000001SNE1';
DELETE FROM sm_paramregister WHERE pk_param = '0001ZZ1000000001SNE2';