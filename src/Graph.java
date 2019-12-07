import java.util.*;

/*****************
 * Graph Class
 ****************/
public class Graph {
    HashMap<String, Set<String>> graph;
    //Graph constructor
    public Graph() {
        graph = new HashMap<>();
    }
    //Add edges
    public void addEgde (String a1, String a2) {
        if(a1.equals(a2)){
            return;
        }
        else{
            //Get node and create link to other node
            if(graph.containsKey(a1)){
                graph.get(a1).add(a2);
            }
            else {
                Set<String> h1 = new HashSet<>();
                h1.add(a2);
                graph.put(a1,h1);

            }
            if(graph.containsKey(a2)){
                graph.get(a2).add(a1);
            }
            else{

                Set<String> h2 = new HashSet<>();
                h2.add(a1);
                graph.put(a2,h2);
            }

        }


    }
    /***********************
     * Breadth First Search
     ***********************/
    public void BFS(String start, String end) {
        if (!graph.containsKey(start) || !graph.containsKey(end)) {
            System.out.println("No Such Actor");
            return;
        } else {


            ArrayList<String> lookup = new ArrayList<>();
            LinkedList<String> queue = new LinkedList<>();
            Hashtable<String, String> path = new Hashtable<>(); //To hold the correct path
            path.put(start, "p"); //put start path to placeholder
            queue.add(start);
            lookup.add(start);
            while (!queue.contains(end)) {
                String actor = queue.removeFirst();
                //System.out.println(actor);B
                for (String contact : graph.get(actor)) {
                    //add path
                    if (!lookup.contains(contact)) {
                        lookup.add(contact);
                        queue.add((contact));
                        path.put(contact,actor);
                        //end if you got path
                        if (contact.equals(end)) {
                            System.out.print("Shortest Path between " + start + " and " + end + ": ");
                            if (!path.containsKey(end)) {
                                path.put(end, actor);
                            }


                        }


                    }

                }
            }
            //Put path into stack and pop to print in correct order
            String person = path.get(end);
            Stack<String> shortPath = new Stack<>();

            do {
                shortPath.push(person);
                person = path.get(person);

            } while (!person.equals("p"));

            while(!shortPath.isEmpty()){
                if(!person.equals(end)){
                    person = shortPath.pop();
                    System.out.print(person + " -> ");
                }
                else {
                    System.out.print(person);
                }

            }
            System.out.print(end);
        }
    }

}




