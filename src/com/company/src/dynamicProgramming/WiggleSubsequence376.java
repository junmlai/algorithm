package com.company.src.dynamicProgramming;

import java.util.Arrays;

/**
 * 376. 摆动序列
 * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为 摆动序列 。第一个差（如果存在的话）可能是正数或负数。仅有一个元素或者含两个不等元素的序列也视作摆动序列。
 *
 * 例如， [1, 7, 4, 9, 2, 5] 是一个 摆动序列 ，因为差值 (6, -3, 5, -7, 3) 是正负交替出现的。
 *
 * 相反，[1, 4, 7, 2, 5] 和 [1, 7, 4, 5, 5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
 * 子序列 可以通过从原始序列中删除一些（也可以不删除）元素来获得，剩下的元素保持其原始顺序。
 *
 * 给你一个整数数组 nums ，返回 nums 中作为 摆动序列 的 最长子序列的长度 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,7,4,9,2,5]
 * 输出：6
 * 解释：整个序列均为摆动序列，各元素之间的差值为 (6, -3, 5, -7, 3) 。
 * 示例 2：
 *
 * 输入：nums = [1,17,5,10,13,15,10,5,16,8]
 * 输出：7
 * 解释：这个序列包含几个长度为 7 摆动序列。
 * 其中一个是 [1, 17, 10, 13, 10, 16, 8] ，各元素之间的差值为 (16, -7, 3, -3, 6, -8) 。
 */
public class WiggleSubsequence376 {
    public int wiggleMaxLength(int[] nums) {
        int len = nums.length;
        if (len==0)
            return 0;

        // 以当前元素i元素为子序列的结尾时，序列长度最大的
        int[] dpPositive = new int[len]; //和上一个元素差值为正
        int[] dpNegative = new int[len]; //和上一个元素差值为负

        Arrays.fill(dpNegative,1);
        Arrays.fill(dpPositive,1);

        for (int i = 1; i < len; i++) {
            for (int j = 0; j <i ; j++) {
                if (nums[i]<nums[j]){
                    dpNegative[i] = Math.max(dpNegative[i], dpPositive[j]+1);
                }else if (nums[i]>nums[j]){
                    dpPositive[i] = Math.max(dpPositive[i], dpNegative[j]+1);
                }

            }
        }
        // 不缺定以哪个元素结尾会最大，所以就遍历找最大值
        int max =1;
        for (int i = 0; i <len ; i++) {
            max = Math.max(max,dpNegative[i]);
            max = Math.max(max,dpPositive[i]);
        }
        return max;
    }
}