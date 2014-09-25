/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jdbc.connection;

/**
 *
 * @author epam
 */
public enum DBTypes {

    MYSQL("MYSQL"),
    ORACLE("ORACLE");

    DBTypes(String id) {
        this.id = id;
    }
    private String id;

    public String getId() {
        return id;
    }
}
