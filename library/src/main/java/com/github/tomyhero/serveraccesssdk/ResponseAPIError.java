package com.github.tomyhero.serveraccesssdk;

/**
 * Created by tomyhero on 12/21/15.
 */

import org.json.JSONException;
import org.json.JSONObject;




public class ResponseAPIError implements ResponseImpl {


    private static final String TAG = "ResponseAPIError";

    public JSONObject jsonObject;
    public APIError apiError;

    @Override
    public void load(JSONObject jsonObject) throws JSONException {
        this.jsonObject = jsonObject;
        this.apiError = APIError.create(jsonObject.getInt("error"));
    }


    public ResponseAPIError(JSONObject jsonObject) throws JSONException {
        this.load(jsonObject);
    }

}
