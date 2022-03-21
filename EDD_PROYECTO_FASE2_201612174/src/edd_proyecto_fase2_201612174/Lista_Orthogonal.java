package edd_proyecto_fase2_201612174;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class Lista_Orthogonal {

    Node inicio, fin, next;
    int row, col;

    public Lista_Orthogonal() {
        this.inicio = null;
        this.fin = null;
        this.next = null;
        this.row = 0;
        this.col = 0;
    }

    public void add_headers(String name, int x, int y) {
        Node node_Header = new Node(name);  // Create new node that has matrix name (1st block)
        this.inicio = node_Header;
        this.row = x;
        this.col = y;
        Node temp = this.inicio;
        for (int i = 0; i < x; i++) {         // Create all the row headers in the matrix
            Node row = new Node(i + 1);
            row.next = temp;
            temp.prev = row;
            temp = temp.prev;
        }
        temp = this.inicio;                 // Return temp to the 1st block
        for (int i = 0; i < y; i++) {         // Create all the col headers in the matrix
            Node col = new Node(i + 1);
            col.prev = temp;
            temp.next = col;
            temp = temp.next;
        }
    }

    public void add_nodes(int x, int y, int data) { // Change data 
        Node newNode = new Node(x, y, data);
        System.out.println(newNode.row + " TTT " + newNode.col);
        Node temp = this.inicio.next; // col
        Node temp_ = new Node();
        while (temp.id != y) {                    //sets it to the correct coloumn
            temp = temp.next;
            System.out.println(temp.id);
        }
        if (temp.nodeAccess == null) {            //Checks if headers point to something
            temp.nodeAccess = newNode;          //If it doesn't, it'll point to it from the coloumn
            temp = inicio.prev;                 //Sets it to the row
            while (temp.id != x) {                //Searches for the right row
                temp = temp.prev;
            }                                   //Should be on the right row now
            if (temp.nodeAccess == null) {
                temp.nodeAccess = newNode;
            } else {
                if (temp.nodeAccess.col > newNode.col) { //If it's smaller than node access, it becomes the new node access
                    temp.nodeAccess.left = newNode;
                    newNode.right = temp.nodeAccess;
                    temp.nodeAccess = newNode;
                } else {                                    // Otherwise, it compares the col until it reaches the correct one
                    temp_ = temp.nodeAccess;
                    while (temp_ != null) {
                        if (temp_.right == null) {
                            temp_.right = newNode;
                            newNode.left = temp_;
                            break;
                        } else {
                            if (temp_.right.col > newNode.col) {
                                temp_.right.left = newNode;
                                newNode.left = temp_;
                                newNode.right = temp_.right;
                                temp_.right = newNode;
                            }
                        }
                        temp_ = temp.right;
                    }
                }
            }
        } else {                                //If header already points to a node, it does a comparison
            if (temp.nodeAccess.row > newNode.row) { //If it's smaller than node access, it becomes the new node access
                
                System.out.println(newNode.row + " GGG " + newNode.col);
                temp.nodeAccess.up = newNode;
                newNode.down = temp.nodeAccess;
                temp.nodeAccess = newNode;

                temp = inicio.prev;
                while (temp.id != x) {
                    temp = temp.prev;
                }
                if (temp.nodeAccess == null) {
                    temp.nodeAccess = newNode;
                } else {
                    if (temp.nodeAccess.col > newNode.col) {
                        temp.nodeAccess.left = newNode;
                        newNode.right = temp.nodeAccess;
                        temp.nodeAccess = newNode;
                    } else {
                        temp_ = temp.nodeAccess;
                        while (temp_ != null) {
                            if (temp_.right == null) {
                                temp_.right = newNode;
                                newNode.left = temp_;
                                break;
                            } else {
                                if (temp_.right.col > newNode.col) {
                                    temp_.right.left = newNode;
                                    newNode.left = temp_;
                                    newNode.right = temp_.right;
                                    temp_.right = newNode;
                                }
                            }
                            temp_ = temp.right;
                        }
                    }
                }

            } else {                                    // Otherwise, it compares the col until it reaches the correct one
                temp_ = temp.nodeAccess;
                
                while (temp_ != null) {
                    if (temp_.down == null) {
                        temp_.down = newNode;
                        newNode.up = temp_;
                        break;
                    } else {
                        if (temp_.down.row > newNode.row) {
                            System.out.println(data + " ddd");
                            temp_.down.up = newNode;
                            newNode.up = temp_;
                            newNode.down = temp_.down;
                            
                            temp_.down = newNode;
                        }
                    }
                    temp_ = temp.down;
                }
                while (temp.id != x) {                //Searches for the right row
                    temp = temp.prev;
                }                                   //Should be on the right row now
                if (temp.nodeAccess == null) {
                    temp.nodeAccess = newNode;
                } else {
                    System.out.println(temp.nodeAccess.row + ":" + temp.nodeAccess.col + " FFF " + newNode.row + ":" + newNode.col);
                    if (temp.nodeAccess.col > newNode.col) { //If it's smaller than node access, it becomes the new node access
                        temp.nodeAccess.left = newNode;
                        newNode.right = temp.nodeAccess;
                        temp.nodeAccess = newNode;
                        System.out.println("row_nodeA_change");
                    } else {                                    // Otherwise, it compares the col until it reaches the correct one
                        temp_ = temp.nodeAccess;
                        while (temp_ != null) {
                            if (temp_.right == null) {
                                temp_.right = newNode;
                                newNode.left = temp_;
                                break;
                            } else {
                                if (temp_.right.col > newNode.col) {
                                    temp_.right.left = newNode;
                                    newNode.left = temp_;
                                    newNode.right = temp_.right;
                                    temp_.right = newNode;
                                }
                            }
                            temp_ = temp.right;
                        }
                    }//Verify row nodes
                }
            }

//            temp_ = temp.nodeAccess;
//            while (temp_.down != null) {
//                temp_ = temp_.down;
//            }
//            temp_.down = newNode;
//            newNode.up = temp_;
//            if (temp.prev == this.inicio) {
//                temp = this.inicio.prev;
//                while (temp.id != x) {
//                    temp = temp.prev;
//                }
//                temp.nodeAccess = newNode;
//            } else {
//                temp = temp.prev;
//                temp_ = temp.nodeAccess;
//                while (temp_.down != null) {
//                    temp_ = temp_.down;
//                }
//                temp_.right = newNode;
//                newNode.left = temp_;
//            }
//        }
        }
    }

    public void display() {
        Node temp = inicio;
        Node temp_ = temp.next;
        //Print headers
        System.out.print(temp.name + " ");
        while (temp_ != null) {
            System.out.print(temp_.id + " ");
            temp_ = temp_.next;
        }
        temp = inicio.prev;                                   //Setting to the 1st row
        System.out.println("");//Setting to the row nodes
        while (temp != null) {
            temp_ = temp.nodeAccess;
            System.out.print(temp.id + " ");
            while (temp_ != null) {
                System.out.print(temp_.data + " ");
                temp_ = temp_.right;
            }
            System.out.println("");
            temp = temp.prev;
        }
    }

    public void display2() {
        Node temp = inicio;
        Node temp_ = temp.prev;
        //Print headers
        System.out.print(temp.name + " ");
        while (temp_ != null) {
            System.out.print(temp_.id + " ");
            temp_ = temp_.prev;
        }
        temp = inicio.next;                                   //Setting to the 1st row
        System.out.println("");//Setting to the row nodes
        while (temp != null) {
            temp_ = temp.nodeAccess;
            System.out.print(temp.id + " ");
            while (temp_ != null) {
                System.out.print(temp_.data + " ");
                temp_ = temp_.down;
            }
            System.out.println("");
            temp = temp.next;
        }

    }

    public void graph(String fileName) {
        try {
            FileWriter myWriter = new FileWriter("src\\edd_proyecto_fase2_201612174\\" + fileName + ".txt");
            myWriter.write("digraph G\n{\nrankdir=\"TB\"\nnode[shape = rectangle]\nlabel=\"Carnet: 201612174\"\n");
            myWriter.write(graficadora());
            myWriter.write("}");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
            GraphViz.imprimir("MD");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String graficadora() {
        String cadena;
        String rowInfo = "MD;";
        cadena = "MD -> ";
        for (int i = 0; i < row; i++) {
            if (i == row - 1) {
                cadena += "a" + (i + 1) + "\n";
            } else {
                cadena += "a" + (i + 1) + " -> ";
            }

        }

        cadena += "MD -> ";
        for (int i = 0; i < col; i++) {
            if (i == col - 1) {
                cadena += "b" + (i + 1) + "\n";
            } else {
                cadena += "b" + (i + 1) + " -> ";
            }
            rowInfo += "b" + (i + 1) + ";";
        }
        cadena += "{rank=same;" + rowInfo + "}\n";
        Node temp = inicio.prev;
        while (temp != null) {
            rowInfo = "";
            rowInfo += "a" + temp.id + ";";
            Node temp_ = temp.nodeAccess;
            if (temp.nodeAccess == null) {
                temp = temp.prev;
                continue;
            } else {
                cadena += "a" + temp_.row + " -> x" + temp_.data + " -> a" + temp_.row + "[constraint=false]\n";
                rowInfo += "x" + temp_.data + ";";
                if (temp_.up == null) {
                    System.out.println(temp_.data);
                    cadena += "b" + temp_.col + " -> x" + temp_.data + " -> b" + temp_.col + "\n";
                } else {
                    cadena += "x" + temp_.up.data + " -> x" + temp_.data + " -> x" + temp_.up.data + "\n";
                }
                temp_ = temp_.right;
            }
            while (temp_ != null) {
                cadena += "x" + temp_.left.data + " -> x" + temp_.data + " -> x" + temp_.left.data + "[constraint=false]\n";
                rowInfo += "x" + temp_.data + ";";
                if (temp_.up == null) {
                    cadena += "b" + temp_.col + " -> x" + temp_.data + " -> b" + temp_.col + "\n";
                } else {
                    cadena += "x" + temp_.up.data + " -> x" + temp_.data + " -> x" + temp_.up.data + "\n";
                }
                temp_ = temp_.right;
            }
            temp = temp.prev;
            cadena += "\n{rank=same;" + rowInfo + "}\n";
        }

        return cadena;
    }
}
