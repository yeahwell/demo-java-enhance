package com.yeahwell.acm.leecode.demo01to27;

import java.util.HashMap;

public class Solution {

	public static int[] twoSum(int[] nums, int target) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int[] result = new int[2];
		for (int i = 0; i < nums.length; i++) {
			map.put(nums[i], i);
		}
		for (int i = 0; i < nums.length; i++) {
			int searched = target - nums[i];
			if (map.containsKey(searched) && map.get(searched) != i) {
				int index = map.get(searched);
				if (index < i) {
					result[0] = map.get(searched) + 1;
					result[1] = i + 1;
				} else {
					result[0] = i + 1;
					result[1] = map.get(searched) + 1;
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {

	}

}
