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
    <version>0.0.5</version>
</dependency>
 ```
 
 If you would like to auto enhance your classes add the following plugins, provided by ebeans:
 
 ```xml
<plugin>
    <groupId>io.repaint.maven</groupId>
    <artifactId>tiles-maven-plugin</artifactId>
    <version>2.8</version>
    <extensions>true</extensions>
    <configuration>
        <tiles>
            <tile>org.avaje.tile:java-compile:1.1</tile>
            <tile>io.ebean.tile:enhancement:5.1</tile>
        </tiles>
    </configuration>
    </plugin>
<plugin>
    <groupId>io.ebean</groupId>
    <artifactId>ebean-maven-plugin</artifactId>
    <version>11.5.1</version>
    <executions>
        <execution>
            <id>main</id>
            <phase>process-classes</phase>
            <configuration>
                <transformArgs>debug=1</transformArgs>
            </configuration>
            <goals>
                <goal>enhance</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

Please dont forget to add ebean.entity-packages to your spring configuration so that ebeans can find your entities and stuff in the deployed jar!


# Additional resources
For more information head over to [ebean-orm.github.io](http://ebean-orm.github.io/)
