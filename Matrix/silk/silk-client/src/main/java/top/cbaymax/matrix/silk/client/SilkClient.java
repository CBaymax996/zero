package top.cbaymax.matrix.silk.client;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import top.cbaymax.matrix.silk.client.domain.transfer.InputDTO;
import top.cbaymax.matrix.silk.client.domain.transfer.SilkDTO;
import top.cbaymax.matrix.silk.client.layer.parse.ParseLayer;

@Getter
@ComponentScan
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class SilkClient {

    private ApplicationContext springContext;

    private final String app;

    private SilkClient(String app) {
        this.app = app;
    }


    public static SilkClient getSilkClient(String app) {
        SilkClient silkClient = new SilkClient(app);
        silkClient.springContext = new AnnotationConfigApplicationContext(SilkClient.class);
        return silkClient;
    }


    public SilkDTO weaving(InputDTO input) {
        ParseLayer bean = springContext.getBean(ParseLayer.class);
        return new SilkDTO();
    }
}
