package com.example.projectstartingcoding;

public class APPOINTMENT {
    int client_id;
    String court_name;
    String date;
    int lawyer_id;
    String start_time;
    String end_time;
    int appointment_id;
    int street_number;
    String city;

    public APPOINTMENT(int appointment_id,String date,String start_time,String end_time,String court_name,int street_number,String city,int lawyer_id,int client_id){
        this.appointment_id=appointment_id;
        this.city=city;
        this.date=date;
        this.end_time=end_time;
        this.client_id=client_id;
        this.lawyer_id=lawyer_id;
        this.start_time=start_time;
        this.court_name=court_name;
        this.street_number=street_number;




    }

    public void setLawyer_id(int lawyer_id) {
        this.lawyer_id = lawyer_id;
    }

    public int getLawyer_id() {
        return lawyer_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(int appointment_id) {
        this.appointment_id = appointment_id;
    }

    public int getStreet_number() {
        return street_number;
    }

    public void setStreet_number(int street_number) {
        this.street_number = street_number;
    }

    public String getCourt_name() {
        return court_name;
    }

    public void setCourt_name(String court_name) {
        this.court_name = court_name;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getStart_time() {
        return start_time;
    }
}
