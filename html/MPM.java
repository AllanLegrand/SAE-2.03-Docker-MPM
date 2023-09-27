import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
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
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("MPM.svg"), "UTF8"));

            int nbMaxSmt = max();

            pw.println("<svg width=\""+(this.lstNiveaux.size()*100+50)+"\" height=\""+(nbMaxSmt*100+50)+"\">");

            for(int cptNiv = 0; cptNiv < this.lstNiveaux.size(); cptNiv++)
            {
                for(int cptSmt = 0; cptSmt < this.lstNiveaux.get(cptNiv).size(); cptSmt++)
                {
                    pw.println("<rect x=\""+(50+cptNiv*100)+"\" y=\""+((cptSmt+1)*(nbMaxSmt*100+50)/(this.lstNiveaux.get(cptNiv).size()+1))+"\" width=\"50\" height=\"50\" fill=\"white\" stroke-width=\"4\" stroke=\"black\" />");
                    pw.println("<rect x=\""+(50+cptNiv*100)+"\" y=\""+(25+(cptSmt+1)*(nbMaxSmt*100+50)/(this.lstNiveaux.get(cptNiv).size()+1))+"\" width=\"50\" height=\"25\" fill=\"white\" stroke-width=\"4\" stroke=\"black\" />");
                    pw.println("<rect x=\""+(75+cptNiv*100)+"\" y=\""+(25+(cptSmt+1)*(nbMaxSmt*100+50)/(this.lstNiveaux.get(cptNiv).size()+1))+"\" width=\"25\" height=\"25\" fill=\"white\" stroke-width=\"4\" stroke=\"black\" />");
                    pw.println("<text x=\""+(50+cptNiv*100+20)+"\" y=\""+((cptSmt+1)*(nbMaxSmt*100+50)/(this.lstNiveaux.get(cptNiv).size()+1)+15)+"\">"+this.lstNiveaux.get(cptNiv).get(cptSmt).getNom()+"</text>");
                    pw.println("<text x=\""+(50+cptNiv*100+10)+"\" y=\""+((cptSmt+1)*(nbMaxSmt*100+50)/(this.lstNiveaux.get(cptNiv).size()+1)+40)+"\">"+this.lstNiveaux.get(cptNiv).get(cptSmt).getDateTot()+"</text>");
                    pw.println("<text x=\""+(75+cptNiv*100+10)+"\" y=\""+((cptSmt+1)*(nbMaxSmt*100+50)/(this.lstNiveaux.get(cptNiv).size()+1)+40)+"\">"+this.lstNiveaux.get(cptNiv).get(cptSmt).getDateTard()+"</text>");
                    for(Sommet s : this.lstNiveaux.get(cptNiv).get(cptSmt).getLstSuivant())
                        for(int cptNivAnt = 0; cptNivAnt < this.lstNiveaux.size(); cptNivAnt++)
                            for(int cptSmtAnt = 0; cptSmtAnt < this.lstNiveaux.get(cptNivAnt).size(); cptSmtAnt++)
                                if(s.equals(this.lstNiveaux.get(cptNivAnt).get(cptSmtAnt)))
                                {
                                    pw.println("<text x=\""+((100+cptNiv*100)+(50+cptNivAnt*100))/2+"\" y=\""+((((cptSmt+1)*(nbMaxSmt*100+50)/(this.lstNiveaux.get(cptNiv).size()+1)+25)+((cptSmtAnt+1)*(nbMaxSmt*100+50)/(this.lstNiveaux.get(cptNivAnt).size()+1)+25))/2-10)+"\">"+this.lstNiveaux.get(cptNiv).get(cptSmt).getDuree()+"</text>");
                                    pw.println("<line x1=\""+(100+cptNiv*100)+"\" y1=\""+((cptSmt+1)*(nbMaxSmt*100+50)/(this.lstNiveaux.get(cptNiv).size()+1)+25)+"\" x2=\""+(50+cptNivAnt*100)+"\" y2=\""+((cptSmtAnt+1)*(nbMaxSmt*100+50)/(this.lstNiveaux.get(cptNivAnt).size()+1)+25)+"\" stroke=\"black\" stroke-width=\"2\" />");
                                }
                }
            }

            pw.println("</svg>");

            pw.close();
        } catch (Exception e) {

        }
    }

    public int max()
    {
        int max = 0;
        for (ArrayList<Sommet> lstSmt : this.lstNiveaux)
            if(max < lstSmt.size()) max = lstSmt.size();

        return max;
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
        ArrayList<Sommet> lstSansSuiv =  new ArrayList<Sommet>();
        for(Sommet s : this.lstSommet)
            if(s.getLstSuivant().size() == 0)
                lstSansSuiv.add(s);
        this.lstSommet.add(new Sommet("fin", 0, lstSansSuiv));

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

        this.lstNiveaux.add(0, new ArrayList<Sommet>(Arrays.asList(new Sommet("debut",0,new ArrayList<Sommet>()))));
        this.lstNiveaux.get(0).get(0).setDateTard(0);
        for(Sommet s : this.lstNiveaux.get(1))
        {
            this.lstNiveaux.get(0).get(0).addSuivant(s);
            s.getLstAntecedant().add(this.lstNiveaux.get(0).get(0));
        }
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
