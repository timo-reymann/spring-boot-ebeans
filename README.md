spring-boot-ebeans
===
# What is this?
Wrapper to use ebeans with spring boot. Simply put ``@EnableEbeansSupport``on your spring application and let the magic happen ;).

# Additional features
Easily implement database services with builtin generic database service, like shown:

````java
@Service
public class FooService extends DatabaseService<Foo,String> {
    @Autowired
    public Foo(EbeanServer ebeanServer) {
        super(new GenericDAO<>(), ebeanServer);
    }

    public List<Foo> all() {
        return dao.findAll();
    }

    @Override
    protected Class<Foo> provideClass() {
        return Foo.class;
    }
    
    public Foo findByProp(String myProp) {
        return dao.query()
                .where()
                .eq("myProp",myProp);
    }
}
````

To make furhter configuration use config properties prefixed by ``ebean``.

# How to use?
This project is available via the maven central,  you can include it by adding it as dependency

```xml
<dependency>
    <groupId>com.github.timo-reymann</groupId>
    <artifactId>spring-boot-ebean</artifactId>
    <version>1.0.0</version>
</dependency>
 ```
 
 If you would like to auto enhance your classes add the following plugins, provided by ebeans:
 
 ```xml
<plugin>
    <groupId>io.repaint.maven</groupId>
    <artifactId>tiles-maven-plugin</artifactId>
</plugin>
<plugin>
    <groupId>io.ebean</groupId>
    <artifactId>ebean-maven-plugin</artifactId>
</plugin>
```

Please dont forget to add ebean.entity-packages to your spring configuration so that ebeans can find your entities and stuff in the deployed jar!


# Additional resources
For more information about ebeans head over to [ebean-orm.github.io](http://ebean-orm.github.io/).

For javadoc please click [here](https://www.javadoc.io/doc/com.github.timo-reymann/spring-boot-ebean)
