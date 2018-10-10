package nc.ui.zl.ld_housesource.ace.config;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.vo.pub.lang.UFDouble;

import nc.vo.zl.ld_housesource.HousesourceVO;

public class BatchAreaDlg extends UIDialog {

	private static final long serialVersionUID = -9152484574365793689L;

	public BatchAreaDlg(Container parent) {
		super(parent);
		initialize();
		initDialog();
		this.showModal();
	}

	private BatchAreaDlg dialog;
	
	private BillCardPanel card;
	
	private boolean flag=false;
	
	private HousesourceVO vo;

	private void initialize() {
		if (null == this.card) {
			this.card = new BillCardPanel();
			this.card.loadTemplet("1001ZZ100000000BVYN2");
			BillItem[] items=card.getHeadItems();
			for(BillItem it : items){
				if(!it.getKey().equals("PG_h")&&!it.getKey().equals("PG_h")){
					it.setShow(false);
				}
			}
			card.setBillData(card.getBillData());
		}
	}

	private void initDialog() {
		dialog = this;
		// ���öԻ�������
		this.setTitle("ʵ�����");
		// �������ʺϵĴ�С
		this.setSize(new Dimension(350, 300));
		// ���öԻ���λ�ã�������
		this.setLocationRelativeTo(getParent());
		// ���öԻ��򲼾�
		this.setLayout(new BorderLayout());
		// ���ð�ť
		UIButton sureBtn = new UIButton("ȷ  ��");
		UIButton notBtn = new UIButton("ȡ  ��");
		sureBtn.setPreferredSize(new Dimension(80, 25));
		notBtn.setPreferredSize(new Dimension(80, 25));
		// �Ӽ���
		sureBtn.addMouseListener(new SureMouseLister());
		notBtn.addMouseListener(new NotMouseLister());

		UIPanel b_panel = new UIPanel();
		b_panel.add(sureBtn);
		b_panel.add(notBtn);
		// ��panel���ص��Ի�����
		this.add(card, BorderLayout.CENTER);
		this.add(b_panel, BorderLayout.SOUTH);
		// ���ùرշ�ʽ
		this.setDefaultCloseOperation(UIDialog.DISPOSE_ON_CLOSE);
	}
	
	
	class SureMouseLister implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			
			Object pk_hxtype=card.getHeadItem("pk_hxtype").getValueObject();
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

}

