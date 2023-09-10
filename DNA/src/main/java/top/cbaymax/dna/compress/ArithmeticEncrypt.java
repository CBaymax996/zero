package top.cbaymax.dna.compress;



import top.cbaymax.dna.compress.util.CollectionUtil;

import java.util.*;
import java.util.regex.Pattern;

/**
 * 算数编码的简单实现
 * <p>
 * 该实现参考了网络上的一些算数编码的原理，受限于Double的最小精度{@link Double#MIN_VALUE}，可能会发生数值下溢，仅作学习讨论
 */
public class ArithmeticEncrypt {

    /**
     * 纯小数二进制字符串正则匹配，用以参数验证
     */
    private final static Pattern pure_decimal_regex = Pattern.compile("^0.[0-1]+$");


    /**
     * 编码
     * <p>
     * 不传频数字典，默认使用{@link  ArithmeticEncrypt#getFrequentMap(String)}生成字典
     *
     * @param raw 原始字符串
     * @return 编码后的字符串 这里返回二进制小数
     */
    public static String encoding(String raw) {
        Map<Character, Integer> frequentMap = ArithmeticEncrypt.getFrequentMap(raw);
        return ArithmeticEncrypt.encoding(raw, frequentMap);
    }

    public static String encoding(String rawString, Map<Character, Integer> frequency) {
        if (CollectionUtil.isEmpty(frequency)) {
            return null;
        }
        Map<Character, Range> charRanges = generateRangeMap(frequency);
        System.out.println(("对应频率区间: " + charRanges));

        // 初始化目标区间 (0，1)
        Range aimInterval = new Range(0, 1);
        for (int i = 0; i < rawString.length(); i++) {
            char c = rawString.charAt(i);
            Range cRange = charRanges.get(c);
            // 当前区间长度
            double length = aimInterval.right - aimInterval.left;
            // 从目标区间里按照比例取一段区间，作为新的目标区间
            aimInterval = new Range(aimInterval.left + length * cRange.left, aimInterval.left + length * cRange.right);
        }

        System.out.println(("最终目标区间: " + aimInterval));
        String zipString = aimInterval.choseShortestBinaryNumber();
        System.out.println("选择区间最短二进制表示：" + zipString);
        return zipString;
    }


    /**
     * 解码
     *
     * @param zipString 压缩字符串，必须为以"0."开头的二进制纯小数
     * @param frequency 解码一定要指定字典，这里传入频数分布，解码过程依赖于频数对应的字符个数，因为无法从压缩的二进制小数确定实际精度
     * @return 解压后的字符串
     */
    public static String decode(String zipString, Map<Character, Integer> frequency) {
        // 参数检查，压缩字符串必须是二进制纯小数
        if (!pure_decimal_regex.matcher(zipString).matches() || CollectionUtil.isEmpty(frequency)) {
            throw new IllegalArgumentException();
        }
        // 保存最终解压结果
        StringBuilder rawString = new StringBuilder();

        double aiming = ArithmeticEncrypt.pureDecimalToDouble(zipString);

        int total = frequency.values().stream().reduce(Integer::sum).orElseThrow();
        Map<Character, Range> charRanges = ArithmeticEncrypt.generateRangeMap(frequency);

        Range currentRange = new Range(0, 1);


        while (rawString.length() < total) {
            // 寻找目标子区间
            for (Map.Entry<Character, Range> entry : charRanges.entrySet()) {
                Range cRange = entry.getValue();
                double length = currentRange.right - currentRange.left;
                double nextLeft = currentRange.left + length * cRange.left;
                double nextRight = currentRange.left + length * cRange.right;
                // 编码值在当前子区间内
                if (nextLeft <= aiming && aiming < nextRight) {
                    rawString.append(entry.getKey());
                    currentRange = new Range(currentRange.left + length * cRange.left, currentRange.left + length * cRange.right);
                    break;
                }
            }
        }

        return rawString.toString();
    }


    /**
     * 获取输入字符串的字符频数字典
     *
     * @param str 字符串
     * @return 对应字符的频数Map
     */
    public static Map<Character, Integer> getFrequentMap(String str) {
        if (str == null) {
            return Collections.emptyMap();
        }
        Map<Character, Integer> frequencyMap = new HashMap<>(64);
        for (int i = 0; i < str.length(); i++) {
            Character c = str.charAt(i);
            if (frequencyMap.containsKey(c)) {
                frequencyMap.put(c, frequencyMap.get(c) + 1);
            } else {
                frequencyMap.put(c, 1);
            }
        }
        return frequencyMap;
    }

    /**
     * 计算频数分布区间
     *
     * @param frequentMap 频数分布Map
     * @return 区间Map
     */
    private static Map<Character, Range> generateRangeMap(Map<Character, Integer> frequentMap) {

        double sum = frequentMap.values().stream().reduce(Integer::sum).orElse(0);

        HashMap<Character, Range> rangMap = new HashMap<>(frequentMap.size());

        double left = 0;
        for (Map.Entry<Character, Integer> entry : frequentMap.entrySet()) {
            double value = entry.getValue();
            double right = value / sum + left;
            Range interval = new Range(left, right);
            rangMap.put(entry.getKey(), interval);
            left = right;
        }
        return rangMap;
    }

    /**
     * 纯小数转换为二进制字符串
     */
    private static String pureDecimalToBinaryString(double num) {
        if (num > 1 || num < 0) {
            throw new RuntimeException("只能转换纯小数!");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0.");
        do {
            num = num * 2;
            stringBuilder.append(((int) num));
            num -= (int) num;
        } while (num > Double.MIN_NORMAL);

        return stringBuilder.toString();
    }

    /**
     * 二进制纯小数转换为十进制浮点数
     */
    private static double pureDecimalToDouble(String number) {
        double n = 0;
        double mod = 1;
        // 从小数点后一位开始
        for (int i = number.indexOf(".") + 1; i < number.length(); i++) {
            mod = mod / 2;
            if (number.charAt(i) == '1') {
                n += mod;
            }
        }
        return n;
    }


    /**
     * 区间
     */
    private static class Range {
        /**
         * 左端点
         */
        double left;
        /**
         * 右端点
         */
        double right;

        public Range(double left, double right) {
            this.left = left;
            this.right = right;
            if (right - left < Double.MIN_NORMAL) {
                throw new ArithmeticException("over the accuracy of double");
            }
        }

        @Override
        public String toString() {
            return "(" + left + ", " + right + ']';
        }

        /**
         * 根据左右端点选择最短二进制值
         */
        public String choseShortestBinaryNumber() {
            String left = ArithmeticEncrypt.pureDecimalToBinaryString(this.left);
            String right = ArithmeticEncrypt.pureDecimalToBinaryString(this.right);
            StringBuilder binaryString = new StringBuilder();
            for (int i = 0; i < Math.max(left.length(), right.length()); i++) {
                char c1 = i > left.length() ? '0' : left.charAt(i);
                char c2 = i > right.length() ? '0' : right.charAt(i);
                // 一直取到第一个不相同的数为止
                if (c1 == c2) {
                    binaryString.append(c1);
                } else {
                    binaryString.append("1");
                    break;
                }
            }
            return binaryString.toString();
        }
    }
}
