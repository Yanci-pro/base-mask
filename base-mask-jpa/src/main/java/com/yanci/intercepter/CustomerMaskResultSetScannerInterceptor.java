package com.yanci.intercepter;

import com.yanci.MaskResultSetScannerInterceptor;
import com.yanci.util.DesUtil;

import java.util.Objects;

/**
 * @author Yanci
 * @date 2022/6/18  13:51
 * Description
 */
public class CustomerMaskResultSetScannerInterceptor extends MaskResultSetScannerInterceptor {
    /**
     * 自定义的实现类可以根据自己业务处理，根据session，
     * 当前登录用户id的相关信息等内容，比如超级用户admin可以查看所有数据，
     * 当然这个属于具体业务，精确到这个界别也完全可以用框架自带的拦截器处理了，只是预留这么一个方法，实际比较鸡肋
     *
     * @param sql
     * @param obj
     * @param org
     * @return
     */
    @Override
    public Object dealString(String sql, Object obj, Object... org) {
        if (obj == null || org == null || org.length == 0) {
            return obj;
        }
        String key = String.valueOf(org[0]);
        String value = String.valueOf(obj);
        //解析sql判断来源的字段是哪一张表
        String tableName = getTableName(sql, key);

        if (!Objects.equals(tableName, "user")) {
            //如果不是user表不处理
            return obj;
        }
        if (key.contains("phone")) {
            char[] chars = value.toCharArray();
            int length = chars.length / 3;
            for (int i = length; i < 2 * length; i++) {
                chars[i] = '#';
            }
            return new String(chars);
        } else if (key.contains("password")) {
            return DesUtil.encrypt(value);
        }
        return obj;
    }

    private String getTableName(String sql, String key) {
        //解析sql的语句进行处理,暂时写死返回user表
        return "user";
    }

}