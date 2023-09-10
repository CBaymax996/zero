package top.cbaymax.dna.compress;

/**
 * LZ77算法的Java实现
 */
public class LZ77 {
    /**
     * 编码时的特殊标志位
     */
    static char flag = 0xffff;
    /**
     * 最小压缩长度，如果重复的字符串长度小于该值，没有压缩价值
     */
    static int min_compress_len = 5;
    /**
     * 滑动窗口大小，一般为4KB（4096 Bytes)
     */
    static int window_size = 4096;
    /**
     * 前向缓冲区大小，一般为100Bytes
     */
    static int ahead_size = 1024;



    /**
     * 压缩
     */
    static String compress(String data) {
        int cursor = -1;
        StringBuilder compressed = new StringBuilder();

        while (cursor < data.length() - 1) {
            // [startOfBuffer, cursor] [cursor+1,     endOfAhead]
            int startOfBuffer = Math.max(0, cursor - window_size);
            int endOfAhead = Math.min(cursor + ahead_size, data.length() - 1);

            int p = 0, l = 0;
            char c = data.charAt(cursor + 1);
            // 寻找最长匹配子串
            for (int i = startOfBuffer; i < cursor + 1; i++) {
                int len = 0;
                while (cursor + 1 + len < endOfAhead && data.charAt(i + len) == data.charAt(cursor + 1 + len)) {
                    len++;
                }
                if (len > min_compress_len && len >= l) {
                    // p是相对cursor的位置，i是绝对位置
                    p = cursor - i + 1;
                    l = len;
                    c = data.charAt(cursor + 1 + len);
                }
            }
            encodeBlock(p, l, c, compressed);
            cursor += l + 1;
        }
        return compressed.toString();
    }


    /**
     * 解压
     */
    static String decompress(String data) {
        StringBuilder raw = new StringBuilder();
        int cursor = 0;
        while (cursor < data.length()) {
            if (data.charAt(cursor) == flag) {
                int p = decodeInt(data.charAt(cursor + 1), data.charAt(cursor + 2));
                int l = decodeInt(data.charAt(cursor + 3), data.charAt(cursor + 4));
                decodeBlock(p, l, raw);
                cursor += 5;
            }
            raw.append(data.charAt(cursor));
            cursor++;
        }
        return raw.toString();
    }

    private static void encodeBlock(int p, int l, char c, StringBuilder output) {
        if (output == null) {
            throw new RuntimeException("output is null");
        }
        if (p != 0 && l != 0) {
            output.append(flag);
            output.append((char) (p >> 16));
            output.append((char) (p & 0x0000FFFF));
            output.append((char) (l >> 16));
            output.append((char) (l & 0x0000FFFF));
        }
        output.append(c);
    }

    private static void decodeBlock(int p, int l, StringBuilder output) {
        // case1 未压缩
        if (p == 0) {
            return;
        }
        int length = output.length();
        // case2 循环压缩情况
        if (p <= l) {
            for (int i = 0; i < l; i++) {
                output.append(output.charAt(length - p + i % p));
            }
            return;
        }
        // case3 普通压缩情况
        for (int i = 0; i < l; i++) {
            output.append(output.charAt(length - p + i));
        }
    }

    private static int decodeInt(char high, char low) {
        return ((int) high << 16) + ((int) low);
    }
}