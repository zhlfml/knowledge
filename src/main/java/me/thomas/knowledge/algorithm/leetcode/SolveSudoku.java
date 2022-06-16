/**
 * LY.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package me.thomas.knowledge.algorithm.leetcode;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * SolveSudoku：思路不正确，错误在从备选数中剔除某个数容易，但是恢复一个数就难了。因为假设可以恢复数字1时，是不是那个让1剔除的数将1恢复的。
 * 正确的思路还是应该每次再填充数字时，判断这个数是否可以填。
 *
 * @author xinsheng2.zhao
 * @version Id: SolveSudoku.java, v 0.1 3/13/22 7:16 PM xinsheng2.zhao Exp $
 */
public class SolveSudoku {

    private char[][] answer = {{'5','3','4','6','7','8','9','1','2'},
        {'6','7','2','1','9','5','3','4','8'},{'1','9','8','3','4','2','5','6','7'},{'8','5','9','7','6','1','4','2','3'},
        {'4','2','6','8','5','3','7','9','1'},{'7','1','3','9','2','4','8','5','6'},{'9','6','1','5','3','7','2','8','4'},
        {'2','8','7','4','1','9','6','3','5'},{'3','4','5','2','8','6','1','7','9'}};

    public void solveSudoku(char[][] board) {
        PriorityQueue<Puzzle> puzzles = new PriorityQueue<>(Comparator.comparingInt(puzzle -> puzzle.candidates));
        Map<Position, Puzzle> puzzleMap = new HashMap<>(81);
        this.init(board, puzzles, puzzleMap);
        this.solveSudoku(board, puzzles, puzzleMap);
    }

    /**
     * 初始化操作：计算每个空白格子的候选数字
     */
    private void init(char[][] board, PriorityQueue<Puzzle> puzzles, Map<Position, Puzzle> puzzleMap) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.') {
                    Puzzle puzzle = new Puzzle(i, j);
                    this.findCandidateNumbers(board, puzzle);
                    puzzles.add(puzzle);
                    puzzleMap.put(puzzle.position, puzzle);
                }
            }
        }
    }

    /**
     * 使用回溯算法解决所有难题，解题难度由易到难。
     */
    private boolean solveSudoku(char[][] board, PriorityQueue<Puzzle> puzzles, Map<Position, Puzzle> puzzleMap) {
        if (puzzles.isEmpty()) {
            System.exit(0);
            return true;
        }
        if (puzzles.peek().candidateNumbers.isEmpty()) {
            return false;
        }
        Puzzle puzzle = puzzles.poll();
        puzzle.candidateNumbers.forEach(number -> {
            System.out.println("[" + puzzle.position.row + ", " + puzzle.position.column + "]");
            board[puzzle.position.row][puzzle.position.column] = (char) (number + '0');

            printBoard(board, puzzle.position.row, puzzle.position.column);
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            // debug puzzle.position.row == 0 && puzzle.position.column == 2
            tryCandidateNumber(board, puzzle, number, puzzleMap);
            boolean resolved = solveSudoku(board, puzzles, puzzleMap);
            if (!resolved) {
                // 尝试下一个候选数前需要先恢复当前状态
                board[puzzle.position.row][puzzle.position.column] = '.';
                recoverCandidateNumber(board, puzzle, number, puzzleMap);
            }
        });
        puzzles.add(puzzle);
        return false;
    }

    private void printBoard(char[][] board, int row, int column) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (row == i && column == j) {
                    //System.out.print("[");
                }
                if (board[i][j] != '.' && board[i][j] != answer[i][j]) {
                    System.out.print( board[i][j]);
                } else {
                    System.out.print(board[i][j]);
                }
                if (row == i && column == j) {
                    //System.out.print("]");
                }
                System.out.print("  ");
                if (j == 8) {
                    System.out.print("\n");
                }
            }
        }
        System.out.println("#####################################");
    }

    /**
     * 排除难题所在行，列，宫内已出现的数字。
     */
    private void findCandidateNumbers(char[][] board, Puzzle puzzle) {
        // 所在行
        for (int j = 0; j < 9; j++) {
            if (board[puzzle.position.row][j] != '.') {
                puzzle.removeCandidateNumber(board[puzzle.position.row][j] - '0');
            }
        }
        // 所在列
        for (int i = 0; i < 9; i++) {
            if (board[i][puzzle.position.column] != '.') {
                puzzle.removeCandidateNumber(board[i][puzzle.position.column] - '0');
            }
        }
        // 所在宫
        int baseRow = puzzle.position.row / 3 * 3,
                baseColumn = puzzle.position.column / 3 * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[baseRow + i][baseColumn + j] != '.') {
                    puzzle.removeCandidateNumber(board[baseRow + i][baseColumn + j] - '0');
                }
            }
        }
        // 保存快照
        puzzle.saveSnapshot();
    }

    /**
     * 猜测难题的答案，同步更新相关难题的候选项。
     */
    private void tryCandidateNumber(char[][] board, Puzzle puzzle, int number, Map<Position, Puzzle> puzzleMap) {
        // 所在行
        for (int j = 0; j < 9; j++) {
            if (board[puzzle.position.row][j] == '.') {
                puzzleMap.get(Position.of(puzzle.position.row, j)).removeCandidateNumber(number);
            }
        }
        // 所在列
        for (int i = 0; i < 9; i++) {
            if (board[i][puzzle.position.column] == '.') {
                puzzleMap.get(Position.of(i, puzzle.position.column)).removeCandidateNumber(number);
            }
        }
        // 所在宫
        int baseRow = puzzle.position.row / 3 * 3,
                baseColumn = puzzle.position.column / 3 * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[baseRow + i][baseColumn + j] == '.') {
                    puzzleMap.get(Position.of(baseRow + i, baseColumn + j)).removeCandidateNumber(number);
                }
            }
        }
    }

    /**
     * 当前数尝试失败，恢复相关难题的候选数。
     */
    private void recoverCandidateNumber(char[][] board, Puzzle puzzle, int number, Map<Position, Puzzle> puzzleMap) {
        // 所在行
        for (int j = 0; j < 9; j++) {
            if (board[puzzle.position.row][j] == '.') {
                puzzleMap.get(Position.of(puzzle.position.row, j)).addCandidateNumber(number);
            }
        }
        // 所在列
        for (int i = 0; i < 9; i++) {
            if (board[i][puzzle.position.column] == '.') {
                puzzleMap.get(Position.of(i, puzzle.position.column)).addCandidateNumber(number);
            }
        }
        // 所在宫
        int baseRow = puzzle.position.row / 3 * 3,
                baseColumn = puzzle.position.column / 3 * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[baseRow + i][baseColumn + j] == '.') {
                    puzzleMap.get(Position.of(baseRow + i, baseColumn + j)).addCandidateNumber(number);
                }
            }
        }
    }

    /**
     * 空白格子所在位置
     */
    private static class Position {
        /**
         * 格子所在行
         */
        private int row;
        /**
         * 格子所在列
         */
        private int column;

        private Position(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public static Position of(int row, int column) {
            return new Position(row, column);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Position)) return false;
            Position position = (Position) o;
            return row == position.row &&
                    column == position.column;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column);
        }
    }

    /**
     * 每一个空白格子都是一个难题
     */
    private static class Puzzle {
        /**
         * 空白格子所在位置
         */
        private Position position;
        /**
         * 候选数的数量
         */
        private int candidates;
        /**
         * 候选数字列表，按照数字从小到大排序
         */
        private Set<Integer> candidateNumbers;
        /**
         * 初始化后确定下来的候选项的快照
         */
        private Set<Integer> candidateNumbersSnapshot;

        public Puzzle(int row, int column) {
            this.position = new Position(row, column);
            this.candidates = 9;
            this.candidateNumbers = new TreeSet<>();
            for (int number = 1; number <= 9; number++) {
                this.candidateNumbers.add(number);
            }
        }

        /**
         * 移除候选数字
         */
        public void removeCandidateNumber(int number) {
            if (this.candidateNumbers.remove(number)) {
                this.candidates--;
            }
        }

        /**
         * 增加候选数字，但确保不能增加初始化后不能有的候选数
         */
        public void addCandidateNumber(int number) {
            if (this.candidateNumbersSnapshot.contains(number) && this.candidateNumbers.add(number)) {
                this.candidates++;
            }
        }

        /**
         * 保存初始化后的快照，不能更新
         */
        public void saveSnapshot() {
            this.candidateNumbersSnapshot = Collections.unmodifiableSet(new HashSet<>(this.candidateNumbers));
        }
    }

}
