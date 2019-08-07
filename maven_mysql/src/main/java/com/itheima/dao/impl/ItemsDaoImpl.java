package com.itheima.dao.impl;

import com.itheima.dao.ItemsDao;
import com.itheima.domain.Items;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/***
 * 从数据库中取数据
 * 四个属性：驱动，链接地址，用户名，密码
 */
public class ItemsDaoImpl implements ItemsDao {
    public List<Items> findAll() throws Exception {
        List<Items> list = new ArrayList<Items>();
        Connection connection=null;
        PreparedStatement pst=null;
        ResultSet resultSet =null;
        try{
            //加载驱动类
            Class.forName("com.mysql.jdbc.Driver");
            //现获取contection对象
            connection = DriverManager.getConnection("jdbc:mysql:///day23","root","root");
            //获取操作数据库的对象
            pst=connection.prepareCall("select * from province");
            //执行数据库操作
            resultSet = pst.executeQuery();
            //把数据库结果集转成JavaList集合

            while (resultSet.next()){
                Items items = new Items();
                items.setId(resultSet.getInt("id"));
                items.setName(resultSet.getString("name"));
                list.add(items);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connection.close();
            pst.close();
            resultSet.close();
        }
        return list;

    }
}
