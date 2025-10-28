package com.example.projectstartingcoding;

public class BILLING {

int billing_id;
String invoice_status;

double rate;
int hours;
  double  expenses;
  int lawyer_id;
    int caseid;


    public BILLING(int billing_id,int hours,double rate,double expenses,String invoice_status,int lawyer_id,int caseid){
        this.billing_id=billing_id;
        this.caseid=caseid;
        this.hours=hours;
        this.rate=rate;
        this.expenses=expenses;
        this.invoice_status=invoice_status;
        this.lawyer_id=lawyer_id;

    }

    public void setCaseid(int caseid) {
        this.caseid = caseid;
    }

    public int getCaseid() {
        return caseid;
    }

    public int getLawyer_id() {
        return lawyer_id;
    }

    public void setLawyer_id(int lawyer_id) {
        this.lawyer_id = lawyer_id;
    }

    public int getBilling_id() {
        return billing_id;
    }

    public void setBilling_id(int billing_id) {
        this.billing_id = billing_id;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getInvoice_status() {
        return invoice_status;
    }

    public void setInvoice_status(String invoice_status) {
        this.invoice_status = invoice_status;
    }
}
