package frc.robot;

import org.junit.runners.model.InitializationError;
import org.junit.runners.BlockJUnit4ClassRunner;

public class SingleInstanceRunner extends BlockJUnit4ClassRunner {
    private Object instance;
    
    public SingleInstanceRunner(Class<?> clss) throws InitializationError {
        super(clss);
    }
 
    @Override
    protected synchronized Object createTest() throws Exception {
        if (instance == null) {
            instance = super.createTest();
        }
 
        return instance;
    }
} 