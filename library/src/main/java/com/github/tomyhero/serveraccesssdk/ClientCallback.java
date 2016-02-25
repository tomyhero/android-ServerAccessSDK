package com.github.tomyhero.serveraccesssdk;

import com.android.volley.VolleyError;

/**
 * Created by tomyhero on 12/21/15.
 */
public interface ClientCallback<Void> {

    public void onSuccess( ResponseImpl res );
    public void onCriticalError(VolleyError error);
    public void onError(ResponseAPIError res);
    public void onMaintenance(ResponseAPIError res);
    public void onUpgradeClient(ResponseAPIError res);
    public void onUpgradeData(ResponseAPIError res);
    public void onFinalize();


}
