package com.db;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.entity.Expense;
import com.entity.User;

public class Hibernateutil {

	private static SessionFactory sessionfactory;

	public static SessionFactory getSessionFactory() {
		if (sessionfactory == null) {
			Configuration configuration = new Configuration();

			Properties properties = new Properties();
			properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
			properties.put(Environment.URL, "jdbc:mysql://localhost:3306/expanse_tracker_db");
			properties.put(Environment.USER, "root");
			properties.put(Environment.PASS, "pass@123");
			properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
			properties.put(Environment.HBM2DDL_AUTO, "update");
			properties.put(Environment.SHOW_SQL, true);

			/*
			 * properties.put(Environment.USE_SECOND_LEVEL_CACHE, true);
			 * properties.put(Environment.CACHE_REGION_FACTORY,
			 * "org.cache.ehcache.internal.EhcacheRegionFactory");
			 */

			configuration.setProperties(properties);
			configuration.addAnnotatedClass(User.class);
			configuration.addAnnotatedClass(Expense.class);

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();

			sessionfactory = configuration.buildSessionFactory(serviceRegistry);

		}
		return sessionfactory;
	}
}
