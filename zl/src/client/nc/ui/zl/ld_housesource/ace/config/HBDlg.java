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
import nc.uif.pub.exception.UifException;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.zl.base_project.ProjectVO;
import nc.vo.zl.hql_builldingfile.BuildingfileVO;
import nc.vo.zl.ld_housesource.AggHousesourceVO;
import nc.vo.zl.ld_housesource.HousesourceVO;

public class HBDlg extends UIDialog {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8217007425593939593L;

	private AggHousesourceVO[] aggvos = null;
	
	
	IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
	
	public HBDlg(Container parent,AggHousesourceVO[] aggvos) {
		super(parent);
		this.aggvos = aggvos;
		initialize();
		initDialog();
		this.showModal();
	}

	private HBDlg dialog;
	
	private BillCardPanel card;
	
	private boolean flag=false;
	
	private void initialize() {
		if (null == this.card) {
			this.card = new BillCardPanel();
			this.card.loadTemplet("1001ZZ100000000CBMOU");
			initvalue();
			card.addEditListener(new BodyAfterEditLister());
		}
	}

	private void initDialog() {
		dialog = this;
		// ���öԻ�������
		this.setTitle("��Դ�ϲ�");
		// �������ʺϵĴ�С
		this.setSize(new Dimension(1200, 400));
		// ���öԻ���λ�ã�������
		this.setLocationRelativeTo(getParent());
		// ���öԻ��򲼾�
		this.setLayout(new BorderLayout());
		// ���ð�ť
		//UIButton addBtn = new UIButton("��  ��");
		//UIButton delBtn = new UIButton("ɾ  ��");
		UIButton sureBtn = new UIButton("ȷ  ��");
		UIButton notBtn = new UIButton("ȡ  ��");
		//addBtn.setPreferredSize(new Dimension(80, 25));
		//delBtn.setPreferredSize(new Dimension(80, 25));
		sureBtn.setPreferredSize(new Dimension(80, 25));
		notBtn.setPreferredSize(new Dimension(80, 25));
		// �Ӽ���
		//addBtn.addMouseListener(new AddMouseLister());
		//delBtn.addMouseListener(new DelMouseLister());
		sureBtn.addMouseListener(new SureMouseLister());
		notBtn.addMouseListener(new NotMouseLister());

		UIPanel b_panel = new UIPanel();
		//b_panel.add(addBtn);
		//b_panel.add(delBtn);
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
			card.getBillModel().addLine();
		}

		public void mouseEntered(MouseEvent e) {}

		public void mouseExited(MouseEvent e) {}

		public void mousePressed(MouseEvent e) {}

		public void mouseReleased(MouseEvent e) {}
	}
	
	class DelMouseLister implements MouseListener {

		public void mouseClicked(MouseEvent e) {
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
				for(int i=0;i<vos.length;i++){
					if(!isNumeric(vos[i].getRoomnumber())){
						MessageDialog.showErrorDlg(card, "����", "���Ų��ܴ��з��������ַ������飡");
						card.getBillModel().setValueAt(null, 0, "roomnumber");
						return;
					}
				}
				//У�����
				try {
					String sql="select count(*) from zl_housesource where nvl(dr,0)=0 and pk_org='"+vos[0].getPk_org()+"'" +
							" and projectname='"+vos[0].getProjectname()+"' and estatecode='"+vos[0].getEstatecode()+"'";
					int a=(Integer) iQ.executeQuery(sql, new ColumnProcessor());
					if(a>0){
						MessageDialog.showHintDlg(card, "��ʾ", "�÷�Դ�����Ѵ��ڣ����飡");
						//card.getBillModel().setValueAt(null, 0, "estatecode");
						return;
					}
					
				//У������
					String sql2="select count(*) from zl_housesource where nvl(dr,0)=0 and pk_org='"+vos[0].getPk_org()+"'" +
							" and projectname='"+vos[0].getProjectname()+"' and estatename='"+vos[0].getEstatename()+"'";
					int aa=(Integer) iQ.executeQuery(sql2, new ColumnProcessor());
					if(aa>0){
						MessageDialog.showHintDlg(card, "��ʾ", "�÷�Դ�����Ѵ��ڣ����飡");
						//card.getBillModel().setValueAt(null, 0, "estatename");
						return;
					}
				}catch (BusinessException e1) {
						// TODO �Զ����ɵ� catch ��
						e1.printStackTrace();
					}
				
				String []pks=HYPubBO_Client.insertAry(vos);
				for(AggHousesourceVO aggvo:aggvos){
					
					aggvo.getParentVO().setDr(2);
					aggvo.getParentVO().setVdef1(pks[0]);
					HYPubBO_Client.update(aggvo.getParentVO());
				}
				Object pkld=vos[0].getBuildname();
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
				
				Object pk_xm=vos[0].getProjectname();
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
				// TODO �Զ����ɵ� catch ��
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
	
	class BodyAfterEditLister implements BillEditListener{

		@Override
		public void afterEdit(BillEditEvent e) {
			if(e.getKey().equals("unit")|| e.getKey().equals("roomnumber")){
				try {
					Object obj=card.getBillModel().getValueObjectAt(0, "unit");
					Object obj2=card.getBillModel().getValueObjectAt(0, "roomnumber");
					if(obj!=null){
						Object ld=getColvalue(card.getBillModel().getValueObjectAt(0, "buildname"));
						String sql="select nunit from zl_buildingfile where nvl(dr,0)=0 and pk_buildingfile='"+ld+"'";
						Object obja=iQ.executeQuery(sql, new ColumnProcessor());
						UFDouble dy=obja==null?new UFDouble(0):new UFDouble(obja.toString());
						UFDouble dy2=obj==null?new UFDouble(0):new UFDouble(obj.toString());
						if(dy.compareTo(dy2)<0){
							MessageDialog.showHintDlg(card, "��ʾ", "��¥�����Ԫ��Ϊ"+dy+",���Ԫ�������飡");
							card.getBillModel().setValueAt(null, 0, "unit");
							return;
						}
					}
					if(obj!=null && obj2!=null){
						String sql="select  count(*) from zl_housesource where nvl(dr,0)=0 and pk_org='"+aggvos[0].getParentVO().getPk_org()+"' " +
							" and projectname='"+aggvos[0].getParentVO().getProjectname()+"' and unit='"+obj+"' and roomnumber='"+obj2+"'" +
									" and buildname='"+aggvos[0].getParentVO().getBuildname()+"'";
						int a=(Integer) iQ.executeQuery(sql, new ColumnProcessor());
						if(a>0){
							MessageDialog.showHintDlg(card, "��ʾ", "�õ�Ԫ�·����Ѵ��ڣ����飡");
							card.getBillModel().setValueAt(null, 0, e.getKey());
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
				String sql="select count(*) from zl_housesource where nvl(dr,0)=0 and pk_org='"+aggvos[0].getParentVO().getPk_org()+"'" +
						" and projectname='"+aggvos[0].getParentVO().getProjectname()+"' and estatecode='"+e.getValue()+"'";
				int a=(Integer) iQ.executeQuery(sql, new ColumnProcessor());
				if(a>0){
					MessageDialog.showHintDlg(card, "��ʾ", "�÷�Դ�����Ѵ��ڣ����飡");
					card.getBillModel().setValueAt(null, 0, e.getKey());
					return;
				}
			}catch (BusinessException e1) {
					// TODO �Զ����ɵ� catch ��
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
						card.getBillModel().setValueAt(null, 0, e.getKey());
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
			Object pk_org=aggvos[0].getParentVO().getPk_org();
			Object pk_project=aggvos[0].getParentVO().getProjectname();
			UIRefPane ref=(UIRefPane)card.getBodyItem("pk_familyfile").getComponent();
			ref.getRefModel().addWherePart(" and pk_org='"+pk_org+"' and pk_projectid='"+pk_project+"'");
		card.getBillModel().clearBodyData();
		card.getBillModel().addLine();
		UFDouble jz=new UFDouble(0);//�����������
		UFDouble tn=new UFDouble(0);//�����������
		for(AggHousesourceVO aggvo:aggvos){
			jz=jz.add(aggvo.getParentVO().getBuildarea());
			tn=tn.add(aggvo.getParentVO().getInnerarea());
		}
		String sql="select * from zl_housesource where nvl(dr,0)=0 and pk_housesource='"+aggvos[0].getParentVO().getPk_housesource()+"'";
			HousesourceVO hvo=(HousesourceVO) iQ.executeQuery(sql, new BeanProcessor(HousesourceVO.class));
		//HousesourceVO hvo=aggvos[0].getParentVO();
		hvo.setPk_housesource(null);
		hvo.setRoomnumber(null);
		hvo.setCreator(AppContext.getInstance().getPkUser());
		hvo.setCreationtime(new UFDateTime());
		hvo.setDbilldate(new UFDate());
		hvo.setBuildarea(jz);
		hvo.setInnerarea(tn);
		hvo.setEstatecode(null);
		hvo.setEstatename(null);
		hvo.setModifier(null);
		hvo.setModifiedtime(null);
		hvo.setEstatecode(aggvos[0].getParentVO().getEstatecode());
		hvo.setEstatename(aggvos[0].getParentVO().getEstatename());
		card.getBillModel().setBodyRowVO(hvo, 0);
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

