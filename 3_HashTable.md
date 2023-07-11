哈希表 一般用的最多的就是HashMap 

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

