package com.igormaznitsa.jcpreprocessor.directives;

import com.igormaznitsa.jcpreprocessor.context.PreprocessorContext;
import com.igormaznitsa.jcpreprocessor.expression.Expression;
import com.igormaznitsa.jcpreprocessor.expression.Value;
import com.igormaznitsa.jcpreprocessor.expression.ValueType;
import java.io.IOException;

public class ExitIfDirectiveHandler extends AbstractDirectiveHandler {

    @Override
    public String getName() {
        return "exitif";
    }

    @Override
    public boolean hasExpression() {
        return true;
    }

    @Override
    public String getReference() {
        return null;
    }

    @Override
    public DirectiveBehaviourEnum execute(String string, ParameterContainer state, PreprocessorContext context) {
        // To end processing the file processing immediatly if the value is true
        final Value condition = Expression.eval(string,context);
        if (condition == null || condition.getType() != ValueType.BOOLEAN) {
            throw new RuntimeException("//#exitif needs a boolean condition");
        }
        if (((Boolean) condition.getValue()).booleanValue()) {
            state.setEndPreprocessing(true);
            return DirectiveBehaviourEnum.READ_NEXT_LINE;
        }
        return DirectiveBehaviourEnum.PROCESSED;
    }

 }
