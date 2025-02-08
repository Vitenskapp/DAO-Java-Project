package Model.DAO.impl;

import DB.Database;
import DB.DatabaseException;
import Model.DAO.DepartmentDAO;
import Model.Entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDAO {

    private Connection connection;

    public DepartmentDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Department department) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {

            statement = connection.prepareStatement(
                    "INSERT INTO department (Name) VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            statement.setString(1, department.getName());

            int rowsAffected = statement.executeUpdate();

            if(rowsAffected > 0) {
                resultSet = statement.getGeneratedKeys();
                if(resultSet.next()) {
                    int id = resultSet.getInt(1);
                    department.setId(id);
                }
            } else {
                throw new DatabaseException("Unexpected error! No rows affected");
            }

        } catch (SQLException sqlException) {
            throw new DatabaseException(sqlException.getMessage());
        }

    }

    @Override
    public void update(Department department) {
        PreparedStatement statement = null;

        try{

            statement = connection.prepareStatement(
                    "UPDATE department " +
                            "SET Name = ? " +
                            "WHERE Id = ?"
            );

            statement.setString(1, department.getName());
            statement.setInt(2, department.getId());

            statement.executeUpdate();

        } catch(SQLException sqlException) {
            throw new DatabaseException(sqlException.getMessage());
        } finally {
            Database.closeStatement(statement);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement(
                    "DELETE FROM department WHERE Id = ?"
            );
            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException sqlException) {
            throw new DatabaseException(sqlException.getMessage());
        } finally {
            Database.closeStatement(statement);
        }
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

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM department"
            );

            resultSet = preparedStatement.executeQuery();

            List<Department> departmentList = new ArrayList<>();

            while(resultSet.next()) {
                departmentList.add(instantiateDepartment(resultSet));
            }

            return departmentList;

        } catch (SQLException sqlException) {
            throw new DatabaseException(sqlException.getMessage());
        } finally {
            Database.closeResultSet(resultSet);
            Database.closeStatement(preparedStatement);
        }

    }

    public Department instantiateDepartment(ResultSet resultSet) throws SQLException {
        return new Department(resultSet.getInt("Id"), resultSet.getString("Name"));
    }

}
