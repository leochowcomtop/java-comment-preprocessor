package com.igormaznitsa.jcpreprocessor.directives;

import com.igormaznitsa.jcpreprocessor.context.PreprocessorContext;
import java.io.IOException;

public class BreakDirectiveHandler extends AbstractDirectiveHandler {

    @Override
    public String getName() {
        return "break";
    }

    @Override
    public boolean hasExpression() {
        return false;
    }

    @Override
    public String getReference() {
        return null;
    }

    @Override
    public DirectiveBehaviourEnum execute(String string, ParameterContainer state, PreprocessorContext configurator) {
        if (state.isWhileCounterZero()) {
            throw new RuntimeException("//#break without //#when detected");
        }

        if (state.isProcessingEnabled() && state.getWhileCounter() == state.getActiveWhileCounter()) {
            state.setThereIsNoBreakCommand(false);
        }
        return DirectiveBehaviourEnum.PROCESSED;
    }

    @Override
    public boolean processOnlyIfProcessingEnabled() {
        return false;
    }
}
