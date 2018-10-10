package nc.itf.gl;

import nc.vo.pub.BusinessException;

public interface ISendVoucher2 {

	
	public String doSendVoucherToNCSystem(String xml)throws BusinessException;
}
