package com.learning.dp;

import java.util.Arrays;

public class Jumping {

    public static void main(String arg[]) {
        Jumping jumping = new Jumping();
        int[] nums = {3,2,1,1,4};
        jumping.miniStepsToJumpToEnd(nums);
    }

    public void miniStepsToJumpToEnd(int[] nums) {
        int[] dp = new int[nums.length];
        int result =  miniStepsFromIndex(nums, 0, dp);
        Arrays.stream(dp).forEach(System.out::println); // dynamically calculated decisions
        System.out.println("Minimum steps needed to jump to end of array "+ result);
    }

    public int miniStepsFromIndex(int[] nums, int i, int[] dp){
        if(i==nums.length-1)
            return 0    ;

        if(nums[i] == 0) {
            dp[i] = 0;
            return Integer.MAX_VALUE;
        }

        int minSteps = Integer.MAX_VALUE;
        int last = Math.min(i+nums[i], nums.length-1);

        for(int j = i+1; j <= last; j++) {
            minSteps = (dp[j] > 0)
                        ? Math.min(minSteps, dp[j]) //added to not make decisions from already visited item
                        : Math.min(minSteps, miniStepsFromIndex(nums, j, dp));
        }

        return dp[i] = (minSteps==Integer.MAX_VALUE ? Integer.MAX_VALUE : minSteps+1);
    }

    /*DP calculate each and every further movement
    * and decide if we can reach the end of the array*/
    public boolean canJump(int[] nums) {
        return canJumpFromIndex(nums, 0);
    }

    public boolean canJumpFromIndex(int[] nums, int i) {
        if(i == nums.length -1)
            return true;
        int last = Math.min(i+nums[i], nums.length-1);
        for(int j= i+1; j<=last; j++){
            if(canJumpFromIndex(nums,j))
                return true;
        }
        return false;
    }
}
