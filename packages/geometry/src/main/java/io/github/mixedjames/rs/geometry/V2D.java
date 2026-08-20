package io.github.mixedjames.rs.geometry;

/**
 * V2D - 2d vector
 *
 * <p>Supports: - Basic arithmetic operations - Vector normalization - Dot product - Angle between
 * vectors
 */
public final class V2D {

  private float x_;
  private float y_;

  public V2D(float x, float y) {

    if (!Float.isFinite(x) || !Float.isFinite(y)) {
      throw new IllegalArgumentException("All parameters must be finite values");
    }

    this.x_ = x;
    this.y_ = y;
  }

  public static V2D createCopy(V2D other) {
    return new V2D(other.x_, other.y_);
  }

  public static V2D createZero() {
    return new V2D(0, 0);
  }

  public float getX() {
    return x_;
  }

  public float getY() {
    return y_;
  }

  public void setX(float x) {
    if (!Float.isFinite(x)) {
      throw new IllegalArgumentException("x must be finite");
    }

    this.x_ = x;
  }

  public void setY(float y) {
    if (!Float.isFinite(y)) {
      throw new IllegalArgumentException("y must be finite");
    }

    this.y_ = y;
  }

  public void set(float x, float y) {

    if (!Float.isFinite(x) || !Float.isFinite(y)) {
      throw new IllegalArgumentException("x and y must be finite");
    }

    this.x_ = x;
    this.y_ = y;
  }

  public V2D addSet(V2D other) {
    this.x_ += other.x_;
    this.y_ += other.y_;
    return this;
  }

  public static V2D add(V2D v1, V2D v2) {
    return new V2D(v1.x_ + v2.x_, v1.y_ + v2.y_);
  }

  public V2D subtractSet(V2D other) {
    this.x_ -= other.x_;
    this.y_ -= other.y_;
    return this;
  }

  public static V2D subtract(V2D v1, V2D v2) {
    return new V2D(v1.x_ - v2.x_, v1.y_ - v2.y_);
  }

  public V2D scaleSet(float scalar) {

    if (!Float.isFinite(scalar)) {
      throw new IllegalArgumentException("scalar must be finite");
    }

    this.x_ *= scalar;
    this.y_ *= scalar;
    return this;
  }

  public static V2D scale(V2D v, float scalar) {
    return new V2D(v.x_ * scalar, v.y_ * scalar);
  }

  public float dot(V2D other) {
    return (this.x_ * other.x_) + (this.y_ * other.y_);
  }

  public V2D perpendicular() {
    return new V2D(-this.y_, this.x_);
  }

  public float lengthSquared() {
    return (this.x_ * this.x_) + (this.y_ * this.y_);
  }

  public float length() {
    return (float) Math.sqrt((this.x_ * this.x_) + (this.y_ * this.y_));
  }

  public V2D normalizeSet() {
    float l = this.length();

    if (l == 0) {
      throw new IllegalStateException("Cannot normalize a zero vector");
    }

    this.x_ /= l;
    this.y_ /= l;

    return this;
  }

  public static V2D normalize(V2D v) {
    float l = v.length();

    if (l == 0) {
      throw new IllegalStateException("Cannot normalize a zero vector");
    }

    return new V2D(v.x_ / l, v.y_ / l);
  }

  public V2D invertSet() {
    this.x_ = -this.x_;
    this.y_ = -this.y_;
    return this;
  }

  public static V2D invert(V2D v) {
    return new V2D(-v.x_, -v.y_);
  }

  @Override
  public boolean equals(Object o) {

    /* We handle a few situations here that I note because I hadn't thought about them when
      I first wrote this.

      'o' might be...
      1. Self (would work without special handling, but this is a fast path)
      2. Null
      3. Not a V2D
      4. A V2D

      If it's a V2D then we compare the x and y values. We use Float.compare to handle NaN and
      -0.0 vs 0.0 correctly.
    */

    if (this == o) {
      return true;
    }

    if (o instanceof V2D) {
      V2D v = (V2D) o;
      return Float.compare(v.x_, x_) == 0 && Float.compare(v.y_, y_) == 0;
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    int result = Float.hashCode(x_);
    result = 31 * result + Float.hashCode(y_);
    return result;
  }

  @Override
  public String toString() {
    return "V2D(" + x_ + ", " + y_ + ")";
  }
}
