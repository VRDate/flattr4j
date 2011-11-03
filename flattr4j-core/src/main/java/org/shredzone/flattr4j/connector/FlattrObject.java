/*
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
package org.shredzone.flattr4j.connector;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.shredzone.flattr4j.exception.MarshalException;

/**
 * Represents the raw Flattr data.
 * <p>
 * Basically, this is a wrapper around {@link JSONObject}, which takes care for the
 * {@link JSONException} and also for serialization of JSON structures.
 *
 * @author Richard "Shred" Körber
 * @version $Revision:$
 */
public class FlattrObject implements Externalizable {
    // TODO: serialVersionID
    
    private JSONObject data;

    public FlattrObject() {
        data = new JSONObject();
    }
    
    public FlattrObject(JSONObject data) {
        this.data = data;
    }
    
    public FlattrObject(String json) {
        try {
            this.data = (JSONObject) new JSONTokener(json).nextValue();
        } catch (JSONException ex) {
            throw new MarshalException(ex);
        }
    }

    public Object getObject(String key) {
        try {
            return data.get(key);
        } catch (JSONException ex) {
            throw new MarshalException(key, ex);
        }
    }

    public String get(String key) {
        try {
            return data.getString(key);
        } catch (JSONException ex) {
            throw new MarshalException(key, ex);
        }
    }
    
    public String getSubString(String key, String subKey) {
        try {
            JSONObject obj = data.getJSONObject(key);
            return obj.getString(subKey);
        } catch (JSONException ex) {
            throw new MarshalException(key, ex);
        }
    }
    
    public FlattrObject getFlattrObject(String key) {
        try {
            return new FlattrObject(data.getJSONObject(key));
        } catch (JSONException ex) {
            throw new MarshalException(key, ex);
        }
    }
    
    public void put(String key, Object value) {
        try {
            data.put(key, value);
        } catch (JSONException ex) {
            throw new MarshalException(key, ex);
        }
    }
    
    public void putStrings(String key, Collection<String> value) {
        try {
            JSONArray array = new JSONArray();
            if (value != null) {
                for (String tag : value) {
                    array.put(tag);
                }
            }
            data.put(key, array);
        } catch (JSONException ex) {
            throw new MarshalException(key, ex);
        }
    }
    
    public int getInt(String key) {
        try {
            return data.getInt(key);
        } catch (JSONException ex) {
            throw new MarshalException(key, ex);
        }
    }
    
    public boolean getBoolean(String key) {
        try {
            return data.getBoolean(key);
        } catch (JSONException ex) {
            throw new MarshalException(key, ex);
        }
    }

    public Date getDate(String key) {
        try {
            if (data.isNull(key)) {
                return null;
            }
            
            long ts = data.getLong(key);
            return (ts != 0 ? new Date(ts * 1000L) : null);
        } catch (JSONException ex) {
            throw new MarshalException(key, ex);
        }
    }

    public List<String> getStrings(String key) {
        try {
            JSONArray array = data.getJSONArray(key);
            List<String> result = new ArrayList<String>(array.length());
            for (int ix = 0; ix < array.length(); ix++) {
                result.add(array.getString(ix));
            }
            return result;
        } catch (JSONException ex) {
            throw new MarshalException(key, ex);
        }
    }
    
    @Override
    public String toString() {
        return data.toString();
    }
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(data.toString());
    }
    
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        try {
            data = new JSONObject(in.readUTF());
        } catch (JSONException ex) {
            throw new IOException("JSON deserialization failed: " + ex.getMessage());
        }
    }

}