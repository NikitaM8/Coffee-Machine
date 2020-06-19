package machine;

import java.util.Arrays;
import java.util.Scanner;

public class test {

    static void setUpX(Robot robot, int toX) {
        if (toX < robot.getX() && robot.getDirection() != Direction.LEFT) {

            //set right direction to first pass of robot
            switch (robot.getDirection()) {
                case RIGHT:
                    robot.turnRight();
                    robot.turnRight();
                    break;
                case UP:
                    robot.turnLeft();
                    break;
                case DOWN:
                    robot.turnRight();
                    break;
                default:
                    break;
            }

        }

        if (toX > robot.getX() && robot.getDirection() != Direction.RIGHT) {

            switch (robot.getDirection()) {
                case UP:
                    robot.turnRight();
                    break;
                case DOWN:
                    robot.turnLeft();
                    break;
                case LEFT:
                    robot.turnRight();
                    robot.turnRight();
                    break;
                default:
                    break;
            }

        }
    }

    static void setUpY(Robot robot, int toY) {
        if (toY < robot.getY() && robot.getDirection() != Direction.DOWN) {

            switch (robot.getDirection()) {
                case UP:
                    robot.turnRight();
                    robot.turnRight();
                    break;
                case LEFT:
                    robot.turnLeft();
                    break;
                case RIGHT:
                    robot.turnRight();
                    break;
                default:
                    break;
            }

        }

        if (toY > robot.getY() && robot.getDirection() != Direction.UP) {

            switch (robot.getDirection()) {
                case RIGHT:
                    robot.turnLeft();
                    break;
                case DOWN:
                    robot.turnRight();
                    robot.turnRight();
                    break;
                case LEFT:
                    robot.turnRight();
                    break;
                default:
                    break;
            }

        }
    }

    public static void main(String[] args) {

        Robot robot = new Robot(0,0, Direction.UP);

        moveRobot(robot, 0, 10);
    }

    public static void moveRobot(Robot robot, int toX, int toY) {

        //set up direction to X
        setUpX(robot, toX);

        //move robot ot destination X
        while (toX != robot.getX()) {
            robot.stepForward();
        }

        //set up direction to Y
        setUpY(robot, toY);

        //move robot to destination Y
        while (toY != robot.getY()) {
            robot.stepForward();
        }

    }
}

enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Direction turnLeft() {
        switch (this) {
            case UP:
                return LEFT;
            case DOWN:
                return RIGHT;
            case LEFT:
                return DOWN;
            case RIGHT:
                return UP;
            default:
                throw new IllegalStateException();
        }
    }

    public Direction turnRight() {
        switch (this) {
            case UP:
                return RIGHT;
            case DOWN:
                return LEFT;
            case LEFT:
                return UP;
            case RIGHT:
                return DOWN;
            default:
                throw new IllegalStateException();
        }
    }

    public int dx() {
        return dx;
    }

    public int dy() {
        return dy;
    }
}

class Robot {
    private int x;
    private int y;
    private Direction direction;

    public Robot(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void turnLeft() {
        direction = direction.turnLeft();
        System.out.println("Turn Left");
    }

    public void turnRight() {
        direction = direction.turnRight();
        System.out.println("Turn Right");
    }

    public void stepForward() {
        x += direction.dx();
        y += direction.dy();
        System.out.println("Step Forward");
    }

    public Direction getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}