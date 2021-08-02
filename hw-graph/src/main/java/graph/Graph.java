package graph;

import java.util.*;

// Graph is a representation of a Directed Labeled Graph.
// A Graph contain nodes with unique names.
// A Node can point to other Nodes. This "point" is called an Edge
// and each Edge has a name. Edge names are reusable. A single Node
// can have multiple Edges with the same name.




public class Graph {


    // Abstraction Function:
    // Graph is a representation of a Directed Labeled Graph.
    // A Graph uses a HashMap to contain nodes with unique names.
    // A Node uses a HashMap which contain ArrayLists of other nodes.
    // This connection is called an edge. Names are reusable and
    // each Edge points to a Node. A single Node
    // can have multiple Edges with the same name which is why an
    // ArrayList is used as one edge name may point to different
    // Nodes.

    // Neither HashMap of Nodes nor the contents of the HashMap can be null.
    HashMap<String,Node> nodes;
    static boolean debug;
    /**
     * Sets debug to false and creates a HashMap.
     * */
    public Graph(){
        nodes = new HashMap<>();
        debug = false;
        checkRep();}
    /**
     * @param entry Node to be added to the graph
     * @return true if node was successfully added, else return false
     * @throws IllegalArgumentException if entry is null
     * @spec.modifies this
     * @spec.effects places node in graph unless name for new
     * Node already exists, then return false
     */
    public boolean addNode(String entry) {
        if(isPresent(entry)){
            return false;
        }
        nodes.put(entry,new Node());
        checkRep();
        return true;
        //throw new RuntimeException("Method not implemented!");
    }

    /**
     * @param entry Node to be removed from the graph
     * @return true if node was present, else return false
     * @throws IllegalArgumentException if entry is null
     * @spec.modifies this
     * @spec.effects removes node if present. Does not remove edges
     * pointing to node.
     */
    public boolean popNode(String entry) {
        if(!isPresent(entry)){
            return false;
        }
        nodes.remove(entry);
        checkRep();
        return true;
        //throw new RuntimeException("Method not implemented!");
    }

    /**
     * @param entry Node we are trying to locate
     * @return true if node was present, else return false
     * @throws IllegalArgumentException if entry is null
     */
    public boolean isPresent(String entry) {
        if(entry==null){
            throw new IllegalArgumentException("Node cannot be null!");
        }
        return nodes.containsKey(entry);
    }

    /**
     * @return List of Node's name of All Nodes in graph
     * @spec.effects List all Nodes in Graph
     */
    public List<String> listNodes() {
        Set<String> temp = nodes.keySet();
        return new ArrayList<>(temp);
    }

    /**
     * @param entry is the name of the Node where we start
     * @return List of Node's names. These Nodes hold entry as the name of their parent Node.
     * @throws IllegalArgumentException if entry is null
     * @spec.effects Lists all children Nodes
     */
    public List<String> listChildren(String entry) {
        isPresent(entry);
        Collection<ArrayList<String>> temp = nodes.get(entry).edges.values();
        List<String> goal = new ArrayList<>();
        for(ArrayList<String> nodes : temp){
            for(String node : nodes){
                if(!goal.contains(node)){
                goal.add(node);
                }
            }
        }
        return goal;
    }

    /**
     * @param starterNode name of the Node edge comes from
     * @param edge        name of the edge
     * @param endNode     name of the Node edge points to.
     * @return true if the edge was added, else return false
     * @throws IllegalArgumentException if any param is null
     * @spec.modifies this, Node
     * @spec.effects adds an edge to a node pointing to another node
     * named edge if such an edge does not already exist
     */
    public boolean addEdge(String starterNode, String edge, String endNode) {
        isPresent(edge);
        if(!isPresent(starterNode)){
            return false;
        }
        if(!isPresent(endNode)){
            return false;
        }
        Node node = nodes.get(starterNode);
        if(!node.edges.containsKey(edge)){
            ArrayList<String> add = new ArrayList<>();
            add.add(endNode);
            node.edges.put(edge,add);
            return true;
        }
        if(node.edges.get(edge).contains(endNode)){
            return false;
        }
        node.edges.get(edge).add(endNode);
        checkRep();
        return true;
        //throw new RuntimeException("Method not implemented!");
    }

    /**
     * @param starterNode name of the Node edge comes from
     * @param edge        name of the edge
     * @param endNode     name of the Node edge points to.
     * @return true if the edge was removed, else return false
     * @throws IllegalArgumentException if any param is null
     * @spec.modifies this, Node
     * @spec.effects removes the specified edge from the specified
     * node if such an edge exists.
     */
    public boolean popEdge(String starterNode, String edge, String endNode) {
        if (!isEdge(starterNode, edge, endNode)){
            return false;
        }
        ArrayList<String> edg = nodes.get(starterNode).edges.get(edge);
        edg.remove(endNode);
        if(edg.size()==0){
            nodes.get(starterNode).edges.remove(edge,edg);
        }
        checkRep();
        return true;
        //throw new RuntimeException("Method not implemented!");
    }

    /**
     * @param starterNode name of the Node edge comes from
     * @param edge        name of the edge
     * @param endNode     name of the Node edge points to.
     * @return true if the edge is present, else return false
     * @throws IllegalArgumentException if any param is null
     */
    public boolean isEdge(String starterNode, String edge, String endNode) {
        isPresent(edge);
        if(!isPresent(endNode)){
            return false;
        }
        if(!isPresent(starterNode)){
            return false;
        }
        if(!nodes.get(starterNode).edges.containsKey(edge)){
            return false;
        }
        return nodes.get(starterNode).edges.get(edge).contains(endNode);
    }

    // A Node contains a HashMap that represents edges. You enter a name
    // and get an ArrayList of Nodes edges with this name lead to.
    private class Node {
        // HashMap edges cannot be null, it cannot contain
        // a null ArrayList, and it's ArrayLists cannot
        // contain null
        HashMap<String,ArrayList<String>> edges;
        /**
         * Creating a Node with no edges
         * */
        Node(){
            edges = new HashMap<>();
            checkRep();
        }
        public boolean isEdge(String edge, String endNode) {
            return edges.get(edge).contains(endNode);
        }
        private void checkRep(){
            if(debug){
            assert(edges != null);
            assert(!edges.containsValue(null));
                for(ArrayList<String> nodes : edges.values()){
                    for(String node : nodes){
                        assert(node != null);
                    }
                }
            }
        }
    }
    private void checkRep(){
        if(debug){
        assert(nodes != null);
        assert(!nodes.containsKey(null));
        }
    }
}