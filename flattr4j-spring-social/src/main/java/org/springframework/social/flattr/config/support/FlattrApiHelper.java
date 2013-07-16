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
package org.springframework.social.flattr.config.support;

import org.shredzone.flattr4j.FlattrService;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.xml.ApiHelper;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;

/**
 * This API helper is used to either fetch or intantiate an instance of the Flattr API
 * binding class.
 *
 * @author Richard "Shred" Körber
 * @since 2.8
 */
public class FlattrApiHelper implements ApiHelper<FlattrService> {

    private final UsersConnectionRepository usersConnectionRepository;
    private final UserIdSource userIdSource;

    private FlattrApiHelper(UsersConnectionRepository usersConnectionRepository, UserIdSource userIdSource) {
        this.usersConnectionRepository = usersConnectionRepository;
        this.userIdSource = userIdSource;
    }

    @Override
    public FlattrService getApi() {
        ConnectionRepository repository = usersConnectionRepository.createConnectionRepository(userIdSource.getUserId());
        Connection<FlattrService> connection = repository.findPrimaryConnection(FlattrService.class);
        return (connection != null ? connection.getApi() : null);
    }

}
