package dev.fvames.orika;

import dev.fvames.domain.Person;
import dev.fvames.domain.PersonDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

public class PersonMapper extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(Person.class, PersonDto.class)
                .field("name.first", "firstName")
                .field("name.last", "lastName")
                .field("knownAliases{first}", "aliases{[0]}")
                .field("knownAliases{last}", "aliases{[1]}")
                .byDefault()
                .register();

    }
}
