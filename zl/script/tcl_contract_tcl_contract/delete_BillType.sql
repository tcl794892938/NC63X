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
