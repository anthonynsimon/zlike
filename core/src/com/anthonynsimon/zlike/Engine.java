package com.anthonynsimon.zlike;

import com.anthonynsimon.zlike.components.Component;
import com.anthonynsimon.zlike.systems.System;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Engine {
    private int idSeq = 0;
    public final Map<Integer, LinkedList<Component>> entities = new HashMap<>();
    public final List<System> systems = new LinkedList<>();

    public int newEntityId() {
        idSeq += 1;
        entities.put(idSeq, new LinkedList<>());
        return idSeq;
    }

    public void addComponent(int entityId, Component component) {
        LinkedList<Component> components = entities.get(entityId);
        if (components != null) {
            components.add(component);
        }
    }

    public Component findEntityComponent(int entityId, Class<? extends Component> componentClass) {
        LinkedList<Component> components = entities.get(entityId);
        if (components == null) {
            return null;
        }
        return components.stream()
                .filter(c -> c.getClass() == componentClass)
                .findFirst()
                .orElse(null);
    }
}
