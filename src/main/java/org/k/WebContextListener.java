package org.k;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Kateryna on 30.07.2015.
 */
public class WebContextListener implements ServletContextListener {
    private Thread thread;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        UpdatingRunnable updatingRunnable = new UpdatingRunnable();

        thread = new Thread(updatingRunnable);
        thread.setDaemon(true);
        thread.start();

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        thread.interrupt();
    }

}
