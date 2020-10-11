package action;

import java.util.Scanner;

import service.RegistService;
import util.ConsoleUtil;

public class RegistUnderClothAction implements Action {

	@Override
	public void excute(Scanner sc) throws Exception {
	
		ConsoleUtil consoleUtil = new ConsoleUtil();
		// 객체 생성
		System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
		System.out.println(" 다음중 가지고 계신 하의의 이름을 적어주세요. \n그만 입력하고 싶을시 '끝'을 입력하세요.");
		consoleUtil.printUnderClothList();


		sc.nextLine();
		boolean run = true;
		while (run) {
			System.out.print(">>  ");
			String name = sc.nextLine();
			if (name.equals("끝")) {
				run = false;
			} else {
				RegistService rs = new RegistService();

				boolean insertSuccess = rs.insertMyUnderCloth(name); // 옷이 잘 저장됐는지
				if (insertSuccess) {
					consoleUtil.printRegisSuccessMessage();
				} else {
					consoleUtil.printRegisFailMessage();
				} // end if(insertSuccess)
			} // end if(name)
		} // end while(run)
	}
}
