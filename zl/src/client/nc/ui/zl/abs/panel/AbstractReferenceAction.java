package nc.ui.zl.abs.panel;

import java.util.List;

import nc.funcnode.ui.action.INCAction;
import nc.ui.pubapp.billref.dest.TransferBillViewProcessor;
import nc.ui.uif2.NCAction;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;


public abstract class AbstractReferenceAction extends NCAction {
  public static final String PREX_ACTION_CODE = "Ref";

  private static final long serialVersionUID = 5414584277167603113L;

  private String btShowName;

  /** ҵ������ */
  private List<String> busitypes;

  private boolean flowBillType = true;

  private boolean isShowNameSetted = false;

  private String sourceBillName;

  private String sourceBillType;

  private TransferBillViewProcessor transferBillViewProcessor;

  private TransferViewProcessor transferViewProcessor;

  private List<String> transtypes;

  public String getBtShowName() {
    return this.btShowName;
  }

  /**
   * @return ҵ������
   */
  public List<String> getBusitypes() {
    return this.busitypes;
  }

  /**
   * 
   * �������������������Դ�������͵����ƣ�������Ϊ��ť�����֡�
   * <p>
   * <b>����˵��</b>
   * 
   * @return ��Դ�������͵�����
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-1-27 ����04:39:34
   */
  public String getSourceBillName() {
    return this.sourceBillName;
  }

  /**
   * 
   * �������������������Դ�������͡�
   * <p>
   * <b>����˵��</b>
   * 
   * @return ��Դ�������ͣ����Ƶ����뷵��nc.ui.pu.pub.action.ReferenceAction.MANUAL_CODE
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-1-27 ����04:38:44
   */
  public String getSourceBillType() {
    return this.sourceBillType;
  }

  /**
   * 
   * �����������������ת���Ĵ�ת���洦������
   * <p>
   * <b>����˵��</b>
   * 
   * @return ת��������
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-2-3 ����02:35:19
   * @deprecated ��������UE�淶�����������������ʾ������ʹ��{@link #getTransferViewProcessor()}
   */
  @Deprecated
  public TransferBillViewProcessor getTransferBillViewProcessor() {
    return this.transferBillViewProcessor;
  }

  public TransferViewProcessor getTransferViewProcessor() {
    return this.transferViewProcessor;
  }

  public List<String> getTranstypes() {
    return this.transtypes;
  }

  /**
   * 
   * ����������������Դ�����Ƿ���Խ����������á�
   * <p>
   * <b>����˵��</b>
   * 
   * @return true-���Խ����������õĵ��ݣ�false-��������������
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-1-28 ����10:28:49
   */
  public boolean isFlowBillType() {
    return this.flowBillType;
  }

  /**
   * 
   * �����������������ð�ť��ʾ���ơ�������ô˷��������޷�ͨ��setSourceBillType()�Զ���ȡ�������ƶ�����������ö���
   * 
   * <p>
   * <b>����˵��</b>
   * 
   * @param btShowName
   *          ��ť��ʾ���ơ�
   *          <p>
   * 
   */
  public void setBtShowName(String btShowName) {
    this.isShowNameSetted = true;
    this.btShowName = btShowName;
    this.setBtnNameByOrder();
  }

  /**
   * @param busitypes
   *          ҵ������
   */
  public void setBusitypes(List<String> busitypes) {
    this.busitypes = busitypes;
  }

  /**
   * 
   * ����������������Դ�����Ƿ���Խ����������á�
   * <p>
   * <b>����˵��</b>
   * 
   * @param flowBillType
   *          true-���Խ����������ã�false-���ܽ�����������
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-1-28 ����10:36:47
   */
  public void setFlowBillType(boolean flowBillType) {
    this.flowBillType = flowBillType;
  }

  public void setSourceBillName(String sourceBillName) {
    this.sourceBillName = sourceBillName;
    this.setBtnNameByOrder();
  }

  public void setSourceBillType(String sourceBillType) {
    this.sourceBillType = sourceBillType;
    if (this.getSourceBillType().equals("MANUAL")) {
      this.setSourceBillName(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("pubapp_0", "0pubapp-0295")/* * @res* "����" */);
    }
    // ���ӿ��жϣ���ֹ�����ڹ�����������ֵ�����︲�ǵ�
    if (StringUtils
        .isBlank(ObjectUtils.toString(this.getValue(INCAction.CODE)))) {
      this.setCode(AbstractReferenceAction.PREX_ACTION_CODE + sourceBillType);
    }
    this.setBtnNameByOrder();
  }

  /**
   * 
   * 
   * @param transferBillViewProcessor
   * @deprecated ��������UE�淶�����������������ʾ������ʹ��
   *             {@link #setTransferBillViewProcessor(TransferBillViewProcessor)}
   */
  @Deprecated
  public void setTransferBillViewProcessor(
      TransferBillViewProcessor transferBillViewProcessor) {
    this.transferBillViewProcessor = transferBillViewProcessor;
  }

  public void setTransferViewProcessor(
      TransferViewProcessor transferViewProcessor) {
    this.transferViewProcessor = transferViewProcessor;
  }

  public void setTranstypes(List<String> transtypes) {
    this.transtypes = transtypes;
  }

  /**
   * 
   * ���������������Ƿ������ư�ť��
   * <p>
   * <b>����˵��</b>
   * 
   * @return true-���ư�ť��false-�������ư�ť
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-1-28 ����10:43:11
   */
  protected boolean isManual() {
    return false;
  }

  private void setBtnNameByOrder() {
    if (this.isShowNameSetted) {
      this.setBtnName(this.getBtShowName());
      return;
    }
    else if (this.getSourceBillName() != null
        && this.getSourceBillType() != null) {
      if (!nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes()
          .getCurrLanguage()
          .getCode()
          .equals(
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getDefaultLanguage()
                  .getCode())) {
        this.tranMultiLangName();
      }
      this.setBtnName(this.getSourceBillName());
    }
    else if (this.getSourceBillName() == null) {
      this.setBtnName("��������Դ�������ƣ�Ĭ�����֣���");/*-=notranslate=-*/
    }
    else if (this.getSourceBillType() == null) {
      this.setBtnName("��������Դ�������ͱ��룡");/*-=notranslate=-*/
    }
  }

  private void tranMultiLangName() {
    if (this.sourceBillType.equals("MANUAL")) {
      this.sourceBillName =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("pubapp_0",
              "0pubapp-0295")/* * @res* "����" */;
    }
    else {
      this.sourceBillName =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("billtype",
              "D" + this.sourceBillType);
    }
  }

}
