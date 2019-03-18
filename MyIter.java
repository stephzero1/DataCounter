import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class MyIter<E> implements Iterator<Map.Entry<E,Integer>>{
    //OVERVIEW: MyIter è una classe d'appoggio che crea un Iteratore senza il metodo remove

    private Iterator<Map.Entry<E,Integer>> itr;

    public MyIter(final Iterator<Map.Entry<E,Integer>> parametro) {
      if (parametro == null) {
          throw new NullPointerException("Iteratore Vuoto");
      }
      this.itr = parametro;
    }
    //REQUIRES: paramentro != null
    //THROWS: se (parametro==null) lancia una NullPointerException
    //MODIFIES:
    //EFFECTS:

    public boolean hasNext(){return this.itr.hasNext();}
    public Map.Entry<E,Integer> next(){return this.itr.next();}

    public void remove(){ throw new UnsupportedOperationException("remove() non consentita!"); }
    //EFFECTS: lancia una UnsupportedOperationException;
}
