package com.adrianocodenow.contatos2022.dao;

import com.adrianocodenow.contatos2022.exceptions.PhoneException;
import com.adrianocodenow.contatos2022.factory.ConnectionFactory;
import java.util.Objects;

/**
 *
 * @author ElderBR
 */
public class PhoneTypeDao extends ConnectionFactory {
    
    private static PhoneTypeDao instance;
    public final static String ID_PHONE_TYPE = "id_phone_type";
    public final static String NAME_PHONE_TYPE = "name_phone_type";

    private PhoneTypeDao() {
        try{
            sql = "CREATE TABLE IF NOT EXISTS tb_phone_type ("
                    + "id_phone_type INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "name_phone_type TEXT NOT NULL UNIQUE"
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
    
    
    
}
