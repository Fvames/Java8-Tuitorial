package dev.fvames.orika;

import dev.fvames.domain.BasicPerson;
import dev.fvames.domain.BasicPersonDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class BasicPersonMapper {

    public MapperFactory classMap() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(BasicPerson.class, BasicPersonDto.class)
                .field("name", "fullName")
                .mapNulls(true).mapNullsInReverse(true)
                .field("age", "currentAge")
                .mapNulls(false).mapNullsInReverse(false)
                .field("birthDate", "birthDate")
                .byDefault()
                .register();

        return mapperFactory;
    }

    public MapperFactory fieldMap() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(BasicPerson.class, BasicPersonDto.class)
                .fieldMap("name", "fullName")
                .mapNulls(true).mapNullsInReverse(true)
                .add()
                .fieldMap("age", "currentAge")
                .mapNulls(false).mapNullsInReverse(false)
                .add()
                .field("birthDate", "birthDate")
                .byDefault()
                .register();

        return mapperFactory;
    }
}
