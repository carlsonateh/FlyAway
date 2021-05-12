package service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import constant.Queries;
import model.Airline;
import model.City;
import model.Flight;



public class Database {

	public Map<Integer,City> getAllCities() {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Map<Integer,City> cityMap = new HashMap<>();
		Session session = factory.openSession();
		Query query=session.createQuery(Queries.GET_MASTER_LIST_SOURCE_AND_DESTINATIONS);  
		List<Object[]> cities=query.list();  
		for(Object[] city : cities) {
			int cityId = (int)city[0];
			String cityName = (String)city[1]; 
			cityMap.put(cityId, new City(cityId, cityName));
		}
		session.close();
		factory.close();
		return cityMap;

	}
	
	public Map<Integer,Airline> getAllAirlines() {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Map<Integer,Airline> airlineMap = new HashMap<>();
		Session session = factory.openSession();
		Query query=session.createQuery(Queries.GET_MASTER_LIST_AIRLINES);  
		List<Object[]> airlines=query.list();  
		for(Object[] airline : airlines) {
			int airlineId = (int)airline[0];
			String airlineName = (String)airline[1]; 
			airlineMap.put(airlineId, new Airline(airlineId, airlineName));
		}
		session.close();
		factory.close();
	return airlineMap;
	}

	public Map<Integer,Flight> getAllFlights() {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Map<Integer,Flight> cityMap = new HashMap<>();
		Session session = factory.openSession();
		Query query=session.createQuery(Queries.GET_MASTER_LIST_FLIGHTS); 
		List<Object[]> flights=query.list();  
		for(Object[] flight : flights) {
			int flightId = (int)flight[0];
			String flightName = (String)flight[1];
			int flightPrice = (int)flight[2];
			int sourceId = (int)flight[3];
			int destinationId = (int)flight[4];
			int airlineId = (int)flight[5];
			Date travelDate = (Date)flight[6];
			cityMap.put(flightId, new Flight(flightId, flightName, flightPrice, sourceId, destinationId, airlineId, travelDate));
		}
		session.close();
		factory.close();
		return cityMap;
	}
	
	public Boolean changeAdminPassword(String newPassword) {
		boolean result = false;
		 StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();  
		 Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();  
		  
		SessionFactory factory = meta.getSessionFactoryBuilder().build();  
		Session session = factory.openSession();  
		Transaction t = session.beginTransaction();   
		Query q=session.createQuery(Queries.UPDATE_ADMIN_PASSWORD);  
		q.setParameter("newPassword",newPassword);  
		int status=q.executeUpdate();  
		if(status==1) {
			result=true;
		}
		t.commit();    
		session.close();    
		factory.close();
		return result;
	}
	
	public Boolean verifyAdminLogin(String username, String password) {
		boolean result = false;
		
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();  
		 Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();  
		  
		SessionFactory factory = meta.getSessionFactoryBuilder().build();  
		Session session = factory.openSession();  
		Query q=session.createQuery(Queries.VERIFY_LOGIN);  
		q.setParameter("username",username);  
		q.setParameter("password",password);  
		List list=q.list();
		if(list.size()>0) {
			result=true;
		}
		session.close();    
		factory.close();
		return result;
	}


}
