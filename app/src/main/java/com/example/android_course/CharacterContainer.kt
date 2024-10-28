package com.example.android_course

class CharactersContainer {
    private var _characters : MutableMap<String?, Character> = mutableMapOf()
    private var _deaultLink = "https://i.pinimg.com/564x/0e/9b/1e/0e9b1e7319dae4b8046c5366d62532e3.jpg"

    fun addCharacter(character : Character)
    {
        _characters[character.getName()] = character
    }

    fun getCharacterByName(name: String?) : Character
    {
        return _characters.getOrDefault(
            key = name,
            defaultValue = Character(
                name = "Undefined",
                resourcesLink = _deaultLink,
                welcomeMessage = "Undefined"
            )
        )
    }
}