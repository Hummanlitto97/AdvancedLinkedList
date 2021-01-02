/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TetstasASList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author edvsad1
 */
public class Zaidimas implements Comparable<Zaidimas>
{
    //Konstantos
    private static final String idCode = "CG";    
    private static int serNr = 100;   
    private final String zaidRegNr;
    //Savybės 
    private String pavadinimas;
    private String zanras;
    private LinkedList<String> papildomosSavybes;
    private int metaiIsleidimo;
    private double kaina;
    private double ivertinimas;
    
    public Zaidimas()
    {
        papildomosSavybes = new LinkedList<String>();
        zaidRegNr = idCode + (serNr++);
    }
    
    public Zaidimas(String pavadinimas, String zanras, LinkedList papildomosSavybes, 
            int metaiIsleidimo, double kaina, double ivertinimas)
    {
        zaidRegNr = idCode + (serNr++);
        this.pavadinimas = pavadinimas;
        this.zanras = zanras;
        this.papildomosSavybes = papildomosSavybes;
        this.metaiIsleidimo = metaiIsleidimo;
        this.kaina = kaina;
        this.ivertinimas = ivertinimas;
    }
    
    public Zaidimas(Builder builder) {
        zaidRegNr = idCode + (serNr++);    // suteikiamas originalus autoRegNr
        this.zanras= builder.zanras;
        this.pavadinimas = builder.pavadinimas;
        this.metaiIsleidimo = builder.gamMetai;
        this.papildomosSavybes = builder.papildomosSavybes;
        this.ivertinimas = builder.ivertinimas;
        this.kaina = builder.kaina;
    }
    
    public Zaidimas(String sarasasSavybiu)
    {
        papildomosSavybes = new LinkedList();
        zaidRegNr = idCode + (serNr++);
        this.parse(sarasasSavybiu);
    }
    
    public String getPavadinimas()
    {
        return pavadinimas;
    }
    
    public String getZanras()
    {
        return zanras;
    }
    
    public LinkedList<String> getPapildomosSavybes()
    {
        return papildomosSavybes;
    }
    
    public int getMetai()
    {
        return metaiIsleidimo;
    }
    
    public double getKaina()
    {
        return kaina;
    }
    
    public double getIvertinimas()
    {
        return ivertinimas;
    }
    
    public int getPapildomuSavybiuKiekis()
    {
        return papildomosSavybes.size();
    }
    
    public String getZaidRegNr()
    {
        return zaidRegNr;
    }
    
    public void setKaina(double kiek)
    {
        kaina = kiek;
    }
    
    public void addPapildomaSavybe(String savybe)
    {
        papildomosSavybes.add(savybe);
    }
    
    public Zaidimas create(String sarasasSavybiu)
    {
        Zaidimas trumpalaikis = new Zaidimas();
        trumpalaikis.parse(sarasasSavybiu);
        return trumpalaikis;
    }
    
    public final void parse(String sarasasSavybiu)
    {
            Scanner sarasas = new Scanner(sarasasSavybiu);
            pavadinimas = sarasas.next();
            zanras = sarasas.next();
            while(!sarasas.hasNextInt())
            {
                String a = sarasas.next();
                papildomosSavybes.add(a);
            }
            metaiIsleidimo = sarasas.nextInt();
            kaina = sarasas.nextDouble();
            ivertinimas = sarasas.nextDouble();
    }
    
    @Override
    public int compareTo(Zaidimas kitas)
    {
            return getZaidRegNr().compareTo(kitas.getZaidRegNr());
    }
    
    public final static Comparator<Zaidimas> pagalSavybiuKieki = (Zaidimas z1, Zaidimas z2)->
    {
        if(z1.getPapildomosSavybes().size() == z2.getPapildomosSavybes().size())
                {
                    return 0;
                }
                else
                {
                    if(z1.getPapildomosSavybes().size() > z2.getPapildomosSavybes().size())
                    {
                        return 1;
                    }
                    else
                    {
                        return -1;
                    }
                }
    };
    
    public final static Comparator<Zaidimas> pagalZanra = (Zaidimas z1, Zaidimas z2)->
    {
        return z1.getZanras().compareToIgnoreCase(z2.getZanras());
    };
    
    public final static Comparator<Zaidimas> pagalPavadinima = (Zaidimas z1, Zaidimas z2)->
    {
        return z1.getPavadinimas().compareToIgnoreCase(z2.getPavadinimas());
    };
    
    public final static Comparator<Zaidimas> pagalIvertinima = (Zaidimas z1, Zaidimas z2)->
    {
        if(z1.getIvertinimas() == z2.getIvertinimas())
                {
                    return 0;
                }
                else
                {
                    if(z1.getIvertinimas() > z2.getIvertinimas())
                    {
                        return 1;
                    }
                    else
                    {
                        return -1;
                    }
                }
    };
    
    public final static Comparator<Zaidimas> pagalMetus = (Zaidimas z1, Zaidimas z2)->
    {
        if(z1.getMetai() == z2.getMetai())
                {
                    return 0;
                }
                else
                {
                    if(z1.getMetai() > z2.getMetai())
                    {
                        return 1;
                    }
                    else
                    {
                        return -1;
                    }
                }
    };
    
    public final static Comparator<Zaidimas> pagalKaina = (Zaidimas z1, Zaidimas z2)-> 
            {   
                if(z1.getKaina() == z2.getKaina())
                {
                    return 0;
                }
                else
                {
                    if(z1.getKaina() > z2.getKaina())
                    {
                        return 1;
                    }
                    else
                    {
                        return -1;
                    }
                }
            };
    

    
    @Override
    public String toString()
    {
        int kelintas = 0;
        String savybiuSarasas = "";
        for(String savybe: papildomosSavybes)
        {
            kelintas++;
            if(papildomosSavybes.size() == kelintas)
            {
                savybiuSarasas += savybe;
            }
            else
            {
                savybiuSarasas += savybe + ",";
            }
        }
        return String.format("%-15s|%-15s|%-8s|%-8s|%-8d|%-8.2f|%-8.2f", zaidRegNr, pavadinimas, zanras, savybiuSarasas, metaiIsleidimo, kaina, ivertinimas);   
    }
    
    public static class Builder 
    {

        private final static Random RANDOM = new Random(1974);  // Atsitiktinių generatorius
        private final static String[][] MODELIAI = { // galimų žanrų ir jų savybių masyvas
            {"FPS", "Multiplayer", "Gore"},
            {"RTS", "Gore", "Dynamic Grahpics"},
            {"RPG", "Choose Path", "Adventure"},
            {"MMO", "Free", "Story"},
        };
        private final static String[] PAVADINIMAI = {"Hulk", "Dragon slayer", "Mafia", "Juggernaut"};

        private String pavadinimas = "";
        private String zanras = "";
        private int gamMetai = -1;
        private LinkedList<String> papildomosSavybes = new LinkedList();
        private double kaina = -1.0;
        private double ivertinimas = -1.0;

        public Zaidimas build() 
        {
            return new Zaidimas(this);
        }

        public Zaidimas buildRandom() 
        {
            int ma = RANDOM.nextInt(MODELIAI.length);        // markės indeksas  0..
            int mp = RANDOM.nextInt(PAVADINIMAI.length); 
            int mo = RANDOM.nextInt(MODELIAI[ma].length - 1) + 1;// modelio indeksas 1..    
            LinkedList<String> betkoks = new LinkedList();
            betkoks.add(MODELIAI[ma][mo]);
            return new Zaidimas(
                    MODELIAI[ma][0],
                    PAVADINIMAI[mp],
                    betkoks,
                    1997 + RANDOM.nextInt(20),// metai tarp 1997 ir 2009
                    2 + RANDOM.nextDouble() * 10,// kaina tarp 2 ir 200
                    1 + RANDOM.nextDouble() * 10);// ivertinimas tarp 0 ir 10
        }

        public Builder gamMetai(int gamMetai) {
            this.gamMetai = gamMetai;
            return this;
        }

        public Builder zanras(String markė) {
            this.zanras = zanras;
            return this;
        }
        
        public Builder papildomosSavybes(LinkedList<String> pS) {
            papildomosSavybes = pS;
            return this;
        }

        public Builder pavadinimas(String pavadinimas) {
            this.pavadinimas = pavadinimas;
            return this;
        }

        public Builder ivertinimas(double ivert) {
            ivertinimas = ivert;
            return this;
        }

        public Builder kaina(double kaina) {
            this.kaina = kaina;
            return this;
        }
    }
    
}
