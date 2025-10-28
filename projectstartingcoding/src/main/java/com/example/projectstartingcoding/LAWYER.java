package com.example.projectstartingcoding;

import java.time.LocalDate;

public class LAWYER {

private int lawyer_id;
private String fname;
    private String lname;
    private String city;
   // private String address;
    private String phonenumber;
    private String email;
    private int numberofyear;
private String sex;
    private int streetnumber;
    private String BD;
   // private String education;

    public LAWYER(int lawyer_id,String fname,String lname,String city,String phonenumber,String email,int numberofyear,String sex,String BD  ,int streetnumber) {
        this.phonenumber = phonenumber;
        this.city=city;
        this.BD=BD;
        this.fname=fname;
      //  this.address=address;
        this.email=email;
        this.numberofyear=numberofyear;
        this.lname=lname;
        this.sex=sex;
        this.lawyer_id=lawyer_id;
        this.streetnumber=streetnumber;
    }


    public void setLawyer_id(int lawyer_id){
        this.lawyer_id=lawyer_id;
    }

    public int getLawyer_id() {
        return lawyer_id;
    }

    public void setBD(String BD) {
        this.BD = BD;
    }

    public String getBD() {
        return BD;
    }



    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFname() {
        return fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getLname() {
        return lname;
    }

    public void setNumberofyear(int numberofyear) {
        this.numberofyear = numberofyear;
    }

    public int getNumberofyear() {
        return numberofyear;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setStreetnumber(int streetnumber) {
        this.streetnumber = streetnumber;
    }

    public int getStreetnumber() {
        return streetnumber;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public String getSex() {
        return sex;
    }
}