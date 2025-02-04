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

        Seller seller = sellerDAO.findById(17);

        seller.setName("Leonardo");

        sellerDAO.update(seller);

    }
}