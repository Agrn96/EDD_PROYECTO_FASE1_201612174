package edd_proyecto_fase2_201612174;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author 201612174 --Alberto Gabriel Reyes Ning
 */
public class ArbolB {
    int orden_arbol = 5;
    int h = 0;
    int nodo = 0;
    int r = -1;
    String cadena = "";
    RamaB raiz;

    public ArbolB() {
        this.insertar("1234567890101", "Admin", "EDD1S2022");
    }

    public void insertar(String id, String nombreC, String pass) {
        NodoB nodo = new NodoB(id, nombreC, pass);
        String path = "src\\Salidas\\" + nodo.id;
        File f1 = new File(path);
        boolean bool = f1.mkdir();
        if (bool) {
            System.out.println("Folder is created successfully");
            String path1 = path + "\\Images";
            path += "\\Capas";

            System.out.println(path);
            f1 = new File(path);
            f1.mkdir();

            f1 = new File(path1);
            f1.mkdir();
        } else {
            System.out.println("Error: Possible Duplicate");
        }

        if (raiz == null) {
            raiz = new RamaB();
            raiz.insertar(nodo);
        } else {
            NodoB obj = insertar_en_rama(nodo, raiz);
            if (obj != null) {
                //si devuelve algo el metodo de insertar en rama quiere decir que creo una nueva rama, y se debe insertar en el arbol
                raiz = new RamaB();
                raiz.insertar(obj);
                raiz.hoja = false;
            }
        }
    }

    private NodoB insertar_en_rama(NodoB nodo, RamaB rama) {
        if (rama.hoja) {
            rama.insertar(nodo);
            if (rama.contador == orden_arbol) {
                //si ya se insertaron todos los elementos posibles se debe dividir la rama
                return dividir(rama);
            } else {
                return null;
            }
        } else {
            NodoB temp = rama.primero;
            do {
                if (nodo.id == temp.id) {
                    return null;
                } else if (nodo.id.compareTo(temp.id) < 0) {
                    NodoB obj = insertar_en_rama(nodo, temp.izquierda);
                    if (obj instanceof NodoB) {
                        rama.insertar((NodoB) obj);
                        if (rama.contador == orden_arbol) {
                            return dividir(rama);
                        }
                    }
                    return null;
                } else if (temp.siguiente == null) {
                    NodoB obj = insertar_en_rama(nodo, temp.derecha);
                    if (obj instanceof NodoB) {
                        rama.insertar((NodoB) obj);
                        if (rama.contador == orden_arbol) {
                            return dividir(rama);
                        }
                    }
                    return null;
                }
                temp = (NodoB) temp.siguiente;
            } while (temp != null);
        }
        return null;
    }

    private NodoB dividir(RamaB rama) {
        String val = "-999";
        String nombre = "";
        String pass = "";
        NodoB temp, Nuevito;
        NodoB aux = rama.primero;
        RamaB rderecha = new RamaB();
        RamaB rizquierda = new RamaB();

        int cont = 0;
        while (aux != null) {
            cont++;
            //implementacion para dividir unicamente ramas de 4 nodos
            if (cont < 3) {
                temp = new NodoB(aux.id, aux.nombre_Cliente, aux.password);
                temp.izquierda = aux.izquierda;
                if (cont == 2) {
                    temp.derecha = aux.siguiente.izquierda;
                } else {
                    temp.derecha = aux.derecha;
                }
                //si la rama posee ramas deja de ser hoja
                if (temp.derecha != null && temp.izquierda != null) {
                    rizquierda.hoja = false;
                }

                rizquierda.insertar(temp);

            } else if (cont == 3) {
                val = aux.id;
                nombre = aux.nombre_Cliente;
                pass = aux.password;
            } else {
                temp = new NodoB(aux.id, aux.nombre_Cliente, aux.password);
                temp.izquierda = aux.izquierda;
                temp.derecha = aux.derecha;
                //si la rama posee ramas deja de ser hoja
                if (temp.derecha != null && temp.izquierda != null) {
                    rderecha.hoja = false;
                }
                rderecha.insertar(temp);
            }
            aux = aux.siguiente;
        }
        Nuevito = new NodoB(val, nombre, pass);
        Nuevito.derecha = rderecha;
        Nuevito.izquierda = rizquierda;
        return Nuevito;
    }

    public void graph(String fileName) {
        try {
            FileWriter myWriter = new FileWriter("src\\Salidas\\" + fileName + ".txt");
            myWriter.write("digraph G\n{\nrankdir=\"TB\"\nnode[shape = record, width = 0.5]\nlabel=\"Carnet: 201612174\"\n");
            myWriter.write(graficadora());
            myWriter.write("}");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
            GraphViz.imprimir(fileName);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String graficadora() {
        String cadena = "";
        nodo = 0;
        h = 0;
        cadena = Show();
        System.out.println("TEST\n" + cadena);
        return cadena;
    }

    public String Show() {
        cadena = Show(raiz, 0);
        return cadena;
    }

    // Display
    private String Show(RamaB x, int pos) {
        assert (x == null);
        NodoB temp = x.primero;
        if (nodo != 0) {
            cadena += "Nodo" + pos + " -> Nodo" + nodo + "\n";
        }
        cadena += "Nodo" + nodo + " [label=\"";
        for (int i = 0; i < x.contador; i++) {
            cadena += "<r" + i + ">|" + temp.nombre_Cliente + "|";
            //System.out.print(temp.id + " ");
            temp = temp.siguiente;
        }
        cadena += "\"];\n";
        System.out.println(cadena);
        int tmp = nodo;
        temp = x.primero;
        while (temp != null) {
            if (temp.izquierda != null) {
                h++;
                nodo++;
                Show(temp.izquierda, tmp);
                h--;
            }
            if (temp.derecha != null) {
                h++;
                nodo++;
                Show(temp.derecha, tmp);
                h--;
            }
            temp = temp.siguiente;
        }
        return cadena;
    }

    public void Search(String id, ABB capas) {
        Search(raiz, 0, id, capas);
    }

    // Display
    private void Search(RamaB x, int pos, String id, ABB capas) {
        assert (x == null);
        NodoB temp = x.primero;
        if (nodo != 0) {
            if (temp.id.compareTo(id) == 0) {
                temp.node_Capas = capas;
            }
        }
        for (int i = 0; i < x.contador; i++) {
            if (temp.id.compareTo(id) == 0) {
                temp.node_Capas = capas;
            }
            temp = temp.siguiente;
        }
        int tmp = nodo;
        temp = x.primero;
        while (temp != null) {
            if (temp.izquierda != null) {
                Search(temp.izquierda, tmp, id, capas);
            }
            if (temp.derecha != null) {
                Search(temp.derecha, tmp, id, capas);
            }
            temp = temp.siguiente;
        }
    }

    public NodoB find(String usuarioID) {
        RamaB x = this.raiz;
        NodoB temp = x.primero;

        if (temp.id.compareTo(usuarioID) == 0) {
            return temp;
        }

        while (temp != null) {
            if (temp.id.compareTo(usuarioID) == 0) {
                return temp;
            }
            if (temp.siguiente == null && usuarioID.compareTo(temp.id) > 0) {
                temp = temp.derecha.primero;
            } else if (usuarioID.compareTo(temp.id) < 0) {
                temp = temp.izquierda.primero;
            } else {
                temp = temp.siguiente;
            }
        }
        return null;
    }
}
