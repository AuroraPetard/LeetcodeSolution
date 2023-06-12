### 数组

- 704  简单 二分查找 

-  给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
  链接：https://leetcode.cn/problems/binary-search

- ```java
  // 有序数组 查找存在 第一考虑二分查找
  class Solution {
      public int search(int[] nums, int target) {
          int left=0;
          int right=nums.length-1;
  
          while(left<=right){
              // 避免溢出
              int mid=left+(right-left)/2;
  
              if(nums[mid]==target){
                  return mid;
              }else if(nums[mid]>target){
                  // 如果中间值大于target 表明应该去左边找
                  // 所以应该 right=mid-1
                  right=mid-1;
              }else{
                  // 小于往右边找
                  left=mid+1;
              }
          }
          // 找不到 返回-1
          return -1;
      }
  }
  ```

  

- 35 简单 搜索插入位置

- 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

  请必须使用时间复杂度为 O(log n) 的算法
  链接：https://leetcode.cn/problems/search-insert-position

- ```java
  class Solution {
      public int searchInsert(int[] nums, int target) {
          // 排序数组 目标值 ologn 二分查找
          int left=0;
          int right=nums.length-1;
          while(left<=right){
              int mid=(left+right)/2;
              if(nums[mid]==target){
                  return mid;
              }else if(nums[mid]>target){
                  right=mid-1;
              }else{
                  left=mid+1;
              }
          }
          // 一共四种情况 
          // 1 在其中 上面已经 返回return mid了
          // 不在其中  分为下面三种情况
          // 小于最小值 
          // 大于最大值
          // 中间某个值附近
  
          return left;
      }
  }
  ```

- 34 中等 在排序数组中查找元素的第一个和最后一个位置

- 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。

  如果数组中不存在目标值 target，返回 [-1, -1]。

  你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
  链接：https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array

- ```java
  class Solution {
      public int[] searchRange(int[] nums, int target) {
          // 排序数组 不用想了 二分查找没跑了
          // 先去找 排序数组 二分查找  
          // 找得到 再继续 
          // 找不到 返回-1 -1
          int index=BinarySearch(nums,target);
          if(index==-1){
              return new int[]{-1,-1};
          }
          
          // 初始化两个以二分查找结果的变量 
          // 然后用这两个变量往左右扩散 寻找开始位置 结束位置
          int left=index;
          int right=index;
  
          // 此处用while 一直往左右两边找 符合条件的值
          // 注意区间范围
          while(left-1>=0 && nums[left-1]==target){
              left--;
          }
  
          while(right+1 <nums.length && nums[right+1]==target){
              right++;
          }
  
  
          // 即使最后二分查找的结果 也是对的
          return new int[]{left,right};
  
      }
  
      public int BinarySearch(int[] nums,int target){
          // 模版代码了都
          int left=0;
          int right=nums.length-1;
          while(left<=right){
              int mid=left+(right-left)/2;
              if(nums[mid]==target){
                  return mid;
              }else if(nums[mid]>target){
                  right=mid-1;
              }else{
                  left=mid+1;
              }
          }
          return-1;
      }
  }
  ```

- 

