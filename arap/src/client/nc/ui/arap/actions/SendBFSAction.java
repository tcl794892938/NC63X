package nc.ui.arap.actions;

import java.awt.event.ActionEvent;
import java.util.Map;

import javax.xml.namespace.QName;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.ui.arap.bill.ArapBillUIUtil;
import nc.ui.arap.model.ArapBillManageModel;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.trade.business.HYPubBO_Client;
import nc.ui.uif2.NCAction;
import nc.vo.arap.basebill.BaseAggVO;
import nc.vo.arap.pay.AggPayBillVO;
import nc.vo.arap.pay.PayBillItemVO;
import nc.vo.arap.pay.PayBillVO;
import nc.vo.arap.pub.ArapBillTypeInfo;
import nc.vo.logging.Debug;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

public class SendBFSAction extends NCAction {

	
	private ArapBillManageModel model;
	
	Map<String, Object> map=null;
	
	public SendBFSAction(){
		this.setBtnName("发送");
		this.setCode("sendbfsaction");
	}
	
	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		
		Object obj=model.getSelectedData();
		if(obj==null){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "未选择任何数据！");
			return ;
		}
		
		AggPayBillVO aggvo=(AggPayBillVO)obj;
		PayBillVO vo=(PayBillVO)aggvo.getParentVO();
		PayBillItemVO[] bvos=(PayBillItemVO[])aggvo.getChildrenVO();
		
		if("Y".equals(vo.getDef11())){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "已经发送过该付款单！");
			return ;
		}
		
		if(bvos==null||bvos.length<1){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "表体需要数据才可发送！");
			return ;
		}
		if(bvos[0].getRecaccount()==null||"".equals(bvos[0].getRecaccount())){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "供应商银行账户不可为空！");
			return ;
		}
		
		//统计合计金额
		UFDouble ud=new UFDouble(0);
		for(PayBillItemVO b:bvos){
			ud=ud.add(b.getMoney_de());
		}
		String str=getStrXmlInfo(vo, bvos[0],ud);
		
		//校验必输字段
		if(map.get("def1")==null||map.get("accnum")==null||map.get("bankname")==null||map.get("name")==null||
				map.get("code2")==null||map.get("accnum2")==null||map.get("bankname2")==null
				||map.get("name2")==null||map.get("combinenum")==null){
			
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "付款单位、付款方与收款方的户名，银行帐号，开户行名，" +
					"以及收款方的联行号不能为空！");
			return ;
		}
		Debug.debug(str);
		
		//发送给百特
		//获取选择信息
		String sql="select defaultvalue from pub_sysinittemp where initcode='TCL02'";
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Object objvalue=iQ.executeQuery(sql, new ColumnProcessor());
		if(objvalue==null){
			MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", "请先配置全局参数TCL02");
			return ;
		}
		String stinfo=doSendBaite(objvalue.toString() ,str);
		
		String sss="<MESSAGE>";
		String returnstr="";
		if(stinfo.lastIndexOf(sss)!=-1){
			int k=stinfo.indexOf("</MESSAGE>");
			returnstr=stinfo.substring(stinfo.lastIndexOf(sss)+sss.length(), k);
		}
		MessageDialog.showHintDlg(model.getContext().getEntranceUI(), "提示", ">>>>>>>>>>>webservice返回信息："+returnstr);
		
		//刷新数据库
		
		String def="<STATUS>";
		String def1="";
		if(stinfo.lastIndexOf(def)!=-1){
			int k=stinfo.indexOf("</STATUS>");
			def1=stinfo.substring(stinfo.lastIndexOf(def)+def.length(), k);
		}
		if(def1.equals("1")){//成功标志
			
			vo.setDef11("Y");
			HYPubBO_Client.update(vo);
			BaseAggVO[] bills=NCLocator.getInstance().lookup(ArapBillTypeInfo.getInstance(vo.getPk_billtype()).
					getBillQueryService()).findBillByPrimaryKey(new String[]{vo.getPk_paybill()});
			
			BaseAggVO baseAggVO = null != bills && bills.length > 0 ? bills[0] : null;
			ArapBillUIUtil.refreshChildVO2HeadVO(baseAggVO);
			getModel().directlyUpdate(baseAggVO);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static String doSendBaite(String url,String xmlstr) throws Exception {

		String str="";
		
		System.out.println(">>>>>>>>>>>开始调用OA的webservice>>>>>>>>");
		String endpoint = url;

		try{
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(endpoint));
			
			/*call.setOperationName(new QName("http://server.webService.jpkg.erp.hibernate.byttersoft.com","paybillService"));
			call.addParameter("xmlStr", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
			String message = (String) call.invoke(new Object[] {xmlstr});
			System.out.print(">>>>>>>>>>>webservice返回信息："+message);*/
			
			call.setOperationName(new QName("http://server.webService.jpkg.erp.hibernate.byttersoft.com","paybillService"));
			call.addParameter(new QName("xmlStr"), XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);
			call.setReturnType(XMLType.XSD_STRING);
			String message = (String) call.invoke(new Object[] {xmlstr});
			Debug.debug(xmlstr);
			System.out.print(">>>>>>>>>>>webservice返回信息："+message);
			str=">>>>>>>>>>>webservice返回信息："+message;
			
			
		}catch(Exception e){
			System.out.print(">>>>>>>>>>>WSCaller.toCall调用webservice发生异常！"+e.getMessage());

			return "WSCaller.toCall调用webservice发生异常！"+e.getMessage();
		}
		
		return str;
	}
	
	
	public String getStrXmlInfo(PayBillVO vo,PayBillItemVO bvo,UFDouble ud){
		
		String billno=vo.getBillno();
		String type=vo.getPk_tradetype();
		String sql="";
		if(type.equals("F3-Cxx-0003")||type.equals("F3-Cxx-0004")){//对个人
			
			sql="select distinct s.code,s.def1,bb.code accnum,c.name bankname,s.name,c.province,c.city,m.money_de,v.accnum accnum2," +
					"r.code code2,r.name name2,cc.name bankname2,v.province province2,v.city city2,v.combinenum,m.scomment " +
					"from ap_paybill t left join org_orgs s on t.pk_org=s.pk_org " +
					"left join ap_payitem m on t.pk_paybill=m.pk_paybill " +
					"left join bd_bankaccsub b on m.payaccount=b.pk_bankaccsub " +
					"left join bd_bankaccbas bb on b.pk_bankaccbas=bb.pk_bankaccbas " +
					"left join bd_bankdoc c on bb.pk_bankdoc=c.pk_bankdoc " +
					"left join bd_psndoc r on m.pk_psndoc=r.pk_psndoc " +
					"left join bd_psnbankacc k on k.pk_psndoc=r.pk_psndoc " +
					"left join bd_bankaccbas v on v.pk_bankaccbas=k.pk_bankaccbas " +
					"left join bd_bankdoc cc on v.pk_bankdoc=cc.pk_bankdoc" +
					" where t.billno='"+billno+"' and k.pk_bankaccsub='"+bvo.getRecaccount()+"' ";
		}else{
			sql="select distinct s.code,s.def1,bb.code accnum,c.name bankname,s.name,c.province,c.city,m.money_de,g.accnum accnum2," +
					"r.code code2,r.name name2,cc.name bankname2,v.province province2,v.city city2,v.combinenum,m.scomment " +
					"from ap_paybill t left join org_orgs s on t.pk_org=s.pk_org " +
					"left join ap_payitem m on t.pk_paybill=m.pk_paybill " +
					"left join bd_bankaccsub b on m.payaccount=b.pk_bankaccsub " +
					"left join bd_bankaccbas bb on b.pk_bankaccbas=bb.pk_bankaccbas " +
					"left join bd_bankdoc c on bb.pk_bankdoc=c.pk_bankdoc " +
					"left join bd_supplier r on m.supplier=r.pk_supplier " +
					"left join bd_custbank k on k.pk_cust=r.pk_supplier " +
					"left join bd_bankaccsub g on g.pk_bankaccsub=k.pk_bankaccsub " +
					"left join bd_bankaccbas v on v.pk_bankaccbas=g.pk_bankaccbas " +
					"left join bd_bankdoc cc on v.pk_bankdoc=cc.pk_bankdoc" +
					" where t.billno='"+billno+"' and g.pk_bankaccsub='"+bvo.getRecaccount()+"' ";
		}
		
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		
		try {
			map=(Map<String, Object>)iQ.executeQuery(sql, new MapProcessor());
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		StringBuffer strvoucher = new StringBuffer();
		strvoucher.append("<?xml version='1.0' encoding='UTF-8'?>\n");
		strvoucher.append("<MESSAGE>\n");
		strvoucher.append("	<HEAD>\n");
		strvoucher.append("		<SOURCE>ERP</SOURCE>\n");
		strvoucher.append("		<DES>BFS</DES>\n");
		strvoucher.append("		<RMK></RMK>\n");
		strvoucher.append("		<DATE>"+new UFDate().toString()+"</DATE>\n");
		strvoucher.append("	</HEAD>\n");
		strvoucher.append("	<BODY>\n");
		strvoucher.append("		<ROW INDEX='"+0+"'>\n");
		strvoucher.append("			<DATAITEM FIELDNAME='SERIAL_NO_NC' VALUE='"+billno+"'/>\n");
		String data=new UFDate().toString().substring(0, 10);
		strvoucher.append("			<DATAITEM FIELDNAME='WISH_PAYDAY' VALUE='"+data.replaceAll("-", "")+"'/>\n");
		strvoucher.append("			<DATAITEM FIELDNAME='CORP_CODE' VALUE='"+getStrobj(map.get("def1"))+"'/>\n");//付款方单位
		strvoucher.append("			<DATAITEM FIELDNAME='PAYER_ACC_NO' VALUE='"+getStrobj(map.get("accnum"))+"'/>\n");//付款方帐号
		strvoucher.append("			<DATAITEM FIELDNAME='PAYER_BANK_NAME' VALUE='"+getStrobj(map.get("bankname"))+"'/>\n");//付款方开户行名
		strvoucher.append("			<DATAITEM FIELDNAME='PAYER_ACC_NAME' VALUE='"+getStrobj(map.get("name"))+"'/>\n");//付款方户名
		strvoucher.append("			<DATAITEM FIELDNAME='PAYER_PROV' VALUE='"+getStrobj(map.get("province"))+"'/>\n");
		strvoucher.append("			<DATAITEM FIELDNAME='PAYER_CITY' VALUE='"+getStrobj(map.get("city"))+"'/>\n");
		
		strvoucher.append("			<DATAITEM FIELDNAME='AMT' VALUE='"+getStrobj(ud)+"'/>\n");
		strvoucher.append("			<DATAITEM FIELDNAME='CUR' VALUE='CNY'/>\n");
		
		strvoucher.append("			<DATAITEM FIELDNAME='PAYEE_ACC_NO' VALUE='"+getStrobj(map.get("accnum2"))+"'/>\n");//付款方单位
		strvoucher.append("			<DATAITEM FIELDNAME='PAYEE_CORP_CODE' VALUE='"+getStrobj(map.get("code2"))+"'/>\n");//付款方帐号
		strvoucher.append("			<DATAITEM FIELDNAME='PAYEE_NAME' VALUE='"+getStrobj(map.get("name2"))+"'/>\n");//付款方开户行名
		strvoucher.append("			<DATAITEM FIELDNAME='PAYEE_BANK' VALUE='"+getStrobj(map.get("bankname2"))+"'/>\n");//付款方户名
		strvoucher.append("			<DATAITEM FIELDNAME='PAYEE_CODE' VALUE='"+getStrobj(map.get("combinenum"))+"'/>\n");//联行号
		strvoucher.append("			<DATAITEM FIELDNAME='PAYER_PROV' VALUE='"+getStrobj(map.get("province2"))+"'/>\n");
		strvoucher.append("			<DATAITEM FIELDNAME='PAYER_CITY' VALUE='"+getStrobj(map.get("city2"))+"'/>\n");
		
		strvoucher.append("			<DATAITEM FIELDNAME='ABS' VALUE='"+getStrobj(map.get("scomment"))+"'/>\n");
		strvoucher.append("			<DATAITEM FIELDNAME='URGENCY_FLAG' VALUE='"+0+"'/>\n");
		strvoucher.append("			<DATAITEM FIELDNAME='VOUCHER_TYPE' VALUE='"+34+"'/>\n");
		if(type.equals("F3-Cxx-0003")||type.equals("F3-Cxx-0004")){//对个人
			strvoucher.append("			<DATAITEM FIELDNAME='ISFORINDIVIDUAL' VALUE='"+0+"'/>\n");
		}else{
			strvoucher.append("			<DATAITEM FIELDNAME='ISFORINDIVIDUAL' VALUE='"+1+"'/>\n");
		}
		
		strvoucher.append("		</ROW>\n");
		strvoucher.append("	</BODY>\n");
		strvoucher.append("</MESSAGE>\n");
		
	   return strvoucher.toString();
	}
	
	
	public ArapBillManageModel getModel() {
		return model;
	}
	public void setModel(ArapBillManageModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}
	
	private String getStrobj(Object obj){
		return obj==null?"":obj.toString();
	}

	@Override
	protected boolean isActionEnable() {
		Object obj=model.getSelectedData();
		if(obj==null){
			return false;
		}
		AggPayBillVO aggvo=(AggPayBillVO)obj;
		PayBillVO vo=(PayBillVO)aggvo.getParentVO();
		if(vo.getBillstatus()==1){
			return true;
		}
		return false;
	}
	
	
	
}