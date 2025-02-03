package Application;

import Model.DAO.DaoFactory;
import Model.DAO.SellerDAO;
import Model.Entities.Department;
import Model.Entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        SellerDAO sellerDAO = DaoFactory.createSellerDAO();
        SellerDAO sellerDAO1 = DaoFactory.createSellerDAO();

        List<Seller> sellersList = sellerDAO.findByDepartment(new Department(2, "Eletronics"));
        List<Seller> allSellersList = sellerDAO1.findAll();

        for(Seller sel : allSellersList) {
            System.out.println(sel);
        }

    }
}