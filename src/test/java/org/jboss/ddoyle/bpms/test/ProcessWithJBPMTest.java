package org.jboss.ddoyle.bpms.test;

import java.util.HashMap;
import java.util.Map;

import org.jbpm.test.JbpmJUnitBaseTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;

public class ProcessWithJBPMTest extends JbpmJUnitBaseTestCase {
	
	private RuntimeManager runtimeManager;

	private RuntimeEngine runtimeEngine;

	private KieSession kieSession;

	public ProcessWithJBPMTest() {
		// Setup the datasource and enable persistence.
		super(true, true);
	}

	@Before
	public void init() {
		Map<String, ResourceType> testResources = new HashMap<>();
		testResources.put("ruleflow-group.bpmn", ResourceType.BPMN2);
		testResources.put("rules.drl", ResourceType.DRL);
		runtimeManager = createRuntimeManager(testResources);
		//runtimeManager = createRuntimeManager("sample-process.bpmn");
		runtimeEngine = getRuntimeEngine(null);
		kieSession = runtimeEngine.getKieSession();
	}

	@After
	public void destroy() {
		runtimeManager.disposeRuntimeEngine(runtimeEngine);
		runtimeManager.close();
	}

	@Test
	public void testProcessWithJBPM() {
		//Define input
		Input input = new Input();
		
		Map<String, Object> inputParams = new HashMap<>();
		inputParams.put("inputData", input);
		
		kieSession.startProcess("creditEvaluationSimple", inputParams);
	
		//Don't need to do asserts, we just want this process to go to 100% CPU in a test environment.
	}

}