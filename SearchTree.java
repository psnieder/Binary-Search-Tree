package bst;

/**
 * Search Tree Interface.
 * @author Paul Snieder
 * @param <E> generic
 */
public interface SearchTree<E> {
  /**
   * Inserts item into where it belongs in the tree.
   * @param item the item to add
   * @return true if item is inserted, false if item is already in tree.
   */
  boolean add(E item);
  
  /**
   * Looks for an item in the tree.
   * @param item the item to check
   * @return true if item is in the tree, false otherwise.
   */
  boolean contains(E item);
  
  /**
   * Looks for an item in the tree.
   * @param target the item to look for
   * @return a reference to the target if found, null if target isn't in the tree.
   */
  E find(E target);
  
  /**
   * Removes target from the tree.
   * @param target the item to delete
   * @return a reference to the target if found, null if target isn't in the tree.
   */
  E delete(E target);
  
  /**
   * Removes target from the tree.
   * @param target the item to remove
   * @return true if target was in the tree, false otherwise.
   */
  boolean remove(E target);
}