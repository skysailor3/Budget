package Budget;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;


public class State {
	
	private Double state = 0.0;
	String output = "";
	private boolean checkOnce = false;
	private String userID;
	
	public State(String userID){
		this.userID = userID;
	}

	public void setUserID(String userID){
		this.userID = userID;
	}
	
	public String getState() throws SQLException {
		DecimalFormat df = new DecimalFormat("#####0.00z³"); 
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/operacje?"
			      + "user=root&password=");
		PreparedStatement ps = con.prepareStatement("SELECT kwota FROM usersoperations WHERE id_klienta = ?");
		ps.setString(1, userID);
		ResultSet rs = ps.executeQuery();
		if(!checkOnce){
			checkOnce = true;
			while(rs.next()){
				Double amount = Double.valueOf(rs.getString("kwota"));
				state = state + amount;
			}
		}
		output = df.format(state);
		System.out.println("State.output.getState() = " + output);
		return output;
	}
	
	public void setPlusState(Double a) throws SQLException {
		state =+ a;
		setStateLabel(); 
	}
	
	public void setMinusState(Double a) throws SQLException {
		state =- a;
		setStateLabel();
	}
	
	public void setStateLabel(){
		Budget.labelStan.setText(state.toString());
	}
}
