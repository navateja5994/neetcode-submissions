class Solution {

    int ROWS, COLS;

    int[][] directions = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };

    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        ROWS = heights.length;
        COLS = heights[0].length;

        boolean[][] pacific = new boolean[ROWS][COLS];
        boolean[][] atlantic = new boolean[ROWS][COLS];

        // Pacific Ocean
        for (int c = 0; c < COLS; c++) {
            dfs(heights, 0, c, pacific);
        }

        for (int r = 0; r < ROWS; r++) {
            dfs(heights, r, 0, pacific);
        }

        // Atlantic Ocean
        for (int c = 0; c < COLS; c++) {
            dfs(heights, ROWS - 1, c, atlantic);
        }

        for (int r = 0; r < ROWS; r++) {
            dfs(heights, r, COLS - 1, atlantic);
        }

        List<List<Integer>> result = new ArrayList<>();

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {

                if (pacific[r][c] && atlantic[r][c]) {
                    result.add(Arrays.asList(r, c));
                }
            }
        }

        return result;
    }

    private void dfs(int[][] heights, int r, int c,
                     boolean[][] visited) {

        if (r < 0 || c < 0 ||
            r >= ROWS || c >= COLS ||
            visited[r][c]) {
            return;
        }

        visited[r][c] = true;

        for (int[] dir : directions) {

            int nr = r + dir[0];
            int nc = c + dir[1];

            if (nr >= 0 && nr < ROWS &&
                nc >= 0 && nc < COLS &&
                heights[nr][nc] >= heights[r][c]) {

                dfs(heights, nr, nc, visited);
            }
        }
    }
}