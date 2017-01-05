
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
    gameCycle();
  }

  public Game() {

    cg_init_Game_1();
  }

  public void print() {

    pyramid.print();
    IO.print("\n");
    if (!(Utils.equals(wastePile.getCards().size(), 0L))) {
      IO.print("Pile 1: ");
      IO.print(wastePile.top());
      IO.print("\n");
    }

    if (!(Utils.equals(wastePile2.getCards().size(), 0L))) {
      IO.print("Pile 2: ");
      IO.print(wastePile2.top());
      IO.print("\n");
    }
  }

  public void gameCycle() {

    {
      print();
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

  public Card makePlay(final String rank, final Character suit) {

    Number score = pyramid.convertRank(rank);
    Boolean orResult_1 = false;

    if (Utils.equals(checkCardPile(rank, suit), true)) {
      orResult_1 = true;
    } else {
      orResult_1 = Utils.equals(pyramid.selectCard(rank, suit), true);
    }

    if (orResult_1) {
      return new Card(score, suit);

    } else {
      return new Card();
    }
  }

  private Boolean checkCardPile(final String rank, final Character suit) {

    Number score = pyramid.convertRank(rank);
    Boolean andResult_9 = false;

    if (!(Utils.equals(wastePile.getCards().size(), 0L))) {
      Boolean andResult_10 = false;

      if (Utils.equals(wastePile.top().getScore(), score)) {
        if (Utils.equals(wastePile.top().getSuit(), suit)) {
          andResult_10 = true;
        }
      }

      if (andResult_10) {
        andResult_9 = true;
      }
    }

    if (andResult_9) {
      wastePile.pop();
      return true;

    } else {
      Boolean andResult_11 = false;

      if (!(Utils.equals(wastePile2.getCards().size(), 0L))) {
        Boolean andResult_12 = false;

        if (Utils.equals(wastePile2.top().getScore(), score)) {
          if (Utils.equals(wastePile2.top().getSuit(), suit)) {
            andResult_12 = true;
          }
        }

        if (andResult_12) {
          andResult_11 = true;
        }
      }

      if (andResult_11) {
        wastePile2.pop();
        return true;

      } else {
        return false;
      }
    }
  }

  public Boolean validatePlay(final Card card1) {

    if (Utils.equals(card1.getScore(), 13L)) {
      return true;

    } else {
      return false;
    }
  }

  public Boolean validatePlay(final Card card1, final Card card2) {

    if (Utils.equals(card1.getScore().longValue() + card2.getScore().longValue(), 13L)) {
      return true;

    } else {
      return false;
    }
  }

  public Boolean validatePlay(final Card card1, final Card card2, final Card card3) {

    if (Utils.equals(
        card1.getScore().longValue() + card2.getScore().longValue() + card3.getScore().longValue(),
        13L)) {
      return true;

    } else {
      return false;
    }
  }

  public Boolean validatePlay(
      final Card card1, final Card card2, final Card card3, final Card card4) {

    if (Utils.equals(
        card1.getScore().longValue()
            + card2.getScore().longValue()
            + card3.getScore().longValue()
            + card4.getScore().longValue(),
        13L)) {
      return true;

    } else {
      return false;
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
  
  
  
}
