package Application;

import Model.Entities.Department;
import Model.Entities.Seller;

import java.util.Date;

public class Program {
    public static void main(String[] args) {

        Department department = new Department(1, "Computers");
        Seller seller = new Seller(21, "Bob", "bob@gmail.com", new Date(), 3000.0, department);

        System.out.println(seller);
    }
}