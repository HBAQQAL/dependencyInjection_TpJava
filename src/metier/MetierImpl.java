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
