package edu.grinnell.csc207.util;

import static edu.grinnell.csc207.util.MatrixAssertions.assertMatrixEquals;
import static edu.grinnell.csc207.util.MatrixAssertions.assertFigure;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

/**
 * A variety of tests for the Matrix class.
 *
 * @author Cade Johnston
 */
public class TestsByStudent {
  /**
   * Checks that Matrices can be created, values can be set, and values can be gotten.
   */
  @Test
  public void johnstonCadeTest1() {
    Matrix<String> matA = new MatrixV0<String>(3, 3);
    assertMatrixEquals(new String[][] {{null, null, null}, {null, null, null}, {null, null, null}}, matA,
        "three-by-three of null");
    String[][] vals = new String[][] {{"A","B","C"},{"D","E","F"},{"G","H","I"}};
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        matA.set(r, c, vals[r][c]);
        assertEquals(vals[r][c], matA.get(r,c));
      } // for [c]
    } // for [r]
  } // johnstonCadeTest1()

  /**
   * Checks that Matrices can be created, added to, and subtracted from.
   */
  @Test
  public void johnstonCadeTest2() {
    Matrix<String> matA = new MatrixV0<String>(2, 2);
    assertMatrixEquals(new String[][] {{null, null}, {null, null}}, matA,
        "two-by-two of null");
    matA.insertCol(0);
    matA.insertRow(0);
    assertMatrixEquals(new String[][] {{null, null, null}, {null, null, null}, {null, null, null}}, matA,
        "three-by-three of null");
    matA.deleteCol(1);
    matA.deleteRow(1);
    assertMatrixEquals(new String[][] {{null, null}, {null, null}}, matA,
        "two-by-two of null");
    try {
      matA.insertCol(1, new String[]{"X", "X"});
      matA.insertRow(1, new String[]{"X", "X", "X"});
    } catch (ArraySizeException e) {
      fail("Parameter lengths were correct yet an error was thrown.");
    }
    assertMatrixEquals(new String[][] {{null, "X", null}, {"X", "X", "X"}, {null, "X", null}}, matA,
        "three-by-three of X with null corners");
    matA.deleteCol(2);
    matA.deleteRow(2);
    assertMatrixEquals(new String[][] {{null, "X"}, {"X", "X"}}, matA,
        "two-by-two of X with null top left corner");
  } // johnstonCadeTest2()

  /**
   * Checks that Matrices can be created, cloned
   * (non-shallowly for reference, ambiguously for values),
   * and compared.
   */
  @Test
  public void johnstonCadeTest3() {
    Matrix<String> matA = new MatrixV0<String>(2, 2, "a");
    Matrix<String> matB = matA.clone();
    assertMatrixEquals(new String[][] {{"a", "a"}, {"a", "a"}}, matA,
        "two-by-two of a");
    assertMatrixEquals(new String[][] {{"a", "a"}, {"a", "a"}}, matB,
        "two-by-two of a");
    assertEquals(matA, matB);
    matA.insertCol(0);
    matB.insertRow(0);
    assertMatrixEquals(new String[][] {{"a", "a", "a"}, {"a", "a", "a"}}, matA,
        "two-by-three of a");
    assertMatrixEquals(new String[][] {{"a", "a"}, {"a", "a"}, {"a", "a"}}, matB,
        "three-by-two of a");
    assertNotEquals(matA, matB);
  } // johnstonCadeTest3()

  /**
   * Tests what occurs with an empty matrix.
   */
  @Test
  public void johnstonCadeEdge1() {
    Matrix<String> matZA = new MatrixV0<String>(0, 0);
    Matrix<String> matZB = new MatrixV0<String>(0, 0);
    assertFigure("+\n",matZA, "Output of 0x0 matrix");
    try {
      matZA.insertRow(1);
      fail("Should have been out of range.");
    } catch (Exception e) {
      // Continue with tests
    }
    try {
      matZB.insertCol(1);
      fail("Should have been out of range.");
    } catch (Exception e) {
      // Continue with tests
    }
    matZA.insertRow(0);
    assertFigure("+\n|\n+\n",matZA, "Output of 1x0 matrix");
    matZB.insertCol(0);
    assertFigure("+--+\n",matZB, "Output of 0x1 matrix");
    try {
      matZA.insertRow(1, new String[]{});
    } catch (Exception e) {
      fail("Correct parameter length (0 Strings) resulted in error.");
    }
    try {
      matZB.insertCol(1,new String[]{});
    } catch (Exception e) {
      fail("Correct parameter length (0 Strings) resulted in error.");
    }
    try {
      matZA.insertCol(0, new String[]{});
      fail("Incorrect parameter (0 Strings) should have resulted in error.");
    } catch (Exception e) {
      // Continue with tests
    }
    try {
      matZB.insertRow(0, new String[]{});
      fail("Incorrect parameter (0 Strings) should have resulted in error.");
    } catch (Exception e) {
      // Continue with tests
    }
    try {
      matZA.insertCol(0, new String[]{"",""});
      matZA.insertCol(0, new String[]{"",""});
    } catch (Exception e) {
      fail("Correct parameter length (2 Strings) resulted in error.");
    }
    try {
      matZB.insertRow(0,new String[]{"",""});
      matZB.insertRow(0,new String[]{"",""});
    } catch (Exception e) {
      fail("Correct parameter length (2 Strings) resulted in error.");
    }
    assertEquals(matZA, matZB);
  } // johnstonCadeEdge1()
} // TestsByStudent
