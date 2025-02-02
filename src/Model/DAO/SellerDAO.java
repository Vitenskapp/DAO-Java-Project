package Model.DAO;

import Model.Entities.Department;
import Model.Entities.Seller;

import java.util.List;

public interface SellerDAO {

    void insert(Seller seller);
    void update(Seller seller);
    void deletById(Integer id);
    Department findById(Integer id);
    List<Department> findAll();

}
