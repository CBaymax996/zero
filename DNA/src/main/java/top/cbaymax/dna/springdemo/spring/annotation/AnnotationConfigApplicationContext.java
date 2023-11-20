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


    private Class<?> configClass;

    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public AnnotationConfigApplicationContext(Class<?> configClass) {
        this.configClass = configClass;
        this.scan();

        beanDefinitionMap.forEach((beanName, beanDef) -> {
            // 单例bean
            if (beanDef.getScope().equals("singleton")) {
                singletonObjects.put(beanName, createBean(beanDef));
            }

        });
    }


    private void scan() {

        this.configClass = configClass;

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
        URL resource = classLoader.getResource(scanClassPath.replace(".", "/"));
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
                            .replace("\\", ".");
                    System.out.println(className);
                    Class<?> beanClass = classLoader.loadClass(className);
                    if (beanClass.isAnnotationPresent(Component.class)) {
                        System.out.println(beanClass);
                        // 解析类，判断是单例还是原型
                        // beanDefinition
                        Component componentAnnotation = beanClass.getAnnotation(Component.class);
                        String beanName = componentAnnotation.value();
                        Scope annotation = beanClass.getAnnotation(Scope.class);
                        String scope = annotation.value();
                        beanDefinitionMap.put(beanName, new BeanDefinition(beanClass, scope));
                    }
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

            });
        }

    }

    // 创建对象

    public Object createBean(BeanDefinition beanDefinition) {
        try {
            Class<?> aClass = beanDefinition.getaClass();
            return aClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public Object getBean(String beanName) {
        if (!beanDefinitionMap.containsKey(beanName)) {
            throw new RuntimeException("can't find bean " + beanName);
        }
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition.getScope().equals("singleton")) {
            return singletonObjects.get(beanName);
        }
        return createBean(beanDefinition);
    }
}
