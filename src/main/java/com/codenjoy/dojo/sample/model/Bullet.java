package com.codenjoy.dojo.sample.model;

import com.codenjoy.dojo.services.*;

/**
 * Артефакт Бомба на поле
 */
public class Bullet extends PointImpl implements Tickable, State<Elements, Player> {
    private Direction direction;

    private Field field;

    public Field getField() {
        return field;
    }

    public Bullet(int x, int y, Direction direction, Field field) {
        super(x, y);
        this.direction = direction;
        this.field = field;
    }

    public Direction getDirection() {
        return direction;
    }
//    public Bullet(Point point) {
//        super(point);
//    }

    @Override
    public Elements state(Player player, Object... alsoAtPoint) {
        return Elements.BULLET;
    }

    @Override
    public void tick() {
        if (direction != null) {
            int newX = direction.changeX(x);
            int newY = direction.changeY(y);

//            if (field.isBomb(newX, newY)) {
//                alive = false;
//                field.removeBomb(newX, newY);
//            }

            if (!field.isBarrier(newX, newY)) {
                move(newX, newY);
                x = newX;
                y = newY;
            }
        }
//        direction = null;
    }
}
