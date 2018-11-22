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
        StatementResult result = session.run("match(n) where n.name={name} return n,labels(n);", parameters("name",name));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");


        int count = 0;

        Person person = new Person();
        while (result.hasNext()) {
            count++;
            Record record = result.next();
            Value value = record.get("n");
            String labels = "";
            for (Value l: record.get("labels(n)").values()){
                labels =  labels + l.asString()+",";
            }
            person.setLabel(labels);
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
            person.setNumberOfDrugs(value.get("毒品数量").asString());
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

    public void addPerson(Person person) {
        Session session = DatabaseService.driver.session();
        session.run("create a:{label} {name:{name},毒品数量:{numOfDrugs},职业:{job},户籍所在地:{placeOfResidence}," +
                "民族:{nationality},文化程度:{education},出生日期:{dateOfBirth},案件号:{caseNumber},现住址:{curAdd}," +
                "性别:{gender},出生地:{placeOfBirth} }",
                parameters("label",person.getLabel(),"name",person.getName(),"numOfDrugs", person.getNumberOfDrugs(),
                        "job",person.getOccupation(),"placeOfResidence",person.getPlaceOfResidence(), "nationality", person.getNationality(),
                        "education",person.getEducationLevel(),"dateOfBirth",person.getDateOfBirth(), "caseNumber",person.getCaseNumber(),
                        "curAdd",person.getCurrentAddress(),"gender",person.getGender(),"placeOfBirth",person.getPlaceOfBirth()));
    }
}
