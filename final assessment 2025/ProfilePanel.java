import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class ProfilePanel extends JPanel {
    private final Color PURPLE_THEME = new Color(128, 0, 128);
    private final Color LIGHT_PURPLE = new Color(230, 204, 255);
    private final Color DARK_PURPLE = new Color(102, 0, 102);
    private Runnable onHomeNavigation;
    private Runnable onLogoutNavigation;

    // Fields for user info
    private JTextField nameField, emailField, addressField;
    private JCheckBox notificationsCheckBox, privacyCheckBox;
    private JComboBox<String> languageCombo;
    private JButton editButton, saveButton, cancelButton;

    public ProfilePanel(JFrame parentFrame, Runnable onHomeNavigation, Runnable onLogoutNavigation) {
        this.onHomeNavigation = onHomeNavigation;
        this.onLogoutNavigation = onLogoutNavigation;
        initializeUI();
    }

    public void updateUserInfo() {
        if(BillingApplication.currentUser != null) {
            nameField.setText(BillingApplication.currentUser.name);
            emailField.setText(BillingApplication.currentUser.email);
            addressField.setText(BillingApplication.currentUser.address);
            // Anda boleh tambahkan field lain jika perlu
    }
        repaint();
}

    private void initializeUI() {
        setLayout(new BorderLayout(20, 20));
        setBackground(LIGHT_PURPLE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header with profile icon
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setOpaque(false);
        JLabel headerLabel = new JLabel("My Profile", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(PURPLE_THEME);
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Main content panel
        JPanel contentPanel = new JPanel(new GridLayout(2, 1, 20, 20));
        contentPanel.setOpaque(false);
        contentPanel.add(createUserInfoPanel());
        contentPanel.add(createSettingsPanel());
        add(contentPanel, BorderLayout.CENTER);

        // Action buttons panel
        add(createAccountActionsPanel(), BorderLayout.SOUTH);

        // Initially disable editing
        toggleEditMode(false);
    }

    private JPanel createUserInfoPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(PURPLE_THEME, 2),
                "Personal Information",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14),
                PURPLE_THEME
        ));
        panel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Name field
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(PURPLE_THEME);
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        nameField = new JTextField("ulgahihihi", 20);
        nameField.setBorder(BorderFactory.createLineBorder(PURPLE_THEME));
        panel.add(nameField, gbc);

        // Email field
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(PURPLE_THEME);
        panel.add(emailLabel, gbc);
        gbc.gridx = 1;
        emailField = new JTextField("fisheyeball@yahoo.com", 20);
        emailField.setBorder(BorderFactory.createLineBorder(PURPLE_THEME));
        panel.add(emailField, gbc);

        // Address field
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setForeground(PURPLE_THEME);
        panel.add(addressLabel, gbc);
        gbc.gridx = 1;
        addressField = new JTextField("KT5 GMI", 20);
        addressField.setBorder(BorderFactory.createLineBorder(PURPLE_THEME));
        panel.add(addressField, gbc);

        // Edit button
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.fill = GridBagConstraints.NONE;
        editButton = new JButton("✏ Edit Profile");
        styleButton(editButton, PURPLE_THEME);
        editButton.addActionListener(e -> toggleEditMode(true));
        panel.add(editButton, gbc);

        return panel;
    }

    private JPanel createSettingsPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(PURPLE_THEME, 2),
                "Account Settings",
                javax.swing.border.TitledBorder.LEFT,
                javax.swing.border.TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14),
                PURPLE_THEME
        ));
        panel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Notifications
        gbc.gridx = 0; gbc.gridy = 0;
        notificationsCheckBox = new JCheckBox("Enable Notifications");
        notificationsCheckBox.setSelected(true);
        notificationsCheckBox.setForeground(DARK_PURPLE);
        panel.add(notificationsCheckBox, gbc);

        // Privacy
        gbc.gridx = 0; gbc.gridy = 1;
        privacyCheckBox = new JCheckBox("Private Profile");
        privacyCheckBox.setSelected(false);
        privacyCheckBox.setForeground(DARK_PURPLE);
        panel.add(privacyCheckBox, gbc);

        // Language
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel languageLabel = new JLabel("Language:");
        languageLabel.setForeground(PURPLE_THEME);
        panel.add(languageLabel, gbc);
        gbc.gridx = 1;
        languageCombo = new JComboBox<>(new String[]{"English", "Malaysia", "German"});
        languageCombo.setBackground(Color.WHITE);
        languageCombo.setForeground(DARK_PURPLE);
        languageCombo.setBorder(BorderFactory.createLineBorder(PURPLE_THEME));
        panel.add(languageCombo, gbc);

        return panel;
    }

    private JPanel createAccountActionsPanel() {
        JPanel panel = new JPanel(new BorderLayout(20, 0));
        panel.setOpaque(false);

        // Left side: Save and Cancel buttons
        JPanel editButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        editButtonsPanel.setOpaque(false);

        saveButton = new JButton("💾 Save Changes");
        styleButton(saveButton, PURPLE_THEME);
        saveButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Profile saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            toggleEditMode(false);
        });

        cancelButton = new JButton("✖ Cancel");
        styleButton(cancelButton, DARK_PURPLE);
        cancelButton.addActionListener(e -> toggleEditMode(false));

        editButtonsPanel.add(saveButton);
        editButtonsPanel.add(cancelButton);
        editButtonsPanel.setVisible(false);
        panel.add(editButtonsPanel, BorderLayout.WEST);

        // Right side: Account actions
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        actionPanel.setOpaque(false);

        JButton backButton = new JButton("← Back to Home");
        styleButton(backButton, PURPLE_THEME);
        backButton.addActionListener(e -> onHomeNavigation.run());
        actionPanel.add(backButton);

        JButton logoutButton = new JButton("🚪 Log Out");
        styleButton(logoutButton, PURPLE_THEME);
        logoutButton.addActionListener(e -> confirmLogout());
        actionPanel.add(logoutButton);

        JButton deleteButton = new JButton("☠ Delete Account");
        styleButton(deleteButton, Color.RED.darker());
        deleteButton.addActionListener(e -> confirmDeletion());
        actionPanel.add(deleteButton);

        panel.add(actionPanel, BorderLayout.EAST);
        return panel;
    }

    private void styleButton(JButton button, Color bgColor) {
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(bgColor.darker(), 1),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        button.setFocusPainted(false);
    }

    private void toggleEditMode(boolean edit) {
        nameField.setEditable(edit);
        emailField.setEditable(edit);
        addressField.setEditable(edit);

        Color bg = edit ? Color.WHITE : LIGHT_PURPLE;
        nameField.setBackground(bg);
        emailField.setBackground(bg);
        addressField.setBackground(bg);

        editButton.setVisible(!edit);
        
        // Show/hide save and cancel buttons
        ((JPanel) getComponent(2)).getComponent(0).setVisible(edit);
    }

    private void confirmLogout() {
        int response = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to log out?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (response == JOptionPane.YES_OPTION) {
            onLogoutNavigation.run();
        }
    }

    private void confirmDeletion() {
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBorder(BorderFactory.createLineBorder(PURPLE_THEME));

        Object[] message = {
                "Enter your password to confirm deletion:",
                passwordField
        };

        int option = JOptionPane.showConfirmDialog(
                this,
                message,
                "Delete Account",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (option == JOptionPane.OK_OPTION) {
            char[] password = passwordField.getPassword();
            if (password.length > 0) {
                Arrays.fill(password, '0');
                JOptionPane.showMessageDialog(
                        this,
                        "Account deleted successfully.",
                        "Account Deleted",
                        JOptionPane.INFORMATION_MESSAGE
                );
                onLogoutNavigation.run();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Password cannot be empty!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("User Profile System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 650);
            frame.setLocationRelativeTo(null);

            CardLayout cardLayout = new CardLayout();
            JPanel mainPanel = new JPanel(cardLayout);

            JPanel homePanel = new JPanel(new BorderLayout());
            homePanel.setBackground(new Color(230, 204, 255));

            JLabel header = new JLabel("Welcome to User Profile System", JLabel.CENTER);
            header.setFont(new Font("Arial", Font.BOLD, 24));
            header.setForeground(new Color(102, 0, 102));
            header.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
            homePanel.add(header, BorderLayout.NORTH);

            JButton profileButton = new JButton("Profile");
            profileButton.setFont(new Font("Arial", Font.PLAIN, 18));
            profileButton.setBackground(new Color(128, 0, 128));
            profileButton.setForeground(Color.WHITE);
            profileButton.setPreferredSize(new Dimension(200, 60));

            JPanel centerPanel = new JPanel(new GridBagLayout());
            centerPanel.setOpaque(false);
            centerPanel.add(profileButton);
            homePanel.add(centerPanel, BorderLayout.CENTER);

            JPanel loginPanel = createLoginPanel(frame, cardLayout, mainPanel);

            ProfilePanel profilePanel = new ProfilePanel(frame,
                    () -> cardLayout.show(mainPanel, "Home"),
                    () -> cardLayout.show(mainPanel, "Login")
            );

            profileButton.addActionListener(e -> {
                cardLayout.show(mainPanel, "Profile");
                frame.setTitle("User Profile System - Profile");
            });

            mainPanel.add(homePanel, "Home");
            mainPanel.add(profilePanel, "Profile");
            mainPanel.add(loginPanel, "Login");

            frame.add(mainPanel);
            frame.setVisible(true);
        });
    }

    private static JPanel createLoginPanel(JFrame frame, CardLayout cardLayout, JPanel mainPanel) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(230, 204, 255));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel title = new JLabel("Login to Your Account");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(102, 0, 102));
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        JTextField usernameField = new JTextField(20);
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(20);
        panel.add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(128, 0, 128));
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(e -> {
            if (usernameField.getText().isEmpty() || passwordField.getPassword().length == 0) {
                JOptionPane.showMessageDialog(panel, "Please enter both username and password", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                cardLayout.show(mainPanel, "Home");
                frame.setTitle("User Profile System - Home");
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);

        return panel;
    }
}
