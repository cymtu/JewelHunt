package org.jewelhunt.utils;

import java.util.ResourceBundle;

/**
 * Класс обеспечивает интернационализацию программы
 * @author Барабанов А.В.
 * @version 1.0.1
 */
public class ResourceMessage {

    private final ResourceBundle resource;

    public ResourceMessage(){
        resource = ResourceBundle.getBundle("properties.local");
    }

    /**
     * Возращает интернационализированое значение, по ключу
     * @param key Ключ, по которому производится поиск интернационального значения
     * @return Интернациональное значение
     */
    public String getMessage(String key) {
        return resource.getString(key);
    }
}
