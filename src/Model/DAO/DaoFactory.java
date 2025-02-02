package Model.DAO;

import Model.DAO.impl.SellerDaoJDBC;

public class DaoFactory {

    public static SellerDAO createSellerDAO() {
        return new SellerDaoJDBC();
    }

    //public static DepartmentDAO createDepartmentDAO() {
        //return new DepartmentDAO();
    //}

}
