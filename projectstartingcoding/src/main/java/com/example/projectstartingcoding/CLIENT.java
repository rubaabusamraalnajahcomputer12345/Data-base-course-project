package com.example.projectstartingcoding;

public class CLIENT {
    private int client_id;
    private String name;

    private String city;

    private String phonenumber;
    private String email;

    private String sex;
    private int streetnumber;
    private String BD;
    private int lawyer_id;

    public CLIENT(int client_id,String name,String city,String phonenumber,String email,String sex,String BD ,int lawyer_id ,int streetnumber ) {
        this.phonenumber = phonenumber;
        this.city=city;
        this.lawyer_id=lawyer_id;
        this.BD=BD;
        this.name=name;
        this.email=email;
        this.sex=sex;
        this.client_id=client_id;
        this.streetnumber=streetnumber;
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


    public void setClient_id(int lawyer_id){
        this.client_id=client_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setBD(String BD) {
        this.BD = BD;
    }

    public String getBD() {
        return BD;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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

    public int getLawyer_id() {
        return lawyer_id;
    }

    public void setLawyer_id(int lawyer_id) {
        this.lawyer_id = lawyer_id;
    }
}
