package pres;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import metier.IMetier;

public class PresentationXML {
  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
    IMetier metier = (IMetier) context.getBean("metier");
    System.out.println("resultat =  " + metier.calcul() + " F");
  }
}
