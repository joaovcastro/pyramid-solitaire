
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class DeckTest {
  private Deck deck = new Deck();
  private Card takenCard = new Card();

  private void assertTrue(final Boolean cond) {

    return;
  }

  private void testTakeCard() {

    assertTrue(Utils.equals(deck.cards.size(), 52L));
    takenCard = deck.takeCard();
    assertTrue(Utils.equals(deck.cards.size(), 51L));
  }

  public static void main(String[] args) {
	Game g = new Game();
    //new DeckTest().testTakeCard();
  }

  public DeckTest() {}

  public String toString() {

    return "DeckTest{"
        + "deck := "
        + Utils.toString(deck)
        + ", takenCard := "
        + Utils.toString(takenCard)
        + "}";
  }
}
