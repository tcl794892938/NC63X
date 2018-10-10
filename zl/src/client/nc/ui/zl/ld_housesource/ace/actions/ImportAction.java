package nc.ui.zl.ld_housesource.ace.actions;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.IVOPersistence;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.NCAction;
import nc.ui.zl.abs.tool.ExcelUtils;
import nc.vo.pub.lang.UFDouble;
import nc.vo.zl.base_project.ProjectVO;
import nc.vo.zl.hql_builldingfile.BuildingfileVO;
import nc.vo.zl.ld_housesource.HousesourceVO;

public class ImportAction extends NCAction{

	private static final long serialVersionUID = 8519431471345832207L;
	
	int a = 0,b = 0,c = 0,d = 0,e = 0,f = 0,g = 0,h = 0,k = 0,l = 0,m = 0;//每个标题所在位置

	Map<Integer , Object[]> mapList = new HashMap<Integer , Object[]>();
	private ShowUpableBillListView listView;
	public ShowUpableBillListView getListView() {
		return listView;
	}

	public void setListView(ShowUpableBillListView listView) {
		this.listView = listView;
	}

	public ImportAction() {
		super();
		this.setCode("import");
		this.setBtnName("导入");
	}

	@Override
	public void doAction(ActionEvent arg0) throws Exception {
		
		Object[][] exlimp =ExcelUtils.doImport(listView);
		if(exlimp==null){
			return;
		}
		if(null!=exlimp){
			//将Excel中的值放到mapList中
			for(int i=0;i<exlimp.length;i++){
				mapList.put(i, exlimp[i]);	
			}
			//格式校验	
			boolean gsFag=checkExcelGS(exlimp[0]);
			if(!gsFag){
				String errMsg="导入的Excel格式不正确，缺少标题或标题写错！";
				MessageDialog.showHintDlg(listView, "提示", errMsg);
				return ;
			}
			
			
			Object[] exobj_0 = mapList.get(0);
			BillListPanel bp=listView.getBillListPanel();
			for(int i=0;i<exobj_0.length;i++){
				if("组织编码".equals(getStgObj(exobj_0[i]))){
					a=i;
				}else if(getStgObj(bp.getHeadItem("pk_org").getName()).equals(getStgObj(exobj_0[i]))){
					b=i;
				}else if(getStgObj(bp.getHeadItem("projectname").getName()).equals(getStgObj(exobj_0[i]))){
					c=i;
				}else if(getStgObj(bp.getHeadItem("buildname").getName()).equals(getStgObj(exobj_0[i]))){
					d=i;
				}else if(getStgObj(bp.getHeadItem("unit").getName()).equals(getStgObj(exobj_0[i]))){
					e=i;
				}else if(getStgObj(bp.getHeadItem("roomnumber").getName()).equals(getStgObj(exobj_0[i]))){
					f=i;
				}else if(getStgObj(bp.getHeadItem("estatecode").getName()).equals(getStgObj(exobj_0[i]))){
					g=i;
				}else if(getStgObj(bp.getHeadItem("estatename").getName()).equals(getStgObj(exobj_0[i]))){
					h=i;
				}else if(getStgObj(bp.getHeadItem("buildarea").getName()).equals(getStgObj(exobj_0[i]))){
					k=i;
				}else if(getStgObj(bp.getHeadItem("innerarea").getName()).equals(getStgObj(exobj_0[i]))){
					l=i;
				}else if(getStgObj(bp.getHeadItem("housestate").getName()).equals(getStgObj(exobj_0[i]))){
					m=i;
				}
			}
			
			
			//校验输入数据是否合法
			boolean isFlag = isNumberD(exlimp);
			if(!isFlag){
				MessageDialog.showHintDlg(listView, "提示", "添加数据不合法！");
				return ;
			}
			
			
			//数据库修改操作
			
			//查询导出数据
			IUAPQueryBS iQ = NCLocator.getInstance().lookup(IUAPQueryBS.class);
			
			//获取组织pk_org
			String get_pk_org_o = "select org_orgs.pk_org from org_orgs where nvl(dr,0)=0 and org_orgs.name='"+getStgObj(exlimp[1][b])+"' and org_orgs.code='"+getStgObj(exlimp[1][a])+"'";
			Object pk_org_o=iQ.executeQuery(get_pk_org_o, new ColumnProcessor());
			if(pk_org_o==null || "".equals(pk_org_o)){
				MessageDialog.showHintDlg(listView, "提示", "组织编码不存在！");
				return;
			}
			
			//获取项目信息主键
			String get_pk_proj_o = "select pk_project from zl_project where nvl(dr,0)=0 and name='"+getStgObj(exlimp[1][c])+"' and pk_org='"+getStgObj(pk_org_o)+"'";
			Object pk_proj_o=iQ.executeQuery(get_pk_proj_o, new ColumnProcessor());
			if(pk_proj_o==null || "".equals(pk_proj_o)){
				MessageDialog.showHintDlg(listView, "提示", "项目信息不存在！");
				return;
			}
			
			//获取楼栋主键
			String get_pk_build_o = "select pk_buildingfile from zl_buildingfile where nvl(dr,0)=0 and name='"+getStgObj(exlimp[1][d])+"' and pk_org='"+getStgObj(pk_org_o)+"' and pk_projectid='"+getStgObj(pk_proj_o)+"'";
			Object pk_build_o=iQ.executeQuery(get_pk_build_o, new ColumnProcessor());
			if(pk_proj_o==null || "".equals(pk_proj_o)){
				MessageDialog.showHintDlg(listView, "提示", "该楼栋不存在！");
				return;
			}
			
			//查询导出数据
			String sqlall = "select * from zl_housesource where nvl(dr,0)=0 and pk_org='"+getStgObj(pk_org_o)+"' and projectname='"+getStgObj(pk_proj_o)+"' and buildname='"+getStgObj(pk_build_o)+"' order by to_number(floorn) desc,zl_housesource.unit asc,((to_number(roomnumber)/100)-(to_number(floorn)))*100 asc";
			List<HousesourceVO> voList=(List<HousesourceVO>)iQ.executeQuery(sqlall, new BeanListProcessor(HousesourceVO.class));
			
			for(int i=0;i<voList.size();i++){
				
				for(int j = 1;j < exlimp.length;j++){
					Object[] colobj=mapList.get(j);
					if(voList.get(i).getEstatecode().equals(colobj[g])){
						
						double buildarea = Double.parseDouble(getStgObj(colobj[k]));
						//double innerarea = Double.parseDouble(getStgObj(colobj[l]));
						if(colobj[m].equals("空置")||colobj[m].equals("自用")){
							voList.get(i).setBuildarea(new UFDouble(buildarea));
							//voList.get(i).setInnerarea(new UFDouble(innerarea));
						}
						break;
					}
				}
				
			}
			
			IVOPersistence ivp=NCLocator.getInstance().lookup(IVOPersistence.class);
			
			ivp.updateVOList(voList);
			
			String allbuild = AllBuild(voList);
			UFDouble nb = new UFDouble(getStr(allbuild));
			//String allinner = AllInner(voList);
			//UFDouble ni = new UFDouble(getStr(allinner));
			String allpersonal = AllPersonal(voList);
			UFDouble np = new UFDouble(getStr(allpersonal));
			//获取当前项目下所有楼栋
			String sql_all_build = "select * from zl_buildingfile b where nvl(dr,0)=0 and b.pk_projectid='"+getStgObj(pk_proj_o)+"'";
			List<BuildingfileVO> bvoList=(List<BuildingfileVO>)iQ.executeQuery(sql_all_build, new BeanListProcessor(BuildingfileVO.class));
			//回写楼栋
			for(int i=0;i<bvoList.size();i++){
				String pkb = bvoList.get(i).getPk_buildingfile();
				if(bvoList.get(i).getPk_buildingfile().equals(getStgObj(pk_build_o))){
					bvoList.get(i).setBuiltuparea(nb.sub(np));
					//bvoList.get(i).setInnerarea(ni);
					bvoList.get(i).setPersonalarea(np);
					break;
				}
			}
			ivp.updateVOList(bvoList);
			//回写项目
			//获取当前项目下所有非自用面积
			UFDouble nparea = new UFDouble(0);
			for(int i=0;i<bvoList.size();i++){
				nparea = nparea.add(getStr(bvoList.get(i).getBuiltuparea()));
				
			}
			String sql_back_project = "select * from zl_project  where nvl(dr,0)=0 and pk_project='"+getStgObj(pk_proj_o)+"'";
			ProjectVO pvo=(ProjectVO)iQ.executeQuery(sql_back_project, new BeanProcessor(ProjectVO.class));
			pvo.setPk_project(getStgObj(pk_proj_o));
			pvo.setNarea(nparea);
			ivp.updateVO(pvo);
			
			MessageDialog.showHintDlg(listView, "提示", "导入成功！");
		}
		
		
	}
	//校验格式是否正确
		public boolean checkExcelGS(Object [] exobj){
			int exlength=exobj.length;
			//将标题放到map中
			Object[] exob = mapList.get(0);
			int count=0;
			BillListPanel bp=listView.getBillListPanel();
			for(int i=0;i<exlength;i++){
				if("组织编码".equals(getStgObj(exob[i]))||
						getStgObj(bp.getHeadItem("pk_org").getName()).equals(getStgObj(exob[i]))||
						getStgObj(bp.getHeadItem("projectname").getName()).equals(getStgObj(exob[i]))||
						getStgObj(bp.getHeadItem("buildname").getName()).equals(getStgObj(exob[i]))||
						getStgObj(bp.getHeadItem("unit").getName()).equals(getStgObj(exob[i]))||
						getStgObj(bp.getHeadItem("roomnumber").getName()).equals(getStgObj(exob[i]))||
						getStgObj(bp.getHeadItem("estatecode").getName()).equals(getStgObj(exob[i]))||
						getStgObj(bp.getHeadItem("estatename").getName()).equals(getStgObj(exob[i]))||
						getStgObj(bp.getHeadItem("buildarea").getName()).equals(getStgObj(exob[i]))
						//||getStgObj(bp.getHeadItem("innerarea").getName()).equals(getStgObj(exob[i]))
						){
					count++;
				}
			}
			if(count<9){
				return false;
			}
			
			
			return true;
		}
		
		//判断数据是否合法
		public boolean isNumberD(Object [][] obj) throws Exception{
			for(int i=1;i<obj.length;i++){
				Object[] exobj=obj[i];
				//String pk_housesource = getStgObj(exobj[g]);//房源主键
				String builda = getStgObj(exobj[k]);//建筑面积
				String innera = getStgObj(exobj[l]);//套内面积
				//判断是否为整数
				 Pattern pattern1 = Pattern.compile("[0-9]*"); 
				   Matcher isNumB_1 = pattern1.matcher(builda);
				   Matcher isNumI_1 = pattern1.matcher(innera);
				//判断是否为双精度数据
				   Pattern pattern2 = Pattern.compile("^[-\\+]?[.\\d]*$");
				   Matcher isNumB_2 = pattern2.matcher(builda);
				   Matcher isNumI_2 = pattern2.matcher(innera);
				   
				   //将数据转化为UFDouble类型数据
				   UFDouble build = new UFDouble(builda);
				   UFDouble inner = new UFDouble(innera);
				   
				   //判断
				   
				   if((isNumB_1.matches()||isNumB_2.matches())&&(isNumI_1.matches()||isNumI_2.matches())){
					   if(!((build.toDouble()>=0)&&(inner.toDouble()>=0))){
						   MessageDialog.showHintDlg(listView, "提示", "Excel中第"+(i+1)+"行填入数据不能小于0，请检查！");
					       return false;
						   
					   }
				   }else{
					   MessageDialog.showHintDlg(listView, "提示", "Excel中第"+(i+1)+"行填入数据类型不合法，请检查！");
				       return false; 
				   }
				  
				  // return false;	
			}
			return true;
			
		}
		//获取建筑面积总面积
		public String AllBuild(List<HousesourceVO> obj){
			UFDouble allBuild = new UFDouble(0);
			for(int i=0;i<obj.size();i++){
				
				//UFDouble m = new UFDouble(obj.get(i).getBuildarea());
				allBuild = allBuild.add(getStr(obj.get(i).getBuildarea()));
			}
			return allBuild.toString();
		}
		//获取套内面积总面积
		public String AllInner(List<HousesourceVO> obj){
			UFDouble allInner = new UFDouble(0);
			for(int i=0;i<obj.size();i++){
				
				//UFDouble m = new UFDouble(obj.get(i).getInnerarea());
				allInner = allInner.add(getStr(obj.get(i).getInnerarea()));
			}
			return allInner.toString();
		}
		//获取建筑面积的自用总面积
		public String AllPersonal(List<HousesourceVO> obj){
			UFDouble allPersonal = new UFDouble(0);
			for(int i=0;i<obj.size();i++){
				if(obj.get(i).getHousestate().equals(1)){
					allPersonal = allPersonal.add(getStr(obj.get(i).getBuildarea()));
				}
				
			}
			return allPersonal.toString();
			
		}
		//字符串封装
		public String getStgObj(Object obj){
			return obj==null?"":obj.toString();
		}
		//判断字符串是否为空
		public UFDouble getStr(Object str){
			
			return str==null?new UFDouble(0):new UFDouble(str.toString());
		}

}
