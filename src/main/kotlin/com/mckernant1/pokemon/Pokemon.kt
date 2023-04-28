package com.mckernant1.pokemon

data class Pokemon(
    val name: String,
    val types: List<Type>
) {

    fun superEffectiveAgainstThis() {
        types.flatMap { it.superEffectiveAgainstThis() }
            .associate {
                it to it
            }

        val pokemonWeaknesses = mutableListOf<Type>()



    }


}
