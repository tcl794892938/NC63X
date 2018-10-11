package nc.ui.zl.tcl_contract.ace.handler;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.zl.abs.enums.AbsEnumType;
import nc.ui.zl.tcl_contract.ace.config.CalculateRentUtils;
import nc.ui.zl.tcl_contract.ace.config.ValidateUtils;
import nc.vo.logging.Debug;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.zl.lm_customer.CustomerVO;

public class AceCardBodyAfterEditHandler implements IAppEventHandler<CardBodyAfterEditEvent>{

	private ShowUpableBillForm billForm;
	@SuppressWarnings("unchecked")
	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		
		String tabcode=billForm.getBillCardPanel().getCurrentBodyTableCode();
		BillCardPanel panel=billForm.getBillCardPanel();
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		BillModel model2=billForm.getBillCardPanel().getBillModel(tabcode);
		
		//======================================================客户页签=============================================================
		if(e.getKey().equals("pk_customer")&&tabcode.equals("pk_contract_cust")){
			
			Object obj=e.getValue();
			billForm.getBillCardPanel().setHeadItem("pk_customer", obj);
			
			String[] key=new String[]{"pk_customer","customertype","sfzh","yyzz","customerlxfs","customeraddress"};
			String[] htkey=new String[]{"pk_customer","customertype","idno","busilicence","lxphone","lxaddress"};
			if(obj==null){
				for(String k:htkey){
					model2.setValueAt(null, e.getRow(), k);
				}
				return ;
			}
			
			String sql="select * from zl_customer r where r.pk_customer='"+obj+"'";
			CustomerVO vo=null;
			try {
				vo=(CustomerVO)iQ.executeQuery(sql, new BeanProcessor(CustomerVO.class));
			} catch (BusinessException e1) {
				Debug.debug(e1.getMessage());
			}
			if(vo==null){
				MessageDialog.showHintDlg(billForm, "提示", "客户数据异常！");
				model2.setValueAt(null, e.getRow(), e.getKey());
				return ;
			}
			
			for(int i=0;i<key.length;i++){
				billForm.getBillCardPanel().setBodyValueAt(vo.getAttributeValue(key[i]), e.getRow(), htkey[i]);
				//model2.setValueAt(vo.getAttributeValue(key[i]), e.getRow(), htkey[i]);
			}
			
		}
		
		//======================================================房产页签=============================================================
		if(e.getKey().equals("pk_building")&&tabcode.equals("pk_contract_house")){
			
			String[] keys=new String[]{"pk_house","narea","nprice","ndaymny","nmonthmny","nyearmny","vmemo"};
			for(String k:keys){
				model2.setValueAt(null, e.getRow(), k);
			}
		}
		
		if(e.getKey().equals("pk_house")&&tabcode.equals("pk_contract_house")){
			
			String[] keys=new String[]{"narea","nprice","ndaymny","nmonthmny","nyearmny","vmemo"};
			for(String k:keys){
				model2.setValueAt(null, e.getRow(), k);
			}
			
			if(e.getValue()==null){
				return ;
			}
			
			//校验房产是否重复
			try {
				ValidateUtils.ValidateHouse(panel);
			} catch (ValidationException e2) {
				MessageDialog.showHintDlg(billForm, "提示", e2.getMessage());
				model2.setValueAt(null, e.getRow(), e.getKey());
				return ;
			}
			
			//校验房产是否可用
			try {
				ValidateUtils.ValidateHouseUseful(panel,e.getValue().toString());
			} catch (BusinessException e3) {
				MessageDialog.showHintDlg(billForm, "提示", e3.getMessage());
				model2.setValueAt(null, e.getRow(), e.getKey());
				return ;
			}
			
			Object objarea=null;
			String sql="select buildarea from zl_housesource where pk_housesource='"+e.getValue()+"'";
			try {
				objarea=iQ.executeQuery(sql, new ColumnProcessor());
			} catch (BusinessException e1) {
				Debug.debug(e1.getMessage());
			}
			if(objarea==null){
				MessageDialog.showHintDlg(billForm, "提示", "请先维护该房源的建筑面积！");
				model2.setValueAt(null, e.getRow(), e.getKey());
				return ;
			}
			model2.setValueAt(objarea, e.getRow(), "narea");
			
			//重算房产租金
			int[] rows=new int[]{e.getRow()};
			try {
				CalculateRentUtils.recalHouse(billForm.getBillCardPanel(), rows);
			} catch (BusinessException e1) {
				MessageDialog.showHintDlg(billForm, "提示", e1.getMessage());
			}
		}
		
		if(e.getKey().equals("vdef4")&&tabcode.equals("pk_contract_house")){
			try {
				BillModel m3=billForm.getBillCardPanel().getBillModel("pk_contract_zqfy");
				String sql="select pk_costproject from zl_costproject where nvl(dr,0)=0 and code='02'";
				Object pk=iQ.executeQuery(sql, new ColumnProcessor());
				Object pk_house=model2.getValueObjectAt(e.getRow(), "pk_house");
				Object obj=billForm.getBillCardPanel().getHeadItem("pk_customer").getValueObject();
				Object objdate=billForm.getBillCardPanel().getHeadItem("dstartdate").getValueObject();
				if(pk_house==null){
					MessageDialog.showHintDlg(billForm, "提示", "请先选择楼栋房产名称！");
					model2.setValueAt(null, e.getRow(), e.getKey());
					return ;
				}
				if(e.getValue().equals(true)){
					//校验房产-收费项目重复
					int rowcount=m3.getRowCount();
					for(int i=0;i<rowcount;i++){
						Object obj1=getColvalue(m3.getValueObjectAt(i, "pk_house"));
						Object obj2=getColvalue(m3.getValueObjectAt(i, "pk_costproject"));
						Object obj3=getColvalue(pk_house);
						if(obj1==null||obj2==null||obj3==null){
							continue ;
						}
						String pks2=obj1.toString()+obj2.toString();
						String pks1=obj3.toString()+pk.toString();
						if(pks1.equals(pks2)){
							MessageDialog.showHintDlg(billForm, "提示", "存在重复的房产和收费项目！");
							model2.setValueAt(null, e.getRow(), e.getKey());
							return ;
						}
					}
					m3.addLine();
					m3.setValueAt((rowcount+1)*10+"", rowcount, "rowno");
					m3.setValueAt(obj, rowcount, m3.getItemIndex("pk_customer"));
					m3.setValueAt(pk_house, rowcount, m3.getItemIndex("pk_house"));
					m3.setValueAt(pk, rowcount, m3.getItemIndex("pk_costproject"));
					m3.setValueAt(model2.getValueAt(e.getRow(), "narea"), rowcount, m3.getItemIndex("narea"));
					m3.setValueAt(6, rowcount, m3.getItemIndex("ntaxrate"));
					m3.setValueAt(objdate, rowcount, m3.getItemIndex("dstartdate"));
					m3.loadLoadRelationItemValue();
					model2.setCellEditable(e.getRow(), "pk_house", false);
				}else{
					int[] a={-1};
					int rowcount=m3.getRowCount();
					for(int i=0;i<rowcount;i++){
						if(getColvalue(pk_house).equals(getColvalue(m3.getValueObjectAt(i, "pk_house")))
								&&pk.equals(getColvalue(m3.getValueObjectAt(i, "pk_costproject")))){
							a[0]=i;
						}
					}
					if(a[0]!=-1){
						BillModel m4=billForm.getBillCardPanel().getBillModel("pk_contract_zqfycf");
						int row1=m4.getRowCount();
						if(row1>0){
							for(int i=0;i<row1;i++){
								Object obj1=getColvalue(m4.getValueObjectAt(i, "pk_house"));
								Object obj2=getColvalue(m4.getValueObjectAt(i, "pk_costproject"));
								Object obj3=getColvalue(pk_house);
								if(obj1==null||obj2==null||obj3==null){
									continue ;
								}
								String pks2=obj1.toString()+obj2.toString();
								String pks1=obj3.toString()+pk.toString();
								if(pks1.equals(pks2)){
									MessageDialog.showHintDlg(billForm, "提示", "该房产已经生成周期费用拆分，无法取消！");
									model2.setValueAt(true, e.getRow(), e.getKey());
									return;
								}
							}
						}
						m3.delLine(a);
						int row2=m3.getRowCount();
						if(row2>0){
							for(int j=0;j<row2;j++){
								m3.setValueAt((j+1)*10+"", j, "rowno");
							}
						}
					}
					model2.setCellEditable(e.getRow(), "pk_house", true);
				}
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
		}
		
		//======================================================保证金页签=============================================================
		if(e.getKey().equals("nysmny")&&tabcode.equals("pk_contract_bzj")){
			
			model2.setValueAt(e.getValue(), e.getRow(), "nqkmny");
		}
		
		//======================================================免租期页签=============================================================
		if((e.getKey().equals("dstartdate")||e.getKey().equals("denddate"))&&tabcode.equals("pk_contract_mzq")){
			
			Object h1=panel.getHeadItem("dstartdate").getValueObject();
			Object h2=panel.getHeadItem("denddate").getValueObject();
			UFDate ud1=new UFDate(h1.toString());
			UFDate ud2=new UFDate(h2.toString());
			
			if(e.getValue()==null){
				return ;
			}
			//校验当前日期在时间内
			UFDate udcurr=new UFDate(e.getValue().toString());
			if(udcurr.beforeDate(ud1)||udcurr.afterDate(ud2)){
				MessageDialog.showHintDlg(billForm, "提示", "免租日期需介于合同起始日和合同终止日！");
				model2.setValueAt(null, e.getRow(), e.getKey());
				return ;
			}
			
			//当前行起始和结束日期
			Object b11=model2.getValueAt(e.getRow(), "dstartdate");
			Object b22=model2.getValueAt(e.getRow(), "denddate");
			
			
			//校验和上一行/下一行的时间关系
			if(e.getKey().equals("dstartdate")){
				//判断是否包含免租期
				Object objmz=panel.getHeadItem("is_mz").getValueObject();
				UFBoolean ub=new UFBoolean(objmz.toString());
				if(e.getRow()==0&&ub.booleanValue()){
					UFDate udb11=new UFDate(b11.toString());
					if(!udb11.isSameDate(ud1)){
						MessageDialog.showHintDlg(billForm, "提示", "包含免租期的免租开始日期应等于合同起始日期！");
						model2.setValueAt(null, e.getRow(), e.getKey());
						return ;
					}
				}
				
				int row=e.getRow()-1;
				if(row>=0){
					Object pre=model2.getValueAt(row, "denddate");
					UFDate udpre=new UFDate(pre.toString());
					UFDate udb11=new UFDate(b11.toString());
					if(!udb11.afterDate(udpre)){
						MessageDialog.showHintDlg(billForm, "提示", "免租开始日期应大于上一行免租结束日期！");
						model2.setValueAt(null, e.getRow(), e.getKey());
						return ;
					}
				}
			}else if(e.getKey().equals("denddate")){
				int row=e.getRow()+1;
				if(row<=model2.getRowCount()-1){
					Object last=model2.getValueAt(row, "dstartdate");
					UFDate udlast=new UFDate(last.toString());
					UFDate udb22=new UFDate(b22.toString());
					if(!udb22.beforeDate(udlast)){
						MessageDialog.showHintDlg(billForm, "提示", "免租结束日期应小于下一行免租开始日期！");
						model2.setValueAt(null, e.getRow(), e.getKey());
						return ;
					}
				}
			}
			
			//校验当前行起始和结束日期
			if(b11==null||b22==null){
				return ;
			}
			UFDate udb11=new UFDate(b11.toString());
			UFDate udb22=new UFDate(b22.toString());
			if(udb11.afterDate(udb22)){
				MessageDialog.showHintDlg(billForm, "提示", "免租开始日期不能大于免租结束日期！");
				model2.setValueAt(null, e.getRow(), e.getKey());
				return ;
			}
			
		}
		
		if(e.getKey().equals("vdef5")&&tabcode.equals("pk_contract_mzq")){
			if(e.getValue()==null){
				return ;
			}
			UFDouble ud=new UFDouble(e.getValue().toString());
			if(ud.compareTo(new UFDouble(0))<=0||ud.compareTo(new UFDouble(12))>0){
				MessageDialog.showHintDlg(billForm, "提示", "免租月租金倍数需介于0到12之间！");
				model2.setValueAt(null, e.getRow(), e.getKey());
				return ;
			}
		}
		
		//======================================================增长期页签=============================================================
		if(e.getKey().equals("dstartdate")&&tabcode.equals("pk_contract_zzq")){
			
			if(e.getValue()==null){
				return ;
			}
			
			Object h1=panel.getHeadItem("dstartdate").getValueObject();
			Object h2=panel.getHeadItem("denddate").getValueObject();
			UFDate ud1=new UFDate(h1.toString());
			UFDate ud2=new UFDate(h2.toString());
			
			//校验当前日期在时间内
			UFDate udcurr=new UFDate(e.getValue().toString());
			if(udcurr.beforeDate(ud1)||udcurr.afterDate(ud2)){
				MessageDialog.showHintDlg(billForm, "提示", "增长日期需介于合同起始日和合同终止日！");
				model2.setValueAt(null, e.getRow(), e.getKey());
				return ;
			}
			
			//校验和上一行/下一行的时间关系
			int row=e.getRow()-1;
			if(row>=0){
				Object pre=model2.getValueAt(row, "dstartdate");
				UFDate udpre=new UFDate(pre.toString());
				if(!udcurr.afterDate(udpre)){
					MessageDialog.showHintDlg(billForm, "提示", "增长开始日期应大于上一行开始日期！");
					model2.setValueAt(null, e.getRow(), e.getKey());
					return ;
				}
			}
				
			int row2=e.getRow()+1;
			if(row2<=model2.getRowCount()-1){
				Object last=model2.getValueAt(row2, "dstartdate");
				UFDate udlast=new UFDate(last.toString());
				if(!udcurr.beforeDate(udlast)){
					MessageDialog.showHintDlg(billForm, "提示", "增长开始日期应小于下一行开始日期！");
					model2.setValueAt(null, e.getRow(), e.getKey());
					return ;
				}
			}
		}
		
		if(e.getKey().equals("nzzrate")&&tabcode.equals("pk_contract_zzq")){
			
			String[] keys=new String[]{"ndayzzmny","ndaymny","nmonthzzmny","nmonthmny","nyearzzmny","nyearmny","vmemo","nmny"};
			for(String k:keys){
				model2.setValueAt(null, e.getRow(), k);
			}
			
			if(e.getValue()==null){
				return ;
			}
			
			UFDouble ud=new UFDouble(e.getValue().toString());
			if(ud.compareTo(new UFDouble(0))<=0){
				MessageDialog.showHintDlg(billForm, "提示", "增长比例不可小于等于零！");
				model2.setValueAt(null, e.getRow(), e.getKey());
				return ;
			}
			
			//计算增长率
			int[] rows=new int[]{e.getRow()};
			try {
				CalculateRentUtils.recalZzqData(billForm.getBillCardPanel(), rows);
			} catch (BusinessException e1) {
				MessageDialog.showHintDlg(billForm, "提示", e1.getMessage());
			}
		}
		
		if(e.getKey().equals("nmny")&&tabcode.equals("pk_contract_zzq")){
			
			String[] keys=new String[]{"ndayzzmny","ndaymny","nmonthzzmny","nmonthmny","nyearzzmny","nyearmny","vmemo","nzzrate"};
			for(String k:keys){
				model2.setValueAt(null, e.getRow(), k);
			}
			
			if(e.getValue()==null){
				return ;
			}
			
			//查找合同表头租赁方式
			Object objrent=panel.getHeadItem("rentstyle").getValueObject();
			Integer rent=Integer.valueOf(objrent.toString());
			String key="";
			if(rent==AbsEnumType.ZLstyle_1||rent==AbsEnumType.ZLstyle_2){
				key="ndaymny";
			}else if(rent==AbsEnumType.ZLstyle_3||rent==AbsEnumType.ZLstyle_4){
				key="nmonthmny";
			}else if(rent==AbsEnumType.ZLstyle_5||rent==AbsEnumType.ZLstyle_6){
				key="nyearmny";
			}
			
			
			UFDouble ud=new UFDouble(e.getValue().toString());
			int row=e.getRow();
			if(row==0){
				try {
					CalculateRentUtils.recalRent(panel);
				} catch (BusinessException e1) {
					MessageDialog.showHintDlg(billForm, "提示", e1.getMessage());
				}
				//比较录入后的金额大小
				Object nmny=panel.getHeadItem(key).getValueObject();
				UFDouble nday=new UFDouble(nmny.toString());//表头租金
				if(ud.compareTo(nday)<=0){
					MessageDialog.showHintDlg(billForm, "提示", "该租金方式下的金额不可小于"+nday+"元！");
					model2.setValueAt(null, e.getRow(), e.getKey());
					return ;
				}
			}else{
				//获取上一行的金额
				Object nmny=model2.getValueAt(e.getRow()-1, key);
				UFDouble nday=new UFDouble(nmny.toString());
				if(ud.compareTo(nday)<=0){
					MessageDialog.showHintDlg(billForm, "提示", "该租金方式下的金额不可小于上一行"+nday+"元！");
					model2.setValueAt(null, e.getRow(), e.getKey());
					return ;
				}
			}
			
			//计算增长率
			int[] rows=new int[]{e.getRow()};
			try {
				CalculateRentUtils.recalZzqData(billForm.getBillCardPanel(), rows);
			} catch (BusinessException e1) {
				MessageDialog.showHintDlg(billForm, "提示", e1.getMessage());
			}
		}
		
		//======================================================业务拆分页签=============================================================
		if(e.getKey().equals("nyhmny")&&tabcode.equals("pk_contract_ywcf")){
			
			UFDouble ud=getUFdobj(e.getValue());
			UFDouble zymny=getUFdobj(model2.getValueAt(e.getRow(), "nqzmny"));
			if(zymny.add(ud).compareTo(new UFDouble(0))<0){
				MessageDialog.showHintDlg(billForm, "提示", "调整后金额小于零！");
				model2.setValueAt(null, e.getRow(), "nyhmny");
				return ;
			}
			model2.setValueAt(zymny.add(ud), e.getRow(), "nysmny");
		}
		

		//======================================================周期明细页签=============================================================
		if(e.getKey().equals("dstartdate")&&tabcode.equals("pk_contract_zqmx")){
			
			if(e.getValue()==null){
				return ;
			}
			
			Object h1=panel.getHeadItem("dstartdate").getValueObject();
			Object h2=panel.getHeadItem("denddate").getValueObject();
			UFDate ud1=new UFDate(h1.toString());
			UFDate ud2=new UFDate(h2.toString());
			
			//校验当前日期在时间内
			UFDate udcurr=new UFDate(e.getValue().toString());
			if(udcurr.beforeDate(ud1)||udcurr.afterDate(ud2)){
				MessageDialog.showHintDlg(billForm, "提示", "变更日期需介于合同起始日和合同终止日！");
				model2.setValueAt(null, e.getRow(), e.getKey());
				return ;
			}
			
			//校验和上一行/下一行的时间关系
			int row=e.getRow()-1;
			if(row>=0){
				Object pre=model2.getValueAt(row, "dstartdate");
				UFDate udpre=new UFDate(pre.toString());
				if(!udcurr.afterDate(udpre)){
					MessageDialog.showHintDlg(billForm, "提示", "变更日期应大于上一行日期！");
					model2.setValueAt(null, e.getRow(), e.getKey());
					return ;
				}
			}
				
			int row2=e.getRow()+1;
			if(row2<=model2.getRowCount()-1){
				Object last=model2.getValueAt(row2, "dstartdate");
				UFDate udlast=new UFDate(last.toString());
				if(!udcurr.beforeDate(udlast)){
					MessageDialog.showHintDlg(billForm, "提示", "变更日期应小于下一行日期！");
					model2.setValueAt(null, e.getRow(), e.getKey());
					return ;
				}
			}
			
			Object objmz=panel.getHeadItem("is_mz").getValueObject();
			UFBoolean is_mz=new UFBoolean(objmz.toString());
			
			if(is_mz.booleanValue()){
				Object obje=panel.getBillModel("pk_contract_mzq").getValueAt(0, "denddate");
				UFDate ude=new UFDate(obje.toString());
				if(!udcurr.afterDate(ude)){
					MessageDialog.showHintDlg(billForm, "提示", "周期变更日期应大于免租期内的结束日期！");
					model2.setValueAt(null, e.getRow(), e.getKey());
					return ;
				}
			}
		}
		
		
		//======================================================周期费用页签=============================================================
		
		/*if((e.getKey().equals("pk_house")||e.getKey().equals("pk_costproject")||e.getKey().equals("pk_feescale")||
				e.getKey().equals("dstartdate")||e.getKey().equals("nsfmny")||e.getKey().equals("dsfzq")||e.getKey().equals("ntaxrate"))&&tabcode.equals("pk_contract_zqfy")){
			
			int rowcount7=panel.getBillModel("pk_contract_zqfycf").getRowCount();
			if(rowcount7>0){
				int it=MessageDialog.showOkCancelDlg(billForm, "提示", "更改当前信息将清空[周期费用拆分]信息，是否确认更改？");
				if(it!=UIDialog.ID_OK){
					panel.setHeadItem(e.getKey(), e.getOldValue());
					return ;
				}else{
					panel.getBillModel("pk_contract_zqfycf").clearBodyData();
				}
			}
		}*/
		
		if(e.getKey().equals("pk_house")&&tabcode.equals("pk_contract_zqfy")){
			
			//校验房产-收费项目重复
			try {
				ValidateUtils.ValidateZqfyRepeat(panel);
			} catch (ValidationException e2) {
				MessageDialog.showHintDlg(billForm, "提示", e2.getMessage());
				model2.setValueAt(null, e.getRow(), e.getKey());
				return ;
			}
			
			Object objarea=null;
			String sql="select buildarea from zl_housesource where pk_housesource='"+e.getValue()+"'";
			try {
				objarea=iQ.executeQuery(sql, new ColumnProcessor());
			} catch (BusinessException e1) {
				Debug.debug(e1.getMessage());
			}
			
			model2.setValueAt(objarea, e.getRow(), "narea");
			
		}
		
		if(e.getKey().equals("pk_costproject")&&tabcode.equals("pk_contract_zqfy")){
			
			String[] keys=new String[]{"pk_feescale","nsfmny","dsfzq","vmemo"};
			for(String k:keys){
				model2.setValueAt(null, e.getRow(), k);
			}
			//校验房产-收费项目重复
			try {
				ValidateUtils.ValidateZqfyRepeat(panel);
			} catch (ValidationException e2) {
				MessageDialog.showHintDlg(billForm, "提示", e2.getMessage());
				model2.setValueAt(null, e.getRow(), e.getKey());
				return ;
			}
		}
		
		if(e.getKey().equals("pk_feescale")&&tabcode.equals("pk_contract_zqfy")){
			
			String[] keys=new String[]{"nsfmny","dsfzq","vmemo"};
			for(String k:keys){
				model2.setValueAt(null, e.getRow(), k);
			}
			
			Object house=model2.getValueObjectAt(e.getRow(), "pk_house");
			if(house==null){
				MessageDialog.showHintDlg(billForm, "提示", "请先录入房产信息！");
				model2.setValueAt(null, e.getRow(), e.getKey());
				return ;
			}
			
			if(e.getValue()==null){
				return ;
			}
			
			//计算
			String sql="select accountform,nvl(accountscal,0) accountscal,roundform from zl_feescale where pk_feescale='"+e.getValue()+"'";
			Map<String, Object> map=null;
			try {
				map=(Map<String, Object>)iQ.executeQuery(sql, new MapProcessor());
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
			
			//房产面积
			Object obj2=model2.getValueAt(e.getRow(), "narea");
			UFDouble ud2=obj2==null?new UFDouble(0):new UFDouble(obj2.toString());
			
			Integer acc1=Integer.valueOf(map.get("accountform").toString());
			Integer acc2=Integer.valueOf(map.get("roundform").toString());
			UFDouble ud=new UFDouble(map.get("accountscal").toString());
			if(acc1==AbsEnumType.FeeScale_mj){
				ud=ud.multiply(ud2);
			}
			
			if(acc2==AbsEnumType.FeeScale2_QZ){
				ud=ud.add(new UFDouble(0), 0);
			}else if(acc2==AbsEnumType.FeeScale2_BLYW){
				ud=ud.add(new UFDouble(0), 1);
			}else if(acc2==AbsEnumType.FeeScale2_BLLW){
				ud=ud.add(new UFDouble(0), 2);
			}else if(acc2==AbsEnumType.FeeScale2_BLSANW){
				ud=ud.add(new UFDouble(0), 3);
			}else if(acc2==AbsEnumType.FeeScale2_BLSIW){
				ud=ud.add(new UFDouble(0), 4);
			}else if(acc2==AbsEnumType.FeeScale2_JW){
				ud=new UFDouble(Math.ceil(ud.doubleValue()),0);
			}
			
			model2.setValueAt(ud, e.getRow(), "nsfmny");
		}
		
		if(e.getKey().equals("dstartdate")&&tabcode.equals("pk_contract_zqfy")){
			
			if(e.getValue()==null){
				return ;
			}
			
			Object h1=panel.getHeadItem("dstartdate").getValueObject();
			Object h2=panel.getHeadItem("denddate").getValueObject();
			UFDate ud1=new UFDate(h1.toString());
			UFDate ud2=new UFDate(h2.toString());
			
			//校验当前日期在时间内
			UFDate udcurr=new UFDate(e.getValue().toString());
			if(udcurr.beforeDate(ud1)||udcurr.afterDate(ud2)){
				MessageDialog.showHintDlg(billForm, "提示", "开始日期需介于合同起始日和合同终止日！");
				model2.setValueAt(null, e.getRow(), e.getKey());
				return ;
			}
		}
		
		if(e.getKey().equals("dsfzq")&&tabcode.equals("pk_contract_zqfy")){
			
			if(e.getValue()==null){
				return ;
			}
			Integer it=Integer.valueOf(e.getValue().toString());
			if(it<=0){
				MessageDialog.showHintDlg(billForm, "提示", "收费周期需大于0！");
				model2.setValueAt(null, e.getRow(), e.getKey());
				return ;
			}
		}
		
		if(e.getKey().equals("ntaxrate")&&tabcode.equals("pk_contract_zqfy")){
			
			if(e.getValue()==null){
				return ;
			}
			UFDouble it=new UFDouble(e.getValue().toString());
			if(it.compareTo(new UFDouble(0))<=0){
				MessageDialog.showHintDlg(billForm, "提示", "税率需大于0！");
				model2.setValueAt(null, e.getRow(), e.getKey());
				return ;
			}
		}
		
		//======================================================周期费用拆分页签=============================================================
		if(e.getKey().equals("nyhmny")&&tabcode.equals("pk_contract_zqfycf")){
			
			UFDouble ud=getUFdobj(e.getValue());
			UFDouble fymny=getUFdobj(model2.getValueAt(e.getRow(), "nfymny"));
			if(fymny.add(ud).compareTo(new UFDouble(0))<0){
				MessageDialog.showHintDlg(billForm, "提示", "调整后金额小于零！");
				model2.setValueAt(null, e.getRow(), "nyhmny");
				return ;
			}
			model2.setValueAt(fymny.add(ud), e.getRow(), "nysmny");
			UFDouble taxrate=getUFdobj(model2.getValueAt(e.getRow(), "vdef1"));
			
			UFDouble ntaxmny=(fymny.add(ud)).div(taxrate.div(100).add(1),2);
			//UFDouble taxmny=(fymny.add(ud)).multiply(taxrate).div(100);
			model2.setValueAt(fymny.add(ud).sub(ntaxmny), e.getRow(), "ntaxmny");
			model2.setValueAt(ntaxmny, e.getRow(), "nnotaxmny");
			model2.setValueAt(new UFDouble(0), e.getRow(), "nskmny");
		}
		
		//======================================================付款方式页签=============================================================
		if(e.getKey().equals("dstartdate")&&tabcode.equals("pk_contract_fkmx")){

			
			if(e.getValue()==null){
				return ;
			}
			
			Object h1=panel.getHeadItem("dstartdate").getValueObject();
			Object h2=panel.getHeadItem("denddate").getValueObject();
			UFDate ud1=new UFDate(h1.toString());
			UFDate ud2=new UFDate(h2.toString());
			
			//校验当前日期在时间内
			UFDate udcurr=new UFDate(e.getValue().toString());
			if(udcurr.beforeDate(ud1)||udcurr.afterDate(ud2)){
				MessageDialog.showHintDlg(billForm, "提示", "付款变更日期需介于合同起始日和合同终止日！");
				model2.setValueAt(null, e.getRow(), e.getKey());
				return ;
			}
			
			//校验和上一行/下一行的时间关系
			int row=e.getRow()-1;
			if(row>=0){
				Object pre=model2.getValueAt(row, "dstartdate");
				UFDate udpre=new UFDate(pre.toString());
				if(!udcurr.afterDate(udpre)){
					MessageDialog.showHintDlg(billForm, "提示", "付款日期应大于上一行开始日期！");
					model2.setValueAt(null, e.getRow(), e.getKey());
					return ;
				}
			}
				
			int row2=e.getRow()+1;
			if(row2<=model2.getRowCount()-1){
				Object last=model2.getValueAt(row2, "dstartdate");
				UFDate udlast=new UFDate(last.toString());
				if(!udcurr.beforeDate(udlast)){
					MessageDialog.showHintDlg(billForm, "提示", "付款日期应小于下一行开始日期！");
					model2.setValueAt(null, e.getRow(), e.getKey());
					return ;
				}
			}
			
			Object objmz=panel.getHeadItem("is_mz").getValueObject();
			UFBoolean is_mz=new UFBoolean(objmz.toString());
			
			if(is_mz.booleanValue()){
				Object obje=panel.getBillModel("pk_contract_mzq").getValueAt(0, "denddate");
				UFDate ude=new UFDate(obje.toString());
				if(!udcurr.afterDate(ude)){
					MessageDialog.showHintDlg(billForm, "提示", "付款日期应大于免租期内的结束日期！");
					model2.setValueAt(null, e.getRow(), e.getKey());
					return ;
				}
			}
		
		}
		
		//======================================================租金明细页签=============================================================
		if(e.getKey().equals("nht1mny")&&tabcode.equals("pk_contract_zjmx")){
			
			UFDouble ud=getUFdobj(e.getValue());
			
			UFDouble newmny=ud;
			//控制调整金额
			if(newmny.compareTo(new UFDouble(0))<0){
				newmny=new UFDouble(0).sub(newmny);
			}
			if(newmny.compareTo(new UFDouble(10000))>0){
				MessageDialog.showHintDlg(billForm, "提示", "调整金额需控制在10000以内！");
				model2.setValueAt(null, e.getRow(), e.getKey());
				return ;
			}
			
			UFDouble nhtmny=getUFdobj(model2.getValueAt(e.getRow(), "nhtmny"));
			model2.setValueAt(nhtmny.add(ud), e.getRow(), "nht2mny");
			
			Object obj=model2.getTotalTableModel().getValueAt(0, model2.getBodyColByKey("nht2mny"));
			panel.setHeadItem("nmny", obj);
		}
		
		if(e.getKey().equals("nyear1mny")&&tabcode.equals("pk_contract_zjmx")){
			
			UFDouble ud=getUFdobj(e.getValue());
			UFDouble newmny=ud;
			//控制调整金额
			if(newmny.compareTo(new UFDouble(0))<0){
				newmny=new UFDouble(0).sub(newmny);
			}
			if(newmny.compareTo(new UFDouble(10000))>0){
				MessageDialog.showHintDlg(billForm, "提示", "调整金额需控制在10000以内！");
				model2.setValueAt(null, e.getRow(), e.getKey());
				return ;
			}
			
			UFDouble nhtmny=getUFdobj(model2.getValueAt(e.getRow(), "nyearmny"));
			model2.setValueAt(nhtmny.add(ud), e.getRow(), "nyear2mny");
			
		}
		
		if(e.getKey().equals("nht2mny")&&tabcode.equals("pk_contract_zjmx")){
			
			UFDouble ud=getUFdobj(e.getValue());
			
			UFDouble nhtmny=getUFdobj(model2.getValueAt(e.getRow(), "nhtmny"));
			
			UFDouble ht1mny=ud.sub(nhtmny);
			
			UFDouble newmny=ht1mny;
			//控制调整金额
			if(newmny.compareTo(new UFDouble(0))<0){
				newmny=new UFDouble(0).sub(newmny);
			}
			if(newmny.compareTo(new UFDouble(10000))>0){
				MessageDialog.showHintDlg(billForm, "提示", "调整金额需控制在10000以内！");
				model2.setValueAt(null, e.getRow(), e.getKey());
				return ;
			}
			
			
			model2.setValueAt(ht1mny, e.getRow(), "nht1mny");
			
			Object obj=model2.getTotalTableModel().getValueAt(0, model2.getBodyColByKey("nht2mny"));
			panel.setHeadItem("nmny", obj);
		}
		
		if(e.getKey().equals("nyear2mny")&&tabcode.equals("pk_contract_zjmx")){
			
			UFDouble ud=getUFdobj(e.getValue());
			
			UFDouble nhtmny=getUFdobj(model2.getValueAt(e.getRow(), "nyearmny"));
			
			UFDouble ht1mny=ud.sub(nhtmny);
			
			UFDouble newmny=ht1mny;
			//控制调整金额
			if(newmny.compareTo(new UFDouble(0))<0){
				newmny=new UFDouble(0).sub(newmny);
			}
			if(newmny.compareTo(new UFDouble(10000))>0){
				MessageDialog.showHintDlg(billForm, "提示", "调整金额需控制在10000以内！");
				model2.setValueAt(null, e.getRow(), e.getKey());
				return ;
			}
			
			
			model2.setValueAt(ht1mny, e.getRow(), "nyear1mny");
			
		}
		model2.loadLoadRelationItemValue();
		
	}
	
	
	public ShowUpableBillForm getBillForm() {
		return billForm;
	}
	public void setBillForm(ShowUpableBillForm billForm) {
		this.billForm = billForm;
	}
	
	/*private Object getColvalue(Object obj){
		
		if(obj==null){
			return obj;
		}else if(obj instanceof DefaultConstEnum){
			return ((DefaultConstEnum)obj).getValue();
		}
		
		return null;
	}*/
	
	private UFDouble getUFdobj(Object obj){
		return obj==null?new UFDouble(0):new UFDouble(obj.toString());
	}
	
	private static Object getColvalue(Object obj){
		
		if(obj==null){
			return obj;
		}else if(obj instanceof DefaultConstEnum){
			return ((DefaultConstEnum)obj).getValue();
		}
		
		return null;
	}

}
