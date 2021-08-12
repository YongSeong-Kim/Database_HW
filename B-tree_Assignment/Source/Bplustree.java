// Searching on a B+ tree in Java
package workspace;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;

public class Bplustree {
  int m; // 차수.
  InternalNode root; // 루트.
  LeafNode firstLeaf;


  public Bplustree(int m) {
    this.m = m;
    this.root = null;
  }
  
  
  public void searchKey(int key)
  {
	  boolean find = false;
	  if(root == null)
	  {
		  for(int i = 0; i < firstLeaf.order; i++)
		  {
			  if(firstLeaf.data[i].key == key)
			  {
				  find = true;
				  System.out.println(firstLeaf.data[i].value);
				  break;
			  }
		  }
		  if(find == false) System.out.println("NOT FOUND");
	  }
	  else
	  {
		  InternalNode in = root;
		  for(int i = 0; i < in.num - 1; i++)
		  {
			  System.out.print(in.key[i]);

			  if(i != in.num - 2)
			  {
				  System.out.print(",");
			  }
		  }
		  System.out.println();
		  int idx;
		  for(idx = 0; idx < in.num - 1; idx++)
		  {
			  if(key < in.key[idx]) break;
		  }
		  
		  Node child = in.child_pointers[idx];
		  
		  if(child instanceof LeafNode)
		  {
			  LeafNode lf = (LeafNode) child;
			  for(int i = 0; i < lf.order; i++ )
			  {
				  if(key == lf.data[i].key)
				  {
					  find = true;
					  System.out.println(lf.data[i].value);
					  break;
				  }
			  }
			  if(find = false) System.out.println("NOT FOUND");
		  }
		  else
		  {
			  searchKey( (InternalNode) child, key);
		  }
		  
	  } 
  }
  
  public void searchKey(InternalNode in, int key)
  {
	  boolean find = false;
	  for(int i = 0; i < in.num - 1; i++)
	  {
		  System.out.print(in.key[i]);

		  if(i != in.num - 2)
		  {
			  System.out.print(",");
		  }
	  }
	  System.out.println();
	  int idx;

	  
	  for (idx = 0; idx < in.num - 1; idx++)
	  {
		  if(key < in.key[idx])
		  {
			  break; // 맞는 인덱스를 찾는 과정.
		  }
	  }
	  
	  Node child = in.child_pointers[idx];
	  
	  if(child instanceof LeafNode)
	  {
		  LeafNode lf = (LeafNode) child;
		  for(int i = 0; i < lf.order; i++ )
		  {
			  if(key == lf.data[i].key)
			  {
				  find = true;
				  System.out.println(lf.data[i].value);
				  break;
			  }
		  }
		  if(find == false) System.out.println("NOT FOUND");
		   // LeafNode를 리턴.
	  }
	  else
	  {
		  searchKey((InternalNode) in.child_pointers[idx], key); // 재귀함수.
	  }
		  
  }
  
  
  
  
  
  public void rangedSearch(int start, int end)
  {
	  LeafNode lf = findLeaf(start);
	  
	  while(lf != null)
	  {
		  for(int i = 0; i < lf.order; i++)
		  {
			  if(lf.data[i].key >= start && lf.data[i].key <= end)
			  {
				  System.out.println(lf.data[i].key+","+lf.data[i].value);
			  }
			  else if( lf.data[i].key > end) return;
		  }
		  lf = lf.rightSibling;
	  }
  }
  
  
  
  public void print()
  {
	  if(root == null)
	  {
		  for(int i = 0; i< firstLeaf.order; i++)
		  {
			  System.out.print(firstLeaf.data[i].key + " ");
		  }
		  System.out.println();
	  }
	  else
	  {
		  for(int i = 0; i < root.num -1; i++)
		  {
			  System.out.print(root.key[i] +" ");
		  }
		  System.out.println();
		  for(int i = 0; i < root.num; i++)
		  {
			  print(root.child_pointers[i]);
		  }
	  }
  }
  
  public void print(Node n)
  {
	  if(n instanceof LeafNode)
	  {
		  LeafNode ln = (LeafNode) n;
		  for(int i = 0; i < ln.order; i++)
		  {
			  System.out.print(ln.data[i].key + " ");
		  }
		  System.out.println();
		  return;
	  }
	  else
	  {
		  InternalNode in = (InternalNode) n;
		  for(int i = 0; i < in.num -1; i++)
		  {
			  System.out.print(in.key[i] + " ");
		  }
		  System.out.println();
		  for(int i = 0; i < in.num; i++)
		  {
			  print(in.child_pointers[i]);
		  }
		  
	  }
	  
  }
  
  public LeafNode leftMostChild()
  {
	  if(root == null)
	  {
		  return firstLeaf;
	  }
	  else
	  {
		  Node node = root.child_pointers[0];
		  
		  while(! (node instanceof LeafNode) )
		  {
			  InternalNode in =(InternalNode) node;
			  node = in.child_pointers[0];
		  }
		  return (LeafNode) node;
	  }
  }
  
  public void saveBplustree(String index_file)
  {
	  LeafNode lf = leftMostChild();
	  PrintWriter outputStream = null;
	  
	  try {
		  outputStream = new PrintWriter(new FileOutputStream(index_file));
	  } catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		  e.printStackTrace();
	  }
	  
	  outputStream.println(this.m);
	  
	  while(lf != null)
	  {
		  for(int i = 0; i < lf.order; i++)
		  {
			  outputStream.println(lf.data[i].key +"," +lf.data[i].value);
		  }
		  lf = lf.rightSibling;
	  }
	  
	  outputStream.close();  
  }
  
  
  public void splitInternalNode(InternalNode n)
  {
	  InternalNode parent = n.parent;
	  
	  int mid = getMidpoint();
	  int newKey = n.key[mid];
	  Integer[] splitedKey = splitKey(n.key, mid);
	  Node[] splitedChild = splitChild(n, mid);
	  
	  n.num  = nullSearch(n.child_pointers);
	  
	  InternalNode in = new InternalNode(this.m, splitedKey, splitedChild);
	  
	  for(int i = 0; i < splitedChild.length; i++)
	  {
		  if(splitedChild[i] != null) splitedChild[i].parent = in;
	  }
	  
	  if(parent == null)
	  {
		  Integer[] key = new Integer[this.m];
		  key[0] = newKey;
		  InternalNode newRoot = new InternalNode(this.m, key);
		  newRoot.addChild(n);
		  newRoot.addChild(in);
		  
		  n.parent = newRoot;
		  in.parent = newRoot;
		  
		  this.root = newRoot;
	  }
	  else
	  {
		  parent.key[parent.num - 1] = newKey;
		  Arrays.sort(parent.key, 0, parent.num);
		  
		  int pointerIdx = parent.findIdxPointer(n) + 1;
		  parent.insertChild(in, pointerIdx);
		  in.parent = parent;
		  
	  }
	  
  }
  
  public Node[] splitChild(InternalNode n, int mid)
  {
	  Node[] p = n.child_pointers;
	  Node[] splitedChild = new Node[this.m + 1];
	  
	  for(int i = mid + 1; i < p.length; i++)
	  {
		  splitedChild[i - mid - 1] = p[i];
		  n.removePointer(i);
	  }
	  return splitedChild;
  }
  
  public Integer[] splitKey(Integer[] key, int mid)
  {
	  Integer[] splitedKey = new Integer[this.m];
	  key[mid] = null;
	  
	  for(int i = mid + 1; i < key.length; i++)
	  {
		  splitedKey[i - mid - 1] = key[i];
		  key[i] = null;
	  }
	  return splitedKey;
  }
  
  public LeafNode findLeaf(int key)
  {
	  Integer[] keys = this.root.key;
	  int i;
	  
	  for( i = 0; i < this.root.num - 1; i++)
	  {
		  if(key < keys[i]) break;
	  }
	  
	  Node child = this.root.child_pointers[i];
	  
	  if(child instanceof LeafNode)  
		  return (LeafNode) child;
	  else 
		  return findLeaf( (InternalNode) child, key);
	  
  }
  public LeafNode findLeaf(InternalNode node, int key) //Overloading.
  {
	  Integer[] keys = node.key;
	  int i;
	  
	  for (i = 0; i < node.num - 1; i++)
	  {
		  if(key < keys[i])
		  {
			  break;
		  }
	  }
	  
	  Node child = node.child_pointers[i];
	  
	  if(child instanceof LeafNode)
	  {
		  return (LeafNode) child; 
	  }
	  else
	  {
		  return findLeaf((InternalNode) node.child_pointers[i], key);
	  }
  }

  public int nullSearch(Data[] data)
  {
	  for(int i = 0; i < data.length; i++)
	  {
		  if(data[i] == null)
			  return i;
	  }
	  return -1;
  }
  
  public int nullSearch(Node[] n)
  {
	  for(int i = 0; i< n.length; i++)
	  {
		  if(n[i] == null) return i;
	  }
	  
	  return -1;
  }
  public int getMidpoint()
  {
	  return (int) Math.ceil((this.m + 1 ) / 2.0) -1;
  }
  
  public void sortData(Data[] data)
  {
	  Arrays.sort(data, new Comparator<Data>() {

		@Override
		public int compare(Data o1, Data o2) {
			if(o1 == null && o2 == null)
				return 0;
			if(o1 == null)
				return 1;
			if(o2 == null)
				return -1;
			return o1.compareTo(o2);
		}
	  });
  }
  
  public Data[] splitData(LeafNode l, int mid)
  {
	  Data[] data = l.data;
	  Data[] splitedData = new Data[this.m];
	  
	  for(int i = mid; i < data.length; i++)
	  {
		  splitedData[i - mid] = data[i];
		  l.deleteLeafNode(i);
	  }
	  return splitedData;
  }
  
  public void changeInternalNode(LeafNode ln)
	{	
		InternalNode in =ln.parent;
		
		if(root == null) return;
		
		while( in != root && in.num < in.minNum)
		{
			  
			InternalNode ancestor = in.parent;
			int borrowLeftPointer = ancestor.findIdxPointer(in) - 1;
			int borrowRightPointer = ancestor.findIdxPointer(in) + 1;
			
			InternalNode parentLeft;
			InternalNode parentRight;
			if(borrowLeftPointer < 0 ) 
			{
				parentLeft = null;
				parentRight = ( InternalNode )ancestor.child_pointers[borrowRightPointer];
			}
			else if(borrowRightPointer == ancestor.maxNum) 
			{
				parentLeft = ( InternalNode )ancestor.child_pointers[borrowLeftPointer];
				parentRight = null; 
			}
			else
			{
				parentLeft = ( InternalNode )ancestor.child_pointers[borrowLeftPointer];
				parentRight = ( InternalNode )ancestor.child_pointers[borrowRightPointer];
			}

			
			if(parentLeft != null && parentLeft.minNum < parentLeft.num) 
			{
				  
				in.borrowLeftInternalNode(ancestor, parentLeft, borrowLeftPointer);
				in = ancestor;
				ancestor = ancestor.parent;  
				  
			}
			else if(parentRight != null && parentRight.minNum < parentRight.num) 
			{
				in.borrowRightInternalNode(ancestor, parentRight, borrowLeftPointer);
				  
				in = ancestor;
				  
				ancestor = ancestor.parent;
			}
			else if( ancestor == root )
			{
				int changeHeight = ancestor.num - 2;
				  
				if(changeHeight == 0) 
				{
					if(parentRight == null) 
					{
						parentLeft.shrinkHeight(ancestor, in);
						root = parentLeft;	
						parentLeft.parent = null;
					}
					else
					{
						parentRight.shrinkHeight(ancestor, in);
						root = parentRight;
						parentRight.parent = null;
					}
					break;
				}
				else
				{
					if(parentLeft != null) 
					{
						in.rootKeyLeftBorrow(ancestor, parentLeft, borrowLeftPointer);
						in = ancestor;
						ancestor = ancestor.parent;
					}
					else
					{
						in.rootKeyRightBorrow(ancestor, parentRight, borrowLeftPointer);
						in = ancestor;
						ancestor = ancestor.parent;
					}
				  }   
			  }
			else
			{
				
				if(parentLeft != null)
				{
					in.rootKeyLeftBorrow(ancestor, parentLeft, borrowLeftPointer);
					in = ancestor;
					ancestor = ancestor.parent;
				}
				else
				{
					
					in.rootKeyRightBorrow(ancestor, parentRight, borrowLeftPointer);
					in = ancestor;
					ancestor = ancestor.parent;
				}
			}
			
		  }
		
	}
  
  
  public void insert(int key, int value)
  {
	  if(firstLeaf == null) 
	  {
		LeafNode l = new LeafNode(m);
		l.data[l.order++] = new Data(key, value);
		this.firstLeaf = l;
	  }
	  else
	  {
		  LeafNode l = ( this.root == null ) ? this.firstLeaf : findLeaf(key);  
		  if(! l.insert(new Data(key, value)))
		  {
			  l.data[l.order] = new Data(key, value);
			  l.order++;
			  sortData(l.data);
			  
			  int mid = getMidpoint();
			  
			  Data[] s = splitData(l, mid);
			  
			  if( l.parent == null) 
			  {
				  Integer[] parent_key = new Integer[this.m];
				  parent_key[0] = s[0].key;
				  InternalNode parent = new InternalNode(this.m, parent_key);
				  l.parent = parent;
				  parent.addChild(l);
			  }
			  else 
			  {
				  int k = s[0].key;
				  l.parent.key[l.parent.num - 1] = k;
				  Arrays.sort(l.parent.key, 0, l.parent.num );
			  } 
			  
			  LeafNode newLeaf = new LeafNode(this.m, s, l.parent);
			  
			  int pointerIdx = l.parent.findIdxPointer( l ) + 1;
			  
			  l.parent.insertChild(newLeaf, pointerIdx);
			  
			  newLeaf.rightSibling = l.rightSibling;
			  l.rightSibling = newLeaf;
			  
			  if(this.root == null)
			  {
				  this.root = l.parent;
			  }
			  else
			  {
				  InternalNode node = l.parent;
				  while(node != null)
				  {
					  if(node.num == node.maxNum + 1)
					  {
						  splitInternalNode(node);
					  }
					  else break;
					  
					  node = node.parent;
				  }
			  }
		  }
	  }  
  }
  
  public InternalNode aboveInternalNode(InternalNode in, int key) // 지우고자 하는 delete키가 내부노드에 존재하는지 확인.
  {  
	  while(in != null)
	  {
		  for(int i = 0; i < in.num - 1; i++)
		  {
			  if(in.key[i] == key) return in;
		  }
		  in = in.parent;
	  }
	  return in;
  }
  

  
  
  public void delete(int key)
  {
	  LeafNode ln = ( this.root == null ) ? this.firstLeaf : findLeaf(key); // findLeaf함수를 만들어서 key값이 있는 LeafNode 찾기.
	  
	  if(ln == null && firstLeaf == null) 
	  {
		  System.out.println(key + " NOT FOUND");
		  return;
	  }
	  
	  boolean find = false;
	  for(int i = 0; i < ln.order; i++)
	  {
		  if(ln.data[i].key == key) find = true;
	  }
	  
	  if(find == false) 
	  {
		  System.out.println(key + " NOT FOUND");
		  return;
	  }
	  InternalNode in = aboveInternalNode(ln.parent, key); 
	  
	  int deleteIdx;
	  
	  for( deleteIdx = 0; deleteIdx < ln.order; deleteIdx++)
	  {
		  if( ln.data[deleteIdx].key == key ) break;
	  }
	  
	  
	  if(in == null && ln.minOrder < ln.order || root == null && ln == firstLeaf)
	  {
		  ln.deleteLeafNode(deleteIdx);
		  sortData(ln.data);
	  }
	  else if(in == null && ln.minOrder == ln.order) 

	  {
  
		  
		  LeafNode leftSibling = ln.leftSibling(ln);
		  
		  if(leftSibling != null && leftSibling.order > leftSibling.minOrder)
		  {
			  ln.borrowLeftLeafNode(leftSibling, deleteIdx);
			  
		  }
		  else if(ln.rightSibling != null && ln.rightSibling.order > ln.rightSibling.minOrder && ln.rightSibling.parent == ln.parent) // 지우려는 LeafNode의 오른쪽에서 빌려오기.
		  { 
			  ln.borrowRightLeafNode(ln.rightSibling, deleteIdx);
		  }
		  else
		  {
			  if(leftSibling != null) 
			  {
				 ln.mergeLeftLeafNode(leftSibling, deleteIdx);
				 
				 if(leftSibling.parent == root && leftSibling.parent.num == 1)
				  {
					  root = null;
					  firstLeaf = leftSibling;
					  leftSibling.parent = null;
				  }
			  }
			  else
			  {  
				  ln.mergeRightLeafNode(deleteIdx);
				  if(ln.parent == root  && ln.parent.num == 1)
				  {
					  root = null;
					  firstLeaf = ln;
					  ln.parent = null;
				  }
			  }
			  changeInternalNode(ln);
		  }
	  }
	  else if(in == ln.parent && ln.minOrder < ln.order) 
	  {
		  
		  ln.deleteLeafNode(deleteIdx);
		  sortData(ln.data);
		  int i;
		  for( i = 0; i< in.num - 1; i++)
		  {
			  if(in.key[i] == key) break;
		  }
		  
		  in.key[i] = ln.data[0].key;
	  }
	  else if(in == ln.parent && ln.minOrder == ln.order)
	  {
		  LeafNode leftSibling = ln.leftSibling(ln);
		  
		  if(leftSibling != null && leftSibling.order > leftSibling.minOrder) 
		  {
			  ln.borrowLeftLeafNode(leftSibling, deleteIdx);
		  }
		  else if(ln.rightSibling != null && ln.rightSibling.order > ln.rightSibling.minOrder && ln.rightSibling.parent == ln.parent) // 지우려는 LeafNode의 오른쪽에서 빌려오기.
		  { 
			  
			  ln.borrowRightLeafNode(ln.rightSibling, deleteIdx);
			  
			  int firstNewParentKey = ln.data[0].key;
			  int firstKeyIndex = ln.parent.findIdxPointer(ln) - 1;
			  
			  ln.parent.key[firstKeyIndex] = firstNewParentKey;	  
		  }
		  else
		  {
			  ln.mergeLeftLeafNode(leftSibling, deleteIdx);
			  
			  if(leftSibling.parent == root && leftSibling.parent.num == 1)
			  {
				  root = null;
				  firstLeaf = leftSibling;
				  leftSibling.parent = null;
			  }
			  changeInternalNode(leftSibling);   
		  }
	  }
	  else if( in != null ) 
	  {
		  if(ln.order > ln.minOrder)
		  {  
			  ln.deleteLeafNode(deleteIdx);
			  sortData(ln.data);
			  
			  for(int i = 0; i < in.num -1; i++)
			  {
				  if(in.key[i] == key) 
				  {
					  in.key[i] = ln.data[0].key;
					  break;
				  }
			  }
		  }
		  else
		  {			  
			  if(ln.rightSibling.order > ln.rightSibling.minOrder)
			  {
				  ln.borrowRightLeafNode(ln.rightSibling, deleteIdx);
				  for(int i = 0; i< in.num -1; i++)
				  {
					  if(key == in.key[i]) in.key[i] = ln.data[0].key; 
				  }
				  
			  }
			  else ln.mergeRightLeafNode(in, deleteIdx, key);
			  
			  changeInternalNode(ln);
  
		  }
	  }
	  
  }
  
  
  public class InternalNode extends Node{

		 
		private int maxNum; // key의 최대개수.
		private int minNum;
		private int num;//현재 포인터의 개수.
		private Integer[] key; // 현재 키값.
		private Node[] child_pointers;
		
		public InternalNode(int m, Integer[] key)
		{
			this.maxNum = m ;//포인터의 개수.
			this.minNum = (int) (Math.ceil(m / 2.0) );
			this.num = 0;
			this.key = key; // 현재 키값.
			this.child_pointers = new Node[ m + 1 ];
		}
		
		public InternalNode(int m, Integer[] key, Node[] n)
		{
			this.maxNum = m ;//포인터의 개수.
			this.minNum = (int) (Math.ceil(m / 2.0) );
			this.num = nullSearch(n);
			this.key = key; // 현재 키값.
			this.child_pointers = n;
		}
		
		public void addChild(Node n)
		{
			this.child_pointers[num] = n;
			this.num++;
		}
		
		public int findIdxPointer(Node n)
		{
			for(int i = 0; i < child_pointers.length; i++)
			{
				if(child_pointers[i] == n) return i;
			}	
			return -1;
		}
		
		public void insertChild (Node n, int idx)
		{
			for(int i = num - 1; i >= idx; i--)
			{
				child_pointers[i + 1] = child_pointers[i];
			}
			this.child_pointers[idx] = n;
			this.num++;
		}
		

		public void removePointer(int idx)
		{
			this.child_pointers[idx] = null;
			this.num--;
		}
		
		public void shrinkHeight(InternalNode ancestor, InternalNode in)
		{
			int borrowKey = ancestor.key[0];
			  
			int parentIdx;
			  
			parentIdx = this.num - 1; 
			  
			this.key[parentIdx] = borrowKey;
			parentIdx++;
			  
			for(int i = 0 ; i < in.num - 1; i++)
			{
				this.key[parentIdx] = in.key[i];
				parentIdx++;
			}
			Arrays.sort(this.key, 0, parentIdx);
			
			
			
			if(this.key[0] < in.key[0]) 
			{
				parentIdx = this.num;
				
				for(int i = 0; i < in.num; i++)
				{
					this.child_pointers[parentIdx] = in.child_pointers[i];
					in.child_pointers[i].parent = this;
					parentIdx++;
				}

				this.num = parentIdx;
			}
			else
			{
				parentIdx = this.num - 1;
				for(int i = this.num - 1 + in.num; i > in.num - 1; i--)
				{
					this.child_pointers[i] = this.child_pointers[parentIdx];
					
					parentIdx--;
				}
				this.num = this.num + in.num;
				
				parentIdx = 0;
				for(int i = 0 ; i < in.num; i++)
				{
					this.child_pointers[parentIdx] = in.child_pointers[i];
					in.child_pointers[i].parent = this;
					parentIdx++;
				}
				
			}
			
		}
		
		public void rootKeyLeftBorrow(InternalNode ancestor, InternalNode parentLeft, int idx)
		{
			int borrowKey = ancestor.key[idx];
			ancestor.key[idx] = null;
			
			for(int i = idx; i < ancestor.num - 1; i++)
			{
				ancestor.key[i] = ancestor.key[i+1];
			}

			  
			int leftIdx;
			  
			leftIdx = parentLeft.num - 1;
			  
			parentLeft.key[leftIdx] = borrowKey; 
			leftIdx++;
			  
			for(int i = 0 ; i < this.num - 1; i++)
			{
				parentLeft.key[leftIdx] = this.key[i];
				leftIdx++;
			}
			  
			leftIdx = parentLeft.num;
			
			for(int i = 0 ; i < this.num; i++) 
			{
				parentLeft.child_pointers[leftIdx] = this.child_pointers[i];
				this.child_pointers[i].parent = parentLeft; 
				leftIdx++;
			}
			parentLeft.num = leftIdx;
			  
			for(int i = idx + 1; i < ancestor.num - 1 ;i++)
			{
				ancestor.child_pointers[i] = ancestor.child_pointers[i + 1];
			}
			 
			ancestor.num--;
		}
		
		public void rootKeyRightBorrow(InternalNode ancestor, InternalNode parentRight, int borrowLeftPointer)
		{
			int borrowKey = ancestor.key[borrowLeftPointer + 1];
			ancestor.key[borrowLeftPointer + 1] = null;

			for(int i = 0; i < ancestor.num - 1; i++)
			{
				ancestor.key[i] = ancestor.key[i+1];
			}
			
			int parentIdx;
			  
			parentIdx = this.num - 1; 
			  
			this.key[parentIdx] = borrowKey;
			parentIdx++;
			  
			for(int i = 0 ; i < parentRight.num - 1; i++)
			{
				this.key[parentIdx] = parentRight.key[i];
				parentIdx++;
			}
			  
			parentIdx = this.num;
			for(int i = 0 ; i < parentRight.num; i++) 
			{
				this.child_pointers[parentIdx] = parentRight.child_pointers[i];
				parentRight.child_pointers[i].parent = this;
				parentIdx++;
			}
			this.num = parentIdx;
			  
			for(int i = borrowLeftPointer + 2 ; i < ancestor.num ;i++)
			{
				ancestor.child_pointers[i] = ancestor.child_pointers[i + 1];
			}
			 	
			ancestor.num--;
			 
		}
		public void borrowLeftInternalNode(InternalNode ancestor, InternalNode parentLeft, int borrowLeftPointer)
		{
			int ancestorKey = parentLeft.key[parentLeft.num - 2];
			  
			int parentKey = ancestor.key[borrowLeftPointer]; 
			  
			Node tempNode = parentLeft.child_pointers[parentLeft.num-1];
			  
			parentLeft.key[parentLeft.num - 2] = null;
			parentLeft.child_pointers[parentLeft.num-1] = null;
			
			parentLeft.num --;
			
			this.key[this.num - 1] = parentKey;
			
			Arrays.sort(this.key, 0, this.num);
			
			for(int i = this.num; i > 0; i--)
			{
				this.child_pointers[i] = this.child_pointers[i-1];
			}	
			tempNode.parent = this;
			this.child_pointers[0] = tempNode;
			this.num++; 

			ancestor.key[borrowLeftPointer] = ancestorKey;
			
		}
		
		
		public void borrowRightInternalNode(InternalNode ancestor, InternalNode parentRight, int borrowLeftPointer)
		{
			//완료
			int ancestorKey = parentRight.key[0];
			  
			  int parentKey = ancestor.key[borrowLeftPointer + 1];
			  
			  Node tempNode = parentRight.child_pointers[0]; 
			  
			  parentRight.key[0] = null;
			  parentRight.child_pointers[0] = null;
			  for(int i = 0; i < parentRight.num - 1; i++ )
			  {
				  parentRight.key[i] = parentRight.key[i + 1];
				  parentRight.child_pointers[i] = parentRight.child_pointers[i + 1];
			  }
			  
			  parentRight.num --;
			  
			  this.key[this.num - 1] = parentKey;

			  Arrays.sort(this.key, 0, this.num);		
			  tempNode.parent = this;
			  this.child_pointers[this.num] = tempNode;
			  this.num++;
			  
			  ancestor.key[borrowLeftPointer + 1] = ancestorKey;
		}

		

	}
  
  public class LeafNode extends Node{

		private int maxOrder; // 최대 키의 개수.
		private int minOrder; // 최소 키의 개수.
		private int order;//현재 데이터의 개수.
		LeafNode rightSibling;
		private Data[] data;
		
		public LeafNode(int m) 
		{
			this.maxOrder = m - 1;// 2
			this.minOrder = (int) (Math.ceil(m / 2.0) - 1); 
			this.order = 0;
			this.data = new Data[m]; 
		}
		
		public LeafNode(int m, Data[] d, InternalNode parent)
		{
			this.maxOrder = m - 1;
			this.minOrder = (int) (Math.ceil(m / 2.0) - 1);
			this.order = nullSearch(d);
			this.data = d; 
			this.parent = parent;
		}
		
		public void deleteLeafNode(int idx)
		{
			this.data[idx] = null;
			this.order--;
		}
		
		public boolean isFull()
		{
			if(maxOrder == order)
			{
				return true;
			}
			else return false;
		}
		
		public boolean insert(Data d) 
		{
			if(this.isFull() ) return false;
			else
			{
				this.data[order] = d;
				order++;
				Arrays.sort(this.data, 0, order);
				return true;
			}
		}
		
		
		public LeafNode leftSibling(LeafNode ln)
		{
			int leftSiblingIdx = ln.parent.findIdxPointer(ln) - 1;
			
			if(leftSiblingIdx < 0) return null;
			
			return (LeafNode) ln.parent.child_pointers[leftSiblingIdx];
		}
		
		public void borrowLeftLeafNode(LeafNode leftSibling, int deleteIdx)
		{
			int borrowIdx = leftSibling.order - 1;
			  Data borrowData = leftSibling.data[borrowIdx];
			  this.deleteLeafNode(deleteIdx);
			  sortData(this.data);
			  this.insert(borrowData);
			  leftSibling.deleteLeafNode(borrowIdx);
			  
			  int newParentKey = this.data[0].key;
			  
			  int keyIndex = this.parent.findIdxPointer(this) - 1;
			  
			  this.parent.key[keyIndex] = newParentKey; 
		}
		
		public void borrowRightLeafNode(LeafNode rightSibling, int deleteIdx) 
		{
			 int borrowIdx = 0; //
			  Data borrowData = rightSibling.data[0];
			  deleteLeafNode(deleteIdx);
			  sortData(data);
			  
			  
			  insert(borrowData);
			  rightSibling.deleteLeafNode(borrowIdx);
			  
			  sortData(rightSibling.data);
			  int newParentKey = rightSibling.data[0].key;
			  
			  int keyIndex = parent.findIdxPointer(rightSibling) - 1;
			  
			  this.parent.key[keyIndex] = newParentKey;
		}
		
		public void mergeLeftLeafNode(LeafNode leftSibling, int deleteIdx)
		{
			
			  deleteLeafNode(deleteIdx);
			  sortData(this.data);
			  
			  for(int i = 0; i < this.order; i++)
			  {
				  leftSibling.insert(this.data[i]);
			  }
			  
			  leftSibling.rightSibling = this.rightSibling;
			  
			  int pointer = this.parent.findIdxPointer(this);
			  
			  for(int i = pointer; i < this.parent.num; i++) 
			  {
				  this.parent.child_pointers[i ] = this.parent.child_pointers[i + 1];
			  }
			  
			  for(int i = pointer - 1; i < this.parent.num - 1; i++)
			  {
				  this.parent.key[i] = this.parent.key[i + 1];
			  }
			  this.parent.num--;
			
		}
		
		public void mergeRightLeafNode(int deleteIdx)
		{
			
			  for(int i = 0; i < this.rightSibling.order; i++)
			  {
				  this.insert(this.rightSibling.data[i]);
			  }
			  
			  this.data[deleteIdx] = null;
			  this.order--;
			  sortData(this.data);
			  
			  int pointer = this.parent.findIdxPointer(this.rightSibling);

			  this.rightSibling = this.rightSibling.rightSibling;
			  
			  for(int i = pointer; i < this.parent.num - 1; i++) 
			  {
				  this.parent.child_pointers[i] = this.parent.child_pointers[i + 1];
			  }
			  
			  
			  for(int i = pointer - 1; i < this.parent.num - 2; i++) 
			  {
				  this.parent.key[i] = this.parent.key[i + 1];
			  }
			  this.parent.num--;
			
			
			}
		
		
		public void mergeRightLeafNode(InternalNode in, int deleteIdx,int key) 
		{
			  
			  for(int i = 0; i < this.rightSibling.order; i++)
			  {
				  this.insert(this.rightSibling.data[i]);
			  }
			  
			  this.data[deleteIdx] = null;
			  this.order--;
			  sortData(this.data);
			  
			  int pointer = this.parent.findIdxPointer(this.rightSibling);

			  this.rightSibling = this.rightSibling.rightSibling;
			  
			  for(int i = pointer; i < this.parent.num - 1; i++) 
			  {
				  this.parent.child_pointers[i] = this.parent.child_pointers[i + 1];
			  }
			  
			  
			  for(int i = pointer - 1; i < this.parent.num - 2; i++)
			  {
				  this.parent.key[i] = this.parent.key[i + 1];
			  }
			  this.parent.num--;
			  
			  for(int i = 0; i < in.num -1; i ++)
			  {
				  if(in.key[i] == key)
				  {
					  in.key[i] = this.data[0].key;
				  }
			  }			
		}

  }
  public class Data implements Comparable<Data>
  {
	int key;
	int value;
	public Data(int key, int value)
	{
		this.key = key;
		this.value = value;
	}
	public int compareTo(Data d)
	{
		if(key == d.key) return 0;
		else if(key > d.key) return 1;
		else return -1;
	}
  }

}
