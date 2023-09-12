/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pos_java_ant_1;

/**
 *
 * @author wow
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */




import java.awt.Container;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.*;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;



public class ReportView extends JFrame
{
    public ReportView(String fileName)
    {
        this(fileName, null);
    }
   
    
    
    public ReportView(String fileName, HashMap para)
    {
        super("Flexiart Software Solutions  POS System (Report Viewer)");

//        db d = new db();
        Connection con = db.myCon();
                
                
//        
//        Dbcon dba;
//        dba = new Dbcon();
//        java.sql.Connection con;
//        con = Dbcon.mycon();
//       

        try
        {
            JasperPrint print = JasperFillManager.fillReport(fileName, para, con);
            JRViewer viewer = new JRViewer(print);
            Container c = getContentPane();
            c.add(viewer);            
        } 
        catch (JRException jRException)
        {
            System.out.println(jRException);
        }
        setBounds(5, 5, 1400, 1000);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    ReportView(InputStream report) {
        Connection con = db.myCon();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        try {
//            if (report != null) {
//                JasperReport jasperReport = (JasperReport) JRLoader.loadObject(report);
//                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, con);
//
//                // Display the report using a JasperViewer or your custom ReportView
//                JasperViewer.viewReport(jasperPrint, false);
//
//            } else {
//                System.out.println("JasperReport file not found.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        setBounds(5, 5, 1400, 1000);
//        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    void print() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
