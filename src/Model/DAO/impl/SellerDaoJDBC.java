package Model.DAO.impl;

import DB.Database;
import DB.DatabaseException;
import Model.DAO.DepartmentDAO;
import Model.DAO.SellerDAO;
import Model.Entities.Department;
import Model.Entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJDBC implements SellerDAO {

    private Connection connection;

    public SellerDaoJDBC(Connection connection) {
        this.connection = connection;
    }

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
    public Seller findById(Integer id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            statement = connection.prepareStatement("\n" +
                    "SELECT seller.*, department.Name as DepName\n" +
                    "FROM seller INNER JOIN department\n" +
                    "ON seller.DepartmentId = department.Id\n" +
                    "WHERE seller.Id = ?"
            );

            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if(resultSet.next()) {
                Department findedDepartment = new Department();
                findedDepartment.setId(resultSet.getInt("DepartmentId"));
                findedDepartment.setName(resultSet.getString("DepName"));

                Seller findedSeller = new Seller();
                findedSeller.setId(resultSet.getInt("Id"));
                findedSeller.setName(resultSet.getString("Name"));
                findedSeller.setBaseSalary(resultSet.getDouble("BaseSalary"));
                findedSeller.setBirthDate(resultSet.getDate("BirthDate"));
                findedSeller.setEmail(resultSet.getString("Email"));
                findedSeller.setDepartment(findedDepartment);

                return findedSeller;
            }

            return null;

        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            Database.closeStatement(statement);
            Database.closeResultSet(resultSet);
        }
    }

    @Override
    public List<Seller> findAll() {
        return List.of();
    }

}
