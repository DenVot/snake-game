package org.homework;

public record Vector(int x, int y) {
  public Vector plus(Vector a) {
    return new Vector(x + a.x(), y + a.y());
  }
}
