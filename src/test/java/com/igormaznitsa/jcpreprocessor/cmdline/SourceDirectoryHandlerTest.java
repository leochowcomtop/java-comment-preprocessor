package com.igormaznitsa.jcpreprocessor.cmdline;

import com.igormaznitsa.jcpreprocessor.context.PreprocessorContext;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SourceDirectoryHandlerTest extends AbstractCommandLineHandlerTest {

    private static final SourceDirectoryHandler HANDLER = new SourceDirectoryHandler();
    
    @Override
    public void testExecution() throws Exception {
        final PreprocessorContext mock = mock(PreprocessorContext.class);
        
        assertFalse(HANDLER.processArgument(null, mock));
        assertFalse(HANDLER.processArgument("", mock));
        assertFalse(HANDLER.processArgument("/i:", mock));
        assertFalse(HANDLER.processArgument("/I:", mock));
        assertFalse(HANDLER.processArgument("/II", mock));
        
        assertTrue(HANDLER.processArgument("/i:testdir", mock));
        verify(mock).setSourceDirectory("testdir");
    }

    @Override
    public void testName() {
        assertEquals("/I:", HANDLER.getKeyName());
    }

    @Override
    public void testDescription() {
        assertDescription(HANDLER);
    }
    
}