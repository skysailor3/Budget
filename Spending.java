package Budget;

import java.sql.SQLException;

public class Spending {
	private double valueOfOutcome;
	private State state;
	
	public Spending(Double a) throws SQLException{
		this.valueOfOutcome = a;
		state.getInstance().setMinusState(a);
	}
	
	public double getValueOfOutcome() {
		return valueOfOutcome;
	}
}
