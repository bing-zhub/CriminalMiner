package com.example.Service;

import com.example.Common.BaseException;
import com.example.Common.Neo4jUtilities;
import com.example.Model.Person;
import org.neo4j.driver.v1.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.neo4j.driver.v1.Values.parameters;

public class PersonService {

    public Person findPersonByName(String name) throws BaseException{
        Session session = DatabaseService.driver.session();
        StatementResult result = session.run("match(n) where n.name={name} return n;", parameters("name",name));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");


        int count = 0;

        Person person = new Person();
        while (result.hasNext()) {
            count++;
            Value value = result.next().get("n");
            person.setOccupation(value.get("职业").asString());
            person.setPlaceOfResidence(value.get("户籍所在地").asString());
            person.setNationality(value.get("民族").asString());
            person.setEducationLevel(value.get("文化程度").asString());
            try {
                person.setDateOfBirth(dateFormat.parse(value.get("出生日期").asString()));
            } catch (ParseException e) {
                throw new BaseException("时间解析错误");
            }
            person.setCaseNumber(value.get("案件号").asString());
            person.setName(value.get("name").asString());
            person.setCurrentAddress(value.get("现住址").asString());
            person.setGender(value.get("性别").asString());
            person.setPlaceOfBirth(value.get("出生地").asString());
        }

        if(count==0)
            throw new BaseException("没有此人员");
        else if(count != 1)
            throw new BaseException("有重名人员");
        session.close();
        return person;
    }

    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        try {
            System.out.println(dateFormat.parse("2018年12月10日"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
