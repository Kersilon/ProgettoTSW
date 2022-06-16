import java.sql.SQLException;

import planetGaming.Indirizzo.IndirizzoBean;
import planetGaming.Indirizzo.IndirizzoDAO;

public class Test {
	IndirizzoBean indBean;
	IndirizzoDAO indDao;
	
	
	public void test(){
		indBean = null;
		
		try {
			indDao.doSave(indBean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
