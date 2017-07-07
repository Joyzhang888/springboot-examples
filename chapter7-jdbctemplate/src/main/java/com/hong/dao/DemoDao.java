package com.hong.dao;

import com.hong.domain.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

/**
 * Created by hong on 2017/4/25.
 */
@Repository
/** 开启事务 **/
@EnableTransactionManagement
public class DemoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * jdbcTemplate 和dataSource
     */
    @Autowired
    private DataSource dataSource;


    /**
     * 使用元数据接口的方式获取Demo列表.
     *
     * @return
     */
    public List<Map<String, Object>> getDemoList() {

        return jdbcTemplate.execute(new StatementCallback<List<Map<String, Object>>>() {
            @Override
            public List<Map<String, Object>> doInStatement(Statement statement) throws SQLException, DataAccessException {
                //查询所有的记录.
                ResultSet resultSet = statement.executeQuery("select * from demo");
                //获取元数据结果集.
                ResultSetMetaData metaData = resultSet.getMetaData();

                //获取到表结构有多少列.
                int columnCount = metaData.getColumnCount();

                List<Map<String, Object>> data = new LinkedList<>();

                List<String> columnNames = new ArrayList<>(columnCount);

                //获取到所有列字段名.
                for (int i = 1; i <= columnCount; i++) {
                    columnNames.add(metaData.getColumnName(i));
                }

                while (resultSet.next()) {
                    Map<String, Object> map = new HashMap<>();
                    for (String columnName : columnNames) {
                        Object columnValue = resultSet.getObject(columnName);
                        map.put(columnName, columnValue);
                    }
                    data.add(map);
                }
                return data;
            }
        });
    }


    /**
     * 通过原生jdbc 方式获取demo对象.
     *
     * @param id
     * @return
     */
    public Demo getDemoById(Long id) {
        Demo demo = new Demo();
        Connection connection = null;
        Savepoint savepoint = null;
        try {
            //获取到数据库连接
            connection = dataSource.getConnection();

            //datasource 事务默认是提交的,设置为false,手动提交.
            connection.setAutoCommit(false);

            //也可以手动设置还原点
            savepoint = connection.setSavepoint();

            //通过连接获取到数据库操作对象
            //注意,这种方式不好，后面的参数有可能发生sql 注入.
//            Statement statement = connection.createStatement();

            PreparedStatement preparedStatement = connection.prepareStatement("select * from demo where id =?");

//            ResultSet resultSet = statement.executeQuery("select * from demo where id =" + id);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                demo.setId(resultSet.getLong("id"));
                demo.setName(resultSet.getString("name"));
            }

            connection.commit();
        } catch (SQLException e) {
            //当发生异常时，手动回滚到之前设置的还原点
            if (connection != null) {
                try {
                    connection.rollback(savepoint);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            //关闭连接.
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return demo;
    }


    /**
     * 在使用事务之前,我们得知道当前数据库是否支持事务.
     *
     * @return
     */
    public Boolean supportedTransaction() {
        boolean supported = false;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            //通过元数据接口获取当前数据库是否支持事务.
            supported = metaData.supportsTransactions();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return supported;
    }


    /**
     * JdbcTemplate 插入记录
     */
    /**
     * 加入事务处理.
     **/
    @Transactional
    public void save() {
        String sql = "insert into demo(name) values(?);";
        jdbcTemplate.update(sql, new Object[]{"张三丰"});

        //故意出错.
        System.out.println(1 / 0);
    }

    /**
     * 使用jdbcTemplate的方式,通过id获取demo对象.
     *
     * @param id
     * @return
     */
    public Demo getById(Long id) {
        String sql = "select * from demo where id=?";
        RowMapper<Demo> rowMapper = new BeanPropertyRowMapper<>(Demo.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }


}
