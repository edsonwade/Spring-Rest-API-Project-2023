package traineer.vanilson.restfullapis_with_spring_boot_2022.serialization_converter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

public class YamlJacksonTwoHttpMessageConverter extends AbstractJackson2HttpMessageConverter {
    public YamlJacksonTwoHttpMessageConverter() {
        super(
                new YAMLMapper()
                        .setSerializationInclusion(
                                JsonInclude.Include.NON_NULL),
                MediaType.parseMediaType("application/x-yaml"));
    }
}
