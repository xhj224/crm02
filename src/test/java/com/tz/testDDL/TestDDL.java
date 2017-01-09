package com.tz.testDDL;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.annotation.Resource;

/**
 * Description:
 * Created by xhj224.
 * Date: 2016/12/10 19:59.
 * Project: crm01.
 */
@ContextConfiguration(classes = AppConfig.class)
public class TestDDL extends AbstractTestNGSpringContextTests {

    @Resource
    private LocalSessionFactoryBean lsfb;

    @Test
    public void testDDl() {
        Configuration cfg = lsfb.getConfiguration();
        SchemaExport export = new SchemaExport(cfg);
        export.create(true, true);
    }
}
