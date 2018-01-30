package finiteAutomaton;

import java.util.ArrayList;

public class State {
	
	private int id; /*id of the state*/
	private String name; /*the name of the state*/
	private ArrayList<Transition> transitions; /*The next States*/
	
	/**
	 * 
	 * @param name the name of the State
	 * @param id the ID of the state
	 */
	public State(String name, int id)
	{
		this.transitions = new ArrayList<Transition>();
		this.id = id;
		this.name = name;
	}
	/** 
	 * @param id the ID of the state
	 */
	public State(int id)
	{
		this.transitions = new ArrayList<Transition>();
		this.id = id;
		this.name = "Defualt";
	}
	
	/**
	 * Adds a new transition to another state
	 */
	public void addNextState(State next, char symbol)
	{
		transitions.add(new Transition(this, next,symbol));
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Transition> getTransitions() {
		return transitions;
	}

	/*
	 * Char -> State
	 * Given a Transition symbol, moves to the next state
	 * returns the next State
	 * char c: Symbol in the alphabet
	 */
	public State nextStateTransition(char c)
	{
		for(Transition next: transitions)
		{
			if(next.getSymbol() == c)
			{
				return next.getNextState();
			}
		}
		return this;
		
	}
	
	
}
