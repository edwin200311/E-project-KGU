//본 파일은 계획서 제출용 입력,출력,검색 기능만 있는 파일입니다.

package teamE;

import java.util.Scanner;

public class Store {
	Scanner scan = new Scanner(System.in);
	static Manager flowerMgr = new Manager();
	static Manager userMgr = new Manager();

	void run() {
		flowerMgr.readAll("flower.txt", new Factory() {
			public Manageable create() {
				return new Flower();
			}			
		});
		userMgr.readAll("user.txt", new Factory() {
			public Manageable create() {
				return new User();
			}
		});
		menu();
	}
	
	void menu() {
		int num;
		while (true) {
			System.out.printf("(1)꽃 정보 출력 (2)사용자 정보 출력 (3)검색 (기타)종료\n입력: ");
			num = scan.nextInt();
			if (num < 1 || num >3)
				break;
			switch (num) {
			case 1: flowerMgr.printAll(); break;
			case 2: userMgr.printAll(); break;
			case 3: searchMenu(); break;
			default: break;
			}
		}
	}

	void searchMenu() {
		int num;
		while (true) {
			System.out.printf("(1)꽃 검색 (2)사용자 검색 (기타)이전으로\n입력: ");
			num = scan.nextInt();
			if (num < 1 || num >2)
				break;
			switch (num) {
			case 1: flowerMgr.search(scan); break;
			case 2: userMgr.search(scan); break;
			default: break;
			}
		}
	}

	public static void main(String args[]) {
		Store my = new Store();
		my.run();
	}
}