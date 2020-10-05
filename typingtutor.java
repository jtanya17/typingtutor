package linkedlist;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

public class TypingTutor extends JFrame implements ActionListener {
	private JLabel labeltimer;
	private JLabel labelscore;
	private JLabel labelflashWord;
	private JTextField textWord;
	private JButton start;
	private JButton stop;

	private Timer timer = null;
	private boolean running = false;
	private int timeRemaining = 50;
	private int score = 0;

	private String[] words = null;

	public TypingTutor(String[] args) {
		this.words = args;
		GridLayout layout = new GridLayout(3, 2);
		super.setLayout(layout);

		Font font = new Font("Lucida Handwriting", 1, 100);

		labeltimer = new JLabel("Timer");
		labeltimer.setFont(font);
		labeltimer.setOpaque(true);
		labeltimer.setBackground(Color.cyan);
		super.add(labeltimer);

		labelscore = new JLabel("Score");
		labelscore.setFont(font);
		labelscore.setOpaque(true);
		labelscore.setBackground(Color.GREEN);
		super.add(labelscore);

		labelflashWord = new JLabel("");
		labelflashWord.setFont(font);
		labelflashWord.setOpaque(true);
		labelflashWord.setBackground(Color.PINK);
		super.add(labelflashWord);

		textWord = new JTextField("");
		textWord.setFont(font);
		textWord.setBackground(Color.MAGENTA);
		super.add(textWord);

		start = new JButton("Start");
		start.setFont(font);
		start.addActionListener(this);
		start.setBackground(Color.yellow);
		super.add(start);

		stop = new JButton("Stop");
		stop.addActionListener(this);
		stop.setBackground(Color.blue);
		stop.setFont(font);
		super.add(stop);

		super.setTitle("Typing Tutor");
		super.setExtendedState(MAXIMIZED_BOTH);
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		super.setVisible(true);
		
		setupthegame();
	}

	private void setupthegame() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == start) {
			handleStart();
		} else if (e.getSource() == stop) {
			handleStop();
		} else if (e.getSource() == timer) {
			handleTimer();
		}
	}

	private void handleTimer() {
		timeRemaining--;

		String actual, expected;
		actual = textWord.getText();
		expected = labelflashWord.getText();
		if (expected.length() > 0 && actual.equals(expected)) {
			score++;
		}

		labeltimer.setText("Timer: " + timeRemaining);
		labelscore.setText("Score: " + score);

		int ridx = (int) (Math.random() * words.length);
		labelflashWord.setText(words[ridx]);
		textWord.setText("");
	}

	private void handleStop() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, "Stop was clicked");
	}

	private void handleStart() {
		if (running == true) {
			timer.stop();
			running = false;
			start.setText("Resume");
			textWord.setEnabled(false);
			stop.setEnabled(false);
		} else {
			timer.start();
			running = true;
			start.setText("Pause");
			textWord.setEnabled(true);
			stop.setEnabled(true);
		}
	}
}