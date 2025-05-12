package com.adrianocodenow.contatos2022.factory;

import com.adrianocodenow.contatos2022.controller.Config;
import com.adrianocodenow.contatos2022.exceptions.ConexaoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sqlite.SQLiteConfig;

/**
 *
 * @author apereira
 */
public class ConnectionFactory {

    public static Connection conn = null;
    public static PreparedStatement smt;
    public static ResultSet rs;
    public static String sql;

    public static Connection abre(String bancoDeDados) {
        Connection db = null;
        try {
            Class.forName("org.sqlite.JDBC");
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            db = DriverManager.getConnection(
                    "jdbc:sqlite:" + bancoDeDados,
                    config.toProperties()
            );
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
    }

    public static void fecha(Connection db) {
        try {
            if (db != null) {
                db.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static Connection connected() throws SQLException {

        String url = "jdbc:sqlite:" + Config.BANCO_DE_DADOS;
        try {
            Class.forName("org.sqlite.JDBC");
            SQLiteConfig config = new SQLiteConfig();
            config.setEncoding(SQLiteConfig.Encoding.UTF8);
            conn = DriverManager.getConnection(url, config.toProperties());
            conn.setAutoCommit(false);
            return conn;
        } catch (ClassNotFoundException e) {
            throw new ConexaoException("Erro ao se conectar com o banco!", e);
        }
    }

    public static void begin() throws SQLException {
        if (Objects.isNull(conn)) {
            throw new ConexaoException("Conexão fechada!");
        }
        conn.beginRequest();
    }

    public static void rollback() throws SQLException {
        if (Objects.isNull(conn)) {
            throw new ConexaoException("Conexão fechada!");
        }
        conn.rollback();
    }

    public static boolean desconect() {
        try {
            if (!Objects.isNull(conn) && !conn.isClosed()) {
                conn.close();
                conn = null;
            }
            if (!Objects.isNull(smt) && !smt.isClosed()) {
                smt.close();
            }
            if (!Objects.isNull(rs) && !rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException e) {
            throw new ConexaoException("Erro ao fechar as conexões do banco!!!", "desconect()", e);
        }
        return true;
    }

    public static PreparedStatement prepared(String sql) throws SQLException {
        hasConnected();// Verifica se existe conexão
        return smt = conn.prepareStatement(sql);
    }

    public static PreparedStatement preparedInsert(String sql) throws SQLException {
        hasConnected();// Verifica se existe conexão
        return smt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    }

    public static boolean exec(String sql) throws SQLException {
        hasConnected();// Verifica se existe conexão
        return conn.prepareStatement(sql).execute();
    }
    
    private static void hasConnected() throws SQLException{
        if (Objects.isNull(conn)) {
            connected();
        }
    }
}
