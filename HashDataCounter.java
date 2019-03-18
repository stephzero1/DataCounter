import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import java.util.Comparator;
import java.util.Collections;

public class HashDataCounter<E extends Comparable<E>> implements DataCounter<E>{

    //OVERVIEW: HashDataCounter implementa una collezione di oggetti E associati ad un valore Integer

    //AF: { <key_i,ogg.get(key_i)> | ogg.containsKey(key_i)==T && 0<=i<ogg.size()=n && key_i != null }
    //IR: { ForAll i,j . 0<=key_i<key_j<ogg.size() => key_i!=key_j }

    private Hashtable<E,Integer> ogg;

    public HashDataCounter(){
        ogg = new Hashtable<>();
    }
    public HashDataCounter(List<E> ist){
        ogg = new Hashtable<>();
        Integer n;

        //Chiama getCount che restituisce 0 se "el" non c'è, altrimenti il suo val associato
        //Somma 1 e lo aggiunge direttamente (eventualmente sovrascrivendo la vecchia occorrenza)
        for(E el : ist){
            n=this.getCount(el)+1;
            ogg.put(el,n);
        }
    }

    //incrementa il valore associato all’elemento data di tipo E
    public void incCount(E data) throws NullPointerException, MissingElementException{
        if(data==null) throw new NullPointerException();
        Integer n = this.getCount(data);
        if(n==0) throw new MissingElementException();
        else ogg.put(data,n+1);
    }
    /*
    //REQUIRES: data!=null && data ∈ this
    //THROWS: Se (data==null) lancia una NullPointerException
    //        Se data ∉ this lancia una MissingElementException
    //MODIFIES: ogg.get(data)
    //EFFECTS: ogg.put(data,n+1);
    */

    // restituisce il numero degli elementi presenti nella collezione
    public int getSize(){
        return ogg.size();
    }
    //REQUIRES:
    //THROWS:
    //MODIFIES:
    //EFFECTS: restituisce la cardinalità dell'insieme

    //restituisce il valore corrente associato al parametro data,
    // e 0 se data non appartiene alla collezione
    public int getCount(E data) throws NullPointerException{
        if(data==null) throw new NullPointerException();
        Integer n = ogg.get(data);
        if(n==null) n=0;
        return n;
    }
    //REQUIRES: data!=null
    //THROWS: se data==null lancia una NullPointerException
    //MODIFIES:
    //EFFECTS: restituisce il valore numerico associato a data se esso è presente
    //         altrimenti restituisce 0

    // restituisce un iteratore (senza remove) per la collezione
    public Iterator<Map.Entry<E,Integer>> getIterator(){
        Comparator<Map.Entry<E,Integer>> cmp = new Comparator<Map.Entry<E,Integer>>(){
            public int compare(Map.Entry<E,Integer> e1, Map.Entry<E,Integer> e2){
                int j = e2.getValue().compareTo(e1.getValue());
                if(j==0) j= e1.getKey().compareTo(e2.getKey());
                return j;
            }
        };
        //Richiama il Set navigabile dell'ogg
        List<Map.Entry<E,Integer>> lst = new ArrayList<Map.Entry<E,Integer>>(ogg.entrySet());
        Collections.sort(lst,cmp);

        MyIter<E> itr = new MyIter<E>(lst.iterator());
        return itr;
    }
    //REQUIRES:
    //THROWS:
    //MODIFIES:
    //EFFECTS: restituisce un Iteratore di coppie <chiave, valore> ordinato, senza la remove
}
