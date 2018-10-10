package nc.impl.zl;

import nc.impl.pub.ace.AceLd_formattypePubServiceImpl;
import nc.itf.zl.ILd_formattypeMaintain;
import nc.vo.zl.ld_formattype.FormattypeVO;
import nc.vo.pub.BusinessException;

public class Ld_formattypeMaintainImpl extends AceLd_formattypePubServiceImpl implements ILd_formattypeMaintain {

      @Override
    public void delete(FormattypeVO vos) throws BusinessException {
        super.deletetreeinfo(vos);
    }
  
      @Override
    public FormattypeVO insert(FormattypeVO vos) throws BusinessException {
    	  Object pk_org=vos.getPk_org();
    	  String org=pk_org==null?"":pk_org.toString();
    	  if(org.equals("")){
    		  vos.setPk_org(vos.getPk_group());
    	  }
        return super.inserttreeinfo(vos);
    }
    
      @Override
    public FormattypeVO update(FormattypeVO vos) throws BusinessException {
    	  Object pk_org=vos.getPk_org();
    	  String org=pk_org==null?"":pk_org.toString();
    	  if(org.equals("")){
    		  vos.setPk_org(vos.getPk_group());
    	  }
        return super.updatetreeinfo(vos);    
    }
  
      @Override
    public FormattypeVO[] query(String whereSql)
        throws BusinessException {
        return super.querytreeinfo(whereSql);
    }

  
}
