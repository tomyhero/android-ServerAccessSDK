package com.github.tomyhero.serveraccesssdk;

import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import android.app.Application;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;

import java.util.Map;


/**
 * Created by tomyhero on 12/21/15.
 */
public class Client {

    private final static String TAG = "Client";

    public String GetEndpoint(){
        return "";
    }

    public CustomRequest CreateRequest(int method, String url, Map<String, String> params,
                                       Response.Listener<JSONObject> reponseListener, Response.ErrorListener errorListener){
        return new CustomRequest(method,url,params,reponseListener,errorListener);
    }



    public void get(final Command cmd,
                    final Map<String, String> params,
                    final ClientCallback<Void> callback
    ) {



        String url = this.GetEndpoint() + cmd.path;

        final Client c = this;

        CustomRequest jsonObjReq = this.CreateRequest(Request.Method.POST, url, params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        c.onResponse(cmd, callback, jsonObject);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        c.onErrorResponse(callback, error);

                    }
                }

        );

        int TIMEOUT_MS = 10000;//10秒

        DefaultRetryPolicy policy = new DefaultRetryPolicy(TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjReq.setRetryPolicy(policy);


        RequestQueue queue = Volley.newRequestQueue(this.getApplication());
        queue.add(jsonObjReq);
    }


    private void onErrorResponse(final ClientCallback<Void> callback, VolleyError error){
        VolleyLog.d(TAG, "Error: " + error.getMessage());
        callback.onCriticalError(error);
        callback.onFinalize();
    }


     private void onResponse(final Command cmd,final ClientCallback<Void> callback,JSONObject jsonObject){
        // 処理
        Log.d(TAG, jsonObject.toString());

        try {
            if (jsonObject.getInt("error") == APIError.ERROR.getID()) {
                ResponseAPIError res = new ResponseAPIError(jsonObject);
                callback.onError(res);
            } else if (jsonObject.getInt("error") == APIError.CLIENT_MAINTENANCE.getID()) {
                ResponseAPIError res = new ResponseAPIError(jsonObject);
                callback.onMaintenance(res);
            } else if (jsonObject.getInt("error") == APIError.CLIENT_UPGRADE_DATA.getID()) {
                ResponseAPIError res = new ResponseAPIError(jsonObject);
                callback.onUpgradeData(res);
            } else if (jsonObject.getInt("error") == APIError.CLIENT_UPGRADE_VERSION.getID()) {
                ResponseAPIError res = new ResponseAPIError(jsonObject);
                callback.onUpgradeClient(res);
            } else if (jsonObject.getInt("error") == APIError.NONE.getID()) {
                ResponseImpl res = (ResponseImpl)cmd.cls.newInstance();
                res.load(jsonObject);
                callback.onSuccess(res);
            } else {
                throw new IllegalArgumentException("not supported error number");
            }

        } catch (JSONException e) {
            Log.e(TAG, e.toString());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            callback.onFinalize();
        }


    }



    // abstruct
    public Application getApplication() {
        return null;
    }




}
