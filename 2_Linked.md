#### [203. 移除链表元素](https://leetcode.cn/problems/remove-linked-list-elements/)

给你一个链表的头节点 `head` 和一个整数 `val` ，请你删除链表中所有满足 `Node.val == val` 的节点，并返回 **新的头节点** 。

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public ListNode removeElements(ListNode head, int val) {
        
        // 首先判断头节点
        if(head==null){
            return null;
        }
    
        // 创建一个虚拟头节点 指向head 
        // 便于操作头节点 万一头节点的值等于val 还得专门处理头节点
        ListNode dummy=new ListNode(-1,head);
        
        // 删除一个节点需要知道它的前一个节点
        //前面一个节点
        ListNode pre=dummy;
        
        //当前节点
        ListNode cur=head;
        
        //null.next 没有意义 
        //所以判断当前是不是为空
        while(cur!=null){
            if(cur.val==val){
                //删除当前节点 前一个节点的next指向当前节点的下一个
                pre.next=cur.next;
            }else{
                //迭代
                pre=cur;
            }
            //迭代
            cur=cur.next;
        }
        
        //最后还是要返回真正的头节点
        return dummy.next;

    }
}
```



#### [707. 设计链表](https://leetcode.cn/problems/design-linked-list/)

你可以选择使用单链表或者双链表，设计并实现自己的链表。

单链表中的节点应该具备两个属性：`val` 和 `next` 。`val` 是当前节点的值，`next` 是指向下一个节点的指针/引用。

如果是双向链表，则还需要属性 `prev` 以指示链表中的上一个节点。假设链表中的所有节点下标从 **0** 开始。

实现 `MyLinkedList` 类：

- `MyLinkedList()` 初始化 `MyLinkedList` 对象。

- `int get(int index)` 获取链表中下标为 `index` 的节点的值。如果下标无效，则返回 `-1` 。

- `void addAtHead(int val)` 将一个值为 `val` 的节点插入到链表中第一个元素之前。在插入完成后，新节点会成为链表的第一个节点。

- `void addAtTail(int val)` 将一个值为 `val` 的节点追加到链表中作为链表的最后一个元素。

- `void addAtIndex(int index, int val)` 将一个值为 `val` 的节点插入到链表中下标为 `index` 的节点之前。如果 `index` 等于链表的长度，那么该节点会被追加到链表的末尾。如果 `index` 比长度更大，该节点将 **不会插入** 到链表中。

- `void deleteAtIndex(int index)` 如果下标有效，则删除链表中下标为 `index` 的节点。

  ```java
  // 单链表
  class ListNode{
      int val;
      ListNode next;
      ListNode(){};
      ListNode(int val){
          this.val=val;
      }
  }
  
  class MyLinkedList {
      
      int size;
      ListNode head;
      
      public MyLinkedList() {
          head=new ListNode(0);
          size=0;
      }
      
      public int get(int index) {
          // =取不到
          if(index<0 || index>=size){
              return -1;
          }
          
          ListNode cur=head;
          for(int i=0;i<=index;i++){
              cur=cur.next;
          }
          
          return cur.val;
          
      }
      
      public void addAtHead(int val) {
          addAtIndex(0,val);
  
      }
      
      public void addAtTail(int val) {
          addAtIndex(size,val);
  
      }
      
      public void addAtIndex(int index, int val) {
          if(index>size){
              return ;
          }
          
          if(index<=0){
              index=0;
          }
          
          // 记得增加size
          size++;
          
          
          ListNode toAdd=new ListNode(val);
          
          ListNode cur=head;
          
          for(int i=0;i<index;i++){
              cur=cur.next;
          }
          
          toAdd.next=cur.next;
          cur.next=toAdd;
          
  
      }
      
      public void deleteAtIndex(int index) {
          if(index<0 || index>=size){
              return ;
          }
          
          size--;
          
          if(index==0){
              head=head.next;
              // 返回出去
              return ;
          }
          
          ListNode cur=head;
          
          for(int i=0;i<index;i++){
              cur=cur.next;
          }
          
          cur.next=cur.next.next;
      }
  }
  
  /**
   * Your MyLinkedList object will be instantiated and called as such:
   * MyLinkedList obj = new MyLinkedList();
   * int param_1 = obj.get(index);
   * obj.addAtHead(val);
   * obj.addAtTail(val);
   * obj.addAtIndex(index,val);
   * obj.deleteAtIndex(index);
   */
  ```

   双链表实现

  ```java
  
  ```

  