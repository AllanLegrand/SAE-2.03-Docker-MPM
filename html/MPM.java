import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

public class MPM 
{
    private ArrayList<Sommet> lstSommet;
    private ArrayList<ArrayList<Sommet>> lstNiveaux;

    public MPM() 
    {
        this.lire();
        this.genererMPM();
        this.ecrire();
    }

    public void lire() 
    {
        lstSommet = new ArrayList<Sommet>();

        try 
        {

            Scanner sc = new Scanner(new FileInputStream("./tableau.csv"));

            sc.nextLine();

            while (sc.hasNextLine())
                lstSommet.add(creerSommet(sc.nextLine()));

            sc.close();

        } catch (Exception e) {

        }
    }

    public Sommet creerSommet(String s) 
    {
        ArrayList<String> tabS = new ArrayList<String>();

        String colonne = "";
        for (int cpt = 0; cpt < s.length(); cpt++) 
        {
            if (s.charAt(cpt) != ',') {
                colonne += s.charAt(cpt);
                if (cpt + 1 == s.length())
                    tabS.add(colonne);
            } 
            else 
            {
                tabS.add(colonne);
                colonne = "";
            }
        }

        ArrayList<Sommet> lstAntecedant = new ArrayList<Sommet>();
        for (int cpt = 2; cpt < tabS.size(); cpt++)
            lstAntecedant.add(chercherSommet(tabS.get(cpt)));

        return new Sommet(tabS.get(0), Integer.parseInt(tabS.get(1)), lstAntecedant);
    }

    public void ecrire() {
        try 
        {
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("entree.dot"), "UTF8"));

            pw.println("digraph mygraph{");
            pw.println("\tnode [shape=plaintext]");

            for (Sommet s : this.lstSommet)
                for (Sommet suivant : s.getLstSuivant()) 
                    pw.println("\"" + s + "\" -> \"" + suivant + "\"");

            pw.println("}");

            pw.close();
        } catch (Exception e) {

        }
    }

    public Sommet chercherSommet(String nom) 
    {
        for (Sommet s : this.lstSommet)
            if (nom.equals(s.getNom()))
                return s;

        return null;
    }

    private void genererMPM() 
    {
        this.lstSommet.add(new Sommet("fin", 0, this.lstSommet));

        // Generation de la liste des niveaux
        this.lstNiveaux = new ArrayList<ArrayList<Sommet>>();

        while (!listeComplete()) 
        {
            this.lstNiveaux.add(new ArrayList<Sommet>());

            for (Sommet s : this.lstSommet)
                if (ajoutDansLstNiv(s))
                    this.lstNiveaux.get(this.lstNiveaux.size() - 1).add(s);
        }

        // Generation des dates au plus tot
        for (ArrayList<Sommet> lstS : this.lstNiveaux)
            for (Sommet s : lstS)
                for (Sommet antecedant : s.getLstAntecedant())
                    s.setDateTot(antecedant.getDateTot() + antecedant.getDuree());

        // Generation des dates au plus tard
        for (Sommet s : this.lstSommet)
            s.setDateTard(this.lstSommet.get(this.lstSommet.size() - 1).getDateTot());

        for (int cpt = this.lstNiveaux.size() - 2; cpt >= 0; cpt--)
            for (Sommet s : this.lstNiveaux.get(cpt))
                for (Sommet suivant : s.getLstSuivant())
                    s.setDateTard(suivant.getDateTard() - s.getDuree());
    }

    private boolean listeComplete() 
    {
        int somme = 0;
        for (ArrayList<Sommet> lstS : this.lstNiveaux)
            somme += lstS.size();

        return somme == this.lstSommet.size();
    }

    private boolean ajoutDansLstNiv(Sommet sommet) 
    {
        for (Sommet antecedant : sommet.getLstAntecedant())
            if (!estDansLstNiv(antecedant, 1))
                return false;

        return !estDansLstNiv(sommet, 0);
    }

    private boolean estDansLstNiv(Sommet sommet, int niveau) 
    {
        for (int cpt = 0; cpt < this.lstNiveaux.size() - niveau; cpt++)
            for (Sommet s : this.lstNiveaux.get(cpt))
                if (sommet.equals(s))
                    return true;

        return false;
    }

    public static void main(String[] args) 
    {
        new MPM();
    }
}
