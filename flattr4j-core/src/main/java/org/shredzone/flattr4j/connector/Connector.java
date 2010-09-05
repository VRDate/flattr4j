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
package org.shredzone.flattr4j.connector;

import java.io.Reader;
import org.shredzone.flattr4j.exception.FlattrException;

/**
 * Interface for connectors that connect to the Flattr API.
 *
 * @author Richard "Shred" Körber
 * @version $Revision$
 */
public interface Connector {

    /**
     * Invokes a Flattr function.
     * 
     * @param url
     *            Command URL to be invoked
     * @return {@link Reader} with the response
     */
    Result call(String url) throws FlattrException;

    /**
     * Invokes a Flattr function with additional arguments.
     * 
     * @param url
     *            Command URL to be invoked
     * @param data
     *            Argument data to be sent to the Flattr function
     * @return {@link Reader} with the response
     */
    Result post(String url, String data) throws FlattrException;

}
