package br.ufc.apsoo.DAO.config;
import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public final class PostGresMapConfig {

    private static final SqlMapClient sqlMapClient;

    /**
     * Overcomes the default constructor. Utility classes should be final and must not have public constructors.
     */
    private PostGresMapConfig() {
    }

    static {
        try {
            //Definindo o caminho para o SqlMapConfig e criando o reader
            String res = "SqlMapConfig.xml";
            Reader reader = Resources.getResourceAsReader(res);

            //Recuperando o client para o SqlMap
            sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
        } catch (Exception e) {
           
            throw new RuntimeException(e);
        }
    }

    public static SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
}