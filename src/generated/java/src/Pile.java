
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Pile {
  public VDMSeq cards = SeqUtil.seq();
  public Number numCards;

  public void cg_init_Pile_2(final Deck deck) {

    cards = deck.cards;
    return;
  }

  public void cg_init_Pile_1() {

    numCards = 0L;
    return;
  }

  public Pile() {

    cg_init_Pile_1();
  }

  public Pile(final Deck deck) {

    cg_init_Pile_2(deck);
  }

  public void push(final Card x) {

    cards = SeqUtil.conc(SeqUtil.seq(x), Utils.copy(cards));
    numCards = numCards.longValue() + 1L;
  }

  public void pop() {

    cards = SeqUtil.tail(Utils.copy(cards));
  }

  public Card top() {

    return ((Card) cards.get(0));
  }

  public Card draw() {

    Card drawn = ((Card) cards.get(0));
    cards = SeqUtil.tail(Utils.copy(cards));
    return drawn;
  }

  public VDMSeq getCards() {

    return Utils.copy(cards);
  }

  public String toString() {

    return "Pile{"
        + "cards := "
        + Utils.toString(cards)
        + ", numCards := "
        + Utils.toString(numCards)
        + "}";
  }
}
