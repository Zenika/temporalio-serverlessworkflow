# temporalio-serverlessworkflow
Experimentation about the implementation of (Serverless Workflow)[https://serverlessworkflow.io/] (specifications)[https://github.com/serverlessworkflow/specification/blob/main/specification.md] with (temporalio)[https://www.temporal.io/]


This is based on https://github.com/temporalio/samples-java and tests several use case like : 

* Waiting for an event => implemented with signals in Temporal


# Execute the demo 

* First run the worker that will trigger workflows and activities executions

```shell
./gradlew -q execute -PmainClass=com.zenika.temporalio.serverlessworkflow.Worker
```

* Then to test waiting for event state : 
    * Launch the workflow :
    
    ```shell
    ./gradlew -q execute -PmainClass=com.zenika.temporalio.serverlessworkflow.StarterOnBoarding
    ```

    * Get the current Run ID : 

    ```shell
    tctl workflow list --workflow_id "onboarding" --open 
    ```

    * Send the signal `HiringFormFilledEvent` to the currently running `onboarding` workflow : 

    ```shell
    tctl workflow signal --workflow_id "onboarding" --name "HiringFormFilledEvent" --run_id `tctl workflow list --workflow_id 'onboarding' --open | sed -En '2p' | cut -f 3 -d '|'`
    ```

    * Send the signal `ContractSignedEvent` to the currently running `onboarding` workflow : 

    ```shell
    tctl workflow signal --workflow_id "onboarding" --name "ContractSignedEvent" --run_id `tctl workflow list --workflow_id 'onboarding' --open | sed -En '2p' | cut -f 3 -d '|'`
    ```

    * Send the signal `IntegrationTrainingEvent` to the currently running `onboarding-child-integrationtraining` child workflow : 

    ```shell
    tctl workflow signal --workflow_id "onboarding-child-integrationtraining" --name "IntegrationTrainingEvent" --run_id `tctl workflow list --workflow_id 'onboarding-child-integrationtraining' --open | sed -En '2p' | cut -f 3 -d '|'`
    ```

    * Send the signal `FirstMonthReviewEvent` to the currently running `onboarding-child-firstmonthreview` child workflow : 

    ```shell
    tctl workflow signal --workflow_id "onboarding-child-firstmonthreview" --name "FirstMonthReviewEvent" --run_id `tctl workflow list --workflow_id 'onboarding-child-firstmonthreview' --open | sed -En '2p' | cut -f 3 -d '|'`
    ```

    * Send the signal `ValidateTrialPeriodEvent` to the currently running `onboarding` workflow : 

    ```shell
    tctl workflow signal --workflow_id "onboarding" --name "ValidateTrialPeriodEvent" --run_id `tctl workflow list --workflow_id 'onboarding' --open | sed -En '2p' | cut -f 3 -d '|'`
    ```

    * You should be able to see the `onboarding` workflow ended in the Web UI or the data results in the Shell that first starts the Workflow instance.