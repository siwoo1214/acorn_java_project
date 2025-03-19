package test1;

import javax.swing.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.*;

public class ButtonClickExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("이벤트 처리");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new FlowLayout());
        contentPane.setBackground(Color.GREEN);

        JButton button = new JButton("클릭하세요");
        contentPane.add(button);

        // ✅ 버튼 클릭 이벤트 추가
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "버튼이 클릭되었습니다!");
            }
        });

        frame.setVisible(true);
    }
}
