package Budget;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class History {

	public static ArrayList<History> historyList = new ArrayList<History>();
	private Double amount;
	private String describtion;
	private String incomeOrSpending;
	public String selectedCategory;
	private int i;
	private LoginWindow lw;
	
	public History(Double a, String d, String i, String h){
		this.amount = a;
		this.describtion = d;
		this.incomeOrSpending = i;
		this.selectedCategory = h;
	}
	
	public History(){
		
	}
	
	public Object[][] dataToTable() throws SQLException{
		fillHistory();
		DecimalFormat df = new DecimalFormat("#####0.00"); 
		Object[][] dataTable = new Object[historyList.size()][5];
		for(int x = 0; x<historyList.size(); x++){
				dataTable[x][0] = x+1;
				dataTable[x][1] = historyList.get(x).amount;
				dataTable[x][2] = historyList.get(x).describtion;
				dataTable[x][3] = historyList.get(x).incomeOrSpending;
				dataTable[x][4] = historyList.get(x).selectedCategory;		
		}
		
		return dataTable;
		
	}

	private void fillHistory() throws SQLException {
		if(historyList != null){
			historyList.clear();
		}
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/operacje?"
			      + "user=root&password=");
		PreparedStatement ps = con.prepareStatement("SELECT * FROM usersoperations WHERE id_klienta = ?");
		System.out.println("HistoryClass.UserID is: " + lw.getUserID());
		ps.setString(1, lw.getUserID());
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			amount = rs.getDouble("kwota");
			describtion = rs.getString("opis");
			incomeOrSpending = rs.getString("typ");
			selectedCategory = rs.getString("kategoria");
			History h = new History(amount, describtion, incomeOrSpending, selectedCategory);
			historyList.add(h);				
		}
	}
	
	
}
