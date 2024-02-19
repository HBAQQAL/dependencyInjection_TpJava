package pres;

import dao.DaoImpl;
import metier.MetierImpl;

public class Presentation {
  public static void main(String[] args) {
    // dependency injection using static instanciation
    MetierImpl metier = new MetierImpl();
    metier.setDao(new DaoImpl());
    System.out.println("resultat =  " + metier.calcul() + " F");
  }

}
