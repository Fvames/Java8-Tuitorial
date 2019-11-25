## 1、Java8 Date Time API 简介	

### 1.1、新旧 Date Time API 对比

### 包位置对比

1、Java8之前的日期、时间分布在`java.util`和`java.sql`的包中，此外用于格式化和解析的类在`java.text`包下，位置分散凌乱，不便于查找使用，类命名具有误导性，

示例：

> java.util.Date：实际作用应该是DateTime，提供getDay()，getHours()，getMinutes()等方法，且getDay()方法获取的是星期几
>
> java.sql.Date：代表日期，与java.util.Date同名，且大量过时方法，没有关联时区不支持国际化
>
> java.sql.Timestamp
>
> java.text.SimpleDateFormat：格式化

2、Java8开始，功能集中在java.time包下面，且各个包、类的命名及作用清晰，

​      每个类内提供 Format 和 Parse方法，可以直接调用`java.time.LocalDateTime#getDayOfMonth`、`java.time.LocalDateTime#getHour` 等方法

​      每个类提供通用方法，如：加、减、格式化、解析、从日期/时间中提取单独部分，等等。

> java.time 根目录下: LocalDate、LocalTime、LocalDateTime、Instant类
> Chrono/chronology  日历系统：包含很多年表，包括日本、泰国、民国等
> format 格式化：时间格式化及解析
> temporal 时态包：时态的一些操作定义、底层框架和扩展特性，例如：获取月份最后一天
> zone 时区：时区的支持类

### 安全性

1、Java8之前 所有Date类都是可变的，在别处的修改会影响该日期值，所以应该返回该日期的克隆而不是日期本身

多线程下使用 **静态的 SimpleDateFormat 实例** 时存在线程安全问题，如： `ava.lang.NumberFormatException: multiple points错误`、格式化的时间与输入的时间不一致等问题。

原因：

`java.text.SimpleDateFormat#format(java.util.Date, java.lang.StringBuffer, java.text.Format.FieldDelegate)`

```java
// 源码
private StringBuffer format(Date date, StringBuffer toAppendTo,
                                FieldDelegate delegate) {
    // 多线程问题，thread1 赋值之后，中断；thread2 赋值，中断；thread1回来数据值已变更
    calendar.setTime(date);
    ...
}

/**
 解决： 
	 1.作为方法内部局部变量使用，但每次调用就需要新创建一个实例
	 2.使用ThreadLocal，每个线程持有一个simpleDateFormat对象
	 3.同步simpleDateFormat对象，当一个线程调用此方法时，其他调用此方法的线程就要等待
*/
private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
public static String formatDate(Date date) throws Exception{
    synchronized(sdf) { 
        return sdf.formate(date);
    }
}

```



2、Java8时所有Date类都具有不变性（final 修饰类），且每次调用都会返回新的对象，所以每次的修改都能保证原对象不变。

// todo Java8 format 如何解决

```java
// 源码
public LocalDate plusDays(long daysToAdd) {
    if (daysToAdd == 0) {
        return this;
    }
    long mjDay = Math.addExact(toEpochDay(), daysToAdd);
    return LocalDate.ofEpochDay(mjDay);
}

public static LocalDate ofEpochDay(long epochDay) {
    long zeroDay = epochDay + DAYS_0000_TO_1970;
    // .......
    return new LocalDate(year, month, dom);
}
```



## 2、Java8 Date Time API 示例

### 2.1 java.time.LocalDate 日期类，默认格式为 `yyyy-MM-dd`

```JAVA
// 当前日期
LocalDate today = LocalDate.now();		
System.out.println("当前日期=" + today); // 当前日期=2018-12-27

// 指定日期
LocalDate firstDay_2018 = LocalDate.of(2018, Month.JANUARY, 1);
System.out.println("指定日期=" + firstDay_2018); // 指定日期=2018-01-01

// 其他时区当前日期
LocalDate todayNew_York = LocalDate.now(ZoneId.of("America/New_York"));
System.out.println("纽约当前日期=" + todayNew_York); //洛杉矶当前日期=2018-12-27

LocalDate dateFromBase = LocalDate.ofEpochDay(365); // 01/01/1970
System.out.println("开始日期增加 365 天 = " + dateFromBase); //开始日期增加 365 天 = 1971-01-01

LocalDate hundredDay2014 = LocalDate.ofYearDay(2018, 90);
System.out.println("年份第一天增加 90 天=" + hundredDay2014); //年份第一天增加 100 天=2018-03-31
```

### 2.2 java.time.LocalTime 时间类

```Java
LocalTime time = LocalTime.now();
System.out.println("当前时间=" + time); // 当前时间=14:55:50.494

LocalTime specificTime = LocalTime.of(12, 20, 25, 40);
System.out.println("具体时间=" + specificTime); // 具体时间=12:20:25.000000040

LocalTime timeLos_Angeles = LocalTime.now(ZoneId.of("America/Los_Angeles"));
System.out.println("洛杉矶当前时间=" + timeLos_Angeles); // 洛杉矶当前时间=22:55:50.495

LocalTime specificSecondTime = LocalTime.ofSecondOfDay(10000);
System.out.println("1000 秒= " + specificSecondTime); //10000th second time= 02:46:40
```

### 2.3 java.time.LocalDateTime 日期时间类

```Java
LocalDateTime today = LocalDateTime.now();
System.out.println("当前日期时间=" + today); // 当前日期时间=2018-12-28T15:15:01.364

today = LocalDateTime.of(LocalDate.now(), LocalTime.now());
System.out.println("当前日期时间=" + today); // 当前日期时间=2018-12-28T15:15:01.365

LocalDateTime specificDate = LocalDateTime.of(2014, Month.JANUARY, 1, 10, 10, 30);
System.out.println("具体日期时间=" + specificDate); // 具体日期时间=2014-01-01T10:10:30

LocalDateTime todayLos_Angeles = LocalDateTime.now(ZoneId.of("America/Los_Angeles"));
System.out.println("洛杉矶当前日期时间=" + todayLos_Angeles); // 洛杉矶当前日期时间=2018-12-27T23:15:01.366

LocalDateTime dateFromBase = LocalDateTime.ofEpochSecond(10000, 0, ZoneOffset.UTC);
System.out.println("初始日期加 10000 秒 = " + dateFromBase); // 初始日期加 10000 秒 = 1970-01-01T02:46:40
```

### 2.4 java.time.Instant 时间戳

```java
Instant timestamp = Instant.now();
System.out.println("当前时间 = " + timestamp); //当前时间 = 2018-12-28T07:36:40.572Z

long timestampLong = timestamp.toEpochMilli();
System.out.println("时间戳 = "+ timestampLong); // 时间戳 = 1545982600572

Instant specificTime = Instant.ofEpochMilli(timestampLong);
System.out.println("具体时间 = " + specificTime); // 具体时间 = 2018-12-28T07:36:40.572Z
```



## 3、Java8 及旧 API 常用操作实例

### 3.1 获取当前日期

```java
LocalDate newCurrentDate = LocalDate.now(); //2018-12-17
LocalDate newSetDate = LocalDate.of(2018, 12, 17); 	
```

```java
Date oldCurrentDate = new Date(); // Thu Dec 27 11:20:47 CST 2018
```

### 3.2  获取月的某一天

```Java
  LocalDate lastDayOfThisMonth = newCurrentDate.with(TemporalAdjusters.lastDayOfMonth()); //2018-12-31
  LocalDate secondDayOfThisMonth = newCurrentDate.withDayOfMonth(2); //2018-12-02
  // 获取指定月份的最后一天
  LocalDate lastDayOfMonth = newCurrentDate.withMonth(11).with(TemporalAdjusters.lastDayOfMonth()); // 2018-11-30
```

```Java
  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
  Calendar calendar = Calendar.getInstance();
  calendar.set(Calendar.DAY_OF_MONTH,
               calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
  String firstDayOfWeek = format.format(calendar.getTime());  // 2018-12-31
  // 获取指定月份的最后一天
  calendar.set(Calendar.MONTH, 11); // 月份从 0 开始
  calendar.set(Calendar.DAY_OF_MONTH, 0); // 2018-11-30
  
```

### 3.3 计算时间差

```Java
LocalDateTime start = LocalDateTime.of(2018, 11, 1, 0, 0, 0);
LocalDateTime end = LocalDateTime.of(2018, 12, 1, 0, 0, 0);
// Duration 
Duration duration = Duration.between(start, end);
System.out.println("日期天数差：" + duration.toDays());	 // 30
// Period
Period period = Period.between(start.toLocalDate(), end.toLocalDate());
System.out.println(period.getYears()+" 年 "+ period.getMonths()+" 月 "+ period.getDays()+" 日 "); // 0 年 1 月 0 日 
// ChronoUnit
long daysDiff = ChronoUnit.DAYS.between(start, end);
System.out.println("日期天数差：" + daysDiff);
long hoursDiff = ChronoUnit.HOURS.between(start, end);
System.out.println("日期小时差：" + hoursDiff);

```

```Java
SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
/*天数差*/
Date fromDate1 = simpleFormat.parse("2018-12-01 00:00");  
Date toDate1 = simpleFormat.parse("2018-12-30 12:00");  
long from1 = fromDate1.getTime();  
long to1 = toDate1.getTime();  
int days = (int) ((to1 - from1) / (1000 * 60 * 60 * 24));  
System.out.println("两个时间之间的天数差为：" + days);
```

### 3.4 格式化

```java
DateTimeFormatter formatter =
                             DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
String strDate = LocalDateTime.now().format(formatter);
System.out.println("系统自带年月日："+ strDate); // 2018-12-17
DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
String strDate1 = LocalDateTime.now().format(formatter1);
System.out.println("指定年月日："+ strDate1); // 2018-12-17
```

```Java
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
String strDate2 = simpleDateFormat.format(new Date());
System.out.println("系统自带："+strDate2);  // 2018-12-17
// org.apache.commons.lang3.time.DateFormatUtils
String strDate3 = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
System.out.println("工具类："+ strDate3); // 2018-12-17
```

### 3.5 解析

```java
LocalDateTime parseDate1 = LocalDateTime.parse("2018-12-17 18:04:01",     
							DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
 System.out.println("Java8: "+ parseDate1); // Java8: 2018-12-17T18:04:01
```

```Java
try {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = simpleDateFormat.parse("2018-12-17 18:04:01");
    System.out.println("Java8 之前: " +date); // Java8 之前: Mon Dec 17 18:04:01 CST 2018
} catch (ParseException e) {
    e.printStackTrace();
}
```

### 3.6 获取毫秒

```java
// 获取秒数
Long second = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
System.out.println("second = "+ second);
// 获取毫秒数
Long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
System.out.println("milliSecond = "+milliSecond);
// LocalDateTime 获取毫秒数
System.out.println(Timestamp.valueOf(LocalDateTime.now()).getTime());
// Date 获取毫秒数
System.out.println(new java.util.Date().getTime());
```

### 3.7 Java8 对旧 Date API 的支持

```java
// Date to Instant
Instant timestamp = new Date().toInstant();

// Calendar to Instant
Instant time = Calendar.getInstance().toInstant();
System.out.println("Instant = "+time); // Instant = 2018-12-28T07:45:31.652Z

// Instant 转 Date
Date dt = Date.from(Instant.now());
System.out.println("date = "+ dt); // date = Fri Dec 28 15:45:31 CST 2018

// java.util.TimeZone 转 java.time.ZoneId
ZoneId defaultZone = TimeZone.getDefault().toZoneId();
System.out.println("defaultZone = "+defaultZone); // defaultZone = Asia/Shanghai

/**
* java.time.ZoneId 转  java.util.TimeZone 
* TimeZone =sun.util.calendar.ZoneInfo[id="Asia/Shanghai",offset=28800000,dstSavings=0,
*              useDaylight=false,transitions=19,lastRule=null]
*/
TimeZone tz = TimeZone.getTimeZone(defaultZone);
System.out.println("TimeZone = "+ tz); 

//java.util.GregorianCalendar#GregorianCalendar() 转 java.time.ZonedDateTime
ZonedDateTime gregorianCalendarDateTime = new GregorianCalendar().toZonedDateTime();
System.out.println(gregorianCalendarDateTime); // 2018-12-28T15:45:31.672+08:00[Asia/Shanghai]

/**
* java.time.ZonedDateTime 转  java.util.GregorianCalendar#GregorianCalendar()
* GregorianCalendar = java.util.GregorianCalendar[time=1545983131672,areFieldsSet=true,
* areAllFieldsSet=true,lenient=true,zone=sun.util.calendar.ZoneInfo[id="Asia/Shanghai",
* offset=28800000,dstSavings=0,useDaylight=false,transitions=19,lastRule=null],
* firstDayOfWeek=2,minimalDaysInFirstWeek=4,ERA=1,YEAR=2018,MONTH=11,WEEK_OF_YEAR=52,
*WEEK_OF_MONTH=4,DAY_OF_MONTH=28,DAY_OF_YEAR=362,DAY_OF_WEEK=6,DAY_OF_WEEK_IN_MONTH=4,AM_PM=1,
*HOUR=3,HOUR_OF_DAY=15,MINUTE=45,SECOND=31,MILLISECOND=672,ZONE_OFFSET=28800000,DST_OFFSET=0]
*/
GregorianCalendar gc = GregorianCalendar.from(gregorianCalendarDateTime);
System.out.println("GregorianCalendar = "+gc);
```





