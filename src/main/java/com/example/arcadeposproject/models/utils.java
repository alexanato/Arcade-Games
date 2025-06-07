package com.example.arcadeposproject.models;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import toxi.geom.Vec2D;

public class utils {
    public static boolean load(String filename) {

        ObjectMapper mapper = new ObjectMapper();

        // Datei aus dem Ressourcenordner laden
        ClassLoader classLoader = utils.class.getClassLoader();
        return false;
        // JSON in Map lesen
        // Zugriff auf Daten
        //return Map;
    }
    public static boolean intersectRaySegment(Vec2D p,  Vec2D a, Vec2D b) {
        if (a.y > b.y) {
            Vec2D temp = a;
            a = b;
            b = temp;
        }

        if (p.y == a.y || p.y == b.y) {
            p = new Vec2D(p.x, p.y + 0.0001f);
        }
        if (p.y > a.y && p.y < b.y) {
            float xIntersect = a.x + (p.y - a.y) * (b.x - a.x) / (b.y - a.y);
            return xIntersect > p.x;
        }
        return false;
    }
    public static Vec2D calcDirToLine(Vec2D A, Vec2D B, Vec2D P) {
        Vec2D AB = B.sub(A);
        Vec2D AP = P.sub(A);

        Vec2D n = new Vec2D(-AB.y, AB.x);

        float skalar = AP.dot(n) / n.magSquared();
        Vec2D distanzRichtung = n.scale(skalar);

        return distanzRichtung;
    }
}
