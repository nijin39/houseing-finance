package com.tandem6.housingfinance.account.application;

public class Sorting {

    public void sort() {

        //[1] 입력
        int[] data = {7, 5, 6, 1, 9};

        //[2] 처리
        //Arrays.sort(data); //이미 만들어진 API를 이용하면 쉽게 정렬할수있음.

        //Selection Sort
        int temp = 0; //데이터 Swap용 임시 변수

        for (int i = 0; i < data.length - 1; i++) {
            for (int j = i + 1; j < data.length; j++) {
                if (data[i] > data[j]) { //오름차순 ; 큰수를 뒤로, data[i] > data[j]
                    //내림차순 ; 큰수를 앞으로, data[i] < data[j]
                    //데이터 값 체인지
                    temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                }
            }
            ShowArray(data); //현재 i번째 데이터 출력
        }

        //[3] 출력
        System.out.println("====== 정렬 결과 ======");
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + "  "); // 1, 5, 6, 7, 9 출력되도록
        }

    }

    public int foo(int a) {
        int b = 12;
        if (a == 1) {
            return b;
        }
        return b;  // Noncompliant
    }

    //회전시마다 배열상태를 보기위한 메서드
    private static void ShowArray(int[] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + "  ");
        }
        System.out.println();
    }
}
