import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Collections;

public interface DataCounter<E> {
    //OVERVIEW: DataCounter è una collezione finita e modificabile di oggetti generici E,
    //          dove ad ogni oggetto è associato un valore numerico intero
    //TYPICAL ELEMENT { <ogg_0, val_0> ... <ogg_n-1, val_n-1> | ogg_i istanceOf E && val_i ∈ int 0<=i<n-1}

    //incrementa il valore associato all’elemento data di tipo E
    public void incCount(E data) throws NullPointerException, MissingElementException;
    //REQUIRES: data!=null && data ∈ this
    //THROWS: Se (data==null) lancia una NullPointerException
    //        Se (data ∉ this) lancia una MissingElementException
    //MODIFIES: val_data
    //EFFECTS: incrementa di uno il valore associato a "data"

    // restituisce il numero degli elementi presenti nella collezione
    public int getSize();
    //REQUIRES:
    //THROWS:
    //MODIFIES:
    //EFFECTS: restituisce la cardinalità della collezione (n)

    //restituisce il valore corrente associato al parametro data,
    // e 0 se data non appartiene alla collezione
    public int getCount(E data) throws NullPointerException;
    //REQUIRES: data!=null
    //THROWS: se (data==null) lancia una NullPointerException
    //MODIFIES:
    //EFFECTS: restituisce val_data se  data è presente nella collezione
    //         altrimenti restituisce 0

    // restituisce un iteratore (senza remove) per la collezione
    public Iterator<Map.Entry<E,Integer>> getIterator();
    //REQUIRES:
    //THROWS:
    //MODIFIES:
    //EFFECTS: restituisce un Iteratore sulla collezione senza la remove
}

class MissingElementException extends Exception{
    public MissingElementException(){
        super();
    }
    public MissingElementException(String s){
        super(s);
    }
}
