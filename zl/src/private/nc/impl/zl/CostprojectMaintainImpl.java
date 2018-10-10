package nc.impl.zl;

import nc.impl.pub.ace.AceCostprojectPubServiceImpl;
import nc.itf.zl.ICostprojectMaintain;
import nc.vo.zl.cwf_costproject.CostprojectVO;
import nc.vo.pub.BusinessException;

public class CostprojectMaintainImpl extends AceCostprojectPubServiceImpl implements ICostprojectMaintain {

      @Override
    public void delete(CostprojectVO vos) throws BusinessException {
        super.deletetreeinfo(vos);
    }
  
      @Override
    public CostprojectVO insert(CostprojectVO vos) throws BusinessException {
    	  Object pk_org=vos.getPk_org();
    	  String org=pk_org==null?"":pk_org.toString();
    	  if(org.equals("")){
    		  vos.setPk_org(vos.getPk_group());
    	  }
        return super.inserttreeinfo(vos);
    }
    
      @Override
    public CostprojectVO update(CostprojectVO vos) throws BusinessException {
    	  Object pk_org=vos.getPk_org();
    	  String org=pk_org==null?"":pk_org.toString();
    	  if(org.equals("")){
    		  vos.setPk_org(vos.getPk_group());
    	  }
        return super.updatetreeinfo(vos);    
    }
  
      @Override
    public CostprojectVO[] query(String whereSql)
        throws BusinessException {
        return super.querytreeinfo(whereSql);
    }

  
}
