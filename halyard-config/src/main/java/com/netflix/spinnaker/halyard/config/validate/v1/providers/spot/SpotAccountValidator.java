/*
 * Copyright 2017 Schibsted ASA.
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
 *
 */

package com.netflix.spinnaker.halyard.config.validate.v1.providers.spot;

import com.netflix.spinnaker.halyard.config.model.v1.node.Validator;
import com.netflix.spinnaker.halyard.config.model.v1.providers.spot.SpotAccount;
import com.netflix.spinnaker.halyard.config.problem.v1.ConfigProblemSetBuilder;
import com.netflix.spinnaker.halyard.config.services.v1.ProviderService;
import com.netflix.spinnaker.halyard.core.problem.v1.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class SpotAccountValidator extends Validator<SpotAccount> {

    @Autowired
    ProviderService providerService;

    private static final Pattern SPOT_ACCOUNT_PATTERN = Pattern.compile("^act-[a-zA-Z0-9]{8}$");

    @Override
    public void validate(ConfigProblemSetBuilder p, SpotAccount spotAccount) {
        String  accountId                = spotAccount.getAccountId();
        Boolean isAccountIdPattternValid = checkIfAccountIdPatternIsValid(accountId);

        if (isAccountIdPattternValid == false) {
            p.addProblem(Problem.Severity.FATAL, "Invalid Account ID: " + accountId);
        }
    }

    private Boolean checkIfAccountIdPatternIsValid(String accountId) {
        return SPOT_ACCOUNT_PATTERN.matcher(accountId).matches();
    }
}
