package com.codenjoy.dojo.sample.model;

import com.codenjoy.dojo.services.*;

/**
 * Это реализация героя. Обрати внимание, что он имплементит {@see Joystick}, а значит может быть управляем фреймворком
 * Так же он имплементит {@see Tickable}, что значит - есть возможность его оповещать о каждом тике игры.
 */
public class Hero extends PointImpl implements Joystick, Tickable, State<Elements, Player> {

    public static final int ABILITY_LIFE_TIME = 10;
    public static final int START_HEALTH = 100;
    public static final int DEFFENCE_MULTIPLICATOR = 2;
    private Field field;
    private boolean alive;
    private Direction direction;
    private Direction previousDirection;
    private int deathTimeCounter;
    private Ability ability;
    private int health;


    private int abilityCounter;

    public Hero(Point xy) {
        super(xy);
        direction = null;
        alive = true;
        this.deathTimeCounter = 0;
        this.abilityCounter = 0;
        this.health = START_HEALTH;
    }

    public void setAlive(boolean pAlive) {
        alive = pAlive;
    }

    public void init(Field field) {
        this.field = field;
    }

    @Override
    public void down() {
        if (!alive) return;

        direction = Direction.DOWN;
    }

    @Override
    public void up() {
        if (!alive) return;

        direction = Direction.UP;
    }

    @Override
    public void left() {
        if (!alive) return;

        direction = Direction.LEFT;
    }

    @Override
    public void right() {
        if (!alive) return;

        direction = Direction.RIGHT;
    }

    public Direction getPreviousDirection() {
        return previousDirection;
    }

    @Override
    public void act(int... p) {
        if (!alive) return;

        field.fireBullet(x, y, previousDirection, field, this);
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public void tick() {
        if (!alive) return;

        if (direction != null) {
            int newX = direction.changeX(x);
            int newY = direction.changeY(y);

//            if (field.isBomb(newX, newY)) {
//                alive = false;
//                field.removeBomb(newX, newY);
//            }

            if (!field.isBarrier(newX, newY)) {
                move(newX, newY);
            }
        }
        previousDirection = direction;
        direction = null;
        if (abilityCounter != 0 && --abilityCounter == 0){
            ability = null;
        }
    }

    public boolean isAlive() {
        return alive;
    }

    @Override
    public Elements state(Player player, Object... alsoAtPoint) {
        if (!isAlive()) {
            return Elements.DEAD_HERO;
        }

        if (this == player.getHero()) {
            return Elements.HERO;
        } else {
            return Elements.OTHER_HERO;
        }
    }

    public int getDeathTimeCounter() {
        return deathTimeCounter;
    }

    public void setDeathTimeCounter(int pDeathTimeCounter) {
        deathTimeCounter = pDeathTimeCounter;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability pAbility) {
        ability = pAbility;
        abilityCounter = ABILITY_LIFE_TIME;
    }

    public int getAbilityCounter() {
        return abilityCounter;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int pHealth) {
        health = pHealth;
    }

    public void setDamage(int pDamage) {

        health -= ((ability != null && ability.getAbilityType() == Ability.Type.DEFENCE) ? pDamage/DEFFENCE_MULTIPLICATOR : pDamage);

        Math.max(0, health);
    }
}
