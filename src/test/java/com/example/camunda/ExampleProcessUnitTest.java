package com.example.camunda;

import io.camunda.zeebe.spring.test.ZeebeSpringTest;
import org.camunda.community.process_test_coverage.junit5.platform8.ProcessEngineCoverageExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.Map;

import static io.camunda.zeebe.process.test.assertions.BpmnAssert.assertThat;
import static io.camunda.zeebe.spring.test.ZeebeTestThreadSupport.waitForProcessInstanceCompleted;


@SpringBootTest
@ZeebeSpringTest
@ActiveProfiles("unit-test")
@ExtendWith(ProcessEngineCoverageExtension.class)
class ExampleProcessUnitTest extends CamundaTests {


    @Test
    void happyPath() throws Exception {
        final var processId = "Process_Example";
        final var messageName = "barMessage";
        final var correlationKey = "123";

        completeJobs("foo", Collections.emptyMap());

        final var processInstance =
                createNewProcessInstance(processId, Map.of("anVar", "anValue"));

        assertThat(processInstance).isActive();
        waitIdleProcess();

        final var messageResponse = publishMessage(messageName, correlationKey, Collections.emptyMap());

        waitIdleProcess();
        assertThat(messageResponse);
        assertThat(messageResponse).hasBeenCorrelated();

        waitForProcessInstanceCompleted(processInstance);

        assertThat(processInstance)
                .hasPassedElement("Task_Foo")
                .hasPassedElement("Task_Bar")
                .isCompleted();
    }


}
