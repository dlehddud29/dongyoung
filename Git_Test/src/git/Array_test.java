package git;

import java.util.Scanner;

public class Array_test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int a[] = {1,2,3,4};//int 선언
		String name[] = {"손태원", "이동영", "장승한","남궁헌"};//string 선언
		name[3] = "아무개";//특정 index값 변경
		
		String change = ""; //다른 변수로 입력하여 특정 index값 변경
		change = "홍길동";
		name[2] = change;
		
		System.out.println("name의 배열의 크기는 : " + name.length);//배열의 크기(길이) 알아보기 *.length
		System.out.println("a의 배열의 크기는  : "+ a.length);
		
		for(int i = 0; i < name.length; i++) {
				System.out.println(a[i]);
				System.out.println(name[i]);
			}
		
		
		//배열 입력값 넣기
		int test[] = new int[5];
		int sum = 0;
		int avg = 0;
		for(int i = 0; i < test.length; i++) {
			test[i] = sc.nextInt();
			sum += test[i];
		}
		avg = sum/test.length;
		System.out.println("합계는 : "+ sum);
		System.out.println("평균 : "+ avg);
		
	}
}
