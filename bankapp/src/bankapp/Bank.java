package bankapp;

//import java.lang.System.Logger;
import java.sql.SQLException;
import java.util.Scanner;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public class Bank {
	static Scanner s = new Scanner(System.in);
	static Account a = new Account();
	static Customer c = new Customer();
	private static Logger log = Logger.getLogger(Bank.class);
	private static Layout layout = new SimpleLayout();
	private static Appender app = new ConsoleAppender(layout);

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		log.addAppender(app);
		Class.forName("com.mysql.cj.jdbc.Driver");
		JdbcRowSet rowset = RowSetProvider.newFactory().createJdbcRowSet();
		String url = "jdbc:mysql://localhost:3306/mydatabase";
		String username = "root";
		String password = "Satyasri@1999";
		rowset.setUrl(url);
		rowset.setUsername(username);
		rowset.setPassword(password);
		rowset.setReadOnly(false);
		int ch;
		do {
			System.out.println("-----------welcome to bank management------------------");
			System.out.println("Enter choice to do operation");
			
			System.out.println(
					"1.Add customer and account details\n2.delete customer and account details\n3.update customer and account details\n4.select a particular customer and account\n5.displaying records\n6.make a transaction\n7.back to menu");
			ch = s.nextInt();
			System.out.println(" ");
			switch (ch) {
			case 1:
				System.out.println("adding customer and account details");
				addCustomer(rowset);
				addAccount(rowset);
				break;
			case 2:
				System.out.println("deleting details");
				deleteAccount(rowset);
				deleteCustomer(rowset);
				
				break;
			case 3:
				System.out.println("updating customer details");
				updateCustomer(rowset);
				updateAccount(rowset);
				break;
			case 4:
				System.out.println("selecting a customer");
				System.out.println("Enter row number to select");
				int d = s.nextInt();
				rowset.absolute(d);
				rowset.setCommand("select * from account");
				rowset.execute();
				System.out.println("Enter accid to select");
				int id = s.nextInt();
				while (rowset.next()) {
					int aid = rowset.getInt(1);
					if (aid == id) {
						System.out.println(rowset.getInt(1) + " " + rowset.getString(2) + " " + rowset.getDouble(3));
					}
				}
				System.out.println("---------------------------------------------");
				rowset.setCommand("select * from customer");
				rowset.execute();
				System.out.println("Enter accid to select");
				int id1 = s.nextInt();
				while (rowset.next()) {
					int aid = rowset.getInt("aid");
					if (aid == id1) {
						System.out.println(rowset.getInt(1) + " " + rowset.getInt(2) + " " + rowset.getString(3) + " "
								+ rowset.getString(4) + " " + rowset.getString(5));
					}
				}
				

			
				break;
			case 5:
				System.out.println("-------------customers-------------");
				displayCustomer(rowset);
				System.out.println("-------------------account-------------------");
				displayAccount(rowset);
				System.out.println("---------------------------------------------");
			break;
		case 6:
			System.out.println("If you want deposit enter(c),otherwise enter (d)");
			char c = s.next().charAt(0);
			if (c == 'd') {
				deposit(rowset);
			}
			if (c == 'c') {
				withdrawl(rowset);
			}
			break;
		case 7:
			System.exit(0);
			break;
		default:
			System.out.println("Enter valid choice");

		}
	} while (ch <= 7);

	}

	private static void deposit(JdbcRowSet rowset) {
		// TODO Auto-generated method stub
		try {
			rowset.setCommand("select * from account");
			rowset.execute();
			System.out.println("enter row number to deposit");
			int d = s.nextInt();
			rowset.absolute(d);
			System.out.println("enter amount you want to deposit:");
			double amount = s.nextInt();
			a.setAccBal(a.getAccBal() + amount);
			rowset.updateDouble("accBal", a.getAccBal());
			rowset.updateRow();
			// rowset.beforeFirst();
			rowset.moveToCurrentRow();
		} catch (SQLException e) {
		} catch (NullPointerException e) {
			log.error("row number not exists");
		}

	}

	private static void withdrawl(JdbcRowSet rowset) {
		// TODO Auto-generated method stub
		try {
			rowset.setCommand("select * from account");
			rowset.execute();
			System.out.println("enter row number to withdrawl");
			int d = s.nextInt();
			rowset.absolute(d);
			System.out.println("enter amount you want to withdrawl:");
			double amount = s.nextInt();
			a.setAccBal(a.getAccBal() - amount);
			rowset.updateDouble("accBal", a.getAccBal());
			rowset.updateRow();
			// rowset.beforeFirst();
			rowset.moveToCurrentRow();
		} catch (SQLException e) {
		} catch (NullPointerException e) {
			log.error("row number not exists");
		}

	}

	private static void displayAccount(JdbcRowSet rowset) {
		try {
			rowset.setCommand("select * from account");
			rowset.execute();

			while (rowset.next()) {
				System.out.println(rowset.getInt(1) + " " + rowset.getString(2) + " " + rowset.getDouble(3));
			}

		} catch (SQLException e) {

		}
	}

	private static void displayCustomer(JdbcRowSet rowset) {
		try {
			rowset.setCommand("select * from customer");
			rowset.execute();
			while (rowset.next()) {
				System.out.println(rowset.getInt(1) + " " + rowset.getInt(2) + " " + rowset.getString(3) + " "
						+ rowset.getString(4) + " " + rowset.getString(5));
			}
		} catch (SQLException e) {
		}
	}

	private static void deleteAccount(JdbcRowSet rowset) throws SQLException {
		// TODO Auto-generated method stub
		try {
			rowset.setCommand("select * from account");
			rowset.execute();
			System.out.println("Enter row number to delete in account");
			int id = s.nextInt();
			rowset.absolute(id);
			rowset.deleteRow();
			//rowset.beforeFirst();
			rowset.moveToCurrentRow();
		} catch (SQLException e) {

		}
		log.info("row deleted");
	}

	private static void deleteCustomer(JdbcRowSet rowset) throws SQLException {
		try {
			rowset.setCommand("select * from customer");
			rowset.execute();
			System.out.println("Enter row number to delete in customer");
			int id = s.nextInt();
			rowset.absolute(id);
			rowset.deleteRow();
			//rowset.beforeFirst();
			rowset.moveToCurrentRow();
		} catch (SQLException e) {

		}
		log.info("row deleted");
	}

	private static void updateCustomer(JdbcRowSet rowset) {
		// TODO Auto-generated method stub
		try {

			rowset.setCommand("select * from customer");
			rowset.execute();
			System.out.println("Enter row number to update in customer");
			int id = s.nextInt();
			if (id > 0) {
				rowset.absolute(id);
				c.setCustomername(a.getAccHoldername());
				System.out.println("Enter customer dob to update:");
				c.setcDOb(s.next());
				System.out.println("Enter customer gender to update:");
				c.setCustGender(s.next());

				rowset.updateInt(2, c.getAid());
				rowset.updateString(3, c.getCustomername());
				rowset.updateString(4, c.getcDOb());
				rowset.updateString(5, c.getCustGender());
				rowset.updateRow();
				rowset.moveToCurrentRow();
			}
		} catch (SQLException | NullPointerException ex) {
		}

	}

	private static void addAccount(JdbcRowSet rowset) throws SQLException {
		// TODO Auto-generated method stub
		try {
			rowset.setCommand("select * from account");
			rowset.execute();
			rowset.moveToInsertRow();
			rowset.updateInt(1, a.getAccid());
			rowset.updateString(2, a.getAccHoldername());
			rowset.updateDouble(3, a.getAccBal());
			rowset.insertRow();
			rowset.beforeFirst();
		} catch (SQLException e) {

		}
		log.info("row inserted");
	}

	private static void addCustomer(JdbcRowSet rowset) throws SQLException {
		// TODO Auto-generated method stub
		rowset.setCommand("select * from customer");
		rowset.execute();
		rowset.moveToInsertRow();
		rowset.updateInt(2, c.getAid());
		rowset.updateString(3, c.getCustomername());
		rowset.updateString(4, c.getcDOb());
		rowset.updateString(5, c.getCustGender());
		rowset.insertRow();
		rowset.beforeFirst();
		log.info("Row inserted");
	}

	public static void updateAccount(JdbcRowSet rowset) throws SQLException {
		try {
			rowset.setCommand("select * from account");
			rowset.execute();
			System.out.println("Enter row number to update in account");
			int id = s.nextInt();
			if (id > 0) {
				System.out.println("Enter accno to edit");
				int id1 = s.nextInt();
				rowset.setCommand("select * from account");
				rowset.execute();
				rowset.absolute(id1);
				a.setAccid(a.getAccid());
				System.out.println("Enter acc Holdername  to update");
				String name = s.next();
				a.setAccHoldername(name);
				System.out.println("Enter acc bal to update");
				double bal = s.nextDouble();
				a.setAccBal(bal);
				rowset.absolute(id);
				rowset.updateInt(1, a.getAccid());
				rowset.updateString(2, a.getAccHoldername());
				rowset.updateDouble(3, a.getAccBal());
				rowset.updateRow();

				rowset.moveToCurrentRow();

			}
		} catch (SQLException | NullPointerException ex) {

		}
		log.info("row updated");
		rowset.beforeFirst();
	}
}
