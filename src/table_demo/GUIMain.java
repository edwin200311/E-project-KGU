package table_demo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import facade.UIData;
import store.FlowerMgr;
import store.OrderMgr;
import store.OrderedItemMgr;
import test.Main;

public class GUIMain {
    // 싱글톤 패턴 적용 부분
    private static GUIMain main = null;

    public static GUIMain getInstance() {
        if (main == null)
            main = new GUIMain();
        return main;
    }

    // 엔진의 인스턴스를 편리를 위해 변수에 저장한다
    public static void startGUI() {
        // 이벤트 처리 스레드를 만들고
        // 거기서 GUI를 생성하고 보여준다.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GUIMain.getInstance().createAndShowGUI();
            }
        });
    }

    /**
     * GUI를 생성하여 보여준다. 스레드 안전을 위하여
     * 이 메소드는 이벤트 처리 스레드에서 불려져야 한다.
     */
    static JFrame mainFrame = new JFrame("E조꽃집");

    private void createAndShowGUI() {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 탭을 생성하고 두개 패널을 추가한다.
        JTabbedPane jtab = new JTabbedPane();
        setupmainPane();
        setupfirstPane();
        setuprorderPane();
        setupItemPane();
        setupOrderPane();
        // 아이템 리스트 탭과 주문 탭 두 개의 패널을 가지는 탭 패널
        jtab.add("소비자.첫화면", mainPane);
        jtab.add("소비자.꽃사전", scrollPane);
        jtab.add("소비자.주문", rorderPane);
        jtab.add("관리자.아이템", itemPane);
        jtab.add("관리자.주문", orderPane);
        jtab.setTabPlacement(JTabbedPane.NORTH);
        mainFrame.getContentPane().add(jtab);
        // Display the window.
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private JPanel mainPane;

    private void setupmainPane() {
        mainPane = new JPanel(new BorderLayout());
        ImageIcon background = new ImageIcon("images/main.png");
        background = GUIMain.getInstance().od.resize(background, 1280, 720);
        JLabel label = new JLabel(background);
        mainPane.add(label, BorderLayout.CENTER);
    }

    // 상품을 보여주는 패널 부분 - 탑과 JTable 포함
    private JPanel itemPane;
    TableSelectionDemo itemTable = new TableSelectionDemo();
    ItemTopPanel itemTop = new ItemTopPanel(); // 검색과 상세보기 버튼을 가진 패널

    private void setupItemPane() {
        itemPane = new JPanel(new BorderLayout());
        // Create and set up the content pane.
        itemTable.tableTitle = "item";
        itemTable.addComponentsToPane(FlowerMgr.getInstance()); // 싱글톤
        itemTop.setupTopPane(itemTable);
        itemPane.add(itemTop, BorderLayout.NORTH);
        itemPane.add(itemTable, BorderLayout.CENTER);
    }

    // 상품을 보여주는 패널 부분 - 위에는 주문 JTable, 아래 패널은 장바구니와 버튼
    private JPanel orderPane;
    TableSelectionDemo orderTable = new TableSelectionDemo();
    BasketTableDemo basketTable = new BasketTableDemo();

    private void setupOrderPane() {
        orderPane = new JPanel(new BorderLayout());
        orderTable.tableTitle = "order";
        orderTable.addComponentsToPane(OrderMgr.getInstance());
        orderPane.add(orderTable, BorderLayout.CENTER);
        // 아래쪽은 장바구니 테이블과 라벨로 나누기 위해 패널 추가
        JPanel bottom = new JPanel(); // 디폴트 플로우레이아웃
        basketTable.tableTitle = "basket";
        basketTable.addComponentsToPane(OrderedItemMgr.getInstance());

        bottom.add(basketTable, BorderLayout.CENTER);
        // 여기에 여러 가지 버튼을 넣을 수 있음 - 결재, 취소, 추가, 변경 등
        orderPane.add(bottom, BorderLayout.SOUTH);
    }

    private JPanel firstPane;
    private JScrollPane scrollPane = new JScrollPane();

    private void setupfirstPane() {
        firstPane = new JPanel(new BorderLayout());
        // 현재 꽃 42개-> Layout 14x3으로 설정
        firstPane.setLayout(new GridLayout(14, 3));
        List<?> itemlist = FlowerMgr.getInstance().search("");
        for (Object o : itemlist) {
            firstPane.add(new ImageCell((UIData) o));
        }
        scrollPane = new JScrollPane(firstPane);
    }

    private JPanel rorderPane;
    TableSelectionDemo rorderTable = new TableSelectionDemo();
    ItemTopPanel citemTop = new ItemTopPanel();
    orderDetail od = new orderDetail(null, null, null);
    showCost sc = new showCost(null);
    ItemTopPanel buyingitemTop = new ItemTopPanel();
    public String[] imagePaths = { "images/None.png", "images/None.png", "images/None.png", "images/None.png",
            "images/None.png", "images/None.png", "images/None.png", "images/None.png", "images/None.png", };
    showBuy sb = new showBuy(imagePaths);

    public void setuprorderPane() {
        rorderPane = new JPanel(new BorderLayout());

        // 버튼을 통해 주문창에서 꽃을 검색할 수 있는 곳으로 가는 기능을 넣고 싶었음........
        //
        // JButton search = new JButton("원하는 꽃을 찾아보세요");
        // Font font = new Font("맑은 고딕", Font.BOLD, 12);
        // search.setFont(font);
        // search.addActionListener(new ActionListener() {
        // public void actionPerformed(ActionEvent e) {
        // if (e.getActionCommand().equals("원하는 꽃을 찾아보세요")) {
        // 관리자.아이템 탭으로 가는 기능 구현 못함
        // }
        // }
        // });

        // 지금까지의 꽃 구매 현황 확인
        JPanel west = new JPanel();
        sb = new showBuy(imagePaths);
        west.add(sb, BorderLayout.CENTER);

        // 주문할 상품의 상세정보가 표를 선택할때마다 바뀌어서 출력
        JPanel east = new JPanel();
        ImageIcon icon = null;
        String imgName = "notChosen";
        icon = new ImageIcon("images/" + imgName + ".png");
        if (imgName.equals("notChosen")) {
            icon = GUIMain.getInstance().od.resize(icon, 200, 300);
        } else {
            icon = GUIMain.getInstance().od.resize(icon, 300, 300);
        }
        od = new orderDetail("", "", icon);
        od.setPreferredSize(new Dimension(500, 300));
        east.add(od, BorderLayout.LINE_START);

        // 총가격과 주문할 상품 고르는 곳
        JPanel bottom = new JPanel(new GridBagLayout());
        JPanel buyPane = new JPanel(new BorderLayout());
        JPanel fourpanel = new JPanel(new GridBagLayout());
        JPanel fourpanel2 = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JScrollPane scrollPane = new JScrollPane();
        buyPane.setLayout(new GridLayout(14, 3));
        List<?> itemlist = FlowerMgr.getInstance().search("");
        for (Object o : itemlist) {
            buyPane.add(new buyingImageCell((UIData) o));
        }
        scrollPane = new JScrollPane(buyPane);
        scrollPane.setPreferredSize(new Dimension(500, 800));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        gbc.weighty = 0.2; // Adjusted weighty for buyingitemTop
        bottom.add(buyingitemTop, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.7;
        gbc.weighty = 0.6; // Adjusted weighty for sc
        bottom.add(sc, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.7;
        gbc.weighty = 0.2; // Adjusted weighty for scrollPane
        bottom.add(scrollPane, gbc);

        rorderTable.tableTitle = "find";
        rorderTable.addComponentsToPane(FlowerMgr.getInstance());
        citemTop.setupTopPane(rorderTable);
        // bottom.add(citemTop, BorderLayout.LINE_START);
        // bottom.add(rorderTable, BorderLayout.LINE_END);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        fourpanel2.add(west, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.weighty = 0.1;
        fourpanel.add(bottom, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.NORTH;
        fourpanel2.add(east, gbc);
        west.setPreferredSize(new Dimension(300, 100));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        fourpanel.add(fourpanel2, gbc);

        rorderPane.add(fourpanel, BorderLayout.CENTER);
        // rorderPane.add(west, BorderLayout.WEST);
        // rorderPane.add(bottom, BorderLayout.EAST);
        // rorderPane.add(east, BorderLayout.SOUTH);
    }
}
