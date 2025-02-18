package com.example.camunda;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import io.camunda.zeebe.client.api.response.PublishMessageResponse;
import io.camunda.zeebe.process.test.api.ZeebeTestEngine;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public abstract class CamundaTests {

    @Autowired
    protected ZeebeClient client;

    @Autowired
    protected ZeebeTestEngine zeebeTestEngine;

    protected void completeJobs(
            final String jobType,
            final Map<String, Object> variables) {
        client
                .newWorker()
                .jobType(jobType)
                .handler((jobClient, job) -> jobClient.newCompleteCommand(job).variables(variables).send())
                .open();
    }

    protected ProcessInstanceEvent createNewProcessInstance(
            final String processId,
            final Map<String, Object> variables
    ) {
        return client
                .newCreateInstanceCommand()
                .bpmnProcessId(processId)
                .latestVersion()
                .variables(variables)
                .send()
                .join();
    }

    protected PublishMessageResponse publishMessage(
            final String messageName,
            final String correlationKey,
            final Map<String, Object> variables
    ) {
        return client
                .newPublishMessageCommand()
                .messageName(messageName)
                .correlationKey(correlationKey)
                .variables(variables)
                .send()
                .join();
    }

    protected void waitIdleProcess() throws InterruptedException, TimeoutException {
        zeebeTestEngine.waitForIdleState(Duration.ofMinutes(5));
    }
}
