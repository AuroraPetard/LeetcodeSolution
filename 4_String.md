#### [344. 反转字符串](https://leetcode.cn/problems/reverse-string/)

编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 `s` 的形式给出。

不要给另外的数组分配额外的空间，你必须**[原地](https://baike.baidu.com/item/原地算法)修改输入数组**、使用 O(1) 的额外空间解决这一问题

```java
class Solution {
    public void reverseString(char[] s) {
        // O(1) 实现
        
        // 双指针 交换头和末尾的节点数据 完成反转
        int left=0;
        int right=s.length-1;
        
        while(left<right){
            
            char temp=s[left];
            s[left]=s[right];
            s[right]=temp;
            
            left++;
            right--;
            
        }
    }
}
```

#### [541. 反转字符串 II](https://leetcode.cn/problems/reverse-string-ii/)

给定一个字符串 `s` 和一个整数 `k`，从字符串开头算起，每计数至 `2k` 个字符，就反转这 `2k` 字符中的前 `k` 个字符。

- 如果剩余字符少于 `k` 个，则将剩余字符全部反转。

- 如果剩余字符小于 `2k` 但大于或等于 `k` 个，则反转前 `k` 个字符，其余字符保持原样。

- **所以当需要固定规律一段一段去处理字符串的时候，要想想在在for循环的表达式上做做文章。**

  ```java
  class Solution {
      public String reverseStr(String s, int k) {
        
          
          // 转化成数组便于后面替换反转
          char[] arr=s.toCharArray();
          int n=arr.length;
          
          
          // 每次走2k就好了
          for(int i=0;i<n;i+=2*k){
              
              
              // 反转前k个 
              int left=i;
              
              // 判断是不是末尾
              // 为什么-1
              // 下标啊喂 从0开始 所以-1
              int right=Math.min(left+k-1,n-1);
              
              
              // 常规替换前k个
              while(left<right){
                  
                  char temp=arr[left];
                  arr[left]=arr[right];
                  arr[right]=temp;
                  
                  
                  // 注意下标跟随变化
                  left++;
                  right--;
                  
              }
              
              
              
              
          }
          
          return new String(arr);
          
      }
  }
  ```
  
  #### [剑指 Offer 05. 替换空格](https://leetcode.cn/problems/ti-huan-kong-ge-lcof/)
  
  请实现一个函数，把字符串 `s` 中的每个空格替换成"%20"。
  
  ```java
  class Solution {
      public String replaceSpace(String s) {
          
          StringBuilder sb=new StringBuilder();
          
          for(int i=0;i<s.length();i++){
              
              if(s.charAt(i)==' '){
                  sb.append("%20");
              }else{
                  sb.append(s.charAt(i));
              }
              
          }
          return sb.toString();
      }
  }
  ```
  
  #### [151. 反转字符串中的单词](https://leetcode.cn/problems/reverse-words-in-a-string/)
  
  给你一个字符串 `s` ，请你反转字符串中 **单词** 的顺序。
  
  **单词** 是由非空格字符组成的字符串。`s` 中使用至少一个空格将字符串中的 **单词** 分隔开。
  
  返回 **单词** 顺序颠倒且 **单词** 之间用单个空格连接的结果字符串。
  
  **注意：**输入字符串 `s`中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
  
  ```java
  class Solution {
      public String reverseWords(String s) {
  
          String[] strArr=s.trim().split("\\s+");
          
          StringBuilder sb=new StringBuilder();
          
          for(int i=strArr.length-1;i>=0;i--){
              
              sb.append(strArr[i]);
              
              if(i>0){
                  sb.append(" ");
              }
              
          }
          
          return sb.toString();
      }
  }
  ```
  
  #### [剑指 Offer 58 - II. 左旋转字符串](https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/)
  
  字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
  
  ```java
  class Solution {
      public String reverseLeftWords(String s, int n) {
          
          
          StringBuilder sb=new StringBuilder();
          
          sb.append(s.substring(n));
          
          sb.append(s.substring(0,n));
          
          return sb.toString();
          
      }
  }
  ```
  
  