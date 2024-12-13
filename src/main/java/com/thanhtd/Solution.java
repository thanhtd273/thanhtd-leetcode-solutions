package com.thanhtd;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {
        List<List<Integer>> l1 = new LinkedList<>();
        List<Integer> t1 = new LinkedList<>();
        t1.add(9);
        l1.add(t1);
        List<List<Integer>> l2 = new LinkedList<>();
        List<Integer> t2 = new LinkedList<>();
        t2.add(20);
        l2.add(t2);
        List<Integer> temp = new LinkedList<>();
        for (List<Integer> integers : l1) {
            temp.addAll(integers);
        }
        for (List<Integer> integers : l2) {
            temp.addAll(integers);
        }
        System.out.println(temp);
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        List<Integer> list = new LinkedList<>();
        list.add(root.val);
        res.add(list);
        List<List<Integer>> left = levelOrder(root.left);
        List<List<Integer>> right = levelOrder(root.right);
        left.addAll(right);
        List<Integer> temp = new ArrayList<>();
        if (!left.isEmpty()) {
            temp.add(left.get(0).get(0));
        }
        if (!right.isEmpty()) {
            temp.add(right.get(0).get(0));
        }

        if (!temp.isEmpty()) {
            res.add(temp);
        }


        return res;
    }

    public static int[][] kClosest(int[][] points, int k) {
        Arrays.sort(points, (p1, p2) -> p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1]);
        return Arrays.copyOfRange(points, 0, k);
    }

    public static int[][] insertInterval(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();
        int l = intervals.length;
        if (l == 0)
            return new int[][] {newInterval};
        else if (intervals[0][0] > newInterval[1]) {
            list.add(newInterval);
            Collections.addAll(list, intervals);
        }
        else if (intervals[l - 1][1] < newInterval[0]) {
            Collections.addAll(list, intervals);
            list.add(newInterval);
        } else {
            int i = 0;
            while (i < l && intervals[i][1] < newInterval[0]) {
                list.add(intervals[i]);
                i ++;
            }
            if (overlap(intervals[i], newInterval)) {
                int[] merged = newInterval;
                while (i < l && overlap(intervals[i], merged)) {
                    merged = mergeInterval(merged, intervals[i]);
                    i ++;
                }

                list.add(merged);
                while (i < l) {
                    list.add(intervals[i]);
                    i ++;
                }
            } else {
                list.add(newInterval);
                while (i < l) {
                    list.add(intervals[i]);
                    i ++;
                }
            }
        }

        for (int j = 0; j < list.size(); j ++) {
            System.out.println(Arrays.toString(list.get(j)));
        }
        return list.toArray(new int[list.size()][]);
    }

    private static boolean overlap(int[] a, int[] b) {
        return ! (b[1] < a[0] || b[0] > a[1]);
    }

    private static int[] mergeInterval(int[] a, int[] b) {
        return new int[] {Math.min(a[0], b[0]), Math.max(a[1], b[1])};
    }

    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i ++) {
            for (int j = i + 1; j < nums.length; j ++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[] {};
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(candidates, target, 0, new ArrayList<>(), 0, res);
        return res;
    }

    private static void dfs(int[] candidates, int target, int  i, List<Integer> cur, int total, List<List<Integer>> res) {
        if (total == target) {
            res.add(new ArrayList<>(cur));
            return;
        }
        if (i >= candidates.length || total > target)
            return;

        cur.add(candidates[i]);
        dfs(candidates, target, i + 1, cur, total + candidates[i], res);
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backTracking(candidates, target, 0,  new ArrayList<>(), 0, res);

        return res;
    }

    private static void backTracking(int[] candidates, int target, int  i, List<Integer> cur, int total, List<List<Integer>> res) {
        if (total == target) {
            res.add(new ArrayList<>(cur));
            return;
        }
        if (i >= candidates.length || total > target) {
            return;
        }

        cur.add(candidates[i]);
        backTracking(candidates, target, i, cur, total + candidates[i], res);
        cur.remove(cur.size() - 1);
        backTracking(candidates, target, i + 1,  cur, total, res);
    }

    private static int sumOfList(List<Integer> arr) {
        int sum = 0;
        for (Integer integer : arr) {
            sum += integer;
        }
        return sum;
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 1) {
            List<Integer> temp = Arrays.stream(nums).boxed().collect(Collectors.toList());
            res.add(temp);
            return res;
        }

        for (int i = 0; i < nums.length; i ++) {
            int n = nums[0];
            nums = Arrays.copyOfRange(nums, 1, nums.length);
            List<List<Integer>> perms = permuteUnique(nums);
            for (List<Integer> perm: perms) {
                perm.add(n);
            }
            res.addAll(perms);
            nums = append(nums, n);
        }
        return new ArrayList<>(new HashSet<>(res));
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 1) {
            List<Integer> temp = Arrays.stream(nums).boxed().collect(Collectors.toList());
            res.add(temp);
            return res;
        }

        for (int i = 0; i < nums.length; i ++) {
            int n = nums[0];
            nums = Arrays.copyOfRange(nums, 1, nums.length);
            List<List<Integer>> perms = permuteUnique(nums);
            for (List<Integer> perm: perms) {
                perm.add(n);
            }
            res.addAll(perms);
            nums = append(nums, n);
        }
        return res;
    }

    private static int[] append(int[] arr, int n) {
        int[] res = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i ++) {
            res[i] = arr[i];
        }
        res[arr.length] = n;
        return res;
    }

    public static int maxProfit(int[] prices) {
        int buy = prices[0];
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < buy) {
                buy = prices[i];
            } else if (prices[i] - buy > profit) {
                profit = prices[i] - buy;
            }
        }
        return profit;
    }

    public int maxProfitII(int[] prices) {
        int max = 0;
        int start = prices[0];
        int len = prices.length;
        for(int i = 1;i<len; i++){
            if(start < prices[i]) max += prices[i] - start;
            start = prices[i];
        }
        return max;
    }
}