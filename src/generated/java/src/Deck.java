
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Deck {
  public VDMSeq cards;
  public Card cardTemp;

  public void cg_init_Deck_1() {

    cards = SeqUtil.seq();
    long toVar_1 = 13L;

    for (Long i = 1L; i <= toVar_1; i++) {
      cardTemp = new Card(i, 'S');
      cards = SeqUtil.conc(Utils.copy(cards), SeqUtil.seq(cardTemp));
      cardTemp = new Card(i, 'C');
      cards = SeqUtil.conc(Utils.copy(cards), SeqUtil.seq(cardTemp));
      cardTemp = new Card(i, 'H');
      cards = SeqUtil.conc(Utils.copy(cards), SeqUtil.seq(cardTemp));
      cardTemp = new Card(i, 'D');
      cards = SeqUtil.conc(Utils.copy(cards), SeqUtil.seq(cardTemp));
    }
    return;
  }

  public Deck() {

    cg_init_Deck_1();
  }

  public Card takeCard() {

    Card drawn = ((Card) cards.get(0));
    cards = SeqUtil.tail(Utils.copy(cards));
    return drawn;
  }

  public void printDeck() {

    IO.print(Utils.copy(cards));
  }

  public String toString() {

    return "Deck{"
        + "cards := "
        + Utils.toString(cards)
        + ", cardTemp := "
        + Utils.toString(cardTemp)
        + "}";
  }
}
