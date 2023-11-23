package top.cbaymax.dna.springdemo.spring.annotation;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 褚浩
 */
public class AnnotationConfigApplicationContext {

    // /
    private static final String dirSplit = File.separator;
    private static final String packageSplit = ".";
    private final ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private final Class<?> configClass;
    private final ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();

    public AnnotationConfigApplicationContext(Class<?> configClass) {
        this.configClass = configClass;
        this.scanPackage();
        this.initializeSingleton();

    }

    public <T> T getBean(String beanName, Class<T> clz) {
        if (!beanDefinitionMap.containsKey(beanName)) {
            // bean not found
            throw new RuntimeException("can't find bean " + beanName);
        }
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        Object bean = ScopeType.singleton.equals(beanDefinition.getScope())
                ? singletonObjects.get(beanName) : createBean(beanDefinition);
        if (!clz.isInstance(bean)) {
            throw new RuntimeException("class of bean not match");
        }
        return clz.cast(bean);

    }

    /**
     * 创建对象
     */
    private Object createBean(BeanDefinition beanDefinition) {
        try {
            Class<?> aClass = beanDefinition.getaClass();
            return aClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private BeanDefinition getBeanDefinition(Class<?> beanClass) {
        Component componentAnnotation = beanClass.getAnnotation(Component.class);
        String beanName = componentAnnotation.value();
        // 判断是单例还是原型
        Scope annotation = beanClass.getAnnotation(Scope.class);
        ScopeType scope = annotation.value();
        return new BeanDefinition(beanName, beanClass, scope);
    }

    private void initializeSingleton() {
        beanDefinitionMap.forEach((beanName, beanDef) -> {
            // 单例bean
            if (ScopeType.singleton.equals(beanDef.getScope())) {
                singletonObjects.put(beanName, createBean(beanDef));
            }
        });
    }


    /**
     * 包扫描
     */
    private void scanPackage() {
        // 解析 配置类
        // 注解 扫描路径
        ComponentScan componentScanAnnotation = configClass.getDeclaredAnnotation(ComponentScan.class);
        // 包扫描路径
        String scanClassPath = componentScanAnnotation.value();
        System.out.println(scanClassPath); // top.cbaymax.dna.springdemo.test.service

        // 类加载器
        // bootstrap -->  jre/lib
        // ext       --> jre/lib/ext
        // app       --> classpath
        /*
         * C:\Users\chuhao\.jdks\azul-17.0.8.1\bin\java.exe "
         * -javaagent:D:\Works\IntelliJ IDEA 2023.2.1\lib\idea_rt.jar=53936:D:\Works\IntelliJ IDEA 2023.2.1\bin"
         * -Dfile.encoding=UTF-8
         * -classpath D:\Works\Projects\zero\dna\target\classes top.cbaymax.dna.springdemo.test.MainTest
         */
        // app class loader
        ClassLoader classLoader = AnnotationConfigApplicationContext.class.getClassLoader();
        //
        URL resource = classLoader.getResource(scanClassPath.replace(packageSplit, dirSplit));
        assert resource != null;
        File file = new File(resource.getFile());

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            assert files != null;
            Arrays.stream(files).forEach(classFile -> {
                try {
                    String absolutePath = classFile.getAbsolutePath();
                    System.out.println(absolutePath);
                    if (!absolutePath.endsWith(".class")) {
                        return;
                    }
                    String className = absolutePath.substring(absolutePath.indexOf("top"), absolutePath.indexOf(".class"))
                            .replace(dirSplit, packageSplit);
                    System.out.println(className);
                    Class<?> beanClass = classLoader.loadClass(className);
                    if (beanClass.isAnnotationPresent(Component.class)) {
                        BeanDefinition beanDefinition = getBeanDefinition(beanClass);
                        beanDefinitionMap.put(beanDefinition.getBeanName(), beanDefinition);
                    }
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

            });
        }
    }
}
