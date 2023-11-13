//본 파일은 계획서 제출용 입력,출력,검색 기능만 있는 파일입니다.

package teamE;

import java.util.ArrayList;
import java.util.Scanner;


public class User implements Manageable {

	String id;
	ArrayList<Flower> registeredFlower = new ArrayList<>();

	@Override
	public void read(Scanner scan) {
		id = scan.next();
		String flowername;
		Flower Flower = null;
		while (true) {
			flowername = scan.next();
			if (flowername.equals("0"))
				break;
			Flower = (Flower) Store.flowerMgr.find(flowername);
			if (Flower == null) {
				System.out.print("null: " + flowername);
				return;
			}
			registeredFlower.add(Flower);
		}
	}

	@Override
	public void print() {
		int costAll = 0;
		System.out.printf("%s: ", id);
		for (Flower myflower : registeredFlower) {
			System.out.printf("%s ", myflower.getname());
			costAll += myflower.getcost();
		}
		System.out.printf("(총: %d원)", costAll);
		System.out.println();
	}

	@Override
	public boolean matches(String kwd) {
		ArrayList<String> flowernameList = new ArrayList<>();
		for (Flower myflower : registeredFlower) {
			flowernameList.add(myflower.getname());
		}
		if (id.contentEquals(kwd))
			return true;
		if (flowernameList.contains(kwd))
			return true;
		return false;
	}
}