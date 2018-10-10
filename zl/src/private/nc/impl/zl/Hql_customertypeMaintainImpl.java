package nc.impl.zl;

import nc.impl.pub.ace.AceHql_customertypePubServiceImpl;
import nc.itf.zl.IHql_customertypeMaintain;
import nc.vo.zl.hql_customertype.CustomertypeVO;
import nc.vo.pub.BusinessException;

public class Hql_customertypeMaintainImpl extends AceHql_customertypePubServiceImpl implements IHql_customertypeMaintain {

      @Override
    public void delete(CustomertypeVO vos) throws BusinessException {
        super.deletetreeinfo(vos);
    }
  
      @Override
    public CustomertypeVO insert(CustomertypeVO vos) throws BusinessException {
    	  Object pk_org=vos.getPk_org();
    	  String org=pk_org==null?"":pk_org.toString();
    	  if(org.equals("")){
    		  vos.setPk_org(vos.getPk_group());
    	  }
        return super.inserttreeinfo(vos);
    }
    
      @Override
    public CustomertypeVO update(CustomertypeVO vos) throws BusinessException {
    	  Object pk_org=vos.getPk_org();
    	  String org=pk_org==null?"":pk_org.toString();
    	  if(org.equals("")){
    		  vos.setPk_org(vos.getPk_group());
    	  }
        return super.updatetreeinfo(vos);    
    }
  
      @Override
    public CustomertypeVO[] query(String whereSql)
        throws BusinessException {
        return super.querytreeinfo(whereSql);
    }

  
}
