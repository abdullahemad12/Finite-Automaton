package finiteAutomaton;

public class Transition {
	private State currentState;
	private State nextState;
	private char symbol;
	
	public Transition (State currentState, State nextState, char symbol)
	{
		this.currentState = currentState;
		this.nextState = nextState;
		this.symbol = symbol;
	}
	public State getNextState() {
		return nextState;
	}


	public char getSymbol() {
		return symbol;
	}
	public State CurrentState() {
		return currentState;
	}

	
}
