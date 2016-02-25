package com.github.tomyhero.serveraccesssdk;

/**
 * Created by tomyhero on 12/21/15.
 */
public class Command {

    public String path;
    public String method ;
    public Class cls ;

    public Command(String path,Class cls,String method){
        this.path = path;
        this.cls = cls;
        this.method = method;


    }

    public Command(String path,Class cls){
        this.path = path;
        this.cls = cls;
        this.method = "POST";
    }


}
