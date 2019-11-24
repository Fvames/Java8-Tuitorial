package dev.fvames.apache.commons;

import dev.fvames.domain.Department;
import dev.fvames.domain.User;
import dev.fvames.domain.UserNative;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean2;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanUtilsDemo {

    public static void main(String[] args) throws Exception {

        BeanUtilsBean beanUtilsBean = new BeanUtilsBean2();
        beanUtilsBean.getConvertUtils().register(false, false, 0);

        //bean2Bean(beanUtilsBean);

        UserNative userNative = new UserNative();
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("userName", "史塔克");
        map.put("age", null);
        beanUtilsBean.populate(userNative, map);
        System.out.println(userNative.toString());

        // 包装类型（含 null） 转 基本类型
        User user = new User();
        user.setId(1L);
        user.setUserName("史塔克");
        user.setAge(null);

        UserNative userNative1 = new UserNative();

        // 异常
        // beanUtilsBean.copyProperties(userNative1, user);

        // 先转为 map，再转为 pojo
        Map<String, String> describe = beanUtilsBean.describe(user);
        beanUtilsBean.populate(userNative1, describe);

        System.out.println(userNative1);

        // 基本类型 转 包装类型
        User user1 = new User();
        beanUtilsBean.copyProperties(user1, userNative1);
        System.out.println(user1);
    }

    private static void bean2Bean(BeanUtilsBean beanUtilsBean) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Department department = new Department();
        beanUtilsBean.setProperty(department, "deptName", "开发部");

        String deptName = beanUtilsBean.getProperty(department, "deptName");
        System.out.println("deptName: " + deptName);

        String userNames = beanUtilsBean.getProperty(department, "userNames");
        System.out.println("userNames: " + userNames);

        beanUtilsBean.setProperty(department, "userNames", new String[]{"a", "b"});
        String userNames1 = beanUtilsBean.getProperty(department, "userNames[0]");
        System.out.println("userNames1: " + userNames1);

        String userList = beanUtilsBean.getProperty(department, "userList");
        System.out.println("userList: " + userList);

        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId(1L);
        user.setUserName("达克妮");
        user.setAge(32);
        department.setUserList(users);

        //String user1 = beanUtilsBean.getProperty(department, "userList[0]");
        //System.out.println("user1: " + user1);

        Department dept2 = new Department();
        beanUtilsBean.copyProperties(dept2, department);
        System.out.println("dept2.getDeptName(): " + dept2.getDeptName());

        Department dept3 = (Department) beanUtilsBean.cloneBean(department);
        System.out.println("dept3.getDeptName(): " + dept3.getDeptName());
    }
}
