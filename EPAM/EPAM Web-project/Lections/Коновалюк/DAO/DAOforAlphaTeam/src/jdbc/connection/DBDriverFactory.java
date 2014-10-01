/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.connection;

/**
 *
 * @author epam
 */
public final class DBDriverFactory {

    private static DBDriverBase driver = null;

    public static DBDriverBase createDriver(final DBTypes dbType) throws ClassNotFoundException {
        switch (dbType) {
            case MYSQL:
                driver = new DBDriverMysql();
                break;
            case ORACLE:
                throw new ClassNotFoundException("Unknown DB type: " + dbType);
            default:
                driver = new DBDriverMysql();
        }

        return driver;
    }

    private DBDriverFactory() {
    }
}
