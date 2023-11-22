package com.simios.simioapp.comunes.utiles;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class JSONUtil {

    public static ArrayList<String> fromJSONToArrayList(String bienesJSON) throws java.io.IOException {
        return new ObjectMapper().readValue(bienesJSON, ArrayList.class);
    }

    public static HashMap fromJSONToHashMap(String bienesJSON) throws java.io.IOException {
        return new ObjectMapper().readValue(bienesJSON, HashMap.class);
    }

    public static final String toJSON(Object object, boolean prettyFormat) {

        if (object == null) return StringUtils.EMPTY;

        try {

            ObjectMapper mapper = new ObjectMapper();

            if (prettyFormat) {
                return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            }

            return mapper.writeValueAsString(object);

        } catch (JsonProcessingException e) {

            return StringUtils.EMPTY;
        }
    }

}
