package Budget;

import java.sql.SQLException;

public class Income implements Observe{
	
	private double newValue;
	private String s;
	public State state = new State(s);
	
	public Income(){
		
	}
	
	public void addNewIncome(Double d){
		newValue = d;
		execute();
	}
	
	@Override
	public void execute() {
		try {
			state.setPlusState(newValue);
			System.out.println("Income.amount: " + newValue);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
