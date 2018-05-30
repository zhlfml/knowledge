package me.thomas.knowledge.solr;

import me.thomas.knowledge.utils.DbHelper;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.ConcurrentUpdateSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoxinsheng
 * @date 7/26/16.
 */
public class Index {

    public static void main(String[] avgs) throws Exception {
        String urlString = "http://localhost:8983/solr/TRAVEL";
        SolrClient solr = new ConcurrentUpdateSolrClient
                .Builder(urlString)
                .withQueueSize(10)
                .withThreadCount(4)
                .build();

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/travel?useUnicode=true&characterEncoding=UTF-8";
        String username = "root";
        String password = "123456";

        Connection conn = DbHelper.getConnection(driver, url, username, password);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from gou_goods");

        List<SolrInputDocument> documents = new ArrayList<>();
        while (rs.next()) {
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", rs.getInt("id"));
            document.addField("name", rs.getString("name"));
            document.addField("default_price", rs.getFloat("default_price"));

            documents.add(document);

        }

        UpdateResponse response = solr.add(documents);
        // Remember to commit your changes!
        solr.commit();

        rs.close();
        stmt.close();
        conn.close();
    }

}
