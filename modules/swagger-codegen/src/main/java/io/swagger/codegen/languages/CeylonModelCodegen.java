package io.swagger.codegen.languages;

import java.io.File;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;

import io.swagger.codegen.CliOption;
import io.swagger.codegen.CodegenConfig;
import io.swagger.codegen.CodegenConstants;
import io.swagger.codegen.CodegenModel;
import io.swagger.codegen.CodegenOperation;
import io.swagger.codegen.CodegenProperty;
import io.swagger.codegen.CodegenType;
import io.swagger.codegen.DefaultCodegen;

import io.swagger.models.Model;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import io.swagger.models.parameters.FormParameter;
import io.swagger.models.parameters.Parameter;
import io.swagger.models.properties.ArrayProperty;
import io.swagger.models.properties.BooleanProperty;
import io.swagger.models.properties.DoubleProperty;
import io.swagger.models.properties.FloatProperty;
import io.swagger.models.properties.IntegerProperty;
import io.swagger.models.properties.LongProperty;
import io.swagger.models.properties.MapProperty;
import io.swagger.models.properties.Property;
import io.swagger.models.properties.StringProperty;

public class CeylonModelCodegen extends DefaultCodegen implements CodegenConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(CeylonModelCodegen.class);
    public static final String FULL_JAVA_UTIL = "fullJavaUtil";
    public static final String DEFAULT_LIBRARY = "<default>";

    protected String invokerPackage = "io.swagger.client";

// protected String groupId = "io.swagger";
// protected String artifactId = "swagger-java-client";
// protected String artifactVersion = "1.0.0";
    protected String projectFolder = "src" + File.separator + "main";
    protected String sourceFolder = projectFolder + File.separator + "java";
    protected String localVariablePrefix = "";
    protected boolean fullJavaUtil = false;
    protected String javaUtilPrefix = "";
    protected Boolean serializableModel = false;

    public CeylonModelCodegen() {
        super();
        outputFolder = "generated-code" + File.separator + "ceylon";
        modelTemplateFiles.put("model.mustache", ".ceylon");

        // apiTemplateFiles.put("api.mustache", ".ceylon");
        embeddedTemplateDir = templateDir = "ceylon";
        apiPackage = "io.swagger.client.api";
        modelPackage = "io.swagger.client.model";

        reservedWords = new HashSet<String>(Arrays.asList("assembly", "module", "package", "import", "alias", "class",
                    "interface", "object", "given", "value", "assign", "void", "function", "new", "of", "extends",
                    "satisfies", "abstracts", "in", "out", "return", "break", "continue", "throw", "assert", "dynamic",
                    "if", "else", "switch", "case", "for", "while", "try", "catch", "finally", "then", "let", "this",
                    "outer", "super", "is", "exists", "nonempty",

                    // actually used language annotations.
                    "shared", "actual",

                    // annotations for Swagger/Jackson/...
                    "apiModelProperty", "apiModel", "jsonProperty"

                    // language annotations not used by us – these can be hidden by fields without problems.
                    // "abstract", "formal", "default", "variable", "late", "native", "deprecated",
                    // "final", "sealed", "annotation", "suppressWarnings", "small", "doc", "by", "license", "see",
                    // "throws", "tagged"
                ));

        languageSpecificPrimitives = new HashSet<String>(Arrays.asList("String", "Boolean", "Integer", "Float",
                    "Object"
                    // TODO: "byte[]"
                ));
        instantiationTypes.put("array", "List");
        instantiationTypes.put("map", "Map");

        cliOptions.add(new CliOption(CodegenConstants.MODEL_PACKAGE, CodegenConstants.MODEL_PACKAGE_DESC));

        // cliOptions.add(new CliOption(CodegenConstants.API_PACKAGE, CodegenConstants.API_PACKAGE_DESC));
        cliOptions.add(new CliOption(CodegenConstants.INVOKER_PACKAGE, CodegenConstants.INVOKER_PACKAGE_DESC));

        // cliOptions.add(new CliOption(CodegenConstants.GROUP_ID, CodegenConstants.GROUP_ID_DESC));
        // cliOptions.add(new CliOption(CodegenConstants.ARTIFACT_ID, CodegenConstants.ARTIFACT_ID_DESC));
        // cliOptions.add(new CliOption(CodegenConstants.ARTIFACT_VERSION, CodegenConstants.ARTIFACT_VERSION_DESC));
        // cliOptions.add(new CliOption(CodegenConstants.SOURCE_FOLDER, CodegenConstants.SOURCE_FOLDER_DESC));
        cliOptions.add(new CliOption(CodegenConstants.LOCAL_VARIABLE_PREFIX,
                CodegenConstants.LOCAL_VARIABLE_PREFIX_DESC));
        // cliOptions.add(new CliOption(CodegenConstants.SERIALIZABLE_MODEL, CodegenConstants.SERIALIZABLE_MODEL_DESC));
        // cliOptions.add(new CliOption(FULL_JAVA_UTIL,
        // "whether to use fully qualified name for classes under java.util").defaultValue("false"));

// supportedLibraries.put(DEFAULT_LIBRARY, "HTTP client: Jersey client 1.18. JSON processing: Jackson 2.4.2");
// supportedLibraries.put("feign", "HTTP client: Netflix Feign 8.1.1");
// supportedLibraries.put("jersey2", "HTTP client: Jersey client 2.6");
// supportedLibraries.put("okhttp-gson", "HTTP client: OkHttp 2.4.0. JSON processing: Gson 2.3.1");
// supportedLibraries.put("retrofit", "HTTP client: OkHttp 2.4.0. JSON processing: Gson 2.3.1 (Retrofit 1.9.0)");
// supportedLibraries.put("retrofit2",
// "HTTP client: OkHttp 2.5.0. JSON processing: Gson 2.4 (Retrofit 2.0.0-beta2)");

        final CliOption library = new CliOption(CodegenConstants.LIBRARY, "library template (sub-template) to use");
        library.setDefault(DEFAULT_LIBRARY);
        library.setEnum(supportedLibraries);
        library.setDefault(DEFAULT_LIBRARY);
        cliOptions.add(library);
    }

    @Override
    public CodegenType getTag() {
        return CodegenType.OTHER;
    }

    @Override
    public String getName() {
        return "ceylon";
    }

    @Override
    public String getHelp() {
        return "Generates ceylon classes";
    }

    @Override
    public void processOpts() {
        super.processOpts();

        if (additionalProperties.containsKey(CodegenConstants.INVOKER_PACKAGE)) {
            this.setInvokerPackage((String) additionalProperties.get(CodegenConstants.INVOKER_PACKAGE));
        } else {

            // not set, use default to be passed to template
            additionalProperties.put(CodegenConstants.INVOKER_PACKAGE, invokerPackage);
        }

        if (additionalProperties.containsKey(CodegenConstants.SOURCE_FOLDER)) {
            this.setSourceFolder((String) additionalProperties.get(CodegenConstants.SOURCE_FOLDER));
        }

        if (additionalProperties.containsKey(CodegenConstants.LOCAL_VARIABLE_PREFIX)) {
            this.setLocalVariablePrefix((String) additionalProperties.get(CodegenConstants.LOCAL_VARIABLE_PREFIX));
        }

        if (additionalProperties.containsKey(CodegenConstants.SERIALIZABLE_MODEL)) {
            this.setSerializableModel(Boolean.valueOf(
                    additionalProperties.get(CodegenConstants.SERIALIZABLE_MODEL).toString()));
        }

        if (additionalProperties.containsKey(CodegenConstants.LIBRARY)) {
            this.setLibrary((String) additionalProperties.get(CodegenConstants.LIBRARY));
        }

        // need to put back serializableModel (boolean) into additionalProperties as value in additionalProperties is
        // string
        additionalProperties.put(CodegenConstants.SERIALIZABLE_MODEL, serializableModel);

        this.sanitizeConfig();

        // supportingFiles.add(new SupportingFile("pom.mustache", "", "pom.xml"));
// TODO        supportingFiles.add(new SupportingFile("README.mustache", "", "README.md"));
// supportingFiles.add(new SupportingFile("build.gradle.mustache", "", "build.gradle"));
// supportingFiles.add(new SupportingFile("settings.gradle.mustache", "", "settings.gradle"));
// supportingFiles.add(new SupportingFile("gradle.properties.mustache", "", "gradle.properties"));
// supportingFiles.add(new SupportingFile("manifest.mustache", projectFolder, "AndroidManifest.xml"));
        // supportingFiles.add(new SupportingFile("ApiClient.mustache", invokerFolder, "ApiClient.java"));
        // supportingFiles.add(new SupportingFile("StringUtil.mustache", invokerFolder, "StringUtil.java"));

    }

    private void sanitizeConfig() {
        // Sanitize any config options here. We also have to update the additionalProperties because
        // the whole additionalProperties object is injected into the main object passed to the mustache layer

        this.setApiPackage(sanitizePackageName(apiPackage));
        if (additionalProperties.containsKey(CodegenConstants.API_PACKAGE)) {
            this.additionalProperties.put(CodegenConstants.API_PACKAGE, apiPackage);
        }

        this.setModelPackage(sanitizePackageName(modelPackage));
        if (additionalProperties.containsKey(CodegenConstants.MODEL_PACKAGE)) {
            this.additionalProperties.put(CodegenConstants.MODEL_PACKAGE, modelPackage);
        }

        this.setInvokerPackage(sanitizePackageName(invokerPackage));
        if (additionalProperties.containsKey(CodegenConstants.INVOKER_PACKAGE)) {
            this.additionalProperties.put(CodegenConstants.INVOKER_PACKAGE, invokerPackage);
        }
    }

    @Override
    public String escapeReservedWord(final String name) {
        return "_" + name;
    }

    @Override
    public String apiFileFolder() {
        return outputFolder + "/" + sourceFolder + "/" + apiPackage().replace('.', '/');
    }

    @Override
    public String modelFileFolder() {
        return outputFolder + "/" + sourceFolder + "/" + modelPackage().replace('.', '/');
    }

    @Override
    public String toVarName(String name) {

        // sanitize name
        name = sanitizeName(name);

        if ("_".equals(name)) {
            name = "_u";
        }

        // if it's all uppper case, do nothing
        if (name.matches("^[A-Z_]*$")) {
            return name;
        }

        // camelize (lower first character) the variable name
        // pet_id => petId
        name = camelize(name, true);

        // for reserved word or word starting with number, append _
        if (reservedWords.contains(name) || name.matches("^\\d.*")) {
            name = escapeReservedWord(name);
        }

        return name;
    }

    @Override
    public String toParamName(final String name) {

        // should be the same as variable name
        return toVarName(name);
    }

    @Override
    public String toModelName(String name) {
        name = sanitizeName(name);

        // model name cannot use reserved keyword, e.g. return
        if (reservedWords.contains(name)) {
            throw new RuntimeException(name + " (reserved word) cannot be used as a model name");
        }

        // camelize the model name
        // phone_number => PhoneNumber
        return camelize(name);
    }

    @Override
    public String toModelFilename(final String name) {

        // should be the same as the model name
        return toModelName(name);
    }

    @Override
    public String getTypeDeclaration(final Property p) {
        if (p instanceof ArrayProperty) {
            final ArrayProperty ap = (ArrayProperty) p;
            final Property inner = ap.getItems();
            return getSwaggerType(p) + "<" + getTypeDeclaration(inner) + ">";
        } else if (p instanceof MapProperty) {
            final MapProperty mp = (MapProperty) p;
            final Property inner = mp.getAdditionalProperties();

            return getSwaggerType(p) + "<String, " + getTypeDeclaration(inner) + ">";
        }

        return super.getTypeDeclaration(p);
    }

    @Override
    public String toDefaultValue(final Property p) {
        if (p instanceof ArrayProperty) {
            final ArrayProperty ap = (ArrayProperty) p;
            final String pattern;
            if (fullJavaUtil) {
                pattern = "new java.util.ArrayList<%s>()";
            } else {
                pattern = "new ArrayList<%s>()";
            }

            return String.format(pattern, getTypeDeclaration(ap.getItems()));
        } else if (p instanceof MapProperty) {
            final MapProperty ap = (MapProperty) p;
            final String pattern;
            if (fullJavaUtil) {
                pattern = "new java.util.HashMap<String, %s>()";
            } else {
                pattern = "new HashMap<String, %s>()";
            }

            return String.format(pattern, getTypeDeclaration(ap.getAdditionalProperties()));
        } else if (p instanceof IntegerProperty) {
            final IntegerProperty dp = (IntegerProperty) p;
            if (dp.getDefault() != null) {
                return dp.getDefault().toString();
            }

            return "null";
        } else if (p instanceof LongProperty) {
            final LongProperty dp = (LongProperty) p;
            if (dp.getDefault() != null) {
                return dp.getDefault().toString() + "l";
            }

            return "null";
        } else if (p instanceof DoubleProperty) {
            final DoubleProperty dp = (DoubleProperty) p;
            if (dp.getDefault() != null) {
                return dp.getDefault().toString() + "d";
            }

            return "null";
        } else if (p instanceof FloatProperty) {
            final FloatProperty dp = (FloatProperty) p;
            if (dp.getDefault() != null) {
                return dp.getDefault().toString() + "f";
            }

            return "null";
        } else if (p instanceof BooleanProperty) {
            final BooleanProperty bp = (BooleanProperty) p;
            if (bp.getDefault() != null) {
                return bp.getDefault().toString();
            }

            return "null";
        } else if (p instanceof StringProperty) {
            final StringProperty sp = (StringProperty) p;
            if (sp.getDefault() != null) {
                final String _default = sp.getDefault();
                if (sp.getEnum() == null) {
                    return "\"" + escapeText(_default) + "\"";
                } else {

                    // convert to enum var name later in postProcessModels
                    return _default;
                }
            }

            return "null";
        }

        return super.toDefaultValue(p);
    }

    @Override
    public String getSwaggerType(final Property p) {
        final String swaggerType = super.getSwaggerType(p);
        String type = null;
        if (typeMapping.containsKey(swaggerType)) {
            type = typeMapping.get(swaggerType);
            if (languageSpecificPrimitives.contains(type) || type.indexOf(".") >= 0) {
                return type;
            }
        } else {
            type = swaggerType;
        }

        if (null == type) {
            LOGGER.error("No Type defined for Property " + p);
        }

        return toModelName(type);
    }

    @Override
    public String toOperationId(final String operationId) {

        // throw exception if method name is empty
        if (StringUtils.isEmpty(operationId)) {
            throw new RuntimeException("Empty method/operation name (operationId) not allowed");
        }

        // method name cannot use reserved keyword, e.g. return
        if (reservedWords.contains(operationId)) {
            throw new RuntimeException(operationId + " (reserved word) cannot be used as method name");
        }

        return camelize(sanitizeName(operationId), true);
    }

    @Override
    public CodegenModel fromModel(final String name, final Model model, final Map<String, Model> allDefinitions) {
        CodegenModel codegenModel = super.fromModel(name, model, allDefinitions);

        if (allDefinitions != null && codegenModel != null && codegenModel.parent != null && codegenModel.hasEnums) {
            final Model parentModel = allDefinitions.get(toModelName(codegenModel.parent));
            final CodegenModel parentCodegenModel = super.fromModel(codegenModel.parent, parentModel);
            codegenModel = this.reconcileInlineEnums(codegenModel, parentCodegenModel);
        }

        return codegenModel;
    }

    @Override
    public Map<String, Object> postProcessModels(final Map<String, Object> objs) {
        final List<Object> models = (List<Object>) objs.get("models");
        for (final Object _mo : models) {
            final Map<String, Object> mo = (Map<String, Object>) _mo;
            final CodegenModel cm = (CodegenModel) mo.get("model");
            for (final CodegenProperty var : cm.vars) {
                Map<String, Object> allowableValues = var.allowableValues;

                // handle ArrayProperty
                if (var.items != null) {
                    allowableValues = var.items.allowableValues;
                }

                if (allowableValues == null) {
                    continue;
                }

                final List<String> values = (List<String>) allowableValues.get("values");
                if (values == null) {
                    continue;
                }

                // put "enumVars" map into `allowableValues", including `name` and `value`
                final List<Map<String, String>> enumVars = new ArrayList<Map<String, String>>();
                final String commonPrefix = findCommonPrefixOfVars(values);
                final int truncateIdx = commonPrefix.length();
                for (final String value : values) {
                    final Map<String, String> enumVar = new HashMap<String, String>();
                    String enumName;
                    if (truncateIdx == 0) {
                        enumName = value;
                    } else {
                        enumName = value.substring(truncateIdx);
                        if ("".equals(enumName)) {
                            enumName = value;
                        }
                    }

                    enumVar.put("name", toEnumVarName(enumName));
                    enumVar.put("value", value);
                    enumVars.add(enumVar);
                }

                allowableValues.put("enumVars", enumVars);

                // handle default value for enum, e.g. available => StatusEnum.AVAILABLE
                if (var.defaultValue != null) {
                    String enumName = null;
                    for (final Map<String, String> enumVar : enumVars) {
                        if (var.defaultValue.equals(enumVar.get("value"))) {
                            enumName = enumVar.get("name");
                            break;
                        }
                    }

                    if (enumName != null) {
                        var.defaultValue = var.datatypeWithEnum + "." + enumName;
                    }
                }
            }
        }

        return objs;
    }

    @Override
    public Map<String, Object> postProcessOperations(final Map<String, Object> objs) {
        if ("retrofit".equals(getLibrary()) || "retrofit2".equals(getLibrary())) {
            final Map<String, Object> operations = (Map<String, Object>) objs.get("operations");
            if (operations != null) {
                final List<CodegenOperation> ops = (List<CodegenOperation>) operations.get("operation");
                for (final CodegenOperation operation : ops) {
                    if (operation.hasConsumes == Boolean.TRUE) {
                        final Map<String, String> firstType = operation.consumes.get(0);
                        if (firstType != null) {
                            if ("multipart/form-data".equals(firstType.get("mediaType"))) {
                                operation.isMultipart = Boolean.TRUE;
                            }
                        }
                    }

                    if (operation.returnType == null) {
                        operation.returnType = "Void";
                    }

                    if ("retrofit2".equals(getLibrary()) && StringUtils.isNotEmpty(operation.path)
                            && operation.path.startsWith("/")) {
                        operation.path = operation.path.substring(1);
                    }
                }
            }
        }

        return objs;
    }

    @Override
    public void preprocessSwagger(final Swagger swagger) {
        if (swagger != null && swagger.getPaths() != null) {
            for (final String pathname : swagger.getPaths().keySet()) {
                final Path path = swagger.getPath(pathname);
                if (path.getOperations() != null) {
                    for (final Operation operation : path.getOperations()) {
                        boolean hasFormParameters = false;
                        for (final Parameter parameter : operation.getParameters()) {
                            if (parameter instanceof FormParameter) {
                                hasFormParameters = true;
                            }
                        }

                        final String defaultContentType = hasFormParameters ? "application/x-www-form-urlencoded"
                                                                            : "application/json";
                        final String contentType = operation.getConsumes() == null || operation
                                .getConsumes().isEmpty() ? defaultContentType : operation
                                .getConsumes().get(0);
                        final String accepts = getAccept(operation);
                        operation.setVendorExtension("x-contentType", contentType);
                        operation.setVendorExtension("x-accepts", accepts);
                    }
                }
            }
        }
    }

    private String getAccept(final Operation operation) {
        String accepts = null;
        final String defaultContentType = "application/json";
        if (operation.getProduces() != null && !operation.getProduces().isEmpty()) {
            final StringBuilder sb = new StringBuilder();
            for (final String produces : operation.getProduces()) {
                if (defaultContentType.equalsIgnoreCase(produces)) {
                    accepts = defaultContentType;
                    break;
                } else {
                    if (sb.length() > 0) {
                        sb.append(",");
                    }

                    sb.append(produces);
                }
            }

            if (accepts == null) {
                accepts = sb.toString();
            }
        } else {
            accepts = defaultContentType;
        }

        return accepts;
    }

    @Override
    protected boolean needToImport(final String type) {
        return super.needToImport(type) && type.indexOf(".") < 0;
    }

    private String findCommonPrefixOfVars(final List<String> vars) {
        final String prefix = StringUtils.getCommonPrefix(vars.toArray(new String[vars.size()]));

        // exclude trailing characters that should be part of a valid variable
        // e.g. ["status-on", "status-off"] => "status-" (not "status-o")
        return prefix.replaceAll("[a-zA-Z0-9]+\\z", "");
    }

    private String toEnumVarName(final String value) {
        final String var = value.replaceAll("\\W+", "_").toUpperCase();
        if (var.matches("\\d.*")) {
            return "_" + var;
        } else {
            return var;
        }
    }

    private CodegenModel reconcileInlineEnums(final CodegenModel codegenModel, final CodegenModel parentCodegenModel) {
        // This generator uses inline classes to define enums, which breaks when
        // dealing with models that have subTypes. To clean this up, we will analyze
        // the parent and child models, look for enums that match, and remove
        // them from the child models and leave them in the parent.
        // Because the child models extend the parents, the enums will be available via the parent.

        // Only bother with reconciliation if the parent model has enums.
        if (parentCodegenModel.hasEnums) {

            // Get the properties for the parent and child models
            final List<CodegenProperty> parentModelCodegenProperties = parentCodegenModel.vars;
            final List<CodegenProperty> codegenProperties = codegenModel.vars;

            // Iterate over all of the parent model properties
            boolean removedChildEnum = false;
            for (final CodegenProperty parentModelCodegenPropery : parentModelCodegenProperties) {

                // Look for enums
                if (parentModelCodegenPropery.isEnum) {

                    // Now that we have found an enum in the parent class,
                    // and search the child class for the same enum.
                    final Iterator<CodegenProperty> iterator = codegenProperties.iterator();
                    while (iterator.hasNext()) {
                        final CodegenProperty codegenProperty = iterator.next();
                        if (codegenProperty.isEnum && codegenProperty.equals(parentModelCodegenPropery)) {

                            // We found an enum in the child class that is
                            // a duplicate of the one in the parent, so remove it.
                            iterator.remove();
                            removedChildEnum = true;
                        }
                    }
                }
            }

            if (removedChildEnum) {

                // If we removed an entry from this model's vars, we need to ensure hasMore is updated
                int count = 0;
                final int numVars = codegenProperties.size();
                for (final CodegenProperty codegenProperty : codegenProperties) {
                    count += 1;
                    codegenProperty.hasMore = (count < numVars) ? true : null;
                }

                codegenModel.vars = codegenProperties;
            }
        }

        return codegenModel;
    }

    public void setInvokerPackage(final String invokerPackage) {
        this.invokerPackage = invokerPackage;
    }

    public void setSourceFolder(final String sourceFolder) {
        this.sourceFolder = sourceFolder;
    }

    public void setLocalVariablePrefix(final String localVariablePrefix) {
        this.localVariablePrefix = localVariablePrefix;
    }

    public Boolean getSerializableModel() {
        return serializableModel;
    }

    public void setSerializableModel(final Boolean serializableModel) {
        this.serializableModel = serializableModel;
    }

    private String sanitizePackageName(String packageName) {
        packageName = packageName.trim();
        packageName = packageName.replaceAll("[^a-zA-Z0-9_\\.]", "_");
        if (Strings.isNullOrEmpty(packageName)) {
            return "invalidPackageName";
        }

        return packageName;
    }

    public void setFullJavaUtil(final boolean fullJavaUtil) {
        this.fullJavaUtil = fullJavaUtil;
    }
}
