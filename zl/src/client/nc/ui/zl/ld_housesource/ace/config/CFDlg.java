package nc.ui.zl.ld_housesource.ace.config;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillEditListener;
import nc.ui.trade.business.HYPubBO_Client;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.zl.base_project.ProjectVO;
import nc.vo.zl.hql_builldingfile.BuildingfileVO;
import nc.vo.zl.ld_housesource.AggHousesourceVO;
import nc.vo.zl.ld_housesource.HousesourceVO;

public class CFDlg extends UIDialog {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8217007425593939593L;

	private AggHousesourceVO aggvo = null;
	
	IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
	
	public CFDlg(Container parent,AggHousesourceVO aggvo) {
		super(parent);
		this.aggvo = aggvo;
		initialize();
		initDialog();
		this.showModal();
	}

	private CFDlg dialog;
	
	private BillCardPanel card;
	
	private boolean flag=false;
	
	private void initialize() {
		if (null == this.card) {
			this.card = new BillCardPanel();
			this.card.loadTemplet("1001ZZ100000000CBMOU");
			initvalue();
			card.addEditListener(new HeadAfterEditLister());
		}
	}

	private void initDialog() {
		dialog = this;
		// ���öԻ�������
		this.setTitle("��Դ���");
		// �������ʺϵĴ�С
		this.setSize(new Dimension(1200, 400));
		// ���öԻ���λ�ã�������
		this.setLocationRelativeTo(getParent());
		// ���öԻ��򲼾�
		this.setLayout(new BorderLayout());
		// ���ð�ť
		UIButton addBtn = new UIButton("��  ��");
		UIButton delBtn = new UIButton("ɾ  ��");
		UIButton sureBtn = new UIButton("ȷ  ��");
		UIButton notBtn = new UIButton("ȡ  ��");
		addBtn.setPreferredSize(new Dimension(80, 25));
		delBtn.setPreferredSize(new Dimension(80, 25));
		sureBtn.setPreferredSize(new Dimension(80, 25));
		notBtn.setPreferredSize(new Dimension(80, 25));
		// �Ӽ���
		addBtn.addMouseListener(new AddMouseLister());
		delBtn.addMouseListener(new DelMouseLister());
		sureBtn.addMouseListener(new SureMouseLister());
		notBtn.addMouseListener(new NotMouseLister());

		UIPanel b_panel = new UIPanel();
		b_panel.add(addBtn);
		b_panel.add(delBtn);
		b_panel.add(sureBtn);
		b_panel.add(notBtn);
		// ��panel���ص��Ի�����
		this.add(card, BorderLayout.CENTER);
		this.add(b_panel, BorderLayout.SOUTH);
		// ���ùرշ�ʽ
		this.setDefaultCloseOperation(UIDialog.DISPOSE_ON_CLOSE);
	}
	
	class AddMouseLister implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			try {
			card.getBillModel().addLine();
			HousesourceVO hvo=(HousesourceVO) card.getBillModel().getBodyValueRowVO(0, HousesourceVO.class.getName());
			hvo.setPk_housesource(null);
			hvo.setRoomnumber(null);
			hvo.setCreator(AppContext.getInstance().getPkUser());
			hvo.setCreationtime(new UFDateTime());
			hvo.setDbilldate(new UFDate());
			hvo.setEstatecode(hvo.getEstatecode());
			hvo.setEstatename(hvo.getEstatename());
			hvo.setModifier(null);
			hvo.setModifiedtime(null);
			hvo.setBuildarea(new UFDouble(0));
			hvo.setInnerarea(new UFDouble(0));
			
			card.getBillModel().setBodyRowVO(hvo, card.getBillModel().getRowCount()-1);
			card.getBillModel().execLoadFormulaByRow(card.getBillModel().getRowCount()-1);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		public void mouseEntered(MouseEvent e) {}

		public void mouseExited(MouseEvent e) {}

		public void mousePressed(MouseEvent e) {}

		public void mouseReleased(MouseEvent e) {}
	}
	
	class DelMouseLister implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			if(card.getBillModel().getRowCount()<=2){
				MessageDialog.showHintDlg(card, "��ʾ", "��Դ�������2�У�������ɾ�У�");
				return;
			}else if(card.getBillTable().getSelectedRow()==-1){
				int []a=new int[1];
				a[0]=card.getBillModel().getRowCount()-1;
				card.getBillModel().delLine(a);
			}
			else{
				int []a=new int[1];
				a[0]=card.getBillTable().getSelectedRow();
				card.getBillModel().delLine(a);
			}
		}
		public void mouseEntered(MouseEvent e) {}

		public void mouseExited(MouseEvent e) {}

		public void mousePressed(MouseEvent e) {}

		public void mouseReleased(MouseEvent e) {}
	}
	
	class SureMouseLister implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			try {
				card.stopEditing();
				try{
					card.dataNotNullValidate();
					}catch (BusinessException e1) {
						MessageDialog.showErrorDlg(card, "����", "�������Ϊ�գ����飡");
						return;
					}
				HousesourceVO vos[]=(HousesourceVO[])card.getBillModel().getBodyValueVOs(HousesourceVO.class.getName());
				UFDouble jz=new UFDouble(0);
				UFDouble tn=new UFDouble(0);
				for(HousesourceVO vo:vos){
					if(vo.getBuildarea().compareTo(new UFDouble(0))==0 || vo.getInnerarea().compareTo(new UFDouble(0))==0){
						MessageDialog.showErrorDlg(card, "����", "��������������������Ϊ0�����飡");
						return;
					}
					if(!isNumeric(vo.getRoomnumber())){
						MessageDialog.showErrorDlg(card, "����", "���Ų��ܴ��з��������ַ������飡");
						return;
					}
					
					jz=jz.add(vo.getBuildarea());
					tn=tn.add(vo.getInnerarea());
					vo.setVdef1(aggvo.getParentVO().getPk_housesource());
					
					//У�����
						for(HousesourceVO vo2:vos){
							if(!(vo2.getUnit()+vo2.getRoomnumber()).equals(vo.getUnit()+vo.getRoomnumber()) 
									&& vo2.getEstatecode().equals(vo.getEstatecode())){
								MessageDialog.showErrorDlg(card, "����", "��ֺ�Դ�ı���Ӧ������ͬ�����飡");
								return;
							}
						}
					String sql="select count(*) from zl_housesource where nvl(dr,0)=0 and pk_org='"+vo.getPk_org()+"'" +
							" and projectname='"+vo.getProjectname()+"' and estatecode='"+vo.getEstatecode()+"'";
					int a=(Integer) iQ.executeQuery(sql, new ColumnProcessor());
					if(a>0){
						MessageDialog.showHintDlg(card, "��ʾ", "��Դ�������Ѵ��ڣ����飡");
						//card.getBillModel().setValueAt(null, e.getRow(), e.getKey());
						return;
					}
					
					//У������
					for(HousesourceVO vo2:vos){
						if(!(vo2.getUnit()+vo2.getRoomnumber()).equals(vo.getUnit()+vo.getRoomnumber()) 
								&& vo2.getEstatename().equals(vo.getEstatename())){
							MessageDialog.showErrorDlg(card, "����", "��ֺ�Դ������Ӧ������ͬ�����飡");
							return;
						}
					}
				String sql2="select count(*) from zl_housesource where nvl(dr,0)=0 and pk_org='"+vo.getPk_org()+"'" +
						" and projectname='"+vo.getProjectname()+"' and estatename='"+vo.getEstatename()+"'";
				int aa=(Integer) iQ.executeQuery(sql2, new ColumnProcessor());
				if(aa>0){
					MessageDialog.showHintDlg(card, "��ʾ", "��Դ�������Ѵ��ڣ����飡");
					//card.getBillModel().setValueAt(null, e.getRow(), e.getKey());
					return;
				}
				}
				if(jz.compareTo(aggvo.getParentVO().getBuildarea())!=0||tn.compareTo(aggvo.getParentVO().getInnerarea())!=0){
					MessageDialog.showHintDlg(card, "����", "��ֺ�ķ�Դ������������������������ǰ��ȣ�");
					return;
				}
				aggvo.getParentVO().setDr(3);
				HYPubBO_Client.insertAry(vos);
				HYPubBO_Client.update(aggvo.getParentVO());
				
				Object pkld=aggvo.getParentVO().getBuildname();
				//��д¥����Ϣ
				
				String sql="select floorn,nnum,buildarea,buildarea2 from" +
					"(select sum(e.buildarea) buildarea from zl_housesource e " +
					" where  buildname ='"+pkld+"' and nvl(dr,0)=0 and e.housestate=1)a ," +
					"(select sum(e.buildarea) buildarea2 from zl_housesource e " +
					" where  buildname ='"+pkld+"' and nvl(dr,0)=0 and e.housestate<>1)b," +
					"(select count(distinct e.floorn) floorn," +
					"count(1) nnum from zl_housesource e " +
					" where  buildname ='"+pkld+"' and nvl(dr,0)=0 )c ";
				
				IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
				Map<String, Object> map=(Map<String, Object>)iQ.executeQuery(sql, new MapProcessor());
				
				String sql2="select * from zl_buildingfile where pk_buildingfile='"+pkld+"'";
				BuildingfileVO fvo=(BuildingfileVO)iQ.executeQuery(sql2, new BeanProcessor(BuildingfileVO.class));
				if(fvo==null){
					throw new BusinessException("¥�������쳣��");
				}
				fvo.setAttributeValue("nproperty", map.get("nnum"));
				fvo.setAttributeValue("nbuilding", map.get("floorn"));
				fvo.setAttributeValue("builtuparea", map.get("buildarea2"));
				fvo.setAttributeValue("personalarea", map.get("buildarea"));
				HYPubBO_Client.update(fvo);
				
				Object pk_xm=aggvo.getParentVO().getProjectname();
				//��д��Ŀ��Ϣ
				String sql3="select * from (select sum(e.buildarea) buildarea from zl_housesource e where e.projectname='"+pk_xm+"' and nvl(dr,0)=0 and e.housestate<>1)a," +
						"(select count(distinct e.buildname) buildnum,count(1) nnum from zl_housesource e where e.projectname='"+pk_xm+"' and nvl(dr,0)=0 )b," +
						"(select count(1) nhomenum from zl_housesource e left join zl_familyfile f on e.pk_familyfile=f.pk_familyfile left join zl_formattype g " +
						"on f.pk_formattypeid=g.pk_formattype where e.projectname='"+pk_xm+"' and nvl(e.dr,0)=0 and g.code like '04%' )c," +
						"(select count(1) nshopnum from zl_housesource e left join zl_familyfile f on e.pk_familyfile=f.pk_familyfile left join zl_formattype g " +
						"on f.pk_formattypeid=g.pk_formattype where e.projectname='"+pk_xm+"' and nvl(e.dr,0)=0 and g.code like '02%' )d," +
						"(select count(1) nofficenum from zl_housesource e left join zl_familyfile f on e.pk_familyfile=f.pk_familyfile left join zl_formattype g " +
						"on f.pk_formattypeid=g.pk_formattype where e.projectname='"+pk_xm+"' and nvl(e.dr,0)=0 and g.code like '01%' )e," +
						"(select count(1) ncarnum from zl_housesource e left join zl_familyfile f on e.pk_familyfile=f.pk_familyfile left join zl_formattype g " +
						"on f.pk_formattypeid=g.pk_formattype where e.projectname='"+pk_xm+"' and nvl(e.dr,0)=0 and g.code like '03%' )f ";
				Map<String, Object> map2=(Map<String, Object>)iQ.executeQuery(sql3, new MapProcessor());
				
				String sql4="select * from zl_project where pk_project='"+pk_xm+"'";
				ProjectVO pvo=(ProjectVO)iQ.executeQuery(sql4, new BeanProcessor(ProjectVO.class));
				if(pvo==null){
					throw new BusinessException("��Ŀ�����쳣��");
				}
				pvo.setAttributeValue("narea", map2.get("buildarea"));
				pvo.setAttributeValue("nfloor", map2.get("buildnum"));
				pvo.setAttributeValue("nholds", map2.get("nnum"));
				pvo.setAttributeValue("nhomeholds", map2.get("nhomenum"));
				pvo.setAttributeValue("nshopholds", map2.get("nshopnum"));
				pvo.setAttributeValue("nofficeholds", map2.get("nofficenum"));
				pvo.setAttributeValue("ncarholds", map2.get("ncarnum"));
				HYPubBO_Client.update(pvo);
				
			} catch (Exception e1) {
				MessageDialog.showErrorDlg(card, "����", e1.getMessage());
				e1.printStackTrace();
			}
			
			flag=true;
			dialog.setVisible(false);
		}

		public void mouseEntered(MouseEvent e) {}

		public void mouseExited(MouseEvent e) {}

		public void mousePressed(MouseEvent e) {}

		public void mouseReleased(MouseEvent e) {}
	}

	class NotMouseLister implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			flag=false;
			dialog.setVisible(false);
		}

		public void mouseEntered(MouseEvent e) {}

		public void mouseExited(MouseEvent e) {}

		public void mousePressed(MouseEvent e) {}

		public void mouseReleased(MouseEvent e) {}
	}
	
	class HeadAfterEditLister implements BillEditListener{

		@Override
		public void afterEdit(BillEditEvent e) {

			if(e.getKey().equals("unit")|| e.getKey().equals("roomnumber")){
				try {
					Object obj=card.getBillModel().getValueObjectAt(e.getRow(), "unit");
					Object obj2=card.getBillModel().getValueObjectAt(e.getRow(), "roomnumber");
					if(obj!=null){
						Object ld=getColvalue(card.getBillModel().getValueObjectAt(0, "buildname"));
						String sql="select nunit from zl_buildingfile where nvl(dr,0)=0 and pk_buildingfile='"+ld+"'";
						Object obja=iQ.executeQuery(sql, new ColumnProcessor());
						UFDouble dy=obja==null?new UFDouble(0):new UFDouble(obja.toString());
						UFDouble dy2=obj==null?new UFDouble(0):new UFDouble(obj.toString());
						if(dy.compareTo(dy2)<0){
							MessageDialog.showHintDlg(card, "��ʾ", "��¥�����Ԫ��Ϊ"+dy+",���Ԫ�������飡");
							card.getBillModel().setValueAt(null, e.getRow(), "unit");
							return;
						}
					}
					if(obj!=null && obj2!=null){
						for(int i=0;i<card.getBillModel().getRowCount()&&i!=e.getRow();i++){
							if(obj.equals(card.getBillModel().getValueAt(i, "unit")) &&
									obj2.equals(card.getBillModel().getValueAt(i, "roomnumber"))){
								MessageDialog.showErrorDlg(card, "����", "��ֺ�Դ�ĵ�Ԫ+����Ӧ������ͬ�����飡");
								card.getBillModel().setValueAt(null, e.getRow(), e.getKey());
								return;
							}
						}
						
						String sql="select  count(*) from zl_housesource where nvl(dr,0)=0 and pk_org='"+aggvo.getParentVO().getPk_org()+"' " +
							" and projectname='"+aggvo.getParentVO().getProjectname()+"' and unit='"+obj+"' and roomnumber='"+obj2+"' and " +
									" buildname='"+aggvo.getParentVO().getBuildname()+"'";
						int a=(Integer) iQ.executeQuery(sql, new ColumnProcessor());
						if(a>0){
							MessageDialog.showHintDlg(card, "��ʾ", "�õ�Ԫ�·����Ѵ��ڣ����飡");
							card.getBillModel().setValueAt(null, e.getRow(), e.getKey());
							return;
						}
					}
				} catch (BusinessException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			}
			if(e.getKey().equals("estatecode")){
				try {
					for(int i=0;i<card.getBillModel().getRowCount()&&i!=e.getRow();i++){
						if(e.getValue().equals(card.getBillModel().getValueAt(i, "estatecode"))){
							MessageDialog.showErrorDlg(card, "����", "��ֺ�Դ�ı���Ӧ������ͬ�����飡");
							card.getBillModel().setValueAt(null, e.getRow(), e.getKey());
							return;
						}
					}
				String sql="select count(*) from zl_housesource where nvl(dr,0)=0 and pk_org='"+aggvo.getParentVO().getPk_org()+"'" +
						" and projectname='"+aggvo.getParentVO().getProjectname()+"' and estatecode='"+e.getValue()+"'";
				int a=(Integer) iQ.executeQuery(sql, new ColumnProcessor());
				if(a>0){
					MessageDialog.showHintDlg(card, "��ʾ", "�÷�Դ�����Ѵ��ڣ����飡");
					card.getBillModel().setValueAt(null, e.getRow(), e.getKey());
					return;
				}
			}catch (BusinessException e1) {
					
					e1.printStackTrace();
				}
			}
			if(e.getKey().equals("floorn")){
				try {
				Object ld=getColvalue(card.getBillModel().getValueObjectAt(0, "buildname"));
				String sql="select nbuilding from zl_buildingfile where nvl(dr,0)=0 and pk_buildingfile='"+ld+"'";
					Object obj=iQ.executeQuery(sql, new ColumnProcessor());
					UFDouble lc=obj==null?new UFDouble(0):new UFDouble(obj.toString());
					UFDouble lc2=e.getValue()==null?new UFDouble(0):new UFDouble(e.getValue().toString());
					if(lc.compareTo(lc2)<0){
						MessageDialog.showHintDlg(card, "��ʾ", "��¥�����¥����Ϊ"+lc+"������¥��������飡");
						card.getBillModel().setValueAt(null, e.getRow(), e.getKey());
						return;
					}
				} catch (BusinessException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			}
		
		}

		@Override
		public void bodyRowChange(BillEditEvent e) {}
		
	}

	public void initvalue(){
		try {
		Object pk_org=aggvo.getParentVO().getPk_org();
		Object pk_project=aggvo.getParentVO().getProjectname();
		UIRefPane ref=(UIRefPane)card.getBodyItem("pk_familyfile").getComponent();
		ref.getRefModel().addWherePart(" and pk_org='"+pk_org+"' and pk_projectid='"+pk_project+"'");
		card.getBodyItem("buildarea").setEnabled(true);
		card.getBodyItem("innerarea").setEnabled(true);
		card.getBillModel().clearBodyData();
		card.getBillModel().addLine();
		card.getBillModel().addLine();
		
		String sql="select * from zl_housesource where nvl(dr,0)=0 and pk_housesource='"+aggvo.getParentVO().getPk_housesource()+"'";
		HousesourceVO hvo=(HousesourceVO) iQ.executeQuery(sql, new BeanProcessor(HousesourceVO.class));
		hvo.setPk_housesource(null);
		hvo.setRoomnumber(null);
		hvo.setCreator(AppContext.getInstance().getPkUser());
		hvo.setCreationtime(new UFDateTime());
		hvo.setDbilldate(new UFDate());
//		hvo.setEstatecode(null);
//		hvo.setEstatename(null);
		hvo.setModifier(null);
		hvo.setModifiedtime(null);
		card.getBillModel().setBodyRowVO(hvo, 0);
		hvo.setBuildarea(new UFDouble(0));
		hvo.setInnerarea(new UFDouble(0));
		card.getBillModel().setBodyRowVO(hvo, 1);
		card.getBillModel().execLoadFormula();
		
		} catch (BusinessException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
	}
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	private static Object getColvalue(Object obj){
		
		if(obj==null){
			return obj;
		}else if(obj instanceof DefaultConstEnum){
			return ((DefaultConstEnum)obj).getValue();
		}
		
		return null;
	}
	public static boolean isNumeric(String str){
		for (int i = str.length();--i>=0;){  
			if (!Character.isDigit(str.charAt(i))){
		           return false;
		       }
	   }
	   return true;
	 }
}

