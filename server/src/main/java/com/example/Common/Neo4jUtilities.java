package com.example.Common;

import org.neo4j.driver.v1.*;

public class Neo4jUtilities {
    public static Driver createDriver(){
        return GraphDatabase.driver( "bolt://localhost:7687", AuthTokens.basic( "neo4j", "admin" ));
    }
}
