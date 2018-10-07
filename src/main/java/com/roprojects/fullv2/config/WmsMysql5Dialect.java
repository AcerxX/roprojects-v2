package com.roprojects.fullv2.config;

import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StringType;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WmsMysql5Dialect extends MySQLDialect {

    public WmsMysql5Dialect() {
        super();
        this.registerFunction("GROUP_CONCAT", new StandardSQLFunction("GROUP_CONCAT", new StringType()));
    }
}
