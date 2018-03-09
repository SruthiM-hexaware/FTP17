import java.util.List;
import java.util.Map;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Query;

public class DataBaseDemo {
    public static void main(String[] args) {
        Handle handle = null;
        DBI dbi = new DBI("jdbc:mysql://localhost:3306/testdb",
                "testuser", "test623");

        String sql = "SELECT * FROM Cars";
        try {
            handle = dbi.open();
            Query<Map<String, Object>> q = handle.createQuery(sql);
            List<Map<String, Object>> l = q.list();
            for (Map<String, Object> m : l) {
                System.out.printf("%d ", m.get("Id"));
                System.out.printf("%s ", m.get("Name"));
                System.out.println(m.get("Price"));
            }
        } finally {
            if (handle != null) {
                handle.close();
            }
        }
    }
}
