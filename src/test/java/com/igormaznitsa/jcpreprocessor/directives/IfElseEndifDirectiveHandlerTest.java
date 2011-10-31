package com.igormaznitsa.jcpreprocessor.directives;

import org.junit.Test;
import static org.junit.Assert.*;

public class IfElseEndifDirectiveHandlerTest extends AbstractDirectiveHandlerIntegrationTest {

    private static final IfDirectiveHandler IF_HANDLER = new IfDirectiveHandler();
    private static final ElseDirectiveHandler ELSE_HANDLER = new ElseDirectiveHandler();
    private static final EndIfDirectiveHandler ENDIF_HANDLER = new EndIfDirectiveHandler();
    
    @Override
    public void testExecution() throws Exception {
        assertFilePreprocessing("directive_if_else_endif.txt", null);
    }

    @Test
    public void testIf_ExceptionWithoutExpression() throws Exception {
        assertPreprocessorException("\n\n\n   \n  //#if   \ntest\n  //#endif", 5, null);
        assertPreprocessorException("\n\n\n   \n  //#if\ntest\n  //#endif", 5, null);
    }

    @Test
    public void testIf_ExceptionWithoutEndIf() throws Exception {
        assertPreprocessorException("\n\n\n   \n  //#if true\n\n", 5, null);
        assertPreprocessorException("\n\n\n   \n  //#if true\n//#if true\n//#endif\n", 5, null);
    }

    @Test
    public void testElse_ExeptionWithoutIf() throws Exception {
        assertPreprocessorException("\n\n\n   \n  //#else  \ntest\n  //#endif", 5, null);
    }

    @Test
    public void testEndIf_ExceptionWithoutIf() throws Exception {
        assertPreprocessorException("\n\n\n   \n  //#endif", 5, null);
    }

    @Override
    public void testKeyword() throws Exception {
        assertEquals("if",IF_HANDLER.getName());
        assertEquals("else",ELSE_HANDLER.getName());
        assertEquals("endif",ENDIF_HANDLER.getName());
    }

    @Override
    public void testHasExpression() throws Exception {
        assertTrue(IF_HANDLER.hasExpression());
        assertFalse(ELSE_HANDLER.hasExpression());
        assertFalse(ENDIF_HANDLER.hasExpression());
    }

    @Override
    public void testExecutionCondition() throws Exception {
        assertFalse(IF_HANDLER.executeOnlyWhenExecutionAllowed());
        assertFalse(ELSE_HANDLER.executeOnlyWhenExecutionAllowed());
        assertFalse(ENDIF_HANDLER.executeOnlyWhenExecutionAllowed());
    }

    @Override
    public void testReference() throws Exception {
        assertReference(IF_HANDLER);
        assertReference(ELSE_HANDLER);
        assertReference(ENDIF_HANDLER);
    }

    @Override
    public void testPhase() throws Exception {
        assertTrue(IF_HANDLER.isPreprocessingPhaseAllowed());
        assertFalse(IF_HANDLER.isGlobalPhaseAllowed());
        assertTrue(ELSE_HANDLER.isPreprocessingPhaseAllowed());
        assertFalse(ELSE_HANDLER.isGlobalPhaseAllowed());
        assertTrue(ENDIF_HANDLER.isPreprocessingPhaseAllowed());
        assertFalse(ENDIF_HANDLER.isGlobalPhaseAllowed());
    }
    
    
}
