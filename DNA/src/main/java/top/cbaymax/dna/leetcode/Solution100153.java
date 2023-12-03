package top.cbaymax.dna.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 统计完全子字符串
 *
 * @author 褚浩
 */
public class Solution100153 {
    public static void main(String[] args) {
        Solution100153 solution = new Solution100153();

        System.out.println(solution.countCompleteSubstrings("igigee", 2));
        System.out.println(solution.countCompleteSubstrings("bbb", 2));
    }

    public int countCompleteSubstrings(String word, int k) {
        HashMap<String, Boolean> cache = new HashMap<>();

        int count = 0;
        for (int len = k; len <= word.length(); len++) {
            int left = 0;
            while (left + len <= word.length()) {
                String substring = word.substring(left, left + len);
                if (isTotalStrWithCache(substring, k, cache)) {
                    System.out.println(substring);
                    count++;
                }
                left++;

            }
        }
        return count;
    }

    private boolean isTotalStr(String word, int k) {

        HashMap<Character, Integer> count = new HashMap<>();
        count.put(word.charAt(0), 1);
        for (int i = 1; i < word.length(); i++) {
            char c = word.charAt(i);
            int charCount = count.getOrDefault(c, 0) + 1;
            count.put(c, charCount);
            if (charCount > k) {
                return false;
            }
            if (Math.abs(word.charAt(i) - word.charAt(i - 1)) > 2) {
                return false;
            }
        }
        for (Integer value : count.values()) {
            if (value != k) {
                return false;
            }
        }
        return true;
    }

    private boolean isTotalStrWithCache(String word, int k, Map<String, Boolean> cache) {
        if (cache.containsKey(word)) {
            return cache.get(word);
        }
        boolean b = isTotalStr(word, k);
        cache.put(word, b);
        return b;
    }
}