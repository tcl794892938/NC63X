package nc.ui.zl.ld_housesource.ace.config;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.MapProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillEditListener;
import nc.ui.pub.bill.BillModel;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

import nc.vo.zl.ld_housesource.AggHousesourceVO;
import nc.vo.zl.ld_housesource.HousesourceVO;

public class BatchEditDlg extends UIDialog {

	private static final long serialVersionUID = -9152484574365793689L;
    
	private AggHousesourceVO[] aggvos = null;
	
	private Container ctr = null;
	
	public BatchEditDlg(Container parent,String pk_project,AggHousesourceVO[] aggvos) {
		super(parent);
		this.pk_project=pk_project;
		this.aggvos = aggvos;
		this.ctr = parent;
		initialize();
		addCardData(aggvos);
		initDialog();
		this.showModal();
	}

	private BatchEditDlg dialog;
	
	private String pk_project;

	private BillCardPanel card;
	
	private boolean flag=false;
	
	private HousesourceVO vo;

	private void initialize() {
		if (null == this.card) {
			this.card = new BillCardPanel();
			this.card.loadTemplet("1001ZZ100000000BVYN2");
			UIRefPane refp=(UIRefPane)card.getHeadItem("pk_familyfile").getComponent();
			refp.getRefModel().addWherePart(" and pk_projectid='"+pk_project+"'");
			card.addBillEditListenerHeadTail(new HeadAfterEditLister());
		}
	}

	private void initDialog() {
		dialog = this;
		// 设置对话框主题
		this.setTitle("房源批改");
		// 设置最适合的大小
		this.setSize(new Dimension(350, 300));
		// 设置对话框位置，正中央
		this.setLocationRelativeTo(getParent());
		// 设置对话框布局
		this.setLayout(new BorderLayout());
		// 设置按钮
		UIButton sureBtn = new UIButton("确  定");
		UIButton notBtn = new UIButton("取  消");
		sureBtn.setPreferredSize(new Dimension(80, 25));
		notBtn.setPreferredSize(new Dimension(80, 25));
		// 加监听
		sureBtn.addMouseListener(new SureMouseLister());
		notBtn.addMouseListener(new NotMouseLister());

		UIPanel b_panel = new UIPanel();
		b_panel.add(sureBtn);
		b_panel.add(notBtn);
		// 将panel加载到对话框中
		this.add(card, BorderLayout.CENTER);
		this.add(b_panel, BorderLayout.SOUTH);
		// 设置关闭方式
		this.setDefaultCloseOperation(UIDialog.DISPOSE_ON_CLOSE);
	}
	
	
	class SureMouseLister implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			
			Object pk_hxtype=card.getHeadItem("pk_familyfile").getValueObject();
			Object nbuildarea=card.getHeadItem("buildarea").getValueObject();
			Object nindoorarea=card.getHeadItem("innerarea").getValueObject();
			
			
			
			UFDouble ud1=nbuildarea==null?new UFDouble(0):new UFDouble(nbuildarea.toString());
			UFDouble ud2=nindoorarea==null?new UFDouble(0):new UFDouble(nindoorarea.toString());
			
			
			vo=new HousesourceVO();
			
			if(pk_hxtype!=null){
				vo.setPk_familyfile(pk_hxtype.toString());
			}
			
			if(ud1.compareTo(new UFDouble(0))!=0){
				vo.setBuildarea(ud1);
			}
			
			if(ud2.compareTo(new UFDouble(0))!=0){
				vo.setInnerarea(ud2);
			}
			Object code = card.getHeadItem("code").getValueObject();
			Object name = card.getHeadItem("name").getValueObject();
			if(aggvos.length == 1){
				if(code == null){
					MessageDialog.showHintDlg(ctr, "提示", "房产编码不能为空!");
					return;
				}
				if(name == null){
					MessageDialog.showHintDlg(ctr, "提示", "房产名称不能为空!");
					return;
				}
				vo.setEstatecode(code.toString());
				vo.setEstatename(name.toString());
			}
			
			Object status = card.getHeadItem("housestatus").getValueObject();
			if(status != null){
				vo.setHousestate(Integer.parseInt(status.toString()));
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
			if(e.getKey().equals("pk_familyfile")){
				Object obj = e.getValue();
				if(obj != null){
					String pk_familyfile = ((String[])obj)[0];
					IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
					String sql = "select builtuparea,innerarea from zl_familyfile where " +
						 	     "nvl(dr,0)=0 and pk_familyfile = '"+pk_familyfile.toString()+"'";
					Map<String, Object> map = null;
					try {
						map = (Map<String, Object>) iQ.executeQuery(sql, new MapProcessor());
					} catch (BusinessException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					card.setHeadItem("buildarea", map.get("builtuparea"));
					card.setHeadItem("innerarea", map.get("innerarea"));
				}
				
			}
		}

		@Override
		public void bodyRowChange(BillEditEvent e) {}
		
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public HousesourceVO getVo() {
		return vo;
	}

	public void setVo(HousesourceVO vo) {
		this.vo = vo;
	}
	private void addCardData(AggHousesourceVO[] aggvos){
		if(aggvos.length > 1){
			card.getHeadItem("code").setEdit(false);
			card.getHeadItem("name").setEdit(false);
		}else {
			card.getHeadItem("code").setEdit(true);
			card.getHeadItem("name").setEdit(true);
			card.setHeadItem("code", aggvos[0].getParentVO().getEstatecode());
			card.setHeadItem("name", aggvos[0].getParentVO().getEstatename());
			card.setHeadItem("pk_familyfile", aggvos[0].getParentVO().getPk_familyfile());
			card.setHeadItem("buildarea", aggvos[0].getParentVO().getBuildarea());
			card.setHeadItem("innerarea", aggvos[0].getParentVO().getInnerarea());
			card.setHeadItem("housestatus", aggvos[0].getParentVO().getHousestate());
		}
		
	}
}

