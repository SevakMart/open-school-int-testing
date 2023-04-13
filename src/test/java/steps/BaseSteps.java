package steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class BaseSteps {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected final Map<String, Object> params = new HashMap<>();
    protected final Map<String, Object> pathVariables = new HashMap<>();
}
