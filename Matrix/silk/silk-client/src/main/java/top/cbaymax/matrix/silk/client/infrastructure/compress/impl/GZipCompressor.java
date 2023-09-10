package top.cbaymax.matrix.silk.client.infrastructure.compress.impl;


import top.cbaymax.matrix.silk.client.infrastructure.compress.CompressedEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import top.cbaymax.matrix.silk.client.infrastructure.compress.EncodeType;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 默认压缩算法
 */
@Component
@Slf4j
public class GZipCompressor implements CompressedEncoder {


    @Override
    public String encode(String raw) {
        if (!StringUtils.hasText(raw)) {
            return raw;
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            GZIPOutputStream gzip = new GZIPOutputStream(outputStream);
            gzip.write(raw.getBytes(DEFAULT_CHARACTER_SET));
            gzip.close();
        } catch (IOException e) {
            log.error("GZip CompressedEncoder Encode Error");
            throw new RuntimeException("GZip CompressedEncoder Encode Error", e);
        }
        return outputStream.toString();
    }

    @Override
    public String decode(String compressed) {
        if (!StringUtils.hasText(compressed)) {
            return compressed;
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(compressed.getBytes(DEFAULT_CHARACTER_SET));
            GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);
            byte[] buffer = new byte[256];
            int n;
            while ((n = gzipInputStream.read(buffer)) >= 0) {
                outputStream.write(buffer, 0, n);
            }
        } catch (IOException e) {
            log.error("GZip CompressedEncoder Decode Error");
            throw new RuntimeException("GZip CompressedEncoder Decode Error", e);
        }


        return outputStream.toString(DEFAULT_CHARACTER_SET);
    }

    @Override
    public EncodeType encodeType() {
        return EncodeType.GZIP;
    }
}
