package other;

public class Vec2D {
    private final double x;
    private final double y;

//    Constructors
    public Vec2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Vec2D(Vec2D vec2d) {
        this(vec2d.x, vec2d.y);
    }

    public Vec2D() {
        this(0, 0);
    }

//    Getters
    public String toString() {
        return ("(" + this.x + "," + this.y + ")");
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }


//    Comparators
    public boolean equals(Vec2D other) {
        return this.x == other.x && this.y == other.y;
    }

//    Operators
//      Add
    public Vec2D add(Vec2D other) {
        return new Vec2D(this.x + other.x, this.y + other.y);
    }

    public Vec2D add(double x, double y) {
        return new Vec2D(this.x + x, this.y + y);
    }

    public Vec2D add(double c) {
        return this.add(c, c);
    }

//      Subtract
    public Vec2D subtract(Vec2D other) {
        return new Vec2D(this.x - other.x, this.y - other.y);
    }

    public Vec2D subtract(double x, double y) {
        return new Vec2D(this.x - x, this.y - y);
    }

    public Vec2D subtract(double c) {
        return this.subtract(c, c);
    }

//      Multiply
    public Vec2D multiply(Vec2D other) {
        return new Vec2D(this.x * other.x, this.y * other.y);
    }

    public Vec2D multiply(double x, double y) {
        return new Vec2D(this.x * x, this.y * y);
    }

    public Vec2D multiply(double c) {
        return this.multiply(c, c);
    }

//      Divide
    public Vec2D divide(Vec2D other) {
        return new Vec2D(this.x / other.x, this.y / other.y);
    }

    public Vec2D divide(double x, double y) {
        return new Vec2D(this.x / x, this.y / y);
    }

    public Vec2D divide(double c) {
        return this.divide(c, c);
    }

//      Reverse
    public Vec2D reverse() {
        return this.multiply(-1);
    }

//      Floor
    public Vec2D floor() {
        return new Vec2D(Math.floor(this.x), Math.floor(this.y));
    }

    public Vec2D ceil() {
        return new Vec2D(Math.ceil(this.x), Math.ceil(this.y));
    }

    public double abs() {
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }
}
