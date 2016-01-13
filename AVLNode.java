public class AVLNode
{
    public AVLNode parent, left, right;
    public int index, height;
    public Position data;
    
    public AVLNode()
    {
        parent = null;
        left = null;
        right = null;
        data = null;
        index = 0;
        height = 1;
    }

    public AVLNode(AVLNode p, Position pos, int i)
    {
        parent = p;
        left = null;
        right = null;
        data = pos;
        index = i;
        height = 1;
    }
}