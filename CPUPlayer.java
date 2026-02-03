import java.util.ArrayList;
import java.util.HashMap;

// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
class CPUPlayer
{

    // Contient le nombre de noeuds visités (le nombre
    // d'appel à la fonction MinMax ou Alpha Beta)
    // Normalement, la variable devrait être incrémentée
    // au début de votre MinMax ou Alpha Beta.
    private int numExploredNodes;

    private Mark mark;
    private Mark otherMark;

    // Le constructeur reçoit en paramètre le
    // joueur MAX (X ou O)
    public CPUPlayer(Mark cpu){
        if(cpu == Mark.X){
            mark = cpu;
            otherMark = Mark.O;
        }else{
            mark = Mark.O;
            otherMark = Mark.X;
        }
    }

    // Ne pas changer cette méthode
    public int  getNumOfExploredNodes(){
        return numExploredNodes;
    }

    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveMinMax(Board board)
    {
        numExploredNodes = 0;
        int maxScore = -101;

        ArrayList<Move> bestNextMoves = new ArrayList<Move>();

        for (Move move : board.getPossibleMoves()) {
            Board tempBoard = new Board(board);
            tempBoard.play(move,mark);
            int score  = minMax(tempBoard,"MIN");

            if(score > maxScore){
                maxScore = score;
                bestNextMoves.clear();
                bestNextMoves.add(move);
            }else if (score == maxScore){
                bestNextMoves.add(move);
            }
        }
        return bestNextMoves;
    }

    public int minMax(Board board, String direction){
        int evaluation = board.evaluate(mark);
        if(!board.hasSpace() || evaluation==100 || evaluation==-100){
            return evaluation;
        }
        
        if (direction.equals("MAX")){
            int maxScore = -101;
            for (Move possibleMove : board.getPossibleMoves()) {
                Board tempBoard = new Board(board);
                tempBoard.play(possibleMove, mark);
                int score = minMax(tempBoard,"MIN");
                maxScore = Math.max(maxScore, score);
            }
            return maxScore;
        } else{
            int minScore = 101;
            for (Move possibleMove : board.getPossibleMoves()) {
                Board tempBoard = new Board(board);
                tempBoard.play(possibleMove, otherMark);
                int score = minMax(tempBoard,"MAX");
                minScore = Math.min(minScore, score);
            }
            return minScore;
        }
    }

    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveAB(Board board){
        numExploredNodes = 0;

        //todo
        return new ArrayList<>();
    }

}
