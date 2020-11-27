import database.DBExample;
import forms.MainForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Main {
//Гайд по swing forms https://habr.com/ru/post/305974/

    public static void main(String[] args) throws SQLException {
        System.out.println("Hello World");
        DBExample db = new DBExample();

        ResultSet rs = db.selectAll();

        while (rs.next()) {
            System.out.println(rs.getInt("id") + "\t" +
                    rs.getString("Name") + "\t" +
                    rs.getDouble("Hint"));
        }


        MainForm form = new MainForm("Моя форма" ,db);
        form.getDataButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            form.t1model.reloadData();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }

                    }
                }

        );
    }

}
