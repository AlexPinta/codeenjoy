package com.codenjoy.dojo.sample.model;

import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;
import com.codenjoy.dojo.services.State;

/**
 * Артефакт Золото на поле
 */
public class Ability extends PointImpl implements State<Elements, Player> {

    enum Type {WEAPON, DEFENCE}

    private Type abilityType;

    public Ability(int x, int y, Dice dice) {
        super(x, y);
        int randomChoice = dice.next(Type.values().length);
        for (Type elem : Type.values()){
            if (elem.ordinal() == randomChoice){
                abilityType = elem;
            }
        }
    }

    public Ability(Point point, Type abilityType) {
        super(point);
        this.abilityType = abilityType;
    }

    @Override
    public Elements state(Player player, Object... alsoAtPoint) {
        if (abilityType == Type.WEAPON){
            return Elements.SUPER_ATTACK;
        } else {
            return Elements.SUPER_DEFENCE;
        }
    }
}
