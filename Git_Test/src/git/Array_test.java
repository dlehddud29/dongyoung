package git;

import java.util.Scanner;

public class Array_test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int a[] = {1,2,3,4};//int ����
		String name[] = {"���¿�", "�̵���", "�����","������"};//string ����
		name[3] = "�ƹ���";//Ư�� index�� ����
		
		String change = ""; //�ٸ� ������ �Է��Ͽ� Ư�� index�� ����
		change = "ȫ�浿";
		name[2] = change;
		
		System.out.println("name�� �迭�� ũ��� : " + name.length);//�迭�� ũ��(����) �˾ƺ��� *.length
		System.out.println("a�� �迭�� ũ���  : "+ a.length);
		
		for(int i = 0; i < name.length; i++) {
				System.out.println(a[i]);
				System.out.println(name[i]);
			}
		
		
		//�迭 �Է°� �ֱ�
		int test[] = new int[5];
		int sum = 0;
		int avg = 0;
		for(int i = 0; i < test.length; i++) {
			test[i] = sc.nextInt();
			sum += test[i];
		}
		avg = sum/test.length;
		System.out.println("�հ�� : "+ sum);
		System.out.println("��� : "+ avg);
		
	}
}
