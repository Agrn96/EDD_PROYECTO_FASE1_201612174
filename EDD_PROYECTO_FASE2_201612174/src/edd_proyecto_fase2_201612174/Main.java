package edd_proyecto_fase2_201612174;

/**
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Lista_Orthogonal MD = new Lista_Orthogonal();
        
        MD.add_headers("t",5,5);
        MD.display();
        MD.add_nodes(4, 3, 2);
        MD.add_nodes(1, 3, 1);
        MD.add_nodes(5, 4, 3);
        MD.add_nodes(2, 2, 4);
        MD.add_nodes(3, 2, 5);
        //MD.add_nodes(1, 3, 7); // Duplicates are taking into account the col pointers but not adding the row ones
        MD.add_nodes(5, 1, 6);
        //MD.add_nodes(3, 1, 2);
        
        System.out.println("New");
        MD.display();
        System.out.println("New");
        MD.display2();
        MD.graph("MD");
    }
    
    
}
