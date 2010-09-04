/**
 * flattr4j - A Java library for Flattr
 *
 * Copyright (C) 2010 Richard "Shred" Körber
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
package org.shredzone.flattr4j.oauth;

/**
 * Scope of rights the consumer requests from the user.
 *
 * @author Richard "Shred" Körber
 * @version $Revision$
 */
public enum Scope {

    /**
     * The consumer needs public user information and thing information.
     */
    READ,

    /**
     * The consumer wants to publish things for the authenticated user.
     */
    PUBLISH,

    /**
     * The consumer wants to click things for the authenticated user.
     */
    CLICK;
    
}