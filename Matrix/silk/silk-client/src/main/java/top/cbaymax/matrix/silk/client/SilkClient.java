package top.cbaymax.matrix.silk.client;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import top.cbaymax.matrix.silk.client.domain.transfer.InputDTO;
import top.cbaymax.matrix.silk.client.domain.transfer.SilkDTO;
import top.cbaymax.matrix.silk.client.layer.parse.ParseLayer;

@ComponentScan
public class SilkClient {

    private ApplicationContext springContext;

    private final String app;


    public SilkClient(String app) {
        this.app = app;
        this.springContext = new AnnotationConfigApplicationContext(SilkClient.class);
    }

    /**
     * todo: delete me
     * why spring must run with no arg constructor ?
     */
    private SilkClient() {
        this.app = "silk";
    }

    public SilkDTO weaving(InputDTO input) {
        ParseLayer bean = springContext.getBean(ParseLayer.class);
        return new SilkDTO();
    }
}
