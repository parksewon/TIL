
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_Boj_1303_전쟁전투 {
    static int N, M; // 전쟁터의 가로, 세로 크기
    static int whiteTeam = 0, blueTeam = 0; // 우리팀 병사의 위력의 합, 적국의 병사의 위력의 합
    static char [][] map; // 전쟁터
    static boolean [][] visited; // 방문여부 체크
    static int [] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우 (행)
    static int [] dy = {0, 0, -1, 1}; // 상, 하, 좌, 우 (열)

    static void bfs(int i, int j, char color){ // bfs 사용해서 같은 팀의 병사들이 몇명 뭉쳐있는지 계산하는 함수
        Queue<Pos> queue = new LinkedList<>();
        visited[i][j] = true;
        queue.add(new Pos(i, j));
        int cnt = 1; // 몇명 뭉쳐있는지 세는 변수

        while(!queue.isEmpty()){
            Pos temp = queue.poll();

            for(int idx = 0; idx < 4; idx++){
                int newR = temp.r + dx[idx];
                int newC = temp.c + dy[idx];

                if(!valid(newR, newC) || visited[newR][newC]) continue;
                if(map[newR][newC] != color) continue;

                visited[newR][newC] = true;
                queue.add(new Pos(newR, newC));
                cnt++; // 같은 팀의 병사가 모여있을때 cnt 증가
            }
        }
        if(color == 'W'){ // 만약 우리팀 병사라면
            whiteTeam += cnt * cnt; // 우리팀 병사의 위력의 합을 cnt 제곱만큼 증가
        }
        else { // 적국 병사라면
            blueTeam += cnt * cnt; // 적국의 병사의 위력의 합을 cnt 제곱만큼 증가
        }
    }

    static boolean valid(int r, int c){ // 병사들이 인접한 것을 판단하는 사방탐색에서 배열의 범위가 유효한지 검사하는 함수
        return (0 <= r && 0 <= c && r < M && c < N);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[M][N];
        visited = new boolean[M][N];

        for(int i = 0; i < M; i++){
            String s = br.readLine();
            for(int j = 0; j < N; j++){
                map[i][j] = s.charAt(j);
            }
        }

        for(int i = 0; i < M; i++){
            for(int j = 0; j < N; j++){
                if(!visited[i][j]) bfs(i, j, map[i][j]);
            }
        }

        System.out.println(whiteTeam + " " + blueTeam);


    }

    static class Pos{  // 위치 클래스
        int r, c;

        Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

}
