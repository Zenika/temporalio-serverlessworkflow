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

    * Send the signal, replacing the `XXXX` value of `run_id` parameters with the selected above : 

    ```shell
    tctl workflow signal --workflow_id "onboarding" --name "HiringFormFilledEvent" --run_id "XXXX"
    ```

    * See the workflow progression in the Web UI or with the CLI

    ```shell
    tctl workflow show --workflow_id "onboarding" --run_id "XXXX"
    ```
