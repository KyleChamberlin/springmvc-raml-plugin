/*
 * Copyright 2002-2017 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.phoenixnap.oss.ramlapisync.raml.rjp.raml10v2;

import org.raml.v2.api.model.v10.datamodel.TypeDeclaration;

import com.phoenixnap.oss.ramlapisync.raml.RamlParamType;
import com.phoenixnap.oss.ramlapisync.raml.RamlUriParameter;

/**
 * Implementation based on the Raml 1.0 Parser
 * 
 * @author Aleksandar Stojsavljevic
 */
public class RJP10V2RamlUriParameter extends RamlUriParameter {

    private static RJP10V2RamlModelFactory ramlModelFactory = new RJP10V2RamlModelFactory();

    private final TypeDeclaration uriParameter;

    public RJP10V2RamlUriParameter(TypeDeclaration uriParameter) {
        this.uriParameter = uriParameter;
    }

    /**
     * Expose internal representation only package private
     * @return the internal model
     */
    TypeDeclaration getUriParameter() {
        return uriParameter;
    }

    @Override
    public String getDisplayName() {
        return uriParameter.displayName().value();
    }

    @Override
    public void setDisplayName(String displayName) {
    	throw new UnsupportedOperationException();
    }

    @Override
    public RamlParamType getType() {
        return ramlModelFactory.createRamlParamType(uriParameter.type());
    }

    @Override
    public void setType(RamlParamType paramType) {
    	throw new UnsupportedOperationException();
    }

    @Override
    public boolean isRequired() {
        return uriParameter.required().booleanValue();
    }

    @Override
    public void setRequired(boolean required) {
    	throw new UnsupportedOperationException();
    }

    @Override
    public void setExample(String example) {
    	throw new UnsupportedOperationException();
    }

    @Override
    public void setDescription(String description) {
    	throw new UnsupportedOperationException();
    }

    @Override
    public String getExample() {
        return uriParameter.example().value();
    }

    @Override
    public String getDescription() {
        return uriParameter.description().value();
    }

	@Override
	public String getDefaultValue() {
		return uriParameter.defaultValue();
	}
}
