/*
 *  Copyright (c) 2020 Temporal Technologies, Inc. All Rights Reserved
 *
 *  Copyright 2012-2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 *  Modifications copyright (C) 2017 Uber Technologies, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"). You may not
 *  use this file except in compliance with the License. A copy of the License is
 *  located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 *  or in the "license" file accompanying this file. This file is distributed on
 *  an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 *  express or implied. See the License for the specific language governing
 *  permissions and limitations under the License.
 */

package com.zenika.temporalio.serverlessworkflow;

import static com.zenika.temporalio.serverlessworkflow.utils.DslWorkflowUtils.getFileAsString;

import io.serverlessworkflow.api.Workflow;
import java.util.HashMap;
import java.util.Map;

/** Class that loads up all the DSL workflows and allows access via id-version */
public class DslWorkflowCache {

  private static class WorkflowHolder {
    static final Map<String, Workflow> dslWorkflowMap = new HashMap<>();

    static {
      try {
        Workflow zenikaOnBoardingWorkflow =
            Workflow.fromSource(getFileAsString("serverlessworkflow-zenika-onboard.json"));

        dslWorkflowMap.put(
            zenikaOnBoardingWorkflow.getId() + "-" + zenikaOnBoardingWorkflow.getVersion(),
            zenikaOnBoardingWorkflow);

      } catch (Exception e) {
        System.out.println("Exception: " + e.getMessage());
      }
    }
  }

  public static Workflow getWorkflow(String workflowId, String workflowVersion) {
    return WorkflowHolder.dslWorkflowMap.get(workflowId + "-" + workflowVersion);
  }
}