/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacePack;

/**
 *
 * @author Hummanlitto
 * Išplėstinis nuorodų sąrašas, dvikryptis , nuorodos sąrašas saugo nuorodų sąrašus, dirba su bet kokaiais tipais 
 */
public interface ASLList<K>
{
    //Patikrinama ar aibė tuščia.
    boolean arNetuscia();

    // Grąžinamas aibėje esančių elementų kiekis.
    int dydis();

    // Išvaloma aibė.
    void isvalyti();

    // Aibė papildoma nauju elementu.
    void prideti(K elementas);

    // Pašalinamas elementas iš aibės.
    boolean istrinti(K elementas);

    // Patikrinama ar elementas egzistuoja aibėje.
    boolean arPriklauso(K elementas);

}
    
