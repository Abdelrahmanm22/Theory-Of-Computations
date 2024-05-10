import java.security.InvalidParameterException;
import java.util.*;

public class Pda {
    private final String[] states;
    private final String[] inputAlphabet;
    private final String[] stackAlphabet;
    private final String[] acceptingStates;

    /**
     * A HashMap of transitions. A HashMap is used to speed up searching
     * through the table to find the correct transition.
     * Keys are of the form input,currentState,stackTop.
     */
    private final Map<String, Set<PdaTransition>> transitions = new HashMap<>();
    private final Deque<String> stack = new ArrayDeque<>();

    private String startState;
    private Set<String> currentState;

    /**
     * Constructs a new PDA.
     *
     * @param states          The set of states which the PDA may be in.
     * @param inputAlphabet   The set of inputs which may be supplied to the PDA.
     * @param stackAlphabet   The set of symbols that may appear on the stack.
     * @param acceptingStates The subset of states which are accepting.
     * @param transitions     A list of transitions between states on inputs.
     * @param startState      The starting state.
     */
    public Pda(String[] states, String[] inputAlphabet, String[] stackAlphabet,
               String[] acceptingStates, PdaTransition[] transitions, String startState) {
        this.states = states;
        this.inputAlphabet = inputAlphabet;
        this.stackAlphabet = stackAlphabet;
        this.acceptingStates = acceptingStates;
        this.startState = startState;

        // Initialize current state to the start state
        currentState = new HashSet<>();
        currentState.add(startState);

        // stack.push("$");

        // Populate transitions
        for (PdaTransition t : transitions) {
            String key = getKeyForTransition(t.input, t.startState, t.stackTop);
            this.transitions.computeIfAbsent(key, k -> new HashSet<>()).add(t);
        }
    }

    /**
     * Resets the PDA to its starting state.
     * This method returns the object it is called on, so may be chained.
     */
    public Pda reset() {
        currentState.clear();
        currentState.add(startState);
        stack.clear();
        return this;
    }

    /**
     * Given an input, transitions the PDA to another state according to
     * the transition table.
     * This method returns the object it is called on, so may be chained.
     *
     * @param input The input to the PDA.
     */
    public Pda input(String input) throws InvalidParameterException {
        input = input.replaceAll("\s", "");
        for (int i = 0; i < input.length() + 1; i++) {
            String str;
            if (i == input.length())
                str = "";
            else
                str = String.valueOf(input.charAt(i));

            Set<String> nextState = new HashSet<>();

            boolean epsilonTrans = false, firstItr = true;
            // Explore all possible transitions from the current state
            for (String state : currentState) {
                epsilonTrans = false; // For each state, find the applicable transitions
                if (!str.equals("")) {
                    String key = getKeyForTransition(str, state, stack.peek());
                    Set<PdaTransition> possibleTransitions = transitions.getOrDefault(key, Collections.emptySet());
                    if (possibleTransitions.isEmpty()) {
                        key = getKeyForTransition(str, state, "");
                        possibleTransitions = transitions.getOrDefault(key, Collections.emptySet());
                    }
                    for (PdaTransition transition : possibleTransitions) {
                        // Apply the transition
                        nextState.add(transition.newState);
                        if (!transition.stackPush.equals("")) {
                            stack.push(transition.stackPush);
                        }
                        if (!transition.stackPop.equals("")) {
                            if (!stack.isEmpty() && stack.peek().equals(transition.stackPop)) {
                                stack.pop();
                            } else {
                                // Handle invalid transition due to stack mismatch
                                throw new InvalidParameterException("Invalid transition due to stack mismatch.");
                            }
                        }
                    }
                    if (possibleTransitions.isEmpty() && firstItr) {
                        epsilonTrans = true;
                    }
                    firstItr = false;
                }
                if (epsilonTrans||str=="") {
                    String epsilonKey;
                    if (stack.peek() == null)
                        epsilonKey = getKeyForTransition("", state, "");
                    else
                        epsilonKey = getKeyForTransition("", state, stack.peek());
                    Set<PdaTransition> epsilonTransitions = transitions.getOrDefault(epsilonKey,
                            Collections.emptySet());
                    // System.out.println("epsilon trans: "+ epsilonTransitions);
                    for (PdaTransition epsilonTransition : epsilonTransitions) {
                        nextState.add(epsilonTransition.newState);
                        if (!epsilonTransition.stackPush.equals("")) {
                            stack.push(epsilonTransition.stackPush);
                        }
                        if (!epsilonTransition.stackPop.equals("")) {
                            if (!stack.isEmpty() && stack.peek().equals(epsilonTransition.stackPop)) {
                                stack.pop();
                            } else {
                                // Handle invalid transition due to stack mismatch
                                throw new InvalidParameterException("Invalid transition due to stack mismatch.");
                            }
                        }
                    }
                }
            }
            if (epsilonTrans) {
                i--;
            }
            // Update current state
            currentState = nextState;
        }
        return this;
    }

    /**
     * Returns the current state of the PDA.
     */
    public Set<String> getState() {
        return currentState;
    }

    /**
     * Returns true if any of the current states are accepting states.
     */
    public boolean isAccepting() {
        for (String state : currentState) {
            if (Arrays.asList(acceptingStates).contains(state)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Calculates the HashMap key used to look up the transition which should
     * be taken, given the current state, input, and stack top.
     */
    private String getKeyForTransition(String input, String state, String stackTop) {
        return input + "," + state + "," + stackTop;
    }
}
