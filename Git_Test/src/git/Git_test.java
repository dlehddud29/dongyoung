package git;

import java.util.Scanner;

public class Git_test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		int money = 0;	
		boolean end = true;
		
		while(end) {
			System.out.println("------------------------");
			System.out.println("1.예금|2.출금|3.잔고|4.종료");
			System.out.println("------------------------");
			System.out.print("선택 > ");
			int input = sc.nextInt();
			if(input == 4) {
				end = false;
				System.out.print("프로그램이 종료되었습니다.");
			}
			else if (input == 1){
				System.out.print("예금액>");
				int sum = sc.nextInt();
				money = money + sum;
				System.out.println("현재잔액은"+money+"입니다");
			}
			else if (input == 2) {
				System.out.print("출금액 >");
				int sub = sc.nextInt();
				if(money < sub) {
					System.out.println("잔고가 부족합니다.");
					System.out.println("현재잔액은"+money+"입니다");
				}else {
				money = money - sub;
				System.out.println("현재잔액은"+money+"입니다");
				}
			}
			else if(input ==3) {
				System.out.println("현재잔액은"+money+"입니다");
			}	
		}
	}
}
