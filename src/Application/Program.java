package Application;

import Model.DAO.DaoFactory;
import Model.DAO.DepartmentDAO;
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

        DepartmentDAO departmentDAO = DaoFactory.createDepartmentDAO();

        departmentDAO.update(new Department(16, "Computers"));

    }
}