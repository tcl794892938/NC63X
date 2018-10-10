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
    
	//第二步返回用
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
			
			//移除表体行自动增加监听
			card.getBillTable(tab1).removeSortListener();
			card.getBillTable(tab2).removeSortListener();
			card.getBodyPanel(tab1).setAutoAddLine(false);
			card.getBodyPanel(tab2).setAutoAddLine(false);
			
			//增加表头的监听
			card.addBillEditListenerHeadTail(new HeadAfterEditLister());
			card.addEditListener(tab1,new BodyAfterEditLister());
			card.addEditListener(tab2,new BodyAfterEditLister());
		}
	}
	private void addCardData(){
		card.setHeadItem("vmemo", "确定单元数、每层的户数以及楼层号");
		card.setHeadItem("pk_project", vo.getPk_projectid());
		//System.out.println(vo.getPk_projectid());
		card.setHeadItem("pk_building", vo.getPk_buildingfile());
		//System.out.println(vo.getPk_buildingfile());
		card.setHeadItem("startfloor", 1);
		
		//为表体1设置模式
		Integer unitnum=vo.getNunit();
		BillModel model1=card.getBillModel(tab1);
		for(int i=0;i<unitnum;i++){
			model1.addLine();
			model1.setValueAt((i+1)+"" +
					"单元", i, "dname");
			model1.setValueAt(String.valueOf((i+1)), i, "dcode");
			model1.setValueAt(2, i, "dnum");//默认每单元2户
		}
		
		//为表体2设置模式
		Integer floornum=vo.getNbuilding();
		BillModel model2=card.getBillModel(tab2);
		for(int i=0;i<floornum;i++){
			model2.addLine();
			model2.setValueAt(floornum-i, i, "floor");
		}
		
	}
	private void initDialog() {
		dialog = this;
		// 设置对话框主题
		this.setTitle("快速建房");
		// 设置最适合的大小
		this.setSize(new Dimension(900, 600));
		// 设置对话框位置，正中央
		this.setLocationRelativeTo(getParent());
		// 设置对话框布局
		this.setLayout(new BorderLayout());
		// 设置按钮
		UIButton sureBtn = new UIButton("下一步");
		UIButton notBtn = new UIButton("取  消");
		sureBtn.setPreferredSize(new Dimension(80, 25));
		notBtn.setPreferredSize(new Dimension(80, 25));
		// 加监听
		sureBtn.addMouseListener(new SureMouseLister());
		notBtn.addMouseListener(new NotMouseLister());

		UIPanel b_panel = new UIPanel();
		b_panel.add(sureBtn);
		//b_panel.add(notBtn);
		// 将panel加载到对话框中
		this.add(card, BorderLayout.CENTER);
		this.add(b_panel, BorderLayout.SOUTH);
		// 设置关闭方式
		this.setDefaultCloseOperation(UIDialog.DISPOSE_ON_CLOSE);
	}
	
	
	class HeadAfterEditLister implements BillEditListener{

		@Override
		public void afterEdit(BillEditEvent e) {
			// TODO 自动生成的方法存根
			if(e.getKey().equals("startfloor")){
				
				Object obj=e.getValue();
				if(obj==null){
					return ;
				}
				
				Integer it=new Integer(obj.toString());
				if(it<=0){
					MessageDialog.showHintDlg(ctr, "提示", "楼层起始层数需大于等于零");
					card.setHeadItem("startfloor", null);
					return ;
				}
				//更改表体楼层
				BillModel model=card.getBillModel(tab2);
				Integer floornum=vo.getNbuilding();
				model.clearBodyData();
				for(int i=0;i<floornum;i++){
					model.addLine();
					model.setValueAt(floornum-(i+1)+it, i, "floor");
				}
				
				//设置表体页签切换
				card.getTabbedPane(IBillItem.BODY).setSelectedIndex(1);
			}
		}

		@Override
		public void bodyRowChange(BillEditEvent e) {}
	}
	class BodyAfterEditLister implements BillEditListener{
		@Override
		public void afterEdit(BillEditEvent e) {
			
			if(e.getKey().equals("dnum")){//户数
				
				BillModel model=card.getBillModel(tab1);
				
				if(e.getValue()==null){
					return ;
				}
				Integer it=new Integer(e.getValue().toString());
				if(it<=0){
					MessageDialog.showHintDlg(ctr, "提示", "单元户数不可小于等于零！");
					model.setValueAt(null, e.getRow(), e.getKey());
					return ;
				}
				if(it>200){
					MessageDialog.showHintDlg(ctr, "提示", "单元户数不可超过200！");
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
					MessageDialog.showHintDlg(ctr, "提示", "楼层数不可小于等于零！");
					model.setValueAt(null, e.getRow(), e.getKey());
					return ;
				}
				if(it>200){
					MessageDialog.showHintDlg(ctr, "提示", "楼层数不可超过200！");
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
			
			//判断户数，楼层是否合法
			BillModel model1=card.getBillModel(tab1);
			Map<String, String> map1=new HashMap<String,String>();
			Map<String, String> map2=new HashMap<String,String>();
			int row1=model1.getRowCount();
			//LinkedHashMap<String, Integer> bodymap=new LinkedHashMap<String, Integer>();//待传参数
			for(int i=0;i<row1;i++){
				Object obj=model1.getValueAt(i, "dname");
				Object obj1=model1.getValueAt(i, "dcode");
				Object obj2=model1.getValueAt(i, "dnum");
				if(obj==null||obj2==null||obj1==null){
					MessageDialog.showHintDlg(ctr, "提示", "单元信息有未录入数据！");
					return ;
				}
				
				if(map1.containsKey(obj)){
					MessageDialog.showHintDlg(ctr, "提示", "单元名称重复！");
					return ;
				}else{
					map1.put(obj.toString(), obj.toString());
				}
				
				if(map2.containsKey(obj1)){
					MessageDialog.showHintDlg(ctr, "提示", "单元编码重复！");
					return ;
				}else{
					map2.put(obj1.toString(), obj1.toString());
				}
				
				//bodymap.put(obj.toString(), new Integer(obj2.toString()));
			}
			
			//判断楼层
			BillModel model2=card.getBillModel(tab2);
			Map<Integer, Integer> map=new HashMap<Integer,Integer>();
			int row=model2.getRowCount(); 
			//List<Integer> bodylist=new ArrayList<Integer>();//待传参数
			for(int i=0;i<row;i++){
				Object obj=model2.getValueAt(i, "floor");
				if(obj==null){
					MessageDialog.showHintDlg(ctr, "提示", "楼层数有未录入数据！");
					return ;
				}
				
				if(map.containsKey(obj)){
					MessageDialog.showHintDlg(ctr, "提示", "楼层数重复！");
					return ;
				}else{
					map.put(new Integer(obj.toString()), new Integer(obj.toString()));
				}
				
				//bodylist.add(new Integer(obj.toString()));
			}
			
			/*HashMap<String, Object> headmap=new HashMap<String, Object>();//代传参数
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
