# Finite Automaton
this project defines a Deterministic Finite automaton as a java object and can simulate all the transitions associated with the DFA.

## Description

A finite automaton is five tuple (Q, Σ, δ, q0, F), such that:
 
1. a finite set of states (Q)
2. a finite set of input symbols called the alphabet (Σ)
3. a transition function (δ : Q × Σ → Q)
4. a start state (q0 ∈ Q)
5. a set of terminal states (F ⊆ Q)

## Package

To import this package to your project simply add the following line to the top of the file you are intending to use this in:
 
`import finiteAutomaton`

## Usage

Once you have defined the five tuples of your DFA and imported the package, you are ready to use the DFA object.

** First you need to create a new object with the following encoding: **

`DFiniteAutomata variable_name = new DFiniteAutomata(StartState,states,alpha, acceptState);`

Where:

- StartState is an int representing the ID of the start state
- states is an array of ints, i.e `int[] states = {1,2}`, where each number represents some state. 
- alpha is an array of chars,  i.e `char[] alpha = {'0', '1'};`, where each char belongs to the alphabet of the DFA.
- acceptState is an array of ints representing the IDs of the of the accept state.

** After creating the DFA object you need to define the transitions: ** 

`variable_name.nextState(int stateID, int nextStateID, char c);`

where: 

- stateID is an int representing the current State.
- nextStateID is an int representing the next state.
- c is a character representing the input alphabet.

** Now you are ready to simulate the DFA **

`variable_name.runDFA()`

this will start reading to inputs from stdin and will terminate once it reads a new line
