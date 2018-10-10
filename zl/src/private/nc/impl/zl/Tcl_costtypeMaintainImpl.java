package nc.impl.zl;

import nc.impl.pub.ace.AceTcl_costtypePubServiceImpl;
import nc.itf.zl.ITcl_costtypeMaintain;
import nc.vo.zl.tcl_costtype.CosttypeVO;
import nc.vo.pub.BusinessException;

public class Tcl_costtypeMaintainImpl extends AceTcl_costtypePubServiceImpl implements ITcl_costtypeMaintain {

      @Override
    public void delete(CosttypeVO vos) throws BusinessException {
        super.deletetreeinfo(vos);
    }
  
      @Override
    public CosttypeVO insert(CosttypeVO vos) throws BusinessException {
    	if(vos.getPk_org()==null){
    		vos.setPk_org(vos.getPk_group());
    	}
        return super.inserttreeinfo(vos);
    }
    
      @Override
    public CosttypeVO update(CosttypeVO vos) throws BusinessException {
    	 if(vos.getPk_org()==null){
      		vos.setPk_org(vos.getPk_group());
      	}
        return super.updatetreeinfo(vos);    
    }
  
      @Override
    public CosttypeVO[] query(String whereSql)
        throws BusinessException {
        return super.querytreeinfo(whereSql);
    }

  
}
