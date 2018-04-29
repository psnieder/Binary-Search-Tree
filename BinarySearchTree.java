package bst;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Binary Search Tree class.
 * @author Paul Snieder
 * @param <E> generic
 */
public class BinarySearchTree<E> implements SearchTree<E> {
  
  private BinaryNode root;
  private Comparator<E> comp;
  
  /**
   * Constructor
   * Creates a new BinarySearchTree.
   * @param c the comparator to use for determining order.
   */
  public BinarySearchTree(Comparator<E> c) {
    this.comp = c;
    this.root = null;
  }
  
  
  /**
   * Nested class to generate BinaryNodes.
   * @author Paul Snieder
   */
  private class BinaryNode {
    
    private E data;
    private BinaryNode left;
    private BinaryNode right;
    
    /**
     * Constructor.
     * Creates a new BinaryNode
     */
    public BinaryNode() {
      this.data = null;
      this.left = null;
      this.right = null;
    }
    
  }

  @Override
  public boolean add(E item) {
    if (root == null) {
      root = new BinaryNode();
      root.data = item;
      return true;
    } else {
      return add(item, root);
    }
  }
  
  /**
   * Private recursive helper method for add.
   */
  private boolean add(E e, BinaryNode node) {
    int result = comp.compare(e, node.data);
    if (result < 0) {
      if (node.left == null) {
        node.left = new BinaryNode();
        node.left.data = e;
        return true;
      } else {
        return add(e, node.left);
      }
    } else if (result > 0) {
      if (node.right == null) {
        node.right = new BinaryNode();
        node.right.data = e;
        return true;
      } else {
        return add(e, node.right);
      }
    } else {
      return false;
    }
  }

  @Override
  public boolean contains(E item) {
    return (find(item) == null) ? false : true;
  }

  @Override
  public E find(E target) {
    return find(target, root);
  }
  
  /**
   * Private recursive helper method for find.
   */
  private E find(E e, BinaryNode node) {
    if (node == null) {
      return null;
    }
    int result = comp.compare(e, node.data);
    if (result == 0) {
      return node.data;
    } else if (result < 0) {
      return find(e, node.left);
    } else {
      return find(e, node.right);
    }
  }

  @Override
  public E delete(E target) {
    return delete(target, root, null);
  }
  
  /**
   * Private recursive helper method for delete.
   */
  private E delete(E e, BinaryNode node, BinaryNode parent) {
    if (node == null) {
      return null;
    }
    int result = comp.compare(e, node.data);
    if (result < 0) {
      return delete(e, node.left, node);
    } else if (result > 0) {
      return delete(e, node.right, node);
    } else {
      E temp = node.data;
      if (node.left == null) {
        if (parent.left == node) {
          parent.left = node.right;
        } else {
          parent.right = node.right;
        }
        node.right = null;
      } else if (node.right == null) {
        if (parent.left == node) {
          parent.left = node.left;
        } else {
          parent.right = node.left;
        }
        node.left = null;
      } else {
        if (node.left.right == null) {
          node.data = node.left.data;
          node.left = node.left.left;
        } else {
          node.data = findLargestChild(node.left);
        }
      }
      return temp;
    }
  }
  
  /**
   * Private method to find the largest child of a root node.
   */
  private E findLargestChild(BinaryNode parent) {
    if (parent.right.right == null) {
      E returnValue = parent.right.data;
      parent.right = parent.right.left;
      return returnValue;
    } else {
      return findLargestChild(parent.right);
    }
  }

  @Override
  public boolean remove(E target) {
    return (delete(target) == null) ? false : true;
  }
  
  /**
   * Creates a list with the inorder representation of a tree.
   * @return a list with the inorder representation of the tree
   */
  public List<E> inorder() {
    return inorder(new ArrayList<E>(), root);
  }
  
  /**
   * Private recursive helper method for inorder.
   */
  private List<E> inorder(List<E> list, BinaryNode node) {
    if (node == null) {
      return list;
    } else {
      inorder(list, node.left);
      list.add(node.data);
      inorder(list, node.right);
      return list;
    }
  }

}
