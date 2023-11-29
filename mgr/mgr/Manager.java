package mgr;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import store.flower;

public class Manager<T extends Manageable> {
	public ArrayList<T> mList = new ArrayList<>();
	public T find(String kwd) {
	    for (T m: mList)
	    	if (m.matches(kwd))
	    		return m;
	    return null;
	}
	public void readAll(String filename, Factory<T> fac) {
		Scanner filein = openFile(filename);
		T m = null;
		while (filein.hasNext()) {
			m = fac.create();
			m.read(filein);
			mList.add(m);
		}
		filein.close();
	}

	public void printAll() {
		for (T m : mList) {
			m.print();
		}
	}
	public void search(Scanner keyScanner) {
		String kwd = null;
		while (true) {
			System.out.print(">> ");
			System.out.print("end를 입력하면 종료됩니다.\n키워드: ");
			kwd = keyScanner.next();
			if (kwd.equals("end"))
				break;
			for (T m : mList) {
				if (m.matches(kwd))
					m.print();
			}
		}
	}
	public List<T> findAll(String kwd) {
		List<T> result = new ArrayList<>();
		for (T m : mList) {
			if (m.matches(kwd))
				result.add(m);
		}
		return result;
	}

	public Scanner openFile(String filename) {
		Scanner filein = null;
		try {
			filein = new Scanner(new File(filename));
		} catch (Exception e) {
			System.out.println(filename + ": 파일 없음");
			System.exit(0);
		}
		return filein;
	}
	public void addElement(T e) {
		mList.add(e);
	}
	
	public void Save(String Savefile) {
		Scanner scan = new Scanner(System.in);
		ArrayList<String> input = new ArrayList<>();
		try {
			FileWriter writer = new FileWriter(Savefile, true);
			System.out.printf("ID를 입력하세요: ");
			String id = scan.next();
			flower ynflower = null;
			System.out.printf("(예: 장미 튤립 0)\n구매하고 싶은 꽃을 적어주세요: ");
			while (true) {
				String flowername = scan.next();
				if (flowername.equals("0"))
					break;
				try {
					ynflower=(store.flower) store.FlowerMgr.getInstance().find(flowername);
					//System.out.println(ynflower.getname());
				} catch (Exception e1) {
					if (ynflower == null) {
						System.out.println("저희 가게에 없는 상품입니다!");
						for(int i=0;i<40;i++) System.out.print("*");
						System.out.println();
						break;
					}
				}
				input.add(flowername);
			}
			if (input.size() > 1) {
				writer.write("\n" + id + " ");
				for (String fn : input) {
					writer.write(fn + " ");
				}
				writer.write("0");
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}




}
