package top.cbaymax.matrix.silk.dashboard.infrastructure.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import top.cbaymax.matrix.silk.dashboard.infrastructure.error.CommonError;
import top.cbaymax.matrix.silk.dashboard.infrastructure.error.SilkError;

/**
 * @author 褚浩
 */
public final class JsonParser {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Deprecated
    private JsonParser() {
    }

    public static String toJSONString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new SilkError(CommonError.json_parse_error);
        }
    }


    public static <T> T toObject(String jsonContent, Class<T> tClass) {
        try {
            return objectMapper.readValue(jsonContent, tClass);
        } catch (JsonProcessingException e) {
            throw new SilkError(CommonError.json_parse_error);
        }
    }

    public static <T> T toObject(String jsonContent, TypeReference<T> valueType) {
        try {
            return objectMapper.readValue(jsonContent, valueType);
        } catch (JsonProcessingException e) {
            throw new SilkError(CommonError.json_parse_error);
        }
    }
}
