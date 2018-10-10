package nc.impl.zl.linkvoucher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillConcurrentTool;
import nc.pubitf.fip.external.IBillReflectorService;
import nc.vo.fip.external.FipExtendAggVO;
import nc.vo.fip.service.FipRelationInfoVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.zl.cwf_gather.AggGatherVO;

public class BillReflectorServiceImpl implements IBillReflectorService{

	/***
	 * 
	 * @author by cwf 2018
	 *
	 */
	
	@Override
	public Collection<FipExtendAggVO> queryBillByRelations(
		      Collection<FipRelationInfoVO> arg0) throws BusinessException {
			  
		    Map<String, FipRelationInfoVO> mapInfo = this.getMapRelationInfo(arg0);
		    String[] hids =mapInfo.keySet().toArray(new String[mapInfo.keySet().size()]);

		    BillQuery<AggGatherVO> query = new BillQuery<AggGatherVO>(AggGatherVO.class);
		    AggGatherVO[] bills = query.query(hids);
		    // 加锁
		    BillConcurrentTool tool = new BillConcurrentTool();
		    TimeLog.logStart();
		    tool.lockBill(bills);
		    TimeLog.info("锁定表头、表体主健"); /*-=notranslate=-*/

		    Collection<FipExtendAggVO> fipAggVO = this.createFipMsgVO(bills);
		    return fipAggVO;
		  }

	private Collection<FipExtendAggVO> createFipMsgVO(AggGatherVO[] vos) {
	    Collection<FipExtendAggVO> msgvolist = new ArrayList<FipExtendAggVO>();
	    for (AggGatherVO vo : vos) {
	      // SaleInvoiceBVO[] items = vo.getChildrenVO();
	      FipExtendAggVO msgvo = new FipExtendAggVO();
	      // String cbillbid = items[0].getCsaleinvoicebid();
	      msgvo.setBillVO(vo);
	      msgvo.setRelationID(vo.getPrimaryKey());
	      // FipRelationInfoVO infovo = mapInfo.get(cbillbid);
	      // msgvo.setMessageinfo(infovo);
	      msgvolist.add(msgvo);
	    }
	    return msgvolist;
	  }

	  private Map<String, FipRelationInfoVO> getMapRelationInfo(
	      Collection<FipRelationInfoVO> relationvos) {
	    Map<String, FipRelationInfoVO> map =
	        new HashMap<String, FipRelationInfoVO>();
	    for (FipRelationInfoVO vo : relationvos) {
	      String id = vo.getRelationID();
	      map.put(id, vo);
	    }
	    return map;
	  }
}
