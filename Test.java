import java.util.*;
import java.io.*;

public class Test{

    //OVERVIEW: Test èè la classe responsabile

    public static void main(String[] args) throws FileNotFoundException, IOException {

        Scanner input=new Scanner(System.in);
        Scanner input2=new Scanner(System.in);
        String file = null;

        //MENU 1 (selezione file da testcase o manuale)
        System.out.println("Che file vuoi aprire?");
        System.out.println();
        System.out.println("1) test1.txt");
        System.out.println("2) test2.txt");
        System.out.println("3) test3.txt");
        System.out.println("4) Inserire percorso:");
        System.out.println();

        int f =input.nextInt();
        if(f>4 || f<1) throw new IOException("Il file non esiste");

        if(f==1){file = new String("testcase/test1.txt");}
        else if(f==2){file = new String("testcase/test2.txt");}
        else if(f==3){file = new String("testcase/test3.txt");}
        //SUBMENU 1 Selezione file manuale
        else if(f==4){
            System.out.println();
            System.out.print("Inserire il percorso assoluto del file: ");
            file = input2.nextLine();
        }
        //CREAZIONE FILE (se esiste)
        File prova = new File(file);

        //MENU 2 (selezione su che struttura usare)
        System.out.println();
        System.out.println("Che struttura vuoi utilizzare?");
        System.out.println();
        System.out.println("1) HashDataCounter");
        System.out.println("2) MapDataCounter");
        System.out.println();

        int s =input.nextInt();
        if(s>2 || s<1) throw new IOException("Struttura dati non presente");

        //Inizio esecuzione del main
        long inizio = System.currentTimeMillis();
        //creazione dizionario con struttura proposta
        Dictionary p = new Dictionary(prova,s);
        //Intestazione file
        System.out.println();
        System.out.print("File:     "+file);
        System.out.println();
        p.stampa();
        //Output performance
        long fine = System.currentTimeMillis();
        long time=((fine-inizio));
        System.out.println();
        System.out.println("time exec: " + time+"ms");
    }

}
