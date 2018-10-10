package nc.impl.gl;

import org.w3c.dom.Document;

import nc.bs.dao.BaseDAO;
import nc.itf.gl.ISendVoucher2;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.xmlchange.DOMUtils;
import nc.vo.pfxx.pub.PostFile;
import nc.vo.pub.BusinessException;

public class SendVoucherImpl2 implements ISendVoucher2 {

	@Override
	public String doSendVoucherToNCSystem(String xml) throws BusinessException {
		
		String str="";
		PostFile ps=new PostFile();
		Document doc=DOMUtils.parseXMLDocument(xml);
		String corp=doc.getDocumentElement().getAttribute("receiver");
		String sql1="select defaultvalue from pub_sysinittemp where initcode='TCL11' ";
		String sql2="select defaultvalue from pub_sysinittemp where initcode='TCL12' ";
		BaseDAO dao=new BaseDAO("design");
		Object obj1=dao.executeQuery(sql1, new ColumnProcessor());
		Object obj2=dao.executeQuery(sql2, new ColumnProcessor());
		if(obj1==null||"".equals(obj1)||obj2==null||"".equals(obj2)){
			return "�������ü��Ų���TCL01,TCL02�����ڻ�ȡ���ݿ�������Ϣ��";
		}
		String url=obj1+"/service/XChangeServlet?account="+obj2+"&receiver="+corp+"&langcode=simpchn";
		try {
			str=ps.sendDocument(doc, url, "UTF-8",true, null);
		} catch (Exception e) {
			e.printStackTrace();
			str=e.getMessage();
		}
		
		return str;
	}

}
