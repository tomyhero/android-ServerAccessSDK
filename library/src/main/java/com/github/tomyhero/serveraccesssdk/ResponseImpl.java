package com.github.tomyhero.serveraccesssdk;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;


/**
 * Created by tomyhero on 12/21/15.
 */
public interface ResponseImpl {
    public void load(JSONObject jsonObject) throws JSONException;
}
