package dev.fvames.orika;

import dev.fvames.domain.*;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class PersonConvertTest {
    private Person person;

    @Before
    public void preparePojo() {
        Name name = new Name("Li", "Jie");
        Name name1 = new Name("Jon", "Pitersen");
        Name name2 = new Name("Xia", "Luoke");

        List<Name> names = Arrays.asList(name1, name2);

        person = new Person(name, names, new Date());
    }

    @Test
    public void personToDto() {
        PersonMapper personMapper = new PersonMapper();

        PersonDto personDto = new PersonDto();
        personMapper.map(person, personDto);

        System.out.println(personDto);
    }

    @Test
    public void user() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(User.class, UserNative.class)
                .byDefault().register();
        MapperFacade mapper = mapperFactory.getMapperFacade();

        User user = new User();
        user.setId(1L);
        user.setUserName("史塔克");
        user.setAge(null); // null 转为默认值

        UserNative userNative = new UserNative();
        mapper.map(user, userNative);
        System.out.println(userNative);
    }

    @Test
    public void departmentList() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(User.class, UserNative.class)
                .byDefault().register();
        MapperFacade mapper = mapperFactory.getMapperFacade();

        Department department = new Department();
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId(1L);
        user.setUserName("达克妮");
        user.setAge(32);
        department.setUserList(users);
        department.setDeptNames(Arrays.asList("开发部", "销售部", "行政部"));

        Department department2 = mapper.map(department, Department.class);
        System.out.println(department2);
        // 拷贝成功，size = 3，对基本类型的包装类进行拷贝， ps：List<String>
        System.out.println("dept name size(): " + department2.getDeptNames().size());
        // 没有拷贝，size = 0
        System.out.println("dept user size(): " + department2.getUserList().size());
    }
}
