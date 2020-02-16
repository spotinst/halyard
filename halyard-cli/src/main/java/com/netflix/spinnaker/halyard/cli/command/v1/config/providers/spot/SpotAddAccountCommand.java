/*
 * Copyright 2016 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.halyard.cli.command.v1.config.providers.spot;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.netflix.spinnaker.halyard.cli.command.v1.config.providers.account.AbstractAddAccountCommand;
import com.netflix.spinnaker.halyard.cli.command.v1.config.providers.aws.AwsCommandProperties;
import com.netflix.spinnaker.halyard.config.model.v1.node.Account;
import com.netflix.spinnaker.halyard.config.model.v1.providers.spot.SpotAccount;

@Parameters(separators = "=")
public class SpotAddAccountCommand extends AbstractAddAccountCommand {
  protected String getProviderName() {
    return "spot";
  }

  @Parameter(
          names = "--account-id",
          description = AwsCommandProperties.ACCOUNT_ID_DESCRIPTION,
          required = true)
  private String accountId;

  @Override
  protected Account buildAccount(String accountName) {
    SpotAccount account = (SpotAccount) new SpotAccount().setName(accountName);

    account.setAccountId(accountId);

    return account;
  }

  @Override
  protected Account emptyAccount() {
    return new SpotAccount();
  }
}
