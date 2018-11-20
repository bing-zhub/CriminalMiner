package com.example.Common;

import org.neo4j.driver.v1.*;

public class Neo4jUtilities {
    public static Driver createDriver(){
        return GraphDatabase.driver( "bolt://localhost:7687", AuthTokens.basic( "neo4j", "admin" ) );
    }

    public static void main(String[] args) {
        Driver driver =  createDriver();
        Session session = driver.session();
        StatementResult result = session.run("match(n) return n;");
        System.out.println(result.toString());
        System.out.println();
//        for (Record r : result.list()){
//            System.out.println(r);
//        }
//        while(result.hasNext()){
//            Record record = result.next();
//            System.out.println(record.get("n").get("name"));
//        }
        session.close();
        driver.close();
    }
}
