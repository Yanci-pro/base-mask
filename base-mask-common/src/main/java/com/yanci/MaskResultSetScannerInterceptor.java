package com.yanci;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetInternalMethods;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.StatementInterceptor;
import com.yanci.util.DesUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @description: some desc
 * @author: yx
 * @date: 2022/6/16 13:13
 */
public class MaskResultSetScannerInterceptor implements StatementInterceptor {
    public MaskResultSetScannerInterceptor() {
    }

    @Override
    public void init(Connection conn, Properties props) throws SQLException {

    }

    @Override
    public ResultSetInternalMethods postProcess(String sql, Statement interceptedStatement, final ResultSetInternalMethods originalResultSet, Connection connection) throws SQLException {

        return (ResultSetInternalMethods) Proxy.newProxyInstance(originalResultSet.getClass().getClassLoader(), new Class[]{ResultSetInternalMethods.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if ("equals".equals(method.getName())) {
                    return args[0].equals(this);
                } else {
                    Object invocationResult = method.invoke(originalResultSet, args);
                    String methodName = method.getName();
                    if (invocationResult != null && invocationResult instanceof String && "getString".equals(methodName)) {
                        return dealString(sql, invocationResult, args);
                    }
                    return invocationResult;
                }
            }
        });
    }

    @Override
    public boolean executeTopLevelOnly() {
        return false;
    }

    @Override
    public void destroy() {

    }

    @Override
    public ResultSetInternalMethods preProcess(String sql, Statement interceptedStatement, Connection connection) throws SQLException {
        return null;
    }

    /**
     * 自定义算法，可以自行根据算法实现
     * @param sql 这里预留sql参数最好在子类中重写这个方法，自行实现这个处理数据的逻辑就行
     * @param obj
     * @param org
     * @return
     */
    public Object dealString(String sql, Object obj, Object... org) {
        if (obj == null || org == null || org.length == 0) {
            return obj;
        }
//        if (Objects.equals(org[0], "phone")) {
//           在jpa中传过来的phone可能变成 phone5_0_，为了兼容jpa放弃精确匹配字段的方法
        String key = String.valueOf(org[0]);
        String value = String.valueOf(obj);

        if (key.contains("phone")) {
            char[] chars = value.toCharArray();
            int length = chars.length / 3;
            for (int i = length; i < 2 * length; i++) {
                chars[i] = '*';
            }
            return new String(chars);
        } else if (key.contains("password")) {
            return DesUtil.encrypt(value);
        }
        return obj;
    }
}
