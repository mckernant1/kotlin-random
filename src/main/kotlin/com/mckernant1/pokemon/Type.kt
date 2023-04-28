package com.mckernant1.pokemon

enum class Type() {
    Normal,
    Fire,
    Water,
    Grass,
    Electric,
    Flying,
    Ground,
    Rock,
    Fighting,
    Ice,
    Poison,
    Bug,
    Ghost,
    Psychic,
    Dragon,
    Dark,
    Fairy,
    Steel
    ;

    companion object {
        val strengthRelations: List<TypeStrength> = listOf(
            TypeStrength(Normal, 0.5, Rock),
            TypeStrength(Normal, 0.5, Steel),
            TypeStrength(Normal, 0.0, Ghost),

            TypeStrength(Fighting, 2.0, Normal),
            TypeStrength(Fighting, 2.0, Rock),
            TypeStrength(Fighting, 2.0, Steel),
            TypeStrength(Fighting, 2.0, Ice),
            TypeStrength(Fighting, 2.0, Dark),
            TypeStrength(Fighting, 0.5, Flying),
            TypeStrength(Fighting, 0.5, Poison),
            TypeStrength(Fighting, 0.5, Bug),
            TypeStrength(Fighting, 0.5, Psychic),
            TypeStrength(Fighting, 0.5, Fairy),
            TypeStrength(Fighting, 0.0, Ghost),

            TypeStrength(Flying, 2.0, Fighting),
            TypeStrength(Flying, 2.0, Bug),
            TypeStrength(Flying, 2.0, Grass),
            TypeStrength(Flying, 0.5, Rock),
            TypeStrength(Flying, 0.5, Steel),
            TypeStrength(Flying, 0.5, Electric),

            TypeStrength(Poison, 2.0, Grass),
            TypeStrength(Poison, 2.0, Fairy),
            TypeStrength(Poison, 0.5, Poison),
            TypeStrength(Poison, 0.5, Ground),
            TypeStrength(Poison, 0.5, Rock),
            TypeStrength(Poison, 0.5, Ghost),
            TypeStrength(Poison, 0.0, Steel),

            TypeStrength(Ground, 2.0, Poison),
            TypeStrength(Ground, 2.0, Rock),
            TypeStrength(Ground, 2.0, Steel),
            TypeStrength(Ground, 2.0, Fire),
            TypeStrength(Ground, 2.0, Electric),
            TypeStrength(Ground, 0.5, Bug),
            TypeStrength(Ground, 0.5, Grass),
            TypeStrength(Ground, 0.0, Flying),

            TypeStrength(Rock, 2.0, Flying),
            TypeStrength(Rock, 2.0, Bug),
            TypeStrength(Rock, 2.0, Fire),
            TypeStrength(Rock, 2.0, Ice),
            TypeStrength(Rock, 0.5, Fighting),
            TypeStrength(Rock, 0.5, Ground),
            TypeStrength(Rock, 0.5, Steel),

            TypeStrength(Bug, 2.0, Grass),
            TypeStrength(Bug, 2.0, Psychic),
            TypeStrength(Bug, 2.0, Dark),
            TypeStrength(Bug, 0.5, Fighting),
            TypeStrength(Bug, 0.5, Flying),
            TypeStrength(Bug, 0.5, Poison),
            TypeStrength(Bug, 0.5, Ghost),
            TypeStrength(Bug, 0.5, Steel),
            TypeStrength(Bug, 0.5, Fire),
            TypeStrength(Bug, 0.5, Fairy),

            TypeStrength(Ghost, 2.0, Ghost),
            TypeStrength(Ghost, 2.0, Psychic),
            TypeStrength(Ghost, 0.5, Dark),
            TypeStrength(Ghost, 0.0, Normal),

            TypeStrength(Steel, 2.0, Rock),
            TypeStrength(Steel, 2.0, Ice),
            TypeStrength(Steel, 2.0, Fairy),
            TypeStrength(Steel, 0.5, Steel),
            TypeStrength(Steel, 0.5, Fire),
            TypeStrength(Steel, 0.5, Water),
            TypeStrength(Steel, 0.5, Electric),

            TypeStrength(Fire, 2.0, Bug),
            TypeStrength(Fire, 2.0, Steel),
            TypeStrength(Fire, 2.0, Grass),
            TypeStrength(Fire, 2.0, Ice),
            TypeStrength(Fire, 0.5, Rock),
            TypeStrength(Fire, 0.5, Fire),
            TypeStrength(Fire, 0.5, Water),
            TypeStrength(Fire, 0.5, Dragon),

            TypeStrength(Water, 2.0, Ground),
            TypeStrength(Water, 2.0, Rock),
            TypeStrength(Water, 2.0, Fire),
            TypeStrength(Water, 0.5, Water),
            TypeStrength(Water, 0.5, Grass),
            TypeStrength(Water, 0.5, Dragon),

            TypeStrength(Grass, 2.0, Ground),
            TypeStrength(Grass, 2.0, Rock),
            TypeStrength(Grass, 2.0, Water),
            TypeStrength(Grass, 0.5, Flying),
            TypeStrength(Grass, 0.5, Poison),
            TypeStrength(Grass, 0.5, Bug),
            TypeStrength(Grass, 0.5, Steel),
            TypeStrength(Grass, 0.5, Fire),
            TypeStrength(Grass, 0.5, Grass),
            TypeStrength(Grass, 0.5, Dragon),

            TypeStrength(Electric, 2.0, Flying),
            TypeStrength(Electric, 2.0, Water),
            TypeStrength(Electric, 0.5, Grass),
            TypeStrength(Electric, 0.5, Electric),
            TypeStrength(Electric, 0.5, Dragon),
            TypeStrength(Electric, 0.0, Ground),

            TypeStrength(Psychic, 2.0, Fighting),
            TypeStrength(Psychic, 2.0, Poison),
            TypeStrength(Psychic, 0.5, Steel),
            TypeStrength(Psychic, 0.5, Psychic),
            TypeStrength(Psychic, 0.0, Dark),

            TypeStrength(Ice, 2.0, Flying),
            TypeStrength(Ice, 2.0, Ground),
            TypeStrength(Ice, 2.0, Grass),
            TypeStrength(Ice, 2.0, Dragon),
            TypeStrength(Ice, 0.5, Steel),
            TypeStrength(Ice, 0.5, Fire),
            TypeStrength(Ice, 0.5, Water),
            TypeStrength(Ice, 0.5, Ice),

            TypeStrength(Dragon, 2.0, Dragon),
            TypeStrength(Dragon, 0.5, Steel),
            TypeStrength(Dragon, 0.0, Fairy),

            TypeStrength(Dark, 2.0, Ghost),
            TypeStrength(Dark, 2.0, Psychic),
            TypeStrength(Dark, 0.5, Fighting),
            TypeStrength(Dark, 0.5, Dark),
            TypeStrength(Dark, 0.5, Fairy),

            TypeStrength(Fairy, 2.0, Fighting),
            TypeStrength(Fairy, 2.0, Dragon),
            TypeStrength(Fairy, 2.0, Dark),
            TypeStrength(Fairy, 0.5, Poison),
            TypeStrength(Fairy, 0.5, Steel),
            TypeStrength(Fairy, 0.5, Fire),
        )
    }

    /**
     * Types in this list are weak against this type
     */
    fun superEffectiveAgainst(): List<Type> = strengthRelations
        .asSequence()
        .filter { it.attacking == this }
        .filter { it.multiplier > 1 }
        .map { it.defending }
        .toList()

    /**
     * Types in this list are strong against this type
     */
    fun notVeryEffectiveAgainst(): List<Type> = strengthRelations
        .asSequence()
        .filter { it.attacking == this }
        .filter { it.multiplier < 1 }
        .map { it.defending }
        .toList()

    /**
     * This type is strong against these types
     */
    fun resists(): List<Type> = strengthRelations
        .asSequence()
        .filter { it.defending == this }
        .filter { it.multiplier < 1 }
        .map { it.attacking }
        .toList()

    /**
     * This type is weak against these types
     */
    fun superEffectiveAgainstThis(): List<Type> = strengthRelations
        .asSequence()
        .filter { it.defending == this }
        .filter { it.multiplier > 1 }
        .map { it.attacking }
        .toList()

}

data class TypeStrength(
    val attacking: Type,
    val multiplier: Multiplier,
    val defending: Type
)
