
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Card {
  private Character rank;
  private Character suit;
  private Number score;

  public void cg_init_Card_2() {

    rank = '0';
    suit = '0';
    score = 0L;
    return;
  }

  public void cg_init_Card_1(final Number sc, final Character su) {

    score = sc;
    suit = su;
    if (Utils.equals(sc, 1L)) {
      rank = 'A';
    } else if (Utils.equals(sc, 11L)) {
      rank = 'J';
    } else if (Utils.equals(sc, 12L)) {
      rank = 'Q';
    } else if (Utils.equals(sc, 13L)) {
      rank = 'K';
    } else {
      rank = 'N';
    }

    return;
  }

  public Card(final Number sc, final Character su) {

    cg_init_Card_1(sc, su);
  }

  public Card() {

    cg_init_Card_2();
  }

  public Number getScore() {

    return score;
  }

  public String getRank() {

    if (Utils.equals(score, 1L)) {
      return "A";

    } else if (Utils.equals(score, 2L)) {
      return "2";

    } else if (Utils.equals(score, 3L)) {
      return "3";

    } else if (Utils.equals(score, 4L)) {
      return "4";

    } else if (Utils.equals(score, 5L)) {
      return "5";

    } else if (Utils.equals(score, 6L)) {
      return "6";

    } else if (Utils.equals(score, 7L)) {
      return "7";

    } else if (Utils.equals(score, 8L)) {
      return "8";

    } else if (Utils.equals(score, 9L)) {
      return "9";

    } else if (Utils.equals(score, 10L)) {
      return "10";

    } else if (Utils.equals(score, 11L)) {
      return "J";

    } else if (Utils.equals(score, 12L)) {
      return "Q";

    } else {
      return "K";
    }
  }

  public Character getSuit() {

    return suit;
  }

  public String toString() {

    return "Card{"
        + "rank := "
        + Utils.toString(rank)
        + ", suit := "
        + Utils.toString(suit)
        + ", score := "
        + Utils.toString(score)
        + "}";
  }
}
