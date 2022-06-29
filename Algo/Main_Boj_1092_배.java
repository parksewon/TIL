import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_Boj_1092_배 {
    static int N, M, time = 0; // 크레인 수, 박스 수, 시간의 최솟값
    static ArrayList<Integer> crane = new ArrayList<>(); // 각 크레인의 무게 제한
    static ArrayList<Integer> box = new ArrayList<>(); // 각 박스의 무게

    static void getTime(){ // 모든 박스를 배로 옮기는데 드는 시간의 최솟값을 구하는 함수
        while(!box.isEmpty()){ // 모든 박스를 옮길 때까지 == 박스 리스트가 비어있을 때까지 반복
            for(int i = crane.size() -1; i >= 0; i--){ // crane의 무게 제한이 큰 것부터 비교
                for(int j = box.size() -1; j >= 0; j--) { // box의 무게가 큰 것부터 비교
                    if (crane.get(i) >= box.get(j)) { // 크레인으로 박스를 옮길 수 있다면 box를 옮겼다고 판단하고 리스트에서 제거
                        box.remove(j);
                        break;
                    }
                }
            }
            time++; // 1분 동안 옮길 수 있는 모든 박스를 옮겼다면 다음 화물을 옮길 수 있도록 시간의 흐름을 표시
        }
    }

    static void removeCrane(){ // 아무런 박스도 옮기지 못하는 (작동하지 않는) 크레인을 제거하는 함수
        for(int i = crane.size()-1; i >= 0; i--){
            if(box.get(0) > crane.get(i)) crane.remove(i); // 박스의 최솟값보다 무게 제한이 작은 크레인은 리스트에서 제거한다.
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            crane.add(Integer.parseInt(st.nextToken()));
        }

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            box.add(Integer.parseInt(st.nextToken()));
        }

        // crane과 box 리스트를 오름차순으로 정렬
        Collections.sort(crane);
        Collections.sort(box);

        // 만약 모든 박스를 배로 옮길 수 없다면 -1 출력
        if(crane.get(crane.size()-1) < box.get(box.size()-1)) System.out.println(-1);
        else{
            // 가능하다면 움직이지 않는/필요없는 크레인은 먼저 제거한 뒤에 시간의 최솟값을 구한다.
            removeCrane();
            getTime();
            System.out.println(time);
        }

        br.close();
    }
}
/*
크레인 1대는 1분에 한대씩 이동 가능
3 => 크레인 수
6 8 9 => 크레인 무게 제한
5 => 박스 수
2 2 4 5 7 => 박스 무게

6 8 9
2 2 4 5 7
6 => 2 4
8 => 2 5
9 => 7


10

17 20 20
5
15 15 17 18 18

2 5 5 5 7 7 11 17 20 20
15 15 17 18 18

3
6 8 9
5
2 5 2 4 10

 */