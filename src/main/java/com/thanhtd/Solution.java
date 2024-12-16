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
    public static boolean isValidBST(TreeNode root) {

        return isValidBST(root, root.val);
    }

    public static boolean isValidBST(TreeNode tree, int rootVal) {
        if (tree == null)
            return true;
        if (tree.left == null && tree.right == null)
            return true;
        if (tree.left != null && tree.right == null)
            return tree.left.val < tree.val && tree.left.val < rootVal;
        if (tree.left == null)
            return tree.right.val > tree.val && tree.right.val > rootVal;

        if (tree.left.val < tree.val && tree.right.val > tree.val) {
            return isValidBST(tree.left, rootVal) && isValidBST(tree.right, rootVal);
        }
        return false;
    }

    public static int[] productExceptSelf(int[] nums) {
        int l = nums.length;
        int[] res = new int[l];
        int[] prefix = new int[l];
        int[] postfix = new int[l];
        for (int i = 0; i < l; i ++) {
            if (i == 0) {
                prefix[i] = nums[i];
            } else {
                prefix[i] = nums[i] * prefix[i - 1];
            }
        }
        for (int i = l - 1; i >= 0; i --) {
            if (i == l - 1) {
                postfix[i] = nums[i];
            } else {
                postfix[i] = nums[i] * postfix[i + 1];
            }
        }

        for (int i = 0; i < l; i ++) {
            if (i == 0) {
                res[i] = postfix[i + 1];
            } else if (i == l - 1) {
                res[i] = prefix[i - 1];
            } else {
                res[i] = prefix[i - 1] * postfix[i + 1];
            }
        }

        return res;
    }

    // TODO
    public static int coinChange(int[] coins, int amount) {
        if (amount == 0)
            return 0;
        int count = 0;
        Arrays.parallelSort(coins);
        int i = coins.length - 1;
        while (i >= 0) {
            if (amount >= coins[i]) {
                count ++;
                amount -= coins[i];
                if (amount == 0)
                    return count;
                continue;
            }
            i --;

        }
        return -1;
    }

    // TODO
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        int count = 0;
        int i = 0;
        while (count <= numCourses) {
            int finish = prerequisites[i][1];
            count ++;
            i = finish;
            if (count > numCourses)
                return false;
            if (count == numCourses)
                return true;
        }
        return true;
    }

    public static int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        int result = Integer.parseInt(tokens[0]);
        for (String token : tokens) {
            if ("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token)) {
                int a = Integer.parseInt(stack.pop());
                int b = Integer.parseInt(stack.pop());

                switch (token) {
                    case "+":
                        result = a + b;
                        break;
                    case "-":
                        result = b - a;
                        break;
                    case "*":
                        result = a * b;
                        break;
                    case "/":
                        result = b / a;
                        break;
                }
                stack.push(String.valueOf(result));
            } else {
                stack.push(token);
            }
        }
        return result;
    }

    public static int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            max = Math.max(sum, max);
            if (sum < 0)
                sum = 0;
        }
        return max;
    }


    private static int sumOfArray(int[] arr) {
        int sum = 0;
        for (int j : arr) {
            sum += j;
        }
        return sum;
    }

    public static boolean isAnagram(String s, String t) {
        for (int i = 0; i < t.length(); i ++) {
            String c = String.valueOf(t.charAt(i));
            if (!s.contains(c))
                return false;
            s = s.replaceFirst(c, "");
        }
        return s.isEmpty();
    }

    public static TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        if (root.left == null && root.right == null) {
            return root;
        } else if (root.left == null) {
            root.left = invertTree(root.right);
            root.right = null;
        } else if (root.right == null) {
            root.right = invertTree(root.left);
            root.left = null;
        } else {
            TreeNode temp = root.right;
            root.right = invertTree(root.left);
            root.left = invertTree(temp);
        }

        return root;
    }

    public static int searchInsert(int[] nums, int target) {
        int i = 0;
        while (i < nums.length && nums[i] < target) {
            i ++;
        }
        return i;
    }

    // TODO
    public static int longestValidParentheses(String s) {
        int longest = 0;
        int length = s.length();
        for (int i = 0; i < length; i ++) {
            if (s.charAt(i) == ')')
                continue;
            int max = 0;
            for (int j = length; j > i; j --) {
                if (s.charAt(j - 1) == '(')
                    continue;
                String str = s.substring(i, j);
                if (isValid(str)) {
                    max = str.length();
                    break;
                }
            }
            if (max > longest) {
                longest = max;
            }
        }
        return longest;
    }

    public static void nextPermutation(int[] nums) {
        int n = nums.length, i = n - 2;

        while (i >= 0 && nums[i] >= nums[i + 1])
            i--;

        if (i >= 0) {
            int j = n - 1;

            while (nums[j] <= nums[i])
                j--;

            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        reverse(nums, i + 1, n - 1);
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    private static boolean isExist(List<List<Integer>> list, List<Integer> item) {
        for (List<Integer> element: list) {
            if (compareUnSortedList(element, item)) {
                return true;
            }
        }
        return false;
    }

    private static boolean compareUnSortedList(List<Integer> l1, List<Integer> l2) {
        if (l1.size() != l2.size()) {
            return false;
        }
        int size = l1.size();
        Collections.sort(l1);
        Collections.sort(l2);
        for (int i = 0; i < size; i ++) {
            if (!Objects.equals(l1.get(i), l2.get(i))) return false;
        }

        return true;
    }

    public static boolean isValid(String s) {
        List<Character> queue = new ArrayList<>();
        for (int i = 0; i < s.length(); i ++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
                queue.add(s.charAt(i));
                continue;
            }
            if (queue.isEmpty()) return false;
            if ((queue.get(queue.size() - 1) == '{' && s.charAt(i) == '}') || (queue.get(queue.size() - 1) == '[' && s.charAt(i) == ']') || (queue.get(queue.size() - 1) == '(' && s.charAt(i) == ')')) {
                queue.remove(queue.size() - 1);
            } else {
                return false;
            }
        }
        return queue.isEmpty();
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        subsets_dfs(nums, subset, 0, result);
        return result;
    }

    private static void subsets_dfs(int[] nums, List<Integer> subset, int i, List<List<Integer>> result) {
        result.add(new ArrayList<>(subset));
        for (int j = i; j < nums.length;j ++) {
            subset.add(nums[j]);
            subsets_dfs(nums, subset, j + 1, result);
            subset.remove(subset.size() - 1);
        }
    }

    public static String countAndSay(int n) {
        String base = "1";
        if (n == 1) return base;
        return runLength(countAndSay(n - 1));
    }

    private static String runLength(String str) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while ( i < str.length()) {
            int count = 0;
            for (int j = i; j < str.length(); j ++) {
                if (str.charAt(i) != str.charAt(j)) {
                    break;
                }
                count ++;
            }
            sb.append(count).append(str.charAt(i));
            i += count;
        }
        return sb.toString();
    }

    public static List<String> findPermutations(String str1, String str2) {
        List<String> list = new ArrayList<>();
        list.add(str1.concat(str2));
        list.add(str2.concat(str1));
        return list;
    }

    public static int strStr(String haystack, String needle) {
        if (haystack.isEmpty())
            return -1;

        int count = needle.length();
        int i = 0;
        while (i < haystack.length()) {
            int start = i, end = i + count;
            if (end > haystack.length()) {
                end = haystack.length() - 1;
            }
            String str = haystack.substring(start, end);
            if (str.equals(needle)) {
                return i;
            }
            i ++;
        }
        return -1;
    }

    public static int removeDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num: nums) {
            if (!list.contains(num)) {
                list.add(num);
            }
        }
        int i = 0;
        for (int num: list) {
            nums[i] = num;
            i ++;
        }
        return list.size();
    }

    public static int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        if (dividend == Integer.MIN_VALUE && divisor == 1) return Integer.MIN_VALUE;

        boolean negative = (dividend < 0) ^ (divisor < 0);

        long absDividend = Math.abs((long) dividend);
        long absDivisor = Math.abs((long) divisor);

        int quotient = 0;

        while (absDividend >= absDivisor) {
            long tempDivisor = absDivisor, multiple = 1;
            while (absDividend >= (tempDivisor << 1)) {
                tempDivisor <<= 1;
                multiple <<= 1;
            }
            absDividend -= tempDivisor;
            quotient += multiple;
        }

        return negative ? -quotient : quotient;
    }

    public static int maxArea(int[] height) {
        int max = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            int currentArea = (right - left) * Math.min(height[left], height[right]);
            if (currentArea > max) {
                max = currentArea;
            }
            if (height[left] < height[right]) {
                left ++;
            } else {
                right --;
            }
        }
        return max;
    }

    public static boolean isMatch(String s, String p) {
        if ("*".equals(p)) return true;
        if ("".equals(p) || "".equals(s)) return false;
        char sHead = s.charAt(0), pHead = p.charAt(0);
        if (pHead == '.' || sHead == pHead)
            return isMatch(s.substring(1), p.substring(1));
        return false;
    }

    public static boolean isPalindrome(int x) {
        String str = String.valueOf(x);
        int i = 0, j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i ++) != str.charAt(j --))
                return false;
        }

        return true;
    }

    public static String convert(String s, int numRows) {
        if (numRows == 1) return s;
        String[] list = new String[numRows];
        int count = 0;
        boolean direct = true;
        for (int i = 0; i < s.length(); i ++) {
            list[count] = list[count] == null ? Character.toString(s.charAt(i)) : String.format("%s%s", list[count], s.charAt(i));
            if (direct) {
                count ++;
            } else {
                count --;
            }
            if (count == numRows - 1) {
                direct = false;
            }
            if (count == 0) {
                direct = true;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String str: list) {
            if (str != null)
                sb.append(str);
        }
        return sb.toString();
    }

    public static int lengthOfLongestSubstring(String s) {
        int max = 0;

        for (int i = 0; i < s.length(); i ++) {
            StringBuilder sb = new StringBuilder();
            sb.append(s.charAt(i));
            for (int j = i + 1; j < s.length(); j ++) {
                String s1 = Character.toString(s.charAt(j));
                if (sb.indexOf(s1) == -1) {
                    sb.append(s1);
                } else {
                    break;
                }
            }
            if (sb.length() > max) {
                max = sb.length();
            }
        }
        return max;
    }

    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.isEmpty()) return result;
        Map<Character, String> map = getMap();

        if (digits.length() == 1) {
            String letters = map.get(digits.charAt(0));
            for (int i = 0; i < letters.length(); i ++) {
                result.add(String.format("%s", letters.charAt(i)));
            }
            return result;
        }

        List<String> arr = new ArrayList<>();
        for (int i = 0; i < digits.length(); i ++) {
            String letters = map.get((digits.charAt(i)));
            arr.add(letters);
        }
        result.addAll(combine(arr.get(0), arr.get(1)));

        for (int i = 2; i < arr.size(); i ++) {
            result = combine(result, arr.get(i));
        }
        return result;
    }

    private static List<String> combine(List<String> list, String str) {
        List<String> result = new ArrayList<>();
        for (String s : list) {
            for (int j = 0; j < str.length(); j ++) {
                result.add(String.format("%s%s", s, str.charAt(j)));
            }
        }
        return result;
    }

    private static List<String> combine(String str1, String str2) {
        int l1 = str1.length();
        int l2 = str2.length();
        List<String> result = new ArrayList<>();
        for (int i = 0; i < l1; i ++) {
            for (int j = 0; j < l2; j ++) {
                result.add(String.format("%s%s", str1.charAt(i), str2.charAt(j)));
            }
        }
        return result;
    }

    private static Map<Character, String> getMap() {
        Map<Character, String> digitsToLettersMap = new HashMap<>();
        digitsToLettersMap.put('2', "abc");
        digitsToLettersMap.put('3', "def");
        digitsToLettersMap.put('4', "ghi");
        digitsToLettersMap.put('5', "jkl");
        digitsToLettersMap.put('6', "mno");
        digitsToLettersMap.put('7', "qprs");
        digitsToLettersMap.put('8', "tuv");
        digitsToLettersMap.put('9', "wxyz");
        return digitsToLettersMap;
    }

    private static int removeElement(int[] nums, int val) {
        int k = 0;
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            if (val == nums[i]) {
                for (int m = i; m < j; m ++) {
                    nums[m] = nums[m + 1];
                }
                nums[j --] = val;
            } else {
                k ++;
                i ++;
            }
        }
        System.out.println(Arrays.toString(nums));
        return k;
    }

    private static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] output = new int[m + n];
        int i = 0, j = 0, k = 0;
        while ( i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                output[k ++] = nums1[i ++];
            } else {
                output[k ++] = nums2[j ++];
            }
        }
        while (i < m) {
            output[k ++] = nums1[i ++];
        }
        while (j < n) {
            output[k ++] = nums2[j ++];
        }
        for (i = 0; i < m + n; i ++) {
            nums1[i] = output[i];
        }
        System.out.println(Arrays.toString(nums1));
    }

    // TODO
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