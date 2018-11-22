package com.example;

import com.example.Common.BaseException;
import com.example.Service.DatabaseService;
import com.example.Service.PersonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.crypto.Data;
import java.sql.Driver;

@SpringBootApplication
public class CriminalMinerApplication {

	public static void main(String[] args) {
		PersonService service = new PersonService();
		try {
			service.findPersonByName("xx");
			System.out.println("Neo4j数据库驱动加载完成");
		} catch (BaseException e) {

		}
		SpringApplication.run(CriminalMinerApplication.class, args);
	}
}
