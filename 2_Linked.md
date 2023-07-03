  [203. 移除链表元素](https://leetcode.cn/problems/remove-linked-list-elements/)

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
  //双链表
  class ListNode{
    int val;
      ListNode next,prev;
      ListNode() {};
      ListNode(int val){
          this.val = val;
      }
  }
  
  
  class MyLinkedList {  
  
      //记录链表中元素的数量
      int size;
      //记录链表的虚拟头结点和尾结点
      ListNode head,tail;
      
      public MyLinkedList() {
          //初始化操作
          this.size = 0;
          this.head = new ListNode(0);
          this.tail = new ListNode(0);
          //这一步非常关键，否则在加入头结点的操作中会出现null.next的错误！！！
          head.next=tail;
          tail.prev=head;
      }
      
      public int get(int index) {
          //判断index是否有效
          if(index<0 || index>=size){
              return -1;
          }
          ListNode cur = this.head;
          //判断是哪一边遍历时间更短
          if(index >= size / 2){
              //tail开始
              cur = tail;
              for(int i=0; i< size-index; i++){
                  cur = cur.prev;
              }
          }else{
              for(int i=0; i<= index; i++){
                  cur = cur.next; 
              }
          }
          return cur.val;
      }
      
      public void addAtHead(int val) {
          //等价于在第0个元素前添加
          addAtIndex(0,val);
      }
      
      public void addAtTail(int val) {
          //等价于在最后一个元素(null)前添加
          addAtIndex(size,val);
      }
      
      public void addAtIndex(int index, int val) {
          //index大于链表长度
          if(index>size){
              return;
          }
          //index小于0
          if(index<0){
              index = 0;
          }
          size++;
          //找到前驱
          ListNode pre = this.head;
          for(int i=0; i<index; i++){
              pre = pre.next;
          }
          //新建结点
          ListNode newNode = new ListNode(val);
          newNode.next = pre.next;
          pre.next.prev = newNode;
          newNode.prev = pre;
          pre.next = newNode;
          
      }
      
      public void deleteAtIndex(int index) {
          //判断索引是否有效
          if(index<0 || index>=size){
              return;
          }
          //删除操作
          size--;
          ListNode pre = this.head;
          for(int i=0; i<index; i++){
              pre = pre.next;
          }
        // 脑子里要有那个链表
          pre.next.next.prev = pre;
          pre.next = pre.next.next;
      }
  }
  
  ```
  
  #### [206. 反转链表](https://leetcode.cn/problems/reverse-linked-list/)
  
  给你单链表的头节点 `head` ，请你反转链表，并返回反转后的链表。
  
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
      public ListNode reverseList(ListNode head) {
          // 前面一个节点
          ListNode pre=null;
          
          // 临时节点 
          ListNode temp=null;
          
          // 当前节点
          ListNode cur=head;
          
          // 一直循环直到 cur!=null 
          while(cur!=null){
              
              // 存储下一个节点
              temp=cur.next;
              
              // 开始反转节点
              cur.next=pre;
              
              // 替换往下进行
              pre=cur;
              
              // cur=cur.next 
              // 因为上面 temp=cur.next; 
              // 所以这里直接使用了cur=temp；
              cur=temp;
          }
          
          // 返回反转后的头节点
          return pre;
      }
  }
  ```
  
  
  
  #### [24. 两两交换链表中的节点](https://leetcode.cn/problems/swap-nodes-in-pairs/)
  
  给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
  
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
      public ListNode swapPairs(ListNode head) {
          // 递归法
          if(head==null || head.next==null){
              return head;
          }
          
          
          // 假设三个节点  head 2 3
          
          
          // 第二个node 2
          ListNode secondNode=head.next;
          
          // 第三个node 3 
          // 交换 第一个和第三个  head 3 
          head.next=swapPairs(secondNode.next);
          
          // 中间的指向 head
          secondNode.next=head;
          
          
          // 返回头节点
          return secondNode;
      }
      
  }
  ```
  
  非递归    
  
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
      public ListNode swapPairs(ListNode head) {
          
        	// 相当于指向头节点 不能随便动 要不然找不到了 
         // 所以下面找了个变量替代它前行
          ListNode dummy=new ListNode(-1,head);
          ListNode prev=dummy;
          
          while(head!=null && head.next!=null){
              
              
              // prev head(first) second  second.next
              // 相邻的两两交换 12 34 56
              
              ListNode first=head;
              ListNode second=head.next;
              
              prev.next=second;
              head.next=second.next;
              second.next=head;
              
              prev=head;
              head=head.next;
          }
          return dummy.next;
      }
  }
  ```
  
  #### [19. 删除链表的倒数第 N 个结点](https://leetcode.cn/problems/remove-nth-node-from-end-of-list/)
  
  给你一个链表，删除链表的倒数第 `n` 个结点，并且返回链表的头结点。
  
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
      public ListNode removeNthFromEnd(ListNode head, int n) {
          // 双指针 快慢指针经典应用
          // 先让快指针走n步 然后快慢指针在同时一起走 
          // 当快指针走到头了 所在的位置就是带删除的位置
          // 当然需要知道当前删除节点的前一个节点
          
          // 使用dummy 便于统一操作
          ListNode dummy=new ListNode(-1,head);
          
          // dummy不能动 使用 变量替它负重前行
          ListNode fast=dummy;
          ListNode slow=dummy;
          
          while(n-->0){
              fast=fast.next;
          }
          
          
          // 定义一个变量用来保存带删除节点的前一个节点
          ListNode prev=null;
          
          while(fast!=null){
              prev=slow;
              slow=slow.next;
              fast=fast.next;
          }
          
          
          prev.next=slow.next;
          
          return dummy.next;
      }
  }
  ```
  
- #### [160. 相交链表](https://leetcode.cn/problems/intersection-of-two-linked-lists/)

  给你两个单链表的头节点 `headA` 和 `headB` ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 `null` 。

  ```java
  要找到两个单链表的相交节点，可以使用双指针的方法。假设链表 A 的长度为 m，链表 B 的长度为 n。如果两个链表相交，相交节点之后的长度是相同的。如果不相交，则相交节点之后的长度是不同的。
  
  我们可以使用两个指针 pA 和 pB，分别指向链表 A 和链表 B 的头节点。然后让它们同时向后移动，当一个指针到达链表的末尾时，将其重定向到另一个链表的头节点。这样，两个指针移动的总距离就相等了。如果存在相交节点，它们将在某个时刻相遇；如果不存在相交节点，它们将同时到达链表的末尾，此时它们都为 null。
  
    /**
   * Definition for singly-linked list.
   * public class ListNode {
   *     int val;
   *     ListNode next;
   *     ListNode(int x) {
   *         val = x;
   *         next = null;
   *     }
   * }
   */
  public class Solution {
      public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
          
          if(headA==null || headB==null){
              return null;
          }
          
          ListNode pA=headA;
          ListNode pB=headB;
          
          while(pA!=pB){
              pA= pA!=null ? pA.next : headB;
              pB= pB!=null ? pB.next : headA;
          }
          return pA;
      }
  }
  ```

  #### [142. 环形链表 II](https://leetcode.cn/problems/linked-list-cycle-ii/)

  给定一个链表的头节点  `head` ，返回链表开始入环的第一个节点。 *如果链表无环，则返回 `null`。*

  ```java
  /**
   * Definition for singly-linked list.
   * class ListNode {
   *     int val;
   *     ListNode next;
   *     ListNode(int x) {
   *         val = x;
   *         next = null;
   *     }
   * }
   */
  public class Solution {
      public ListNode detectCycle(ListNode head) {
          
          // 使用快慢指针来解决
          // 如果有环 那么快慢指针终会相遇
          // 快指针走两步 慢指针走一步 如果相遇 那么有环
          // 然后定义一个detect节点从head节点出发 
          // detect和slow相遇的就是环形入口
          
          ListNode slow = head;
          ListNode fast = head;
  
          // null.next 空指针
          // 判断是否存在环
          while (fast != null && fast.next != null) {
              slow = slow.next;
              fast = fast.next.next;
              if (slow == fast) {
                  break;
              }
          }
  
          if (fast == null || fast.next == null) {
              // 不存在环
              return null;
          }
  
          // 重新定义一个指针，与慢指针同时向后移动，直到相遇
          ListNode detect = head;
          while (detect != slow) {
              detect = detect.next;
              slow = slow.next;
          }
  
          return detect;
      }
  }
  ```

  
