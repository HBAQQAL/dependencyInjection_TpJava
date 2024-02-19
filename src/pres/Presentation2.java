package pres;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;
import dao.IDao;
import metier.IMetier;

public class Presentation2 {
  public static void main(String[] args)
      throws Exception {
    Scanner scanner = new Scanner(new File("src\\config.txt"));
    String daoClassName = scanner.nextLine();
    Class cDao = Class.forName(daoClassName);
    IDao dao = (IDao) cDao.getConstructor().newInstance();

    String metierClassName = scanner.nextLine();
    Class cMetier = Class.forName(metierClassName);
    IMetier metier = (IMetier) cMetier.getConstructor().newInstance();
    Method m = cMetier.getMethod("setDao", IDao.class);
    m.invoke(metier, dao);
    System.out.println("le temperature est : " + String.format("%.1f", metier.calcul()) + " F");
  }
}
