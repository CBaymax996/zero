package top.cbaymax.matrix.silk.client.layer;

public interface Handler {
    /**
     * 接口优先级，从小到大执行
     *
     * @return 默认为100
     */
    default int order() {
        return 100;
    }
}
