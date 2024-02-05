package Countermeasures;

import java.util.HashMap;

/**
 * Countermeasures data
 *
 * @author S.L. Dasanayake
 * @author A. Mudalige
 * @author M.L.T. Perera
 * @Contributor Thilanka Bowala <thilankabowala@gmail.com>
 * Did code refactoring on 28/1/24
 * Original repo: https://bitbucket.org/lasithd2/seproject_framework_for_secure_coding/src/master/
 * @since 2018
 */

public class Countermeasures_data {

    public HashMap<String, String> CountermeasureData = new HashMap<String, String>();

    public Countermeasures_data() {
        CountermeasureData.put("THI00J",
                "<html>" + "<head>" +
                        "<style>" +
                        " th {" +
                        "    color: #9900cc;" +
                        "    border: 1px solid black;" +
                        "    border-collapse: collapse;" +
                        "    padding: 5px;" +
                        "}" +
                        " td {" +
                        "    background-color: #ffffff;" +
                        "    border: 1px solid black;" +
                        "    border-collapse: collapse;" +
                        "    padding: 5px;" +
                        "}" +
                        "</style>" +
                        "</head>" +
                        "<h3><font color='blue'>THI00J : Do not invoke Thread.run()</font><h3>" +
                        "<table style=\"width:100%;\">" +
                        "  <tr>" +
                        "    <th>Severity</th>" +
                        "    <th>Likelihood</th>" +
                        "    <th>Remediation Cost</th>" +
                        "    <th>Priority</th>" +
                        "    <th>Level</th>" +
                        "  </tr>" +
                        "  <tr>" +
                        "    <td>Low</td>" +
                        "    <td>Probable</td>" +
                        "    <td>Medium</td>" +
                        "    <td>P4</td>" +
                        "    <td>L3</td>" +
                        "  </tr>" +
                        "</table>" +
                        "<br>"
                        + "<html>");

        CountermeasureData.put("SER01J",
                "<html>" + "<head>" +
                        "<style>" +
                        " th {" +
                        "    color: #9900cc;" +
                        "    border: 1px solid black;" +
                        "    border-collapse: collapse;" +
                        "    padding: 5px;" +
                        "}" +
                        " td {" +
                        "    background-color: #ffffff;" +
                        "    border: 1px solid black;" +
                        "    border-collapse: collapse;" +
                        "    padding: 5px;" +
                        "}" +
                        "</style>" +
                        "</head>" +
                        "<h3><font color='blue'>SER01J : Do not deviate from the proper signatures of serialization methods</font><h3>" +
                        "<table style=\"width:100%;\">" +
                        "  <tr>" +
                        "    <th>Severity</th>" +
                        "    <th>Likelihood</th>" +
                        "    <th>Remediation Cost</th>" +
                        "    <th>Priority</th>" +
                        "    <th>Level</th>" +
                        "  </tr>" +
                        "  <tr>" +
                        "    <td>High</td>" +
                        "    <td>Likely</td>" +
                        "    <td>Low</td>" +
                        "    <td>P27</td>" +
                        "    <td>L1</td>" +
                        "  </tr>" +
                        "</table>" +
                        "<br>"
                        + "<html>");


        CountermeasureData.put("MET09J",
                "<html>" + "<head>" +
                        "<style>" +
                        " th {" +
                        "    color: #9900cc;" +
                        "    border: 1px solid black;" +
                        "    border-collapse: collapse;" +
                        "    padding: 5px;" +
                        "}" +
                        " td {" +
                        "    background-color: #ffffff;" +
                        "    border: 1px solid black;" +
                        "    border-collapse: collapse;" +
                        "    padding: 5px;" +
                        "}" +
                        "</style>" +
                        "</head>" +
                        "<h3><font color='blue'>MET09J : Classes that define an equals() method must also define a hashCode() method</font><h3>" +
                        "<table style=\"width:100%;\">" +
                        "  <tr>" +
                        "    <th>Severity</th>" +
                        "    <th>Likelihood</th>" +
                        "    <th>Remediation Cost</th>" +
                        "    <th>Priority</th>" +
                        "    <th>Level</th>" +
                        "  </tr>" +
                        "  <tr>" +
                        "    <td>Low</td>" +
                        "    <td>Unlikely</td>" +
                        "    <td>High</td>" +
                        "    <td>P1</td>" +
                        "    <td>L3</td>" +
                        "  </tr>" +
                        "</table>" +
                        "<br>"
                        + "<html>");

        CountermeasureData.put("OBJ05J",
                "<html>" + "<head>" +
                        "<style>" +
                        " th {" +
                        "    color: #9900cc;" +
                        "    border: 1px solid black;" +
                        "    border-collapse: collapse;" +
                        "    padding: 5px;" +
                        "}" +
                        " td {" +
                        "    background-color: #ffffff;" +
                        "    border: 1px solid black;" +
                        "    border-collapse: collapse;" +
                        "    padding: 5px;" +
                        "}" +
                        "</style>" +
                        "</head>" +
                        "<h3><font color='blue'>OBJ05J : Do not return references to private mutable class members</font><h3>" +
                        "<table style=\"width:100%;\">" +
                        "  <tr>" +
                        "    <th>Severity</th>" +
                        "    <th>Likelihood</th>" +
                        "    <th>Remediation Cost</th>" +
                        "    <th>Priority</th>" +
                        "    <th>Level</th>" +
                        "  </tr>" +
                        "  <tr>" +
                        "    <td>Low</td>" +
                        "    <td>Unlikely</td>" +
                        "    <td>High</td>" +
                        "    <td>P1</td>" +
                        "    <td>L3</td>" +
                        "  </tr>" +
                        "</table>" +
                        "<br>"
                        + "<html>");

        CountermeasureData.put("NUM09J",
                "<html>" + "<head>" +
                        "<style>" +
                        " th {" +
                        "    color: #9900cc;" +
                        "    border: 1px solid black;" +
                        "    border-collapse: collapse;" +
                        "    padding: 5px;" +
                        "}" +
                        " td {" +
                        "    background-color: #ffffff;" +
                        "    border: 1px solid black;" +
                        "    border-collapse: collapse;" +
                        "    padding: 5px;" +
                        "}" +
                        "</style>" +
                        "</head>" +
                        "<h3><font color='blue'>NUM09J : Do not use floating-point variables as loop counters</font><h3>" +
                        "<table style=\"width:100%;\">" +
                        "  <tr>" +
                        "    <th>Severity</th>" +
                        "    <th>Likelihood</th>" +
                        "    <th>Remediation Cost</th>" +
                        "    <th>Priority</th>" +
                        "    <th>Level</th>" +
                        "  </tr>" +
                        "  <tr>" +
                        "    <td>Low</td>" +
                        "    <td>Probable</td>" +
                        "    <td>Low</td>" +
                        "    <td>P6</td>" +
                        "    <td>L2</td>" +
                        "  </tr>" +
                        "</table>" +
                        "<br>"
                        + "<html>");

        CountermeasureData.put("ERR08J",
                "<html>" + "<head>" +
                        "<style>" +
                        " th {" +
                        "    color: #9900cc;" +
                        "    border: 1px solid black;" +
                        "    border-collapse: collapse;" +
                        "    padding: 5px;" +
                        "}" +
                        " td {" +
                        "    background-color: #ffffff;" +
                        "    border: 1px solid black;" +
                        "    border-collapse: collapse;" +
                        "    padding: 5px;" +
                        "}" +
                        "</style>" +
                        "</head>" +
                        "<h3><font color='blue'>ERR08J : Do not catch NullPointerException or any of its ancestors</font><h3>" +
                        "<table style=\"width:100%;\">" +
                        "  <tr>" +
                        "    <th>Severity</th>" +
                        "    <th>Likelihood</th>" +
                        "    <th>Remediation Cost</th>" +
                        "    <th>Priority</th>" +
                        "    <th>Level</th>" +
                        "  </tr>" +
                        "  <tr>" +
                        "    <td>Medium</td>" +
                        "    <td>Likely</td>" +
                        "    <td>Medium</td>" +
                        "    <td>P12</td>" +
                        "    <td>L1</td>" +
                        "  </tr>" +
                        "</table>" +
                        "<br>"
                        + "<html>");

        CountermeasureData.put("OBJ01J",
                "<html>" + "<head>" +
                        "<style>" +
                        " th {" +
                        "    color: #9900cc;" +
                        "    border: 1px solid black;" +
                        "    border-collapse: collapse;" +
                        "    padding: 5px;" +
                        "}" +
                        " td {" +
                        "    background-color: #ffffff;" +
                        "    border: 1px solid black;" +
                        "    border-collapse: collapse;" +
                        "    padding: 5px;" +
                        "}" +
                        "</style>" +
                        "</head>" +
                        "<h3><font color='blue'>OBJ01J : Limit accessibility of fields</font><h3>" +
                        "<table style=\"width:100%;\">" +
                        "  <tr>" +
                        "    <th>Severity</th>" +
                        "    <th>Likelihood</th>" +
                        "    <th>Remediation Cost</th>" +
                        "    <th>Priority</th>" +
                        "    <th>Level</th>" +
                        "  </tr>" +
                        "  <tr>" +
                        "    <td>Medium</td>" +
                        "    <td>Likely</td>" +
                        "    <td>Medium</td>" +
                        "    <td>P12</td>" +
                        "    <td>L1</td>" +
                        "  </tr>" +
                        "</table>" +
                        "<br>"
                        + "<html>");

        CountermeasureData.put("OBJ10J",
                "<html>" + "<head>" +
                        "<style>" +
                        " th {" +
                        "    color: #9900cc;" +
                        "    border: 1px solid black;" +
                        "    border-collapse: collapse;" +
                        "    padding: 5px;" +
                        "}" +
                        " td {" +
                        "    background-color: #ffffff;" +
                        "    border: 1px solid black;" +
                        "    border-collapse: collapse;" +
                        "    padding: 5px;" +
                        "}" +
                        "</style>" +
                        "</head>" +
                        "<h3><font color='blue'>OBJ10J : Do not use public static nonfinal fields</font><h3>" +
                        "<table style=\"width:100%;\">" +
                        "  <tr>" +
                        "    <th>Severity</th>" +
                        "    <th>Likelihood</th>" +
                        "    <th>Remediation Cost</th>" +
                        "    <th>Priority</th>" +
                        "    <th>Level</th>" +
                        "  </tr>" +
                        "  <tr>" +
                        "    <td>Medium</td>" +
                        "    <td>Probable</td>" +
                        "    <td>Medium</td>" +
                        "    <td>P8</td>" +
                        "    <td>L2</td>" +
                        "  </tr>" +
                        "</table>" +
                        "<br>"
                        + "<html>");

        CountermeasureData.put("DCL00J",
                "<html>" + "<head>" +
                        "<style>" +
                        " th {" +
                        "    color: #9900cc;" +
                        "    border: 1px solid black;" +
                        "    border-collapse: collapse;" +
                        "    padding: 5px;" +
                        "}" +
                        " td {" +
                        "    background-color: #ffffff;" +
                        "    border: 1px solid black;" +
                        "    border-collapse: collapse;" +
                        "    padding: 5px;" +
                        "}" +
                        "</style>" +
                        "</head>" +
                        "<h3><font color='blue'>DCL00J : Prevent class initialization cycles</font><h3>" +
                        "<table style=\"width:100%;\">" +
                        "  <tr>" +
                        "    <th>Severity</th>" +
                        "    <th>Likelihood</th>" +
                        "    <th>Remediation Cost</th>" +
                        "    <th>Priority</th>" +
                        "    <th>Level</th>" +
                        "  </tr>" +
                        "  <tr>" +
                        "    <td>Low</td>" +
                        "    <td>Unlikely</td>" +
                        "    <td>Medium</td>" +
                        "    <td>P2</td>" +
                        "    <td>L3</td>" +
                        "  </tr>" +
                        "</table>" +
                        "<br>"
                        + "<html>");

        CountermeasureData.put("NUM10J",
                "<html>" + "<head>" +
                        "<style>" +
                        " th {" +
                        "    color: #9900cc;" +
                        "    border: 1px solid black;" +
                        "    border-collapse: collapse;" +
                        "    padding: 5px;" +
                        "}" +
                        " td {" +
                        "    background-color: #ffffff;" +
                        "    border: 1px solid black;" +
                        "    border-collapse: collapse;" +
                        "    padding: 5px;" +
                        "}" +
                        "</style>" +
                        "</head>" +
                        "<h3><font color='blue'>NUM10J : Do not construct BigDecimal objects from floating-point literals</font><h3>" +
                        "<table style=\"width:100%;\">" +
                        "  <tr>" +
                        "    <th>Severity</th>" +
                        "    <th>Likelihood</th>" +
                        "    <th>Remediation Cost</th>" +
                        "    <th>Priority</th>" +
                        "    <th>Level</th>" +
                        "  </tr>" +
                        "  <tr>" +
                        "    <td>Low</td>" +
                        "    <td>Probable</td>" +
                        "    <td>Low</td>" +
                        "    <td>P6</td>" +
                        "    <td>L2</td>" +
                        "  </tr>" +
                        "</table>" +
                        "<br>"
                        + "<html>");

        CountermeasureData.put("SEC07J",
                "<html>" + "<head>" +
                        "<style>" +
                        " th {" +
                        "    color: #9900cc;" +
                        "    border: 1px solid black;" +
                        "    border-collapse: collapse;" +
                        "    padding: 5px;" +
                        "}" +
                        " td {" +
                        "    background-color: #ffffff;" +
                        "    border: 1px solid black;" +
                        "    border-collapse: collapse;" +
                        "    padding: 5px;" +
                        "}" +
                        "</style>" +
                        "</head>" +
                        "<h3><font color='blue'>SEC07J : Call the superclass's getPermissions() method when writing a custom class loader</font><h3>" +
                        "<table style=\"width:100%;\">" +
                        "  <tr>" +
                        "    <th>Severity</th>" +
                        "    <th>Likelihood</th>" +
                        "    <th>Remediation Cost</th>" +
                        "    <th>Priority</th>" +
                        "    <th>Level</th>" +
                        "  </tr>" +
                        "  <tr>" +
                        "    <td>High</td>" +
                        "    <td>Probable</td>" +
                        "    <td>Low</td>" +
                        "    <td>P18</td>" +
                        "    <td>L1</td>" +
                        "  </tr>" +
                        "</table>" +
                        "<br>"
                        + "<html>");

        CountermeasureData.put("FIO02J",
                "<html>" + "<head>" +
                        "<style>" +
                        " th {" +
                        "    color: #9900cc;" +
                        "    border: 1px solid black;" +
                        "    border-collapse: collapse;" +
                        "    padding: 5px;" +
                        "}" +
                        " td {" +
                        "    background-color: #ffffff;" +
                        "    border: 1px solid black;" +
                        "    border-collapse: collapse;" +
                        "    padding: 5px;" +
                        "}" +
                        "</style>" +
                        "</head>" +
                        "<h3><font color='blue'>FIO02J : Detect and handle file-related errors</font><h3>" +
                        "<table style=\"width:100%;\">" +
                        "  <tr>" +
                        "    <th>Severity</th>" +
                        "    <th>Likelihood</th>" +
                        "    <th>Remediation Cost</th>" +
                        "    <th>Priority</th>" +
                        "    <th>Level</th>" +
                        "  </tr>" +
                        "  <tr>" +
                        "    <td>Medium</td>" +
                        "    <td>Probable</td>" +
                        "    <td>Medium</td>" +
                        "    <td>P8</td>" +
                        "    <td>L2</td>" +
                        "  </tr>" +
                        "</table>" +
                        "<br>"
                        + "<html>");

        CountermeasureData.put("ERR07J",
                "<html>" + "<head>" +
                        "<style>" +
                        " th {" +
                        "    color: #9900cc;" +
                        "    border: 1px solid black;" +
                        "    border-collapse: collapse;" +
                        "    padding: 5px;" +
                        "}" +
                        " td {" +
                        "    background-color: #ffffff;" +
                        "    border: 1px solid black;" +
                        "    border-collapse: collapse;" +
                        "    padding: 5px;" +
                        "}" +
                        "</style>" +
                        "</head>" +
                        "<h3><font color='blue'>ERR07J : Do not throw RuntimeException, Exception, or Throwable</font><h3>" +
                        "<table style=\"width:100%;\">" +
                        "  <tr>" +
                        "    <th>Severity</th>" +
                        "    <th>Likelihood</th>" +
                        "    <th>Remediation Cost</th>" +
                        "    <th>Priority</th>" +
                        "    <th>Level</th>" +
                        "  </tr>" +
                        "  <tr>" +
                        "    <td>Low</td>" +
                        "    <td>Likely</td>" +
                        "    <td>Medium</td>" +
                        "    <td>P6</td>" +
                        "    <td>L2</td>" +
                        "  </tr>" +
                        "</table>" +
                        "<br>"
                        + "<html>");


        CountermeasureData.put("ERR04J",
                "<html>" + "<head>" +
                        "<style>" +
                        " th {" +
                        "    color: #9900cc;" +
                        "    border: 1px solid black;" +
                        "    border-collapse: collapse;" +
                        "    padding: 5px;" +
                        "}" +
                        " td {" +
                        "    background-color: #ffffff;" +
                        "    border: 1px solid black;" +
                        "    border-collapse: collapse;" +
                        "    padding: 5px;" +
                        "}" +
                        "</style>" +
                        "</head>" +
                        "<h3><font color='blue'>ERR04J : Do not complete abruptly from a finally block</font><h3>" +
                        "<table style=\"width:100%;\">" +
                        "  <tr>" +
                        "    <th>Severity</th>" +
                        "    <th>Likelihood</th>" +
                        "    <th>Remediation Cost</th>" +
                        "    <th>Priority</th>" +
                        "    <th>Level</th>" +
                        "  </tr>" +
                        "  <tr>" +
                        "    <td>Low</td>" +
                        "    <td>Probable</td>" +
                        "    <td>Medium</td>" +
                        "    <td>P4</td>" +
                        "    <td>L3</td>" +
                        "  </tr>" +
                        "</table>" +
                        "<br>"
                        + "<html>");

        CountermeasureData.put("EXP02J",
                "<html>" + "<head>" +
                        "<style>" +
                        " th {" +
                        "    color: #9900cc;" +
                        "    border: 1px solid black;" +
                        "    border-collapse: collapse;" +
                        "    padding: 5px;" +
                        "}" +
                        " td {" +
                        "    background-color: #ffffff;" +
                        "    border: 1px solid black;" +
                        "    border-collapse: collapse;" +
                        "    padding: 5px;" +
                        "}" +
                        "</style>" +
                        "</head>" +
                        "<h3><font color='blue'>EXP02J : Do not use the Object.equals() method to compare two arrays</font><h3>" +
                        "<table style=\"width:100%;\">" +
                        "  <tr>" +
                        "    <th>Severity</th>" +
                        "    <th>Likelihood</th>" +
                        "    <th>Remediation Cost</th>" +
                        "    <th>Priority</th>" +
                        "    <th>Level</th>" +
                        "  </tr>" +
                        "  <tr>" +
                        "    <td>Low</td>" +
                        "    <td>Likely</td>" +
                        "    <td>Low</td>" +
                        "    <td>P9</td>" +
                        "    <td>L2</td>" +
                        "  </tr>" +
                        "</table>" +
                        "<br>"
                        + "<html>");
    }


}