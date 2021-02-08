package bankapp;

public class Customer {
private int customerid;
private int aid;
private String customername;
private String cDOb;
private String custGender;
public Customer(int customerid, int aid, String customername, String cDOb, String custGender) {
	//super();
	this.customerid = customerid;
	this.aid = aid;
	this.customername = customername;
	this.cDOb = cDOb;
	this.custGender = custGender;
}
public int getCustomerid() {
	return customerid;
}
public void setCustomerid(int customerid) {
	this.customerid = customerid;
}
public int getAid() {
	return aid;
}
public void setAid(int aid) {
	this.aid = aid;
}
public String getCustomername() {
	return customername;
}
public void setCustomername(String customername) {
	this.customername = customername;
}
public String getcDOb() {
	return cDOb;
}
public void setcDOb(String cDOb) {
	this.cDOb = cDOb;
}
public String getCustGender() {
	return custGender;
}
public void setCustGender(String custGender) {
	this.custGender = custGender;
}
@Override
public String toString() {
	return "Customer [customerid=" + customerid + ", accid=" + aid + ", customername=" + customername + ", cDOb="
			+ cDOb + ", custGender=" + custGender + "]";
}
Customer()
{
	
}
}
