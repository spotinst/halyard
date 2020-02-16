/*
 * Copyright 2017 Google, Inc.
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
 *
 */

package com.netflix.spinnaker.halyard.cli.command.v1.config.providers.spot;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.netflix.spinnaker.halyard.cli.command.v1.config.providers.AbstractEditProviderCommand;
import com.netflix.spinnaker.halyard.config.model.v1.node.Provider;
import com.netflix.spinnaker.halyard.config.model.v1.providers.spot.SpotAccount;
import com.netflix.spinnaker.halyard.config.model.v1.providers.spot.SpotProvider;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Parameters(separators = "=")
@Data
public class SpotEditProviderCommand extends AbstractEditProviderCommand<SpotAccount, SpotProvider> {
    String shortDescription = "Set provider-wide properties for the Spot provider";

    String longDescription = "The Spot provider requires an existing Organization, and a valid api token which " +
                             "will be used to authenticate on behalf of all the Spot accounts under that Organization. " +
                             "See https://api.spotinst.com/spotinst-api/administration/create-an-api-token/ for more information on how to generate an api token.";

    @Parameter(names = "--api-token", description = SpotCommandProperties.API_TOKEN_DESCRIPTION, password = true)
    private String apiToken;

    protected String getProviderName() {
        return Provider.ProviderType.SPOT.getName();
    }

    @Override
    protected Provider editProvider(SpotProvider provider) {
        provider.setApiToken(isSet(apiToken) ? apiToken : provider.getApiToken());
        return provider;
    }
}
