package com.wzf.Dbconmb;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.wzf.model.User;

public class DBUtil{
	private static SqlSessionFactory sessionFactory = null;
	private  SqlSessionFactory sessionFactory0 = null;
	private  SqlSession session;
	
	public DBUtil() {
		super();
	}

	public SqlSessionFactory getSessionFactory0() {
		return sessionFactory0;
	}

	public void setSessionFactory0(SqlSessionFactory sessionFactory0) {
		this.sessionFactory0 = sessionFactory0;
	}

	static{
		Reader reader;
		try {
			reader = Resources.getResourceAsReader("config.xml");
			sessionFactory = new SqlSessionFactoryBuilder().build(reader,"db");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public  void createSession(){
		session= sessionFactory.openSession();
	}
	public  void createSession0(){
		session= sessionFactory0.openSession();
	}
	
	public void addUser(User user){
		createSession();
		session.insert("User.insertUser", user);
		session.commit();
		session.close();
	}
	
}
