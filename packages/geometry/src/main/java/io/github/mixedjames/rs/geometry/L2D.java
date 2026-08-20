package io.github.mixedjames.rs.geometry;

public final class L2D {

  private float a_;
  private float b_;
  private float c_;

  public L2D(float a, float b, float c) {

    if (!Float.isFinite(a) || !Float.isFinite(b) || !Float.isFinite(c)) {
      throw new IllegalArgumentException("All parameters must be finite values");
    }

    this.a_ = a;
    this.b_ = b;
    this.c_ = c;
  }

  public static L2D createFromPoints(V2D p1, V2D p2) {
    float a = p2.getY() - p1.getY();
    float b = p1.getX() - p2.getX();
    float c = (p2.getX() * p1.getY()) - (p1.getX() * p2.getY());
    return new L2D(a, b, c);
  }

  public static L2D createHorizontal(float y) {
    return new L2D(0, 1, -y);
  }

  public static L2D createVertical(float x) {
    return new L2D(1, 0, -x);
  }

  public float getA() {
    return a_;
  }

  public float getB() {
    return b_;
  }

  public float getC() {
    return c_;
  }

  public float evaluate(V2D point) {
    return (a_ * point.getX()) + (b_ * point.getY()) + c_;
  }

  @Override
  public boolean equals(Object o) {
    /* We handle a few situations here that I note because I hadn't thought about them when
      I first wrote this.

      'o' might be...
      1. Self (would work without special handling, but this is a fast path)
      2. Null
      3. Not a L2D
      4. A L2D

      If it's a L2D then we compare the a, b, and c values. We use Float.compare to handle NaN and
      -0.0 vs 0.0 correctly.
    */

    if (this == o) {
      return true;
    }

    if (o instanceof L2D) {
      L2D l = (L2D) o;
      return Float.compare(l.a_, a_) == 0
          && Float.compare(l.b_, b_) == 0
          && Float.compare(l.c_, c_) == 0;
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    int result = Float.hashCode(a_);
    result = 31 * result + Float.hashCode(b_);
    result = 31 * result + Float.hashCode(c_);
    return result;
  }

  @Override
  public String toString() {
    return String.format("L2D(a=%.2f, b=%.2f, c=%.2f)", a_, b_, c_);
  }
}
