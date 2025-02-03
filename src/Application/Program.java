package Application;

import Model.DAO.DaoFactory;
import Model.DAO.SellerDAO;
import Model.Entities.Department;
import Model.Entities.Seller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        SellerDAO sellerDAO = DaoFactory.createSellerDAO();

        sellerDAO.insert(new Seller("Leonardo", "leozinho123@gmail.com", new Date("21/11/2005"), 5000.0, new Department(2, "Eletronics")));

    }
}