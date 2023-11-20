package top.cbaymax.matrix.silk.client.layer.parser;

import org.junit.jupiter.api.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.List;

/**
 * @author 褚浩
 */
public class SpELCacheTest {

    private static long calculateRunTime(Runnable function) {
        long start = System.nanoTime();
        function.run();
        long end = System.nanoTime();
        return end - start;

    }

    @Test
    public void testCache() {
        String spelExpression = "(#a.stringList[1]+'tail'+#a.field1).length";

        SpelParserConfiguration configuration = new SpelParserConfiguration(SpelCompilerMode.IMMEDIATE, this.getClass().getClassLoader());

        Expression expressionWithCache = new SpelExpressionParser(configuration).parseExpression(spelExpression);
        Expression expressionWithoutCache = new SpelExpressionParser().parseExpression(spelExpression);


        StandardEvaluationContext ctx = new StandardEvaluationContext();
        ctx.setVariable("a", new ObjectA("demoAbc"));

        for (int i = 0; i < 10; i++) {
            long cacheTime = calculateRunTime(() -> {
                for (int j = 0; j < 1000; j++) {
                    expressionWithCache.getValue(ctx, Double.class);
                }
            });

            long withoutCacheTime = calculateRunTime(() -> {
                for (int j = 0; j < 1000; j++) {
                    expressionWithoutCache.getValue(ctx, Double.class);
                }
            });
            System.out.println("spel with cache cost time:" + cacheTime);
            System.out.println("spel  no  cache cost time:" + withoutCacheTime);
        }
    }

    public static class ObjectA {
        public String field1;
        public List<String> stringList = List.of("sing", "jump", "basketball");

        public ObjectA(String filed1) {
            this.field1 = filed1;
        }

        @Override
        public String toString() {
            return "ObjectA{" +
                    "filed1='" + field1 + '\'' +
                    ", stringList=" + stringList +
                    '}';
        }
    }
}
