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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.namespace.QName;

import org.shredzone.flattr4j.exception.FlattrException;
import org.shredzone.flattr4j.exception.FlattrServiceException;
import org.shredzone.flattr4j.model.ClickCount;
import org.shredzone.flattr4j.model.User;

/**
 * Parses an XML document for {@link ClickCount} entries.
 *
 * @author Richard "Shred" Körber
 * @version $Revision$
 */
public class ClickCountXmlParser extends AbstractXmlParser<ClickCount> {

    private final static QName QN_CLICKS = new QName("clicks");
    private final static QName QN_ANONYMOUS = new QName("anonymous");
    private final static QName QN_PUBLIC = new QName("public");
    private final static QName QN_COUNT = new QName("count");
    private final static QName QN_USER = new QName("user");
    private final static QName QN_ID = new QName("id");
    private final static QName QN_USERNAME = new QName("username");

    private ClickCount current = null;
    private List<User> users;
    private User user = null;
    private boolean insideAnonymous = false;
    private boolean insidePublic = false;

    public ClickCountXmlParser(InputStream in) throws FlattrException {
        super(in);
    }

    @Override
    protected void parseStartElement(QName tag) throws FlattrException {
        if (QN_CLICKS.equals(tag) && current == null) {
            current = new ClickCount();
            users = new ArrayList<User>();
            current.setUsers(Collections.unmodifiableList(users));
            
        } else if (QN_ANONYMOUS.equals(tag) && current != null) {
            insideAnonymous = true;
            
        } else if (QN_PUBLIC.equals(tag) && current != null) {
            insidePublic = true;

        } else if (QN_USER.equals(tag) && current != null && user == null) {
            user = new User();
        }
    }

    @Override
    protected ClickCount parseEndElement(QName tag, String body) throws FlattrException {
        ClickCount result = null;

        if (QN_CLICKS.equals(tag) && current != null) {
            result = current;
            current = null;
            users = null;

        } else if (QN_ANONYMOUS.equals(tag) && insideAnonymous) {
            insideAnonymous = false;

        } else if (QN_PUBLIC.equals(tag) && insidePublic) {
            insidePublic = false;

        } else if (QN_USER.equals(tag) && users != null && user != null) {
            users.add(user);
            user = null;
            
        } else if (QN_COUNT.equals(tag) && current != null && insideAnonymous) {
            try {
                current.setAnonymousCount(Integer.parseInt(body));
            } catch (NumberFormatException ex) {
                throw new FlattrServiceException("Invalid anonymous count: " + body);
            }

        } else if (QN_COUNT.equals(tag) && current != null && insidePublic) {
            try {
                current.setPublicCount(Integer.parseInt(body));
            } catch (NumberFormatException ex) {
                throw new FlattrServiceException("Invalid public count: " + body);
            }

        } else if (QN_ID.equals(tag) && user != null) {
            user.setId(body);

        } else if (QN_USERNAME.equals(tag) && user != null) {
            user.setUsername(body);
        }
        
        return result;
    }

}