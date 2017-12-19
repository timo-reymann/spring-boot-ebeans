spring-boot-ebeans
===

Wrapper to use ebeans with spring boot. Simply but ``@EnableEbeansSupport``on your spring application and let the magic
happen ;).

Easily implement database services with builtin generic database service:

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