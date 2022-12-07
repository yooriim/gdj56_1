package com.filter.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListenerTest implements ServletContextListener {

	//alt shft s v
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		//서버가 종료됐을 때			
		System.out.println("서버가 종료됨.");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		//서버가 실행됐을 때 (이거만 하면 안됨. was가 이 클래스가 있는지 모르기 대문에 web.xml가서 추가해줘야 함!)
		System.out.println("서버가 실행됨.");
	
	}
	
	
	
	
}
