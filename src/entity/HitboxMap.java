package entity;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class HitboxMap {

    static Map<Player, Rectangle> bodyHitboxMap = new HashMap<>();

    private HitboxMap() {

    }

    public static void insertPlayerHitbox(Player player, Rectangle hitbox) {
        bodyHitboxMap.put(player, hitbox);
    }

    public static void removePlayerHitbox(Player player) {
        bodyHitboxMap.remove(player);
    }



}
