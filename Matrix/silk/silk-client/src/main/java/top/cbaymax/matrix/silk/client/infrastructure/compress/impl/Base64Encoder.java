package top.cbaymax.matrix.silk.client.infrastructure.compress.impl;

import top.cbaymax.matrix.silk.client.infrastructure.compress.EncodeType;
import top.cbaymax.matrix.silk.client.infrastructure.compress.Encoder;

import java.util.Base64;

/**
 * Base64 编码器
 * <p>
 * 传输过程中有一些组件不支持特殊字符串的传输，使用该编码器会降低压缩率
 */
public class Base64Encoder implements Encoder {
    @Override
    public String encode(String raw) {
        return new String(Base64.getEncoder().encode(raw.getBytes(DEFAULT_CHARACTER_SET)));
    }

    @Override
    public String decode(String code) {
        return new String(Base64.getDecoder().decode(code.getBytes(DEFAULT_CHARACTER_SET)));
    }

    @Override
    public EncodeType encodeType() {
        return EncodeType.BASE64;
    }
}
