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
import io.swagger.codegen.CodegenProperty;
import io.swagger.codegen.CodegenType;
import io.swagger.codegen.DefaultCodegen;
import io.swagger.codegen.SupportingFile;

import io.swagger.models.Model;
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

    protected String sourceFolder = "";

    protected String versionNumber = "0.0.1-SNAPSHOT";

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

        languageSpecificPrimitives = new HashSet<String>(Arrays.asList("String", "Boolean", "Integer",
                    "Float"
                    // TODO: "byte[]"
                ));
        defaultIncludes = new HashSet<String>(Arrays.asList("String", "Boolean", "Integer", "Float", "List", "Map"));

        instantiationTypes = new HashMap<String, String>();
        instantiationTypes.put("array", "List");
        instantiationTypes.put("map", "Map");

        cliOptions.add(new CliOption(CodegenConstants.MODULE_VERSION, CodegenConstants.MODULE_VERSION_DESC));
        cliOptions.add(new CliOption(CodegenConstants.INVOKER_PACKAGE, CodegenConstants.INVOKER_PACKAGE_DESC));
        cliOptions.add(new CliOption(CodegenConstants.MODEL_PACKAGE, CodegenConstants.MODEL_PACKAGE_DESC));

        cliOptions.add(new CliOption(CodegenConstants.LOCAL_VARIABLE_PREFIX,
                CodegenConstants.LOCAL_VARIABLE_PREFIX_DESC));

        typeMapping = new HashMap<String, String>();
        typeMapping.put("array", "List");
        typeMapping.put("map", "Map");
        typeMapping.put("List", "List");
        typeMapping.put("boolean", "Boolean");
        typeMapping.put("string", "String");
        typeMapping.put("int", "Integer");
        typeMapping.put("float", "Float");
        typeMapping.put("number", "Decimal");
        typeMapping.put("DateTime", "Date");
        typeMapping.put("long", "Integer");
        typeMapping.put("short", "Integer");
        typeMapping.put("char", "String");
        typeMapping.put("double", "Float");
        typeMapping.put("object", "Object");         // TODO - is this the right thing to use?
        typeMapping.put("integer", "Integer");
        typeMapping.put("ByteArray", "Array<Byte>"); // TODO

        importMapping = new HashMap<String, String>();
        importMapping.put("Decimal", "ceylon.math.decimal.Decimal");

        // TODO?
        importMapping.put("UUID", "java.util.UUID");
        importMapping.put("File", "java.io.File");
        importMapping.put("Date", "java.util.Date");
        importMapping.put("Timestamp", "java.sql.Timestamp");

        // TODO: ceylon.time?
        importMapping.put("DateTime", "org.joda.time.*");
        importMapping.put("LocalDateTime", "org.joda.time.*");
        importMapping.put("LocalDate", "org.joda.time.*");
        importMapping.put("LocalTime", "org.joda.time.*");

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
        return "Generates ceylon data model classes";
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

        if (additionalProperties.containsKey(CodegenConstants.MODEL_PACKAGE)) {
            this.setInvokerPackage((String) additionalProperties.get(CodegenConstants.MODEL_PACKAGE));
        } else {

            // not set, use default to be passed to template
            modelPackage = invokerPackage + ".model";
            additionalProperties.put(CodegenConstants.MODEL_PACKAGE, modelPackage);
        }

        if (additionalProperties.containsKey(CodegenConstants.SOURCE_FOLDER)) {
            this.setSourceFolder((String) additionalProperties.get(CodegenConstants.SOURCE_FOLDER));
        }

        this.sanitizeConfig();

        final String helperPackage = invokerPackage + ".helper";

        additionalProperties.put("helperPackage", helperPackage);

        final String invokerFolder = sourceFolder + '/' + invokerPackage.replace(".", "/");
        final String modelFolder = sourceFolder + '/' + modelPackage.replace(".", "/");
        final String helperFolder = sourceFolder + '/' + helperPackage.replace('.', '/');

        supportingFiles.add(new SupportingFile("module.mustache", invokerFolder, "module.ceylon"));
        supportingFiles.add(new SupportingFile("invokerPackage.mustache", invokerFolder, "package.ceylon"));
        supportingFiles.add(new SupportingFile("modelPackage.mustache", modelFolder, "package.ceylon"));
        supportingFiles.add(new SupportingFile("jsonHelper.mustache", helperFolder, "jsonHelper.ceylon"));
        supportingFiles.add(new SupportingFile("jsonParseHelper.mustache", helperFolder, "jsonParseHelper.ceylon"));
        supportingFiles.add(new SupportingFile("helperPackage.mustache", helperFolder, "package.ceylon"));

        additionalProperties.put("versionNumber", versionNumber);
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

        // if it's all uppper case, convert to lower case (Ceylon needs lower case identifiers for values)
        if (name.matches("^[A-Z_]*$")) {
            name = name.toLowerCase();
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
        final String typeDeclaration;
        if (p instanceof ArrayProperty) {
            final ArrayProperty ap = (ArrayProperty) p;
            final Property inner = ap.getItems();
            inner.setRequired(true);
            typeDeclaration = getSwaggerType(p) + "<" + getTypeDeclaration(inner) + ">";
        } else if (p instanceof MapProperty) {
            final MapProperty mp = (MapProperty) p;
            final Property inner = mp.getAdditionalProperties();
            inner.setRequired(true);

            typeDeclaration = getSwaggerType(p) + "<String, " + getTypeDeclaration(inner) + ">";
        } else {
            typeDeclaration = super.getTypeDeclaration(p);
        }

        if (p.getRequired()) {
            return typeDeclaration;
        } else {
            return typeDeclaration + "?";
        }
    }

    @Override
    public String toDefaultValue(final Property p) {

        // use `null` instead of `"null"` when there is no default value.
        if (p instanceof IntegerProperty) {
            final IntegerProperty dp = (IntegerProperty) p;
            if (dp.getDefault() != null) {
                return dp.getDefault().toString();
            }
        } else if (p instanceof LongProperty) {
            final LongProperty dp = (LongProperty) p;
            if (dp.getDefault() != null) {
                return dp.getDefault().toString();
            }
        } else if (p instanceof DoubleProperty) {
            final DoubleProperty dp = (DoubleProperty) p;
            if (dp.getDefault() != null) {
                return dp.getDefault().toString();
            }
        } else if (p instanceof FloatProperty) {
            final FloatProperty dp = (FloatProperty) p;
            if (dp.getDefault() != null) {
                return dp.getDefault().toString();
            }
        } else if (p instanceof BooleanProperty) {
            final BooleanProperty bp = (BooleanProperty) p;
            if (bp.getDefault() != null) {
                return bp.getDefault().toString();
            }
        } else if (p instanceof StringProperty) {
            final StringProperty sp = (StringProperty) p;
            if (sp.getDefault() != null) {
                final String defaultValue = sp.getDefault();
                if (sp.getEnum() == null) {
                    return "\"" + escapeText(defaultValue) + "\"";
                } else {

                    // convert to enum var name later in postProcessModels
                    return defaultValue;
                }
            }
        }

        return null;
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
        @SuppressWarnings("unchecked")
        final List<Object> models = (List<Object>) objs.get("models");
        for (final Object _mo : models) {
            @SuppressWarnings("unchecked")
            final Map<String, Object> mo = (Map<String, Object>) _mo;
            final CodegenModel cm = (CodegenModel) mo.get("model");
            for (final CodegenProperty var : cm.vars) {

                // TODO: implement proper enum handling. The stuff here is copied from Java and likely won't work.
                Map<String, Object> allowableValues = var.allowableValues;

                // handle ArrayProperty
                if (var.items != null) {
                    allowableValues = var.items.allowableValues;
                }

                if (allowableValues == null) {
                    continue;
                }

                @SuppressWarnings("unchecked")
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

    private String sanitizePackageName(String packageName) {
        packageName = packageName.trim();
        packageName = packageName.replaceAll("[^a-zA-Z0-9_\\.]", "_");
        if (Strings.isNullOrEmpty(packageName)) {
            return "invalidPackageName";
        }

        return packageName;
    }
}
