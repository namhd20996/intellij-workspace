package com.example.util;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure().buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session getSession() {
		Session session = null;
		if (sessionFactory != null) {
			session = sessionFactory.openSession();
		}
		return session;
	}

	public static void shutDow() {
		getSessionFactory().close();
	}
}
