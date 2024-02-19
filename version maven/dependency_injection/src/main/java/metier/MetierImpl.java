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
