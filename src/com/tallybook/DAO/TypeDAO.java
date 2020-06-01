package com.tallybook.DAO;

import java.sql.Connection;
import java.util.List;

import com.tallybook.bean.Page;
import com.tallybook.bean.TypeBean;

public interface TypeDAO {
//	二级勾选框中根据select获取对应的类型项	支出对应的 和 收入对应的
	public List<TypeBean> getTypes(Connection conn,int select);
	
//	实现记一笔
	public void addRecord(Connection conn,double amount, int paytypeid, String remark, int userid);
//	根据typename获取支付类型的id进而去对用户金额处理	
	public int getPayTypeId(Connection conn,String typename);
//	根据typename获取类型项的id
	public int getTypeId(Connection conn,String typename);
//	管理员查看所有的类型(分页展示)
	public List<TypeBean> getTypesAndPaytypeId(Connection conn ,int pageNo , int pageSize);
//	获取类型项数量进而设置page的总记录条数实现分页
	public int countType(Connection conn);
//	管理员删除一个类型项
	public void delType(Connection conn,String typeid);
//	管理员修改一个类型项
	public void alterType(Connection conn,String typeid,String typename,String paytype);
//	管理员增加一个新的类型项
	public void addNewType(Connection conn,String typename,String paytype);
	
	
}
