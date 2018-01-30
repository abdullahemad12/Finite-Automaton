/**
 * @author Abdullah Emad
 * @email: abdullahem1997@hotmail.com
 * 
 * This class represents a Nondeterministic finite Automaton 
 * The Automaton accepts any type of transitions 
 * All of the epsilon transitions must be denoted by the character 'E'
 *
 * In order to Start running the machine, it must be converted first to a DFA using the NFA 
 * 
 * Glossary: 
 * 
 * 1) A state Mapping: it is just a renaming for the sets of the power set of the NFA State; For example 
 *    if the power_states(NFA) = {{}, {1}, {2}, {1,2}} then the state mapping is {} -> 0 , {1} -> 1, {2} -> 2, 
 *    {1,2}-> 3   
 * 
 */
package finiteAutomaton;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class NFiniteAutomata extends FiniteAutomata{

	/*All the Transitions Labeled E will be treated as Epsilon Transitions*/
	public NFiniteAutomata(int startStateID, int[] statesID, char[] alphabet,
			int[] accepStatesID) {
		super(startStateID, statesID, alphabet, accepStatesID);
		// TODO Auto-generated constructor stub
	}
	
	/* 
	 * NFiniteAutomata -> DfiniteAutomata
	 * converts the NFA to a DFA 
	 */
	public DFiniteAutomata convertToDFA()
	{
		// creates the power set of the NFA 
		Set<HashSet<Integer>> powerset = this.powerSet();
		int startState = this.getStartState(powerset);
		int[] states = createStatesMap(powerset);
		char[] alphabet = this.convertAlphabet();
		int[] acceptStates = convertAcceptStates(powerset);
		DFiniteAutomata DFA =  new DFiniteAutomata(startState, states, alphabet, acceptStates);
		convertTransitionFunction(DFA);
		return DFA;
	}
	
	/*
	 * gets the start State according to the powerset
	 * returns the Map
	 */
	private int getStartState(Set<HashSet<Integer>> x)
	{
		int map = 0;
		HashSet<Integer> startState = this.EpsilonClosure(this.startState.getId());
		for(HashSet<Integer> i : x)
		{
			if(equals(startState, i))
			{
				return map;
			}
			map++;	
		}
		return 1; // default value
	}
	
	/**
	 * 
	 * @return the power set of the set Of states of the NFA
	 */
	private Set<HashSet<Integer>> powerSet()
	{
		Set<HashSet<Integer>> powerset = new HashSet<HashSet<Integer>>();
		for(int i = 0, n = (int) Math.pow(2, this.states.size()); i < n; i++)
		{
			HashSet<Integer> subset = new HashSet<Integer>();
			
			for(int j = 0, m = this.states.size(); j < m; j++)
			{
				// reads i bit by bit
				int bit = (i >> j) & 0x1;
				if(bit == 1)
				{
					subset.add(this.states.get(j).getId());
				}
			}
			powerset.add(subset);
		}
		return powerset;
	}
	

	/**
	 * makes sure that the symbols does not contain Epsilon
	 */
	private char[] convertAlphabet()
	{
		char[] newalpha = new char[this.alphabet.length];
		for(int i = 0; i < this.alphabet.length; i++)
		{
			if(alphabet[i] != 'E')
			{
				newalpha[i] =  alphabet[i];
			}
		}
		return newalpha;
	}
	
	/**
	 * 
	 */
	 private HashSet<Integer> EpsilonClosure(int StateID)
	{
		return EpsilonClosureHelper(StateID,this.states.size()); 
	}

	
	
	/**
	 * @ n_states: to avoid infinite loops
	 * @return the epsilon closure of a give state of the NFA
	 */
	private HashSet<Integer> EpsilonClosureHelper(int StateID, int n_states)
	{
		/*Makes sure the function does not keep looping*/
		if(n_states == 0)
		{
			return new HashSet<Integer>();
		}
		/*Worst case: all the state are reachable by epsilon transitions From this state*/
		HashSet<Integer> epsiloncl_set = new HashSet<Integer>(); 
		epsiloncl_set.add(StateID);
		
		ArrayList<Transition> transitions = getStatebyID(states, StateID).getTransitions();
		for( Transition transition : transitions)
		{
			if(transition.getSymbol() == 'E')
			{
				epsiloncl_set.add(transition.getNextState().getId());
				epsiloncl_set.addAll(EpsilonClosureHelper(transition.getNextState().getId(), n_states - 1));
			}
		}
		return epsiloncl_set;
		
	}
	
	/**
	 * 
	 * @param x: the set of the power states
	 * @returns: A map for the accept states
	 */
	private int[] convertAcceptStates(Set<HashSet<Integer>> powerset)
	{
		ArrayList<Integer> newstates = new ArrayList<Integer>();
		// iterates over each of the states in the powerset
		int i = 0;
		for(HashSet<Integer> subset : powerset)
		{
			// iterates over all the discrete set in the set of the powersets
			for(int element : subset)
			{
				// checks if one of the states corresponds to an accept state in the original accept states set
				for(State state: acceptStates )
				{
					if(element == state.getId())
					{
						// adds the mapped ID
						newstates.add(new Integer(i));
					}
				}
			}
			i++;
		}
		// converts the arraylist to array of ints
		int[] ret = new int [newstates.size()];
		int k = 0;
		for(Integer z : newstates)
		{
			
			ret[k++] = z.intValue();
		}
		return ret;
	}
	
	

	
	/**
	 * Converts the Transition of the NFA that invoked it to equivalent DFA 
	 * Transition for the Given DFA
	 * 
	 * DFA: dfa that will hold the new transition functions
	 * @param dfa: A DFA 
	 * 
	 * The DFA cannot be NULL and must contain the powerset of the NFA states with IDs = [0,1,...,n]
	 */
	@SuppressWarnings("unchecked")
	private void convertTransitionFunction(DFiniteAutomata dfa)
	{
		// dfa cannot be null
		 if(dfa == null)
		 {
			 return;
		 }
		 // contains at least 2^n elements where n is the length of the nfa states set
		// if(dfa.states.size() != Math.pow(2, this.states.size()))
		 //{
		//	 return;
		 //}
		 
		 int startStateID = dfa.startState.getId();
		 Set<HashSet<Integer>> powerSet = this.powerSet();
		 Object[] powerArr = powerSet.toArray();

		 
				 
	}
	
	/**
	 * Edits a specific state of the DFA
	 * Typically will be passed the start of the DFA then edit all the reachable states recursively 
	 *
	 * @param State
	 * @param DFAState
	 */
	private void convertStateTransitionshelper(HashSet<Integer> State, DFiniteAutomata dfa, State DFAState, int iterations)
	{
		if(iterations <= 0)
		{
			return;
		}
		Object[] states = State.toArray();
		
		for(int i = 0; i < this.alphabet.length; i++)
		{
			// skips the epsilon transition
			if(this.alphabet[i] == 'E')
			{
				continue;
			}
			HashSet<Integer> nextState = new HashSet<Integer>();
			// for each symbol in the alphabet excluding epsilon
			for(int j = 0; j < states.length; j++)
			{
				State current_State = getStatebyID(this.states, ((Integer)states[j]).intValue());
				
				/*Loops over the Transitions*/
				for(Transition trans : current_State.getTransitions())
				{
					if(trans.getSymbol() == this.alphabet[i])
					{
						nextState.add(trans.getNextState().getId());
					}
				}
				
				Object[] next_state_tmp = nextState.toArray();
				/*calculates the Epsilon closure for the set*/
				for(int z = 0, n = nextState.size(); z < n; z++)
				{
					int stateID = ((Integer)next_state_tmp[z]).intValue();
					nextState.addAll(this.EpsilonClosure(stateID));
				}
				
				int stateID = getElementIndex((HashSet[]) powerSet().toArray() ,nextState);
				
				dfa.nextState(DFAState.getId(), stateID, alphabet[i]);
				
				/*makes the transition function for the next state*/
				convertStateTransitionshelper(nextState, dfa, getStatebyID(dfa.states, stateID), --iterations);
				
				
				
			}
			
		}
		
	}
	
	
	public static void main(String args[])
	{
		int[] states = {1,2,3,6};
		char[] alpha = {'0', '1', 'E'};
		int[] acceptState = {2};
		NFiniteAutomata d1 = new NFiniteAutomata(1,states,alpha, acceptState);
		d1.nextState(1, 1, '0');
		d1.nextState(1, 2, 'E');
		d1.nextState(1, 2, '1');
		d1.nextState(2, 2, '0');
		d1.nextState(2, 1, '1');
		System.out.print("Powerset: ");
		printSet2D(d1.powerSet());
		System.out.print("New Alphabet: ");
		printArr1D(d1.convertAlphabet());
		System.out.print("Accept States: ");
		printArr1D(d1.convertAcceptStates(d1.powerSet()));
		System.out.print("States Map: ");
		printArr1D(createStatesMap(d1.powerSet()));
		System.out.print("Epslion Closure Test: ");
		printSet(d1.EpsilonClosure(1));
		
		System.out.print("\nStart State: ");
		System.out.println(d1.getStartState(d1.powerSet()));
		
		int[] states1 = {1,2};
		char[] alpha1 = {'0', '1'};
		int[] acceptState1 = {1};
		DFiniteAutomata d = new DFiniteAutomata(1,states1,alpha1, acceptState1);
		d1.nextState(1, 1, '0');
		d1.nextState(1, 2, '1');
		d1.nextState(2, 2, '0');
		d1.nextState(2, 1, '1');
		d1.convertTransitionFunction(d);

		

	}
	
/*==================================================================================================*/
	/*Private Static Methods*/
	/**
	 *  compares set1 and set2 to each other
	 *  return true if the two sets are equal and false otherwise
	 *  
	 *  Adopted from: https://stackoverflow.com/q/44355737
	 * @param set1
	 * @param set2
	 * @return
	 */
	 public static boolean equals(Set<Integer> set1, Set<Integer> set2){

	        if(set1 == null || set2 ==null){
	            return false;
	        }

	        if(set1.size()!=set2.size()){
	            return false;
	        }

	        return set1.containsAll(set2);

	    }
	 
		/**
		 * 
		 * @param x: the Powerset of the NFA state
		 * @return a renaming of the states in the powerset
		 */
		private static int[] createStatesMap(Set<HashSet<Integer>> x)
		{
			int[] ret = new int[x.size()];
			for(int i = 0 ; i < x.size(); i++)
			{
				ret[i] = i;
			}
			return ret;
		}
		
		
		
/*==========================================================================================================*/
/*Testing Functions*/
		
		/*
		 * prints a 2D array
		 */
		private static void printSet2D(Set<HashSet<Integer>> x)
		{
			System.out.printf("{");
			for(HashSet<Integer> y : x)
			{
				System.out.print(" {");
				for(int j : y)
				{
					System.out.print(j + ",");
				}
				System.out.print("},");
			}
			System.out.println("}");
		}
		private static void printArr1D(int[] x)
		{
			System.out.printf("{");
			for(int i = 0; i < x.length; i++)
			{
				System.out.print(x[i] + ", ");
			}
			System.out.println("}");
		}
		private static void printArr1D(char[] x)
		{
			System.out.printf("{");
			for(int i = 0; i < x.length; i++)
			{
				System.out.print(x[i] + " ,");
			}
			
			System.out.print("}");

		}
		private static void printSet(HashSet<Integer> set)
		{
			System.out.print("{");
			for(Integer i : set)
			{
				System.out.print(i.toString() + ", ");
			}
			System.out.println("}");
		}
		private static int getElementIndex(HashSet[] set, HashSet<Integer> element)
		{
			for(int i = 0; i < set.length; i++)
			{
				if(equals(set[i], element))
				{
					return i;
				}
			}
			return 0;
		}

}


