### 数组

`int[] arr = new int[length]{var1,var2} `

### 二分查找

- 704  简单  

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

- #### 69  简单 [x 的平方根 ](https://leetcode.cn/problems/sqrtx/)

- 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。

  由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。

  注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。

  来源：力扣（LeetCode）
  链接：https://leetcode.cn/problems/sqrtx
  著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

- ```java
  class Solution {
      public int mySqrt(int x) {
          // 算术平方根 a^2=x 求a的取整数部分
          // 可以看成一个 有序的数组  二分查找 
          // 有序 涉及查找 第一要想到二分查找
          // 二分查找 模版
  
          // 返回值
          int ans=-1;
  
          int left=0;
  
          // 涉及到边界问题 关键要看能不能取得到 取到有没有意义
          int right=x;
  
          while(left<=right){
              int mid=left+(right-left)/2;
  
              // 变long值比较
              long value=(long)mid*mid;
  
              // <= 慢慢向右逼近
              if(value<=x){
                  ans=mid;
                  left=mid+1;
              }else{
                  right=mid-1;
              }
          }
          return ans;
      }
  }
  ```

  

- #### [367. 有效的完全平方数](https://leetcode.cn/problems/valid-perfect-square/)

- 给你一个正整数 num 。如果 num 是一个完全平方数，则返回 true ，否则返回 false 。

  完全平方数 是一个可以写成某个整数的平方的整数。换句话说，它可以写成某个整数和自身的乘积。

  不能使用任何内置的库函数，如  sqrt 。

  来源：力扣（LeetCode）
  链接：https://leetcode.cn/problems/valid-perfect-square
  著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

  ```java
  class Solution {
      public boolean isPerfectSquare(int num) {
        // 和前面的那个 X的平方根差不多
          // 前面那个是取整  这个完全平方根 就是要求本身的值 仅且只能由整数转
          // 也可以看作一个二分查找
          int left=0;
          int right=num;
          while(left<=right){
              int mid=left+(right-left)/2;
              // 转成long
              long value=(long)mid*mid;
              // 此处必须相等
              if(value==num){
                  // 相等表示为有效的完全平方根
                  return true;
              }else if(value>num){
                  right=mid-1;
              }else{
                  left=mid+1;
              }
          }
          return false;
      }
  }
  ```
  
  ### 双指针 快慢指针
  
- #### [27. 移除元素](https://leetcode.cn/problems/remove-element/)

  给你一个数组 `nums` 和一个值 `val`，你需要 **[原地](https://baike.baidu.com/item/原地算法)** 移除所有数值等于 `val` 的元素，并返回移除后数组的新长度。

  不要使用额外的数组空间，你必须仅使用 `O(1)` 额外空间并 **[原地 ](https://baike.baidu.com/item/原地算法)修改输入数组**。

  元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。

  ```java
  class Solution {
      public int removeElement(int[] nums, int val) {
          // 快慢指针
          // 一个在前面跑 一个在后面追 合适就处理
          int slowIndex=0;
          for(int fastIndex=0;fastIndex<nums.length;fastIndex++){
              if(nums[fastIndex]!=val){
                  // 不等于目标值 则进行替换 同时slowIndex++ 往后走
                  nums[slowIndex++]=nums[fastIndex];
              }
          }
          return slowIndex;
      }
  }
  ```

- #### [26. 删除有序数组中的重复项](https://leetcode.cn/problems/remove-duplicates-from-sorted-array/)

  给你一个 **升序排列** 的数组 `nums` ，请你**[ 原地](http://baike.baidu.com/item/原地算法)** 删除重复出现的元素，使每个元素 **只出现一次** ，返回删除后数组的新长度。元素的 **相对顺序** 应该保持 **一致** 。然后返回 `nums` 中唯一元素的个数。

  ```java
  class Solution {
      public int removeDuplicates(int[] nums) {
          // 原地删除 判断是否重复 
          // 设定第一个为不重复 所以从第一个开始
          int slowIndex=1;
          for(int fastIndex=1;fastIndex<nums.length;fastIndex++){
              // 判断相邻的是否不等
              // 因为前面从1开始 所以此处并不会越界
              if(nums[fastIndex-1]!=nums[fastIndex]){
                  nums[slowIndex++]=nums[fastIndex];
              }
          }
          return slowIndex;
      }
  }
  ```

- #### [283. 移动零](https://leetcode.cn/problems/move-zeroes/)

  给定一个数组 `nums`，编写一个函数将所有 `0` 移动到数组的末尾，同时保持非零元素的相对顺序。

  **请注意** ，必须在不复制数组的情况下原地对数组进行操作。

  ```java
  class Solution {
      public void moveZeroes(int[] nums) {
          // 移动数据 temp指针
          // 不复制数组 还是原来的数组
          // 保持非0元素的相对顺序
          int slowIndex=0;
          for(int fastIndex=0;fastIndex<nums.length;fastIndex++){
              if(nums[fastIndex]!=0){
                  // 把当前的slowindex值给temp
                  int temp=nums[slowIndex];
                  // 把当前的数组的非0给slowindex
                  nums[slowIndex]=nums[fastIndex];
                  // 
                  nums[fastIndex]=temp;
                  slowIndex++;
              }
          }
      }
  }
  ```

- #### [844. 比较含退格的字符串](https://leetcode.cn/problems/backspace-string-compare/)

  给定 `s` 和 `t` 两个字符串，当它们分别被输入到空白的文本编辑器后，如果两者相等，返回 `true` 。`#` 代表退格字符。

  **注意：**如果对空文本输入退格字符，文本继续为空。

  ```java
  class Solution {
      public boolean backspaceCompare(String s, String t) {
  
          // 使用StringBuilder来模拟栈
          
          return ProcessStr(s).equals(ProcessStr(t));
          
          
          
  
         // 下面是第二种方法没有借助任何工具 
          
          // int i=s.length()-1;
          // int j=t.length()-1;
  
  
          // // 记录字符串中#的个数
          // int skipS=0;
          // int skipT=0;
  
          // // 两个都没有走到尽头
          // while(i>=0 || j>=0){
  
  
          //     // 单独循环s t
          //     while(i>=0){
          //         if(s.charAt(i)=='#'){
          //             // 如果是#号 
          //             // 下标减一 往前移动
          //             // 记录#同时增加
          //             i--;
          //             skipS++;
          //         }else if(skipS>0){
          //             // 如果 #记录大于0说明需要往前退格
          //             skipS--;
          //             i--;
          //         }else{
          //             // 第三种可能是为正常字符 退出去 和 t的进行比较
          //             break;
          //         }
          //     }
  
  
  
          //     while(j>=0){
          //         if(t.charAt(j)=='#'){
          //             skipT++;
          //             j--;
          //         }else if(skipT>0){
          //             skipT--;
          //             j--;
          //         }else{
          //             break;
          //         }
          //     }
  
          //     // 比较当前的s和t的最后末尾 看是否相同
          //     // 当前这个还是不同 末尾 那么就返回false
          //     if(i>=0 && j>=0 && s.charAt(i) !=t.charAt(j)){
          //         return false;
          //     }
  
          //     // 往前减的位置不同 也返回false
          //     if((i>=0) != (j>=0)){
          //         return false;
          //     }
  
  
  
              
          //     // 每次循环 下标都要往前移动
          //     i--;
          //     j--;
          // }
  
          // // 最后走出来了  两个都走到头了 表明两个都是最坏为空 所以返回为真
          // return true;
  
  
  
  
  
      }
  
      public String ProcessStr(String s){
          StringBuilder sb=new StringBuilder();
          for (char c:s.toCharArray()){
              if(c!='#'){
                  sb.append(c);
              }else{
                  // 上面是不等于# 
                  // 这里面肯定是等于=的 所以要哦往前进行退格
                  // 退格之前判断 长度
                  if(sb.length()>0){
                      // 退格 删除最后面的那一个
                      sb.deleteCharAt(sb.length()-1);
                  }
              }
          }
          return sb.toString();
      }
  }
  ```
  
- #### [977. 有序数组的平方](https://leetcode.cn/problems/squares-of-a-sorted-array/)

  给你一个按 **非递减顺序** 排序的整数数组 `nums`，返回 **每个数字的平方** 组成的新数组，要求也按 **非递减顺序** 排序。

  ```java
  class Solution {
      public int[] sortedSquares(int[] nums) {
          // 非递减 那就是递增  顺序排列
          // 平方之后 最大值在头或者尾 判断一下
  
          // 定义一个 和原数组长度一致的数组 用来存放结果
          int[] result=new int[nums.length];
  
          int k=nums.length-1;
  
          int i=0;
          int j=nums.length-1;
  
  
          while(i<=j){
              // 平方后 极值 一定在两端 判断一下
              if(nums[i]*nums[i]>=nums[j]*nums[j]){
                  result[k--]=nums[i]*nums[i++];
              }else{
                  result[k--]=nums[j]*nums[j--];
              }
          }
          return result;
      }
  }
  ```

  ### 滑动窗口

  - [209. 长度最小的子数组](https://leetcode.cn/problems/minimum-size-subarray-sum/)

    给定一个含有 `n` 个正整数的数组和一个正整数 `target` **。**

    找出该数组中满足其和 `≥ target` 的长度最小的 **连续子数组** `[numsl, numsl+1, ..., numsr-1, numsr]` ，并返回其长度**。**如果不存在符合条件的子数组，返回 `0` 。

    ```java
    class Solution {
        public int minSubArrayLen(int target, int[] nums) {
            //控制左右指针 在满足 sum>=target的情况下移动 
            // 就像一个窗口在移动 
            int left=0;
            int sum=0;
            int result=Integer.MAX_VALUE;
            // 左指针作为初始指针
            // 右指针作为循环的每一个index
            // 移动条件 sum>=target
            for(int right=0;right<nums.length;right++){
                // 每次累加进来的值
                sum+=nums[right];
                
                // 向右移动条件 sum>=target
                while(sum>=target){
                    // 取最小值
                    result=Math.min(result,right-left+1);
    
                    // 点睛之笔 
                    // 移动左指针 
                    // 将左指针向右移动 
                    sum-=nums[left++];
                }
            }
    
            // 是否变化
            return result==Integer.MAX_VALUE ? 0 : result;
        }
    }
    ```
    
  - #### [904. 水果成篮](https://leetcode.cn/problems/fruit-into-baskets/)
  
    你正在探访一家农场，农场从左到右种植了一排果树。这些树用一个整数数组 `fruits` 表示，其中 `fruits[i]` 是第 `i` 棵树上的水果 **种类** 。
  
    你想要尽可能多地收集水果。然而，农场的主人设定了一些严格的规矩，你必须按照要求采摘水果：
  
    - 你只有 **两个** 篮子，并且每个篮子只能装 **单一类型** 的水果。每个篮子能够装的水果总量没有限制。
    - 你可以选择任意一棵树开始采摘，你必须从 **每棵** 树（包括开始采摘的树）上 **恰好摘一个水果** 。采摘的水果应当符合篮子中的水果类型。每采摘一次，你将会向右移动到下一棵树，并继续采摘。
    - 一旦你走到某棵树前，但水果不符合篮子的水果类型，那么就必须停止采摘。
  
    给你一个整数数组 `fruits` ，返回你可以收集的水果的 **最大** 数目。
  
  - 这个翻译就是格垃圾 读了几遍 还是不清楚
  
  - 实际上他要问的问题 其实是 
  
  - 求只包含两种元素的最长连续子序列
  
    ```java
    // 最值 连续 
    // 这些关键词要考虑滑动窗口解决
    class Solution {
        public int totalFruit(int[] fruits) {
            // 左z指针
            int left=0;
    
            // 最后的返回结果值
            int result=0;
    
            // 篮子 用来存放水果 以及水果的数量
            Map<Integer,Integer> basket= new HashMap<>();
    
    
            // 右边界 移动右边界
            for(int right=0;right<fruits.length;right++){
                basket.put(fruits[right],basket.getOrDefault(fruits[right],0)+1);
    
                // 移动左边界的条件
                while(basket.size()>2){
                    //从左边开始减
                    int value=basket.get(fruits[left]);
                    if(value==1){
                        basket.remove(fruits[left]);
                    }else{
                        basket.put(fruits[left],basket.get(fruits[left])-1);
                    }
                    // 移动左边界
                    left++;
                }
                  // 每次循环后 
                  //  上面那个是在循环里面每次取值 
              		// 这个是在while循环外取值
                  // 因为上面是取最小值 每次都会缩小 
                  // 这个是取最大值 万一里面有最大的会对结果造成影响
                  // 所以在while外面拿值 安全些
                  // 判断一下值
                  result=Math.max(result,right-left+1);
            }
            return result;
        }
    }
    ```
  
  - #### [76. 最小覆盖子串](https://leetcode.cn/problems/minimum-window-substring/)
  
    给你一个字符串 `s` 、一个字符串 `t` 。返回 `s` 中涵盖 `t` 所有字符的最小子串。如果 `s` 中不存在涵盖 `t` 所有字符的子串，则返回空字符串 `""` 
  
    ```java
    class Solution {
        public String minWindow(String s, String t) {
          // 最值字符串 考虑滑动窗口
            Map<Character,Integer> targetFreq=new HashMap<>();
    
            // 将目标字符串t转成成成词频放入到map中
            for(char ch:t.toCharArray()){
                targetFreq.put(ch,targetFreq.getOrDefault(ch,0)+1);
            }
    
            // 定义变量
            
            // 左边界
            int left=0;
            
            // 统计当前窗口内符合t的个数
            int formed=0;
    
            // 目标t的不重复字符个数
            int required=targetFreq.size();
    
    
            // 定义最后结果返回值
            // 目标求最小 初始定义为最大 且每次在循环里面更新 找最小值
            // 目标求最大 厨师定义为0    在循环外更新求最大
            int minLen=Integer.MAX_VALUE;
    
            // 定义内部滑动窗口的左边界
            int minLeft=0;
    
            // 定义滑动窗口内的词频
            Map<Character,Integer> windowFreq=new HashMap<>();
    
    
            // 开始内部滑动窗口
            // 滑动窗口模版
            for(int right=0;right<s.length();right++){
                char ch=s.charAt(right);
                windowFreq.put(ch,windowFreq.getOrDefault(ch,0)+1);
    
                // 如果目标词频含有这个字符 并且当前这个字符的词频相同
                // 那么表示当前窗口中 已经含有目标串的一个字符
                // 让formed+1
                if(targetFreq.containsKey(ch) && windowFreq.get(ch).intValue()==targetFreq.get(ch).intValue()){
                    formed++;
                }
    
    
                // 时刻考虑边界问题 要有边界感
                // formed==required 表示当前这个小窗口内的数据 
                // 已经包含目标t的全部了 
                // 接下来要做的就是从当前这个小窗口内 
                // 慢慢得 寻找最小
                // 为什么包含了 还要找最小 
                // 假如t abc 当前窗口  daccbcacd 
                // 那么可能的就是将当前窗口从左往右赶 左边慢慢加一 当前小窗口的左边界向右移动
                // accb bca 
                // 那么很明显答案是 bca
                while(formed==required){
    
                  	// 如果有最小值
                    // 循环里面更新最小值 以及 
                    // 当前窗口的左边界
                    if(right-left+1<minLen){
                        minLen=right-left+1;
                        minLeft=left;
                    }
    
                    // 从左边慢慢开始剔除 挑战底线 formed==required
                    char leftChar=s.charAt(left);
    
                    // 在这里还不确定 这个leftchar是不是在目标t里面 
                    windowFreq.put(leftChar,windowFreq.get(leftChar)-1);
    
                    // 这这里if判断
                    // 因为上面-1 万一导致 当前窗口词频小于 退出循环
                    if(targetFreq.containsKey(leftChar) && windowFreq.get(leftChar) < targetFreq.get(leftChar)){
                        formed--;
                    }
    
                    // 移动左边界 
                    // 循环的末尾记得移动指针边界
                    left++;
                }
            }
          	// 窗口内起始位置加上最小字串长度 则是最小字串
            return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft,minLeft+minLen);
    
       }
    }
    ```
    
  - 59. 螺旋矩阵 II
  
    给你一个正整数 `n` ，生成一个包含 `1` 到 `n2` 所有元素，且元素按顺时针顺序螺旋排列的 `n x n` 正方形矩阵 `matrix` 。
  
    ```java
    class Solution {
        public int[][] generateMatrix(int n) {
            // 既然螺旋矩阵 顺时针 那就四个边 上下左右 一个边 一个边的进行 
    
            int left=0;
            int right=n-1;
            int up=0;
            int down=n-1;
    
            // 当前初始值
            // 初始值为1 从1开始
            int current=1;
    
            // 结果矩阵数组
            int[][] result=new int[n][n];
    
            // 循环条件
            while(current<=n*n){
                // 要考虑到下次循环
    
    
                // 行 列
    
    
                // 最上面
                for(int i=left;i<=right;i++){
                    result[up][i]=current++;
                }
                up++; //伴随着上面变化
    
    
                // 最右边
                for(int i=up;i<=down;i++){
                    result[i][right]=current++;
                }
                right--;
    
    
                // 最下面
                for(int i=right;i>=left;i--){
                    result[down][i]=current++;
                }
                down--;
    
    
                // 最左边
                for(int i=down;i>=up;i--){
                    result[i][left]=current++;
                }
                left++;
            }
            return result;
        }
    }
    ```
  
    
  
    - 
  
    #### [54. 螺旋矩阵](https://leetcode.cn/problems/spiral-matrix/)
  
    给你一个 `m` 行 `n` 列的矩阵 `matrix` ，请按照 **顺时针螺旋顺序** ，返回矩阵中的所有元素。
  
    ```java
    class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
    
            int m=matrix.length;
            int n=matrix[0].length;
    
            // 上面那个是n*n 矩阵 方形 这个是不规则 
            int left=0;
            int right=n-1;
    
            int up=0;
            int down=m-1;
    
            // 从1开始 判断次数
            int index=1;
    
            // 循环的次数
            int max=m*n;
    
            List<Integer> resultList=new ArrayList<>();
    
    
                // 能够取到等于值这块
                while(index<=max){
    
                // 每次都要判断是否越界
                for(int i=left;i<=right && index<=max ;i++){
                    resultList.add(matrix[up][i]);
                    index++;
                }
                up++;
    
                for(int i=up;i<=down && index<=max;i++){
                    resultList.add(matrix[i][right]);
                    index++;
                }
                right--;
    
                for(int i=right;i>=left && index<=max ;i--){
                    resultList.add(matrix[down][i]);
                    index++;
                }
                down--;
    
    
                for(int i=down;i>=up && index<=max ;i--){
                    resultList.add(matrix[i][left]);
                    index++;
                }
    
                left++;
            }
            return resultList;
        }
    }
    
    ```
  
  - 

