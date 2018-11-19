package com.example.Model;

import java.util.Date;

public class TestEndPoint {
    private String tester;
    private Date datetime;
    private int flag;


    public TestEndPoint(){

    }

    public TestEndPoint(String tester, Date datetime, int flag){
        this.tester = tester;
        this.datetime = datetime;
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getTester() {
        return tester;
    }

    public void setTester(String tester) {
        this.tester = tester;
    }
}
