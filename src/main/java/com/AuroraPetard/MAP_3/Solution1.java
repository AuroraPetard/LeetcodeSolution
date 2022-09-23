package com.AuroraPetard.MAP_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution1 {
    // https://leetcode.cn/problems/find-all-anagrams-in-a-string/

    public List<Integer> findAnagrams(String s, String p) {
        Map<String,List<Integer>> map=new HashMap<>();

        for(int i=0; i< s.length()-p.length();i++){
            String sub=s.substring(i,i+p.length());
            int[] count=new int[26];

            for(int j=0;j<sub.length();j++){
                count[sub.charAt(j)-'a']++;
            }

            StringBuffer sb=new StringBuffer();

            for(int k=0;k<26;k++){
                if(count[k]!=0){
                    sb.append((char)('a'+k));
                    sb.append(count[k]);
                }
            }
            String key=sb.toString();

            List<Integer> list=map.getOrDefault(key,new ArrayList<Integer>());

            list.add(i);

            map.put(key,list);
        }

        int[] count=new int[26];

        for(int j=0;j<p.length();j++){
            count[p.charAt(j)-'a']++;
        }

        StringBuffer sb=new StringBuffer();

        for(int k=0;k<26;k++){
            if(count[k]!=0){
                sb.append((char)('a'+k));
                sb.append(count[k]);
            }
        }
        String key=sb.toString();

        return map.get(key);

    }
}
