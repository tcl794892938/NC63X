package nc.ui.zl.cwf_salescontrol.ace.actions;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListSelectionModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumnModel;

import com.beust.jcommander.internal.Lists;
import com.borland.dx.text.Alignment;
import com.dlsc.flexgantt.model.treetable.DefaultColumnModel;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.ui.bill.tools.ColorConstants;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.BillModel.RowNumberModel;
import nc.ui.pub.bill.BillScrollPane.BillTable;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.NCAction;

public class QueryAction extends NCAction{

	Map<String, Integer> map=new HashMap<String, Integer>();
	public BillForm billForm;

	public BillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(BillForm billForm) {
		this.billForm = billForm;
	}
	
	public QueryAction(){
		super();
		this.setCode("queryaction");
		this.setBtnName("查询");
	}
	@Override
	public void doAction(ActionEvent e) throws Exception {
		
		map.clear();
		
		this.billForm.getBillCardPanel().dataNotNullValidate();
		this.billForm.getBillCardPanel().getBillModel().clearBodyData();
		Object obj1=this.billForm.getBillCardPanel().getHeadItem("pk_org").getValueObject();
		Object obj2=this.billForm.getBillCardPanel().getHeadItem("pk_project").getValueObject();
		Object obj3=this.billForm.getBillCardPanel().getHeadItem("pk_building").getValueObject();
		
		BillModel model=billForm.getBillCardPanel().getBillModel();
		
		
		IUAPQueryBS iQ=NCLocator.getInstance().lookup(IUAPQueryBS.class);
		String wheresql=" buildname='"+obj3.toString()+"' and pk_org='"+obj1.toString()+"' and projectname='"+obj2.toString()+"' and nvl(dr,0)=0";
		
		//检查楼栋信息有无数据
		String sql3="select floorn,unit,roomnumber,housestate from zl_housesource where "+ wheresql+" order by to_number(floorn) desc,to_number(unit),to_number(roomnumber)";
		List<Map<String,Object>> maplist=(List<Map<String, Object>>) iQ.executeQuery(sql3, new MapListProcessor());
		
			if(maplist.size()<=0){
				MessageDialog.showHintDlg(billForm, "提示", "无相关信息，请检查");
				return;
			}
		
			//构建列
			List<BillItem> itemlist = new ArrayList<BillItem>();
			String tempsql2="select max(count(roomnumber))  from zl_housesource where  "+wheresql+" group by floorn";
			int count= (Integer) iQ.executeQuery(tempsql2, new ColumnProcessor());
			if(count<=0){
				MessageDialog.showHintDlg(billForm, "提示", "数据有误，请检查！");
				return;
			}
			BillItem it2 = new BillItem();
			it2.setDataType(IBillItem.STRING);
			it2.setWidth(80);
			it2.setNull(false);
			it2.setTatol(false);
			it2.setForeground(ColorConstants.COLOR_BLUE);
			it2.setKey("lc");
			it2.setName("楼层");
			itemlist.add(it2);
			
			for(int j=0;j<count;j++){
				BillItem it = new BillItem();
				it.setDataType(IBillItem.STRING);
				it.setWidth(80);
				it.setNull(false);
				it.setTatol(false);
				it.setForeground(ColorConstants.COLOR_DEFAULT);
				it.setKey("fh"+j);
				it.setName("房号");
				itemlist.add(it);
			}
		
		model.setBodyItems(itemlist.toArray(new BillItem[0]));
		billForm.getBillCardPanel().setBillData(billForm.getBillCardPanel().getBillData());// 重新刻画模版
		this.billForm.getBillCardPanel().setRowNOShow(false);
		
		int lc=0;
		int fh=1;
		//int lc2=Integer.parseInt(maplist.get(0).get("floorn").toString());
		int lc2=-1;
		for(int i=0;i<maplist.size();i++){
			Map<String,Object> map1=maplist.get(i);
			if(lc!=Integer.parseInt(map1.get("floorn").toString())){
				model.addLine();
				lc=Integer.parseInt(map1.get("floorn").toString());
				fh=1;
				lc2+=1;
				model.setValueAt(lc, lc2, "lc");
			}
			
				model.setValueAt("  "+map1.get("unit")+"单元"+map1.get("roomnumber"), lc2, fh);
				map.put(""+map1.get("unit")+"单元"+map1.get("roomnumber").toString(), Integer.parseInt(map1.get("housestate").toString()));
				fh+=1;
		}
		
		int kznum=0;
		int zynum=0;
		int dznum=0;
		int yznum=0;
		int ysnum=0;
		
		for(int i=0;i<model.getRowCount();i++){
			for(BillItem bi:model.getBodyItems()){
				if( model.getValueAt(i, bi.getKey())==null){
					//model.setBackground(Color.black, i, model.getBodyColByKey(bi.getKey()));
					continue;
					//donothing
				}
				if(bi.getKey().equals("lc")){
					model.setForeground(Color.BLUE, i, 0);
					continue;
				}
				
					if(model.getValueAt(i, bi.getKey())==null){
						continue ;
					}
					String fh2=model.getValueAt(i, bi.getKey()).toString().trim();
//					String sqla="select housestate from zl_housesource where floorn=("+model.getRowCount()+"-"+i+") and roomnumber="+fh2+" " +
//							"and "+wheresql;
					//Object obja=iQ.executeQuery(sqla, new ColumnProcessor());
					int state=map.get(fh2);
					switch(state){
						case 0: //空置
							model.setBackground(new Color(255, 238, 188), i,model.getBodyColByKey(bi.getKey()));
							kznum+=1;
							break;
						case 1://自用
							model.setBackground(Color.LIGHT_GRAY, i,model.getBodyColByKey(bi.getKey()));
							zynum+=1;
							break;
						case 2://定租
							model.setBackground(new Color(200, 238, 150), i,model.getBodyColByKey(bi.getKey()));
							dznum+=1;
							break;
						case 3://已租
							model.setBackground(Color.CYAN, i,model.getBodyColByKey(bi.getKey()));
							yznum+=1;
							break;
						case 4://已售
							model.setBackground(Color.RED, i,model.getBodyColByKey(bi.getKey()));
							ysnum+=1;
							break;
					}
						
			}
		}
		billForm.getBillCardPanel().setHeadItem("kz", kznum);
		billForm.getBillCardPanel().setHeadItem("zy", zynum);
		billForm.getBillCardPanel().setHeadItem("dz", dznum);
		billForm.getBillCardPanel().setHeadItem("yz", yznum);
		billForm.getBillCardPanel().setHeadItem("ys", ysnum);
		billForm.getBillCardPanel().getBillTable().setRowHeight(60);
		billForm.getBillCardPanel().getBillTable().setFont(new Font("宋体", Font.PLAIN, 13));
		billForm.getBillCardPanel().getBillTable().setCellSelectionEnabled(true);
		billForm.getBillCardPanel().getBillTable().setRowSelectionAllowed(false);
		billForm.getBillCardPanel().getBillTable().setColumnSelectionAllowed(false);
		
		BillTable bt=(BillTable)billForm.getBillCardPanel().getBillTable();
		
		bt.addMouseListener(new MouseLisnter111(bt));
	}
	
	class MouseLisnter111 implements MouseListener{
		
		private int row=0;
		private int col=0;
		private BillTable bt;
		
		public MouseLisnter111(BillTable bt){
			this.bt=bt;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount()>1){
				return ;
			}
			row=bt.getSelectedRow();
			col=bt.getSelectedColumn();
			QueryAction.this.setBackColor();
			QueryAction.this.billForm.getBillCardPanel().getBillModel().setBackground(new Color(255, 228, 196), row, col);
			QueryAction.this.clearSelectionAndLeadAnchor();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			return ;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO 自动生成的方法存根
			
		}

		public int getRow() {
			return row;
		}

		public void setRow(int row) {
			this.row = row;
		}

		public int getCol() {
			return col;
		}

		public void setCol(int col) {
			this.col = col;
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
			Logger.info("没有找到" + col + "列对应实际列.");
			return -1;
		}
	 
	 private void setBackColor(){
		 
		BillModel model = billForm.getBillCardPanel().getBillModel();
		for(int i=0;i<model.getRowCount();i++){
			for(BillItem bi:model.getBodyItems()){
				if( model.getValueAt(i, bi.getKey())==null){
					model.setBackground(Color.WHITE, i, model.getBodyColByKey(bi.getKey()));
					continue;
				}
				if(bi.getKey().equals("lc")){
					model.setForeground(Color.BLUE, i, 0);
					continue;
				}
				
				String fh2=model.getValueAt(i, bi.getKey()).toString().trim();
				
				int state=map.get(fh2);
				switch(state){
					case 0: //空置
						model.setBackground(new Color(255, 238, 188), i,model.getBodyColByKey(bi.getKey()));
						break;
					case 1://自用
						model.setBackground(Color.LIGHT_GRAY, i,model.getBodyColByKey(bi.getKey()));
						break;
					case 2://定租
						model.setBackground(new Color(200, 238, 150), i,model.getBodyColByKey(bi.getKey()));
						break;
					case 3://已租
						model.setBackground(Color.CYAN, i,model.getBodyColByKey(bi.getKey()));
						break;
					case 4://已售
						model.setBackground(Color.RED, i,model.getBodyColByKey(bi.getKey()));
						break;
					}
						
			}
		}
		 
	 }
	 
	 private void clearSelectionAndLeadAnchor() {
		 
		 DefaultListSelectionModel selectionModel=(DefaultListSelectionModel) billForm.getBillCardPanel().getBillTable().getSelectionModel();
		 DefaultTableColumnModel columnModel=(DefaultTableColumnModel)billForm.getBillCardPanel().getBillTable().getColumnModel();
	        selectionModel.setValueIsAdjusting(true);
	        columnModel.getSelectionModel().setValueIsAdjusting(true);

	        selectionModel.clearSelection();
	        columnModel.getSelectionModel().clearSelection();

	        selectionModel.setAnchorSelectionIndex(-1);
	        selectionModel.setLeadSelectionIndex(-1);
	        columnModel.getSelectionModel().setAnchorSelectionIndex(-1);
	        columnModel.getSelectionModel().setLeadSelectionIndex(-1);

	        selectionModel.setValueIsAdjusting(false);
	        columnModel.getSelectionModel().setValueIsAdjusting(false);
	    }

}
