
import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Pyramid {
  public VDMSeq cards;
  private Number numRows;

  public void cg_init_Pyramid_1() {

    cards = SeqUtil.seq();
    numRows = 7L;
    return;
  }

  public Pyramid() {

    cg_init_Pyramid_1();
  }

  public void placeCard(final Card c) {

    cards = SeqUtil.conc(Utils.copy(cards), SeqUtil.seq(c));
  }

  public VDMSeq getCards() {

    return Utils.copy(cards);
  }

  public void deleteCard(final Number index) {

    Utils.mapSeqUpdate(cards, index, new Card());
  }

  public void print() {

	  Number index = 1L;
	    //IO.print("\n");
	    System.out.print("\n");
	    long toVar_7 = numRows.longValue();

	    for (Long i = 1L; i <= toVar_7; i++) {
	      long toVar_6 = i.longValue();
	      for (Long k = 1L; k <= toVar_7 - toVar_6  ; k++) {
	    	  System.out.print("    ");
	      }

	      for (Long j = 1L; j <= toVar_6; j++) {
	        if (((Card) Utils.get(cards, index)).getScore().longValue() > 0L) {
//	          IO.print(((Card) Utils.get(cards, index)).getRank());
//	          IO.print(((Card) Utils.get(cards, index)).getSuit());
	        	printCard((Card) Utils.get(cards, index));
	          //IO.print("        ");
	          System.out.print("    ");
	          index = index.longValue() + 1L;

	        } else {
	          //IO.print(" ");
	          //IO.print("   ");
	          //IO.print("        ");
	        	System.out.print("    ");
	        	System.out.print("    ");
	        	index = index.longValue() + 1L;
	        }
	      }
	      //IO.print("\n");
	      System.out.print("\n\n");
	    }
  }

  public Boolean checkCard(final String rank, final Character suit) {

    Number index = 1L;
    Number row = 1L;
    Number col = 0L;
    Boolean found = false;
    Card tempCard = null;
    Number score = convertRank(rank);
    long toVar_9 = numRows.longValue();

    for (Long i = 1L; i <= toVar_9; i++) {
      long toVar_8 = i.longValue();

      for (Long j = 1L; j <= toVar_8; j++) {
        Boolean andResult_16 = false;

        if (Utils.equals(((Card) Utils.get(cards, index)).getScore(), score)) {
          if (Utils.equals(((Card) Utils.get(cards, index)).getSuit(), suit)) {
            andResult_16 = true;
          }
        }

        if (andResult_16) {
          tempCard = ((Card) Utils.get(cards, index));
          row = i;
          col = j;
          if (checkValidCard(row, col)) {
            found = true;
          }
        }

        index = index.longValue() + 1L;
      }
    }
    return found;
  }

  public Boolean removeCard(final String rank, final Character suit) {

    Number index = 1L;
    Number score = convertRank(rank);
    long toVar_11 = numRows.longValue();

    for (Long i = 1L; i <= toVar_11; i++) {
      long toVar_10 = i.longValue();

      for (Long j = 1L; j <= toVar_10; j++) {
        Boolean andResult_18 = false;

        if (Utils.equals(((Card) Utils.get(cards, index)).getScore(), score)) {
          if (Utils.equals(((Card) Utils.get(cards, index)).getSuit(), suit)) {
            andResult_18 = true;
          }
        }

        if (andResult_18) {
          Utils.mapSeqUpdate(cards, index, new Card());
          return true;
        }

        index = index.longValue() + 1L;
      }
    }
    return false;
  }

  public Boolean checkValidCard(final Number row, final Number col) {

    if (Utils.equals(row, 7L)) {
      return true;

    } else {
      Boolean andResult_22 = false;

      if (Utils.equals(checkCardAtCoordinate(row.longValue() + 1L, col), false)) {
        if (Utils.equals(
            checkCardAtCoordinate(row.longValue() + 1L, col.longValue() + 1L), false)) {
          andResult_22 = true;
        }
      }

      if (andResult_22) {
        return true;

      } else {
        return false;
      }
    }
  }

  public Boolean checkCardAtCoordinate(final Number row, final Number col) {

    Number index = getCardIndex(row, col);
    if (Utils.equals(((Card) Utils.get(cards, index)).getScore(), 0L)) {
      return false;
    }

    return true;
  }

  public Number getCardIndex(final Number row, final Number col) {

    Number index = 1L;
    long toVar_13 = col.longValue();

    for (Long i = 1L; i <= toVar_13; i++) {
      long toVar_12 = i.longValue();

      for (Long j = 1L; j <= toVar_12; j++) {
        index = index.longValue() + 1L;
        Boolean andResult_30 = false;

        if (Utils.equals(i, row)) {
          if (Utils.equals(j, col)) {
            andResult_30 = true;
          }
        }

        if (andResult_30) {
          return index;
        }
      }
    }
    return index;
  }

  public Number convertRank(final String rnk) {

    if (Utils.equals(rnk, "A")) {
      return 1L;

    } else if (Utils.equals(rnk, "2")) {
      return 2L;

    } else if (Utils.equals(rnk, "3")) {
      return 3L;

    } else if (Utils.equals(rnk, "4")) {
      return 4L;

    } else if (Utils.equals(rnk, "5")) {
      return 5L;

    } else if (Utils.equals(rnk, "6")) {
      return 6L;

    } else if (Utils.equals(rnk, "7")) {
      return 7L;

    } else if (Utils.equals(rnk, "8")) {
      return 8L;

    } else if (Utils.equals(rnk, "9")) {
      return 9L;

    } else if (Utils.equals(rnk, "10")) {
      return 10L;

    } else if (Utils.equals(rnk, "J")) {
      return 11L;

    } else if (Utils.equals(rnk, "Q")) {
      return 12L;

    } else {
      return 13L;
    }
  }

  public Number getPyramidSize() {

    Number index = 1L;
    Number count = 0L;
    long toVar_15 = numRows.longValue();

    for (Long i = 1L; i <= toVar_15; i++) {
      long toVar_14 = i.longValue();

      for (Long j = 1L; j <= toVar_14; j++) {
        if (((Card) Utils.get(cards, index)).getScore().longValue() > 0L) {
          count = count.longValue() + 1L;
        }

        index = index.longValue() + 1L;
      }
    }
    return count;
  }

  public String toString() {

    return "Pyramid{"
        + "cards := "
        + Utils.toString(cards)
        + ", numRows := "
        + Utils.toString(numRows)
        + "}";
  }
  
  public static void printCard(Card c) {
	  
	  String s = c.getRank() + "-" + c.getSuit();
	  if(!c.getRank().equals("10"))
		  System.out.print(" " + s);
	  else
	  System.out.print(s);
  }
}
