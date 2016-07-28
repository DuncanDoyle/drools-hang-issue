package org.jboss.ddoyle.bpms.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.event.rule.AgendaGroupPoppedEvent;
import org.kie.api.event.rule.AgendaGroupPushedEvent;
import org.kie.api.event.rule.BeforeMatchFiredEvent;
import org.kie.api.event.rule.MatchCancelledEvent;
import org.kie.api.event.rule.MatchCreatedEvent;
import org.kie.api.event.rule.RuleFlowGroupActivatedEvent;
import org.kie.api.event.rule.RuleFlowGroupDeactivatedEvent;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieRuntime;
import org.kie.api.runtime.KieSession;
import org.kie.internal.runtime.StatefulKnowledgeSession;

public class ProcessTestWithoutJBPM {

	
	@Test
	public void testRulesWithoutJBPM() {

		KieServices kieServices = KieServices.Factory.get();
		KieContainer kieContainer = kieServices.newKieClasspathContainer();
		KieSession kieSession = kieContainer.newKieSession();

		try {

			kieSession.addEventListener(new AgendaEventListener() {

				@Override
				public void matchCreated(MatchCreatedEvent event) {
				}

				@Override
				public void matchCancelled(MatchCancelledEvent event) {
				}

				@Override
				public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
				}

				@Override
				public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
				}

				@Override
				public void beforeMatchFired(BeforeMatchFiredEvent event) {
				}

				@Override
				public void agendaGroupPushed(AgendaGroupPushedEvent event) {
				}

				@Override
				public void agendaGroupPopped(AgendaGroupPoppedEvent event) {
				}

				@Override
				public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
				}

				@Override
				public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
					KieRuntime kieRuntime = event.getKieRuntime();
					if (kieRuntime instanceof StatefulKnowledgeSession) {
						((StatefulKnowledgeSession) kieRuntime).fireAllRules();
					}
				}

				@Override
				public void afterMatchFired(AfterMatchFiredEvent event) {
				}
			});

			Input input = new Input();
			Map<String, Object> inputParams = new HashMap<>();
			inputParams.put("inputData", input);

			kieSession.startProcess("creditEvaluationSimple", inputParams);

		} finally {
			kieSession.dispose();
		}
	}
}
