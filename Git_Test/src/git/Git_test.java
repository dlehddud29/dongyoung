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
			System.out.println("1.����|2.���|3.�ܰ�|4.����");
			System.out.println("------------------------");
			System.out.print("���� > ");
			int input = sc.nextInt();
			if(input == 4) {
				end = false;
				System.out.print("���α׷��� ����Ǿ����ϴ�.");
			}
			else if (input == 1){
				System.out.print("���ݾ�>");
				int sum = sc.nextInt();
				money = money + sum;
				System.out.println("�����ܾ���"+money+"�Դϴ�");
			}
			else if (input == 2) {
				System.out.print("��ݾ� >");
				int sub = sc.nextInt();
				if(money < sub) {
					System.out.println("�ܰ� �����մϴ�.");
					System.out.println("�����ܾ���"+money+"�Դϴ�");
				}else {
				money = money - sub;
				System.out.println("�����ܾ���"+money+"�Դϴ�");
				}
			}
			else if(input ==3) {
				System.out.println("�����ܾ���"+money+"�Դϴ�");
			}	
		}
	}
}
