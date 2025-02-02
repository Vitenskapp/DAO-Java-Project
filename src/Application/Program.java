package Application;

import Model.DAO.DaoFactory;
import Model.DAO.SellerDAO;
import Model.Entities.Department;
import Model.Entities.Seller;

import java.util.Date;

public class Program {
    public static void main(String[] args) {

        Integer id = 3;

        SellerDAO sellerDAO = DaoFactory.createSellerDAO();

        Seller seller = sellerDAO.findById(id);

        System.out.println(seller);
    }
}