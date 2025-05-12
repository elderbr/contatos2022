package com.adrianocodenow.contatos2022.dao;

import com.adrianocodenow.contatos2022.controller.Config;
import com.adrianocodenow.contatos2022.exceptions.ConexaoException;
import com.adrianocodenow.contatos2022.exceptions.PhoneException;
import com.adrianocodenow.contatos2022.factory.ConnectionFactory;
import com.adrianocodenow.contatos2022.interfaces.IPhone;
import com.adrianocodenow.contatos2022.model.Phone;
import com.adrianocodenow.contatos2022.model.Telefone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author apereira
 */
public class TelefoneDao extends ConnectionFactory {

    private static TelefoneDao instance;
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

    private TelefoneDao() {
        try {
            exec(CREATE_TABLE);
            conn.commit();
        } catch (SQLException ex) {
            throw new ConexaoException("Erro ao criar a tabela phone!", ex);
        } finally {
            desconect();
        }
    }

    public static TelefoneDao getInstance() {
        if (Objects.isNull(instance)) {
            instance = new TelefoneDao();
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
        } catch (Exception e) {
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

    public static boolean insere(Telefone objTelefone) {
        String sql
                = "INSERT INTO "
                + "telefones(telefone, idTipoTelefone, idContato) "
                + "VALUES(?, ?, ?)";
        Connection db = null;
        PreparedStatement stmt = null;
        Boolean retorno = true;
        try {
            db = ConnectionFactory.abre(Config.BANCO_DE_DADOS);
            if (db != null) {
                stmt = db.prepareStatement(sql);
                stmt.setString(1, objTelefone.getTelefone());
                stmt.setInt(2, objTelefone.getIdTipoTelefone());
                stmt.setInt(3, objTelefone.getIdContato());
                stmt.execute();
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                ConnectionFactory.fecha(db);
            } catch (SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
        return true;
    }

    public static boolean altera(Telefone objTelefone) {
        String sql
                = "UPDATE telefones SET "
                + "telefone = ? , idTipoTelefone =? , idContato = ? "
                + "WHERE idTelefone = ? ";
        Connection db = null;
        PreparedStatement stmt = null;
        Boolean retorno = true;
        try {
            db = ConnectionFactory.abre(Config.BANCO_DE_DADOS);
            if (db != null) {
                stmt = db.prepareStatement(sql);
                stmt.setString(1, objTelefone.getTelefone());
                stmt.setInt(2, objTelefone.getIdTipoTelefone());
                stmt.setInt(3, objTelefone.getIdContato());
                stmt.setInt(4, objTelefone.getIdTelefone());
                stmt.execute();
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                ConnectionFactory.fecha(db);
            } catch (SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
        return true;
    }

    public static boolean deleta(int id) {
        String sql
                = "DELETE FROM telefones WHERE idTelefone = ? ";
        Connection db = null;
        PreparedStatement stmt = null;
        Boolean retorno = true;
        try {
            db = ConnectionFactory.abre(Config.BANCO_DE_DADOS);
            if (db != null) {
                stmt = db.prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.execute();
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                ConnectionFactory.fecha(db);
            } catch (SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
        return true;
    }

    public static boolean deletaIDContato(int id) {
        String sql
                = "DELETE FROM telefones WHERE idContato = ? ";
        Connection db = null;
        PreparedStatement stmt = null;
        Boolean retorno = true;
        try {
            db = ConnectionFactory.abre(Config.BANCO_DE_DADOS);
            if (db != null) {
                stmt = db.prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.execute();
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                ConnectionFactory.fecha(db);
            } catch (SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
        return true;
    }

    public static List<Telefone> pesquisa(String busca) {

        String sql
                = "SELECT * FROM telefones WHERE telefone LIKE ? ";
        List<Telefone> telefones = new ArrayList<Telefone>();

        Connection db = null;
        PreparedStatement stmt = null;
        ResultSet resultado = null;
        try {
            db = ConnectionFactory.abre(Config.BANCO_DE_DADOS);
            if (db != null) {
                stmt = db.prepareStatement(sql);
                stmt.setString(1, "%" + busca + "%");
                resultado = stmt.executeQuery();
                while (resultado.next()) {
                    Telefone rstTelefone = new Telefone();
                    rstTelefone.setIdTelefone(resultado.getInt("idTelefone"));
                    rstTelefone.setTelefone(resultado.getString("telefone"));
                    rstTelefone.setIdTipoTelefone(resultado.getInt("idTipoTelefone"));
                    rstTelefone.setIdContato(resultado.getInt("idContato"));
                    telefones.add(rstTelefone);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                ConnectionFactory.fecha(db);
            } catch (SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
        return telefones;
    }

    public static List<Telefone> pesquisaID(int id) {

        String sql
                = "SELECT * FROM telefones WHERE idTelefone = ? ";
        List<Telefone> telefones = new ArrayList<Telefone>();

        Connection db = null;
        PreparedStatement stmt = null;
        ResultSet resultado = null;
        try {
            db = ConnectionFactory.abre(Config.BANCO_DE_DADOS);
            if (db != null) {
                stmt = db.prepareStatement(sql);
                stmt.setInt(1, id);
                resultado = stmt.executeQuery();
                while (resultado.next()) {
                    Telefone rstTelefone = new Telefone();
                    rstTelefone.setIdTelefone(resultado.getInt("idTelefone"));
                    rstTelefone.setTelefone(resultado.getString("telefone"));
                    rstTelefone.setIdTipoTelefone(resultado.getInt("idTipoTelefone"));
                    rstTelefone.setIdContato(resultado.getInt("idContato"));
                    telefones.add(rstTelefone);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                ConnectionFactory.fecha(db);
            } catch (SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
        return telefones;
    }

    public static Telefone buscaID(int id) {

        String sql
                = "SELECT * FROM telefones WHERE idTelefone = ? ";
        Telefone retorno = null;

        Connection db = null;
        PreparedStatement stmt = null;
        ResultSet resultado = null;
        try {
            db = ConnectionFactory.abre(Config.BANCO_DE_DADOS);
            if (db != null) {
                stmt = db.prepareStatement(sql);
                stmt.setInt(1, id);
                resultado = stmt.executeQuery();
                if (resultado.next()) {
                    Telefone rstTelefone = new Telefone();
                    rstTelefone.setIdTelefone(resultado.getInt("idTelefone"));
                    rstTelefone.setTelefone(resultado.getString("telefone"));
                    rstTelefone.setIdTipoTelefone(resultado.getInt("idTipoTelefone"));
                    rstTelefone.setIdContato(resultado.getInt("idContato"));
                    retorno = rstTelefone;
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                ConnectionFactory.fecha(db);
            } catch (SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
        return retorno;
    }

    public static boolean buscaIDTipoTipo(int id) {

        String sql
                = "SELECT * FROM telefones WHERE idTipoTelefone = ? ";
        boolean retorno = false;

        Connection db = null;
        PreparedStatement stmt = null;
        ResultSet resultado = null;
        try {
            db = ConnectionFactory.abre(Config.BANCO_DE_DADOS);
            if (db != null) {
                stmt = db.prepareStatement(sql);
                stmt.setInt(1, id);
                resultado = stmt.executeQuery();
                if (resultado.next()) {
                    retorno = true;
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                ConnectionFactory.fecha(db);
            } catch (SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
        return retorno;
    }

    public static List<Telefone> listaIDContato(int id) {

        String sql
                = "SELECT * FROM telefones WHERE idContato = ? ";
        List<Telefone> telefones = new ArrayList<Telefone>();

        Connection db = null;
        PreparedStatement stmt = null;
        ResultSet resultado = null;
        try {
            db = ConnectionFactory.abre(Config.BANCO_DE_DADOS);
            if (db != null) {
                stmt = db.prepareStatement(sql);
                stmt.setInt(1, id);
                resultado = stmt.executeQuery();
                while (resultado.next()) {
                    Telefone rstTelefone = new Telefone();
                    rstTelefone.setIdTelefone(resultado.getInt("idTelefone"));
                    rstTelefone.setTelefone(resultado.getString("telefone"));
                    rstTelefone.setIdTipoTelefone(resultado.getInt("idTipoTelefone"));
                    rstTelefone.setIdContato(resultado.getInt("idContato"));
                    telefones.add(rstTelefone);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                ConnectionFactory.fecha(db);
            } catch (SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
        return telefones;
    }

    public static void main(String[] args) {
        Telefone telefone = new Telefone();
        telefone.setTelefone("+55 (13) 4441 4000 ");
        telefone.setIdTipoTelefone(2);
        telefone.setIdContato(12);
        if (insere(telefone)) {
            System.out.println("Telefone Incluído com Sucesso");
        } else {
            System.out.println("Erro na Inclusão do Telefone");
        }
        for (Telefone retorno : pesquisaID(12)) {
            Telefone telefone1 = retorno;
            telefone1.setTelefone("+55 11 3758 7777");
            if (altera(telefone1)) {
                System.out.println("Telefone Alterado com Sucesso");
            } else {
                System.out.println("Erro na Alteração do Telefone");
            }
        }
        Telefone telefone2 = buscaID(2);
        if (telefone2 != null) {
            telefone2.setTelefone("+81 70 7777 7070");
            if (altera(telefone2)) {
                System.out.println("Telefone Alterado com Sucesso");
            } else {
                System.out.println("Erro na Alteração do Telefone");
            }
        } else {
            System.out.println("Telefone não disponível");
        }
        System.out.println("---------------------------------------------------");
        System.out.println("Lista ICContato");
        System.out.println("---------------------------------------------------");
        for (Telefone retorno : listaIDContato(8)) {
            System.out.println("idTelefone    : " + retorno.getIdTelefone());
            System.out.println("Telefone      : " + retorno.getTelefone());
            System.out.println("idTipoTelefone: " + retorno.getIdTipoTelefone());
            System.out.println("idContato     : " + retorno.getIdContato());
            System.out.println("---------------------------------------------------");
        }
        System.out.println("---------------------------------------------------");
        System.out.println("Pesquisa Telefone");
        System.out.println("---------------------------------------------------");
        for (Telefone retorno : pesquisa("7777")) {
            System.out.println("idTelefone    : " + retorno.getIdTelefone());
            System.out.println("Telefone      : " + retorno.getTelefone());
            System.out.println("idTipoTelefone: " + retorno.getIdTipoTelefone());
            System.out.println("idContato     : " + retorno.getIdContato());
            System.out.println("---------------------------------------------------");
        }
        if (deleta(13)) {
            System.out.println("Telefone Excluído com Sucesso");
        } else {
            System.out.println("Erro na Exclusão do Telefone");
        }
        if (deletaIDContato(12)) {
            System.out.println("Telefone Excluído com Sucesso");
        } else {
            System.out.println("Erro na Exclusão do Telefone");
        }

    }
}
