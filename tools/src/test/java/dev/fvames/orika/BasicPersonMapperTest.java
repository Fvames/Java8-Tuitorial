package dev.fvames.orika;

import dev.fvames.domain.BasicPerson;
import dev.fvames.domain.BasicPersonDto;
import ma.glasnost.orika.MapperFacade;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class BasicPersonMapperTest {


    private BasicPersonMapper basicPersonMapper;

    @Before
    public void getBasicPersonMapper() {
        this.basicPersonMapper = new BasicPersonMapper();
    }

    @Test
    public void classMap() {
        MapperFacade mapperFacade = basicPersonMapper.classMap().getMapperFacade();

        BasicPerson basicPerson = new BasicPerson();
        basicPerson.setName("黑色毛衣");
        basicPerson.setAge(null);
        basicPerson.setBirthDate(new Date());

        BasicPersonDto mapDto = mapperFacade.map(basicPerson, BasicPersonDto.class);
        System.out.println("personToDto: " + mapDto);

        BasicPerson basicPerson1 = mapperFacade.map(mapDto, BasicPerson.class);
        System.out.println("dtoToPerson: " + basicPerson1);
    }

    @Test
    public void fieldMap() {
        MapperFacade mapperFacade = basicPersonMapper.fieldMap().getMapperFacade();

        BasicPerson basicPerson = new BasicPerson();
        basicPerson.setName("黑色毛衣");
        basicPerson.setAge(null);
        basicPerson.setBirthDate(new Date());
        basicPerson.setSex(1);

        BasicPersonDto mapDto = mapperFacade.map(basicPerson, BasicPersonDto.class);
        System.out.println("personToDto: " + mapDto);

        BasicPerson basicPerson1 = mapperFacade.map(mapDto, BasicPerson.class);
        System.out.println("dtoToPerson: " + basicPerson1);
    }
}
