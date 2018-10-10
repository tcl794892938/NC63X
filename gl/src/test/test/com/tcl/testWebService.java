package test.com.tcl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

public class testWebService {

	public static void main(String[] args) throws Exception {

		String webserviceURL = "http://127.0.0.1:633/uapws/service/sendvoucher";
		//String webserviceURL = "http://127.0.0.1:8090/uapws/service/mobileService";
		//String webserviceURL = "http://127.0.0.1:633/axis/services/Pubservice?wsdl";
		//String webserviceURL2 = "http://172.16.1.10:88/uapws/service/sendvoucher2";
		//String webserviceURL = "http://127.0.0.1/uapws/service/sendvoucherST";
		//String webserviceURL = "http://127.0.0.1/uapws/service/updateMid";//舜天接口
		//String webserviceURL = "http://192.168.0.129:8057/uapws/service/TestHello";//精达接口
		
		//String webserviceURL ="http://223.244.237.208:8083/jptestnc/services/jpNcToBfsWebService?wsdl";

		Service service = new Service();

		Call call = (Call) service.createCall();

		call.setTargetEndpointAddress(new java.net.URL(webserviceURL));

		doMethod(call);

	}
	
	public static void doMethod(Call call) throws Exception {
		
		call.setOperationName("doSendVoucherToNCSystem");

		call.addParameter(new QName("xmlStr"), XMLType.XSD_STRING,
				javax.xml.rpc.ParameterMode.IN);

		call.setReturnType(XMLType.XSD_STRING);

		String sss=readFileContent("C:\\Users\\Administrator\\Desktop\\1.xml");
		String str=null;
		try {
			str = (String)call.invoke(new Object[] {sss});
			
		} catch (Exception e) {
			e.getMessage();
		}
		
		System.out.println(str);

			
		
	}
	
	@SuppressWarnings("unchecked")
	public static void doMethod3() throws Exception {

		String param="FKPD108031705170163";
		System.out.println(">>>>>>>>>>>开始调用OA的webservice，参数："+param+">>>>>>>>");
		String endpoint = "http://192.168.1.171/seeyon/services/invokedeeService";

		try{
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(endpoint));
			
			call.setOperationName(new QName("http://impl.invokedeeService.services.v3x.seeyon.com","runDeeClass"));
			call.addParameter("test", org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
			//call.addParameter(new QName("flowId"), org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
			String message = (String) call.invoke(new Object[] {"预留参数"+param});
			System.out.print(">>>>>>>>>>>webservice返回信息："+message);
			
			
		}catch(Exception e){
			System.out.print(">>>>>>>>>>>WSCaller.toCall调用webservice发生异常！"+e.getMessage());

			throw new Exception("WSCaller.toCall调用webservice发生异常！"+e.getMessage());
		}
		System.out.print(">>>>>>>>>>>调用OA的webservice结束>>>>>>>>");
	}

	@SuppressWarnings("unchecked")
	public static void doMethod2(Call call) throws Exception {

		call.setOperationName("getZJZFYSBugetFromNCSystem");
		call.addParameter(new QName("string"), XMLType.XSD_STRING,
				javax.xml.rpc.ParameterMode.IN);
		
		call.setReturnType(XMLType.XSD_STRING);
		
		Object str= call
				.invoke(new Object[] { "2017-01-01;10299;910;0801"});
		System.out.println(str);
	}
	
	public static String readFileContent(String filename) throws IOException {
		
		String textContest="";
		String line = null;
		BufferedReader in = null;
		
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			return "";
		} catch (FileNotFoundException e) {
			return "";
		}

		try {
			while ((line = in.readLine()) != null) {
				
				textContest += line + "\n";
			}
			in.close();
		} catch (IOException e) {
		}
		
		return textContest;
	}
	
}
