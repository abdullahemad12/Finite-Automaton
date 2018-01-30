package finiteAutomaton;

import java.util.ArrayList;
import finiteAutomaton.State;

public class FiniteAutomata {
	ArrayList<State> states;
	State startState; /*Start State of the machine*/
	char[] alphabet; 
	ArrayList<State> acceptStates;
	ArrayList<Transition> transitions;
	State currentState;
	/**
	 * initializes the tuples of the Finite Automata
	 * @param startStateID
	 * @param statesID
	 * @param alphabet
	 * @param accepStatesID
	 */
	public FiniteAutomata(int startStateID, int[] statesID, char[] alphabet,int[] accepStatesID)
	{
		// creates all the states
		states = new ArrayList<State>();
		for(int i = 0; i < statesID.length; i++)
		{
			states.add(new State(statesID[i])); 
		}
		// sets the start state 
		startState = getStatebyID(states, startStateID);
		
		// sets the alphabet
		this.alphabet = alphabet;
		
		// sets the accept states
		acceptStates = new ArrayList<State>();
		for(int i = 0; i < accepStatesID.length; i++)
		{
			acceptStates.add(getStatebyID(states, accepStatesID[i])); 
		}
		this.currentState = startState;
	}
	/**
	 * int, int, Char -> void
	 * Adds a new transition to the transition function 'row'
	 * int stateID: the Id of the state
	 * int nextStateID: the id of the next state
	 * char c: the input symbol
	 */
	public void nextState (int stateID, int nextStateID, char c)
	{
		State state = getStatebyID(states, stateID);
		State nextState  =  getStatebyID(states, nextStateID);
		state.addNextState(nextState, c);
	}
		
	
	/*
	 * char -> void
	 * Give an input alphabet determines the next state
	 * char c: c must belong to the alphabet
	 */
	public void onInput(char c)
	{
		currentState = this.currentState.nextStateTransition(c);
		
	}
	
	
	/**
	 * 
	 * @param states
	 * @param id
	 * @return
	 */
	public static State getStatebyID(ArrayList<State> states, int id)
	{
		for(State state:states)
		{
			if(state.getId() == id)
			{
				return state;
			}
		}
		return null;
	}
	
	public boolean checkInAlpha(char c)
	{
		for(int i = 0; i < alphabet.length; i++)
		{
			if(alphabet[i] == c)
			{
				return true;
			}
		}
		return false;
	}
	public void printAccpetStates()
	{
		for(State state : acceptStates)
		{
			System.out.println("Accept State" + state.getId());
		}
	}
}
