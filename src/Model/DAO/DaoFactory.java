package Model.DAO;

import DB.Database;
import Model.DAO.impl.DepartmentDaoJDBC;
import Model.DAO.impl.SellerDaoJDBC;

public class DaoFactory {

    public static SellerDAO createSellerDAO() {
        return new SellerDaoJDBC(Database.getConnection());
    }
    public static DepartmentDAO createDepartmentDAO() { return new DepartmentDaoJDBC(Database.getConnection()); }
}
