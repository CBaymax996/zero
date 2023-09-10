package top.cbaymax.matrix.silk.client.layer.parse;

import top.cbaymax.matrix.silk.client.domain.context.SilkContext;
import top.cbaymax.matrix.silk.client.layer.Handler;

public interface Parser extends Handler {


    void doParse(SilkContext context);

}
