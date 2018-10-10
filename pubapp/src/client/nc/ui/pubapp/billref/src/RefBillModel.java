package nc.ui.pubapp.billref.src;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.event.EventListenerList;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.itf.uap.pf.IPFConfig;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.AppEvent;
import nc.ui.uif2.AppEventListener;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.ui.uif2.model.AppEventConst;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;

import org.apache.commons.lang.StringUtils;

/**
package nc.ui.pubapp.billref.src;

import java.lang.reflect.Array;

/**
 * @author yangb
 */
public class RefBillModel extends AbstractUIAppModel {

  public class BillSelectedStatus {
    AggregatedValueObject bill;

    Map<String, UFBoolean> bodyRowSelectedStatusMap =
        new HashMap<String, UFBoolean>();

    boolean isBillSelected;

    public BillSelectedStatus(AggregatedValueObject bill) {
      this.bill = bill;
      this.initBodyMap();

    }

    public int getSelectBodyRowLength() {
      int length = 0;
      for (UFBoolean value : this.bodyRowSelectedStatusMap.values()) {
        if (value.booleanValue()) {
          length++;
        }
      }
      return length;
    }

    public void initBodyMap() {
    	CircularlyAccessibleValueObject[] childrenVOByClass = getChildrenVOByClass(this.bill);
      if (childrenVOByClass == null) {
        return;
      }
      this.bodyRowSelectedStatusMap.clear();
      try {
        for (CircularlyAccessibleValueObject bodyVO : childrenVOByClass) {
          this.bodyRowSelectedStatusMap.put(bodyVO.getPrimaryKey(),
              UFBoolean.valueOf(false));
        }
      }
      catch (Exception e) {
        ExceptionUtils.wrappException(e);
      }
    }

    public boolean isBillSelected() {
      return this.isBillSelected;
    }

    public boolean isBodyRowSelect(String bodyId) {
      if (this.bodyRowSelectedStatusMap.containsKey(bodyId)) {
        return this.bodyRowSelectedStatusMap.get(bodyId).booleanValue();
      }
      return false;
    }

    public void selectAll(boolean ifSelected) {
      
      if (this.bodyRowSelectedStatusMap.isEmpty()) {
    	  this.selectBodyRow("", ifSelected);
      }else {
    	  for (String key : this.bodyRowSelectedStatusMap.keySet()) {
    	        this.selectBodyRow(key, ifSelected);
    	        // bodyRowSelectedStatusMap.put(key, new UFBoolean(ifSelected));
    	      } 
      }
      
      this.isBillSelected = ifSelected;
    }

    public void selectBill(boolean ifSelected) {
      this.selectAll(ifSelected);
    }

    public void selectBodyRow(String bodyId, boolean ifSelected) {
    	if (!StringUtils.isEmpty(bodyId)) {
    		
    		this.bodyRowSelectedStatusMap.put(bodyId, UFBoolean.valueOf(ifSelected));
    	}
      if (ifSelected) {
        try {
          RefBillModel.this.getSelectedDataCollection().selectData(
              this.bill.getParentVO().getPrimaryKey(), bodyId);
        }
        catch (BusinessException e) {
          Logger.error(e);
        }
      }
      this.updateHeadStatus();
    }

    public void selectHeadOnly(boolean ifSelected) {
      this.isBillSelected = ifSelected;
    }

    private void updateHeadStatus() {
      boolean ifHeadSelected = false;
      for (UFBoolean value : this.bodyRowSelectedStatusMap.values()) {
        if (value.booleanValue()) {
          ifHeadSelected = true;
          break;
        }
      }
      this.isBillSelected = ifHeadSelected;
    }
  }

  public class RowSelectManager {
    Map<String, RefBillModel.BillSelectedStatus> billStatusMap =
        new HashMap<String, RefBillModel.BillSelectedStatus>();

    public RowSelectManager(Map<String, AggregatedValueObject> mapvos) {
      for (Entry<String, AggregatedValueObject> entry : mapvos.entrySet()) {
        BillSelectedStatus selectedStatus =
            new BillSelectedStatus(entry.getValue());
        this.billStatusMap.put(entry.getKey(), selectedStatus);
      }
    }

    public Map<String, RefBillModel.BillSelectedStatus> getBillStatusMap() {
      return this.billStatusMap;
    }

    public int getSelectBillCount() {
      int count = 0;
      for (BillSelectedStatus billStatus : this.billStatusMap.values()) {
        if (billStatus.isBillSelected) {
          count++;
        }
      }
      return count;
    }

    public int getSelectBillLength() {
      int length = 0;
      for (BillSelectedStatus value : this.billStatusMap.values()) {
        if (value.isBillSelected) {
          length++;
        }
      }
      return length;
    }

    public int getSelectBillRowCount() {
      int count = 0;
      for (BillSelectedStatus billStatus : this.billStatusMap.values()) {
        count += billStatus.getSelectBodyRowLength();
      }
      return count;
    }

    public int getSelectBodyLenth(String billId) {
      return this.billStatusMap.get(billId).getSelectBodyRowLength();
    }

    public boolean isBillSelected(String billId) {
      return this.billStatusMap.get(billId).isBillSelected();
    }

    public boolean isBodyRowSelect(String billId, String bodyId) {
    	boolean isSelect=false;
    	try{
    		isSelect=this.billStatusMap.get(billId).isBodyRowSelect(bodyId);
    	}catch(Exception e){  		
    	Logger.error(new Exception("请检查单据模板上主子表的主键是否放上了。"));
    	}
      return isSelect;
    }

    public void selectAll(boolean ifSelected) {
      for (String headPK : this.billStatusMap.keySet()) {
        this.billStatusMap.get(headPK).selectBill(ifSelected);
      }
    }
    
	public void selectMore(boolean ifSelected, List<String> keys, boolean isSingleTable) {
		for (String headPK : this.billStatusMap.keySet()) {
			if (!isSingleTable && keys.contains(headPK)) {
				this.billStatusMap.get(headPK).selectBill(ifSelected);
			}
			if (isSingleTable) {
				BillSelectedStatus bss = this.billStatusMap.get(headPK);

				for (String key : keys) {
					String[] pks = StringUtils.split(key, "@");

					if (StringUtils.equalsIgnoreCase(pks[0], headPK)) {
						bss.selectHeadOnly(ifSelected);
						bss.selectBodyRow(pks[1], ifSelected);
					}
				}
			}
		}
	}

    public void selectBill(String billId, boolean ifSelected) {
      this.billStatusMap.get(billId).selectBill(ifSelected);
    }

    public void selectBodyAll(String headPk, boolean ifSelected) {
      this.billStatusMap.get(headPk).selectAll(ifSelected);
    }

    public void selectBodyRow(String billId, String bodyId, boolean ifSelected) {
      this.billStatusMap.get(billId).selectBodyRow(bodyId, ifSelected);

    }

    public void selectCollectionMore(boolean ifSelected, List<String> keys) {
      for (Entry<String, Set<String>> entry : RefBillModel.this
          .getSelectedDataCollection().getSelectedCollection().entrySet()) {
        if (keys.contains(entry.getKey())) {
    		this.billStatusMap.get(entry.getKey()).selectHeadOnly(ifSelected);
    	}
        
        for (String bodyPK : entry.getValue()) {
          this.billStatusMap.get(entry.getKey()).selectBodyRow(bodyPK,
              ifSelected);
        }
      }
    }
    
    public void selectCollectionMoreBySingle(boolean ifSelected, List<String> keys) {
        for (Entry<String, Set<String>> entry : RefBillModel.this
            .getSelectedDataCollection().getSelectedCollection().entrySet()) {
          
          for (String bodyPK : entry.getValue()) {
        	  if (keys.contains(entry.getKey() + "@" + bodyPK)) {
        		  this.billStatusMap.get(entry.getKey()).selectHeadOnly(ifSelected);
        		  this.billStatusMap.get(entry.getKey()).selectBodyRow(bodyPK, ifSelected);
        	  }
          }
        }
      }

    public void selectCollectionBodyAll(String headPK, boolean ifSelected) {
      for (String bodyPK : RefBillModel.this.getSelectedDataCollection()
          .getSelectedCollection().get(headPK)) {
        this.billStatusMap.get(headPK).selectBodyRow(bodyPK, ifSelected);
      }
    }

    public void selectHeadOnly(String billId, boolean ifSelected) {
      this.billStatusMap.get(billId).selectHeadOnly(ifSelected);
    }

    public void setBillStatusMap(
        Map<String, RefBillModel.BillSelectedStatus> billStatusMap) {
      this.billStatusMap = billStatusMap;
    }
  }

  public class SelectedDataCollection {
    private Map<String, Set<String>> selectedCollection =
        new HashMap<String, Set<String>>();

    public void deleteAllData() {
      this.selectedCollection.clear();
    }

    public void deleteAllUnSelectedBodyData() {
      Set<String> deleteHeadPKs = new HashSet<String>();
      for (Entry<String, Set<String>> entry : this.selectedCollection
          .entrySet()) {
        String headPK = entry.getKey();
        List<String> deleteBodyPKs = new ArrayList<String>();
        for (String bodyPK : entry.getValue()) {
          if (!RefBillModel.this.getRowSelectManager().isBodyRowSelect(headPK,
              bodyPK)) {
            if (this.selectedCollection.containsKey(headPK)) {
              deleteBodyPKs.add(bodyPK);
            }
          }
        }
        for (String deletePK : deleteBodyPKs) {
          entry.getValue().remove(deletePK);
        }
        if (entry.getValue().size() == 0) {
          deleteHeadPKs.add(headPK);
        }
      }
      for (String deletePK : deleteHeadPKs) {
        this.selectedCollection.remove(deletePK);
      }
    }

    public void deleteSelectedBill(String headPK) {
      this.selectedCollection.remove(headPK);
    }

    public void deleteSelectedBodyData(String headPK, String bodyPK) {
      if (this.selectedCollection.containsKey(headPK)) {
        this.selectedCollection.get(headPK).remove(bodyPK);
        if (this.selectedCollection.get(headPK).size() == 0) {
          this.selectedCollection.remove(headPK);
        }
      }
    }

    public int getLength() {
      return this.selectedCollection.size();
    }

    public int getSelectBodyLenth(String headPK) {
      if (headPK == null) {
        return -1;
      }
      return this.selectedCollection.get(headPK).size();
    }

    public Map<String, Set<String>> getSelectedCollection() {
      return this.selectedCollection;
    }

    public boolean isInCollection(String headPK) {
      return this.selectedCollection.containsKey(headPK);
    }

    public void selectData(String headPK, String bodyPK) {
      if (!this.selectedCollection.containsKey(headPK)) {
        this.selectedCollection.put(headPK, new HashSet<String>());
        if (!StringUtils.isEmpty(bodyPK)) {
        	this.selectedCollection.get(headPK).add(bodyPK);
        }
      }
      else {
        Set<String> bodySelectList = this.selectedCollection.get(headPK);
        if (!bodySelectList.contains(bodyPK) && !StringUtils.isEmpty(bodyPK)) {
          bodySelectList.add(bodyPK);
        }
      }
    }

    public void setSelectedCollection(
        Map<String, Set<String>> selectedCollection) {
      this.selectedCollection = selectedCollection;
    }
  }

  private List<AggregatedValueObject> listbillvos =
      new ArrayList<AggregatedValueObject>();

  private EventListenerList listeners = new EventListenerList();

  private Map<String, AggregatedValueObject> mapvos =
      new HashMap<String, AggregatedValueObject>();

  private IQueryScheme queryScheme;

  private RefInfo refInfo;

  private RowSelectManager rowSelectManager;

  private SelectedDataCollection selectedDataCollection =
      new SelectedDataCollection();

  private String sqlWhere;

  /**
   *
   */
  public RefBillModel() {
    // TODO Auto-generated constructor stub
    super();
  }

  @Override
  public void addAppEventListener(AppEventListener l) {
    // TODO Auto-generated method stub
    this.listeners.remove(AppEventListener.class, l);
    this.listeners.add(AppEventListener.class, l);
  }

  @Override
  public void fireEvent(AppEvent event) {
    AppEventListener[] ls = this.listeners.getListeners(AppEventListener.class);
    for (AppEventListener listener : ls) {
      listener.handleEvent(event);
    }
  }

  public AggregatedValueObject[] getAllBillVOs() {
    if (this.listbillvos == null || this.listbillvos.size() <= 0) {
      return null;
    }
    return new ListToArrayTool<AggregatedValueObject>()
        .convertToArray(this.listbillvos);
  }

  public AggregatedValueObject getBillVO(String key) {
    if (key == null) {
      return null;
    }
    return this.mapvos.get(key);
  }

  public SuperVO[] getBodyVOs(String key) {
    AggregatedValueObject billvo = this.getBillVO(key);
    if (billvo == null) {
      return null;
    }
    
    CircularlyAccessibleValueObject[] childrenVOByClass = getChildrenVOByClass(billvo);
	if (childrenVOByClass == null || childrenVOByClass.length <= 0) {
      try {
        // 查询子表VO数组
        CircularlyAccessibleValueObject[] bodyvos =
            NCLocator
                .getInstance()
                .lookup(IPFConfig.class)
                .queryBodyAllData(
                    this.getRefInfo().getBillSrcVar().getBillType(), key,
                    this.getSqlWhere());
        if (this.getRefInfo().isFakePKUsed()) {
          VOFakePkControl.transferVOWithfFakePK(bodyvos);
        }
        billvo.setChildrenVO(bodyvos);
        this.getRowSelectManager().getBillStatusMap().get(key).initBodyMap();
      }
      catch (Exception e) {
        ExceptionUtils.unmarsh(e);
      }
    }
    return (SuperVO[]) childrenVOByClass;
  }



  public SuperVO[] getCollectBodyVOs(String billId) {
    AggregatedValueObject bill = this.mapvos.get(billId);
    bill = this.getCollectionVO(bill, billId);
    return (SuperVO[]) getChildrenVOByClass(bill);
  }

  public AggregatedValueObject[] getCollectedVOs() {
    AggregatedValueObject[] selectedVos =
        (AggregatedValueObject[]) Array.newInstance(this.getRefInfo()
            .getBillVO().getClass(), this.getSelectedDataCollection()
            .getLength());
    int i = 0;
    for (String billPK : this.mapvos.keySet()) {
      if (this.getSelectedDataCollection().isInCollection(billPK)) {
        selectedVos[i] = this.mapvos.get(billPK);
        selectedVos[i] = this.getCollectionVO(selectedVos[i], billPK);
        i++;
      }
    }
    return selectedVos;

  }

  public CircularlyAccessibleValueObject[] getCollectionChildren(String headPk) {
	  
		if (this.getSelectedDataCollection().getSelectBodyLenth(headPk) <= 0) {
			return null;
		}
	  
    CircularlyAccessibleValueObject[] newChildren =
        (CircularlyAccessibleValueObject[]) Array.newInstance(this.getRefInfo()
            .getBodyVO().getClass(), this.getSelectedDataCollection()
            .getSelectBodyLenth(headPk));
    try {
      AggregatedValueObject bill = this.mapvos.get(headPk);
      CircularlyAccessibleValueObject[] oldChildren = getChildrenVOByClass(bill);

      int i = 0;
      for (CircularlyAccessibleValueObject bodyVO : oldChildren) {
        String bodyPk = bodyVO.getPrimaryKey();
        if (this.getSelectedDataCollection().getSelectedCollection()
            .get(headPk).contains(bodyPk)) {
          newChildren[i] = bodyVO;
          i++;
        }
      }
    }
    catch (Exception e) {
      Logger.error(e);
    }
    return newChildren;
  }

  public IQueryScheme getQueryScheme() {
    return this.queryScheme;
  }

  public RefInfo getRefInfo() {
    return this.refInfo;
  }

  public RowSelectManager getRowSelectManager() {
    return this.rowSelectManager;
  }

  public SuperVO[] getSelectBodyVOs(String billId) {
    AggregatedValueObject bill = this.mapvos.get(billId);
    bill = this.filtedChildVOs(bill, billId);
    return (SuperVO[]) getChildrenVOByClass(bill);
  }

  @Override
  public Object getSelectedData() {
    // TODO Auto-generated method stub
    return null;
  }

  public SelectedDataCollection getSelectedDataCollection() {
    return this.selectedDataCollection;
  }

  public AggregatedValueObject[] getSelectedFiltedVOs() {
    if (this.mapvos.size() == 0) {
      return (AggregatedValueObject[]) Array.newInstance(this.getRefInfo()
          .getBillVO().getClass(), 0);
    }
    AggregatedValueObject[] selectedVos =
        (AggregatedValueObject[]) Array.newInstance(this.getRefInfo()
            .getBillVO().getClass(), this.getRowSelectManager()
            .getSelectBillLength());
    int i = 0;
    for (String billPK : this.mapvos.keySet()) {
      if (this.getRowSelectManager().isBillSelected(billPK)) {
        selectedVos[i] = this.mapvos.get(billPK);
        selectedVos[i] = this.filtedChildVOs(selectedVos[i], billPK);
        i++;
      }
    }
    return selectedVos;
  }

  public String getSqlWhere() {
    // return this.sqlWhere;
    return this.queryScheme.getTableJoinFromWhereSQL().toString();
  }

  @Override
  public void initModel(Object data) {
    // TODO Auto-generated method stub

  }

  @Override
  public void removeAppEventListener(AppEventListener l) {
    // TODO Auto-generated method stub
    this.listeners.remove(AppEventListener.class, l);
  }

  public void setBillVOs(AggregatedValueObject[] vos) {
    this.listbillvos.clear();
    this.mapvos.clear();
    this.selectedDataCollection.getSelectedCollection().clear();
    if (vos == null || vos.length <= 0) {
      this.fireEvent(new AppEvent(AppEventConst.DATA_REFRESHED, this, null));
      return;
    }
    if (this.getRefInfo().isFakePKUsed()) {
      VOFakePkControl.transferAggVOWithFakePK(vos);
    }
    String pkbillfield =
        this.getRefInfo().getHeadVO().getMetaData().getPrimaryAttribute()
            .getColumn().getName();
    String key = null;
    for (AggregatedValueObject vo : vos) {
      key = (String) vo.getParentVO().getAttributeValue(pkbillfield);
      if (key == null) {
        continue;
      }
      this.listbillvos.add(vo);
      this.mapvos.put(key, vo);
    }
    this.rowSelectManager = new RowSelectManager(this.mapvos);
    this.fireEvent(new AppEvent(AppEventConst.DATA_REFRESHED, this, null));
  }

  public void setHeadVOs(SuperVO[] vos) {

    if (vos == null || vos.length <= 0) {
      this.setBillVOs(null);
      return;
    }
    // if (getRefInfo().isFakePKUsed()) {
    // VOFakePkControl.transferVOWithfFakePK(vos);
    // }
    AggregatedValueObject[] billvos = new AggregatedValueObject[vos.length];
    Class<?> cl = this.getRefInfo().getBillVO().getClass();
    for (int i = 0; i < billvos.length; i++) {
      billvos[i] = (AggregatedValueObject) Constructor.construct(cl);
      billvos[i].setParentVO(vos[i]);
    }
    this.setBillVOs(billvos);
  }

  public void setQueryScheme(IQueryScheme queryScheme) {
    this.queryScheme = queryScheme;
  }

  public void setRefInfo(RefInfo refInfo) {
    this.refInfo = refInfo;
  }

  public void setSelectedDataCollection(
      SelectedDataCollection selectedDataCollection) {
    this.selectedDataCollection = selectedDataCollection;
  }

  public void setSqlWhere(String sqlWhere) {
    this.sqlWhere = sqlWhere;
  }

  public void updateBodyRowVO(String headPK, String bodyPK,
      CircularlyAccessibleValueObject newBodyVO) {
    List<CircularlyAccessibleValueObject> bodyList =
        new ArrayList<CircularlyAccessibleValueObject>();
    try {

      for (CircularlyAccessibleValueObject body : this.mapvos.get(headPK)
          .getChildrenVO()) {
        if (bodyPK.equals(body.getPrimaryKey())) {
          bodyList.add(newBodyVO);
        }
        else {
          bodyList.add(body);
        }
      }
    }
    catch (Exception e) {
      Logger.error(e);
    }
    if (bodyList.size() > 0) {
      this.mapvos.get(headPK).setChildrenVO(
          bodyList.toArray((CircularlyAccessibleValueObject[]) Array
              .newInstance(
                  this.mapvos.get(headPK).getChildrenVO()[0].getClass(),
                  bodyList.size())));
    }

  }

  public void updateBodyRowVOs(String headPK,
      CircularlyAccessibleValueObject[] newBodyVOs) {
    if (this.mapvos.get(headPK) == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("pubapp_0","0pubapp-0046")/*@res "该单据不存在。"*/);
    }
    this.mapvos.get(headPK).setChildrenVO(newBodyVOs);

  }

  private AggregatedValueObject filtedChildVOs(AggregatedValueObject bill,
      String pk) {
	if(this.getRefInfo().getBodyVO()==null)return   bill;
    AggregatedValueObject newbill = null;
    try {
      newbill = bill.getClass().newInstance();
//      CircularlyAccessibleValueObject[] newChildren =
//          (CircularlyAccessibleValueObject[]) Array.newInstance(this
//              .getRefInfo().getBodyVO().getClass(), this.getRowSelectManager()
//              .getSelectBodyLenth(pk));
      
      List<CircularlyAccessibleValueObject> bodySelectVo = new ArrayList<CircularlyAccessibleValueObject>();
      
      CircularlyAccessibleValueObject[] oldChildren = getChildrenVOByClass(bill);
      
      if(oldChildren==null){
    	  oldChildren=new CircularlyAccessibleValueObject[0];
      }
      
      for (CircularlyAccessibleValueObject bodyVO : oldChildren) {
        String bodyPk = bodyVO.getPrimaryKey();
        if (this.getRowSelectManager().isBodyRowSelect(pk, bodyPk)) {
        	bodySelectVo.add(bodyVO);
        }
      }

      // 如果没有选择表体则不设置表体数据
      if (bodySelectVo.size() > 0) {
    	  CircularlyAccessibleValueObject[] newChildren = (CircularlyAccessibleValueObject[]) Array.newInstance(
    			  this.getRefInfo().getBodyVO().getClass(),bodySelectVo.size());
    	  int i = 0;
    	  for(CircularlyAccessibleValueObject vo : bodySelectVo) {
    		  newChildren[i++] = vo;
    	  }
    	  newbill.setChildrenVO(newChildren);
      }
      newbill.setParentVO(bill.getParentVO());
    }
    catch (Exception e) {
      Logger.error(e);
    }
    return newbill;
  }

  private AggregatedValueObject getCollectionVO(AggregatedValueObject bill,
      String pk) {
    AggregatedValueObject newbill = null;
    try {
      newbill = bill.getClass().newInstance();
      // CircularlyAccessibleValueObject[] newChildren =
      // (CircularlyAccessibleValueObject[]) Array.newInstance(getRefInfo()
      // .getBodyVO().getClass(), getSelectedDataCollection()
      // .getSelectBodyLenth(pk));
      // CircularlyAccessibleValueObject[] oldChildren = bill.getChildrenVO();
      //
      // int i = 0;
      // for (CircularlyAccessibleValueObject bodyVO : oldChildren) {
      // String bodyPk = bodyVO.getPrimaryKey();
      // if (getSelectedDataCollection().getSelectedCollection().get(pk)
      // .contains(bodyPk)) {
      // newChildren[i] = bodyVO;
      // i++;
      // }
      // }
      newbill.setChildrenVO(this.getCollectionChildren(pk));
      newbill.setParentVO(bill.getParentVO());
    }
    catch (Exception e) {
      Logger.error(e);
    }
    return newbill;

  }

  private CircularlyAccessibleValueObject[] getChildrenVOByClass(AggregatedValueObject billvo) {
		if (billvo instanceof AbstractBill) {
			if (this.getRefInfo().getBodyVO() != null) {
				AbstractBill billVoTemp = (AbstractBill) billvo;
				return (CircularlyAccessibleValueObject[])billVoTemp.getChildren(this.getRefInfo().getBodyVO().getClass());
			}
		}
		return billvo.getChildrenVO();
	}
}
