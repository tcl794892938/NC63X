package nc.impl.zl;

import nc.impl.pub.ace.AceHql_contracttypePubServiceImpl;
import nc.itf.zl.IHql_contracttypeMaintain;
import nc.vo.zl.hql_contracttype.ContracttypeVO;
import nc.vo.pub.BusinessException;

public class Hql_contracttypeMaintainImpl extends AceHql_contracttypePubServiceImpl implements IHql_contracttypeMaintain {

      @Override
    public void delete(ContracttypeVO vos) throws BusinessException {
        super.deletetreeinfo(vos);
    }
  
      @Override
    public ContracttypeVO insert(ContracttypeVO vos) throws BusinessException {
    	  Object pk_org=vos.getPk_org();
    	  String org=pk_org==null?"":pk_org.toString();
    	  if(org.equals("")){
    		  vos.setPk_org(vos.getPk_group());
    	  }
        return super.inserttreeinfo(vos);
    }
    
      @Override
    public ContracttypeVO update(ContracttypeVO vos) throws BusinessException {
    	  Object pk_org=vos.getPk_org();
    	  String org=pk_org==null?"":pk_org.toString();
    	  if(org.equals("")){
    		  vos.setPk_org(vos.getPk_group());
    	  }
        return super.updatetreeinfo(vos);    
    }
  
      @Override
    public ContracttypeVO[] query(String whereSql)
        throws BusinessException {
        return super.querytreeinfo(whereSql);
    }

  
}
