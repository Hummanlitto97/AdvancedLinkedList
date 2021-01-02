/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TetstasASList;

import interfacePack.ASListRealizuotas;
import java.io.PrintStream;


public class Greitaveika
{
        final private static PrintStream p = System.out;

    public static void greitaveikosTyrimas() 
    {
        int pr1 = 1_000;
        int pr2 = 500;
                
        int tikri = 10_000;
        int[] ppp = {5, 193, 100000, 55, 44, 666, 6666 ,99998};
        ASListRealizuotas<Integer> s1 = new ASListRealizuotas(pr1);
        long t0 = System.nanoTime();
        for (int k = 0; k < 1; k++) 
        {
            for (int i = 0; i < tikri; i++) 
            {
                s1.prideti(i);
            }
        }
        long t1 = System.nanoTime();
        long t00 = System.nanoTime();
        for (int k = 0; k < 1; k++) 
        {
            for (int i = tikri; i > 0; i--) 
            {
                for(int p:ppp)
                {
                    s1.arPriklauso(p);
                }
            }
        }
        long t12 = System.nanoTime();
        long t2 = System.nanoTime();
        for (int k = 0; k < 1; k++) 
        {
            for (int i = tikri; i > 0; i--) 
            {
                s1.istrinti(i);
            }
        }
        long t3 = System.nanoTime();
        ASListRealizuotas<Integer> s2 = new ASListRealizuotas(pr2);
        long t4 = System.nanoTime();
        for (int k = 0; k < 1; k++) 
        {
            for (int i = 0; i < tikri; i++) 
            {
                s2.prideti(i);
            }
        }
        long t5 = System.nanoTime();
        long t01 = System.nanoTime();
        for (int k = 0; k < 1; k++) 
        {
            for (int i = tikri; i > 0; i--) 
            {
                for(int p:ppp)
                {
                    s2.arPriklauso(p);
                }
            }
        }
        long t13 = System.nanoTime();
        long t6 = System.nanoTime();
        for (int k = 0; k < 1; k++) 
        {
            for (int i = tikri; i > 0; i--) 
            {
                s2.istrinti(i);
            }
        }
        long t7 = System.nanoTime();
        System.out.println("Pirmas variantas:");
        System.out.println("arPriklauso dt="+(t12-t00)*1e-9);
        System.out.println("Prideti dt="+(t1-t0)*1e-9);
        System.out.println("Istrinti dt="+(t3-t2)*1e-9);
        System.out.println("Antras variantas:");
        System.out.println("arPriklauso dt="+(t13-t01)*1e-9);
        System.out.println("Prideti dt="+(t5-t4)*1e-9);
        System.out.println("Istrinti dt="+(t7-t6)*1e-9);
    }
    public static void main(String[] args) 
    {
        System.setErr(p);
        greitaveikosTyrimas();
    }

}
