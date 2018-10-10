package nc.ui.zl.hql_builldingfile.ace.config;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.itextpdf.text.log.SysoLogger;

import nc.bs.logging.Logger;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.table.ColumnGroup;
import nc.ui.pub.beans.table.GroupableTableHeader;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillEditListener;
import nc.ui.pub.bill.BillEditListener2;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.IBillItem;
import nc.vo.zl.hql_builldingfile.BuildingfileVO;

public class BuildStep2Dlg extends UIDialog {
	
	private Container ctr;
	
	private BuildStep2Dlg dialog;

	private BillCardPanel card;
	
	private BillCardPanel step1card;
	
	private BuildingfileVO vo;
	
	private final String tab1="danyuan";
	private final String tab2="louceng";
	
	private boolean flag=false;
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public BuildStep2Dlg(Container parent,BuildingfileVO vo,BillCardPanel card) {
		super(parent);
		this.vo=vo;
		this.ctr=parent;
		this.step1card=card;
		initialize();
		initDialog();
		this.showModal();
	}
	
	//����������ר��
	public BuildStep2Dlg(Container parent,BuildingfileVO vo,BillCardPanel card,BillCardPanel card2){
		super(parent);
		this.vo=vo;
		this.ctr=parent;
		this.step1card=card;
		this.card=card2;
		initDialog();
		this.showModal();
	}
	
	private void initialize() {
		if (null == this.card) {
			this.card = new BillCardPanel();
			this.card.loadTemplet("1001ZZ1000000001II77");//step2
			
			BillModel model=card.getBillModel();
			List<BillItem> litem=new ArrayList<BillItem>();
			BillItem[] showitems=model.getBodyItems();
			litem.addAll(Arrays.asList(showitems));
			System.out.println(litem.get(0).getKey());
			System.out.println(litem.get(1).getKey());
			
			int row=step1card.getBillModel(tab1).getRowCount();
			BillModel step1model=step1card.getBillModel(tab1);
			BillModel step1model2=step1card.getBillModel(tab2);
			for(int j=0;j<row;j++){
				
				Integer hs=(Integer)step1model.getValueAt(j, "dnum");
				Object key=step1model.getValueAt(j, "dname");
				
				for(int i=0;i<hs;i++){
					BillItem it=new BillItem();
					it.setNull(false);
					it.setDataType(IBillItem.UFREF);
					it.setRefType("���͵���(����),code=N");
					it.setTatol(false);
					it.setWidth(100);
					it.setEdit(true);
					it.setKey("hx"+key+"***"+(i+1));
					it.setName("����");
					it.setShowOrder(3);
					litem.add(it);
				}
			}
			
			model.setBodyItems(litem.toArray(new BillItem[0]));
			//System.out.println("------------------------");
			card.setBillData(card.getBillData());//���¿̻�ģ��
			TableColumnModel cm=card.getBillTable().getColumnModel();
			GroupableTableHeader header=(GroupableTableHeader)card.getBillTable().getTableHeader();
			System.out.println(header);
			System.out.println("--------------------");
			for(int j=0;j<row;j++){
				String key=(String)step1model.getValueAt(j, "dname");
				ColumnGroup cg=new ColumnGroup(key);
				BillItem[] shows=card.getBodyItems();
				for(BillItem show:shows){
					if(show.getKey().startsWith("hx"+key+"***")){
						int col=model.getBodyColByKey(show.getKey());
						cg.add(cm.getColumn(this.getBodyColumnByCol(shows, col)));
						System.out.println(show.getKey());
					}
				}
				header.addColumnGroup(cg);
			}
			header.setForeground(Color.BLUE);
			
			
			card.setHeadItem("vmemo", "ѡ����");
			card.setHeadItem("pk_project", vo.getPk_projectid());
			card.setHeadItem("pk_building", vo.getPk_buildingfile());
			System.out.println("------------------------------");
			System.out.println(vo.getPk_buildingfile());
			
			card.setRowNOShow(false);
			
			//�Ƴ��������Զ����Ӽ���
			card.getBillTable().removeSortListener();
			card.getBodyPanel().setAutoAddLine(false);
			
			//���ӱ�ͷ�ļ���
			card.addBillEditListenerHeadTail(new HeadAfterEditLister());
			card.addEditListener(new BodyAfterEditLister());
			card.addBodyEditListener2(new BodyBeforeEditLister());
			model.addLine();
			
			//��ʼ�����ر���
			for(int i=0;i<step1model2.getRowCount();i++){
				model.addLine();
				model.setValueAt(step1model2.getValueAt(i, "floor"), model.getRowCount()-1, "floor");
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
			BillItem[] items=model.getBodyItems();
			int row=model.getRowCount();
			//�ж��Ƿ�ı�������
			if(e.getKey().equals("hxtype")){
				
				Object obj=model.getValueAt(e.getRow(), model.getBodyColByKey(e.getKey()));
				
				if(e.getRow()==0){//�ܻ����������е�Ԫ��
					for(BillItem it : items){
						if(it.getKey().startsWith("hx")){
							for(int i=0;i<row;i++){
								model.setValueAt(obj, i, it.getKey());
							}
						}
					}
				}else{//�л���
					for(BillItem it : items){
						if(it.getKey().startsWith("hx")){
							model.setValueAt(obj, e.getRow(), it.getKey());
						}
					}
				}
			}
			
			//�ڶ������
			if(e.getKey().startsWith("hx")&&!e.getKey().equals("hxtype")){//�л���
				
				Object obj=model.getValueAt(e.getRow(), model.getBodyColByKey(e.getKey()));
				if(e.getRow()==0){
					for(int i=1;i<row;i++){
						model.setValueAt(obj, i, e.getKey());
					}
				}
			}
		}

		@Override
		public void bodyRowChange(BillEditEvent e) {}		
	}
	
	class BodyBeforeEditLister implements BillEditListener2{

		@Override
		public boolean beforeEdit(BillEditEvent e) {
			
			if(e.getKey().startsWith("hx")){//�Ի��Ϳ�ͷ
				
				UIRefPane refp=(UIRefPane)card.getBodyItem(e.getKey()).getComponent();
				Object pk_proj=card.getHeadItem("pk_project").getValueObject();
				refp.getRefModel().addWherePart(" and pk_projectid='"+pk_proj+"'");
			}
			return true;
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
		UIButton sureBtn = new UIButton("��һ��");
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
			
			//�жϻ����Ƿ�¼��ֵ
			BillModel model = card.getBillModel();
			int row=model.getRowCount();
			
			BillItem[] items=model.getBodyItems();
			
			for(BillItem it : items){
				
				if(it.getKey().startsWith("hx")&&!it.getKey().equals("hxtype")){
					
					for(int i=1;i<row;i++){
						if(model.getValueAt(i, it.getKey())==null){
							MessageDialog.showHintDlg(ctr, "��ʾ", "����δȫ��ά����");
							return ;
						}
					}
				}
				
			}
			dialog.setVisible(false);
			new BuildStep3Dlg(ctr,vo,step1card,card);
		}

		public void mouseEntered(MouseEvent e) {}

		public void mouseExited(MouseEvent e) {}

		public void mousePressed(MouseEvent e) {}

		public void mouseReleased(MouseEvent e) {}
	}
	
	class NotMouseLister implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			
			dialog.setVisible(false);
			new BuildStep1Dlg(ctr,step1card,vo);
		}

		public void mouseEntered(MouseEvent e) {}

		public void mouseExited(MouseEvent e) {}

		public void mousePressed(MouseEvent e) {}

		public void mouseReleased(MouseEvent e) {}
	}
	
}