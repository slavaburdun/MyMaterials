/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

/**
 *
 * @author epam
 */
public class Runner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StudentJDBCDAO student = new StudentJDBCDAO();
		StudentBean alok = new StudentBean();
		alok.setName("Terminator");
		alok.setRollNo(9);
		alok.setCourse("MBA");
		alok.setAddress("Ranchi");
		StudentBean tinkoo = new StudentBean();
		tinkoo.setName("Zubkin");
		tinkoo.setRollNo(6);
		// Adding Data
		student.add(alok);
		// Deleting Data
//		student.delete(7);
		// Updating Data
//		student.update(tinkoo);
		// Displaying Data
		student.findAll();
    }

}

