package com.example.projectstartingcoding;

public class DOCUMENT {

    private int case_id;
   // private int client_id;
    private int document_id;
    private String doctittle;
    private String date;


    public DOCUMENT(int document_id,String doctittle,String date,int case_id){
        this.document_id=document_id;
        this.doctittle=doctittle;
        this.date=date;
        this.case_id=case_id;

    }

    public int getCase_id() {
        return case_id;
    }

    public void setCase_id(int case_id) {
        this.case_id = case_id;
    }



    public int getDocument_id() {
        return document_id;
    }

    public void setDocument_id(int document_id) {
        this.document_id = document_id;
    }

    public String getDoctittle() {
        return doctittle;
    }

    public void setDoctittle(String doctittle) {
        this.doctittle = doctittle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
