package Model.DAO.impl;

import Model.DAO.SellerDAO;
import Model.Entities.Department;
import Model.Entities.Seller;

import java.util.List;

public class SellerDaoJDBC implements SellerDAO {

    @Override
    public void insert(Seller seller) {

    }

    @Override
    public void update(Seller seller) {

    }

    @Override
    public void deletById(Integer id) {

    }

    @Override
    public Department findById(Integer id) {
        return null;
    }

    @Override
    public List<Department> findAll() {
        return List.of();
    }

}
