import java.util.ArrayList;

public class Sommet 
{
    private String nom;
    private ArrayList<Sommet> lstSuivant;
    private ArrayList<Sommet> lstAntecedant;
    private int dateTot;
    private int dateTard;
    private int duree;

    public Sommet(String nom, int duree, ArrayList<Sommet> lstAntecedant) 
    {
        this.nom = nom;
        this.lstSuivant = new ArrayList<Sommet>();
        this.lstAntecedant = (ArrayList) lstAntecedant.clone();
        this.dateTot = 0;
        this.dateTard = Integer.MAX_VALUE;
        this.duree = duree;

        for (Sommet s : this.lstAntecedant)
            s.addSuivant(this);
    }

    public void setDateTot(int date) 
    {
        if (this.dateTot < date)
            this.dateTot = date;
    }

    public void setDateTard(int date) 
    {
        if (this.dateTard > date)
            this.dateTard = date;
    }

    public ArrayList<Sommet> getLstAntecedant() 
    {
        return this.lstAntecedant;
    }

    public ArrayList<Sommet> getLstSuivant() 
    {
        return this.lstSuivant;
    }

    public int getDateTot() 
    {
        return this.dateTot;
    }

    public int getDateTard() 
    {
        return this.dateTard;
    }

    public int getDuree() 
    {
        return this.duree;
    }

    public String getNom() 
    {
        return this.nom;
    }

    public void addSuivant(Sommet sommet) 
    {
        this.lstSuivant.add(sommet);
    }

    public String toString() 
    {
        return this.nom + " : " + this.dateTot + " - " + this.dateTard;
    }
}
