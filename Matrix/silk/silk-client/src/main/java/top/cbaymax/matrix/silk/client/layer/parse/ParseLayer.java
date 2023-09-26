package top.cbaymax.matrix.silk.client.layer.parse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import top.cbaymax.matrix.silk.client.domain.context.SilkContext;
import top.cbaymax.matrix.silk.client.layer.AbstractLayer;


@Component
public class ParseLayer extends AbstractLayer<Parser> {


    public ParseLayer(@Autowired ApplicationContext springContext) {
        super(springContext);
    }


    public void parse(SilkContext context) {
        super.chains.forEach(parser -> parser.doParse(context));
    }
}
