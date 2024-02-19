package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class DaoImpl implements IDao {

  @Override
  public double getData() {
    // connect to database and get temperature
    double temp = Math.random() * 40;
    return temp;
  }

}
