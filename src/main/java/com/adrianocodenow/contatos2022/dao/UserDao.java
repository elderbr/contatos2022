package com.adrianocodenow.contatos2022.dao;

import com.adrianocodenow.contatos2022.exceptions.UserException;
import com.adrianocodenow.contatos2022.factory.ConnectionFactory;
import com.adrianocodenow.contatos2022.interfaces.IUser;
import java.sql.SQLException;
import java.util.Objects;

/**
 *
 * @author ElderBR
 */
public class UserDao extends ConnectionFactory {

    private static UserDao instance;
    private final String TABLE_NAME = "tb_user";
    private final String ID_USER = "id_user";
    private final String NAME_USER = "name_user";
    private final String LAST_NAME_USER = "last_name_user";
    private final String STATUS_USER = "status_user";
    private final String DATE_CREATION_USER = "date_created_user";
    private final String DATE_UPDATED_USER = "date_updated_user";

    private UserDao() {
        try {
            sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                    + ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + NAME_USER + " TEXT NOT NULL, "
                    + LAST_NAME_USER + " TEXT NOT NULL, "
                    + STATUS_USER + " BOOLEAN DEFAULT TRUE, "
                    + DATE_CREATION_USER + " TEXT NOT NULL, "
                    + DATE_UPDATED_USER + " TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP"
                    + ")";
            exec(sql);
        } catch (SQLException e) {
            throw new UserException("Erro ao criar a tabela " + TABLE_NAME, e);
        }
    }

    public static UserDao getInstance() {
        if (Objects.isNull(instance)) {
            instance = new UserDao();
        }
        return instance;
    }
    
    public IUser insert(IUser user){
        try {
            sql = "INSERT INTO "+ TABLE_NAME+" ("
                    + NAME_USER+", "
                    + LAST_NAME_USER+", "
                    + STATUS_USER+", "
                    + DATE_CREATION_USER+", "
                    + DATE_UPDATED_USER+""
                    + ") VALUES (?,?,?,?,?);";
            smt = preparedInsert(sql);            
            smt.setString(1, user.getNameUser());
            smt.setString(2, user.getLastNameUser());
            smt.setBoolean(3, true);
            smt.setString(4, user.toDateCreation());
            smt.setString(5, user.toDateUpdated());            
            smt.executeUpdate();
            conn.commit();
            rs = smt.getGeneratedKeys();
            if(rs.next()){
                user.setIdUser(rs.getInt(1));
                return user;
            }
            throw new UserException("Erro ao adicionar novo usuário!");
        } catch (Exception e) {
            throw new UserException("Erro ao adicionar novo usuário!", e);
        }
    }

}
