package finiteAutomaton;

import java.io.IOException;

public class DFiniteAutomata extends FiniteAutomata{

	public DFiniteAutomata(int startStateID, int[] statesID, char[] alphabets,
			int[] accepStatesID) {
		super(startStateID, statesID, alphabets, accepStatesID);
		// TODO Auto-generated constructor stub
	}
	
	public boolean runDFA() throws IOException
	{
		char in;
		while((in = (char) System.in.read()) != '\n')
		{
			if(!checkInAlpha(in))
			{
				return false;
			}
			onInput(in);
		}
		if(this.acceptStates.contains(this.currentState))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/*
	 * main method
	 */
	public static void main(String[] args) throws IOException
	{
		int[] states = {1,2};
		char[] alpha = {'0', '1'};
		int[] acceptState = {1};
		DFiniteAutomata d1 = new DFiniteAutomata(1,states,alpha, acceptState);
		d1.nextState(1, 1, '0');
		d1.nextState(1, 2, '1');
		d1.nextState(2, 2, '0');
		d1.nextState(2, 1, '1');
		System.out.println("Start typing the String");
		if(d1.runDFA())
		{
			System.out.print("\nString was Accepted by the DFA\n");
		}
		else
		{
			System.out.print("\nString was Rejected by the DFA\n");
		}
	}
	
}
