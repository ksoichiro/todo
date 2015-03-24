import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.Play;
import play.api.mvc.EssentialFilter;
import play.db.DB;
import play.filters.csrf.CSRFFilter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;

public class Global extends GlobalSettings {
    @Override
    public void onStart(Application application) {
        Logger.info("Application has started");

        // Always clear and insert data for test
        try (Connection conn = DB.getConnection();
             BufferedReader reader = new BufferedReader(
                new InputStreamReader(Play.application().resourceAsStream("initial-data.sql")))) {
            conn.setAutoCommit(false);
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("-- ")) {
                    continue;
                }
                sb.append(line);
                if (!line.endsWith(";")) {
                    continue;
                }
                String sql = sb.toString();
                Logger.info("SQL: " + sql);
                conn.createStatement().execute(sql);
                sb = new StringBuilder();
            }
            conn.commit();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load initial data", e);
        }
    }

    @Override
    public <T extends EssentialFilter> Class<T>[] filters() {
        return new Class[]{CSRFFilter.class};
    }
}
