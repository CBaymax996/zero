package top.cbaymax.matrix.silk.client.layer.parse;

import top.cbaymax.matrix.silk.client.layer.AbstractLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import top.cbaymax.matrix.silk.client.domain.context.SilkContext;


@Component
public class ParseLayer extends AbstractLayer<Parser> {


    public ParseLayer(@Autowired ApplicationContext springContext) {
        super(springContext);
    }


    public void parse(SilkContext context) {
        getChains().forEach(parser -> parser.doParse(context));
    }
}
