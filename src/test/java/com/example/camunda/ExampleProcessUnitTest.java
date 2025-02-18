package com.example.camunda;

import io.camunda.zeebe.spring.test.ZeebeSpringTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Map;

import static io.camunda.zeebe.process.test.assertions.BpmnAssert.assertThat;
import static io.camunda.zeebe.spring.test.ZeebeTestThreadSupport.waitForProcessInstanceCompleted;


@SpringBootTest(
        properties = {
                "camunda.client.worker.defaults.enabled=false" // disable all job workers
        })
@ZeebeSpringTest
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
