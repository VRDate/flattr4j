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
package org.shredzone.flattr4j.impl.xml;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;
import org.shredzone.flattr4j.exception.FlattrException;
import org.shredzone.flattr4j.model.Subscription;

/**
 * Unit test of the {@link SubscriptionXmlParser} class.
 *
 * @author Richard "Shred" Körber
 * @version $Revision$
 */
public class SubscriptionXmlParserTest {

    @Test
    public void testParser() throws FlattrException, UnsupportedEncodingException {
        InputStream in = SubscriptionXmlParserTest.class.getResourceAsStream("/org/shredzone/flattr4j/impl/xml/Subscription.xml");
        SubscriptionXmlParser parser = new SubscriptionXmlParser(in);

        Subscription sub = parser.getNext();
        Assert.assertNull(parser.getNext());

        MockDataHelper.assertSubscription(sub);
    }

}