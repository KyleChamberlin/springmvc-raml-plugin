package com.phoenixnap.oss.ramlapisync.generation.rules.basic;

import com.phoenixnap.oss.ramlapisync.data.ApiMappingMetadata;
import com.phoenixnap.oss.ramlapisync.generation.rules.AbstractControllerRuleTestBase;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author armin.weisser
 * @since 0.3.2
 */
public class DelegatingMethodBodyRuleTest extends AbstractControllerRuleTestBase {

    private DelegatingMethodBodyRule rule = new DelegatingMethodBodyRule("controllerDelegate");

    @Test
    public void applyRule_shouldCreate_methodCall_onDelegate() throws JClassAlreadyExistsException {

        JDefinedClass jClass = jCodeModel.rootPackage()._class(JMod.PUBLIC, "TestClass");
        JMethod jMethod = jClass.method(JMod.PUBLIC, Object.class, "getBase");
        ApiMappingMetadata endpointMetadata = getControllerMetadata().getApiCalls().iterator().next();
        jMethod = rule.apply(endpointMetadata, jMethod);

        assertThat(jMethod, is(notNullValue()));
        assertThat(jMethod.body().isEmpty(), is(false));
        assertThat(serializeModel(), containsString("return this.controllerDelegate.getBase();"));
    }

    @Test
    public void applyRule_onEmptyFieldName_shouldFallBackToDefaultFieldName() throws JClassAlreadyExistsException {

        rule = new DelegatingMethodBodyRule("");

        JDefinedClass jClass = jCodeModel.rootPackage()._class(JMod.PUBLIC, "TestClass");
        JMethod jMethod = jClass.method(JMod.PUBLIC, Object.class, "getBase");
        ApiMappingMetadata endpointMetadata = getControllerMetadata().getApiCalls().iterator().next();
        jMethod = rule.apply(endpointMetadata, jMethod);

        assertThat(jMethod, is(notNullValue()));
        assertThat(jMethod.body().isEmpty(), is(false));
        assertThat(serializeModel(), containsString("return this.delegate.getBase();"));
    }


    @Test
    public void applyRule_onNullFieldName_shouldFallBackToDefaultFieldName() throws JClassAlreadyExistsException {

        rule = new DelegatingMethodBodyRule(null);

        JDefinedClass jClass = jCodeModel.rootPackage()._class(JMod.PUBLIC, "TestClass");
        JMethod jMethod = jClass.method(JMod.PUBLIC, Object.class, "getBase");
        ApiMappingMetadata endpointMetadata = getControllerMetadata().getApiCalls().iterator().next();
        jMethod = rule.apply(endpointMetadata, jMethod);

        assertThat(jMethod, is(notNullValue()));
        assertThat(jMethod.body().isEmpty(), is(false));
        assertThat(serializeModel(), containsString("return this.delegate.getBase();"));
    }
}
