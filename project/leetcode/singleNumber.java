package qgb.project.leetcode;

import java.util.ArrayList;

import qgb.U;

public class singleNumber {

	public static void main(String[] args) {
		//U.print();
		int[] nums={1,1,2,2,3,5,5};
		
		U.print(singleNumber(nums));
	}

    public static int singleNumber(int[] nums) {
    	ArrayList<Integer> ali=new ArrayList<>();
    	for (int i = 0; i < nums.length; i++) {
    		if (ali.contains(nums[i])) {
				continue;
			}
    		if (i==nums.length-1) {
				return nums[i];
			}
			for (int j = i+1; j < nums.length; j++) {
				if (nums[i]==nums[j]) {
					ali.add(nums[i]);
					break;
				}
				if (j==nums.length-1) {
					return nums[i];
				}
			}
		}
    	
		return -1;
        
    }
}
