package top.cbaymax.matrix.silk.client.infrastructure.compress;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public interface Encoder {
    /**
     * 编码器默认字符集
     */
    Charset DEFAULT_CHARACTER_SET = StandardCharsets.UTF_8;

    /**
     * 编码
     *
     * @param raw 原始字符串
     * @return 编码后的字符串
     */
    String encode(String raw) ;


    /**
     * 解码
     *
     * @param code 编码字符串
     * @return 原始字符串
     */
    String decode(String code) ;

    EncodeType encodeType();
}
