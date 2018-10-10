DELETE FROM bd_billtype2 WHERE pk_billtypeid = '0001ZZ1000000001M99W';
DELETE FROM bd_fwdbilltype WHERE pk_billtypeid = '0001ZZ1000000001M99W';
DELETE FROM pub_function WHERE pk_billtype = 'H202';
DELETE FROM pub_billaction WHERE pk_billtypeid = '0001ZZ1000000001M99W';
DELETE FROM pub_billactiongroup WHERE pk_billtype = 'H202';
DELETE FROM bd_billtype WHERE pk_billtypeid = '0001ZZ1000000001M99W';
delete from temppkts;
DELETE FROM sm_rule_type WHERE pk_rule_type = null;
DELETE FROM sm_permission_res WHERE pk_permission_res = null;
