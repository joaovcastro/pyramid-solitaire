
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Game {
  public Deck deck;
  public Pyramid pyramid;
  public Pile drawPile;
  public Pile wastePile;
  public Pile wastePile2;
  private Boolean gameOver = false;

  public void cg_init_Game_1() {

    deck = new Deck();
    shuffleDeck();
    pyramid = new Pyramid();
    placeCardsInPyramid();
    drawPile = new Pile(deck);
    wastePile = new Pile();
    wastePile2 = new Pile();
  }

  public Game() {

    cg_init_Game_1();
  }

  public void print() {

	  pyramid.print();
	    //IO.print("\n");
	    System.out.print("\n");
	    System.out.print("Pile 1: ");
	    if (!(Utils.equals(wastePile.getCards().size(), 0L))) {
	      //IO.print("Pile 1: ");
	      
	      //IO.print(wastePile.top());
	      pyramid.printCard(wastePile.top());
	      //IO.print("\n");
	      
	    }
	    System.out.print("\n");
	    System.out.print("Pile 2: ");
	    if (!(Utils.equals(wastePile2.getCards().size(), 0L))) {
	      //IO.print("Pile 2: ");
	      
	      //IO.print(wastePile2.top());
	      pyramid.printCard(wastePile2.top());
	      //IO.print("\n");
	      
	    }
	    System.out.print("\n");
  }

  public void gameCycle() throws IOException {

	  boolean done = false;
	  while(!done) {
		  print();
		  System.out.print("\n");
		  System.out.println("Please input your play");
		  System.out.println("draw - To draw a card");
		  System.out.println("<rank-suite>;<rank-suite>;...;(ex: 9-D;K-C) - To make a play");
		  
		  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		  String input = br.readLine();
		  String[] split1 = input.split(";");
		  
		  if(split1[0].equals("draw")) {
			  drawCard();
			  System.out.println("DRAWING CARD!");
			  break;
		  }
		  else {
			  VDMSeq plays = SeqUtil.seq();
			  for(int i = 0 ; i < split1.length ; i++) {
				  String[] split2 = split1[i].split("-");
				  Play play = new Play(split2[0],split2[1].charAt(0));
				  plays = SeqUtil.conc(Utils.copy(plays), SeqUtil.seq(play));
			  }
			  tryPlay(plays);
		  }
		  
		  
	  }
  }

  public void shuffleDeck() {

    Number newIndex = 1L;
    Card temp = null;
    long toVar_2 = deck.cards.size();

    for (Long i = 1L; i <= toVar_2; i++) {
      temp = ((Card) Utils.get(deck.cards, i));
      newIndex = MATH.rand(52L).longValue() + 1L;
      Utils.mapSeqUpdate(deck.cards, i, ((Card) Utils.get(deck.cards, newIndex)));
      Utils.mapSeqUpdate(deck.cards, newIndex, temp);
    }
  }

  public void drawCard() {

    Card drawnCard = drawPile.draw();
    if (!(Utils.equals(wastePile.getCards().size(), 0L))) {
      Card temp = wastePile.draw();
      wastePile2.push(temp);
    }

    wastePile.push(drawnCard);
  }

  public void pairCards(final Card c1, final Card c2) {

    if (Utils.equals(c1.getScore().longValue() + c2.getScore().longValue(), 13L)) {
      IO.print("pair\n");
    } else {
      IO.print("not pair\n");
    }
  }

  public void printDeck() {

    IO.print("\n");
    long toVar_3 = deck.cards.size();

    for (Long i = 1L; i <= toVar_3; i++) {
      IO.print(((Card) Utils.get(deck.cards, i)).getRank());
      IO.print(" ");
      IO.print(((Card) Utils.get(deck.cards, i)).getSuit());
      IO.print("\n");
    }
  }

  private void placeCardsInPyramid() {

    Boolean whileCond_1 = true;
    while (whileCond_1) {
      whileCond_1 = pyramid.getCards().size() < 28L;
      if (!(whileCond_1)) {
        break;
      }

      {
        Card tempCard = deck.takeCard();
        pyramid.placeCard(tempCard);
      }
    }
  }

  public void tryPlay(final VDMSeq cards) {

    if (Utils.equals(validatePlay(Utils.copy(cards)), true)) {
      makePlay(Utils.copy(cards));
      IO.print("Success");

    } else {
      IO.print("Invalid Play");
    }
  }

  public Boolean validatePlay(final VDMSeq cards) {

    Number total = 0L;
    long toVar_4 = cards.size();

    for (Long i = 1L; i <= toVar_4; i++) {
      Boolean orResult_1 = false;

      if (Utils.equals(
          checkCardPile(((Play) Utils.get(cards, i)).rank, ((Play) Utils.get(cards, i)).suit),
          1L)) {
        orResult_1 = true;
      } else {
        Boolean orResult_2 = false;

        if (Utils.equals(
            checkCardPile(((Play) Utils.get(cards, i)).rank, ((Play) Utils.get(cards, i)).suit),
            2L)) {
          orResult_2 = true;
        } else {
          orResult_2 =
              Utils.equals(
                  pyramid.checkCard(
                      ((Play) Utils.get(cards, i)).rank, ((Play) Utils.get(cards, i)).suit),
                  true);
        }

        orResult_1 = orResult_2;
      }

      if (orResult_1) {
        total =
            total.longValue() + pyramid.convertRank(((Play) Utils.get(cards, i)).rank).longValue();
      }
    }
    if (Utils.equals(total, 13L)) {
      return true;

    } else {
      return false;
    }
  }

  public void makePlay(final VDMSeq cards) {

    long toVar_5 = cards.size();

    for (Long i = 1L; i <= toVar_5; i++) {
      if (Utils.equals(
          pyramid.removeCard(((Play) Utils.get(cards, i)).rank, ((Play) Utils.get(cards, i)).suit),
          false)) {
        if (Utils.equals(
            checkCardPile(((Play) Utils.get(cards, i)).rank, ((Play) Utils.get(cards, i)).suit),
            1L)) {
          wastePile.pop();
        } else if (Utils.equals(
            checkCardPile(((Play) Utils.get(cards, i)).rank, ((Play) Utils.get(cards, i)).suit),
            2L)) {
          wastePile2.pop();
        }
      }
    }
    return;
  }

  private Number checkCardPile(final String rank, final Character suit) {

    Number score = pyramid.convertRank(rank);
    Boolean andResult_8 = false;

    if (!(Utils.equals(wastePile.getCards().size(), 0L))) {
      Boolean andResult_9 = false;

      if (Utils.equals(wastePile.top().getScore(), score)) {
        if (Utils.equals(wastePile.top().getSuit(), suit)) {
          andResult_9 = true;
        }
      }

      if (andResult_9) {
        andResult_8 = true;
      }
    }

    if (andResult_8) {
      return 1L;

    } else {
      Boolean andResult_10 = false;

      if (!(Utils.equals(wastePile2.getCards().size(), 0L))) {
        Boolean andResult_11 = false;

        if (Utils.equals(wastePile2.top().getScore(), score)) {
          if (Utils.equals(wastePile2.top().getSuit(), suit)) {
            andResult_11 = true;
          }
        }

        if (andResult_11) {
          andResult_10 = true;
        }
      }

      if (andResult_10) {
        return 2L;

      } else {
        return 0L;
      }
    }
  }

  public Boolean checkGameOver() {

    if (Utils.equals(pyramid.getPyramidSize(), 0L)) {
      return true;
    }

    return false;
  }

  public String toString() {

    return "Game{"
        + "deck := "
        + Utils.toString(deck)
        + ", pyramid := "
        + Utils.toString(pyramid)
        + ", drawPile := "
        + Utils.toString(drawPile)
        + ", wastePile := "
        + Utils.toString(wastePile)
        + ", wastePile2 := "
        + Utils.toString(wastePile2)
        + ", gameOver := "
        + Utils.toString(gameOver)
        + "}";
  }
  
  public void gameLoop() {
	  
  }
  
  public static void main(String[] args) throws IOException {
		Game g = new Game();
	    //new DeckTest().testTakeCard();
		while(!g.checkGameOver()) {
			g.gameCycle();
		}
		
		
}
  
  
}
