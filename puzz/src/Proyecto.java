
import java.util.*;

public class Proyecto {

       
        public static void main(String[] args) {
                
                
                int[] p1d = {1, 4, 2, 0, 3, 5, 6, 7, 8};
                int hueristic = 2;
                Juego start = new Juego(p1d, hueristic, 0);
                int[] win = { 0, 1, 2,
                                          3, 4, 5,
                                          6, 7, 8};
                Juego goal = new Juego(win, hueristic, 0);
                
                astar(start, goal);

                

        }
        
        public static void astar(Juego start, Juego goal)
        {
                if(start.inversions() % 2 == 1)
                {
                        System.out.println("Unsolvable");
                        return;
                }
                LinkedList<Juego> closedset = new LinkedList<Juego>();

                PriorityQueue<Juego> openset = new PriorityQueue<Juego>();

                openset.add(start);
                while(openset.size() > 0){
                        Juego x = openset.peek();
                        if(x.mapEquals(goal))
                        {

                                 Stack<Juego> toDisplay = reconstruct(x);
                                 System.out.println("la solucion es :) :  ");
                                 System.out.println(start.toString());
                                 print(toDisplay);
                                 return;
                                 
                        }
                        closedset.add(openset.poll());
                        LinkedList <Juego> neighbor = x.getChildren();

                        while(neighbor.size() > 0)
                        {
                                Juego y = neighbor.removeFirst();

                                if(closedset.contains(y)){

                                        continue;
                                }
                                if(!closedset.contains(y)){

                                        openset.add(y);

                                }
                        }
                }
        }
        public static void print(Stack<Juego> x)
        {
                while(!x.isEmpty())
                {
                        Juego temp = x.pop();
                        System.out.println(temp.toString());
                }
        }

        public static Stack<Juego> reconstruct(Juego winner)
        {
                Stack<Juego> correctOutput = new Stack<Juego>();
                
                while(winner.getParent() != null)
                {
                correctOutput.add(winner);
                winner = winner.getParent();
                }
 
                return correctOutput;
        }
        
        }
        
        

