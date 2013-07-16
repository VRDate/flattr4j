/*
 * flattr4j - A Java library for Flattr
 *
 * Copyright (C) 2013 Richard "Shred" Körber
 *   http://flattr4j.shredzone.org
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License / GNU Lesser
 * General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 */
package org.springframework.social.flattr.config;

import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.social.config.annotation.AbstractProviderConfigRegistrarSupport;
import org.springframework.social.flattr.config.annotation.EnableFlattr;
import org.springframework.social.flattr.config.support.FlattrApiHelper;
import org.springframework.social.flattr.connect.FlattrAuthenticationService;
import org.springframework.social.flattr.connect.FlattrConnectionFactory;
import org.springframework.social.security.provider.SocialAuthenticationService;

/**
 * Flattr specific implementation of {@link ImportBeanDefinitionRegistrar} for configuring
 * a connection factory and an API binding bean.
 *
 * @author Richard "Shred" Körber
 * @since 2.8
 */
public class FlattrProviderConfigRegistrar extends AbstractProviderConfigRegistrarSupport {

    public FlattrProviderConfigRegistrar() {
        super(EnableFlattr.class, FlattrConnectionFactory.class, FlattrApiHelper.class);
    }

    @Override
    protected Class<? extends SocialAuthenticationService<?>> getAuthenticationServiceClass() {
        return FlattrAuthenticationService.class;
    }

}
