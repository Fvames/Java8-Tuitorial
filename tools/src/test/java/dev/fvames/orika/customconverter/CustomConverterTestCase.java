package dev.fvames.orika.customconverter;

import lombok.Data;
import ma.glasnost.orika.*;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class CustomConverterTestCase {

    private MapperFactory mapperFactory;

    @Before
    public void getMapperFactory() {
        mapperFactory = new DefaultMapperFactory.Builder().build();
    }

    @Test
    public void testCustomMapping() {
        mapperFactory.getConverterFactory().registerConverter(new MyCustomConverter());
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
        person.setCreateDate(new Date());

        MapperFacade mapperFacade = mapperFactory.getMapperFacade();

        CustomPersonDto personDto = mapperFacade.map(person, CustomPersonDto.class);
        Assert.assertEquals(person.getFirstName() + "-" + person.getLastName(), personDto.getFullName());
        Assert.assertEquals(person.getAge(), personDto.getAge());
        Assert.assertNotNull(personDto.getAge());
        Assert.assertEquals(person.getCreateDate().getTime(), personDto.getCreateDate());

        CustomPerson person1 = mapperFacade.map(personDto, CustomPerson.class);
        Assert.assertEquals(person1.getCreateDate(), new Date(personDto.getCreateDate()));

    }

    public static class MyCustomConverter extends CustomConverter<Date, Long> {

        @Override
        public Long convert(Date source, Type<? extends Long> destinationType, MappingContext mappingContext) {
            return source.getTime();
        }
    }


    @Data
    public static class CustomPersonDto {
        private String fullName;
        private Integer age;
        private long createDate;
    }

    @Data
    public static class CustomPerson {
        private String firstName;
        private String lastName;
        private Integer age;
        private Date createDate;
    }
}
