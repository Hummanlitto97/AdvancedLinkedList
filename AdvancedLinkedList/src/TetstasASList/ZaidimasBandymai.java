/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TetstasASList;
import interfacePack.ASListRealizuotas;
import java.util.Locale;
import java.util.*;
/**
 *
 * @author Hummanlitto
 */
public class ZaidimasBandymai 
{
    public static void testas()
    {
        LinkedList<String> trumpalaikisPS = new LinkedList();
        trumpalaikisPS.add("Multiplayer");
        Zaidimas a1 = new Zaidimas("Need For Power", "FPS", trumpalaikisPS, 1997, 50000, 1700);
        trumpalaikisPS.add("Gore");
        Zaidimas a2 = new Zaidimas.Builder()
                .pavadinimas("GOD")
                .zanras("RTS")
                .gamMetai(2001)
                .papildomosSavybes(trumpalaikisPS)
                .ivertinimas(6)
                .kaina(24)
                .build();
        Zaidimas a3 = new Zaidimas.Builder().buildRandom();
        Zaidimas a4 = new Zaidimas("LomAttack RTS Multiplayer Co-OP 2010 10 7");
        Zaidimas a5 = new Zaidimas("Attack FPS Gore 2007 2.5 5");
        Zaidimas a6 = new Zaidimas("Tanks RTS Rated18 History Co-OP 1990 4 6");
        Zaidimas a7 = new Zaidimas("Rose RPG Drugs Co-OP 2013 60 9.45");
        Zaidimas a8 = new Zaidimas("Sherlock Indie Multiplayer 2007 55.99 8.2");
        Zaidimas a9 = new Zaidimas("Counter RTS Multiplayer Co-OP Gore Rated20 2017 0 3");
        
        ASListRealizuotas<Zaidimas> a = new ASListRealizuotas(2, new Zaidimas("TomAttack RTS Multiplayer Co-OP 2010 10 7"), new Zaidimas("GomAttack RTS Multiplayer Co-OP 2010 10 7"), new Zaidimas("ZopAttack RTS Multiplayer Co-OP 2010 10 7"), new Zaidimas("PomAttack RTS Multiplayer Co-OP 2010 10 7"));
        a.prideti(a4);
        a.prideti(a5);
        a.prideti(a6);
        a.prideti(a3);
        ASListRealizuotas<Zaidimas> y = new ASListRealizuotas(2, new Zaidimas("TomAttack RTS Multiplayer Co-OP 2010 10 7"));
        
        System.out.println("Sąrašo spausdinimas su vienu elementu:");
        System.out.println(y.toString());
        System.out.println("Dydis: " + y.dydis());
        System.out.println("Paskutinis lygis: " + y.getPaskLyg());
        System.out.println("Sąrašo spausdinimas su 8 elementais:");
        System.out.println(a.toString());
        System.out.println("Dydis: " + a.dydis());
        System.out.println("Sąrašo spausdinimas su 8 elementais(mažėjančia tvarka):");
        System.out.println(a.toString(true));
        System.out.println("Dydis: " + a.dydis());
        System.out.println("Randa ar priklauso sąrašui:");
        System.out.println("Priklausantis: " + a.arPriklauso(a6));
        System.out.println("Nepriklausantis: " + a.arPriklauso(a9));
        System.out.println("Paskutinis lygis: " + a.getPaskLyg());
        System.out.println("Ištrina iš sąrašo 2 elementus:");
        System.out.println(a.istrinti(a6));
        System.out.println(a.toString());
        System.out.println("Paskutinis lygis: " + a.getPaskLyg());
        System.out.println("Dydis(po pirmo trinimo): " + a.dydis());
        System.out.println(a.istrinti(a4));
        System.out.println(a.toString());
        System.out.println("Paskutinis lygis: " + a.getPaskLyg());
        System.out.println("Dydis(po antro trinimo): " + a.dydis());
        System.out.println("Spausdina su ištrintu:");
        System.out.println(a.toString());
        System.out.println("Dydis: " + a.dydis());
        System.out.println("Spausdina su ištrintu(mažėjančia tvarka):");
        System.out.println(a.toString(true));
        System.out.println("Dydis: " + a.dydis());
        System.out.println("Spausdina po trinimo ir pridėjimo:");
        a.prideti(a8);
        System.out.println(a.toString());
        System.out.println("Dydis: " + a.dydis());
    }
    
    public static void main(String[] args) throws CloneNotSupportedException
    {
        Locale.setDefault(new Locale("US"));
        testas();
    }
}
