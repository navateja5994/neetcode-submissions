class Solution {

    public void islandsAndTreasure(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[][] queue = new int[rows * cols][2];
        int front = 0;
        int rear = 0;

        // Add all treasure chests (0)
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 0) {
                    queue[rear][0] = r;
                    queue[rear][1] = c;
                    rear++;
                }
            }
        }

        int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };

        // Multi-source BFS
        while (front < rear) {
            int r = queue[front][0];
            int c = queue[front][1];
            front++;

            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr < 0 || nr >= rows ||
                    nc < 0 || nc >= cols) {
                    continue;
                }

                // Water
                if (grid[nr][nc] == -1) {
                    continue;
                }

                // Already processed
                if (grid[nr][nc] != Integer.MAX_VALUE) {
                    continue;
                }

                grid[nr][nc] = grid[r][c] + 1;

                queue[rear][0] = nr;
                queue[rear][1] = nc;
                rear++;
            }
        }
    }
}