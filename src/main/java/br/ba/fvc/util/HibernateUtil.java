package br.ba.fvc.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            AnnotationConfiguration conf = new AnnotationConfiguration();
            SessionFactory obj = conf.configure("hibernate.cfg.xml").buildSessionFactory();
            return obj;
        } catch (Throwable ex) {
            System.err.println("Falha ao Iniciar Sessão." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
