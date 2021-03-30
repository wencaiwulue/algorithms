package question;

/**
 * @author fengcaiwen
 * @since 7/1/2019
 */
public class EightQueen {
    private Grid[][] sheet;
    private int queen;

    public EightQueen(int n, int queen) {
        this.sheet = new Grid[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.sheet[i][j] = new Grid(false, true);
            }
        }
        this.queen = queen;
        init();
    }

    public static class Grid {
        private boolean isQueen;
        private boolean available;

        public Grid(boolean isQueen, boolean available) {
            this.isQueen = isQueen;
            this.available = available;
        }
    }


    public void init() {

    }


}
