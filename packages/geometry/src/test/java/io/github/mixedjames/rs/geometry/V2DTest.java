package io.github.mixedjames.rs.geometry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class V2DTest {

  @Test
  public void testCreation() {
    V2D v = new V2D(1, 2);
    assertEquals(1, v.getX());
    assertEquals(2, v.getY());

    V2D copy = V2D.createCopy(v);
    assertEquals(1, copy.getX());
    assertEquals(2, copy.getY());

    V2D zero = V2D.createZero();
    assertEquals(0, zero.getX());
    assertEquals(0, zero.getY());
  }

  @Test
  public void testSetters() {
    V2D v = new V2D(1, 2);
    v.setX(3);
    assertEquals(3, v.getX());
    assertEquals(2, v.getY());

    v.setY(4);
    assertEquals(3, v.getX());
    assertEquals(4, v.getY());

    v.set(5, 6);
    assertEquals(5, v.getX());
    assertEquals(6, v.getY());
  }

  @Test
  public void testAdd() {
    V2D v1 = new V2D(1, 2);
    V2D v2 = new V2D(3, 4);
    assertEquals(new V2D(4, 6), V2D.add(v1, v2));

    v1.addSet(v2);
    assertEquals(new V2D(4, 6), v1);
  }

  @Test
  public void testSubtract() {
    V2D v1 = new V2D(1, 2);
    V2D v2 = new V2D(3, 4);
    assertEquals(new V2D(-2, -2), V2D.subtract(v1, v2));

    v1.subtractSet(v2);
    assertEquals(new V2D(-2, -2), v1);
  }

  @Test
  public void testScale() {
    V2D v1 = new V2D(1, 2);
    float scale = 2.0f;
    assertEquals(new V2D(2, 4), V2D.scale(v1, scale));

    v1.scaleSet(scale);
    assertEquals(new V2D(2, 4), v1);
  }

  @Test
  public void testEqualsAndHashCode() {
    V2D v1 = new V2D(1, 2);
    V2D v2 = new V2D(1, 2);
    V2D v3 = new V2D(3, 4);

    assertEquals(v1, v2);
    assertEquals(v1.hashCode(), v2.hashCode());
    assertEquals(false, v1.equals(v3));
  }

  @Test
  public void testInvalidConstructorArguments() {

    assertThrows(
        IllegalArgumentException.class,
        () -> {
          new V2D(Float.NaN, 0);
        },
        "Expected constructor to throw, but it didn't");

    assertThrows(
        IllegalArgumentException.class,
        () -> {
          new V2D(Float.NEGATIVE_INFINITY, 0);
        },
        "Expected constructor to throw, but it didn't");

    assertThrows(
        IllegalArgumentException.class,
        () -> {
          new V2D(0, Float.NaN);
        },
        "Expected constructor to throw, but it didn't");

    assertThrows(
        IllegalArgumentException.class,
        () -> {
          new V2D(0, Float.NEGATIVE_INFINITY);
        },
        "Expected constructor to throw, but it didn't");
  }
}
