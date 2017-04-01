package io.swagger.codegen;

import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.Test;

import io.swagger.models.ComposedModel;
import io.swagger.models.ModelImpl;
import io.swagger.models.parameters.BodyParameter;
import io.swagger.models.properties.StringProperty;

public class DefaultCodegenTest {

    @Test
    public void testFromParameter() {
        DefaultCodegen codegen = new DefaultCodegen();
        Set<String> imports = new HashSet<>();
        BodyParameter param = new BodyParameter().name("body")
                .schema(new ComposedModel().parent(new ModelImpl().property("name", new StringProperty())));

        CodegenParameter result = codegen.fromParameter(param, imports);
        // TODO: asserts ... but currently it throws a NullPointerException here.
    }
}
