/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd_proyecto_fase2_201612174;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class Matriz_Dispersa {

    int col_Max, row_Max, count=0;
    Node_MD inicio;

    public Matriz_Dispersa() {
        this.col_Max = -1;
        this.row_Max = -1;
        this.inicio = null;
    }

    void add_Head(Node_MD newNode) {
        this.inicio = newNode;
    }

    void add_Header_Row(int x) {
        Node_MD temp = this.inicio;
        if (this.inicio.row == null) {
            while (x > row_Max) {
                ++row_Max;
                Node_MD newNode = new Node_MD(row_Max);
                temp.row = newNode;
                temp = temp.row;
            }
            return;
        }

        while (temp.row != null) {
            temp = temp.row;
        }
        while (x > row_Max) {
            ++row_Max;
            Node_MD newNode = new Node_MD(row_Max);
            temp.row = newNode;
            temp = temp.row;
        }
    }

    void add_Header_Col(int y) {
        Node_MD temp = this.inicio;
        if (this.inicio.col == null) {
            while (y > col_Max) {
                ++col_Max;
                Node_MD newNode = new Node_MD(col_Max);
                temp.col = newNode;
                temp = temp.col;
            }
            return;
        }

        while (temp.col != null) {
            temp = temp.col;
        }
        while (y > col_Max) {
            ++col_Max;
            Node_MD newNode = new Node_MD(col_Max);
            temp.col = newNode;
            temp = temp.col;
        }
    }

    void add_Node(int x, int y, String data) {
        Node_MD newNode = new Node_MD(x, y, data);
        
        if (this.inicio == null) {
            add_Head(newNode);
            System.out.println("testing if it is working: " + data);
            return;
        }

        if (newNode.row_No > row_Max) {
            add_Header_Row(newNode.row_No);
        }

        if (newNode.col_No > col_Max) {
            add_Header_Col(newNode.col_No);
        }

        add_Col_Pointers(newNode);
        add_Row_Pointers(newNode);
    }

    void add_Col_Pointers(Node_MD newNode) {
        Node_MD temp = this.inicio.col;                 //Col
        //System.out.println("test");
        Node_MD temp_;

        while (temp.id != newNode.col_No) {                        //Correct Col
            temp = temp.col;
            //System.out.println(temp.col.id);
        }

        if (temp.node_Access == null) {
            temp.node_Access = newNode;
        } else if (temp.node_Access.row_No > newNode.row_No) {
            newNode.down = temp.node_Access;
            temp.node_Access.up = newNode;
            temp.node_Access = newNode;
        } else {
            temp_ = temp.node_Access;
            while (temp_ != null) {
                if (temp_.row_No == newNode.row_No) {
                    //System.out.println("ERROR: Nodo Duplicado");
                    return;
                } else if (temp_.down == null) {
                    temp_.down = newNode;
                    newNode.up = temp_;
                    break;
                } else if (temp_.down.row_No > newNode.row_No) {
                    temp_.down.up = newNode;
                    newNode.up = temp_;
                    newNode.down = temp_.down;
                    temp_.down = newNode;
                }
                temp_ = temp_.down;
            }
        }
    }

    void add_Row_Pointers(Node_MD newNode) {
        Node_MD temp = this.inicio.row;                 //Col
        Node_MD temp_;

        while (temp.id != newNode.row_No) {                        //Correct Col
            temp = temp.row;
            //System.out.println(temp.row.id);
        }

        if (temp.node_Access == null) {
            temp.node_Access = newNode;
        } else if (temp.node_Access.col_No > newNode.col_No) {
            newNode.right = temp.node_Access;
            temp.node_Access.left = newNode;
            temp.node_Access = newNode;
        } else {
            temp_ = temp.node_Access;
            while (temp_ != null) {
                if (temp_.col_No == newNode.col_No) {
                    //System.out.println("ERROR: Nodo Duplicado");
                    return;
                } else if (temp_.right == null) {
                    temp_.right = newNode;
                    newNode.left = temp_;
                    break;
                } else if (temp_.right.col_No > newNode.col_No) {
                    temp_.right.left = newNode;
                    newNode.left = temp_;
                    newNode.right = temp_.right;
                    temp_.right = newNode;
                }
                temp_ = temp_.right;
            }
        }
    }

    void display() {
        Node_MD temp = inicio;
        Node_MD temp_ = temp.col;
        //Print headers
        System.out.print(temp.data + " ");
        while (temp_ != null) {
            System.out.print(temp_.id + " ");
            temp_ = temp_.col;
        }
        temp = inicio.row;                                   //Setting to the 1st row
        System.out.println("");//Setting to the row nodes
        while (temp != null) {
            temp_ = temp.node_Access;
            System.out.print(temp.id + " ");
            while (temp_ != null) {
                System.out.print(temp_.data + " ");
                temp_ = temp_.right;
            }
            System.out.println("");
            temp = temp.row;
        }
    }

    void display2() {
        Node_MD temp = inicio;
        Node_MD temp_ = temp.row;
        //Print headers
        System.out.print(temp.data + " ");
        while (temp_ != null) {
            System.out.print(temp_.id + " ");
            temp_ = temp_.row;
        }
        temp = inicio.col;                                   //Setting to the 1st row
        System.out.println("");//Setting to the row nodes
        while (temp != null) {
            temp_ = temp.node_Access;
            System.out.print(temp.id + " ");
            while (temp_ != null) {
                System.out.print(temp_.data + " ");
                temp_ = temp_.down;
            }
            System.out.println("");
            temp = temp.col;
        }
    }

    void add_Matrix(Matriz_Dispersa second) {
        Node_MD temp = second.inicio.row;
        while (temp != null) {
            if (temp.node_Access == null) {
                temp = temp.row;
                continue;
            } else {
                Node_MD temp_ = temp.node_Access;
                while (temp_ != null) {
                    this.add_Node(temp_.row_No, temp_.col_No, temp_.data);
                    temp_ = temp_.right;
                }
            }
            temp = temp.row;
        }
    }

    public void graph(String usuarioID,  Long fileName) {
        try {
            String path = "src\\Salidas\\" + usuarioID + "\\Capas\\" + fileName + ".txt";
            FileWriter myWriter = new FileWriter(path);
            myWriter.write("digraph G\n{\nrankdir=\"TB\"\nnode[shape = rectangle]\nlabel=\"Carnet: 201612174\"\n");
            myWriter.write(graficadora());
            myWriter.write("}");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
            GraphViz.imprimir(path, usuarioID, fileName, 0);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    
    public void graph2(String usuarioID,  Long fileName) {
        try {
            String path = "src\\Salidas\\" + usuarioID + "\\Images\\" + fileName + ".txt";
            FileWriter myWriter = new FileWriter(path);
            myWriter.write("digraph G\n{\nrankdir=\"TB\"\nnode[shape = rectangle]\nlabel=\"Carnet: 201612174\"\n");
            myWriter.write(graficadora());
            myWriter.write("}");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
            GraphViz.imprimir(path, usuarioID, fileName);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String graficadora() {
        String cadena;
        String rowInfo = "MD;";
        cadena = "MD -> ";
        for (int i = 0; i <= row_Max; i++) {
            if (i == row_Max) {
                cadena += "a" + (i) + "\n";
            } else {
                cadena += "a" + (i) + " -> ";
            }
        }
        cadena += "MD -> ";
        for (int i = 0; i <= col_Max; i++) {
            if (i == col_Max) {
                cadena += "b" + (i) + "\n";
            } else {
                cadena += "b" + (i) + " -> ";
            }
            rowInfo += "b" + (i) + ";";
        }
        cadena += "{rank=same;" + rowInfo + "}\n";
        Node_MD temp = inicio.row;
        while (temp != null) {
            rowInfo = "";
            rowInfo += "a" + temp.id + ";";
            Node_MD temp_ = temp.node_Access;
            if (temp.node_Access == null) {
                temp = temp.row;
                continue;
            } else {
                cadena += "x" + temp_.row_No + "y" + temp_.col_No + "[label = \"\", style = filled, fillcolor = \"" + temp_.data + "\"]" + "\n";
                cadena += "a" + temp_.row_No + " -> x" + temp_.row_No + "y" + temp_.col_No + " -> a" + temp_.row_No + "[constraint=false]\n";
                rowInfo += "x" + temp_.row_No + "y" + temp_.col_No + ";"; //data -> color
                if (temp_.up == null) {
                    System.out.println(temp_.data);
                    cadena += "b" + temp_.col_No + " -> x" + temp_.row_No + "y" + temp_.col_No + " -> b" + temp_.col_No + "\n";
                } else {
                    cadena += "x" + temp_.up.row_No + "y" + temp_.up.col_No + " -> x" + temp_.row_No + "y" + temp_.col_No + " -> x" + temp_.up.row_No + "y" + temp_.up.col_No + "\n";
                }
                temp_ = temp_.right;
            }
            while (temp_ != null) {
                cadena += "x" + temp_.row_No + "y" + temp_.col_No + "[label = \"\", style = filled, fillcolor = \"" + temp_.data + "\"]" + "\n";
                cadena += "x" + temp_.left.row_No + "y" + temp_.left.col_No + " -> x" + temp_.row_No + "y" + temp_.col_No + " -> x" + temp_.left.row_No + "y" + temp_.left.col_No + "[constraint=false]\n";
                rowInfo += "x" + temp_.row_No + "y" + temp_.col_No + ";";
                if (temp_.up == null) {
                    cadena += "b" + temp_.col_No + " -> x" + temp_.row_No + "y" + temp_.col_No + " -> b" + temp_.col_No + "\n";
                } else {
                    cadena += "x" + temp_.up.row_No + "y" + temp_.up.col_No + " -> x" + temp_.row_No + "y" + temp_.col_No + " -> x" + temp_.up.row_No + "y" + temp_.up.col_No + "\n";
                }
                temp_ = temp_.right;
            }
            temp = temp.row;
            cadena += "\n{rank=same;" + rowInfo + "}\n";
        }
        return cadena;
    }
}
