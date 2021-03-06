package com.hao.spring.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

/**
 * <code>ContextUtils</code>
 *
 * @description:
 * @author: Hao Xueqiang(xueqiang.hao@tendcloud.com)
 * @creation: 2017/6/15
 * @version: 1.0
 */
public class ContextUtils {
    protected static ApplicationContext applicationContext;

    private static Log log = LogFactory.getLog(ContextUtils.class);

    public static void setApplicationContext(ApplicationContext applicationContext) {
        synchronized (ContextUtils.class) {
            log.info("setApplicationContext, notifyAll");
            ContextUtils.applicationContext = applicationContext;
            ContextUtils.class.notifyAll();
        }
    }

    public static ApplicationContext getApplicationContext() {
        synchronized (ContextUtils.class) {
            while (applicationContext == null) {
                try {
                    log.info("getApplicationContext, wait...");
                    ContextUtils.class.wait(60000);
                    if (applicationContext == null) {
                        log.warn("Have been waiting for ApplicationContext to be set for 1 minute", new Exception());
                    }
                } catch (InterruptedException ex) {
                    log.info("getApplicationContext, wait interrupted");
                }
            }
            return applicationContext;
        }
    }

    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }
}
