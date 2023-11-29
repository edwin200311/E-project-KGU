package table_demo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Font;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import facade.IDataEngine;
import facade.UIData;

/*
 * 테이블을 포함한 패널로 테이블과 테이블모델을 생성하여 초기화하고(init) 
 * 매니저에서 데이터를 받아와 테이블 행을 추가하는 기능(loadData)과
 * 행이 선택되었을 때 필요한 일을 하는 기능(valueChanged)를 가진다.
 */
public class TableSelectionDemo extends JPanel implements ListSelectionListener {
	private static final long serialVersionUID = 1L;
	JTable table;
	DefaultTableModel tableModel;
	int selectedIndex = -1; // 테이블에서 선택된 행의 인덱스를 가질 변수
	String tableTitle = null;
	IDataEngine<?> dataMgr; // 엔진 쪽의 데이터를 관리하는 매니저 파사드 인터페이스

	public TableSelectionDemo() {
		super(new BorderLayout());
	}

	// 테이블을 생성하여 초기화하고 스크롤 붙여서 패널에 add한다
	void addComponentsToPane(IDataEngine<?> mgr) {
		init(mgr);
		JScrollPane center = new JScrollPane(table);
		add(center, BorderLayout.CENTER);
	}

	// 테이블의 기본 설정을 하는 부분 (테이블 모델을 생성하고 초기 데이터 불러오고
	// 테이블에 필요한 설정을 초기화한다
	// 장바구니 테이블은 오버라이드하여 가져올 주문번호를 세팅한 후 수퍼 호출
	@SuppressWarnings("serial")
	void init(IDataEngine<?> mgr) {
		dataMgr = mgr;
		tableModel = new DefaultTableModel(mgr.getColumnNames(), 0) {
			// 셀 수정 못하게 하는 부분
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		loadData("");

		table = new JTable(tableModel);
		ListSelectionModel rowSM = table.getSelectionModel();
		rowSM.addListSelectionListener(this);
		table.setPreferredScrollableViewportSize(new Dimension(500, 220));
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	// 매니저에서 검색된 객체들을 테이블에 보여준다. kwd가 ""면 모두 출력
	void loadData(String kwd) {
		List<?> result = dataMgr.search(kwd); // 매니저에서 검색결과를 가져옴
		tableModel.setRowCount(0); // 현재 데이터모델의 행을 모두 지운다
		for (Object m : result) // 한 행씩 추가함
			tableModel.addRow(((UIData) m).getUiTexts());
	}

	// 아이템 패널에서 상세보기 버튼을 눌렀을 때 실행되는 메소드
	void showDetail() {
		if (selectedIndex < 0)
			return;
		String[] rowTexts = new String[tableModel.getColumnCount()];
		for (int i = 0; i < rowTexts.length; i++)
			rowTexts[i] = (String) tableModel.getValueAt(selectedIndex, i);
		DetailDialog dlg = new DetailDialog(rowTexts);
		dlg.setup();
		dlg.pack();
		dlg.setVisible(true);
	}

	// 선택된 행이 변경되면 그 내용을 편집창으로 보냄
	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		if (!lsm.isSelectionEmpty()) {
			selectedIndex = lsm.getMinSelectionIndex();
			String name = (String) tableModel.getValueAt(selectedIndex, 0);
			String language = (String) tableModel.getValueAt(selectedIndex, 1);
			String flowering = (String) tableModel.getValueAt(selectedIndex, 2);
			String color = (String) tableModel.getValueAt(selectedIndex, 3);
			String cost = (String) tableModel.getValueAt(selectedIndex, 4);
			String care = (String) tableModel.getValueAt(selectedIndex, 5);
			String icon = null;
			ImageIcon imgicon = null;
			Image image = null;
			// 아이템 테이블의 클릭은 텍스트 필드에 값을 보여주고
			// 주문 테이블의 클릭은 장바구니의 값을 바꾼다
			if (tableTitle.equals("item")) {
				GUIMain.getInstance().itemTop.kwdTextField.setText(name);
			} else if (tableTitle.equals("order")) {
				GUIMain.getInstance().basketTable.loadData("" + selectedIndex);
			} else if (tableTitle.equals("find")) {
				GUIMain.getInstance().citemTop.kwdTextField.setText(name);
				Font font = new Font("SansSerif", Font.PLAIN, 16); // 원하는 폰트, 스타일, 크기 설정
				GUIMain.getInstance().od.textArea.setFont(font);
				GUIMain.getInstance().od.textArea.setText("이름: " + name + "\n" + "꽃말: " + language + "\n" + "개화시기: "
						+ flowering + "\n" + "색상: " + color + "\n" + "관리방법: " + cost + "\n" + "가격: " + care+"원");
				icon = "images/" + name + ".png";
				imgicon = new ImageIcon(icon);
				image = imgicon.getImage();
				GUIMain.getInstance().od.changeImg(imgicon);
				
			}
		}
	}
}
