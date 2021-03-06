/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package pw.cdmi.core.http.auth;

/**
 * Default implementation of {@link Credentials}.
 */
public class DefaultCredentials implements Credentials {

    private final String accessKeyId;
    private final String secretAccessKey;
    private SecurityToken securityToken;

    public DefaultCredentials(String accessKeyId, String secretAccessKey) {
        this(accessKeyId, secretAccessKey, null);
    }
    
    public DefaultCredentials(String accessKeyId, String secretAccessKey, SecurityToken securityToken) {
        if (accessKeyId == null || accessKeyId.equals("")) {
            throw new InvalidCredentialsException("Access key id should not be null or empty.");
        }
        if (secretAccessKey == null || accessKeyId.equals("")) {
            throw new InvalidCredentialsException("Secret access key should not be null or empty.");
        }

        this.accessKeyId = accessKeyId;
        this.secretAccessKey = secretAccessKey;
        this.securityToken = securityToken;
    }

    @Override
    public String getAccessKeyId() {
        return accessKeyId;
    }

    @Override
    public String getSecretAccessKey() {
        return secretAccessKey;
    }

    @Override
    public SecurityToken getSecurityToken() {
        return securityToken;
    }

    @Override
    public void setSecurityToken(SecurityToken token) {
        this.securityToken = token;
    }
    
    @Override
    public boolean useSecurityToken() {
        return this.securityToken != null;
    }
}
