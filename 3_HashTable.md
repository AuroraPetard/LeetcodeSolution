哈希表 一般用的最多的就是HashMap 

包括 

数组  小写字母 数量有限可以使用

set 数量无限 

HashMap 既要key 又要value

<font color='red'>遇到需要快速判断一个元素是否出现在集合中 需要考虑使用hashmap</font>

#### [242. 有效的字母异位词](https://leetcode.cn/problems/valid-anagram/)

给定两个字符串 `*s*` 和 `*t*` ，编写一个函数来判断 `*t*` 是否是 `*s*` 的字母异位词。

**注意：**若 `*s*` 和 `*t*` 中每个字符出现的次数都相同，则称 `*s*` 和 `*t*` 互为字母异位词

```java
class Solution {
    public boolean isAnagram(String s, String t) {
        // 数组可以看成是一个简单的map
        // 一共就26个英文字母 遍历循环每个字符 看看每个对应的词频
        
        int[] count=new int[26];
        
        
        for(char c:s.toCharArray()){
            // 26个字母-a会得到不同的值 
            // 间接使用map 计算词频
            count[c-'a']++;
        }
        
        for(char c:t.toCharArray()){
            // 上面++ 这里-- 
            // 如果词频相同那么count每个都应该是0
            // 否则就是不同
            count[c-'a']--;
        }
        
        
        // 遍历
        for(int c:count){
            if(c!=0){
                return false;
            }
        }
        
        return true;
    }
}
```

#### [383. 赎金信](https://leetcode.cn/problems/ransom-note/)

给你两个字符串：`ransomNote` 和 `magazine` ，判断 `ransomNote` 能不能由 `magazine` 里面的字符构成。

如果可以，返回 `true` ；否则返回 `false` 。

`magazine` 中的每个字符只能在 `ransomNote` 中使用一次。

```java
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
     
        // 提前把大的magazine做成词频 
        Map<Character,Integer> countMap=new HashMap<>();
        
        for(char ch:magazine.toCharArray()){
            countMap.put(ch,countMap.getOrDefault(ch,0)+1);
        }
        
        
        for(char ch:ransomNote.toCharArray()){
            countMap.put(ch,countMap.getOrDefault(ch,0)-1);
            // 因为提前把magazine放入里面了 
            // 而且上面也加进去了 也-1了
            // 如果这个时候为负值 那么表示当前ransomNote的这个字符 在 magazine中没有
            // 也就是说 ransomnote无法被magazine组成
            if(countMap.get(ch)<0){
                return false;
            }
        }
        
        return true;
        
    }
}
```

#### [49. 字母异位词分组](https://leetcode.cn/problems/group-anagrams/)

给你一个字符串数组，请你将 **字母异位词** 组合在一起。可以按任意顺序返回结果列表。

**字母异位词** 是由重新排列源单词的所有字母得到的一个新单词。

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // 字母异位 但是词频是一致的
        // 将数组中的每个转化成词频 相同词频的放一个list里面
        // 相同词频的为key 
        
        Map<String,List<String>> countMap=new HashMap<>();
        
        for(String str:strs){
            // 将每个字符串转换成词频 添加到map里面
            
            
            // 词频arr 小写字母
            // 小写字母的 ASCII 值范围是从 97 到 122
            int[] countArr=new int[26];
            
            // 小写字母的 ASCII 值范围是从 97 到 122
            for(char ch:str.toCharArray()){
                countArr[ch-'a']++;
            }
            
            
            StringBuffer  sb=new StringBuffer();
            
            // 小写字母的 ASCII 值范围是从 97 到 122
            for(int i=0;i<26;i++){
                if(countArr[i]!=0){
                    sb.append(i+'a');
                    sb.append(countArr[i]);
                }
            }
            
            String key=sb.toString();
            
             List<String>  list=countMap.getOrDefault(key,new ArrayList<String>());
            
            list.add(str);
            
            countMap.put(key,list);
            
        }
        
        return new ArrayList<List<String>>(countMap.values());
    }
}
```

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // 分组 相同的放一块  相同key value
        // 那么用map
        // 相同的key是什么呢 字母以为 词频相同那么就用词频zuokey
        
        Map<String,List<String>> map=new HashMap<>();
        
        for(String str:strs){
            
            
            String key=str2key(str);
            
            List<String> list=map.getOrDefault(key,new ArrayList<String>());
            
            list.add(str);
            
            map.put(key,list);
            
        }
        
        return new ArrayList<List<String>>(map.values());
        
    }
    
    
    public String str2key(String str){
        int[] count=new int[26];
        StringBuffer sb=new StringBuffer();
        
        
        for(char ch:str.toCharArray()){
            count[ch-'a']++;
        }
 
        
        for(int i=0;i<26;i++){
            if(count[i]!=0){
                sb.append(i+'a');
                sb.append(count[i]);
            }
        }
        
        
        
        return sb.toString();
        
 
    }
    
}
```



#### [438. 找到字符串中所有字母异位词](https://leetcode.cn/problems/find-all-anagrams-in-a-string/)

给定两个字符串 `s` 和 `p`，找到 `s` 中所有 `p` 的 **异位词** 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。

**异位词** 指由相同字母重排列形成的字符串（包括相同的字符串）。

```java
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        //字母异位 词频搞就完了
        
        // 肯定是遍历s 每次遍历p的长度 
        
        // 和前面的相同分组差不多 
        // 前面是自己分好组了 这里只是需要自己分组
        // for(i <=s.length()-p.length())
        
        // 看返回值
        
        Map<String,List<Integer>> result=new HashMap<>();
        
        // 这样i每次加p的长度 
        // 这里一定要等于 因为能够取得到
        for(int i=0;i<=s.length()-p.length();i++){
            // 这样走到最后 也不会越界
            String str=s.substring(i,i+p.length());
            
            // 将每个转化成词频
            
            String key=str2key(str);
            
            List list=result.getOrDefault(key,new ArrayList<Integer>());
            
            list.add(i);
            
            result.put(key,list);
            
        }
        
        // 这里只要将p转化成词频 变成key 从result里面拿出来相同key的就行了
        return result.getOrDefault(str2key(p),new ArrayList<Integer>());
   
        
    }
    
    
    
    // 转化成词频方法
    public String str2key(String str){
        
        // a~z 每个单词
        int[] countArr=new int[26];
        
        
        // 添加
        StringBuffer sb=new StringBuffer();
        
        
        
        
        // a~z 顺序是不变的 字母以为 在变  转化成 a~z的词频是一样的
        for(char ch:str.toCharArray()){
            countArr[ch-'a']++;
        }
        
        
        for(int i=0;i<26;i++){
            if(countArr[i]!=0){
                sb.append(i+'a');
                sb.append(countArr[i]);
            }
        }
        
        return sb.toString();
        
    }
    
}
```

#### [1002. 查找共用字符](https://leetcode.cn/problems/find-common-characters/)

给你一个字符串数组 `words` ，请你找出所有在 `words` 的每个字符串中都出现的共用字符（ **包括重复字符**），并以数组形式返回。你可以按 **任意顺序** 返回答案

其实是在问 在每个字符中出现的字符 重复的也算

```java
class Solution {
    public List<String> commonChars(String[] words) {
        // 小写字母 出现频率 这些都是hash表的使用前提
        //而小写字母 则可以用长度为26的数组来进行表示
        
        // 问题解释就是 在数组中每个都出现的字符 重复的也算
        
        // 思路 定义两个数组其实算是map 第一个用来初始化存储最小的值
        // 第二个数组存储当前的值 比较两个数组中的值 存储最小值
        // 为什么是最小值 如果最小值不为0那么则表示这个字符在每个都有
        // =1 每个只出现一次 >1 出现多次
        // 最后遍历第一个数组 输出
        
        
        int[] minHash=new int[26];
        
        //用第一个字符串进行初始化
        
        for(char ch:words[0].toCharArray()){
            minHash[ch-'a']++;
        }
        
        
        // 遍历剩下的字符串和第一个字符串进行取最小值
        for(int i=1;i<words.length;i++){
            int[] curHash=new int[26];
            
            // 当前的字符转成map
            for(char ch:words[i].toCharArray()){
                curHash[ch-'a']++;
            }
            
            // 对比两个取最小值
            // min
            // =0 有没有的 =1 有且仅有一个 >1多个都要输出因为说了重复也要
            for(int j=0;j<26;j++){
                minHash[j]=Math.min(minHash[j],curHash[j]);
            }
            
        }
        
        
        List<String> resultList=new ArrayList<>();
        
        for(int i=0;i<26;i++){
            // 因为说了重复的也要 所以这里是while
            while(minHash[i]!=0){

                // 转化成string
                char c=(char)(i+'a');
              
                resultList.add(String.valueOf(c));
                
                // 注意这里要-- 
                // 重复的加一次 减一次
                minHash[i]--;
                
            }
        }
        
        return resultList;
        
    }
}
```

#### [349. 两个数组的交集](https://leetcode.cn/problems/intersection-of-two-arrays/)

给定两个数组 `nums1` 和 `nums2` ，返回 *它们的交集* 。输出结果中的每个元素一定是 **唯一** 的。我们可以 **不考虑输出结果的顺序** 。

使用数组来作为hash表的前提是数据量少 可控 比如小写字母 只有26个所以使用数组表示hash正合适

如果数据量分散跨度大 就不合适了 

此时可以考虑使用set hashset 

```java
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        // 唯一的 不考虑顺序 那不就是set吗
        
        //定义两个set 一个用来做初始化
        // 一个用来做最后结果
        Set<Integer> tmp=new HashSet<>();
        Set<Integer> resultSet=new HashSet<>();
        
        
        // 先把num1放入set中
        for(int i:nums1){
            tmp.add(i);
        }
        
        
        // 遍历num2 判断是否在num1 也就是 是否conntains
        for(int i:nums2){
            if(tmp.contains(i)){
                resultSet.add(i);
            }
        }
        
        // 现在结果集是个set 返回结果是int[]
        // 现在需要把set转化成int[]
        
        // lambda
        // return resSet.stream().mapToInt(x -> x).toArray();
        
        // 定义一个结果数组
        int[] resultArr=new int[resultSet.size()];
        

        // 不会定义个变量吗？？？
        int j=0;
        for(int i:resultSet){
            resultArr[j++]=i;
        }
        
        return resultArr;
        
    }
}
```

#### [202. 快乐数](https://leetcode.cn/problems/happy-number/)

编写一个算法来判断一个数 `n` 是不是快乐数。

**「快乐数」** 定义为：

- 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
- 然后重复这个过程直到这个数变为 1，也可能是 **无限循环** 但始终变不到 1。
- 如果这个过程 **结果为** 1，那么这个数就是快乐数。

如果 `n` 是 *快乐数* 就返回 `true` ；不是，则返回 `false` 。

```java
// 无限循环 
// 判断一个数是否存在用hash 
// hash包括 数组 hashset hashmap 选择用哪个

class Solution {
    public boolean isHappy(int n) {
        
    Set<Integer> set=new HashSet<>();
    
        
    // 如果n=1 为快乐数 
    // 如果有重复 那么不是快乐数
    while(n!=1 &&!set.contains(n)){
        set.add(n);
        n=getNext(n);
    }
        
        return n==1;
    
    }
    
    
    // 每次从个位数平方相加
    public int getNext(int n){
        int res=0;
        
        // 每次取整最后会变成0
        while(n>0){
            // 这里是获取的个位数
            int cur=n%10;
            
            res+=cur*cur;
            
            // 去除个位数
            n=n/10;
        }
        
        return res;
    }
    
    
    
}
```

#### [1. 两数之和](https://leetcode.cn/problems/two-sum/)

给定一个整数数组 `nums` 和一个整数目标值 `target`，请你在该数组中找出 **和为目标值** *`target`* 的那 **两个** 整数，并返回它们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。

你可以按任意顺序返回答案。

<font color='red'> 两数之和 梦开始的地方</font>

```java
// 判断一个数 是否存在一个集合中 使用map
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // 为什么用map
        // 判断是否存在
        Map<Integer,Integer> map=new HashMap<>();
        
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(target-nums[i])){
                return new int[]{i,map.get(target-nums[i])};
            }
            map.put(nums[i],i);
        }
        return new int[]{-1,-1};
    } 
}
```

#### [454. 四数相加 II](https://leetcode.cn/problems/4sum-ii/)

给你四个整数数组 `nums1`、`nums2`、`nums3` 和 `nums4` ，数组长度都是 `n` ，请你计算有多少个元组 `(i, j, k, l)` 能满足：

- `0 <= i, j, k, l < n`
- `nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0`

```java
class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // 只能两两相加 判断
        
        // 两两相加 求和 
        // 然后在剩下的两两相加 看看map中有没有对应的其负值
        
        // map 保留数 还咬保留其次数
        
        Map<Integer,Integer> map=new HashMap<>();
        
        
        // 最后的次数
        int count=0;
        
        for(int i=0;i<nums1.length;i++){
            for(int j=0;j<nums2.length;j++){
                int value=nums1[i]+nums2[j];
                map.put(value,map.getOrDefault(value,0)+1);
            }
        }
        
        
        for(int i=0;i<nums3.length;i++){
            for(int j=0;j<nums4.length;j++){
                
                // 此处是专门求的负值
                // 看看map里面有没有这个值
                int value=-(nums3[i]+nums4[j]);
                count+=map.getOrDefault(value,0);
                
                
            }
        }
        return count;
    }
}
```

#### [15. 三数之和](https://leetcode.cn/problems/3sum/)

给你一个整数数组 `nums` ，判断是否存在三元组 `[nums[i], nums[j], nums[k]]` 满足 `i != j`、`i != k` 且 `j != k` ，同时还满足 `nums[i] + nums[j] + nums[k] == 0` 。请

你返回所有和为 `0` 且不重复的三元组。

**注意：**答案中不可以包含重复的三元组。

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // 双指针 让三个数降维成两个数
        // 对数组进行排序
        // 先确定第一个数i 然后定义left=i+1 right=nums.length-1;
        
        // sum=nums[i]+nums[left]+nums[right]
        
        // 因为已经提前排好序了
        
        //if nums[i] >0 return false
        
        // sum >0 right--
        // sum <0 left++
        // sum =0 符合条件相加
        
      // 在解决这个问题的过程中，跳过重复的数是为了避免得到重复的解
      // 重复的数 会得到重复的解
        
      
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        
        // 因为已经排好序了 如果第一个最小的都>0 那么肯定后面的和无法=0
        if(nums[0]>0){
            return result;
        }
        
        for(int i=0;i<nums.length;i++){
            
            //固定一个然后去找剩下的两个
            
            if(i>0 && nums[i]==nums[i-1]){
                continue;
            }
            
            int left=i+1;
            int right=nums.length-1;
            
            while(left<right){
                int sum=nums[i]+nums[left]+nums[right];
                if(sum>0){
                    right--;
                }else if(sum<0){
                    left++;
                }else{
                    result.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    
        						// 跳过left right重复
                    while(left<right && nums[left] == nums[left+1]){
                        left++;
                    }
                    
                    while(left<right && nums[right] == nums[right-1]){
                        right--;
                    }
                    
                           
                    
                    left++;
                    right--;
                }
            }
            
        }
        return result;
        
    }
}
```



#### [18. 四数之和](https://leetcode.cn/problems/4sum/)

给你一个由 `n` 个整数组成的数组 `nums` ，和一个目标值 `target` 。请你找出并返回满足下述全部条件且**不重复**的四元组 `[nums[a], nums[b], nums[c], nums[d]]` （若两个四元组元素一一对应，则认为两个四元组重复）：

- `0 <= a, b, c, d < n`
- `a`、`b`、`c` 和 `d` **互不相同**
- `nums[a] + nums[b] + nums[c] + nums[d] == target`

你可以按 **任意顺序** 返回答案 。

```java
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // 两数之和 用map
        // 三数之和用双指针
        // 四数之和在三数的基础上在添加一层for循环
        
        List<List<Integer>> result=new ArrayList<>();
        
        Arrays.sort(nums);
        
        if(nums[0]>target){
            return result;
        }
        
        
        
        for(int i=0;i<nums.length;i++){
            
            if(i>0 && nums[i-1]==nums[i]){
                continue;
            }
            
            
            // 不能有重复的 所以是从i+1 开始的
            for(int j=i+1;j<nums.length;j++){
                if(j>i+1 && nums[j-1]==nums[j]){
                    continue;
                }
                
                
                int left=j+1;
                int right=nums.length-1;
                
                while(left<right){
                    long sum=(long)nums[i]+nums[j]+nums[left]+nums[right];
                    if(sum>target){
                        right--;
                    }else if(sum<target){
                        left++;
                    }else{
                        result.add(Arrays.asList(nums[i],nums[j],nums[left],nums[right]));
                        
                        while(left < right && nums[left]==nums[left+1]){
                            left++;
                        }
                        
                        while(left < right && nums[right]==nums[right-1]){
                            right--;
                        }
                        
                        
                        
                        
                        
                        left++;
                        right--;
                    }
                }
                
            }
            
        }
        return result;
        
    }
}
```

  