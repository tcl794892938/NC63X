package nc.ui.zl.hql_builldingfile.ace.config;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.table.TableColumnModel;

import nc.bs.logging.Logger;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.beans.table.ColumnGroup;
import nc.ui.pub.beans.table.GroupableTableHeader;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillEditListener;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.IBillItem;
import nc.vo.pubapp.AppContext;
import nc.vo.zl.hql_builldingfile.BuildingfileVO;
import nc.vo.zl.ld_housesource.HousesourceVO;

public class BuildStep3Dlg extends UIDialog {
	private Container ctr;
	
	private BuildStep3Dlg dialog;

	private BillCardPanel card;
	
	private BillCardPanel step1card;
	
	private BillCardPanel step2card;
	
	private BuildingfileVO vo;
	
	public static HousesourceVO[] vos;
	
	private final String tab1="danyuan";
	private final String tab2="louceng";
	
	private boolean flag=false;
	
	public static HousesourceVO[] getVos() {
		return vos;
	}

	public static void setVos(HousesourceVO[] vos) {
		BuildStep3Dlg.vos = vos;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public BuildStep3Dlg(Container parent,BuildingfileVO vo,BillCardPanel card,BillCardPanel card2) {
		super(parent);
		this.vo=vo;
		this.ctr=parent;
		this.step1card=card;
		this.step2card=card2;
		initialize();
		initDialog();
		this.showModal();
	}
	
	private void initialize() {
		if (null == this.card) {
			this.card = new BillCardPanel();
			this.card.loadTemplet("1001ZZ1000000001IMUQ");//step2
			
			BillModel model=card.getBillModel();
			List<BillItem> litem=new ArrayList<BillItem>();
			BillItem[] showitems=model.getBodyItems();
			litem.addAll(Arrays.asList(showitems));
			
			int row=step1card.getBillModel(tab1).getRowCount();
			BillModel step1model=step1card.getBillModel(tab1);
			BillModel step1model2=step1card.getBillModel(tab2);
			for(int j=0;j<row;j++){
				
				Integer hs=(Integer)step1model.getValueAt(j, "dnum");
				Object key=step1model.getValueAt(j, "dname");
				
				for(int i=0;i<hs;i++){
					BillItem it=new BillItem();
					it.setNull(false);
					it.setDataType(IBillItem.STRING);
					it.setTatol(false);
					it.setWidth(100);
					it.setEdit(true);
					it.setKey("fh"+key+"***"+(i+1));
					it.setName("���");
					it.setShowOrder(3);
					litem.add(it);
				}
			}
			
			model.setBodyItems(litem.toArray(new BillItem[0]));
			card.setBillData(card.getBillData());//���¿̻�ģ��
			
			TableColumnModel cm=card.getBillTable().getColumnModel();
			GroupableTableHeader header=(GroupableTableHeader)card.getBillTable().getTableHeader();
			for(int j=0;j<row;j++){
				String key=(String)step1model.getValueAt(j, "dname");
				ColumnGroup cg=new ColumnGroup(key);
				BillItem[] shows=card.getBodyItems();
				for(BillItem show:shows){
					if(show.getKey().startsWith("fh"+key+"***")){
						int col=model.getBodyColByKey(show.getKey());
						cg.add(cm.getColumn(this.getBodyColumnByCol(shows, col)));
					}
				}
				header.addColumnGroup(cg);
			}
			
			
			header.setForeground(Color.BLUE);
			
			card.setHeadItem("vmemo", "���÷���");
			card.setHeadItem("pk_project", vo.getPk_projectid());
			card.setHeadItem("pk_building", vo.getPk_buildingfile());
			
			card.setRowNOShow(false);
			
			//�Ƴ��������Զ����Ӽ���
			card.getBillTable().removeSortListener();
			card.getBodyPanel().setAutoAddLine(false);
			
			//���ӱ�ͷ�ļ���
			card.addBillEditListenerHeadTail(new HeadAfterEditLister());
			card.addEditListener(new BodyAfterEditLister());
			model.addLine();
			
			//��ʼ�����ر���
			for(int i=0;i<step1model2.getRowCount();i++){
				model.addLine();
				model.setValueAt(step1model2.getValueAt(i, "floor"), model.getRowCount()-1, "floor");
			}
			//����Ĭ��ֵ
			BillItem[] items=model.getBodyItems();
			for(int j=0;j<row;j++){
				
				Integer hs=(Integer)step1model.getValueAt(j, "dnum");
				String shs=String.valueOf(hs);
				//�жϻ���λ��
				int mm=0;
				if(shs.length()==3){
					mm=3;
				}else{
					mm=2;
				}
				
				Object key=step1model.getValueAt(j, "dname");
				for(BillItem it:items){
					if(it.getKey().startsWith("fh"+key+"***")){
						
						String newstr=it.getKey().replace("fh"+key+"***", "");
						Integer n=newstr.length();
						//����
						for(int i=n;i<mm;i++){
							newstr="0"+newstr;
						}
						
						model.setValueAt(newstr, 0, it.getKey());
						
						for(int k=1;k<model.getRowCount();k++){
							
							Object objk=model.getValueAt(k, "floor");
							model.setValueAt(objk.toString()+newstr, k, it.getKey());
						}
					}
				}
			}
		}
	}
	
	protected int getBodyColumnByCol(BillItem[] shows,int col) {
		int n = -1;
		for (int i = 0; i < shows.length; i++) {
			BillItem item = shows[i];
			if (item.isShow())
				n++;
			if (i == col)
				return n;
		}
		Logger.info("û���ҵ�" + col + "�ж�Ӧʵ����.");
		return -1;
	}
	
	class HeadAfterEditLister implements BillEditListener{

		@Override
		public void afterEdit(BillEditEvent e) {}

		@Override
		public void bodyRowChange(BillEditEvent e) {}
		
	}
	
	class BodyAfterEditLister implements BillEditListener{

		@Override
		public void afterEdit(BillEditEvent e) {
			
			BillModel model=card.getBillModel();
			int row=model.getRowCount();
			//�ж��Ƿ�ı�������
			if(e.getKey().startsWith("fh")){
				
				if(e.getRow()==0){
					
					for(int i=1;i<row;i++){
							
						Integer it=(Integer)model.getValueAt(i, "floor");
						if(e.getValue()==null){
							model.setValueAt(null, i, e.getKey());
						}else{
							model.setValueAt(it.toString()+e.getValue(), i, e.getKey());
						}
					}
				}
				
			}
			
		}

		@Override
		public void bodyRowChange(BillEditEvent e) {			
		}
		
	}
	
	private void initDialog() {
		dialog = this;
		// ���öԻ�������
		this.setTitle("���ٽ���");
		// �������ʺϵĴ�С
		this.setSize(new Dimension(900, 600));
		this.setResizable(true);
		// ���öԻ���λ�ã�������
		this.setLocationRelativeTo(getParent());
		// ���öԻ��򲼾�
		this.setLayout(new BorderLayout());
		// ���ð�ť
		UIButton sureBtn = new UIButton("ȷ  ��");
		UIButton notBtn = new UIButton("��һ��");
		sureBtn.setPreferredSize(new Dimension(80, 25));
		notBtn.setPreferredSize(new Dimension(80, 25));
		// �Ӽ���
		sureBtn.addMouseListener(new SureMouseLister());
		notBtn.addMouseListener(new NotMouseLister());

		UIPanel b_panel = new UIPanel();
		b_panel.add(notBtn);
		b_panel.add(sureBtn);
		// ��panel���ص��Ի�����
		this.add(card, BorderLayout.CENTER);
		this.add(b_panel, BorderLayout.SOUTH);
		// ���ùرշ�ʽ
		this.setDefaultCloseOperation(UIDialog.DISPOSE_ON_CLOSE);
	}
	
	class SureMouseLister implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			
			//У��ÿ����Ԫ�����Լ�¼��
			BillModel model = card.getBillModel();
			int row=model.getRowCount();
			
			BillItem[] items=model.getBodyItems();
			
			for(BillItem it : items){
				
				if(it.getKey().startsWith("fh")){
					
					for(int i=0;i<row;i++){
						if(model.getValueAt(i, it.getKey())==null){
							MessageDialog.showHintDlg(ctr, "��ʾ", "��ŷ���δȫ��ά����");
							return ;
						}
					}
				}
				
			}
			
			//У���ظ�
			BillModel step1model=step1card.getBillModel(tab1);
			int row2=step1card.getBillModel(tab1).getRowCount();
			for(int j=0;j<row2;j++){
				
				Object key=step1model.getValueAt(j, "dname");
				
				HashMap<String, String> map=new HashMap<String, String>();
				for(BillItem it : items){
					
					if(it.getKey().startsWith("fh"+key+"***")){
						
						//У�����
						Object obj=model.getValueAt(0, it.getKey());
						
						if(map.containsKey("fh"+key+"***"+"xh"+obj)){
							MessageDialog.showHintDlg(card, "��ʾ", key+"���"+obj+"�ظ���");
							return ;
						}else{
							map.put("fh"+key+"***"+"xh"+obj, "fh"+key+"***"+"xh"+obj);
						}
						
						//У�鷿��
						for(int i=1;i<row;i++){
							Object obj2=model.getValueAt(i, it.getKey());
							if(map.containsKey("fh"+key+"***"+"fh"+obj2)){
								MessageDialog.showHintDlg(card, "��ʾ", key+"����"+obj2+"�ظ���");
								return ;
							}else{
								map.put("fh"+key+"***"+"fh"+obj2, "fh"+key+"***"+"fh"+obj2);
							}
						}
					}
					
				}
			}
			
			//���췿Դ��Ϣ
			try {
				initHouseInfo();
			} catch (Exception e2) {
				MessageDialog.showHintDlg(card, "��ʾ",e2.getMessage());
				return ;
			}
			
			dialog.setVisible(false);
		}

		public void mouseEntered(MouseEvent e) {}

		public void mouseExited(MouseEvent e) {}

		public void mousePressed(MouseEvent e) {}

		public void mouseReleased(MouseEvent e) {}
	}

	class NotMouseLister implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			
			dialog.setVisible(false);
			new BuildStep2Dlg(ctr,vo,step1card,step2card);
		}

		public void mouseEntered(MouseEvent e) {}

		public void mouseExited(MouseEvent e) {}

		public void mousePressed(MouseEvent e) {}

		public void mouseReleased(MouseEvent e) {}
	}
	
	//���췿Դ��Ϣ
		protected void initHouseInfo(){
			
			UIRefPane refp1=(UIRefPane)card.getHeadItem("pk_project").getComponent();
			UIRefPane refp2=(UIRefPane)card.getHeadItem("pk_building").getComponent();
			
			BillModel step1model=step1card.getBillModel(tab1);
			int step1row=step1model.getRowCount();
			
			BillModel step2model=step2card.getBillModel();
			//int step2row=step2model.getRowCount();
			
			BillModel model=card.getBillModel();
			int row=model.getRowCount();
			BillItem[] items=model.getBodyItems();
			
			List<HousesourceVO> list=new ArrayList<HousesourceVO>();
			
			for(int i=0;i<step1row;i++){
				
				Object key=step1model.getValueAt(i, "dname");
				Object dcode=step1model.getValueAt(i, "dcode");
				for(BillItem it : items){
					if(it.getKey().startsWith("fh"+key+"***")){
						
						Object xhobj=model.getValueAt(0, it.getKey());//���
						Object step2key=it.getKey().replace("fh", "hx");
						for(int j=1;j<row;j++){
							HousesourceVO hvo=new HousesourceVO();
							Object fh=model.getValueAt(j, it.getKey());
							Object floor=model.getValueAt(j, "floor");
							DefaultConstEnum hx=(DefaultConstEnum)step2model.getValueAt(j, step2model.getBodyColByKey(step2key.toString()));
							
							//Ϊvo����ֵ
							hvo.setProjectname(refp1.getRefPK());
							hvo.setBuildname(refp2.getRefPK());
							hvo.setPk_familyfile(hx.getValue().toString());
							hvo.setEstatecode(refp1.getRefCode()+"-"+refp2.getRefCode()+"-"+dcode+"-"+fh);
							hvo.setEstatename(refp1.getRefName()+"-"+refp2.getRefName()+"-"+key+"-"+fh);
							hvo.setRoomnumber(fh.toString());
							hvo.setFloorn(floor.toString());
							hvo.setUnit(dcode.toString());
							//hvo.setBuildarea();
							//hvo.setInnerarea();
							hvo.setHousestate(0);
							hvo.setDbilldate(AppContext.getInstance().getBusiDate());
							hvo.setCreator(AppContext.getInstance().getPkUser());
							hvo.setCreationtime(AppContext.getInstance().getServerTime());
							hvo.setPk_group(vo.getPk_group());
							hvo.setPk_org(vo.getPk_org());
							hvo.setPk_org_v(vo.getPk_org_v());
							//hvo.setHouseseq(xhobj.toString());
							
							list.add(hvo);
						}
					}
				}
			}
			
			vos=list.toArray(new HousesourceVO[0]);
		}
}
