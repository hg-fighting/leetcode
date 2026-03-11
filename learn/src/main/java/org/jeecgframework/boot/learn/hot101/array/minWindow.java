package org.jeecgframework.boot.learn.hot101.array;

/**
 * @Author: hao gang
 * @Date: 2026/3/3  8:25
 * @Description: 最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 注意：
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 */
public class minWindow {

    //检查是否有小于0的
    boolean check(int[] hash) {
        for (int j : hash) {
            if (j < 0) {
                return false;
            }
        }
        return true;
    }

    public String minWindow(String S, String T) {
        int cnt = S.length() + 1;
        //记录目标字符串T的字符个数
        int[] hash = new int[128];
        for (int i = 0; i < T.length(); i++)
            //初始化哈希表都为负数，找的时候再加为正
        {
            hash[T.charAt(i)] -= 1;
        }
        int slow = 0, fast = 0;
        //记录左右区间
        int left = -1, right = -1;
        for (; fast < S.length(); fast++) {
            char c = S.charAt(fast);
            //目标字符匹配+1
            hash[c]++;
            //没有小于0的说明都覆盖了，缩小窗口
            while (check(hash)) {
                //取最优解
                if (cnt > fast - slow + 1) {
                    cnt = fast - slow + 1;
                    left = slow;
                    right = fast;
                }
                c = S.charAt(slow);
                //缩小窗口的时候减1
                hash[c]--;
                //窗口缩小
                slow++;
            }
        }
        //找不到的情况
        if (left == -1) {
            return "";
        }
        return S.substring(left, right + 1);
    }
}
