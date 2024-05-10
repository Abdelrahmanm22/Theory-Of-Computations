class PdaTransition {
    String startState;
    String input;
    String stackTop;
    String newState;
    String stackPop;
    String stackPush;

    public PdaTransition(String startState, String input, String stackTop, String newState, String stackPop, String stackPush) {
        this.startState = startState;
        this.input = input;
        this.stackTop = stackTop;
        this.newState = newState;
        this.stackPop = stackPop;
        this.stackPush = stackPush;
    }
}