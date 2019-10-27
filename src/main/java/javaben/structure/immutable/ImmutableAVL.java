package javaben.structure.immutable;

import javaben.structure.AVLNode;
import javaben.structure.Structure;

public class ImmutableAVL extends Structure {

    private AVLNode root;

    public ImmutableAVL() {
        this.root = null;
    }

    public ImmutableAVL(AVLNode root) {
        this.root = root;
    }

    // A utility function to get the height of the tree
    public int height(AVLNode N) {
        if (N == null)
            return 0;

        return N.getHeight();
    }

    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    private AVLNode rightRotate(AVLNode y) {
        y = new AVLNode(y);
        AVLNode x = new AVLNode((AVLNode) y.getNodes()[0]);
        AVLNode T2 = (AVLNode) x.getNodes()[1];

        // Perform rotation
        x.getNodes()[1] = y;
        y.getNodes()[0] = T2;

        // Update heights
        y.setHeight(Math.max(height((AVLNode) y.getNodes()[0]), height((AVLNode) y.getNodes()[1])) + 1);
        x.setHeight(Math.max(height((AVLNode) x.getNodes()[0]), height((AVLNode) x.getNodes()[1])) + 1);

        // Return new root
        return x;
    }

    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    private AVLNode leftRotate(AVLNode x) {
        x = new AVLNode(x);
        AVLNode y = new AVLNode((AVLNode) x.getNodes()[1]);
        AVLNode T2 = (AVLNode) y.getNodes()[0];

        // Perform rotation
        y.getNodes()[0] = x;
        x.getNodes()[1] = T2;

        //  Update heights
        x.setHeight(Math.max(height((AVLNode) x.getNodes()[0]), height((AVLNode) x.getNodes()[1])) + 1);
        y.setHeight(Math.max(height((AVLNode) y.getNodes()[0]), height((AVLNode) y.getNodes()[1])) + 1);

        // Return new root
        return y;
    }

    // Get Balance factor of node N
    public int getBalance(AVLNode N) {
        if (N == null)
            return 0;

        return height((AVLNode) N.getNodes()[0]) - height((AVLNode) N.getNodes()[1]);
    }

    public ImmutableAVL insert(int value) {
        return new ImmutableAVL(insert_r(root, value));
    }

    private AVLNode insert_r(AVLNode node, int value) {
        /* 1.  Perform the normal BST insertion */
        if (node == null)
            return (new AVLNode(value, 2));

        node = new AVLNode(node);

        if (value < node.getValue())
            node.getNodes()[0] = insert_r((AVLNode) node.getNodes()[0], value);
        else if (value > node.getValue())
            node.getNodes()[1] = insert_r((AVLNode) node.getNodes()[1], value);
        else // Duplicate keys not allowed
            return node;

        /* 2. Update height of this ancestor node */
        node.setHeight(1 + Math.max(height((AVLNode) node.getNodes()[0]),
                height((AVLNode) node.getNodes()[1])));

        /* 3. Get the balance factor of this ancestor
              node to check whether this node became
              unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 && value < node.getNodes()[0].getValue())
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && value > node.getNodes()[1].getValue())
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && value > node.getNodes()[0].getValue()) {
            node.getNodes()[0] = leftRotate((AVLNode) node.getNodes()[0]);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && value < node.getNodes()[1].getValue()) {
            node.getNodes()[1] = rightRotate((AVLNode) node.getNodes()[1]);
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    /* Given a non-empty binary search tree, return the
    node with minimum key value found in that tree.
    Note that the entire tree does not need to be
    searched. */
    private AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;

        /* loop down to find the leftmost leaf */
        while (current.getNodes()[0] != null)
            current = (AVLNode) current.getNodes()[0];

        return current;
    }

    public ImmutableAVL delete(int value) {
        return new ImmutableAVL(delete_r(root, value));
    }

    private AVLNode delete_r(AVLNode node, int value) {
        // STEP 1: PERFORM STANDARD BST DELETE
        if (node == null)
            return null;

        node = new AVLNode(node);

        // If the value to be deleted is smaller than
        // the node's value, then it lies in left subtree
        if (value < node.getValue())
            node.getNodes()[0] = delete_r((AVLNode) node.getNodes()[0], value);

            // If the value to be deleted is greater than the
            // node's value, then it lies in right subtree
        else if (value > node.getValue())
            node.getNodes()[1] = delete_r((AVLNode) node.getNodes()[1], value);

            // if value is same as node's value, then this is the node
            // to be deleted
        else {

            // node with only one child or no child
            if ((node.getNodes()[0] == null) || (node.getNodes()[1] == null)) {
                AVLNode temp = null;
                if (temp == node.getNodes()[0])
                    temp = (AVLNode) node.getNodes()[1];
                else
                    temp = (AVLNode) node.getNodes()[0];

                // No child case
                // One child case
                // Copy the contents of
                node = temp;
                // the non-empty child
            } else {

                // node with two children: Get the inorder
                // successor (smallest in the right subtree)
                AVLNode temp = minValueNode((AVLNode) node.getNodes()[1]);

                // Copy the inorder successor's data to this node
                node.setValue(temp.getValue());

                // Delete the inorder successor
                node.getNodes()[1] = delete_r((AVLNode) node.getNodes()[1], temp.getValue());
            }
        }

        // If the tree had only one node then return
        if (node == null)
            return node;

        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        node.setHeight(Math.max(height((AVLNode) node.getNodes()[0]), height((AVLNode) node.getNodes()[1])) + 1);

        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        // this node became unbalanced)
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalance((AVLNode) node.getNodes()[0]) >= 0)
            return rightRotate(node);

        // Left Right Case
        if (balance > 1 && getBalance((AVLNode) node.getNodes()[0]) < 0) {
            node.getNodes()[0] = leftRotate((AVLNode) node.getNodes()[0]);
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && getBalance((AVLNode) node.getNodes()[1]) <= 0)
            return leftRotate(node);

        // Right Left Case
        if (balance < -1 && getBalance((AVLNode) node.getNodes()[1]) > 0) {
            node.getNodes()[1] = rightRotate((AVLNode) node.getNodes()[1]);
            return leftRotate(node);
        }

        return node;
    }

    private AVLNode search_r(AVLNode node, int value) {
        if (node == null) {
            return null;
        }

        if (value < node.getValue())
            return search_r((AVLNode) node.getNodes()[0], value);

            // If the value to be deleted is greater than the
            // node's value, then it lies in right subtree
        else if (value > node.getValue())
            return search_r((AVLNode) node.getNodes()[1], value);

        else return node;

    }

    public AVLNode search(int value) {
        return search_r(root, value);
    }

    // A utility function to print preorder traversal
    // of the tree.
    // The function also prints height of every node
    private String preOrder(AVLNode node, StringBuilder stringBuilder) {
        if (node != null) {
            stringBuilder.append(node.getValue()).append(" ");
            preOrder((AVLNode) node.getNodes()[0], stringBuilder);
            preOrder((AVLNode) node.getNodes()[1], stringBuilder);
        }
        return stringBuilder.toString();
    }

    public String getTree() {
        return preOrder(root, new StringBuilder());
    }

    public AVLNode getRoot() {
        return root;
    }

}
