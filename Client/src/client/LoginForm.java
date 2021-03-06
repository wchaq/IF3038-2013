/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author ASUS
 */
public class LoginForm extends javax.swing.JFrame {
    Socket socket;
    ObjectOutputStream out;
    int id;
    Boolean isConnected;
    String jsonStringForTask;
    /**
     * Creates new form LoginForm
     */
    public LoginForm() {
        initComponents();
        SetButton(0);
    }
    
    public void SetButton(int state){
        if(state == 0){ // pertama kali masuk
            btnLogin.setEnabled(true);
            btnChangeStatus.setEnabled(false);
            btnLogout.setEnabled(false);
            btnReconnect.setEnabled(false);
            btnRefresh.setEnabled(false);            
        }else if(state == 1){ // setelah login
            btnLogin.setEnabled(false);
            btnChangeStatus.setEnabled(true);
            btnLogout.setEnabled(true);
            btnReconnect.setEnabled(false);
            btnRefresh.setEnabled(true);     
        }else{
            btnLogin.setEnabled(true);
            btnChangeStatus.setEnabled(true);
            btnLogout.setEnabled(true);
            btnReconnect.setEnabled(true);
            btnRefresh.setEnabled(true);     
        }
    }
    private void initOutStream(){
        try {
            if(socket != null){
                out = new ObjectOutputStream(socket.getOutputStream());    
            }
        } catch (Exception e) {
            e.printStackTrace();
            out = null;
        }
    }
    private void initSocket(){
        Properties prop = new Properties(); 
        try {
            //load a properties file from class path, inside static method
            prop.load(this.getClass().getResourceAsStream("config.properties"));

            //get the property value and print it out
            String address = prop.getProperty("address").toString();
            String port = prop.getProperty("port").toString();
            
            socket = new Socket(address, Integer.parseInt(port));
            SetEnableBtnReconnect(false);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            socket = null;
            SetEnableBtnReconnect(true);
        }
    }
    
    public void SetEnableBtnReconnect(Boolean bool){
        btnReconnect.setEnabled(bool);
        isConnected = !bool;
        if(!isConnected && socket != null){
            try {
                socket.close();
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public void SetTitleID(int id){
        this.id = id;
        setTitle("Client - "+id);
    }
    
    public void SetLoginSukses(String jasonString){
        jsonStringForTask = jasonString;
        txtUsernameActive.setText(txtUsername.getText());
        SetJasonToUI(jasonString);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("log-"+txtUsernameActive.getText()+".txt"));
            writer.write("=== Log Activity : " + txtUsernameActive.getText() +" ===");
            writer.newLine();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SetButton(1);
        //(new Thread(new Synchronization(socket))).start();
    }
    
    void RefreshTask(String jasonString) {
        jsonStringForTask = jasonString;
        SetJasonToUI(jasonString);
    }
    
    public void SetMessageBox(String message){
        JOptionPane.showMessageDialog(null, message);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlLogin = new javax.swing.JPanel();
        txtUsername = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        txtPassword = new javax.swing.JTextField();
        pnlUser = new javax.swing.JPanel();
        txtUsernameActive = new javax.swing.JLabel();
        pnlTask = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaTask = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        cbTask = new javax.swing.JComboBox();
        btnChangeStatus = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnReconnect = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Client Form");

        txtUsername.setText("masperi");

        jLabel3.setText("Password");

        jLabel2.setText("Username");

        jLabel1.setText("Login");

        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        txtPassword.setText("awdawdawdawd");

        javax.swing.GroupLayout pnlLoginLayout = new javax.swing.GroupLayout(pnlLogin);
        pnlLogin.setLayout(pnlLoginLayout);
        pnlLoginLayout.setHorizontalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlLoginLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlLoginLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlLoginLayout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(jLabel1))
                    .addGroup(pnlLoginLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(btnLogin)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        pnlLoginLayout.setVerticalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLogin)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtUsernameActive.setText("Username");

        textAreaTask.setColumns(20);
        textAreaTask.setRows(5);
        jScrollPane1.setViewportView(textAreaTask);

        jLabel4.setText("Status");

        cbTask.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnChangeStatus.setText("Change");
        btnChangeStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeStatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTaskLayout = new javax.swing.GroupLayout(pnlTask);
        pnlTask.setLayout(pnlTaskLayout);
        pnlTaskLayout.setHorizontalGroup(
            pnlTaskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(pnlTaskLayout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbTask, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnChangeStatus)
                .addGap(0, 13, Short.MAX_VALUE))
        );
        pnlTaskLayout.setVerticalGroup(
            pnlTaskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTaskLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTaskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbTask, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChangeStatus))
                .addGap(0, 18, Short.MAX_VALUE))
        );

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnReconnect.setText("Reconnect");
        btnReconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReconnectActionPerformed(evt);
            }
        });

        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlUserLayout = new javax.swing.GroupLayout(pnlUser);
        pnlUser.setLayout(pnlUserLayout);
        pnlUserLayout.setHorizontalGroup(
            pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTask, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlUserLayout.createSequentialGroup()
                        .addComponent(txtUsernameActive)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLogout)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReconnect)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefresh)))
                .addContainerGap())
        );
        pnlUserLayout.setVerticalGroup(
            pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsernameActive)
                    .addComponent(btnRefresh)
                    .addComponent(btnReconnect)
                    .addComponent(btnLogout))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTask, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    Thread serverHandle;
    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        try {
            initSocket();
            initOutStream();
            serverHandle = new Thread(new ServerHandler(socket,this,id));
            serverHandle.start();
        
            String username = txtUsername.getText();
            String password = txtPassword.getText();

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte [] bytesPassword = password.getBytes();
            byte [] bytesMdPassword = md.digest(bytesPassword);
            String mdPassword = bytesMdPassword.toString();
            
            TreeMap<String,Object> msg = new TreeMap<String, Object>();
            msg.put("code", 1);
            msg.put("username", username);
            msg.put("password", password);
            out.writeObject(JSONValue.toJSONString(msg));
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error : "+e.toString());
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
         try {
            String username = txtUsernameActive.getText();
            
            TreeMap<String,Object> msg = new TreeMap<String, Object>();
            msg.put("code", 21);
            msg.put("username", username);
            out.writeObject(JSONValue.toJSONString(msg));
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error : "+e.toString());
        }
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnChangeStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeStatusActionPerformed
        if(isConnected){
            ChangeStatusWhenConnected();
        }else{
            ChangeStatusWhenNotConnected();
        }
    }                                               

    private void ChangeStatusWhenNotConnected() {
        try {
            String fromCB = cbTask.getSelectedItem().toString();
            String taskID = fromCB.split(":")[0].trim();
            String originStatus = fromCB.split(":")[2].trim();
            String username = txtUsernameActive.getText();
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String dtmLastUpdate = dateFormat.format(date);
            
            PrintWriter outFile = new PrintWriter(new BufferedWriter(new FileWriter("log-"+txtUsernameActive.getText()+".txt",true)));
            String notStatus = "";
            if(originStatus.equals("COMPLETE")){
                notStatus = "UNCOMPLETE";
            }else{
                notStatus = "COMPLETE";
            }
            
            TreeMap<String,String> toLog = new TreeMap<String, String>();
            toLog.put("timeupdate", dateFormat.format(date));
            toLog.put("taskid", taskID);
            toLog.put("statusfinal", notStatus);
            outFile.println(JSONValue.toJSONString(toLog));
            outFile.close();
            
            SetJasonToUI(jsonStringForTask,taskID,notStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void ChangeStatusWhenConnected() {
        try {
            String fromCB = cbTask.getSelectedItem().toString();
            String taskID = fromCB.split(":")[0].trim();
            String originStatus = fromCB.split(":")[2].trim();
            String username = txtUsernameActive.getText();

            TreeMap<String,Object> msg = new TreeMap<String, Object>();
            msg.put("code", 2);
            msg.put("taskid", taskID);
            msg.put("username", username);
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            msg.put("dtmlastupdate", dateFormat.format(date));
            out.writeObject(JSONValue.toJSONString(msg));
            out.flush();
            
            PrintWriter outFile = new PrintWriter(new BufferedWriter(new FileWriter("log-"+txtUsernameActive.getText()+".txt",true)));
            String notStatus = "";
            if(originStatus.equals("COMPLETE")){
                notStatus = "UNCOMPLETE";
            }else{
                notStatus = "COMPLETE";
            }
            TreeMap<String,String> toLog = new TreeMap<String, String>();
            toLog.put("timeupdate", dateFormat.format(date));
            toLog.put("taskid", taskID);
            toLog.put("statusfinal", notStatus);
            outFile.println(JSONValue.toJSONString(toLog));
            outFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnChangeStatusActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        try {
            socket.close();
            out.close();
            textAreaTask.setText("");
            SetButton(0);
        } catch (Exception e) {
            e.printStackTrace();
            SetMessageBox("Tidak tehubung dengan server, perubahan saat offline mode di abaikan");
            SetButton(0);
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnReconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReconnectActionPerformed
        initSocket();
        initOutStream();
        if(socket == null){
            SetMessageBox("Server Not Found");
        }else{
            serverHandle = new Thread(new ServerHandler(socket,this,id));
            serverHandle.start();
            
            ArrayList<String> listLine =  GetListFromTextFile();
            TreeMap<String,Object> msgOut = new TreeMap<String, Object>();
            msgOut.put("code",50);
            msgOut.put("message", JSONValue.toJSONString(listLine));
            try {
                out.writeObject(JSONValue.toJSONString(msgOut));
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
            SetButton(1);
            SetEnableBtnReconnect(false);
        }
    }//GEN-LAST:event_btnReconnectActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChangeStatus;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnReconnect;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JComboBox cbTask;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlLogin;
    private javax.swing.JPanel pnlTask;
    private javax.swing.JPanel pnlUser;
    private javax.swing.JTextArea textAreaTask;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUsername;
    private javax.swing.JLabel txtUsernameActive;
    // End of variables declaration//GEN-END:variables

    private void SetJasonToUI(String jasonString) {
        textAreaTask.setText("");
        JSONArray arr = (JSONArray) JSONValue.parse(jasonString);
        String [] aStringArray = new String[arr.size()];
        int i = 0;
        for(Object obj : arr){
            JSONObject jobj = (JSONObject) JSONValue.parse(obj.toString());
            String tampil = "---------------------------------\n";
            tampil += "Task Name : "+(jobj.get("taskname").toString())+ "\n";
            tampil += "Username : "+(jobj.get("username").toString())+ "\n";
            tampil += "Created Date : "+(jobj.get("createddate").toString())+ "\n";
            tampil += "Deadline : "+(jobj.get("deadline").toString())+ "\n";
            tampil += "Status : "+(jobj.get("status").toString())+ "\n";
            tampil += "Category Name : "+(jobj.get("categoryname").toString())+ "\n";
            tampil += "Assignee : "+(jobj.get("listassignee").toString())+ "\n";
            tampil += "Tag : "+(jobj.get("listtag").toString())+ "\n";
            tampil += ("---------------------------------")+ "\n";
            textAreaTask.setText(textAreaTask.getText()+tampil);
            
            aStringArray[i] = (jobj.get("taskid").toString())+" : "+(jobj.get("taskname").toString()+" : "+ (jobj.get("status").toString()));
            i++;
        }
        cbTask.setModel(new DefaultComboBoxModel(aStringArray));
    }

    private void SetJasonToUI(String jasonString,String taskid, String status) {
        textAreaTask.setText("");
        JSONArray arr = (JSONArray) JSONValue.parse(jasonString);
        ArrayList<String> listTask = new ArrayList<String>();
        String [] aStringArray = new String[arr.size()];
        int i = 0;
        for(Object obj : arr){
            JSONObject jobj = (JSONObject) JSONValue.parse(obj.toString());
            String tampil = "---------------------------------\n";
            tampil += "Task Name : "+(jobj.get("taskname").toString())+ "\n";
            tampil += "Username : "+(jobj.get("username").toString())+ "\n";
            tampil += "Created Date : "+(jobj.get("createddate").toString())+ "\n";
            tampil += "Deadline : "+(jobj.get("deadline").toString())+ "\n";
            if((jobj.get("taskid").toString()).equals(taskid)){
                tampil += "Status : "+status+ "\n";
                jobj.remove("status");
                jobj.put("status",status);
            }else{
                tampil += "Status : "+(jobj.get("status").toString())+ "\n";
            }
            tampil += "Category Name : "+(jobj.get("categoryname").toString())+ "\n";
            tampil += "Assignee : "+(jobj.get("listassignee").toString())+ "\n";
            tampil += "Tag : "+(jobj.get("listtag").toString())+ "\n";
            tampil += ("---------------------------------")+ "\n";
            textAreaTask.setText(textAreaTask.getText()+tampil);
            
            if((jobj.get("taskid").toString()).equals(taskid)){
                aStringArray[i] = (jobj.get("taskid").toString())+" : "+(jobj.get("taskname").toString()+" : "+ status);
            }else{
                aStringArray[i] = (jobj.get("taskid").toString())+" : "+(jobj.get("taskname").toString()+" : "+ (jobj.get("status").toString()));
            }            
            i++;
            listTask.add(JSONValue.toJSONString(jobj));
        }
        
        jsonStringForTask = JSONValue.toJSONString(listTask);
        cbTask.setModel(new DefaultComboBoxModel(aStringArray));
    }

    private ArrayList<String> GetListFromTextFile() {
        try 
        { 
            ArrayList<String> itemLine = new ArrayList<String>();
            FileInputStream fstream_school = new FileInputStream("log-"+txtUsernameActive.getText()+".txt"); 
            DataInputStream data_input = new DataInputStream(fstream_school); 
            BufferedReader buffer = new BufferedReader(new InputStreamReader(data_input)); 
            String str_line; 

            while ((str_line = buffer.readLine()) != null) 
            { 
                str_line = str_line.trim(); 
                if ((str_line.length()!=0))  
                { 
                    itemLine.add(str_line);
                } 
            }
            return itemLine;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
