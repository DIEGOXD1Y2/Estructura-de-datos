import java.util.Scanner;

class Nodo {
    private int elemento;
    private Nodo leftNodo;
    private Nodo rightNodo;

    public Nodo(int elemento) {
        this.elemento = elemento;
        this.leftNodo = null;
        this.rightNodo = null;
    }

    public int getElemento() {
        return elemento;
    }

    public void setElemento(int elemento) {
        this.elemento = elemento;
    }

    public Nodo getLeftNodo() {
        return leftNodo;
    }

    public void setLeftNodo(Nodo leftNodo) {
        this.leftNodo = leftNodo;
    }

    public Nodo getRightNodo() {
        return rightNodo;
    }

    public void setRightNodo(Nodo rightNodo) {
        this.rightNodo = rightNodo;
    }
}

public class ArbolBinario {
    protected Nodo nodoRaiz;

    public ArbolBinario() {
        nodoRaiz = null;
    }

    //aqui inserta un elemento, llama al otro insertar
    // el otro insertar es para devolver pero ya en orden donde deve
    //ser insertado ese nodo, y este insertarElemento es para llamarlo
    public void insertarElemento(int elemento) {
        nodoRaiz = insertarConOrden(nodoRaiz, elemento);
    }
    public Nodo getNodoRaiz() {
        return nodoRaiz;
    }
    /*
    Árbol binario parcialmente ordenado:
    Cada nodo debe ser menor que sus hijos directos.
    Se insertan en el hijo con menor profundidad.
    Si se rompe la propiedad, se intercambian los valores y se burbujea hacia arriba.
    */
    private Nodo insertarConOrden(Nodo actual, int elemento) {
        if (actual == null) {
            return new Nodo(elemento);
            //si el nodo actual esta basio significa que ahi tenemos que insertar el elemento
        }
        //si solo tiene 1 hijo,
        if (actual.getLeftNodo() == null) {
            //si no esta el hijo left, ahi insetaremos el nuevo nodo
            Nodo nuevo = new Nodo(elemento);
            actual.setLeftNodo(nuevo); 
            //pero si el hijo es menor asu padre los intercambiamos
            if (nuevo.getElemento() < actual.getElemento()) {
                intercambiar(actual, nuevo);
            }
            //aqui es lo mismo pero con el right
        } else if (actual.getRightNodo() == null) {
            //crea el nuevo nodo
            Nodo nuevo = new Nodo(elemento);
            //hace que el nodo actual apunde a el elemento en su lado right
            actual.setRightNodo(nuevo);
            //si el hijo es menor asu padre cambian lugares
            if (nuevo.getElemento() < actual.getElemento()) {
                intercambiar(actual, nuevo);
            }
            //si tiene dos hijos pues aqui ya no podemos ponerlo
            // asique bajamos un poco hacia donde este menos profundo
        } else {
            //usamos el metodo profundida para ver cual esta menos abajo
            //comparamos el numero que nos de de el lado left y del lado right
            if (profundidad(actual.getLeftNodo()) <= profundidad(actual.getRightNodo())) {
                //si la profundidad es mas baja en en left entonces
                //usamos el metodo insertar pero con orden
                // y le damos el nodo en el que estamos y el numero a gregar osea el elemento
                Nodo nuevoHijo = insertarConOrden(actual.getLeftNodo(), elemento);
                //se llamo asi mismo hasta encontrar el nodo donde pondremos el elemento (el numero xd)
                //ahora acualizamos el hijo left
                actual.setLeftNodo(nuevoHijo); 
                
                //recuerda si el hijo es menor a su padre cambian lugares
                if (nuevoHijo.getElemento() < actual.getElemento()) { 
                    intercambiar(actual, nuevoHijo); 
                }

            } else {
                //pues si el left no es el mas bajo obiamente el right lo es
                //asique hacemos casi lo mismo que
                //llamamos ala funcion insertarconorden y guardamos lo que nos de por que ahi es donde lo vamos a insertar
                Nodo nuevoHijo = insertarConOrden(actual.getRightNodo(), elemento);
                actual.setRightNodo(nuevoHijo);
                // hijo menor que el padre?
                if (nuevoHijo.getElemento() < actual.getElemento()) {
                    //si si pues cambiamos si no pues no pasa nada
                    intercambiar(actual, nuevoHijo);
                }
            }
        }

        return actual;
    }

    private void intercambiar(Nodo nodoPrimero, Nodo nodoDespues) {
        // lo guarda en otra variable para poder poner el otro ahi
        //(por que no queremos que se pierda lo que esta ahi guardado)
        // para depues poner ese que guardamos en el otro
        int cambiar = nodoPrimero.getElemento();
        nodoPrimero.setElemento(nodoDespues.getElemento());
        nodoDespues.setElemento(cambiar);
    }

    private int profundidad(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        //para saver la profundidad del nodo se suma 1 mas el mayor
        //la profundidad del lado left o right
        //se llama asi mismo hasta que termine devolviendo 0
        //el 1 por el nodo raiz que empieza en uno
        return 1 + Math.max(profundidad(nodo.getLeftNodo()), profundidad(nodo.getRightNodo()));
    }

    public void imprimirEnOrden(Nodo nodo) {
        //este es cuando se detiene el metodo por que es recursivo
        //si el nodo que le damos no es nulo
        if (nodo != null) {
            //se llamara asi mismo y le damos el hijo left del nodo donde estamos
            imprimirEnOrden(nodo.getLeftNodo());
            //imprime el eleento del nodo donde estamos
            System.out.print(nodo.getElemento() + " ");
           //le damos el otro hijo, el right
            //recuerda que si es nullo no pasa nada
            imprimirEnOrden(nodo.getRightNodo());
        }
    }

    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();
        Scanner red = new Scanner(System.in);
        int numero = 0;
        int numero2 =1;
        /*
        while(numero2 == 1){
            while (true) {
                try {
                    System.out.println(" ----------------");
                    System.out.print("Ingresa un numero: ");
                    numero = red.nextInt();
                    System.out.println();
                    arbol.insertarElemento(numero);
                    break;
                } catch (Exception e) {
                    System.out.println("=====> Solo numeros <=====");
                    red.next();
                }
            }
            System.out.println(" ----------------");
            while (true) {
                try {
                    System.out.println("Agregar otro numero?");
                    System.out.println(" 1) si");
                    System.out.println(" 2) no");
                    numero2 = red.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println("=====> Solo numeros <=====");
                    red.next();
                }
            }
        }  

        */
/*
                 X
                / \
               X   X
              /\   /\
            X  X  X X

 */
        
        arbol.insertarElemento(5);
/*
                 5
                / \
               X   X
              /\   /\
            X  X  X X

 */
        arbol.insertarElemento(8);
/*
                 5
                / \
               8   X
              /\   /\
            X  X  X X

 */
        arbol.insertarElemento(10);
/*
                 5
                / \
               8   10
              /\   /\
            X  X  X X

 */
        arbol.insertarElemento(12);
/*
                 5
                / \
               8   10
              /\   /\
            12  X  X X

 */
        arbol.insertarElemento(20);
/*
                 5
                / \
               8   10
              /\   /\
            12 X  20 X
 
 */
        arbol.insertarElemento(25);
/*

    5
   / \
  8   10
 / \   / \
12 25 20  X



 */

        System.out.println("Árbol en orden:");
        arbol.imprimirEnOrden(arbol.getNodoRaiz());
    }
}
