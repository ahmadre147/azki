package com.azki.repository;

import com.azki.driver.JDBCHelper;
import com.azki.model.HealthInsurance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HealthInsuranceDao implements Repository<HealthInsurance, Integer>{
    public static final String INSERT_SQL_QUERY     = "INSERT INTO [HealthInsurance]([id],[visitAndDrug],[companies.id],[optic],[hospitalization],[EEG],[ambulance],[dental],[hearingAid],[surgery],[discount],[price],[fracture],[childBirth],[infertility],[sonography],[labServices],[compare],[ordered],[numOfPeople],[type],[peopleHealthInsurance.id],[user.id]) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public static final String UPDATE_SQL_QUERY     = "UPDATE [HealthInsurance] SET [id]=?,[visitAndDrug]=?,[companies.id]=?,[optic]=?,[hospitalization]=?,[EEG]=?,[ambulance]=?,[dental]=?,[hearingAid]=?,[surgery]=?,[discount]=?,[price]=?,[fracture]=?,[childBirth]=?,[infertility]=?,[sonography]=?,[labServices]=?,[compare]=?,[ordered]=?,[numOfPeople]=?,[type]=?,[peopleHealthInsurance.id]=?,[user.id]=? WHERE id=?";
    public static final String SELECT_SQL_QUERY     = "SELECT * FROM [HealthInsurance] WHERE [id]=?";
    public static final String SELECT_ALL_SQL_QUERY = "SELECT * FROM [HealthInsurance]";
    public static final String DELETE_SQL_QUERY     = "DELETE FROM [HealthInsurance] WHERE [id]=?";
    public final Connection con;

    public HealthInsuranceDao() throws SQLException {
        con = JDBCHelper.getConnection();
    }

    @Override
    public HealthInsurance findById(Integer id) {
        HealthInsurance healthInsurance = new HealthInsurance();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            if (con == null) {
                System.out.println("Error getting the connection. Please check if the DB server is running");
                return healthInsurance;
            }

            ps = con.prepareStatement(SELECT_SQL_QUERY);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (!rs.isBeforeFirst()){
                System.out.println( "No healthInsurance by id = " + id + " is found!");
                return null;
            }

            System.out.println( "retrieveHealthInsurance => " + ps.toString() );

            while (rs.next()){

                healthInsurance.setId(rs.getInt("id"));
                healthInsurance.setVisitAndDrug(rs.getInt("visitAndDrug"));
                healthInsurance.setCompanies_id(rs.getInt("[companies.id]"));
                healthInsurance.setOptic(rs.getInt("optic"));
                healthInsurance.setHospitalization(rs.getInt("hospitalization"));
                healthInsurance.setEeg(rs.getInt("EEG"));
                healthInsurance.setAmbulance(rs.getInt("ambulance"));
                healthInsurance.setHearingAid(rs.getInt("dental"));
                healthInsurance.setSurgery(rs.getInt("hearingAid"));
                healthInsurance.setDiscount(rs.getInt("surgery"));
                healthInsurance.setPrice(rs.getInt("discount"));
                healthInsurance.setFracture(rs.getInt("fracture"));
                healthInsurance.setChildBirth(rs.getInt("childBirth"));
                healthInsurance.setInfertility(rs.getInt("infertility"));
                healthInsurance.setSonography(rs.getInt("sonography"));
                healthInsurance.setLabServices(rs.getInt("labServices"));
                healthInsurance.setCompare(rs.getInt("compare"));
                healthInsurance.setOrdered(rs.getInt("ordered"));
                healthInsurance.setNumOfPeople(rs.getInt("numOfPeople"));
                healthInsurance.setType(rs.getString("type"));
                healthInsurance.setPeopleHealthInsurance_id(rs.getInt("[peopleHealthInsurance.id]"));
                healthInsurance.setUser_id(rs.getInt("[user.id]"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCHelper.closePreparedStatement(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return healthInsurance;
    }

    @Override
    public List<HealthInsurance> findByIDs(Collection<Integer> ids) {
        List<HealthInsurance> healthInsurances = new ArrayList<>();

        for (Integer id : ids){
            healthInsurances.add(findById(id));
        }

        return healthInsurances;
    }

    @Override
    public List<HealthInsurance> findAll() {
        List<HealthInsurance> healthInsurances = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            if (con == null) {
                System.out.println("Error getting the connection. Please check if the DB server is running");
                return healthInsurances;
            }

            ps = con.prepareStatement(SELECT_ALL_SQL_QUERY);
            rs = ps.executeQuery();

            System.out.println("retrieveHealthInsurances => " + ps.toString());

            while (rs.next()) {
                HealthInsurance healthInsurance = new HealthInsurance();

                healthInsurance.setId(rs.getInt("id"));
                healthInsurance.setVisitAndDrug(rs.getInt("visitAndDrug"));
                healthInsurance.setCompanies_id(rs.getInt("[companies.id]"));
                healthInsurance.setOptic(rs.getInt("optic"));
                healthInsurance.setHospitalization(rs.getInt("hospitalization"));
                healthInsurance.setEeg(rs.getInt("EEG"));
                healthInsurance.setAmbulance(rs.getInt("ambulance"));
                healthInsurance.setHearingAid(rs.getInt("dental"));
                healthInsurance.setSurgery(rs.getInt("hearingAid"));
                healthInsurance.setDiscount(rs.getInt("surgery"));
                healthInsurance.setPrice(rs.getInt("discount"));
                healthInsurance.setFracture(rs.getInt("fracture"));
                healthInsurance.setChildBirth(rs.getInt("childBirth"));
                healthInsurance.setInfertility(rs.getInt("infertility"));
                healthInsurance.setSonography(rs.getInt("sonography"));
                healthInsurance.setLabServices(rs.getInt("labServices"));
                healthInsurance.setCompare(rs.getInt("compare"));
                healthInsurance.setOrdered(rs.getInt("ordered"));
                healthInsurance.setNumOfPeople(rs.getInt("numOfPeople"));
                healthInsurance.setType(rs.getString("type"));
                healthInsurance.setPeopleHealthInsurance_id(rs.getInt("[peopleHealthInsurance.id]"));
                healthInsurance.setUser_id(rs.getInt("[user.id]"));
                healthInsurances.add(healthInsurance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCHelper.closePreparedStatement(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return healthInsurances;
    }

    @Override
    public Boolean deleteByID(Integer id) {
        if (findById(id) == null){
            return false;
        }

        PreparedStatement ps = null;

        try {
            if (con == null) {
                System.out.println("Error getting the connection. Please check if the DB server is running");
                return false;
            }

            ps = con.prepareStatement(DELETE_SQL_QUERY);
            ps.setInt(1, id);
            ps.execute();

            System.out.println( "deleteHealthInsurance => " + ps.toString() );
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCHelper.closePreparedStatement(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    @Override
    public Boolean deleteByIDs(Collection<Integer> ids) {
        for (Integer id: ids){
            if (!deleteByID(id)){
                return false;
            }
        }

        return true;
    }

    @Override
    public HealthInsurance save(HealthInsurance E) {
        HealthInsurance healthInsurance = findById(E.getId());

        PreparedStatement ps = null;

        if (healthInsurance != null) {
            try {
                if (con == null) {
                    System.out.println("Error getting the connection. Please check if the DB server is running");
                    return null;
                }
                ps = con.prepareStatement(UPDATE_SQL_QUERY);

                ps.setInt(1, E.getVisitAndDrug());
                ps.setInt(2, E.getCompanies_id());
                ps.setInt(3, E.getOptic());
                ps.setInt(4, E.getHospitalization());
                ps.setInt(5, E.getEeg());
                ps.setInt(6, E.getAmbulance());
                ps.setInt(7, E.getDental());
                ps.setInt(8, E.getHearingAid());
                ps.setInt(9, E.getSurgery());
                ps.setFloat(10, E.getDiscount());
                ps.setInt(11, E.getFracture());
                ps.setInt(12, E.getChildBirth());
                ps.setInt(13, E.getInfertility());
                ps.setInt(14, E.getSonography());
                ps.setInt(15, E.getLabServices());
                ps.setInt(16, E.getCompare());
                ps.setInt(17, E.getOrdered());
                ps.setInt(18, E.getNumOfPeople());
                ps.setString(19, E.getType());
                ps.setInt(20, E.getPeopleHealthInsurance_id());
                ps.setInt(21, E.getUser_id());
                ps.setInt(22, E.getId());
                ps.execute();
            }
            catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    JDBCHelper.closePreparedStatement(ps);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                if (con == null) {
                    System.out.println("Error getting the connection. Please check if the DB server is running");
                    return null;
                }
                ps = con.prepareStatement(INSERT_SQL_QUERY);

                ps.setInt(1, E.getId());
                ps.setInt(2, E.getVisitAndDrug());
                ps.setInt(3, E.getCompanies_id());
                ps.setInt(4, E.getOptic());
                ps.setInt(5, E.getHospitalization());
                ps.setInt(6, E.getEeg());
                ps.setInt(7, E.getAmbulance());
                ps.setInt(8, E.getDental());
                ps.setInt(9, E.getHearingAid());
                ps.setInt(10, E.getSurgery());
                ps.setFloat(11, E.getDiscount());
                ps.setInt(12, E.getFracture());
                ps.setInt(13, E.getChildBirth());
                ps.setInt(14, E.getInfertility());
                ps.setInt(15, E.getSonography());
                ps.setInt(16, E.getLabServices());
                ps.setInt(17, E.getCompare());
                ps.setInt(18, E.getOrdered());
                ps.setInt(19, E.getNumOfPeople());
                ps.setString(20, E.getType());
                ps.setInt(21, E.getPeopleHealthInsurance_id());
                ps.setInt(22, E.getUser_id());

                ps.execute();
            }
            catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    JDBCHelper.closePreparedStatement(ps);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return findById(E.getId());
    }
}
