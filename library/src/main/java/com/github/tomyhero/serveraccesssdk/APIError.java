package com.github.tomyhero.serveraccesssdk;

/**
 * Created by tomyhero on 12/21/15.
 */
public enum APIError  {
    NONE(0),
    ERROR(1),
    CLIENT_UPGRADE_VERSION(2),
    CLIENT_UPGRADE_DATA(3),
    CLIENT_MAINTENANCE(4);

    private int id;
    APIError(int id) { this.id = id; }
    public int getID() { return id; }

    public static APIError create(int i){
        switch(i){
            case 0:
                return NONE;
            case 1:
                return ERROR;
            case 2:
                return CLIENT_UPGRADE_VERSION;
            case 3:
                return CLIENT_UPGRADE_DATA;
            case 4:
                return CLIENT_MAINTENANCE;
            default:
                return ERROR;
        }
    }
}
