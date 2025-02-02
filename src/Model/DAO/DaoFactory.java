package Model.DAO;

import DB.Database;
import Model.DAO.impl.SellerDaoJDBC;

public class DaoFactory {

    public static SellerDAO createSellerDAO() {
        return new SellerDaoJDBC(Database.getConnection());
    }

}
