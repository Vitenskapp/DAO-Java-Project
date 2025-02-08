package Model.DAO.impl;

import DB.Database;
import DB.DatabaseException;
import Model.DAO.DepartmentDAO;
import Model.Entities.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDAO {

    private Connection connection;

    public DepartmentDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Department department) {

    }

    @Override
    public void update(Department department) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Department findById(Integer id) {

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            statement = connection.prepareStatement(
                    "SELECT * FROM department WHERE Id = ?"
            );

            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            Department findedDepartment = new Department();

            if(resultSet.next()) {
                findedDepartment = new Department(resultSet.getInt("Id"), resultSet.getString("Name"));
            }

            return findedDepartment;

        } catch(SQLException sqlException) {
            throw new DatabaseException(sqlException.getMessage());
        } finally {
            Database.closeStatement(statement);
            Database.closeResultSet(resultSet);
        }

    }

    @Override
    public List<Department> findAll() {

        return List.of();
    }

}
