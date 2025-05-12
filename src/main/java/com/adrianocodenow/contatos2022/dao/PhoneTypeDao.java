package com.adrianocodenow.contatos2022.dao;

import com.adrianocodenow.contatos2022.exceptions.PhoneException;
import com.adrianocodenow.contatos2022.factory.ConnectionFactory;
import com.adrianocodenow.contatos2022.interfaces.IPhoneType;
import com.adrianocodenow.contatos2022.model.PhoneType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author ElderBR
 */
public class PhoneTypeDao extends ConnectionFactory {
    
    private static PhoneTypeDao instance;
    private static String TABLE_NAME = "tb_phone_type";
    public final static String ID_PHONE_TYPE = "id_phone_type";
    public final static String NAME_PHONE_TYPE = "name_phone_type";

    private PhoneTypeDao() {
        try{
            sql = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("
                    + ID_PHONE_TYPE+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + NAME_PHONE_TYPE+" TEXT NOT NULL UNIQUE"
                    + ")";
            exec(sql);
            conn.commit();
        }catch(Exception e){
            throw new PhoneException("Erro ao criar a tabela tb_phone_type");
        }finally{
            desconect();
        }
    }
    
    public static PhoneTypeDao getInstance(){
        if(Objects.isNull(instance)){
            instance = new PhoneTypeDao();
        }
        return instance;
    }
    
    public int insert(IPhoneType phoneType){
        try {
            sql = "INSERT INTO "+ TABLE_NAME +" ("+ NAME_PHONE_TYPE +") VALUES (?);";
            preparedInsert(sql);
            smt.setString(1, phoneType.getNamePhoneType());
            smt.executeUpdate();
            conn.commit();
            rs = smt.getGeneratedKeys();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (Exception e) {
            throw new PhoneException("Erro ao adicionar novo tipo de telefone!", e);
        }finally{
            desconect();
        }
        throw new PhoneException("Erro ao adicionar novo tipo de telefone!");
    }
    
    public List<IPhoneType> findAll(){
        List<IPhoneType> list = new ArrayList<>();
        try {
            sql = "SELECT * FROM "+ TABLE_NAME;
            prepared(sql);
            rs = smt.executeQuery();
            while(rs.next()){
                PhoneType type = new PhoneType();
                type.setIdPhoneType(rs.getInt(ID_PHONE_TYPE));
                type.setNamePhoneType(rs.getString(NAME_PHONE_TYPE));
                list.add(type);
            }
            return list;
        } catch (Exception e) {
            throw new PhoneException("Erro ao buscar os tipos de telefones!", e);
        }finally{
            desconect();
        }
    }
    
}
