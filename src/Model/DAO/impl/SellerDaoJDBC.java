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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                Department findedDepartment = instantiateDepartment(resultSet);
                return instantiateSeller(resultSet, findedDepartment);
            }

            return null;

        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            Database.closeStatement(statement);
            Database.closeResultSet(resultSet);
        }
    }

    private Department instantiateDepartment(ResultSet resultSet) throws SQLException {
        return new Department(resultSet.getInt("DepartmentId"), resultSet.getString("DepName"));
    }

    private Seller instantiateSeller(ResultSet resultSet, Department department) throws SQLException {
        return new Seller(resultSet.getInt("Id"), resultSet.getString("Name"), resultSet.getString("Email"), resultSet.getDate("BirthDate"), resultSet.getDouble("BaseSalary"), department);
    }


    @Override
    public List<Seller> findAll() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            statement = connection.prepareStatement("\n" +
                    "SELECT seller.*, department.Name as DepName\n" +
                    "FROM seller INNER JOIN department\n" +
                    "ON seller.DepartmentId = department.Id\n" +
                    "ORDER BY Name"
            );

            resultSet = statement.executeQuery();

            List<Seller> sellerList = new ArrayList<Seller>();

            while(resultSet.next()) {

                Department findedDepartment = instantiateDepartment(resultSet);
                sellerList.add(instantiateSeller(resultSet, findedDepartment));

            }

            return sellerList;

        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            Database.closeStatement(statement);
            Database.closeResultSet(resultSet);
        }

    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            statement = connection.prepareStatement(
                    "SELECT seller.*, department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE DepartmentId = ? "
                    + "ORDER BY Name"
            );

            statement.setInt(1, department.getId());

            resultSet = statement.executeQuery();

            List<Seller> sellersList = new ArrayList<Seller>();
            Map<Integer, Department> departmentMap = new HashMap<Integer, Department>();

            while(resultSet.next()) {

                if(departmentMap.get(resultSet.getInt("DepartmentId")) == null) {

                    Department findedDepartment = instantiateDepartment(resultSet);
                    departmentMap.put(findedDepartment.getId(), findedDepartment);

                }

            sellersList.add(instantiateSeller(resultSet, departmentMap.get(resultSet.getInt("DepartmentId"))));
            }
            return sellersList;

        } catch (SQLException sqlException) {
            throw new DatabaseException(sqlException.getMessage());
        } finally {
            Database.closeStatement(statement);
            Database.closeResultSet(resultSet);
        }

    }

}
