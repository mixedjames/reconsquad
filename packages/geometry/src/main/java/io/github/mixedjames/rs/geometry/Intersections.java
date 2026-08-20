package io.github.mixedjames.rs.geometry;

public final class Intersections {

  private Intersections() {
    // Private constructor to prevent instantiation
  }

  public static boolean lineLine(L2D l1, L2D l2, V2D intersection) {

    // https://math.stackexchange.com/questions/25171/intersection-of-two-lines-in-2d

    float a1 = l1.getA();
    float b1 = l1.getB();
    float c1 = l1.getC();

    float a2 = l2.getA();
    float b2 = l2.getB();
    float c2 = l2.getC();

    float determinant = a1 * b2 - a2 * b1;

    if (determinant == 0) {
      return false;
    }

    intersection.set((c1 * b2 - b1 * c2) / determinant, (a1 * c2 - c1 * a2) / determinant);

    return true;
  }
}
