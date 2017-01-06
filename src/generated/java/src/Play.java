
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Play {
  public String rank;
  public Character suit;

  public void cg_init_Play_1(final String rnk, final Character su) {

    rank = rnk;
    suit = su;
    return;
  }

  public Play(final String rnk, final Character su) {

    cg_init_Play_1(rnk, su);
  }

  public Play() {}

  public String toString() {

    return "Play{" + "rank := " + Utils.toString(rank) + ", suit := " + Utils.toString(suit) + "}";
  }
}
