package com.isp.authorizationserver.shared.constants.endpoints;

import lombok.Getter;

@Getter
public enum EndpointPaths {
    AUTH(build("/oauth2/token")),

    CLIENT(build("/client")),

    ROLE(build("/role").setGetPath(CompositeEndpointPaths.ROLE_GET_PATH)),

    USER(build("/user").setValidatePath(CompositeEndpointPaths.USER_VALIDATE_PATH));

    private final String basePath;
    private final String createPath;
    private final String validatePath;
    private final String getPath;
    private final String getAllPath;

    EndpointPaths(Builder builder) {
        this.basePath = builder.basePath;
        this.createPath = builder.createPath;
        this.validatePath = builder.validatePath;
        this.getPath = builder.getPath;
        this.getAllPath = getBasePath() + builder.getAllPath;
    }

    private static Builder build(String basePath) {
        return new Builder(basePath);
    }

    private static class Builder {
        private final String basePath;
        private final String createPath;
        private final String getAllPath;
        private String validatePath;
        private String getPath = "";

        public Builder(String basePath) {
            this.basePath = basePath;
            this.createPath = basePath;
            this.getAllPath = basePath;
        }

        public Builder setValidatePath(String path) {
            this.validatePath = this.basePath + path;
            return this;
        }

        public Builder setGetPath(String path) {
            this.getPath = this.basePath + path;
            return this;
        }
    }
}
