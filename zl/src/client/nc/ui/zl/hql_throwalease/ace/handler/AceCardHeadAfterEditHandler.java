package nc.ui.zl.hql_throwalease.ace.handler;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.zl.hql_throwalease.ace.config.CalculateRentUtils;
import nc.ui.zl.hql_throwalease.ace.config.CalendarUtls;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.zl.tcl_contract.ContractYwcfVO;

public class AceCardHeadAfterEditHandler implements IAppEventHandler<CardHeadTailAfterEditEvent> {

	@Override
	public void handleAppEvent(CardHeadTailAfterEditEvent e) {
		// TODO 自动生成的方法存根
		
		if(e.getKey().equals("dtzdate")){
			Object obj2 = e.getBillCardPanel().getHeadItem("denddate").getValueObject();
			Object obj3 = e.getBillCardPanel().getHeadItem("dtzdate").getValueObject();
			Object obj4 = e.getBillCardPanel().getHeadItem("dentrydate").getValueObject();
			//UFDate start = new UFDate(obj.toString());
			UFDate end = new UFDate(obj2.toString());
			UFDate tzdate = new UFDate(obj3.toString());
			UFDate jcdate = new UFDate(obj4.toString());
			if(tzdate.beforeDate(jcdate)){
				MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "提示", "退租时间应在合同进场日期之后！");
				e.getBillCardPanel().setHeadItem(e.getKey(), null);
				return ;
			}
			if(tzdate.beforeDate(end)){
				e.getBillCardPanel().setHeadItem("tztype", 1);
			}else {
				e.getBillCardPanel().setHeadItem("tztype", 2);
			}
			int row2=e.getBillCardPanel().getBillTable("pk_throwalease_bzjth").getRowCount();
			if(row2==1){
				e.getBillCardPanel().getBillModel("pk_throwalease_bzjth").setValueAt(tzdate, 0, "dytkdate");
			}
			int row = e.getBillCardPanel().getBillTable("pk_throwalease_khfc").getRowCount();
			for(int i = 0;i < row;i++){
				e.getBillCardPanel().getBillModel("pk_throwalease_khfc").setValueAt(tzdate, i, "tzdate");
			}
			int row3 = e.getBillCardPanel().getBillTable("pk_throwalease_fyqs").getRowCount();
			for(int i = 0;i < row3;i++){
				e.getBillCardPanel().getBillModel("pk_throwalease_fyqs").setValueAt(tzdate, i, "tzdate");
			}
			
			//根据退租日期不同算出不同的退款金额
			try {
				for(int k=0;k<row3;k++){
					Object pk_customer=e.getBillCardPanel().getBodyValueAt(k, "pk_customer");
					Object pk_house=e.getBillCardPanel().getBodyValueAt(k, "pk_housesource");
					Object pk_contract=e.getBillCardPanel().getHeadItem("vsrcid").getValueObject();
					Object nkkmny=e.getBillCardPanel().getBillModel("pk_throwalease_fyqs").getValueAt(k, "nkkmny");
					Object tax1=e.getBillCardPanel().getBillModel("pk_throwalease_fyqs").getValueAt(k, "ntaxrate");
					UFDouble tax=tax1==null?new UFDouble(0):new UFDouble(tax1.toString());
					Double kk = (Double) (nkkmny==null?0.0:Double.parseDouble(nkkmny.toString()));
					Double sk2=0.0;
					IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
					String sql="select * from zl_contract_ywcf where nvl(dr,0)=0 and pk_customer='"+pk_customer+"' " +
							"and pk_house='"+pk_house+"' and pk_contract='"+pk_contract+"' and dstartdate<='"+tzdate+"' " +
									"and denddate>='"+tzdate+"' order by dstartdate";
					 ContractYwcfVO ywvo=(ContractYwcfVO) iQ.executeQuery(sql, new BeanProcessor(ContractYwcfVO.class));
					 if(ywvo==null||ywvo.getNskmny()==null){
						 e.getBillCardPanel().getBillModel("pk_throwalease_fyqs").setValueAt(new UFDouble(0), k, "nskmny");
						 e.getBillCardPanel().getBillModel("pk_throwalease_fyqs").setValueAt(new UFDouble(sk2-kk), k, "njsmny");
						 e.getBillCardPanel().getBillModel("pk_throwalease_fyqs").setValueAt(new UFDouble(sk2-kk).div(tax.add(100)).multiply(100), k, "nnotaxmoney");
						 Object obj1=e.getBillCardPanel().getBillModel("pk_throwalease_fyqs").getValueAt(k, "nnotaxmoney");
						 e.getBillCardPanel().getBillModel("pk_throwalease_fyqs").setValueAt(new UFDouble(sk2-kk).sub(new UFDouble(obj1.toString())), k, "ntaxmny");
						 e.getBillCardPanel().getBillModel("pk_throwalease_fyqs").setValueAt(new UFDouble(0), k, "recqr");
						 continue;
					 }else{
						 CalculateRentUtils cal=new CalculateRentUtils();
						 Double nsk=Double.parseDouble(ywvo.getNskmny().toString());
						 Double nys=Double.parseDouble(ywvo.getNysmny().toString());
						 if(nsk==0){
							 e.getBillCardPanel().getBillModel("pk_throwalease_fyqs").setValueAt(new UFDouble(0), k, "nskmny");
							 e.getBillCardPanel().getBillModel("pk_throwalease_fyqs").setValueAt(new UFDouble(0-kk), k, "njsmny");
							 e.getBillCardPanel().getBillModel("pk_throwalease_fyqs").setValueAt(new UFDouble(0-kk).div(tax.add(100)).multiply(100), k, "nnotaxmoney");
							 Object obj1=e.getBillCardPanel().getBillModel("pk_throwalease_fyqs").getValueAt(k, "nnotaxmoney");
							 e.getBillCardPanel().getBillModel("pk_throwalease_fyqs").setValueAt(new UFDouble(0-kk).sub(new UFDouble(obj1.toString())), k, "ntaxmny");
						 }else if(nys==nsk){
							 UFDate startdate=tzdate;
							 UFDate enddate=ywvo.getDenddate();
							 UFDouble sk=cal.calculateMnyBetweenDate(pk_contract.toString(), startdate, enddate,pk_house);
							 e.getBillCardPanel().getBillModel("pk_throwalease_fyqs").setValueAt(sk, k, "nskmny");
							 sk2=Double.parseDouble(sk.toString());
							 e.getBillCardPanel().getBillModel("pk_throwalease_fyqs").setValueAt(new UFDouble(sk2-kk), k, "njsmny");
							 e.getBillCardPanel().getBillModel("pk_throwalease_fyqs").setValueAt(new UFDouble(sk2-kk).div(tax.add(100)).multiply(100), k, "nnotaxmoney");
							 Object obj1=e.getBillCardPanel().getBillModel("pk_throwalease_fyqs").getValueAt(k, "nnotaxmoney");
							 e.getBillCardPanel().getBillModel("pk_throwalease_fyqs").setValueAt(new UFDouble(sk2-kk).sub(new UFDouble(obj1.toString())), k, "ntaxmny");
						 }else{
							 UFDate startdate=tzdate;
							 UFDate enddate=ywvo.getDenddate();
							 UFDouble sk=cal.calculateMnyBetweenDate(pk_contract.toString(), startdate, enddate,pk_house);
							 Double sy=nys-nsk;
							 Double yt=Double.parseDouble(sk.toString())-sy;
							 e.getBillCardPanel().getBillModel("pk_throwalease_fyqs").setValueAt(new UFDouble(yt), k, "nskmny");
							 e.getBillCardPanel().getBillModel("pk_throwalease_fyqs").setValueAt(new UFDouble(yt-kk), k, "njsmny");
							 e.getBillCardPanel().getBillModel("pk_throwalease_fyqs").setValueAt(new UFDouble(yt-kk).div(tax.add(100)).multiply(100), k, "nnotaxmoney");
							 Object obj1=e.getBillCardPanel().getBillModel("pk_throwalease_fyqs").getValueAt(k, "nnotaxmoney");
							 e.getBillCardPanel().getBillModel("pk_throwalease_fyqs").setValueAt(new UFDouble(yt-kk).sub(new UFDouble(obj1.toString())), k, "ntaxmny");
						 }
						 
						 CalendarUtls cul=new CalendarUtls();
						 UFDate ymdate=cul.getMaxMonthDay(tzdate);
						 UFDouble rec=new UFDouble(0);
						 if(ymdate.afterDate(ywvo.getDenddate())){
							 rec=cal.calculateMnyBetweenDate(pk_contract.toString(), tzdate, ywvo.getDenddate(),pk_house);
						 }else{
							 rec=cal.calculateMnyBetweenDate(pk_contract.toString(), tzdate, ymdate,pk_house);
						 }
						 e.getBillCardPanel().getBillModel("pk_throwalease_fyqs").setValueAt(rec, k, "recqr");
						 
					 }
				}
				
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
			 
		}
	}

}
