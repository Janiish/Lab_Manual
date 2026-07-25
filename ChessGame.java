import java.util.Scanner;

// --- Spot Class ---
class Spot {
    private int x; // Row index (0-7)
    private int y; // Column index (0-7)
    private Piece piece;

    public Spot(int x, int y, Piece piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public Piece getPiece() { return piece; }
    public void setPiece(Piece piece) { this.piece = piece; }
}

// --- Abstract Piece Class ---
abstract class Piece {
    private boolean white;
    private boolean killed = false;

    public Piece(boolean white) {
        this.white = white;
    }

    public boolean isWhite() { return white; }
    public boolean isKilled() { return killed; }
    public void setKilled(boolean killed) { this.killed = killed; }

    public abstract boolean canMove(Board board, Spot start, Spot end);
    public abstract String getSymbol();
}

// --- Concrete Piece Subclasses ---
class Pawn extends Piece {
    public Pawn(boolean white) { super(white); }

    @Override
    public String getSymbol() { return isWhite() ? "P" : "p"; }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }
        int direction = isWhite() ? -1 : 1; // White moves up (towards row 0), Black moves down
        int startRow = isWhite() ? 6 : 1;

        int dx = end.getX() - start.getX();
        int dy = Math.abs(end.getY() - start.getY());

        // Standard 1-step forward move
        if (dy == 0 && dx == direction && end.getPiece() == null) {
            return true;
        }
        // Initial 2-step forward move
        if (dy == 0 && start.getX() == startRow && dx == 2 * direction && end.getPiece() == null) {
            int midX = start.getX() + direction;
            if (board.getSpot(midX, start.getY()).getPiece() == null) {
                return true;
            }
        }
        // Diagonal capture
        if (dy == 1 && dx == direction && end.getPiece() != null && end.getPiece().isWhite() != this.isWhite()) {
            return true;
        }

        return false;
    }
}

class Rook extends Piece {
    public Rook(boolean white) { super(white); }

    @Override
    public String getSymbol() { return isWhite() ? "R" : "r"; }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) return false;
        if (start.getX() != end.getX() && start.getY() != end.getY()) return false;

        int stepX = Integer.compare(end.getX(), start.getX());
        int stepY = Integer.compare(end.getY(), start.getY());
        int currX = start.getX() + stepX;
        int currY = start.getY() + stepY;

        while (currX != end.getX() || currY != end.getY()) {
            if (board.getSpot(currX, currY).getPiece() != null) return false;
            currX += stepX;
            currY += stepY;
        }
        return true;
    }
}

class Knight extends Piece {
    public Knight(boolean white) { super(white); }

    @Override
    public String getSymbol() { return isWhite() ? "N" : "n"; }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) return false;
        int dx = Math.abs(start.getX() - end.getX());
        int dy = Math.abs(start.getY() - end.getY());
        return dx * dy == 2;
    }
}

class Bishop extends Piece {
    public Bishop(boolean white) { super(white); }

    @Override
    public String getSymbol() { return isWhite() ? "B" : "b"; }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) return false;
        int dx = Math.abs(start.getX() - end.getX());
        int dy = Math.abs(start.getY() - end.getY());
        if (dx != dy) return false;

        int stepX = Integer.compare(end.getX(), start.getX());
        int stepY = Integer.compare(end.getY(), start.getY());
        int currX = start.getX() + stepX;
        int currY = start.getY() + stepY;

        while (currX != end.getX() || currY != end.getY()) {
            if (board.getSpot(currX, currY).getPiece() != null) return false;
            currX += stepX;
            currY += stepY;
        }
        return true;
    }
}

class Queen extends Piece {
    public Queen(boolean white) { super(white); }

    @Override
    public String getSymbol() { return isWhite() ? "Q" : "q"; }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) return false;
        int dx = Math.abs(start.getX() - end.getX());
        int dy = Math.abs(start.getY() - end.getY());
        if (dx != dy && start.getX() != end.getX() && start.getY() != end.getY()) return false;

        int stepX = Integer.compare(end.getX(), start.getX());
        int stepY = Integer.compare(end.getY(), start.getY());
        int currX = start.getX() + stepX;
        int currY = start.getY() + stepY;

        while (currX != end.getX() || currY != end.getY()) {
            if (board.getSpot(currX, currY).getPiece() != null) return false;
            currX += stepX;
            currY += stepY;
        }
        return true;
    }
}

class King extends Piece {
    public King(boolean white) { super(white); }

    @Override
    public String getSymbol() { return isWhite() ? "K" : "k"; }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (end.getPiece() != null && end.getPiece().isWhite() == this.isWhite()) return false;
        int dx = Math.abs(start.getX() - end.getX());
        int dy = Math.abs(start.getY() - end.getY());
        return (dx <= 1 && dy <= 1);
    }
}

// --- Board Class ---
class Board {
    private Spot[][] boxes = new Spot[8][8];

    public Board() {
        resetBoard();
    }

    public Spot getSpot(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) return null;
        return boxes[x][y];
    }

    public void resetBoard() {
        // Black pieces at top (rows 0 and 1)
        boxes[0][0] = new Spot(0, 0, new Rook(false));
        boxes[0][1] = new Spot(0, 1, new Knight(false));
        boxes[0][2] = new Spot(0, 2, new Bishop(false));
        boxes[0][3] = new Spot(0, 3, new Queen(false));
        boxes[0][4] = new Spot(0, 4, new King(false));
        boxes[0][5] = new Spot(0, 5, new Bishop(false));
        boxes[0][6] = new Spot(0, 6, new Knight(false));
        boxes[0][7] = new Spot(0, 7, new Rook(false));

        for (int i = 0; i < 8; i++) {
            boxes[1][i] = new Spot(1, i, new Pawn(false));
        }

        // Empty slots
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                boxes[i][j] = new Spot(i, j, null);
            }
        }

        // White pieces at bottom (rows 6 and 7)
        for (int i = 0; i < 8; i++) {
            boxes[6][i] = new Spot(6, i, new Pawn(true));
        }

        boxes[7][0] = new Spot(7, 0, new Rook(true));
        boxes[7][1] = new Spot(7, 1, new Knight(true));
        boxes[7][2] = new Spot(7, 2, new Bishop(true));
        boxes[7][3] = new Spot(7, 3, new Queen(true));
        boxes[7][4] = new Spot(7, 4, new King(true));
        boxes[7][5] = new Spot(7, 5, new Bishop(true));
        boxes[7][6] = new Spot(7, 6, new Knight(true));
        boxes[7][7] = new Spot(7, 7, new Rook(true));
    }

    public void displayBoard() {
        for (int i = 0; i < 8; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < 8; j++) {
                Piece p = boxes[i][j].getPiece();
                if (p == null) {
                    System.out.print(". ");
                } else {
                    System.out.print(p.getSymbol() + " ");
                }
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }
}

// --- Main Game Class ---
public class ChessGame {
    private Board board;
    private boolean whiteTurn;

    public ChessGame() {
        board = new Board();
        whiteTurn = true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChessGame game = new ChessGame();

        System.out.println("Chess Game (Console Version)");
        System.out.println("Initial Board Setup:");
        game.board.displayBoard();

        while (true) {
            System.out.println("\n" + (game.whiteTurn ? "White's turn." : "Black's turn."));
            System.out.print("Enter move (e.g., e2 e4) or 'exit' to quit: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Game terminated.");
                break;
            }

            String[] parts = input.split("\\s+");
            if (parts.length != 2) {
                System.out.println("Invalid format! Please enter moves like 'e2 e4'.");
                continue;
            }

            String startStr = parts[0];
            String endStr = parts[1];

            Spot startSpot = parseSpot(game.board, startStr);
            Spot endSpot = parseSpot(game.board, endStr);

            if (startSpot == null || endSpot == null) {
                System.out.println("Invalid coordinates! Use columns a-h and rows 1-8.");
                continue;
            }

            Piece p = startSpot.getPiece();
            if (p == null) {
                System.out.println("Error: No piece at start position " + startStr + ".");
                continue;
            }

            if (p.isWhite() != game.whiteTurn) {
                System.out.println("Error: It is " + (game.whiteTurn ? "White's" : "Black's") + " turn!");
                continue;
            }

            if (!p.canMove(game.board, startSpot, endSpot)) {
                System.out.println("Error: Invalid move for " + getPieceName(p) + " from " + startStr + " to " + endStr + ".");
                continue;
            }

            // Execute Move
            Piece destPiece = endSpot.getPiece();
            if (destPiece != null) {
                destPiece.setKilled(true);
            }

            endSpot.setPiece(p);
            startSpot.setPiece(null);

            System.out.println(getPieceName(p) + " moved from " + startStr + " to " + endStr + ".");

            // Display updated board
            game.board.displayBoard();

            // Win condition (King captured)
            if (destPiece instanceof King) {
                System.out.println("Checkmate!");
                System.out.println((game.whiteTurn ? "White" : "Black") + " wins the game.");
                break;
            }

            // Switch player turn
            game.whiteTurn = !game.whiteTurn;
        }

        scanner.close();
    }

    private static Spot parseSpot(Board board, String coord) {
        if (coord.length() != 2) return null;
        char colChar = Character.toLowerCase(coord.charAt(0));
        char rowChar = coord.charAt(1);

        if (colChar < 'a' || colChar > 'h' || rowChar < '1' || rowChar > '8') {
            return null;
        }

        int col = colChar - 'a';
        int row = 8 - (rowChar - '0');
        return board.getSpot(row, col);
    }

    private static String getPieceName(Piece p) {
        if (p instanceof Pawn) return "Pawn";
        if (p instanceof Rook) return "Rook";
        if (p instanceof Knight) return "Knight";
        if (p instanceof Bishop) return "Bishop";
        if (p instanceof Queen) return "Queen";
        if (p instanceof King) return "King";
        return "Piece";
    }
}
