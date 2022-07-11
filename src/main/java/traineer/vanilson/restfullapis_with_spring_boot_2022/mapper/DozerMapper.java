package traineer.vanilson.restfullapis_with_spring_boot_2022.mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * convert view in entity
 * and entity in object
 */
public class DozerMapper {

    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    // object origin(O) and destine(D) return origin and destination
    public static <O, D> D parseObject(O origin, Class<D> destination) {

        return mapper.map(origin, destination);

    }

    //return  a list of origin and destination.
    public static <O, D> List<D> parseListObject(List<O> origin, Class<D> destination) {
        List<D> destinationObject = new ArrayList<>();
        for (O o : origin) {
            destinationObject.add(mapper.map(origin, destination));
        }
        return destinationObject;
    }
}
