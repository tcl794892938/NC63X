INSERT INTO bd_billtype (ts, iseditableproperty, pk_billtypecode, ncbrcode, parentbilltype, canextendtransaction, isbizflowbill, istransaction, datafinderclz, referclassname, isaccount, isroot, pk_org, component, billtypename, billcoderule, emendenumclass, dr, nodecode, isenablebutton, pk_billtypeid, systemcode, classname, checkclassname, accountclass, islock, forwardbilltype, billtypename2, billtypename3, transtype_class, billtypename4, billtypename5, billtypename6, pk_group, webnodecode, billstyle, def3, def2, isapprovebill, wherestring, def1 ) VALUES ('2018-01-18 15:57:24', null, 'H430', '~', '~', 'Y', null, 'N', null, null, null, null, 'GLOBLE00000000000000', 'tcl_contract', '合同修订', '~', null, null, 'ZLH430', null, '0001ZZ1000000001SON1', 'ZLH2', null, null, null, null, null, null, null, null, null, null, null, '~', '~', null, null, null, 'Y', null, null );
INSERT INTO pub_billaction (ts, finishflag, showhint6, showhint5, showhint4, showhint2, showhint3, constrictflag, action_type, actionstyle, showhint, dr, pk_billtype, pushflag, actionstyleremark, pk_billtypeid, controlflag, actionnote6, pk_billaction, actiontype, actionnote4, actionnote, actionnote5, actionnote2, actionnote3 ) VALUES ('2018-01-18 15:58:54', 'N', null, null, null, null, null, 'N', 10, '1', null, null, 'H430', null, null, '0001ZZ1000000001SON1', 'N', null, '0001ZZ1000000001SON2', 'SAVE', null, '送审', null, null, null );
INSERT INTO pub_billaction (ts, finishflag, showhint6, showhint5, showhint4, showhint2, showhint3, constrictflag, action_type, actionstyle, showhint, dr, pk_billtype, pushflag, actionstyleremark, pk_billtypeid, controlflag, actionnote6, pk_billaction, actiontype, actionnote4, actionnote, actionnote5, actionnote2, actionnote3 ) VALUES ('2018-01-18 15:58:54', 'N', null, null, null, null, null, 'N', 11, '2', null, null, 'H430', null, null, '0001ZZ1000000001SON1', 'N', null, '0001ZZ1000000001SON3', 'APPROVE', null, '审核', null, null, null );
INSERT INTO pub_billaction (ts, finishflag, showhint6, showhint5, showhint4, showhint2, showhint3, constrictflag, action_type, actionstyle, showhint, dr, pk_billtype, pushflag, actionstyleremark, pk_billtypeid, controlflag, actionnote6, pk_billaction, actiontype, actionnote4, actionnote, actionnote5, actionnote2, actionnote3 ) VALUES ('2018-01-18 15:58:54', 'Y', null, null, null, null, null, 'N', 13, '3', null, null, 'H430', null, null, '0001ZZ1000000001SON1', 'Y', null, '0001ZZ1000000001SON4', 'UNSAVEBILL', null, '收回', null, null, null );
INSERT INTO pub_billaction (ts, finishflag, showhint6, showhint5, showhint4, showhint2, showhint3, constrictflag, action_type, actionstyle, showhint, dr, pk_billtype, pushflag, actionstyleremark, pk_billtypeid, controlflag, actionnote6, pk_billaction, actiontype, actionnote4, actionnote, actionnote5, actionnote2, actionnote3 ) VALUES ('2018-01-18 15:58:54', 'Y', null, null, null, null, null, 'N', 12, '3', null, null, 'H430', null, null, '0001ZZ1000000001SON1', 'N', null, '0001ZZ1000000001SON5', 'UNAPPROVE', null, '弃审', null, null, null );
INSERT INTO pub_billaction (ts, finishflag, showhint6, showhint5, showhint4, showhint2, showhint3, constrictflag, action_type, actionstyle, showhint, dr, pk_billtype, pushflag, actionstyleremark, pk_billtypeid, controlflag, actionnote6, pk_billaction, actiontype, actionnote4, actionnote, actionnote5, actionnote2, actionnote3 ) VALUES ('2018-01-18 15:58:54', 'N', null, null, null, null, null, 'N', 30, '3', null, null, 'H430', null, null, '0001ZZ1000000001SON1', 'N', null, '0001ZZ1000000001SON6', 'DELETE', null, '删除', null, null, null );
INSERT INTO pub_billaction (ts, finishflag, showhint6, showhint5, showhint4, showhint2, showhint3, constrictflag, action_type, actionstyle, showhint, dr, pk_billtype, pushflag, actionstyleremark, pk_billtypeid, controlflag, actionnote6, pk_billaction, actiontype, actionnote4, actionnote, actionnote5, actionnote2, actionnote3 ) VALUES ('2018-01-18 15:58:54', 'N', null, null, null, null, null, 'Y', 31, '1', null, null, 'H430', null, null, '0001ZZ1000000001SON1', 'N', null, '0001ZZ1000000001SON7', 'SAVEBASE', null, '保存', null, null, null );
INSERT INTO pub_busiclass (ts, pk_billtypeid, pk_businesstype, classname, isbefore, actiontype, pk_group, dr, pk_billtype, pk_busiclass ) VALUES ('2018-01-18 15:58:54', '0001ZZ1000000001SON1', '~', 'N_H430_SAVE', 'N', 'SAVE', '~', 0, 'H430', '0001ZZ1000000001SON8' );
INSERT INTO pub_busiclass (ts, pk_billtypeid, pk_businesstype, classname, isbefore, actiontype, pk_group, dr, pk_billtype, pk_busiclass ) VALUES ('2018-01-18 15:58:54', '0001ZZ1000000001SON1', '~', 'N_H430_APPROVE', 'N', 'APPROVE', '~', 0, 'H430', '0001ZZ1000000001SON9' );
INSERT INTO pub_busiclass (ts, pk_billtypeid, pk_businesstype, classname, isbefore, actiontype, pk_group, dr, pk_billtype, pk_busiclass ) VALUES ('2018-01-18 15:58:54', '0001ZZ1000000001SON1', '~', 'N_H430_UNSAVEBILL', 'N', 'UNSAVEBILL', '~', 0, 'H430', '0001ZZ1000000001SONA' );
INSERT INTO pub_busiclass (ts, pk_billtypeid, pk_businesstype, classname, isbefore, actiontype, pk_group, dr, pk_billtype, pk_busiclass ) VALUES ('2018-01-18 15:58:54', '0001ZZ1000000001SON1', '~', 'N_H430_UNAPPROVE', 'N', 'UNAPPROVE', '~', 0, 'H430', '0001ZZ1000000001SONB' );
INSERT INTO pub_busiclass (ts, pk_billtypeid, pk_businesstype, classname, isbefore, actiontype, pk_group, dr, pk_billtype, pk_busiclass ) VALUES ('2018-01-18 15:58:54', '0001ZZ1000000001SON1', '~', 'N_H430_DELETE', 'N', 'DELETE', '~', 0, 'H430', '0001ZZ1000000001SONC' );
INSERT INTO pub_busiclass (ts, pk_billtypeid, pk_businesstype, classname, isbefore, actiontype, pk_group, dr, pk_billtype, pk_busiclass ) VALUES ('2018-01-18 15:58:54', '0001ZZ1000000001SON1', '~', 'N_H430_SAVEBASE', 'N', 'SAVEBASE', '~', 0, 'H430', '0001ZZ1000000001SOND' );