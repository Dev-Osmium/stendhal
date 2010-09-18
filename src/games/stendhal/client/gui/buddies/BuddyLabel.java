package games.stendhal.client.gui.buddies;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * Rendering component for buddies in a JList.
 */
class BuddyLabel extends JLabel implements ListCellRenderer {
	/**
	 * The online icon image.
	 */
	private static ImageIcon onlineIcon = new ImageIcon(BuddyLabel.class.getClassLoader().getResource("data/gui/buddy_online.png"));

	/**
	 * The offline icon image.
	 */
	private static ImageIcon offlineIcon = new ImageIcon(BuddyLabel.class.getClassLoader().getResource("data/gui/buddy_offline.png"));

	void setOnline(final boolean online) {
		this.setEnabled(online);
	}

	public BuddyLabel() {
		super();
		initialize();
		this.setText("bobbele");
	}

	private BuddyLabel(final String name) {
		this();
		setName(name);
		setText(name);
	}

	protected BuddyLabel(final String name, final boolean isOnline) {
		this(name);
		setOnline(isOnline);
	}

	/**
	 * This method initializes icons, foreground and size.
	 *
	 */
	private void initialize() {
		this.setOpaque(false);
		this.setIcon(onlineIcon);
		this.setDisabledIcon(offlineIcon);
		this.setForeground(Color.GREEN);
		this.setSize(new Dimension(200, 30));
	}

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean selected, boolean focused) {
		// We ignore most of the parameters
		if (value instanceof Buddy) {
			Buddy buddy = (Buddy) value;
			setText(buddy.getName());
			setOnline(buddy.isOnline());
		}
		return this;
	}
}
