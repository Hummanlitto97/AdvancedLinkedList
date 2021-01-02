/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacePack;
import java.util.Collection;

/**
 *
 * @author Hummanlitto
 */
public class ASListRealizuotas<K extends Comparable<K>> implements ASLList<K>, Cloneable
{
    //Elementų kiekis sąraše
    protected int dydis;
    //Paskutinio lygio nr
    protected int paskLygis;
    //Kiekis į kiek dalių dalinti ir po to didina lygius iki nurodyto makLygiu kiekvieną kartą dalindamas sąrašo dydį iš daliklis
    protected final int daliklis;
    //Sąrašo pradžia
    protected Nuoroda<K> pradzia;
    //Sąrašo pabaiga
    protected Nuoroda<K> pabaiga;
    //Sąrašo rodyklė į aukščiausią lygį(Pradžia)
    protected Nuoroda<K> rodykle;
    //Sąrašo rodyklė į aukščiausią lygį(Pabaiga)
    protected Nuoroda<K> rodyklePab;
    //Sąrašo paskutinis istrintas
    protected Nuoroda<K> istrintas;
    //Sąrašų kiekis
    protected static int kiekis;
    
    //Tiesiog sukurimas sąrašo (
    public ASListRealizuotas()
    {
        this(0);
    }
    
    //Tiesiog sukurimas sąrašo 
    public ASListRealizuotas(int daliklis)
    {
        this.daliklis = daliklis;
        pradzia = null;
        pabaiga = null;
        rodykle = null;
        paskLygis = 0;
    }
    
    //Sukurimas paduodant elementus masyve ir nurodant dalikli
    public ASListRealizuotas(int daliklis, K...elemKiekis)
    {
        this.daliklis = daliklis;
        pradzia = null;
        pabaiga = null;
        rodykle = null;
        paskLygis = 0;
        for(K elem: elemKiekis)
        {
            prideti(elem);
        }
        
    }
    
    //Sukurimas pridedant sąrašo elementus, kiekis nurodamas
    public ASListRealizuotas(Collection<K> sarasas, int daliklis)
    {
        this(daliklis, (K[])sarasas.toArray());
    }
    
    public int getPaskLyg()
    {
        return paskLygis;
    }
    
    //Prideda failą į sąrašą, tada surikiuoja ir padalina į lygius
    @Override
    public void prideti(K elem)
    {
        if(elem == null)
        {
            throw new NullPointerException("Metode pridėti(K elem) buvo nusiųstas elementas be reikšmės");
        }
            if(pradzia == null)
            {
                pradzia = new Nuoroda();
                pradzia.desine = new Nuoroda(elem);
                pabaiga = new Nuoroda();
                pabaiga.kaire = pradzia.desine;
                rodykle = pradzia;
                rodyklePab = pabaiga;
                dydis++;
            }
            else
            {
                boolean reikiaRikiavimo = false;
                Nuoroda<K> idejimui = new Nuoroda(elem, pabaiga.kaire, null);
                if(elem.compareTo(lygioPaskutinisMazgas(0).kaire.elem) < 0)
                {
                    reikiaRikiavimo = true;
                }
                pabaiga.kaire.desine = idejimui;
                pabaiga.kaire = idejimui;
                dydis++;
                if(reikiaRikiavimo)
                {
                    rikiavimas();
                }
                if(daliklis != 0 && daliklis != 1)
                {
                    padalinimas();
                }
            }
    }
    
    //Padalina į lygius sąrašą
    private void padalinimas()
    {
        if(dydis%daliklis != 0)
            {
                return;
            }
        int eilesNr = 0;
        for(Nuoroda<K> dalinimas = pradzia;dalinimas != null;dalinimas=dalinimas.virsus) //lygiams
        {
            int kelintas = 0;
            for(Nuoroda<K> naujas = dalinimas.desine;naujas != null;naujas=naujas.desine) //elementams
            {       
                if(++kelintas%daliklis == 0 && naujas.virsus == null)//randa tinkamus skaicius
                {
                    Nuoroda<K> naujamElementui = lygioPaskutinisMazgas(++eilesNr);
                    if(naujamElementui == null)
                    {
                      naujas.virsus = new Nuoroda(naujas.elem, null, null, naujas, null);   
                      rodykle.virsus = new Nuoroda(); //naujas viršus pradžiai
                      rodykle.virsus.desine = naujas.virsus;
                      rodykle.virsus.apacia = rodykle;
                      rodyklePab.virsus = new Nuoroda();//naujas viršus pabaigai
                      rodyklePab.virsus.kaire = naujas.virsus;
                      rodyklePab.virsus.apacia = rodyklePab;
                      rodykle = rodykle.virsus;
                      rodyklePab = rodyklePab.virsus;
                      paskLygis++;
                    }
                    else
                    {
                        naujas.virsus = new Nuoroda(naujas.elem, naujamElementui.kaire, null, naujas, null); 
                        naujamElementui.kaire.desine = naujas.virsus;
                        Nuoroda<K> pataisymasPab = lygioPaskutinisMazgas(eilesNr);
                        pataisymasPab.kaire = naujas.virsus;
                    }
                }
                else if(naujas.virsus == null)
                {
                    
                }
                    
            }
        }
    }
    
    //Po pridėjimo ir padalinmo(Jei buvo), surikiuoja nuo mažiausio iki didžiausio
    private void rikiavimas()
    {
        Nuoroda<K> trumpalaikis = pabaiga.kaire;
        Nuoroda<K> trumpalaikisV;
        K buves;
        Nuoroda<K> rodykleDidesnis = pabaiga.kaire;
        while(trumpalaikis.kaire != null)
        {
            if(trumpalaikis.kaire.elem.compareTo(rodykleDidesnis.elem) > 0)
            {
                buves = rodykleDidesnis.elem;
                rodykleDidesnis.elem = trumpalaikis.kaire.elem;
                if(rodykleDidesnis.virsus != null)
                {
                    trumpalaikisV = rodykleDidesnis.virsus;
                    while(trumpalaikisV != null)
                    {
                        trumpalaikisV.elem = trumpalaikis.kaire.elem;
                        trumpalaikisV = trumpalaikisV.virsus;
                    }
                }
                if(rodykleDidesnis.apacia != null)
                {
                    trumpalaikisV = rodykleDidesnis.apacia;
                    while(trumpalaikisV != null)
                    {
                        trumpalaikisV.elem = trumpalaikis.kaire.elem;
                        trumpalaikisV = trumpalaikisV.apacia;
                    }
                }
                rodykleDidesnis = trumpalaikis.kaire;
                rodykleDidesnis.elem = buves;
                if(rodykleDidesnis.virsus != null)
                {
                    trumpalaikisV = rodykleDidesnis.virsus;
                    while(trumpalaikisV != null)
                    {
                        trumpalaikisV.elem = buves;
                        trumpalaikisV = trumpalaikisV.virsus;
                    }
                }
                if(rodykleDidesnis.apacia != null)
                {
                    trumpalaikisV = rodykleDidesnis.apacia;
                    while(trumpalaikisV != null)
                    {
                        trumpalaikisV.elem = buves;
                        trumpalaikisV = trumpalaikisV.apacia;
                    }
                }
                trumpalaikis = trumpalaikis.kaire;
            }
            else 
            {
                return;
            }
        }
    }
    
    private void rikiavimasPoTrinimo()
    {
        for(Nuoroda<K> nullElem = istrintas;nullElem != null;nullElem = nullElem.desine)
        {
            if(nullElem.desine != null)
            {
                nullElem.elem = nullElem.desine.elem;
                Nuoroda<K> trump = nullElem.virsus;
                while(trump != null)
                {
                    trump.elem = nullElem.desine.elem;
                    trump = trump.virsus;
                }
                nullElem.desine.elem = null;
                trump = nullElem.desine.virsus;
                while(trump != null)
                {
                    trump.elem = nullElem.desine.elem;
                    trump = trump.virsus;
                }
            }
            else
            {
                pasalintiGalune();
                return;
            }
        } 
    }
    
    private void pasalintiGalune()
    {
        for(Nuoroda<K> pakeistiPab = pabaiga;pakeistiPab != null;pakeistiPab = pakeistiPab.virsus)
        {
            if(pakeistiPab.kaire != null)
            {
                if(pakeistiPab.kaire.kaire != null)
                {
                    Nuoroda<K> salinimui = pakeistiPab.kaire.kaire;
                    salinimui.desine = null;
                    pakeistiPab.kaire = pakeistiPab.kaire.kaire;
                }
                else if(pakeistiPab.kaire.elem == null)
                {
                    if(pakeistiPab.apacia != null)
                    {
                        pakeistiPab = pakeistiPab.apacia;
                        pakeistiPab.virsus = null;
                        rodyklePab = pakeistiPab;
                        rodykle = rodykle.apacia;
                        rodykle.virsus = null;
                    }
                    else
                    {
                        rodykle = pradzia;
                        rodykle.virsus = null;
                        rodyklePab = pabaiga;
                        rodyklePab.virsus = null;
                        paskLygis--;
                    }
                }
            }
        }
    }
    //Paieška po sąrašą rekursiškai
    public Nuoroda<K> rekursiskaPaieska(Nuoroda<K> mazgas, K elem)
    {
       int cmp = mazgas.elem.compareTo(elem);
       if(cmp == 0)
       {
           return mazgas; //grąžina, jei rado
       }
       else if(cmp > 0)
       {
           if(mazgas.kaire != null)
           {
               if(mazgas.kaire.elem.compareTo(elem) < 0)
               {
                   if(mazgas.apacia != null)
                   {
                       return rekursiskaPaieska(mazgas.apacia,elem);
                   }
                   else
                   {
                       return null;
                   }
               }
               else
               {
                    return rekursiskaPaieska(mazgas.kaire,elem);
               }
           }
           else if(mazgas.apacia != null)
           {
               return rekursiskaPaieska(mazgas.apacia,elem);
           }
           else
           {
               return null;
           }
       }
       else if(cmp < 0)
       {
           if(mazgas.desine != null)
           {
               if(mazgas.desine.elem.compareTo(elem) > 0)
               {
                   if(mazgas.apacia != null)
                   {
                       return rekursiskaPaieska(mazgas.apacia,elem);
                   }
                   else
                   {
                       return null;
                   }
               }
               else
               {
                    return rekursiskaPaieska(mazgas.desine,elem);
               }
           }
           else if(mazgas.apacia != null)
           {
               return rekursiskaPaieska(mazgas.apacia,elem);
           }
           else
           {
               return null;
           }
       }
       else
       {
           return null;
       }
    }
    

    //Randa paskutinį elemento mazgą
    private Nuoroda<K> lygioPaskutinisMazgas(int lygis)
    {
        int kelintas = 0;
        for(Nuoroda<K> elemPab = pabaiga;elemPab != null;elemPab = elemPab.virsus)
        {
            if(kelintas++ == lygis)
            {
                return elemPab;
            }
        }
        return null;
    }
    
    public int lygioElemKiekis(int lygis)
    {
        Nuoroda<K> elemPradzia = lygioPradzia(lygis).desine;
        if(elemPradzia == null)
        {
            return 0;
        }
        else
        {
            int kiekis = 0;
            for(;elemPradzia != null;elemPradzia = elemPradzia.desine)
            {
               kiekis++; 
            }
            return kiekis;
        }
        
    }
    //Randa lygio pradinį mazgą 
    private Nuoroda<K> lygioPradzia(int lygis)
    {
        int kelintas = 0;
        for(Nuoroda<K> reikiamas = pradzia;reikiamas != null;reikiamas = reikiamas.virsus)
        {
            if(kelintas++ == lygis)
            {
                return reikiamas;
            }
        }
        return null;
    }
    //Tikrina, ar sąrašas netuščias
    @Override
    public boolean arNetuscia()
    {
        return pradzia != null;
    }

    // Grąžinamas aibėje esančių elementų kiekis.
    @Override
    public int dydis()
    {
        return dydis;
    }

    // Išvaloma aibė.
    @Override
    public void isvalyti()
    {
       pradzia = null;
       pabaiga = null;
       rodykle = null;
       rodyklePab = null;
       istrintas = null;
       paskLygis = 0;
       kiekis--;
       dydis = 0;
    }
   
    // Pašalinamas elementas iš aibės.
    @Override
    public boolean istrinti(K elementas)
    {
        if(elementas == null)
        {
            throw new NullPointerException("metode istrinti(K elementas) paduotas elementas neturi reikšmės");
        }
        if(kolIstrinti(rekursiskaPaieska(rodykle.desine, elementas)) != null)
        {
            rikiavimasPoTrinimo();
            return true;
        }
        else
        {
            return false;
        }
    }
    
    private Nuoroda<K> kolIstrinti(Nuoroda<K> mazgas)
    {
        if(mazgas == null)
        {
            return null;
        }
        dydis--;
        while(mazgas != null)
        {
            mazgas.elem = null;
            istrintas = mazgas;
            mazgas = mazgas.apacia;
        }
        
        return istrintas;
    }
    
    // Patikrinama ar elementas egzistuoja aibėje.
    @Override
    public boolean arPriklauso(K elementas)
    {
        return rekursiskaPaieska(rodykle.desine,elementas) != null;
    }
    
    //Spausdina didėjančia tvarka
    @Override
    public String toString()
    {
        int kelintas = 0;
        String informacija = "";
        for(Nuoroda<K> lygiai = pradzia;lygiai != null;lygiai=lygiai.virsus)
        {
                informacija += String.format("Lygis %d:\n", kelintas++);
                for(Nuoroda<K> elementai = lygiai.desine;elementai != null;elementai=elementai.desine)
                {
                        informacija += String.format("%s\n", elementai.elem);
                }   
        }
        return informacija;
    }
    
    //Spasudina mažėjančia tvarka, jei true , naudoja paprasta toString(), jei false
    public String toString(boolean mazejanti)
    {
        if(mazejanti)
        {
            int kelintas = 0;
            String informacija = "";
            for(Nuoroda<K> lygiai = pabaiga;lygiai != null;lygiai=lygiai.virsus)
            {
                    informacija += String.format("Lygis %d:\n", kelintas++);
                    for(Nuoroda<K> elementai = lygiai.kaire;elementai != null;elementai=elementai.kaire)
                    {
                            informacija += String.format("%s\n", elementai.elem);
                    }  
            }
            return informacija;
        }
        else
        {
            return toString();
        }
    }

    public class Nuoroda<K>
    {
        //Dedamas elementas
        protected K elem;
        //Nuoroda į kitą sąraša kairėn
        protected Nuoroda<K> kaire;
        //Nuoroda į kitą sąraša dešinėn
        protected Nuoroda<K> desine;
        //Nuoroda į sąrašo nuo elemento tęsinį apačioje
        protected Nuoroda<K> apacia;
        //Nuoroda į elemento sąrašo viršų
        protected Nuoroda<K> virsus;
        
        Nuoroda()
        {
            this.elem = null;
            kaire = null;
            desine = null;
            apacia = null;
            virsus = null;
        }
        
        Nuoroda(K elem)
        {
            this.elem = elem;
            kaire = null;
            desine = null;
            apacia = null;
            virsus = null;
        }
        
        Nuoroda(K elem, Nuoroda<K> kairen, Nuoroda<K> desinen)
        {
            this.elem = elem;
            kaire = kairen;
            desine = desinen;
            apacia = null;
            virsus = null;
        }
        
        Nuoroda(K elem, Nuoroda<K> kairen, Nuoroda<K> desinen, Nuoroda<K> apacion, Nuoroda<K> virsun)
        {
            this.elem = elem;
            kaire = kairen;
            desine = desinen;
            apacia = apacion;
            virsus = virsun;
        }
    }
}
