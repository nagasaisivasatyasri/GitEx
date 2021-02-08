package bankapp;

import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;

public class Account {
private int accid;
private String accHoldername;
private double accBal;
public Account(int accid, String accHoldername, double accBal) {
	//super();
	this.accid = accid;
	this.accHoldername = accHoldername;
	this.accBal = accBal;
}
public Account() {
	//super();
}

public int getAccid() {
	return accid;
}
public void setAccid(int accid) {
	this.accid = accid;
}
public String getAccHoldername() {
	return accHoldername;
}
public void setAccHoldername(String accHoldername) {
	this.accHoldername = accHoldername;
}
public double getAccBal() {
	return accBal;
}
public void setAccBal(double accBal) {
	this.accBal = accBal;
}
@Override
public String toString() {
	return "Account [accid=" + accid + ", accHoldername=" + accHoldername + ", accBal=" + accBal + "]";
}


}
