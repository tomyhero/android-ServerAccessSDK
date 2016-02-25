package com.github.tomyhero.serveraccesssdk;

/**
 * Created by tomyhero on 12/21/15.
 */

import java.io.UnsupportedEncodingException;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

import java.util.HashMap;
import com.android.volley.AuthFailureError;


public class CustomRequest extends Request<JSONObject> {
    private static final String TAG = "CustomRequest";

    private Listener<JSONObject> listener;
    private Map<String, String> params;



    public CustomRequest(int method, String url, Map<String, String> params,
                         Listener<JSONObject> responseListener, ErrorListener errorListener) {

        super(method, url, errorListener);
        this.listener = responseListener;
        this.params = params;
    }




    protected Map<String, String> getParams()
            throws com.android.volley.AuthFailureError {
        return params;
    };

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        // TODO Auto-generated method stub
        listener.onResponse(response);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = super.getHeaders();

        Map<String, String> newHeaders = new HashMap<String, String>();
        newHeaders.putAll(headers);
        newHeaders.put("X-ACCESS-TOKEN", this.GetAccessToken());
        newHeaders.put("X-DATA-VERSION", this.GetDataVersion());
        newHeaders.put("X-ANDROID-CLIENT-VERSION", this.GetClientVersion());

        headers = newHeaders;

        return headers;

    }

    public String GetAccessToken() {return "";}
    public String GetDataVersion() {return "";}
    public String GetClientVersion() {
        return "";
    }




}
