package com.codenjoy.dojo.sample.model;

import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;
import com.codenjoy.dojo.services.State;

/**
 * Артефакт Бомба на поле
 */
public class Bullet extends PointImpl implements State<Elements, Player> {

    public Bullet(int x, int y) {
        super(x, y);
    }

    public Bullet(Point point) {
        super(point);
    }

    @Override
    public Elements state(Player player, Object... alsoAtPoint) {
        return Elements.BULLET;
    }
}
