package com.chris.bulleyeadmin.override;

import com.p6spy.engine.common.P6Util;
import com.p6spy.engine.spy.appender.SingleLineFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Onion on 2017/2/23 0023.
 */
public class P6LogFormat extends SingleLineFormat {

    private static Logger logger = LoggerFactory.getLogger(P6LogFormat.class);

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        Thread t = Thread.currentThread();
        sql = SQLFormatter.format(P6Util.singleLine(sql));
        logger.info(sql);
        return now + "|" + t.getName() + "|" +
                "" + elapsed + "|" +
                "" + category + "|connection " +
                "" + connectionId + "|" +
               // "\nAfter Prepared SQL:" + formatter.format(P6Util.singleLine(prepared)) +
                "\nBefore Prepared SQL:" + sql;
    }
}






