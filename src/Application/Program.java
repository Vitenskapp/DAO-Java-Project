package Application;

import Model.DAO.DaoFactory;
import Model.DAO.SellerDAO;
import Model.Entities.Department;
import Model.Entities.Seller;

import java.util.Date;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        SellerDAO sellerDAO = DaoFactory.createSellerDAO();
        System.out.println("[ Testing method: Seller findById ]");
        System.out.println("Insert a id: ");
        Integer id = scanner.nextInt();

        Seller seller = sellerDAO.findById(id);

        System.out.println(seller);
    }
}