/*
 * flattr4j - A Java library for Flattr
 *
 * Copyright (C) 2014 Richard "Shred" Körber
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
package org.shredzone.flattr4j.async.flattr;

import java.util.List;

import org.shredzone.flattr4j.FlattrService;
import org.shredzone.flattr4j.async.FlattrCallable;
import org.shredzone.flattr4j.async.PaginatedFlattrCallable;
import org.shredzone.flattr4j.model.Flattr;
import org.shredzone.flattr4j.model.ThingId;
import org.shredzone.flattr4j.model.UserId;

/**
 * {@link FlattrCallable} for invoking {@link FlattrService#getFlattrs(ThingId)} or
 * {@link FlattrService#getFlattrs(UserId)}
 *
 * @author Iulius Gutberlet
 * @author Richard "Shred" Körber
 */
public class GetFlattrsMethod extends PaginatedFlattrCallable<List<Flattr>> {
    private static final long serialVersionUID = 9025446600337597880L;

    private final ThingId thingId;
    private final UserId userId;

    public GetFlattrsMethod(ThingId thingId) {
        this(thingId, null);
    }

    public GetFlattrsMethod(UserId userId) {
        this(null, userId);
    }

    private GetFlattrsMethod(ThingId thingId, UserId userId) {
        if (thingId == null && userId == null) {
            throw new IllegalArgumentException("parameter must not be null");
        }
        this.thingId = thingId;
        this.userId = userId;
    }

    @Override
    public List<Flattr> call(FlattrService service, Integer count, Integer page)
        throws Exception {
        if (thingId != null) {
            return service.getFlattrs(thingId, count, page);
        } else {
            return service.getFlattrs(userId, count, page);
        }
    }

}
