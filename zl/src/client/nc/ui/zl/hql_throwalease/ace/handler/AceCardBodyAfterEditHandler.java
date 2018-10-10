package nc.ui.zl.hql_throwalease.ace.handler;


import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

public class AceCardBodyAfterEditHandler implements IAppEventHandler<CardBodyAfterEditEvent> {
	
	@SuppressWarnings("unchecked")
	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		// TODO 自动生成的方法存根
		if(e.getKey().equals("nkkmny")&&e.getTableCode().equals("pk_throwalease_bzjth")){
			Object nkkmny=e.getValue();
			Double kk = (Double) (nkkmny==null?0.0:Double.parseDouble(nkkmny.toString()));
			Object obj = e.getBillCardPanel().getBillModel().getValueAt(e.getRow(), "ytdeposit");
			Double sk = (Double)(obj == null?0.0:Double.parseDouble(obj.toString()));
			e.getBillCardPanel().setBodyValueAt(new UFDouble(sk-kk), e.getRow(), "njsmny");
		}
		
		if(e.getKey().equals("nkkmny")&&e.getTableCode().equals("pk_throwalease_fyqs")){
			Object nkkmny=e.getValue();
			Object tax=e.getBillCardPanel().getBillModel().getValueAt(e.getRow(), "ntaxrate");
			Double kk = (Double) (nkkmny==null?0.0:Double.parseDouble(nkkmny.toString()));
			Object obj = e.getBillCardPanel().getBillModel().getValueAt(e.getRow(), "nskmny");
			Double sk = (Double)(obj == null?0.0:Double.parseDouble(obj.toString()));
			e.getBillCardPanel().setBodyValueAt(new UFDouble(sk-kk), e.getRow(), "njsmny");
			e.getBillCardPanel().setBodyValueAt(new UFDouble(sk-kk).div(new UFDouble(tax.toString()).add(100)).multiply(100), e.getRow(), "nnotaxmoney");
			Object obj1=e.getBillCardPanel().getBillModel().getValueAt(e.getRow(), "nnotaxmoney");
			e.getBillCardPanel().setBodyValueAt(new UFDouble(sk-kk).sub(new UFDouble(obj1.toString())), e.getRow(), "ntaxmny");
		}
		
		if(e.getKey().equals("pk_housesource")&&e.getTableCode().equals("pk_throwalease_zqfyqs")){
			try {
				Integer zqrow=e.getBillCardPanel().getBillModel("pk_throwalease_zqfyqs").getRowCount();
				for(int i=0;i<zqrow;i++){
					Object house=e.getBillCardPanel().getBodyValueAt(i, "pk_housesource");
					if(house==null||house.toString().equals("")){
						continue;
					}
					if(i==e.getRow()){
						continue;
					}
					if(e.getValue().equals(house.toString())){
						MessageDialog.showHintDlg(e.getBillCardPanel(), "提示", "重复房产名称，请重新选择！");
						e.getBillCardPanel().setBodyValueAt(null, e.getRow(), "pk_housesource");
						e.getBillCardPanel().setBodyValueAt(null, e.getRow(), "pk_costproject");
						return;
					}
				}
				Object vsrcid=e.getBillCardPanel().getHeadItem("vsrcid").getValueObject();
				IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
				String sql="select pk_costproject,ntaxrate from zl_contract_zqfy where nvl(dr,0)=0 and pk_house='"+e.getValue()+"' and pk_contract='"+vsrcid+"'";
				Map<String,Object> zqfy=(Map<String, Object>) iQ.executeQuery(sql, new MapProcessor());
				e.getBillCardPanel().setBodyValueAt(zqfy.get("pk_costproject"), e.getRow(), "pk_costproject");
				e.getBillCardPanel().setBodyValueAt(zqfy.get("ntaxrate"), e.getRow(), "ntaxrate");
				Object yt=e.getBillCardPanel().getBillModel("pk_throwalease_zqfyqs").getValueAt(e.getRow(), "nskmny");
				if(yt!=null&&new UFDouble(yt.toString()).compareTo(new UFDouble(0))!=0){
					e.getBillCardPanel().setBodyValueAt(new UFDouble(yt.toString()).div(new UFDouble(zqfy.get("ntaxrate").toString()).add(100)).multiply(100), e.getRow(), "nnotaxmnoey");
					Object obj1=e.getBillCardPanel().getBillModel().getValueAt(e.getRow(), "nnotaxmoney");
					e.getBillCardPanel().setBodyValueAt(new UFDouble(yt.toString()).sub(new UFDouble(obj1.toString())), e.getRow(), "ntaxmny");
				}
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
			
		}
		
		if(e.getKey().equals("nskmny")&&e.getTableCode().equals("pk_throwalease_zqfyqs")){
			Double sk=e.getValue()==null?0.0:Double.parseDouble(e.getValue().toString());
			if(sk<0){
				MessageDialog.showHintDlg(e.getBillCardPanel(), "提示", "应退金额不能小于零,系统将自动转成正数");
				e.getBillCardPanel().setBodyValueAt(new UFDouble(0-sk), e.getRow(), "nskmny");
				return;
			}
			Object tax=e.getBillCardPanel().getBillModel().getValueAt(e.getRow(), "ntaxrate");
			if(tax!=null&&new UFDouble(tax.toString()).compareTo(new UFDouble(0))!=0){
				e.getBillCardPanel().setBodyValueAt(new UFDouble(e.getValue().toString()).div(new UFDouble(tax.toString()).add(100)).multiply(100), e.getRow(), "nnotaxmoney");
				Object obj1=e.getBillCardPanel().getBillModel().getValueAt(e.getRow(), "nnotaxmoney");
				e.getBillCardPanel().setBodyValueAt(new UFDouble(e.getValue().toString()).sub(new UFDouble(obj1.toString())), e.getRow(), "ntaxmny");
			}
		}
		
		if(e.getKey().equals("pk_acceptance")){
			if(e.getValue()!=null){
				try {
					String sql="select count(*) from zl_jt_acceptance where nvl(dr,0)=0 and pk_parent='"+e.getValue()+"'";
					IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
					Integer count=(Integer) iQ.executeQuery(sql, new ColumnProcessor());
					if(count>0){
						MessageDialog.showHintDlg(e.getContext().getEntranceUI(), "提示", "进场验收项目需要选择最末级！");
						e.getBillCardPanel().setBodyValueAt(null, e.getRow(), e.getKey());
						return ;
					}
				} catch (BusinessException e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}

}
