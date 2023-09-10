package top.cbaymax.matrix.silk.client.config;

import top.cbaymax.matrix.silk.client.infrastructure.sw.annotation.SwitchConfig;
import top.cbaymax.matrix.silk.client.infrastructure.sw.annotation.SwitchField;

import java.util.List;

@SwitchConfig
public class GlobalSwitch {


    @SwitchField(value = "debug.enable")
    public static Boolean debugEnable = Boolean.TRUE;


    @SwitchField(value = "debug.demo.list")
    public static List<String> list = List.of("123", "456");
}
