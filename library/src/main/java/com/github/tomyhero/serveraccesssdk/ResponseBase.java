package com.github.tomyhero.serveraccesssdk;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

/**
 * Created by tomyhero on 12/22/15.
 */
public class ResponseBase implements ResponseImpl {
    public JSONObject json;

    public void load(JSONObject jsonObject) throws JSONException {
        this.json = jsonObject;
    }

}
