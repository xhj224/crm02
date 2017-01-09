package com.tz.testDDL;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

/**
 * Description:
 * Created by xhj224.
 * Date: 2016/12/10 19:59.
 * Project: crm01.
 */
public class TestDDL {

    @Test
    public void testDDl() {
        Configuration cfg = new Configuration().configure();
        SchemaExport export = new SchemaExport(cfg);
        export.create(true, true);
    }
}
