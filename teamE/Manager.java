//본 파일은 계획서 제출용 입력,출력,검색 기능만 있는 파일입니다.

package teamE;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
	ArrayList<Manageable> mList = new ArrayList<>();

	Scanner openFile(String filename) {
		Scanner filein = null;
		try {
			filein = new Scanner(new File(filename));
		} catch (IOException e) {
			System.out.println("파일 입력 오류");
			System.exit(0);
		}
		return filein;
	}

	public void readAll(String filename, Factory fac) {
		Scanner filein = openFile(filename);
		Manageable m = null;
		while (filein.hasNext()) {
			m = fac.create();
			m.read(filein);
			mList.add(m);
		}
		filein.close();
	}

	public void printAll() {
		for (Manageable m : mList) {
			m.print();
		}
	}

	public void search(Scanner scan) {
		String name = null;
		while (true) {
			System.out.print("end를 입력하면 종료됩니다.\n키워드: ");
			name = scan.next();
			if (name.equals("end"))
				break;
			for (Manageable m : mList) {
				if (m.matches(name))
					m.print();
			}
			for (int i = 0; i < 40; i++)
				System.out.print("-");
			System.out.println();
		}
	}

	public Manageable find(String kwd) {
		for (Manageable m : mList) {
			if (m.matches(kwd))
				return m;
		}
		return null;
	}
}