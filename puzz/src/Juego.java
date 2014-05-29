import java.util.*;

public class Juego implements Comparable <Object> {
        
        
        int[] puzzle = new int[9];
        int h_n= 0;
        int hueristic_type = 0;
        int g_n = 0;
        int f_n = 0;
        Juego parent = null;

        
        public Juego(int[] p, int h_type, int cost)
        {
                this.puzzle = p;
                this.hueristic_type = h_type;
                this.h_n = (h_type == 1) ?  h1(p) : h2(p);
                this.g_n = cost;
                this.f_n = h_n + g_n;
        }
        public int getF_n()
        {
                return f_n;
        }
        public void setParent(Juego input)
        {
                this.parent = input;
        }
        public Juego getParent()
        {
                return this.parent;
        }

        public int inversions()
        {
               
                int inversion = 0;
                for(int i = 0; i < this.puzzle.length; i++ )
                {
                        for(int j = 0; j < i; j++)
                        {
                                if(this.puzzle[i] != 0 && this.puzzle[j] != 0)
                                {
                                if(this.puzzle[i] < this.puzzle[j])
                                        inversion++;
                                }
                        }
                }
                return inversion;
                
        }
        public int h1(int[] list)
     
        {
                int gn = 0;
                for(int i = 0; i < list.length; i++)
                {
                        if(list[i] != i && list[i] != 0)
                                gn++;
                } 
                return gn;
        }
        public LinkedList<Juego> getChildren()
        {
                LinkedList<Juego> children = new LinkedList<Juego>();
                int loc = 0;
        int temparray[] = new int[this.puzzle.length];
        Juego rightP;
        Juego upP, downP, leftP;
                while(this.puzzle[loc] != 0)
                {
                        loc++;
                }
                if(loc % 3 == 0){
                        temparray = this.puzzle.clone();
                        temparray[loc] = temparray[loc + 1];
                        temparray[loc + 1] = 0;
                        rightP = new Juego(temparray, this.hueristic_type, this.g_n + 1);
                        rightP.setParent(this);
                        children.add(rightP);

                }else if(loc % 3 == 1){
             
                        temparray = this.puzzle.clone();
                        temparray[loc] = temparray[loc + 1];
                        temparray[loc + 1] = 0;
                        
                        rightP = new Juego(temparray, this.hueristic_type, this.g_n + 1);
                        rightP.setParent(this);
                        children.add(rightP);
                  
                        temparray = this.puzzle.clone();
                        temparray[loc] = temparray[loc - 1];
                        temparray[loc - 1] = 0;
                        
                        leftP = new Juego(temparray, this.hueristic_type, this.g_n + 1);
                        leftP.setParent(this);
                        children.add(leftP);
                }else if(loc % 3 == 2){
              
                        temparray = this.puzzle.clone();
                        temparray[loc] = temparray[loc - 1];
                        temparray[loc - 1] = 0;
                        
                        leftP = new Juego(temparray, this.hueristic_type, this.g_n + 1);
                        leftP.setParent(this);
                        children.add(leftP);
                }               
                
                if(loc / 3 == 0){
              
                        temparray = this.puzzle.clone();
                        temparray[loc] = temparray[loc + 3];
                        temparray[loc + 3] = 0;
                        
                        downP = new Juego(temparray, this.hueristic_type, this.g_n + 1);

                        downP.setParent(this);

                        children.add(downP);
                
                        
                }else if(loc / 3 == 1 ){
             
                        temparray = this.puzzle.clone();
                        temparray[loc] = temparray[loc - 3];
                        temparray[loc - 3] = 0;
                        
                        upP = new Juego(temparray, this.hueristic_type, this.g_n + 1);
                        upP.setParent(this);

                        children.add(upP);
                 
                        temparray = this.puzzle.clone();
                        temparray[loc] = temparray[loc + 3];
                        temparray[loc + 3] = 0;
                        
                        downP = new Juego(temparray, this.hueristic_type, this.g_n + 1);
                        downP.setParent(this);

                        children.add(downP);
                }else if (loc / 3 == 2 ){
              
                        temparray = this.puzzle.clone();
                        temparray[loc] = temparray[loc - 3];
                        temparray[loc - 3] = 0;
                        
                        upP = new Juego(temparray, this.hueristic_type, this.g_n + 1);
                        upP.setParent(this);

                        children.add(upP);
                }

                return children;
        }
        public int h2(int[] list)
        {
                int gn = 0;
                int row = 0;
                int col = 0;
                for(int i = 0; i < list.length; i++)
                {
                        if(list[i] != 0)
                        {
                                row = list[i] / 3;
                                col = list[i] % 3;
                                row = Math.abs(row - (i / 3));
                                col = Math.abs(col - (i % 3));
                                gn += row;
                                gn += col;
                        }
                        
                }
                return gn;
        }
        public String toString()
        {
                String x = "";
                for(int i = 0; i < this.puzzle.length; i++){
                        x += puzzle[i] + " ";
                        if((i + 1) % 3 == 0)
                                x += "\n";
                }
                return x;
        }
        public int compareTo(Object input) {
                
                
                if (this.f_n < ((Juego) input).getF_n())
                        return -1;
                else if (this.f_n > ((Juego) input).getF_n())
                        return 1;
                return 0;
        }
        
        public boolean equals(Juego test){
                if(this.f_n != test.getF_n())
                        return false;
                for(int i = 0 ; i < this.puzzle.length; i++)
                {
                        if(this.puzzle[i] != test.puzzle[i])
                                return false;
                }
                return true;
        }
        public boolean mapEquals(Juego test){
                for(int i = 0 ; i < this.puzzle.length; i++)
                {
                        if(this.puzzle[i] != test.puzzle[i])
                                return false;
                }
                return true;
        }

}