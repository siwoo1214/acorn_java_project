package Tosstest.GUI;

import Tosstest.EventManage;

import javax.swing.*;
import java.awt.*;

public class EventFrame extends JFrame {

	private JTabbedPane tabbedPane;

	public EventFrame(EventManage eventManage) {
		setTitle("이벤트 및 포인트 관리");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());

		tabbedPane = new JTabbedPane();

		tabbedPane.addTab("쉬운 문제", new EasyQuizPanel(eventManage));
		tabbedPane.addTab("어려운 문제", new HardQuizPanel(eventManage));
		tabbedPane.addTab("포인트 환전", new PointExchangePanel());

		add(tabbedPane, BorderLayout.CENTER);

		setLocationRelativeTo(null);
		setVisible(true);
	}
}
