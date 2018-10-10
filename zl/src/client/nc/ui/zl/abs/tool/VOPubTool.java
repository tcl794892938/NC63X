/**
 * nc.vo.bfriend.pub
 * BFPubTool.java
 * copyright - ��Ȩ����
 */
package nc.ui.zl.abs.tool;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// import nc.vo.ic.pub.bill.GeneralBillItemVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.trade.voutils.SafeCompute;

/**
 * �ṩ���õĶ���ת����ƴ�ӡ����ˡ���֤�ȹ���
 * 
 * @BFPubTool.java
 * @BFRIEND
 */

public class VOPubTool {

	public static UFDouble ZERO_DBL = new UFDouble(0f);

	public static UFDouble ONE_DBL = new UFDouble(1f);

	/**
	 * �����Ƿ�Ϸ������֤��
	 * 
	 * @param idcard
	 * @throws BusinessException
	 */
	public static void checkValidIDCard(String idcard) throws BusinessException {
		if (StringUtil.isEmpty(idcard))
			return;

		if (idcard.length() == 15 || idcard.length() == 18) {
			if (idcard
					.matches("[0-8]\\d\\d\\d\\d\\d\\d\\d[0|1][1-9][0-3]\\d\\d\\d\\d")
					|| idcard
							.matches("[0-8]\\d\\d\\d\\d\\d[1-2][0|9]\\d\\d[0|1][1-9][0-3]\\d\\d\\d\\d[0-9a-zA-Z]")) {
			} else {
				throw new BusinessException("���֤���벻��ȷ��");
			}
		} else {
			throw new BusinessException("���֤����Ӧ����15��18λ��");
		}
	}

	/**
	 * �����Ƿ�Ϸ��Ĺ̶��绰����
	 * 
	 * @param ihomephone
	 * @throws BusinessException
	 */
	public static void checkValidHomePhone(String ihomephone)
			throws BusinessException {
		if (StringUtil.isEmpty(ihomephone))
			return;

		if (ihomephone.length() == 11 || ihomephone.length() == 10) {
			if (ihomephone.matches("\\(?0\\d{2}[) -]?\\d{8}")
					|| ihomephone.matches("\\(?0\\d{2}[) -]?\\d{7}")) {
			} else {
				throw new BusinessException("�绰���뺬�����Ż���벻��ȷ��");
			}
		} else {
			throw new BusinessException("�绰λ������");

		}
	}

	/**
	 * �����Ƿ�Ϸ����ֻ���
	 * 
	 * @param mobile
	 * @throws BusinessException
	 */
	public static void checkValidMobile(String mobile) throws BusinessException {
		if (StringUtil.isEmpty(mobile))
			return;

		if (mobile.length() > 0 && (mobile.length() == 11)) {
			if (mobile.matches("[1][3|5]\\d{9}")) {
			} else {
				throw new BusinessException("�ֻ����벻��ȷ��");
			}
		} else {
			throw new BusinessException("�ֻ�����λ������");
		}
	}

	/**
	 * ��������ʽ��ȷ����ַ��� ���磬�����ַ��� i love this game because i am a gamer ���Ҫ�� am �滻��
	 * was , �ǲ���� game �е� am Ҳ���滻
	 * 
	 * @param source
	 * @param strReplace
	 * @param strReplaced
	 * @return
	 */
	public static String replaceAllString(String source, String strReplace,
			String strReplaced) {
		if (StringUtil.isEmpty(source) || StringUtil.isEmpty(strReplace)
				|| strReplaced == null)
			return source;

		String regex = "\\b" + strReplaced + "\\b";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(source); // get a matcher object
		return m.replaceAll(strReplace);
	}

	/**
	 * ��Exception ���н��װ
	 * 
	 * @param ex
	 * @return
	 */
	public static Exception marshException(Exception ex) {
		Throwable cause = ex.getCause();
		Exception e = null;
		if (cause != null && cause instanceof BusinessException) {
			e = marshBusinessException((BusinessException) cause);
		} else if (cause != null && cause instanceof Exception) {
			e = (Exception) cause;
			e = marshException(e);
		} else {
			e = ex;
		}
		return e;
	}

	private static BusinessException marshBusinessException(BusinessException ex) {
		BusinessException e = null;
		Throwable cause = ex.getCause();
		if (cause != null && cause instanceof BusinessException) {
			e = marshBusinessException((BusinessException) cause);
		} else if (cause != null && cause instanceof Exception) {
			Exception e1 = (Exception) cause;
			e1 = marshException(e1);
			if (!(e1 instanceof BusinessException)) {
				e = new BusinessException(e1);
			} else {
				e = (BusinessException) e1;
			}
		} else {
			e = ex;
		}
		return e;
	}

	/**
	 * ���ܣ�����һ�������ֵ�õ�UFBoolean��ֵ�����Ϊ�գ������û�ָ����FALSE��TRUE
	 * 
	 * @param��Object
	 * @return UFBoolean
	 */
	public static UFBoolean getUFBoolean_NullAs(Object value,
			UFBoolean bDefaultValue) {
		if (value == null || value.toString().trim().equals("")) {
			return bDefaultValue;
		} else if (value instanceof UFBoolean) {
			return (UFBoolean) value;
		} else {
			return new UFBoolean(value.toString().trim());
		}
	}

	/**
	 * ���ܣ�����һ�������ֵ�õ�UFDate��ֵ�����Ϊ�գ����ؿ�
	 * 
	 * @param ��Object
	 * @return UFDate
	 */
	public static UFDate getUFDate(Object value) {
		if (value == null || value.toString().trim().equals("")) {
			return null;
		} else if (value instanceof UFDate) {
			return (UFDate) value;
		} else {
			return new UFDate(value.toString().trim());
		}
	}

	/**
	 * ���ܣ�����һ�������ֵ�õ�UFDateTime��ֵ�����Ϊ�գ����ؿ�
	 * 
	 * @param ��Object
	 * @return UFDate
	 */
	public static UFDateTime getUFDateTime(Object value) {
		if (value == null || value.toString().trim().equals("")) {
			return null;
		} else if (value instanceof UFDateTime) {
			return (UFDateTime) value;
		} else {
			return new UFDateTime(value.toString().trim());
		}
	}

	/**
	 * ���ܣ�����һ�������ֵ�õ�UFDouble��ֵ�����Ϊ�գ�������
	 * 
	 * @param Object
	 * @return UFDouble
	 */
	public static UFDouble getUFDouble_NullAsZero(Object value) {
		if (value == null || value.toString().trim().equals("")) {
			return ZERO_DBL;
		} else if (value instanceof UFDouble) {
			return (UFDouble) value;
		} else if (value instanceof BigDecimal) {
			return new UFDouble((BigDecimal) value);
		} else {
			return new UFDouble(value.toString().trim());
		}
	}

	/**
	 * ���ܣ�����һ��double�õ�UFDouble��ֵ
	 * 
	 * @param double
	 * @return UFDouble
	 */
	public static UFDouble getUFDouble_ValueAsValue(double dValue) {
		if (dValue == 0) {
			return ZERO_DBL;
		} else {
			return new UFDouble(dValue);
		}
	}

	/**
	 * ���ܣ�����һ�������ֵ�õ�UFDouble��ֵ���ռ����ؿգ��㼴������
	 * 
	 * @param Object
	 * @return UFDouble
	 */
	public static UFDouble getUFDouble_ValueAsValue(Object value) {
		if (value == null || value.toString().trim().equals("")) {
			return null;
		} else if (value instanceof UFDouble) {
			return (UFDouble) value;
		} else if (value instanceof BigDecimal) {
			return new UFDouble((BigDecimal) value);
		} else {
			return new UFDouble(value.toString().trim());
		}

	}

	/**
	 * ���ܣ�����һ��double��ֵ�õ�UFDouble��ֵ�����Ϊ�㣬���ؿ�
	 * 
	 * @param Object
	 * @return UFDouble
	 */
	public static UFDouble getUFDouble_ZeroAsNull(double dValue) {
		if (dValue == 0) {
			return null;
		} else {
			return new UFDouble(dValue);
		}
	}

	/**
	 * ���ܣ�����һ�������ֵ�õ�UFDouble��ֵ�����Ϊ�㣬���ؿ�
	 * 
	 * @param Object
	 * @return UFDouble
	 */
	public static UFDouble getUFDouble_ZeroAsNull(Object value) {
		UFDouble dValue = getUFDouble_NullAsZero(value);
		if (dValue.compareTo(ZERO_DBL) == 0) {
			return null;
		}
		return dValue;
	}

	/**
	 * �жϴ���UFDouble �� double �Ƿ�Ϊ��
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isUFDoubleZero(Object value) {
		return getUFDouble_NullAsZero(value).compareTo(ZERO_DBL) == 0;
	}

	/**
	 * ���ܣ�����һ�������ֵ�õ�String��ֵ�����Ϊ�մ������ؿ� �÷�����Ҫ������setAttrobuteValue()
	 * 
	 * @param Object
	 * @return String
	 */
	public static String getString_TrimZeroLenAsNull(Object value) {
		if (value == null || value.toString().trim().length() == 0) {
			return null;
		}
		return value.toString().trim();
	}

	/**
	 * ���ܣ�����һ�������ֵ�õ�String��ֵ�����Ϊ�մ������ؿ� �÷�����Ҫ������setAttrobuteValue()
	 * 
	 * @param Object
	 * @return String
	 */
	public static String getString_TrimZeroLenAs(Object value, String str) {
		if (value == null || value.toString().trim().length() == 0) {
			return str;
		}
		return value.toString().trim();
	}

	/**
	 * 1��2��λ��3~7ȡ5��8��9��λ ; ������λǰ����������
	 */
	public static UFDouble round_TO_ZERO_AND_HALF(UFDouble value) {
		if (isUFDoubleZero(value)) {
			return ZERO_DBL;
		} else {
			
			UFDouble neg = new UFDouble(1.0);
			
			if(value.compareTo(ZERO_DBL) < 0){
				neg = neg.multiply(new UFDouble(-1));
			}
			
			value = value.setScale(0, UFDouble.ROUND_HALF_UP).abs();
			String s_value = value.toString();

			if(s_value.length() == 1){
				return value.multiply(neg);
			}
			
			
			
			String s_p = s_value.substring(0, s_value.length() - 1);
			String s_e = s_value.substring(s_p.length());

			int i_p = Integer.parseInt(s_p);
			int i_e = Integer.parseInt(s_e);

			if (i_e < 3) {
				s_e = "0";
			} else if (i_e < 8) {
				s_e = "5";
			} else {
				s_p = String.valueOf(i_p
						+ (1 * (value.div(value.abs())).intValue()));
				s_e = "0";
			}

			String s_calc = s_p + s_e;

			return new UFDouble(s_calc, 0).multiply(neg);
		}
	}

	/**
	 * ����: ���ݴ�����ַ������Ϸ��� SQL IN �ĸ�ʽ�ַ���
	 * 
	 * @param Collection
	 * @return String
	 * @example 'abc','def','ghi'
	 */
	public static String getStringWithStringArray(Collection coll) {
		String inStr = null;
		if (coll != null && coll.size() > 0) {
			StringBuilder sb = new StringBuilder();
			String[] values = (String[]) coll.toArray(new String[0]);
			for (int i = 0; i < values.length; i++) {
				sb.append("'").append(values[i]).append("'");
				if (i < values.length - 1)
					sb.append(",");
			}
			inStr = sb.toString();
		}
		return inStr;
	}

	/**
	 * ����: ���ݴ�����ַ������鷵�� SQL IN �ĸ�ʽ�ַ���
	 * 
	 * @param Collection
	 * @return String
	 * @example 'abc','def','ghi'
	 */
	public static String getStringWithStringArray(String[] strs) {
		String inStr = null;
		if (strs != null && strs.length > 0) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < strs.length; i++) {
				sb.append("'").append(strs[i]).append("'");
				if (i < strs.length - 1)
					sb.append(",");
			}
			inStr = sb.toString();
		}
		return inStr;
	}

	/**
	 * ����һ������ȥƴ�� in �����
	 * 
	 * @param fld
	 * @param arsKey
	 * @param bNullAll
	 * @return
	 */
	public static String getWherePartByKeys(String fld, String[] arsKey,
			boolean bNullAll) {
		final int MAX = 200;
		if (arsKey == null || arsKey.length == 0) {
			return bNullAll ? " 1 = 1 " : "1>2";
		}
		if (arsKey.length == 1)
			return fld + "='" + arsKey[0] + "'";
		if (arsKey.length <= MAX) {
			String sTmp = fld + " in (";
			for (int i = 0; i < arsKey.length; i++) {
				if (i == arsKey.length - 1) {
					sTmp += "'" + arsKey[i] + "')";
					break;
				}
				sTmp += "'" + arsKey[i] + "',";
			}
			return sTmp;
		}
		int ipos = 0;
		int itimes = arsKey.length / MAX;
		int mode = arsKey.length % MAX;
		String where = null;
		for (int i = 0; i < itimes; i++) {
			if (where == null)
				where = " ( " + fld + " in ( ";
			else
				where += " or " + fld + " in (";
			for (int j = 0; j < MAX; j++) {
				if (j == MAX - 1) {
					where += "'" + arsKey[ipos + j] + "')";
					break;
				}
				where += "'" + arsKey[ipos + j] + "',";
			}
			ipos += MAX;
		}
		if (mode == 0)
			where += " )";
		else {
			where += " or " + fld + " in (";
			for (int k = 0; k < mode; k++) {
				if (k == mode - 1) {
					where += "'" + arsKey[ipos + k] + "'))";
					break;
				}
				where += "'" + arsKey[ipos + k] + "',";
			}
		}
		return where;
	}

	public static String getWherePartByKeys(String fld, List listKeys,
			boolean bNullAll) {
		final int MAX = 200;

		String[] arsKey = (String[]) listKeys.toArray(new String[0]);

		if (listKeys == null || listKeys.size() == 0) {
			return bNullAll ? " 1 = 1 " : "1>2";
		}
		if (arsKey == null || arsKey.length == 0) {
			return bNullAll ? " 1 = 1 " : "1>2";
		}
		if (arsKey.length == 1)
			return fld + "='" + arsKey[0] + "'";
		if (arsKey.length <= MAX) {
			String sTmp = fld + " in (";
			for (int i = 0; i < arsKey.length; i++) {
				if (i == arsKey.length - 1) {
					sTmp += "'" + arsKey[i] + "')";
					break;
				}
				sTmp += "'" + arsKey[i] + "',";
			}
			return sTmp;
		}
		int ipos = 0;
		int itimes = arsKey.length / MAX;
		int mode = arsKey.length % MAX;
		String where = null;
		for (int i = 0; i < itimes; i++) {
			if (where == null)
				where = " ( " + fld + " in ( ";
			else
				where += " or " + fld + " in (";
			for (int j = 0; j < MAX; j++) {
				if (j == MAX - 1) {
					where += "'" + arsKey[ipos + j] + "')";
					break;
				}
				where += "'" + arsKey[ipos + j] + "',";
			}
			ipos += MAX;
		}
		if (mode == 0)
			where += " )";
		else {
			where += " or " + fld + " in (";
			for (int k = 0; k < mode; k++) {
				if (k == mode - 1) {
					where += "'" + arsKey[ipos + k] + "'))";
					break;
				}
				where += "'" + arsKey[ipos + k] + "',";
			}
		}
		return where;
	}

	/**
	 * ��VO�����и���ĳһ�ֶι��˳�ָ��ֵ��VO����
	 * 
	 * @param vos
	 * @param filteFld
	 * @param filterValueCombined
	 * @return
	 */
	public static CircularlyAccessibleValueObject[] filterVOByValue(
			CircularlyAccessibleValueObject[] vos, String filteFld[],
			String filterValueCombined) {
		if (vos == null)
			return null;
		java.util.ArrayList al = new java.util.ArrayList();
		for (int i = 0; i < vos.length; i++) {
			String key = "";
			for (int j = 0; j < filteFld.length; j++) {
				Object value = vos[i].getAttributeValue(filteFld[j]);
				if (value == null)
					break;
				key += value.toString();
			}
			if (key == null || key.length() == 0)
				continue;
			if (key.equals(filterValueCombined))
				al.add(vos[i]);
			else
				continue;
		}
		vos = new CircularlyAccessibleValueObject[al.size()];
		for (int i = 0; i < vos.length; i++)
			vos[i] = (CircularlyAccessibleValueObject) al.get(i);
		return vos;
	}

	/**
	 * ��VO�����и���ĳһ�ֶι��˳�ָ��ֵ��VO����
	 * 
	 * @param vos
	 * @param filteFld
	 * @param filterValue
	 * @return
	 */
	public static CircularlyAccessibleValueObject[] filterVOByValue(
			CircularlyAccessibleValueObject[] vos, String filteFld,
			String filterValue) {
		if (vos == null)
			return null;
		java.util.ArrayList al = new java.util.ArrayList();
		for (int i = 0; i < vos.length; i++) {
			Object value = vos[i].getAttributeValue(filteFld);
			if (value == null)
				continue;
			if (value.equals(filterValue))
				al.add(vos[i]);
		}
		vos = new CircularlyAccessibleValueObject[al.size()];
		for (int i = 0; i < vos.length; i++)
			vos[i] = (CircularlyAccessibleValueObject) al.get(i);
		return vos;
	}

	/**
	 * ��һ��VO�������ҵ�һ��ĳ���ֶ�ֵ��VO .
	 * 
	 * @param vos
	 * @param field
	 * @param value
	 * @return
	 */
	public static CircularlyAccessibleValueObject getExitVOByValue(
			CircularlyAccessibleValueObject[] vos, String field, Object value) {
		Object o = null;
		if (vos == null || vos.length == 0)
			return null;
		for (int i = 0; i < vos.length; i++) {
			o = vos[i].getAttributeValue(field);
			if (o == null)
				continue;
			if (o.equals(value))
				return vos[i];
		}
		return null;
	}

	/**
	 * ��һ��VO�������ҵ�һ��ĳ���ֶ�ֵ��VO ������.
	 * 
	 * @param vos
	 * @param field
	 * @param value
	 * @return
	 */
	public static int getExitVONumByValue(
			CircularlyAccessibleValueObject[] vos, String field, Object value) {
		Object o = null;
		int iNum = 0;
		if (vos == null || vos.length == 0)
			return iNum;
		for (int i = 0; i < vos.length; i++) {
			o = vos[i].getAttributeValue(field);
			if (o == null)
				continue;
			if (o.equals(value))
				iNum++;
		}
		return iNum;
	}

	/**
	 * ��֤:��һ��VO�������Ƿ����ĳ�ֶμ�������һ��������ֵ��
	 * 
	 * @param vos
	 * @param filteFld
	 * @param filterValueCombined
	 * @return
	 */
	public static boolean isExitVOByValue(
			CircularlyAccessibleValueObject[] vos, String filteFld[],
			String filterValueCombined) {
		if (vos == null)
			return false;
		for (int i = 0; i < vos.length; i++) {
			String key = "";
			for (int j = 0; j < filteFld.length; j++) {
				Object value = vos[i].getAttributeValue(filteFld[j]);
				if (value == null)
					break;
				key += value.toString();
			}
			if (key.equals(filterValueCombined))
				return true;
			else
				continue;
		}
		return false;
	}

	/**
	 * ��һ��VO�������Ƿ����ĳһ���ֶ�����һ��������ֵ��
	 * 
	 * @param vos
	 * @param field
	 * @param value
	 * @return
	 */
	public static boolean isExitVOByValue(
			CircularlyAccessibleValueObject[] vos, String field, Object value) {
		Object o = null;
		if (vos == null || vos.length == 0)
			return false;
		for (int i = 0; i < vos.length; i++) {
			o = vos[i].getAttributeValue(field);
			if (o == null)
				continue;
			if (o.equals(value))
				return true;
		}
		return false;
	}

	/**
	 * ȷ������VO��ֵ�Ƿ���ȡ�
	 * 
	 * @param vo1
	 * @param vo2
	 * @return
	 */
	public static boolean isSameVO(CircularlyAccessibleValueObject vo1,
			CircularlyAccessibleValueObject vo2) {
		String[] attrs = vo1.getAttributeNames();
		for (int i = 0; i < attrs.length; i++) {
			Object o1 = vo1.getAttributeValue(attrs[i]);
			Object o2 = vo2.getAttributeValue(attrs[i]);
			if (o1 == null && o2 != null)
				return false;
			if (o1 != null && o2 == null)
				return false;
			if (o1 == null && o2 == null)
				continue;
			if (o1.toString().equals(o2.toString()))
				continue;
			else
				return false;
		}
		return true;
	}

	/**
	 * ����һ��VO�õ�һ���ֶε����е�ֵ
	 * 
	 * @param vos
	 * @param attrName
	 * @return
	 */
	public static Object[] getAllValueObject(
			CircularlyAccessibleValueObject[] vos, String attrName) {
		if (vos == null || vos.length == 0)
			return null;
		if (StringUtil.isEmpty(attrName))
			return null;
		ArrayList al = new ArrayList();
		for (int i = 0; i < vos.length; i++) {
			Object o = vos[i].getAttributeValue(attrName);
			if (o == null)
				continue;
			al.add(o);
		}
		return al.toArray();
	}

	/**
	 * ����һ��VO�õ�һ���ֶε����е�ֵ
	 * 
	 * @param vos
	 * @param attrName
	 * @return
	 */
	public static String[] getAllValueString(
			CircularlyAccessibleValueObject[] vos, String attrName) {
		if (vos == null || vos.length == 0)
			return null;
		if (StringUtil.isEmpty(attrName))
			return null;
		ArrayList al = new ArrayList();
		for (int i = 0; i < vos.length; i++) {
			String str = getString_TrimZeroLenAsNull(vos[i]
					.getAttributeValue(attrName));
			if (str == null)
				continue;
			al.add(str);
		}
		return (String[]) al.toArray(new String[0]);
	}

	/**
	 * ����һ��VO�õ�һ���ֶε����е�ֵ
	 * 
	 * @param vos
	 * @return
	 * @throws BusinessException
	 */
	public static String[] getAllPrimaryKey(
			CircularlyAccessibleValueObject[] vos) throws BusinessException {
		if (vos == null || vos.length == 0)
			return null;

		ArrayList al = new ArrayList();
		for (int i = 0; i < vos.length; i++) {
			String pk = vos[i].getPrimaryKey();
			if (StringUtil.isEmptyWithTrim(pk))
				continue;
			al.add(pk);
		}
		return (String[]) al.toArray(new String[0]);
	}

	/**
	 * ����ָ���ֶη����ֶε�SUMֵ,�����ֶα��붼��UFDouble����,�������쳣
	 * 
	 * @param vos
	 * @param fields
	 * @return UFDouble[]
	 * @throws BusinessException
	 */
	public static UFDouble[] getSumValue(CircularlyAccessibleValueObject[] vos,
			String fields[]) throws BusinessException {

		UFDouble[] rtnValues = new UFDouble[fields.length];
		for (int i = 0; i < rtnValues.length; i++) {
			rtnValues[i] = new UFDouble(0.0);
		}

		if (vos == null || vos.length == 0) {
			return rtnValues;
		}

		try {
			for (int i = 0; i < vos.length; i++) {
				for (int j = 0; j < fields.length; j++) {
					UFDouble ufd_fld = getUFDouble_NullAsZero(vos[i]
							.getAttributeValue(fields[j]));

					rtnValues[j] = SafeCompute.add(rtnValues[j], ufd_fld);
				}
			}
		} catch (Exception e) {
			throw new BusinessException("nc.vo.bfriend.pub.VOUtil:getSumValue:"
					+ e.getMessage(), e);
		}

		return rtnValues;
	}

	// /**
	// * �ж��Ƿ����������
	// * ����: zhongwei
	// * ����ʱ��: 2011-6-29����05:39:25
	// * @param gbill
	// * @return
	// */
	// public static Integer getIsFreeMgt(GeneralBillItemVO itemVO) {
	// Integer iFalse = new Integer(0);
	// Integer iTrue = new Integer(1);
	//
	// if (itemVO == null || itemVO.getFreeItemVO() == null) {
	// return iFalse;
	// }
	//
	// if (!StringUtil.isEmptyWithTrim(itemVO.getVfree1())
	// || !StringUtil.isEmptyWithTrim(itemVO.getFreeItemVO()
	// .getVfree1())) {
	// return iTrue;
	// } else if (!StringUtil.isEmptyWithTrim(itemVO.getVfree1())
	// || !StringUtil.isEmptyWithTrim(itemVO.getFreeItemVO()
	// .getVfree1())) {
	// return iTrue;
	// } else if (!StringUtil.isEmptyWithTrim(itemVO.getVfree2())
	// || !StringUtil.isEmptyWithTrim(itemVO.getFreeItemVO()
	// .getVfree2())) {
	// return iTrue;
	// } else if (!StringUtil.isEmptyWithTrim(itemVO.getVfree3())
	// || !StringUtil.isEmptyWithTrim(itemVO.getFreeItemVO()
	// .getVfree3())) {
	// return iTrue;
	// } else if (!StringUtil.isEmptyWithTrim(itemVO.getVfree4())
	// || !StringUtil.isEmptyWithTrim(itemVO.getFreeItemVO()
	// .getVfree4())) {
	// return iTrue;
	// } else if (!StringUtil.isEmptyWithTrim(itemVO.getVfree5())
	// || !StringUtil.isEmptyWithTrim(itemVO.getFreeItemVO()
	// .getVfree5())) {
	// return iTrue;
	// } else if (!StringUtil.isEmptyWithTrim(itemVO.getVfree6())
	// || !StringUtil.isEmptyWithTrim(itemVO.getFreeItemVO()
	// .getVfree6())) {
	// return iTrue;
	// } else if (!StringUtil.isEmptyWithTrim(itemVO.getVfree7())
	// || !StringUtil.isEmptyWithTrim(itemVO.getFreeItemVO()
	// .getVfree7())) {
	// return iTrue;
	// } else if (!StringUtil.isEmptyWithTrim(itemVO.getVfree8())
	// || !StringUtil.isEmptyWithTrim(itemVO.getFreeItemVO()
	// .getVfree8())) {
	// return iTrue;
	// } else if (!StringUtil.isEmptyWithTrim(itemVO.getVfree9())
	// || !StringUtil.isEmptyWithTrim(itemVO.getFreeItemVO()
	// .getVfree9())) {
	// return iTrue;
	// } else if (!StringUtil.isEmptyWithTrim(itemVO.getVfree10())
	// || !StringUtil.isEmptyWithTrim(itemVO.getFreeItemVO()
	// .getVfree10())) {
	// return iTrue;
	// }
	//
	// return iFalse;
	// }
	//	
	// /**
	// * �ж��Ƿ����ι���
	// * ����: zhongwei
	// * ����ʱ��: 2011-6-29����05:48:45
	// * @return
	// */
	// public static Integer getIsLotMgt(GeneralBillItemVO itemVO){
	// Integer iFalse = new Integer(0);
	// Integer iTrue = new Integer(1);
	//		
	// if (itemVO == null) {
	// return iFalse;
	// }
	//		
	// if(!StringUtil.isEmptyWithTrim(itemVO.getVbatchcode())){
	// return iTrue;
	// }
	//		
	// return iFalse;
	// }
}
