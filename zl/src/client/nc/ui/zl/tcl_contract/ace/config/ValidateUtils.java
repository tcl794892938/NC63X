package nc.ui.zl.tcl_contract.ace.config;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ValidationException;

public class ValidateUtils {
	
	
	/**
	 * 校验房产重复
	 * @param panel
	 * @throws ValidationException
	 */
	public static void ValidateHouse(BillCardPanel panel) throws ValidationException{
		
		BillModel model=panel.getBillModel("pk_contract_house");
		
		int row=model.getRowCount();
		
		Map<String, Object> map=new HashMap<String, Object>();
		
		for(int i=0;i<row;i++){
			
			Object obj=getColvalue(model.getValueObjectAt(i, "pk_house"));
			if(obj==null){
				continue ;
			}
			if(!map.containsKey(obj)){
				map.put(obj.toString(), obj);
			}else{
				throw new ValidationException("该房产已经被引用！");
			}
			
		}
		
	}
	
	/**
	 * 校验房产是否可以使用
	 * @param panel
	 * @throws BusinessException 
	 */
	public static void ValidateHouseUseful(BillCardPanel panel,String pk_house) throws BusinessException{
		
		String st=panel.getHeadItem("dstartdate").getValueObject().toString().substring(0, 10);
		String end=panel.getHeadItem("denddate").getValueObject().toString().substring(0, 10);
		Object pk=panel.getHeadItem("pk_contract").getValueObject();
		
		String sql="select count(1) from zl_contract t left join zl_contract_house e on t.pk_contract=e.pk_contract " +
			" where nvl(t.dr,0)=0 and nvl(e.dr,0)=0 and e.pk_house='"+pk_house+"' and t.version=-1 and t.vbilltypecode='H420' " +
			" and t.htstatus in(1,2,3) and ((substr(t.dstartdate,0,10)>= '"+st+"' and substr(t.dstartdate,0,10)<='"+end+"') or " +
			" (substr(t.denddate,0,10)>= '"+st+"' and substr(t.denddate,0,10)<='"+end+"') or " +
			" (substr(t.dstartdate,0,10)<= '"+st+"' and substr(t.denddate,0,10)>='"+end+"') ) and t.pk_contract<>'"+pk+"'";
		
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		Integer it=(Integer)iQ.executeQuery(sql, new ColumnProcessor());
		if(it>0){
			throw new BusinessException("该房产还在合同期内，不允许再次录入！");
		}
	}
	
	/**
	 * 校验房产和收费项目
	 * @param panel
	 * @throws ValidationException
	 */
	public static void ValidateZqfyRepeat(BillCardPanel panel) throws ValidationException{
		
		BillModel model=panel.getBillModel("pk_contract_zqfy");
		
		int row=model.getRowCount();
		
		Map<String, Object> map=new HashMap<String, Object>();
		
		for(int i=0;i<row;i++){
			
			Object obj1=getColvalue(model.getValueObjectAt(i, "pk_house"));
			Object obj2=getColvalue(model.getValueObjectAt(i, "pk_costproject"));
			if(obj1==null||obj2==null){
				continue ;
			}
			String pk=obj1.toString()+obj2.toString();
			if(!map.containsKey(pk)){
				map.put(pk, pk);
			}else{
				throw new ValidationException("存在重复的房产和收费项目！");
			}
			
		}
		
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
