package org.jewelhunt.utils;

import java.util.ResourceBundle;

public class ResourceMessage {

    private ResourceBundle resource;

    public ResourceMessage(){
    }

    private void getResource(){
        resource = ResourceBundle.getBundle("properties.local");
    }

    public String getMessage(String key) {
        if(resource == null)  getResource();

        return resource.getString(key);
    }
}
