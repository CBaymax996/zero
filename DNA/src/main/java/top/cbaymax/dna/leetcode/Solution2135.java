package top.cbaymax.dna.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 2135. 统计追加字母可以获得的单词数
 *
 * @author 褚浩
 */
public class Solution2135 {

    private Map<String, Long> cache = new HashMap<>();

    public static void main(String[] args) {

        Solution2135 solution = new Solution2135();
        System.out.println(solution.wordCount(new String[]{"ant", "act", "tack"}, new String[]{"tack", "act", "acti"}));
        System.out.println(solution.wordCount(new String[]{"ab", "a"}, new String[]{"abc", "abcd"}));
        System.out.println(solution.wordCount(new String[]{"mox", "bj", "rsy", "jqsh"}, new String[]{"trk", "vjb", "jkr"}));
        System.out.println(solution.wordCount(new String[]{"q", "ugqm", "o", "ar", "e"}, new String[]{"nco", "mnwhi", "tkuw", "ugmiq", "fb", "oykr", "us", "sra", "dxg", "dbp", "ql", "fq"}));
    }

    public int wordCount(String[] startWords, String[] targetWords) {

        Arrays.sort(startWords, Comparator.comparing(String::length));
        Arrays.sort(targetWords, Comparator.comparing(String::length));


        int count = 0;
        int t = 0;
        int s_left = 0;
        int s_left_len = startWords[s_left].length();
        while (t < targetWords.length) {

            String targetWord = targetWords[t];
            if (startWords[s_left].length() + 1 < targetWords[t].length()) {
                if (s_left == startWords.length - 1) {
                    break;
                }
                s_left++;
                if (startWords[s_left].length() > s_left_len) {
                    s_left_len = startWords[s_left].length();
                }
                continue;
            }


            if (startWords[s_left].length() + 1 > targetWords[t].length()) {
                t++;
                continue;
            }

            for (int i = s_left; i < startWords.length && startWords[i].length() == s_left_len; i++) {
                String startWord = startWords[i];
                if (isTrans(startWord, targetWord)) {

                    count++;
                    break;
                }
            }
            t++;


        }

        return count;
    }

    private long getBitMap(String str) {
        if (cache.containsKey(str)) {
            return cache.get(str);
        }
        long res = 0;
        for (char c : str.toCharArray()) {
            res |= 1L << (c - 'a');
        }
        cache.put(str, res);
        return res;
    }

    private boolean isTrans(String str, String target) {
        if (target.length() - str.length() != 1) {
            return false;
        }
        long targetBitMap = getBitMap(target);
        return (targetBitMap | getBitMap(str)) == targetBitMap;
    }


}