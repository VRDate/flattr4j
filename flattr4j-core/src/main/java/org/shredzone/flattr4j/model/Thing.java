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
package org.shredzone.flattr4j.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A {@link Thing}. {@link RegisteredThing} is derived from this class and contains
 * additional information about the registration.
 * 
 * @author Richard "Shred" Körber
 * @version $Revision$
 */
public class Thing implements Serializable {
    private static final long serialVersionUID = -6684005944290342599L;
    
    private String url;
    private String title;
    private String category;
    private String description;
    private List<String> tags = new ArrayList<String>();
    private String language;
    private boolean hidden = false;

    /**
     * URL of the Thing.
     */
    public String getUrl()                      { return url; }
    public void setUrl(String url)              { this.url = url; }

    /**
     * Title of the Thing.
     */
    public String getTitle()                    { return title; }
    public void setTitle(String title)          { this.title = title; }

    /**
     * Category id this Thing belongs to.
     */
    public String getCategory()                 { return category; }
    public void setCategory(String category)    { this.category = category; }

    /**
     * A descriptive text about the Thing.
     */
    public String getDescription()              { return description; }
    public void setDescription(String description) { this.description = description; }

    /**
     * Tags this Thing is tagged with.
     */
    public List<String> getTags()               { return tags; }
    public void setTags(List<String> tags)      { this.tags = tags; }
    public void addTag(String tag)              { tags.add(tag); }

    /**
     * Language id of the Thing.
     */
    public String getLanguage()                 { return language; }
    public void setLanguage(String language)    { this.language = language; }

    /**
     * Is the Thing hidden from the public list of Things at Flattr?
     */
    public boolean isHidden()                   { return hidden; }
    public void setHidden(boolean hidden)       { this.hidden = hidden; }

}