package dev.fvames.orika.custommapper;

import dev.fvames.domain.CustomPerson;
import dev.fvames.domain.CustomPersonDto;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomMappingTestCase {

    private MapperFactory mapperFactory;

    @Before
    public void getMapperFactory() {
        mapperFactory = new DefaultMapperFactory.Builder().build();
    }

    @Test
    public void testCustomMapping() {
        mapperFactory.classMap(CustomPerson.class, CustomPersonDto.class)
                .customize(new CustomMapper<CustomPerson, CustomPersonDto>() {
                    @Override
                    public void mapAtoB(CustomPerson person, CustomPersonDto personDto, MappingContext context) {
                        String lastName = person.getLastName();
                        String firstName = person.getFirstName();
                        personDto.setFullName(firstName + "-" + lastName);
                    }
                })
                .byDefault()
                .register();

        CustomPerson person = new CustomPerson();
        person.setFirstName("七");
        person.setLastName("里香");
        person.setAge(19);

        CustomPersonDto personDto = mapperFactory.getMapperFacade().map(person, CustomPersonDto.class);
        Assert.assertEquals(person.getFirstName() + "-" + person.getLastName(), personDto.getFullName());
        Assert.assertEquals(person.getAge(), personDto.getAge());
        Assert.assertNotNull(personDto.getAge());

    }

    /*@Data
    public class CustomPersonDto {
        private String fullName;
        private Integer age;
    }

    @Data
    public class CustomPerson {
        private String firstName;
        private String lastName;
        private Integer age;
    }*/
}
