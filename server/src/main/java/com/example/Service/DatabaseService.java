package com.example.Service;

import com.example.Common.Neo4jUtilities;
import org.neo4j.driver.v1.Driver;

public class DatabaseService {
    static Driver driver = Neo4jUtilities.createDriver();
}
