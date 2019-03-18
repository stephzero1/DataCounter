import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Iterator;

public class Dictionary{

    private DataCounter<String> struttura;

    //Var che conta numero di parole che ripetizione
    private int length;
    //var che conta numero di parole senza ripetizione
    private int size;
    //Var di appoggio per la stampa
        private int max=0;

    public Dictionary(File in, int choose) throws IOException{
        //Var di appoggio
        List<String> arr;
        BufferedReader input = null;

        String currline;
        StringBuilder tmp;
        char t;

        boolean check = false;

        //Caricamento file per la lettura
        try{
            input = new BufferedReader(new FileReader(in));
        }
        catch(FileNotFoundException e){
            System.out.println(in+" File non trovato");
        }

        tmp = new StringBuilder();
        arr = new ArrayList<String>();

        //Sbobinatura File, operazione di filtraggio e inserimento in struttura
        try{
            while((currline = input.readLine())!=null){
                //Scansione per riga, con lettura di ogni parolaconfinata da caratteri non alfabetici
                //e relativo inserimento in ArrayList
                for(int i = 0; i < currline.length(); i++){
                    t=currline.charAt(i);
                    if(Character.isAlphabetic(t)){
                        tmp=tmp.append(Character.toLowerCase(t));
                        if(!check) check=true;
                    }
                    else{
                        if(check){
                            //Aggiornamento var di appoggio per output
                            if(max<tmp.toString().length())max=tmp.toString().length();
                            //Costruzione stringa finale e inserimento in ArrayList
                            arr.add(tmp.toString());
                            tmp=new StringBuilder();
                            check=false;
                        }
                    }
                }
                //Aggiornamento ultima parola della stringa letta
                if(check){
                    arr.add(tmp.toString());
                    tmp=new StringBuilder();
                    check=false;
                }
            }
            //Chiusura File
            input.close();
        }
        catch(IOException e){
            System.out.println("cip");
        }
        //INSERIMENTO IN STRUTTURA
        if(choose==1) struttura = new HashDataCounter<String>(arr);
        else if(choose==2) struttura = new MapDataCounter<String>(arr);
        //Aggiornamento Var #parole
        length=arr.size();
        size=struttura.getSize();
    }
    //REQUIRES:
    //THROWS:
    //MODIFIES:
    //EFFECTS: restituisce una List<Map.Entry<String,Integer>> ordinata per Integer e per String

    public void stampa(){
        //Stampa intestazione file
        System.out.println();
        System.out.println("#Parole con ripetizione:   " + length);
        System.out.println("#Parole senza ripetizione: " + size);
        System.out.println();

        //Stampa le parole con relative occorrenze (in maniera ordinata)
        int c=1;
        //for(Map.Entry<String,Integer> val : struttura.getIterator()){
        Iterator<Map.Entry<String,Integer>> itr = struttura.getIterator();
        while(itr.hasNext()){
            //Chiama l'iteratore che ha già ordinato su valori e su chiavi
            Map.Entry<String,Integer> it = itr.next();
            //Stampa la Lista di valori ordinati
            System.out.print("#"+c+"    ");
            System.out.print(it.getKey() + ":    ");
            int i=it.getKey().length();
            while(i<=max){System.out.print(" "); i++;}
            System.out.print(it.getValue());
            //Stampa separatore di trattini "--------"
            System.out.println();
            for(i=0; i<max+20; i++) System.out.print("-");
            System.out.println();
            c++;
        }
    }
    //REQUIRES:
    //THROWS:
    //MODIFIES:
    //EFFECTS: Stampa
}
