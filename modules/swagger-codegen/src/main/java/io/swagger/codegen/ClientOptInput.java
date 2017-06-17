package io.swagger.codegen;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.codegen.auth.AuthParser;
import io.swagger.models.Swagger;
import io.swagger.models.auth.AuthorizationValue;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ClientOptInput {
    private CodegenConfig config;
    private ClientOpts opts;
    private Swagger swagger;
    private List<AuthorizationValue> auths;
    private Map<String,String> systemProperties;

    public ClientOptInput swagger(Swagger swagger) {
        this.setSwagger(swagger);
        return this;
    }

    public ClientOptInput opts(ClientOpts opts) {
        this.setOpts(opts);
        return this;
    }

    public ClientOptInput config(CodegenConfig codegenConfig) {
        this.setConfig(codegenConfig);
        return this;
    }

    public ClientOptInput systemProperties(Map<String,String> properties) {
        this.setSystemProperties(properties);
        return this;
    }

    @Deprecated
    public ClientOptInput auth(String urlEncodedAuthString) {
        this.setAuth(urlEncodedAuthString);
        return this;
    }

    @Deprecated
    public String getAuth() {
        return AuthParser.reconstruct(auths);
    }

    @Deprecated
    public void setAuth(String urlEncodedAuthString) {
        this.auths = AuthParser.parse(urlEncodedAuthString);
    }

    @Deprecated
    public List<AuthorizationValue> getAuthorizationValues() {
        return auths;
    }

    public CodegenConfig getConfig() {
        return config;
    }

    public void setConfig(CodegenConfig config) {
        this.config = config;
    }

    public ClientOpts getOpts() {
        return opts;
    }

    public void setOpts(ClientOpts opts) {
        this.opts = opts;
    }

    public Map<String, String> getSystemProperties() {
        if(systemProperties == null) {
            return Collections.emptyMap();
        } else {
            return systemProperties;
        }
    }

    public void setSystemProperties(Map<String, String> systemProperties) {
        this.systemProperties = systemProperties;
    }

    @ApiModelProperty(dataType = "Object")
    public Swagger getSwagger() {
        return swagger;
    }

    public void setSwagger(Swagger swagger) {
        this.swagger = swagger;
    }
}