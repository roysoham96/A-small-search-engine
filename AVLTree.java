import java.io.*;
public class AVLTree
{
	AVLNode root = new AVLNode();
	public AVLTree()
	{
		root = null;
	}

	public AVLTree(AVLNode r)
	{
		root = r;
	}

	public AVLNode returnRoot()
	{
		return root;
	}

	public int depth(AVLNode a)
	{
		if (a==root)
			return 0;
		else
			return 1+depth(a.parent);
	}

	public boolean isLeaf(AVLNode a)
	{
		return (a.left==null && a.right==null);
	}

	public boolean heightImbalance(AVLNode a)
	{
		int h1, h2;
		if (a.right!=null)
			h1 = a.right.height;
		else
			h1 = 0;
		if (a.left!=null)
			h2 = a.left.height;
		else
			h2 = 0;
		int diff = Math.abs(h1-h2);
		if (diff>=2)
			return true;
		else
			return false;
	}

	public void Insert(Position data, int index)
	{
		int h1, h2;
		if(root != null)
		{
			AVLNode curr = returnRoot();
			AVLNode temp = new AVLNode();
			AVLNode par = new AVLNode();
			AVLNode x, y, z;
			while (curr!=null)
			{
				temp = curr;
				if (curr.index>index)
					curr = curr.left;
				else
					curr = curr.right;
			}
			if (temp.index<index)
			{
				temp.right = new AVLNode(temp, data, index);
				temp.right.parent = temp;
				temp.height = 2;
			}
			else
			{
				temp.left = new AVLNode(temp, data, index);
				temp.left.parent = temp;
				temp.height = 2;
			}
			temp=temp.parent;
			while (temp!=null)
			{
				if (temp.left!=null)
					h1 = temp.left.height;
				else
					h1 = 0;
				if (temp.right!=null)
					h2 = temp.right.height;
				else
					h2 = 0;
				temp.height = 1 + Math.max(h1, h2);
				if (heightImbalance(temp))
				{
					if (temp.parent!=null)
						par = temp.parent;
					
					z = temp;
					
					if (z.left!=null)
						h1 = temp.left.height;
					else
						h1 = 0;
					if (z.right!=null)
						h2 = temp.right.height;
					else
						h2 = 0;
					if (h1>=h2)
					{
						y = z.left;
						if (y.left!=null)
							h1 = temp.left.height;
						else
							h1 = 0;
						if (y.right!=null)
							h2 = temp.right.height;
						else
							h2 = 0;
						if (h1>=h2)
						{
							x = y.left;
							AcuteAngle(x,y,z);
						}
						else
						{
							x = y.right;
							MiddleLeft(x,y,z);
						}
					}
					else
					{
						y = z.right;
						if (y.left!=null)
							h1 = temp.left.height;
						else
							h1 = 0;
						if (y.right!=null)
							h2 = temp.right.height;
						else
							h2 = 0;
						if (h1>=h2)
						{
							x = y.left;
							MiddleRight(x,y,z);
						}
						else
						{
							x = y.right;
							ObtuseAngle(x,y,z);
						}
					}
					temp = par;
				}
				else
					temp = temp.parent;
			}
			root.parent = null;
		}
		else
		{
			root = new AVLNode();
			root.data = data;
			root.index = index;
		}
	}

	public void AcuteAngle(AVLNode x, AVLNode y, AVLNode z)
	{
		AVLNode temp1 = y.right;
		if (z==root)
		{
			root=y;
			y.parent=null;
		}
		else
		{
			if (z.parent.left==z)
			{
				z.parent.left=y;
				y.parent = z.parent;
			}
			else
			{
				z.parent.right=y;
				y.parent = z.parent;
			}
		}
		y.right = z;
		z.parent = y;
		z.left = temp1;

		if (temp1!=null)
			temp1.parent = z;
		
		z.height-=2;
	}

	public void ObtuseAngle(AVLNode x, AVLNode y, AVLNode z)
	{
		AVLNode temp1 = y.left;
		if (z==root)
		{
			root=y;
			y.parent=null;
		}
		else
		{
			if (z.parent.left==z)
			{
				z.parent.left=y;
				y.parent = z.parent;
			}
			else
			{
				z.parent.right=y;
				y.parent = z.parent;
			}
		}
		y.left = z;
		z.parent = y;
		z.right = temp1;

		if (temp1!=null)
			temp1.parent = z;
		
		z.height-=2;
	}

	public void MiddleLeft(AVLNode x, AVLNode y, AVLNode z)
	{
		AVLNode temp1 = x.left;
		AVLNode temp2 = x.right;
		if (z==root)
		{
			root=x;
			x.parent=null;
		}
		else
		{
			if (z.parent.left==z)
			{
				z.parent.left=x;
				x.parent = z.parent;
			}
			else
			{
				z.parent.right=x;
				x.parent = z.parent;
			}
		}
		x.left = y;
		x.right = z;
		y.right = temp1;
		z.left = temp2;
		
		y.parent = x;
		z.parent = x;
		x.height+=1;
		z.height-=2;
		y.height-=1;
		if (temp1!=null)
			temp1.parent = y;
		if (temp2!=null)
			temp2.parent = z;
	}

	public void MiddleRight(AVLNode x, AVLNode y, AVLNode z)
	{
		AVLNode temp1 = x.left;
		AVLNode temp2 = x.right;
		if (z==root)
		{
			root=x;
			x.parent=null;
		}
		else
		{
			if (z.parent.left==z)
			{
				z.parent.left=x;
				x.parent = z.parent;
			}
			else
			{
				z.parent.right=x;
				x.parent = z.parent;
			}
		}
		
		x.left = z;
		x.right = y;
		z.right = temp1;
		y.left = temp2;

		y.parent = x;
		z.parent = x;
		x.height+=1;
		z.height-=2;
		y.height-=1;

		if (temp1!=null)
			temp1.parent = z;
		if (temp2!=null)
			temp2.parent = y;

	}

	public AVLNode inorderSuccessor(int n)
	{
		AVLNode curr = root;
		AVLNode temp = new AVLNode();
		AVLNode temp1 = new AVLNode();
		AVLNode temp2 = new AVLNode();
		while (curr!=null)
		{
			temp = curr;
			if (curr.index>n)
				curr = curr.left;
			else if (curr.index<n)
				curr = curr.right;
			else
				break;
		}
		if (curr.right!=null)
		{
			temp1 = curr.right;
			while (temp1!=null)
			{
				temp2=temp1;
				temp1=temp1.left;
			}
			return temp2;
		}
		else
		{
			temp1 = curr;
			while (temp1.parent.right==temp1 && temp1!=root)
				temp1=temp1.parent;
			if (temp1==root)
				return null;
			else
				return temp1.parent;
		}
	}

	public void preorder(AVLNode root)
	{
		AVLNode temp = root;
		if (temp!=null)
		{
			System.out.print(temp.data.WordIndex() + " ");
			preorder(temp.left);
			preorder(temp.right);
		}
	}

	public void inorder(AVLNode root)
	{
		AVLNode temp = root;
		if (temp!=null)
		{
			inorder(temp.left);
			System.out.print(temp.data.WordIndex() + " ");
			inorder(temp.right);
		}
	}
}