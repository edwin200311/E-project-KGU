//본 파일은 계획서 제출용 입력,출력,검색 기능만 있는 파일입니다.

package teamE;

import java.util.Scanner;

public interface Manageable {

	public void read(Scanner scan);

	public void print();

	public boolean matches(String kwd);

}