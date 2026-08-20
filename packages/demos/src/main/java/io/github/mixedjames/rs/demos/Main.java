package io.github.mixedjames.rs.demos;

import io.github.mixedjames.rs.geometry.*;

public final class Main {
  public static void main(String[] args) {
    L2D l1 = L2D.createFromPoints(new V2D(1, 2), new V2D(3, 4));
    L2D l2 = L2D.createFromPoints(new V2D(5, 6), new V2D(7, -8));

    V2D intersection = V2D.createZero();

    if (Intersections.lineLine(l1, l2, intersection)) {
      System.out.println("Intersection: " + intersection);
    } else {
      System.out.println("No intersection");
    }
  }
}
