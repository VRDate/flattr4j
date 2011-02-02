/**
 * flattr4j - A Java library for Flattr
 *
 * Copyright (C) 2011 Richard "Shred" Körber
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
import java.util.Date;

import javax.xml.namespace.QName;

import org.shredzone.flattr4j.exception.FlattrException;
import org.shredzone.flattr4j.exception.FlattrServiceException;
import org.shredzone.flattr4j.model.Subscription;

/**
 * Parses an XML document for {@link Subscription} entries.
 *
 * @author Richard "Shred" Körber
 * @version $Revision$
 */
public class SubscriptionXmlParser extends AbstractXmlParser<Subscription> {

    private final static QName QN_SUBSCRIPTION = new QName("subscription");
    private final static QName QN_ID = new QName("id");
    private final static QName QN_MONTHS = new QName("months");
    private final static QName QN_MONTHSLEFT = new QName("monthsleft");
    private final static QName QN_ADDED = new QName("added");
    private final static QName QN_THING = new QName("thing");
    private final static QName QN_TITLE = new QName("title");
    private final static QName QN_URL = new QName("url");

    private Subscription current = null;
    private boolean insideThing = false;

    public SubscriptionXmlParser(InputStream in) throws FlattrException {
        super(in);
    }

    @Override
    protected void parseStartElement(QName tag) throws FlattrException {
        if (QN_SUBSCRIPTION.equals(tag) && current == null) {
            current = new Subscription();
            
        } else if (QN_THING.equals(tag) && current != null) {
            insideThing = true;
        }
    }

    @Override
    protected Subscription parseEndElement(QName tag, String body) throws FlattrException {
        Subscription result = null;

        if (QN_SUBSCRIPTION.equals(tag) && current != null) {
            result = current;
            current = null;
            insideThing = false;

        } else if (QN_THING.equals(tag) && insideThing) {
            insideThing = false;
            
        } else if (QN_ID.equals(tag) && current != null && !insideThing) {
            current.setSubscriptionId(body);

        } else if (QN_MONTHS.equals(tag) && current != null && !insideThing) {
            try {
                current.setMonths(Integer.parseInt(body));
            } catch (NumberFormatException ex) {
                throw new FlattrServiceException("Invalid months: " + body);
            }

        } else if (QN_MONTHSLEFT.equals(tag) && current != null && !insideThing) {
            try {
                current.setMonthsLeft(Integer.parseInt(body));
            } catch (NumberFormatException ex) {
                throw new FlattrServiceException("Invalid months: " + body);
            }

        } else if (QN_ADDED.equals(tag) && current != null && !insideThing) {
            try {
                current.setAdded(new Date(Long.parseLong(body) * 1000L));
            } catch (NumberFormatException ex) {
                throw new FlattrServiceException("Invalid added: " + body);
            }
            
        } else if (QN_ID.equals(tag) && current != null && insideThing) {
            current.setThingId(body);
            
        } else if (QN_TITLE.equals(tag) && current != null && insideThing) {
            current.setTitle(body);
            
        } else if (QN_URL.equals(tag) && current != null && insideThing) {
            current.setUrl(body);
        }
        
        return result;
    }

}