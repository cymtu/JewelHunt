package org.jewelhunt.ai;

/**
 * Перечисление типов ИИ
 * @author Барабанов А.В.
 * @version 1.0.1
 */
public enum AiTypes {
    Min("AiTypes.Min"),
    Average("AiType.Average");

    private final String name;

    AiTypes(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
