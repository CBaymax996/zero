package top.cbaymax.matrix.ink.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * @author 褚浩
 */
public class SimpleAgent {
    public static void premain(String options, Instrumentation instrumentation) {
        System.out.println(" this is a simple agent");
        instrumentation.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer)
                    throws IllegalClassFormatException {

                if (loader != null) {

                    System.out.println("className: " + className);
                }

                return ClassFileTransformer.super.transform(loader, className, classBeingRedefined, protectionDomain, classfileBuffer);
            }
        });
    }
}
