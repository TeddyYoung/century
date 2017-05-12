SpringMVC部分

1. 需要输出JSON对象只需要在方法之上注解：@ResponseBody

2. 需要对输出的JSON进行过滤或者是所允许的字段和内容：
eg.
@IgnoreProperties(value = @IgnoreProperty(pojo=SysTable.class, name={"tableName"}))

**********************************************************************************************
Entity部分

/*需要将如下信息配置在类之上*/

@Entity	表示实体类

@Table(name="tb_name") 主要用于配置表名，可有可无，默认的表名将会采取Java命名空间

/*需要将如下信息配置在get方法之上*/

@Column(
	length=40,//字段长度
	name="",//字段名称
	unique=true, //是否唯一
	nullable=true,//是否可以为空值
	insertable=true,//是否可插入
	updatable=true//是否可更新
)

@Id 表示当前这个字段是ID，并可为此设置自增长序列等等

**********************************************************************************************
Dao部分 -- 与持久化方法相关

当前中只有实现，没有接口，因为只要写实现就可以了

需要重新写的方法有getDao方法，此方法的作用是为顶级的BaseDao传递一个Class对象，方便Hibernate的对象持久化操作

**********************************************************************************************

Service部分 -- 也业务的方法相关联

当前中只有实现，没有接口，因为只要写实现就可以了

**********************************************************************************************

关于Excel的导出：http://blog.sina.com.cn/s/blog_c30722cf0101hihv.html

*********************************************************************************************
实体校验

Hibernate Validator
Bean Validation 中内置的 constraint  
  
Annotation	属于Bean Validation 规范	应用位置	作用	对Hibernate Core中的元数据的影响
@AssertFalse	yes	field/property	检查被标注的值是否为false.	没有
@AssertTrue	yes	field/property	检查被标注的值是否为true.	没有
@CreditCardNumber	no	字段或属性, 要求其类型为String.	检查被标注的字符串能否通过Luhn Checksum test. 注意, 这个约束条件是为了防止用户手误, 并不对信用卡有效性进行检测. 请参考Anatomy of Credit Card Numbers.	没有
@DecimalMax	yes	字段或属性. 支持类型包括BigDecimal, BigInteger, String, byte, short, int, long和其各自对应的包装器类型.	被标注的值必须不大于约束中指定的最大值. 这个约束的参数是一个通过BigDecimal定义的最大值的字符串表示.	没有
@DecimalMin	yes	字段或属性. 支持类型包括BigDecimal, BigInteger, String, byte, short, int, long和其各自对应的包装器类型.	被标注的值必须不小于约束中指定的最小值. 这个约束的参数是一个通过BigDecimal定义的最小值的字符串表示.	没有
@Digits(integer=, fraction=)	yes	字段或属性. 支持类型包括BigDecimal, BigInteger, String, byte, short, int, long和其各自对应的包装器类型.	检查此值是否是一个数字,并且这个数字的整数部分不超过integer定义的位数, 和小数部分不超过fraction 定义的位数.	对应的数据库表字段会被设置精度(precision)和准度(scale).
@Email	no	字段或属性, 需要是String类型的.	检查所给的字符串是否符合email地址的格式.	没有
@Future	yes	字段或属性, 支持类型是java.util.Date 和 java.util.Calendar.	检查给定的日期是否比现在晚.	没有
@Length(min=, max=)	no	字段或属性, 需要是String类型的.	检查该字符串的长度是否在min 和 max规定的范围内.	对应的数据库表字段的长度会被设置成约束中定义的最大值.
@Max	yes	字段或属性. 支持类型包括BigDecimal, BigInteger, String, byte, short, int, long和其各自对应的包装器类型.	检查该值是否小于或等于约束条件中指定的最大值.	会给对应的数据库表字段添加一个check的约束条件.
@Min	yes	字段或属性. 支持类型包括BigDecimal, BigInteger, String, byte, short, int, long和其各自对应的包装器类型.	检查该值是否大于或等于约束条件中规定的最小值.	会给对应的数据库表字段添加一个check的约束条件.
@NotNull	yes	field/property	检查该值不为null..	对应的表字段不允许为null.
@NotBlank	no	field/property	检查该字符串不为null,并且不是空字符串. 本约束和下面的@NotEmpty的不同之处在于,本约束只能被用在字符串类型上,并且会忽略字符串尾部的空白字符.	没有
@NotEmpty	no	字段或属性. 支持的类型包括String, Collection, Map 和数组.	检查该值不为null同时也不为空.	没有
@Null	yes	field/property	检查该值应该为null.	没有
@Past	yes	字段或属性, 支持类型是java.util.Date 和 java.util.Calendar.	检查标注对象中的值表示的日期比当前早.	没有
@Pattern(regex=, flag=)	yes	字段或属性, 需要是String类型的.	检查该字符串是否能够在match指定的情况下被regex定义的正则表达式匹配.	没有
@Range(min=, max=)	no	字段或属性. 支持类型包括BigDecimal, BigInteger, String, byte, short, int, long和其各自对应的包装器类型.	检查该值是否在[min, max)之间	没有
@Size(min=, max=)	yes	字段或属性. 支持的类型包括String, Collection, Map 和数组.	检查该值的size是否在[min, max)之间.	对应的数据库表字段的长度会被设置成约束中定义的最大值.
@ScriptAssert(lang=, script=, alias=)	no	类	要使用这个约束条件,必须先要保证Java Scripting API 即JSR 223 ("Scripting for the JavaTM Platform")的实现在类路径当中. 如果使用的时Java 6的话,则不是问题, 如果是老版本的话, 那么需要把 JSR 223的实现添加进类路径. 这个约束条件中的表达式可以使用任何兼容JSR 223的脚本来编写. (更多信息请参考javadoc)	没有
@URL(protocol=, host=, port=)	no	字段或属性, 要求其类型为String.	判断该值是否是一个有效的URL, 如果给出了约束中的protocol, host 或 port 参数的话,那个被校验的值需要和其匹配.	没有
@Valid	yes	字段或属性. 支持所有的非原始类型.	递归的对关联对象进行校验, 如果关联对象是个集合或者数组, 那么对其中的元素进行递归校验,如果是一个map,则对其中的值部分进行校验.	没有