package com.adrianocodenow.contatos2022.dao;

import static com.adrianocodenow.contatos2022.dao.TelefoneDao.ID_PHONE;
import static com.adrianocodenow.contatos2022.dao.TelefoneDao.ID_PHONE_TYPE;
import static com.adrianocodenow.contatos2022.dao.TelefoneDao.NUMBER_PHONE;
import com.adrianocodenow.contatos2022.exceptions.ConexaoException;
import com.adrianocodenow.contatos2022.exceptions.PhoneException;
import com.adrianocodenow.contatos2022.factory.ConnectionFactory;
import static com.adrianocodenow.contatos2022.factory.ConnectionFactory.conn;
import static com.adrianocodenow.contatos2022.factory.ConnectionFactory.connected;
import static com.adrianocodenow.contatos2022.factory.ConnectionFactory.desconect;
import static com.adrianocodenow.contatos2022.factory.ConnectionFactory.exec;
import static com.adrianocodenow.contatos2022.factory.ConnectionFactory.prepared;
import static com.adrianocodenow.contatos2022.factory.ConnectionFactory.preparedInsert;
import static com.adrianocodenow.contatos2022.factory.ConnectionFactory.rs;
import static com.adrianocodenow.contatos2022.factory.ConnectionFactory.smt;
import static com.adrianocodenow.contatos2022.factory.ConnectionFactory.sql;
import com.adrianocodenow.contatos2022.interfaces.IPhone;
import com.adrianocodenow.contatos2022.model.Phone;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 *
 * @author ElderBR
 */
public class PhoneDao extends ConnectionFactory{
    
    private static PhoneDao instance;
    private static final String TABLE_NAME = "tb_phone";
    public static final String ID_PHONE = "id_phone";
    public static final String NUMBER_PHONE = "name_phone";
    public static final String ID_PHONE_TYPE = "id_phone_type";
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + ID_PHONE + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NUMBER_PHONE + " TEXT NOT NULL UNIQUE, "
            + ID_PHONE_TYPE + " INTEGER NOT NULL "
            + "CONSTRAINT fk_phone_type REFERENCES tb_phone_type (" + ID_PHONE_TYPE + ") ON DELETE CASCADE"
            + ");";
    private static final String[] COLUMNS_SQL = {ID_PHONE, NUMBER_PHONE, ID_PHONE_TYPE};
    
    private PhoneDao() {
        try {
            sql = CREATE_TABLE;
            exec(sql);
            conn.commit();
        } catch (SQLException ex) {
            throw new ConexaoException("Erro ao criar a tabela phone!", ex);
        } finally {
            desconect();
        }
    }
    
    public static PhoneDao getInstance() {
        if (Objects.isNull(instance)) {
            instance = new PhoneDao();
        }
        return instance;
    }
    
    public int insert(IPhone phone) {
        try {
            sql = "INSERT INTO " + TABLE_NAME + " (" + NUMBER_PHONE + ", " + ID_PHONE_TYPE + ") VALUES (?, ?);";
            connected();
            smt = preparedInsert(sql);
            smt.setString(1, phone.getNumberPhone());
            smt.setInt(2, phone.getPhoneType().getIdPhoneType());
            smt.executeUpdate();
            conn.commit();
            ResultSet rs = smt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
            throw new PhoneException("Erro ao adicionar novo telefone!");
        } catch (SQLException e) {
            throw new PhoneException("Erro ao adicionar novo telefone!", e);
        } finally {
            desconect();
        }
    }

    public IPhone findById(int id) {
        sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_PHONE + " = ?";
        try {
            prepared(sql);
            smt.setInt(1, id);
            rs = smt.executeQuery();
            while (rs.next()) {
                Phone phone = new Phone();
                phone.setIdPhone(rs.getInt(ID_PHONE));
                phone.setNumberPhone(rs.getString(NUMBER_PHONE));
                return phone;
            }
        } catch (Exception e) {
            throw new PhoneException("Erro ao buscar o telefone!", e);
        } finally {
            desconect();
        }
        throw new PhoneException("Erro ao buscar o telefone com o ID " + id);
    }

    public IPhone findByNumber(String number) {
        sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + NUMBER_PHONE + " = ?";
        try {
            prepared(sql);
            smt.setString(1, number);
            rs = smt.executeQuery();
            while (rs.next()) {
                Phone phone = new Phone();
                phone.setIdPhone(rs.getInt(ID_PHONE));
                phone.setNumberPhone(rs.getString(NUMBER_PHONE));
                return phone;
            }
        } catch (Exception e) {
            throw new PhoneException("Erro ao buscar o telefone pelo o número!", e);
        } finally {
            desconect();
        }
        throw new PhoneException("O número " + number + " do telefone não existe!");
    }
}
