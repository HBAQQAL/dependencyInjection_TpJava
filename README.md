# Projet Scolaire

Ce projet implique la création d'interfaces et leurs implémentations, et la réalisation de l'injection de dépendances en utilisant différentes méthodes.

## Auteur

- BAQQAL Hamza
- Etudiant en 2ème année GLSID
- Email : hamzabaqqal2002@gmail.com

## Table des Matières

1. [Création de l'interface IDao](#création-de-linterface-idao)
2. [Implémentation de l'interface IDao](#implémentation-de-linterface-idao)
3. [Création de l'interface IMetier](#création-de-linterface-imetier)
4. [Implémentation de l'interface IMetier](#implémentation-de-linterface-imetier)
5. [Injection des dépendances](#injection-des-dépendances)
   - [Par instanciation statique](#par-instantiation-statique)
   - [Par instanciation dynamique](#par-instantiation-dynamique)
   - [En utilisant le Framework Spring](#en-utilisant-le-framework-spring)
     - [Version XML](#version-xml)
     - [Version annotations](#version-annotations)

## Création de l'interface IDao

```java
public interface IDao {
  double getData();
}
```

## Implémentation de l'interface IDao

```java
package dao;
public class DaoImpl implements IDao {
  @Override
  public double getData() {
    // connect to database and get temperature
    double temp = Math.random() * 40;
    return temp;
  }
}
```

## Création de l'interface IMetier

```java
package metier;

public interface IMetier {
  double calcul();

}
```

## Implémentation de l'interface IMetier

```java
package metier;

import dao.IDao;

public class MetierImpl implements IMetier {
  private IDao dao;

  @Override
  public double calcul() {
    double temp = dao.getData();
    // convert temperature to fahrenheit
    double result = temp * 1.8 + 32;
    return result;
  }

  public IDao getDao() {
    return dao;
  }

  public void setDao(IDao dao) {
    this.dao = dao;
  }

}
```

## Injection des dépendances

### Par instanciation statique

```java
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
```

### Par instanciation dynamique

```java
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
```

### En utilisant le Framework Spring

#### Version XML

--- fichier de configuration XML ---

```xml
<?xml version="1.0" encoding="UTF-8"?>

<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="dao" class="dao.DaoImpl"></bean>
    <bean id="metier" class="metier.MetierImpl">
        <property name="dao" ref="dao"></property>
    </bean>

</beans>
```

--- presentation ---

```java
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
```

#### Version annotations

--- modification de la classe MetierImpl ---

```java
package metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dao.IDao;

@Component
public class MetierImpl implements IMetier {

  @Autowired
  private IDao dao;

  @Override
  public double calcul() {
    double temp = dao.getData();
    // convert temperature to fahrenheit
    double result = temp * 1.8 + 32;
    return result;
  }

  public IDao getDao() {
    return dao;
  }

  public void setDao(IDao dao) {
    this.dao = dao;
  }

}
```

--- presentation ---

```java
package pres;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import metier.IMetier;

public class PresentationAnnotation {
  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext("dao", "metier");

    IMetier metier = (IMetier) context.getBean("metier");
    System.out.println("resultat =  " + metier.calcul() + " F");
  }

}
```
