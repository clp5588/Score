package com.sanzhong.score.pojo;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public abstract class BaseBean {

    protected static Logger logger = Logger.getLogger(BaseBean.class);
    /**
     * 简单数据类型集合
     */
    public static final Set<Class<?>> SIMPLE_CLASS_SET = new HashSet<Class<?>>(18);

    static {
        SIMPLE_CLASS_SET.add(int.class);
        SIMPLE_CLASS_SET.add(long.class);
        SIMPLE_CLASS_SET.add(float.class);
        SIMPLE_CLASS_SET.add(double.class);
        SIMPLE_CLASS_SET.add(byte.class);
        SIMPLE_CLASS_SET.add(char.class);
        SIMPLE_CLASS_SET.add(short.class);
        SIMPLE_CLASS_SET.add(boolean.class);
        SIMPLE_CLASS_SET.add(Integer.class);
        SIMPLE_CLASS_SET.add(Long.class);
        SIMPLE_CLASS_SET.add(Float.class);
        SIMPLE_CLASS_SET.add(Double.class);
        SIMPLE_CLASS_SET.add(Byte.class);
        SIMPLE_CLASS_SET.add(Character.class);
        SIMPLE_CLASS_SET.add(Short.class);
        SIMPLE_CLASS_SET.add(Boolean.class);
        SIMPLE_CLASS_SET.add(String.class);
        SIMPLE_CLASS_SET.add(Date.class);
    }

    protected static RowMapper mapRow(final Class clazz) throws Exception {
        return new RowMapper() {
            public Object mapRow(ResultSet resultset, int rowNum)
                    throws SQLException {
                Field[] fields = clazz.getDeclaredFields();
                Object o = null;
                try {
                    o = clazz.newInstance();
                } catch (InstantiationException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                }
                for (Field field : fields) {
                    if(!SIMPLE_CLASS_SET.contains(field.getType())){
                        continue;
                    }
                    Method m;
                    try {
                        m = clazz.getMethod("set" + toUpp(field.getName()), new Class[]{field.getType()});
                        m.invoke(o, new Object[]{resultset.getObject(field.getName())});
                    } catch (Exception e) {
                        logger.error("field:" + field + "---" + e.getMessage(), e);
                    }
                }
                return o;
            }

            private String toUpp(String str) {
                if (str != null && str != "") {
                    str = str.substring(0, 1).toUpperCase() + str.substring(1);
                }
                return str;
            }
        };
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
