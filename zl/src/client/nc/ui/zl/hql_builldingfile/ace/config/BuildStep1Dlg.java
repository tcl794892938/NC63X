package nc.ui.zl.hql_builldingfile.ace.config;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIPanel;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillEditListener;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.IBillItem;
import nc.vo.zl.hql_builldingfile.BuildingfileVO;

public class BuildStep1Dlg extends UIDialog{
	private final String tab1="danyuan";
	private final String tab2="louceng";
	
	private Container ctr;
	
	private BuildStep1Dlg dialog;

	private BillCardPanel card;
	
	private BuildingfileVO vo;
	
	private boolean flag=false;
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public BuildStep1Dlg(Container ctr, BuildingfileVO vo) {
		this.ctr = ctr;
		this.vo = vo;
		initialize();
		addCardData();
		initDialog();
		this.showModal();
	}
    
	//�ڶ���������
	public BuildStep1Dlg(Container parent, BillCardPanel card, BuildingfileVO vo) {
		super(parent);
		this.vo=vo;
		this.ctr=parent;
		this.card=card;
		initDialog();
		this.showModal();
	}
	
	private void initialize(){
		if (null == this.card) {
			this.card = new BillCardPanel();
			
			this.card.loadTemplet("1001ZZ1000000001IF52");//step1
			
			card.setRowNOShow(tab1, false);
			card.setRowNOShow(tab2, false);
			
			//�Ƴ��������Զ����Ӽ���
			card.getBillTable(tab1).removeSortListener();
			card.getBillTable(tab2).removeSortListener();
			card.getBodyPanel(tab1).setAutoAddLine(false);
			card.getBodyPanel(tab2).setAutoAddLine(false);
			
			//���ӱ�ͷ�ļ���
			card.addBillEditListenerHeadTail(new HeadAfterEditLister());
			card.addEditListener(tab1,new BodyAfterEditLister());
			card.addEditListener(tab2,new BodyAfterEditLister());
		}
	}
	private void addCardData(){
		card.setHeadItem("vmemo", "ȷ����Ԫ����ÿ��Ļ����Լ�¥���");
		card.setHeadItem("pk_project", vo.getPk_projectid());
		//System.out.println(vo.getPk_projectid());
		card.setHeadItem("pk_building", vo.getPk_buildingfile());
		//System.out.println(vo.getPk_buildingfile());
		card.setHeadItem("startfloor", 1);
		
		//Ϊ����1����ģʽ
		Integer unitnum=vo.getNunit();
		BillModel model1=card.getBillModel(tab1);
		for(int i=0;i<unitnum;i++){
			model1.addLine();
			model1.setValueAt((i+1)+"" +
					"��Ԫ", i, "dname");
			model1.setValueAt(String.valueOf((i+1)), i, "dcode");
			model1.setValueAt(2, i, "dnum");//Ĭ��ÿ��Ԫ2��
		}
		
		//Ϊ����2����ģʽ
		Integer floornum=vo.getNbuilding();
		BillModel model2=card.getBillModel(tab2);
		for(int i=0;i<floornum;i++){
			model2.addLine();
			model2.setValueAt(floornum-i, i, "floor");
		}
		
	}
	private void initDialog() {
		dialog = this;
		// ���öԻ�������
		this.setTitle("���ٽ���");
		// �������ʺϵĴ�С
		this.setSize(new Dimension(900, 600));
		// ���öԻ���λ�ã�������
		this.setLocationRelativeTo(getParent());
		// ���öԻ��򲼾�
		this.setLayout(new BorderLayout());
		// ���ð�ť
		UIButton sureBtn = new UIButton("��һ��");
		UIButton notBtn = new UIButton("ȡ  ��");
		sureBtn.setPreferredSize(new Dimension(80, 25));
		notBtn.setPreferredSize(new Dimension(80, 25));
		// �Ӽ���
		sureBtn.addMouseListener(new SureMouseLister());
		notBtn.addMouseListener(new NotMouseLister());

		UIPanel b_panel = new UIPanel();
		b_panel.add(sureBtn);
		//b_panel.add(notBtn);
		// ��panel���ص��Ի�����
		this.add(card, BorderLayout.CENTER);
		this.add(b_panel, BorderLayout.SOUTH);
		// ���ùرշ�ʽ
		this.setDefaultCloseOperation(UIDialog.DISPOSE_ON_CLOSE);
	}
	
	
	class HeadAfterEditLister implements BillEditListener{

		@Override
		public void afterEdit(BillEditEvent e) {
			// TODO �Զ����ɵķ������
			if(e.getKey().equals("startfloor")){
				
				Object obj=e.getValue();
				if(obj==null){
					return ;
				}
				
				Integer it=new Integer(obj.toString());
				if(it<=0){
					MessageDialog.showHintDlg(ctr, "��ʾ", "¥����ʼ��������ڵ�����");
					card.setHeadItem("startfloor", null);
					return ;
				}
				//���ı���¥��
				BillModel model=card.getBillModel(tab2);
				Integer floornum=vo.getNbuilding();
				model.clearBodyData();
				for(int i=0;i<floornum;i++){
					model.addLine();
					model.setValueAt(floornum-(i+1)+it, i, "floor");
				}
				
				//���ñ���ҳǩ�л�
				card.getTabbedPane(IBillItem.BODY).setSelectedIndex(1);
			}
		}

		@Override
		public void bodyRowChange(BillEditEvent e) {}
	}
	class BodyAfterEditLister implements BillEditListener{
		@Override
		public void afterEdit(BillEditEvent e) {
			
			if(e.getKey().equals("dnum")){//����
				
				BillModel model=card.getBillModel(tab1);
				
				if(e.getValue()==null){
					return ;
				}
				Integer it=new Integer(e.getValue().toString());
				if(it<=0){
					MessageDialog.showHintDlg(ctr, "��ʾ", "��Ԫ��������С�ڵ����㣡");
					model.setValueAt(null, e.getRow(), e.getKey());
					return ;
				}
				if(it>200){
					MessageDialog.showHintDlg(ctr, "��ʾ", "��Ԫ�������ɳ���200��");
					model.setValueAt(null, e.getRow(), e.getKey());
					return ;
				}
			}
			
			if(e.getKey().equals("floor")){
				
				BillModel model=card.getBillModel(tab2);
				
				if(e.getValue()==null){
					return ;
				}
				Integer it=new Integer(e.getValue().toString());
				if(it<=0){
					MessageDialog.showHintDlg(ctr, "��ʾ", "¥��������С�ڵ����㣡");
					model.setValueAt(null, e.getRow(), e.getKey());
					return ;
				}
				if(it>200){
					MessageDialog.showHintDlg(ctr, "��ʾ", "¥�������ɳ���200��");
					model.setValueAt(null, e.getRow(), e.getKey());
					return ;
				}
			}
		}

		@Override
		public void bodyRowChange(BillEditEvent e) {			
		}
		
	}
	class SureMouseLister implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			
			//�жϻ�����¥���Ƿ�Ϸ�
			BillModel model1=card.getBillModel(tab1);
			Map<String, String> map1=new HashMap<String,String>();
			Map<String, String> map2=new HashMap<String,String>();
			int row1=model1.getRowCount();
			//LinkedHashMap<String, Integer> bodymap=new LinkedHashMap<String, Integer>();//��������
			for(int i=0;i<row1;i++){
				Object obj=model1.getValueAt(i, "dname");
				Object obj1=model1.getValueAt(i, "dcode");
				Object obj2=model1.getValueAt(i, "dnum");
				if(obj==null||obj2==null||obj1==null){
					MessageDialog.showHintDlg(ctr, "��ʾ", "��Ԫ��Ϣ��δ¼�����ݣ�");
					return ;
				}
				
				if(map1.containsKey(obj)){
					MessageDialog.showHintDlg(ctr, "��ʾ", "��Ԫ�����ظ���");
					return ;
				}else{
					map1.put(obj.toString(), obj.toString());
				}
				
				if(map2.containsKey(obj1)){
					MessageDialog.showHintDlg(ctr, "��ʾ", "��Ԫ�����ظ���");
					return ;
				}else{
					map2.put(obj1.toString(), obj1.toString());
				}
				
				//bodymap.put(obj.toString(), new Integer(obj2.toString()));
			}
			
			//�ж�¥��
			BillModel model2=card.getBillModel(tab2);
			Map<Integer, Integer> map=new HashMap<Integer,Integer>();
			int row=model2.getRowCount(); 
			//List<Integer> bodylist=new ArrayList<Integer>();//��������
			for(int i=0;i<row;i++){
				Object obj=model2.getValueAt(i, "floor");
				if(obj==null){
					MessageDialog.showHintDlg(ctr, "��ʾ", "¥������δ¼�����ݣ�");
					return ;
				}
				
				if(map.containsKey(obj)){
					MessageDialog.showHintDlg(ctr, "��ʾ", "¥�����ظ���");
					return ;
				}else{
					map.put(new Integer(obj.toString()), new Integer(obj.toString()));
				}
				
				//bodylist.add(new Integer(obj.toString()));
			}
			
			/*HashMap<String, Object> headmap=new HashMap<String, Object>();//��������
			headmap.put("vmemo", card.getHeadItem("vmemo").getValueObject());
			headmap.put("pk_project", card.getHeadItem("pk_project").getValueObject());
			headmap.put("pk_building", card.getHeadItem("pk_building").getValueObject());
			headmap.put("startfloor", card.getHeadItem("startfloor").getValueObject());*/
			
			dialog.setVisible(false);
			new BuildStep2Dlg(ctr,vo,card);
		}

		public void mouseEntered(MouseEvent e) {}

		public void mouseExited(MouseEvent e) {}

		public void mousePressed(MouseEvent e) {}

		public void mouseReleased(MouseEvent e) {}
	}
	class NotMouseLister implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			dialog.setVisible(false);
		}

		public void mouseEntered(MouseEvent e) {}

		public void mouseExited(MouseEvent e) {}

		public void mousePressed(MouseEvent e) {}

		public void mouseReleased(MouseEvent e) {}
	}
}
